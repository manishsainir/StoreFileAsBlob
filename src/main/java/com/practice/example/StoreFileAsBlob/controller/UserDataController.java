package com.practice.example.StoreFileAsBlob.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.practice.example.StoreFileAsBlob.model.UserDataVO;
import com.practice.example.StoreFileAsBlob.service.UserDataService;

@Controller
public class UserDataController {

	@Autowired
	UserDataService userDataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView load() {
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/gotoInsertData", method = RequestMethod.GET)
	ModelAndView gotoInsertData() {
		return new ModelAndView("insertData");
	}

	@RequestMapping(value = "/insertData", method = RequestMethod.POST)
	ModelAndView insertData(@RequestParam(name = "textFile") MultipartFile textFile,
			@RequestParam(name = "imageFile") MultipartFile imageFile) throws IOException {
		UserDataVO userDataVO = new UserDataVO();
		userDataVO.setImageName(imageFile.getOriginalFilename());
		userDataVO.setImageFile(imageFile.getBytes());
		userDataVO.setTextFileName(textFile.getOriginalFilename());
		userDataVO.setTextFile(textFile.getBytes());
		this.userDataService.insertData(userDataVO);
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/showData", method = RequestMethod.GET)
	ModelAndView showImages() throws UnsupportedEncodingException, FileNotFoundException {
		List<UserDataVO> list = this.userDataService.search();

		List<List<String>> list2 = new ArrayList<List<String>>();

		for (int i = 0; i < list.size(); i++) {
			list2.add(new ArrayList<String>(Arrays.asList(String.valueOf(list.get(i).getId()),
					list.get(i).getImageName(), list.get(i).getTextFileName())));
		}

		return new ModelAndView("showData", "getName", list2);
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam("userId") String userId, @RequestParam("name") String name) {
		UserDataVO userDataVO = this.userDataService.searchById(Integer.parseInt(userId));
		String file = "";

		if (name.equals("image")) {
			byte[] bytes = userDataVO.getImageFile();
			byte[] encodeBase64 = Base64.encodeBase64(bytes);
			String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
			file = base64Encoded;

		} else if (name.equals("text")) {

			byte[] bytes2 = userDataVO.getTextFile();
			String str = new String(bytes2, StandardCharsets.UTF_8);
			file = str;
		}
		return new ModelAndView("showfile", "file", file).addObject("name", name);
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getDownloadData(@RequestParam("userId") String userId,
			@RequestParam("name") String name) throws Exception {
		UserDataVO userDataVO = this.userDataService.searchById(Integer.parseInt(userId));
		byte[] output = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		if (name.equals("image")) {
			output = userDataVO.getImageFile();
			responseHeaders.setContentType(MediaType.valueOf("images/png"));
			responseHeaders.set("Content-disposition", "attachment; filename=" + userDataVO.getImageName());
		} else if (name.equals("text")) {
			output = userDataVO.getTextFile();
			responseHeaders.setContentType(MediaType.valueOf("text/html"));
			responseHeaders.set("Content-disposition", "attachment; filename=" + userDataVO.getTextFileName());
		}
		responseHeaders.set("charset", "utf-8");
		responseHeaders.setContentLength(output.length);
		return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
	}
}

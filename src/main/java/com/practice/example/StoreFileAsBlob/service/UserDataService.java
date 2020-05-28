package com.practice.example.StoreFileAsBlob.service;

import java.util.List;

import com.practice.example.StoreFileAsBlob.model.UserDataVO;



public interface UserDataService {

	void insertData(UserDataVO userDataVO);

	List<UserDataVO> search();

	UserDataVO searchById(Integer id);

}

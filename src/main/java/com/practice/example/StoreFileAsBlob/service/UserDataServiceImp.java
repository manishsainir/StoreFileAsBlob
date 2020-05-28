package com.practice.example.StoreFileAsBlob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.example.StoreFileAsBlob.dao.UserDataDao;
import com.practice.example.StoreFileAsBlob.model.UserDataVO;

@Service
@Transactional
public class UserDataServiceImp implements UserDataService {

	@Autowired
	UserDataDao userDataDao;

	@Override
	public void insertData(UserDataVO userDataVO) {
		this.userDataDao.insertData(userDataVO);

	}

	@Override
	public List<UserDataVO> search() {
		return this.userDataDao.search();
	}

	@Override
	public UserDataVO searchById(Integer id) {

		return this.userDataDao.searchById(id);
	}

}

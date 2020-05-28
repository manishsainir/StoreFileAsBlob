package com.practice.example.StoreFileAsBlob.dao;

import java.util.List;

import com.practice.example.StoreFileAsBlob.model.UserDataVO;



public interface UserDataDao {

	void insertData(UserDataVO userDataVO);

	List search();

	UserDataVO searchById(Integer id);

}

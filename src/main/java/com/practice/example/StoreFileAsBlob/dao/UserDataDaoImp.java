package com.practice.example.StoreFileAsBlob.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.example.StoreFileAsBlob.model.UserDataVO;

@Repository
public class UserDataDaoImp implements UserDataDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void insertData(UserDataVO userDataVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userDataVO);
	}

	@Override
	public List<UserDataVO> search() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDataVO> q = session.createQuery("from UserDataVO");
		List<UserDataVO> list = q.list();
		return list;
	}

	@Override
	public UserDataVO searchById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDataVO> q = session.createQuery("from UserDataVO where id=" + id + "");
		List<UserDataVO> list = q.list();
		return (UserDataVO) list.get(0);
	}

}

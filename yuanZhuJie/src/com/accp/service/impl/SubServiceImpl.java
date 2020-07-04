package com.accp.service.impl;

import java.util.List;

import com.accp.dao.ISubDao;
import com.accp.dao.impl.SubDaoImpl;
import com.accp.service.ISubService;
import com.accp.student.Subject;

public class SubServiceImpl implements ISubService{
  SubDaoImpl dao=new SubDaoImpl(); 
	@Override
	public List<Subject> query() {
		// TODO Auto-generated method stub
		return dao.query();
	}

	@Override
	public Subject queryById(Integer id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}

	@Override
	public boolean insert(Subject sub) {
		// TODO Auto-generated method stub
		return dao.insert(sub);
	}

	@Override
	public boolean update(Subject sub) {
		// TODO Auto-generated method stub
		return dao.update(sub);
	}

	@Override
	public boolean delete(Subject sub) {
		// TODO Auto-generated method stub
		return dao.delete(sub);
	}

	@Override
	public boolean update2(Subject sub) {
		// TODO Auto-generated method stub
		return dao.update2(sub);
	}

	@Override
	public boolean delete1(Subject sub) {
		// TODO Auto-generated method stub
		return dao.delete1(sub);
	}



}

package com.accp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.accp.dao.BaseDao;
import com.accp.dao.ISubDao;
import com.accp.service.ISubService;
import com.accp.student.Subject;

public class SubDaoImpl extends StudentDaoD implements ISubDao{

	@Override
	public List<Subject> query() {
		
		try {
			return super.queryPlus(Subject.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Subject queryById(Integer id) {
		// TODO Auto-generated method stub
		try {
			return super.queryById(Subject.class, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Subject sub) {
		// TODO Auto-generated method stub
		return super.insert(sub);
	}

	@Override
	public boolean update(Subject sub) {
		// TODO Auto-generated method stub
		return super.update(sub,sub.getId());
	}

	@Override
	public boolean delete(Subject sub) {
		// TODO Auto-generated method stub
		return super.delete(Subject.class,sub.getId());
	}

	@Override
	public boolean update2(Subject sub) {
		// TODO Auto-generated method stub
		return super.update2(sub);
	}

	@Override
	public boolean delete1(Subject sub) {
		// TODO Auto-generated method stub
	try {
		return super.delete1(sub);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
}

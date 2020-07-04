package com.accp.service;

import java.util.List;

import com.accp.student.Subject;

public interface ISubService {
	
		public List<Subject> query();
		public Subject queryById(Integer id);
		public boolean insert(Subject sub);
        public boolean update(Subject sub); 
	    public boolean delete(Subject sub);
	    public boolean update2(Subject sub);
	    public boolean delete1(Subject sub);

		
}

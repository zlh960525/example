package com.accp.student;

import com.accp.annotation.Column;
import com.accp.annotation.Table;

@Table("student")
public class Subject {
	
   public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	@Column(value="id",isPrimary=true)
    private Integer id;
	@Column("stuName")
    private String subjectName;
	@Column("age")
    private Integer gradeId;
}

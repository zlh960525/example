package com.accp.dao.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.accp.annotation.Column;
import com.accp.annotation.Table;
import com.accp.dao.BaseDao;
import com.accp.student.Subject;

public class StudentDaoD extends BaseDao {

	private static final int ArrayList = 0;

	public <T> List<T> find(Class<T> type) {
		String sql = "select ";
		Field[] fields = type.getDeclaredFields();
		for (Field f : fields) {
			Column column = f.getDeclaredAnnotation(Column.class);
			if (column != null) {
				sql += column.value() + ",";
			}
		}
		sql = sql.substring(0, sql.length() - 1);
		String tabName = type.getDeclaredAnnotation(Table.class).value();
		sql += " from " + tabName;
		System.out.println(sql);
		try {
			ResultSet rs = super.query(sql);
			List<T> list = new ArrayList<>();
			while (rs.next()) {

				Object object = type.newInstance();
				for (Field f : fields) {
					f.setAccessible(true);
					Column column = f.getDeclaredAnnotation(Column.class);
					if (column != null) {
						Object value = rs.getObject(column.value());
						f.set(object, value);
					}
				}
				list.add((T) object);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public <T> List<T> queryPlus(Class<T> type) throws SQLException {
		String sql = "select * from " + type.getSimpleName() + "";

		ResultSet rs = super.query(sql);
		List<T> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Field[] fields = type.getDeclaredFields();
				Object objects = type.newInstance();
				for (int i = 0; i < fields.length; i++) {
					Object value = rs.getObject(fields[i].getName());
					fields[i].setAccessible(true);
					fields[i].set(objects, value);
				}
				list.add((T) objects);
			}
			return list;
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public <T> T queryById(Class<T> type, Object uId) throws SQLException {
		String sql = "select * from " + type.getSimpleName() + " where id=" + uId;

		ResultSet rs = super.query(sql);
		List<T> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Field[] fields = type.getDeclaredFields();
				Object objects = type.newInstance();
				for (int i = 0; i < fields.length; i++) {
					Object value = rs.getObject(fields[i].getName());
					fields[i].setAccessible(true);
					fields[i].set(objects, value);
				}
				return (T) objects;
			}

		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public boolean insert(Object objects) {
		Class cls = objects.getClass();
		String tabName = null;
		try {
			tabName = ((Table) cls.getDeclaredAnnotation(Table.class)).value();
			StringBuffer bf = new StringBuffer("insert into  ");
			bf.append(tabName + "(");
			Field[] field = cls.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				Column column = field[i].getDeclaredAnnotation(Column.class);
				if (column != null&&column.isPrimary()==false) {
					bf.append(column.value() + ",");
				}

			}
			bf.delete(bf.length() - 1, bf.length());
			bf.append(") values(");
			for (int i = 0; i < field.length; i++) {

				field[i].setAccessible(true);
				Column column = field[i].getDeclaredAnnotation(Column.class);

				Object value = field[i].get(objects);
				if (column != null&&column.isPrimary()==false) {
					if (value == null) {
						bf.append("'" + 0 + "',");
					} else {
						bf.append("'" + value + "',");
					}
				}
			}
			bf.delete(bf.length() - 1, bf.length());

			bf.append(")");
			System.out.println(bf);
			super.update(bf.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

//	public boolean delete(Object obj,int id) {
//		Class cls=obj.getClass();	
//		try {
//			String	tblName=cls.getSimpleName();
//			StringBuffer bf=new StringBuffer("delete from ");	
//			bf.append(tblName+" where " );
//			Field [] field=cls.getDeclaredFields();				
//				field[0].setAccessible(true);	
//			    Object value=field[0].get(obj);
//			    bf.append(field[0].getName()+"="+id);		
//		
//			System.out.println(tblName);	
//			super.update(bf.toString());
//		}catch (Exception e) {
//			// TODO: handle exception			
//		}
//		
//		return false;
//	}
//	public boolean update(Object objects) {
//		Class cls=objects.getClass();
//	//	String sql="update student set subjectName=?,gradeId=? where Id=?";
//		String tblName=null;
//		try {
//			tblName= cls.getSimpleName();
//			StringBuffer bf=new StringBuffer("update ");
//			bf.append(tblName+ " set ");
//			Field [] field=cls.getDeclaredFields();			
//			for (int i = 0; i < field.length; i++){
//				field[i].setAccessible(true);
//				field[0].setAccessible(true);
//				if(i==field.length-1) {
//					Object value=field[i].get(objects);
//					bf.append(field[i].getName()+"='"+value+"' where  "+field[0].getName()+"="+field[0].get(objects));	
//					break;
//				}
//				Object value=field[i].get(objects);
//				bf.append(field[i].getName()+"='"+value+"', ");	
//			
//			}
//			
//			super.update(bf.toString());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return false;
//	}

	public boolean update(Object object, Object uId) {
		Class cls = object.getClass();
		StringBuffer bf = new StringBuffer("update " + cls.getSimpleName() + " set ");
		try {
			Field[] field = cls.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Object value = field[i].get(object);
				bf.append(field[i].getName() + "='" + value + "',");
			}
			bf.delete(bf.length() - 1, bf.length());
			bf.append(" where id=" + uId);
			
			super.update(bf.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}
	//元注解
	public boolean update2(Object object) {
		Class cls = object.getClass();
	  String tabName = ((Table) cls.getDeclaredAnnotation(Table.class)).value();
		StringBuffer bf = new StringBuffer("update " + tabName + " set ");
		try {
			String key="";
			Object keyValue=null;
			Field[] field = cls.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Column column = field[i].getDeclaredAnnotation(Column.class);
				if(column!=null&&column.isPrimary()==false) {
					Object value = field[i].get(object);
					bf.append(column.value() + "='" + value + "',");
				}else if(column!=null&&column.isPrimary()==true) {
					key=column.value();
					keyValue = field[i].get(object);
				}
			
			}
			bf.delete(bf.length() - 1, bf.length());
			bf.append(" where "+key+"='"+keyValue+"'");
			super.update(bf.toString());
			System.out.println(bf);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}
	public boolean delete(Class cls, Object uId) {
		String tblName = cls.getSimpleName();
		String sql = "delete from " + tblName + " where id=" + uId;
		try {
			return super.update(sql) > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	//元注解
	public boolean delete1(Object object) throws IllegalArgumentException, IllegalAccessException {
		Class cls=object.getClass();
		Table table=(Table)cls.getDeclaredAnnotation(Table.class);
		String tblName =table.value();
		Field [] field=cls.getDeclaredFields();
		String key="";
		Object keyValue=null;
		for(Field f:field) {
			f.setAccessible(true);
			Column col=f.getDeclaredAnnotation(Column.class);
			if(col!=null&&col.isPrimary()==true) {
				key=col.value();
				keyValue=f.get(object);
				break;
			}
		}
		String sql = "delete from " + tblName + " where "+key+"="+keyValue;
		System.out.println(sql);
		try {
			return super.update(sql) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}


	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException {
		StudentDaoD stu = new StudentDaoD();
		Subject sub=new Subject();
		sub.setSubjectName("你吗");
		sub.setGradeId(19);
		sub.setId(22);
	//	stu.update2(sub);
		stu.delete1(sub);
		List<Subject> list = stu.find(Subject.class);
		for (Subject s : list) {
			System.out.print(s.getSubjectName());
			System.out.println(s.getGradeId());

		}
		// 查询
//		List<Subject> ts = stu.queryPlus(Subject.class);
//		for (Subject sub : ts) {
//			System.out.println(sub.getSubjectName());
//		}
		// 添加
//		Subject sb=new Subject();
//		sb.setSubjectName(".Net002");
//		sb.setId(0);
//		sb.setGradeId(2);
//		stu.insert(sb);		

		// 修改
//          Subject sb=new Subject();
//        sb.setId(128);
//	    sb.setSubjectName("sffffs");
//		sb.setGradeId(2);
//		stu.update(sb,sb.getId());

		// Subject sb=new Subject();
		// stu.delete(sb,126);
		// stu.delete(Subject.class, 128);
	}

}

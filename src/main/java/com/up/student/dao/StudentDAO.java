/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-上午10:00:07
 */
package com.up.student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.up.student.base.BaseDAO;
import com.up.student.model.Admin;
import com.up.student.model.Student;

import javax.sound.midi.Soundbank;

/**
 * 模块说明： 学生增删改查
 * 
 */
public class StudentDAO extends BaseDAO {
	private final int fieldNum = 10;
	private final int showNum = 15;
	private static StudentDAO sd = null;

	public static synchronized StudentDAO getInstance() {
		if (sd == null) {
			sd = new StudentDAO();
		}
		return sd;
	}

	// update
	public boolean update(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {

			// update
			String sql = "update student set department=?,email=?,tel=?,hometown=? where username=?";
			String[] param = { stu.getDepartment(), stu.getEmail(), stu.getTel(), stu.getHomeTown(),
					 stu.getUsername() };
            System.out.println(stu.getUsername());
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}

	// delete
	public boolean delete(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		String sql = "delete from student where name=? and sno=?";
		String[] param = { stu.getName(), stu.getSno() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add
	public boolean add(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// check
			if (queryBySno(stu.getSno()) == 1) {
				return result;
			}
			System.out.println(stu.getClasz());
			// insert
			String sql = "insert into student(name,sno,sex,department,hometown,mark,email,tel,clasz) values(?,?,?,?,?,?,?,?,?)";
			String[] param = { stu.getName(), stu.getSno(), stu.getSex(), stu.getDepartment(), stu.getHomeTown(),
					stu.getMark(), stu.getEmail(), stu.getTel() ,stu.getClasz()};
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
	public boolean addAdmin(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// insert
			String sql = "insert into admin(name,username,password,admin) values(?,?,?,?)";
			String[] param = { stu.getName(), stu.getUsername(), stu.getPassword(), String.valueOf(stu.getAdmin())};
			String sqls = "insert into student(name,sno,sex,department,hometown,mark,email,tel,clasz,username) values(?,?,?,?,?,?,?,?,?,?)";
			String[] params = { stu.getName(), stu.getSno(), stu.getSex(), stu.getDepartment(), stu.getHomeTown(),
					stu.getMark(), stu.getEmail(), stu.getTel(),stu.getClasz() ,stu.getUsername()};
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
			if (db.executeUpdate(sqls, params) == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}


	// query by name
	public String[][] queryByName(String name) {
		String[][] result = null;
		if (name.length() < 0) {
			return result;
		}
		List<Student> stus = new ArrayList<Student>();
		int i = 0;
		String sql = "select * from student where username like ?";
		String[] param = { "%" + name + "%" };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// query
	public String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		List<Student> stus = new ArrayList<Student>();
		int i = 0;
		int beginNum = (pageNum - 1) * showNum;
		String sql = "select * from student limit ?,?";
		Integer[] param = { beginNum, showNum };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// 将rs记录添加到list中
	private void buildList(ResultSet rs, List<Student> list, int i) throws SQLException {
		Student stu = new Student();
		stu.setId(i + 1);
		stu.setName(rs.getString("name"));
		stu.setClasz(rs.getString("clasz"));
		stu.setDepartment(rs.getString("department"));
		stu.setEmail(rs.getString("email"));
		stu.setHomeTown(rs.getString("hometown"));
		stu.setMark(rs.getString("mark"));
		stu.setSex(rs.getString("sex"));
		stu.setSno(rs.getString("sno"));
		stu.setTel(rs.getString("tel"));
		list.add(stu);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, List<Student> stus, int j) {
		Student stu = stus.get(j);
		System.out.println("00000000000"+"----"+stu.getClasz());
		result[j][0] = String.valueOf(stu.getId());
		result[j][1] = stu.getName();
		result[j][2] = stu.getClasz();
		result[j][3] = stu.getSno();
		result[j][4] = stu.getSex();
		result[j][5] = stu.getDepartment();
		result[j][6] = stu.getHomeTown();
		result[j][7] = stu.getMark();
		result[j][8] = stu.getEmail();
		result[j][9] = stu.getTel();
	}

	// query by sno
	private int queryBySno(String sno) throws SQLException {
		int result = 0;
		if ("".equals(sno) || sno == null) {
			return result;
		}
		String checkSql = "select * from student where sno=?";
		String[] checkParam = { sno };
		rs = db.executeQuery(checkSql, checkParam);
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

}

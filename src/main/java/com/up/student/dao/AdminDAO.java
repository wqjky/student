/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-上午9:59:58
 */
package com.up.student.dao;

import java.sql.SQLException;

import com.up.student.base.BaseDAO;

/**
 * 模块说明： 管理员增删改查
 * 
 */
public class AdminDAO extends BaseDAO {

	private static AdminDAO ad = null;

	public static synchronized AdminDAO getInstance() {
		if (ad == null) {
			ad = new AdminDAO();
		}
		return ad;
	}

	public int queryForLogin(String username, String password) {
		int admin;
		if (username.length() == 0 || password.length() == 0) {
			return 0;
		}
		String sql = "select * from admin where username=? and password=?";
		String[] param = { username, password };
		rs = db.executeQuery(sql, param);
		try {
			if (rs.next()) {
				System.out.println(rs.getString("admin"));
				if(rs.getString("admin").equals("admin")){
					return 1;
				}else if(rs.getString("admin").equals("student")){
					return 2;
				}
			}else{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
		return 0;
	}

}

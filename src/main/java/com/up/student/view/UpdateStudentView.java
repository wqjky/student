/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月7日-上午11:07:57
 */
package com.up.student.view;

import com.up.student.AppConstants;
import com.up.student.DAO;
import com.up.student.base.BaseDAO;
import com.up.student.dao.StudentDAO;
import com.up.student.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 模块说明： 更新学生信息
 * 
 */
public class UpdateStudentView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField   department, hometown, email, tel;
	private String username;

	public UpdateStudentView(String username) {
		this.username = username;
		init();
	}

	private void init() {
		setTitle(AppConstants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));



		jPanelCenter.add(new JLabel(AppConstants.STUDENT_DEPARTMETN));
		department = new JTextField();
		jPanelCenter.add(department);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_HOMETOWN));
		hometown = new JTextField();
		jPanelCenter.add(hometown);

		jPanelCenter.add(new JLabel(AppConstants.STUDENT_EMAIL));
		email = new JTextField();
		jPanelCenter.add(email);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_TEL));
		tel = new JTextField();
		jPanelCenter.add(tel);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(AppConstants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).update(stu);
					System.out.println(isSuccess);
					if (isSuccess) {
						JOptionPane.showMessageDialog(null, "更新成功","",   JOptionPane.INFORMATION_MESSAGE);
						new StudentView(username);
					}
				}
			}
		});
		jPanelSouth.add(updateButton);
		exitButton = new JButton(AppConstants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ( "".equals(department.getText()) || "".equals(tel.getText())
				|| "".equals(email.getText()) || "".equals(hometown.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildStudent(Student stu) {
		stu.setName(username);
        stu.setUsername(username);
		stu.setDepartment(department.getText());
		stu.setEmail(email.getText());
		stu.setHomeTown(hometown.getText());
		stu.setTel(tel.getText());
	}

	private void setEmpty() {
		department.setText("");
		email.setText("");
		hometown.setText("");
		tel.setText("");
	}
}

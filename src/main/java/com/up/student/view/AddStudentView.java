/**
 * 〈〉
 *
 * @author 王青杰
 * @create 2020-03-138:07
 * @since 1.0.0
 */
package com.up.student.view;

import com.up.student.AppConstants;
import com.up.student.DAO;
import com.up.student.base.BaseDAO;
import com.up.student.dao.StudentDAO;
import com.up.student.model.Admin;
import com.up.student.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentView extends JFrame {

    private static final long serialVersionUID = -1984182788841566838L;

    private JPanel jPanelCenter, jPanelSouth;
    private JButton addButton, exitButton;
    private JTextField name, sno, department, hometown, mark, email, tel, sex,username,password,clasz;

    public AddStudentView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.ADDVIEW_TITLE);
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(12, 2));
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_NAME));
        name = new JTextField();
        jPanelCenter.add(name);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
        sno = new JTextField();
        jPanelCenter.add(sno);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SEX));
        sex = new JTextField();
        jPanelCenter.add(sex);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_DEPARTMETN));
        department = new JTextField();
        jPanelCenter.add(department);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_HOMETOWN));
        hometown = new JTextField();
        jPanelCenter.add(hometown);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_MARK));
        mark = new JTextField();
        jPanelCenter.add(mark);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_EMAIL));
        email = new JTextField();
        jPanelCenter.add(email);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_TEL));
        tel = new JTextField();
        jPanelCenter.add(tel);
        jPanelCenter.add(new JLabel("账号"));
        username = new JTextField();
        jPanelCenter.add(username);
        jPanelCenter.add(new JLabel("密码"));
        password = new JTextField();
        jPanelCenter.add(password);
        jPanelCenter.add(new JLabel("班级"));
        clasz = new JTextField();
        jPanelCenter.add(clasz);
        jPanelCenter.add(new JLabel("-------------------------------------------------"));
        jPanelCenter.add(new JLabel("-------------------------------------------------"));

        // south panel
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));
        addButton = new JButton(AppConstants.ADDVIEW_ADDBUTTON);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()) {
                    Student stu = new Student();
                    buildAdmin(stu);
                    boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).addAdmin(stu);
                    if (isSuccess) {
                        setEmpty();
                        if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
                            MainView.currPageNum = 1;
                        }
                        String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
                                .list(MainView.currPageNum);
                        MainView.initJTable(MainView.jTable, result);
                    }
                }
            }
        });
        jPanelSouth.add(addButton);
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
        setBounds(770, 400, 400, 370);
        setResizable(true);
        setVisible(true);
    }

    private boolean check() {
        boolean result = false;
        if ("".equals(name.getText()) || "".equals(username.getText()) || "".equals(password.getText())) {
            return result;
        } else {
            result = true;
        }
        return result;
    }

    private void buildAdmin(Student stu) {
        stu.setUsername(username.getText());
        stu.setPassword(password.getText());
        stu.setAdmin("student");
        stu.setDepartment(department.getText());
        stu.setEmail(email.getText());
        stu.setHomeTown(hometown.getText());
        stu.setMark(mark.getText());
        stu.setName(name.getText());
        stu.setSno(sno.getText());
        stu.setTel(tel.getText());
        stu.setSex(sex.getText());
        stu.setClasz(clasz.getText());
    }

    private void setEmpty() {
        JOptionPane.showMessageDialog(null, "添加成功","",   JOptionPane.INFORMATION_MESSAGE);
    }
}

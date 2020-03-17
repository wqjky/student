/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-下午1:37:39
 */
package com.up.student.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.up.student.AppConstants;
import com.up.student.DAO;
import com.up.student.base.BaseDAO;
import com.up.student.dao.StudentDAO;

/**
 * 模块说明： 学生页面
 *
 */
public class StudentView extends JFrame {

    private static final long serialVersionUID = 5870864087464173884L;
    private static String username;
    private final int maxPageNum = 99;

    private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
    private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonAdd, jButtonDelete, jButtonUpdate,
            jButtonFind;
    private JLabel currPageNumJLabel;
    private JTextField condition;
    public static JTable jTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;
    private String admin;

    public static String[] column = { "id", AppConstants.STUDENT_NAME,"班级", AppConstants.STUDENT_SNO,
            AppConstants.STUDENT_SEX, AppConstants.STUDENT_DEPARTMETN, AppConstants.STUDENT_HOMETOWN,
            AppConstants.STUDENT_MARK, AppConstants.STUDENT_EMAIL, AppConstants.STUDENT_TEL };
    public static int currPageNum = 1;



    public StudentView(String username) {
        this.username = username;
        init();
    }

    private void init() {
        setTitle(AppConstants.MAINVIEW_TITLE+"--欢迎您-- "+username+" 登录");

        // north panel
        jPanelNorth = new JPanel();
        jPanelNorth.setLayout(new GridLayout(1, 5));
       /* condition = new JTextField(AppConstants.PARAM_FIND_CONDITION);
        condition.addKeyListener(new FindListener());
        jPanelNorth.add(condition);
        // query by name
        jButtonFind = new JButton(AppConstants.PARAM_FIND);
        jButtonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                find();
            }
        });
        jButtonFind.addKeyListener(new FindListener());
        // add
        jPanelNorth.add(jButtonFind);
        jButtonAdd = new JButton(AppConstants.PARAM_ADD);
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddView();
            }
        });
        jPanelNorth.add(jButtonAdd);
        // delete
        jButtonDelete = new JButton(AppConstants.PARAM_DELETE);
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteView();
            }
        });
        jPanelNorth.add(jButtonDelete);
        // update
        jButtonUpdate = new JButton(AppConstants.PARAM_UPDATE);
        jButtonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateView();
            }
        });
        jPanelNorth.add(jButtonUpdate);
*/
       jButtonUpdate = new JButton("更新个人信息");
        jButtonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStudentView(username);
            }
        });
        jPanelNorth.add(jButtonUpdate);
        // center panel
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(1, 1));

        // init jTable
        String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).queryByName(username);
        myTableModel = new DefaultTableModel(result, column);
        jTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, cr);
        initJTable(jTable, result);

        jScrollPane = new JScrollPane(jTable);
        jPanelCenter.add(jScrollPane);

        // south panel
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 5));






        this.add(jPanelNorth, BorderLayout.NORTH);
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setBounds(400, 200, 750, 340);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void initJTable(JTable jTable, String[][] result) {
        ((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
        jTable.setRowHeight(20);
        TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(30);
        firsetColumn.setMaxWidth(30);
        firsetColumn.setMinWidth(30);
        TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(60);
        secondColumn.setMaxWidth(60);
        secondColumn.setMinWidth(60);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(90);
        thirdColumn.setMaxWidth(90);
        thirdColumn.setMinWidth(90);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(30);
        fourthColumn.setMaxWidth(30);
        fourthColumn.setMinWidth(30);
        TableColumn seventhColumn = jTable.getColumnModel().getColumn(6);
        seventhColumn.setPreferredWidth(30);
        seventhColumn.setMaxWidth(30);
        seventhColumn.setMinWidth(30);
        TableColumn ninthColumn = jTable.getColumnModel().getColumn(8);
        ninthColumn.setPreferredWidth(90);
        ninthColumn.setMaxWidth(90);
        ninthColumn.setMinWidth(90);
    }

    class FindListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                find();
            }
        }
    }

    private  void find() {
        currPageNum = 0;
        String param = condition.getText();
        if ("".equals(param) || param == null) {
            initJTable(MainView.jTable, null);
            currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
            return;
        }
        String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).queryByName(param);
        condition.setText("");
        initJTable(MainView.jTable, result);
        currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
    }

}

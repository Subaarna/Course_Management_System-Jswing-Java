package coursemanagement;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;



public class Dashboard {
	String role;
	DatabaseManageR db;
	JFrame DashboardFrame;
	private JTable table;
	private JTable teachers_table;
	private JTable course_table;
	private JTable module_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| UnsupportedLookAndFeelException e) {
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard(DatabaseManageR.getInstance(), "Teacher");
					window.DashboardFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Dashboard(DatabaseManageR db, String role) {
		this.role= role;
		this.db= db;
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println(role);

		DashboardFrame = new JFrame();
		DashboardFrame.setBackground(new Color(0, 255, 128));
		DashboardFrame.getContentPane().setForeground(new Color(255, 255, 255));
		DashboardFrame.setBounds(100, 100, 861, 476);
		DashboardFrame.setResizable(false);
		DashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DashboardFrame.getContentPane().setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBounds(130, 54, 525, 374);
		DashboardFrame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel dashboard = new JPanel();
		dashboard.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.add(dashboard, "name_14416669296300");
		dashboard.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Dashboard");
		lblNewLabel_1.setForeground(new Color(106,54,133));
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
		lblNewLabel_1.setBounds(0, 0, 118, 37);
		dashboard.add(lblNewLabel_1);

		JPanel Courses = new JPanel();
		layeredPane.add(Courses, "name_14426226764900");
		Courses.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Courses");
		lblNewLabel_4.setForeground(new Color(45,78,112));
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
		lblNewLabel_4.setBounds(0, 0, 85, 37);
		Courses.add(lblNewLabel_4);
		Statement stmt1;
		try {
			stmt1= db.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("SELECT course_id, course_name FROM courses");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");

			model.addColumn("course_name");
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString("course_id"),rs.getString("course_name")});
			}
			JScrollPane course_scroll = new JScrollPane();
			course_scroll.setBounds(0, 49, 521, 83);
			Courses.add(course_scroll);

			course_table = new JTable(model);
			course_table.setEnabled(false);
			course_table.getTableHeader().setReorderingAllowed(false);
			course_scroll.setViewportView(course_table);
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		Statement stmt2;
		try {
			stmt2 = db.getConnection().createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT module_id, module_name, module_type, course_id FROM modules");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");
			model.addColumn("module_name");
			model.addColumn("module_type");
			model.addColumn("course_id");


			while (rs.next()) {

				model.addRow(new Object[]{rs.getString("module_id"),rs.getString("module_name"),rs.getString("module_type"), rs.getString("course_id")});
			}
			JScrollPane module_scroll = new JScrollPane();

			module_scroll.setBounds(0, 161, 521, 203);
			Courses.add(module_scroll);

			module_table = new JTable(model);
			module_table.setColumnSelectionAllowed(false);
			module_table.setEnabled(false);
			module_table.getTableHeader().setReorderingAllowed(false);
			module_scroll.setViewportView(module_table);

			JButton edit_course = new JButton("Edit");
			edit_course.setForeground(Color.WHITE);
			edit_course.setBackground(new Color(74, 25, 106));
			edit_course.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit_course.setBackground(new Color(159, 129, 182));
					edit_course.setForeground(Color.white);
					new EditCourses();
				}
			});
			edit_course.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					edit_course.setForeground(new Color(255, 255, 255));
					edit_course.setBackground(new Color(74,25,106));
				}
			});
			edit_course.setBounds(185, 22, 90, 28);
			Courses.add(edit_course);
			if(role.equals("Student")||role.equals("Teacher")){

				edit_course.setVisible(false);
			}else {
				edit_course.setVisible(true);
			}

			JLabel lblNewLabel_4_1 = new JLabel("Modules");
			lblNewLabel_4_1.setForeground(new Color(45, 78, 112));
			lblNewLabel_4_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
			lblNewLabel_4_1.setBounds(0, 128, 103, 37);
			Courses.add(lblNewLabel_4_1);

			JButton getresult_btn = new JButton("Get Result");
			getresult_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new GetResult(db);
				}
			});
			if(role.equals("Student")) {
				getresult_btn.setVisible(true);
			}else {
				getresult_btn.setVisible(false);
			}
			getresult_btn.setForeground(new Color(255, 255, 255));
			getresult_btn.setBackground(new Color(74, 25, 106));
			getresult_btn.setBounds(185, 0, 90, 28);
			Courses.add(getresult_btn);
		}catch(SQLException e1) {
			e1.printStackTrace();
		}


		JPanel students = new JPanel();
		layeredPane.add(students, "name_14429139742400");
		students.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Students");
		lblNewLabel_6.setForeground(new Color(68, 60, 108));
		lblNewLabel_6.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
		lblNewLabel_6.setBounds(0, 6, 96, 27);
		students.add(lblNewLabel_6);
		Statement stmt;
		try {

			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT student_id, fullName, email, phoneNo, Course FROM student_table");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");
			model.addColumn("Name");
			model.addColumn("Email");
			model.addColumn("PhoneNo");
			model.addColumn("Course");


			while (rs.next()) {
				model.addRow(new Object[]{rs.getString("student_id"),rs.getString("fullName"), rs.getString("email"),
						rs.getString("phoneNo"), rs.getString("Course")});
			}

			JScrollPane student_scroll = new JScrollPane();
			student_scroll.setEnabled(false);
			student_scroll.setBounds(0, 29, 525, 346);
			students.add(student_scroll);


			table = new JTable(model);
			table.setColumnSelectionAllowed(false);
			student_scroll.setViewportView(table);
			table.setEnabled(false);

			JButton generateRslt = new JButton("Generate result");
			generateRslt.setForeground(new Color(255, 255, 255));
			generateRslt.setBackground(new Color(74, 25, 106));
			generateRslt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					generateRslt.setBackground(new Color(159, 129, 182));
					generateRslt.setForeground(Color.white);
					new GenerateResult();
				}
			});
			generateRslt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					generateRslt.setBackground(new Color(74,25,106));
					generateRslt.setForeground(Color.white);
				}
			});
			generateRslt.setBounds(168, 0, 120, 33);
			students.add(generateRslt);
			if(role.equals("Teacher")) {
				generateRslt.setVisible(true);
			}else {
				generateRslt.setVisible(false);
			}
			table.getTableHeader().setReorderingAllowed(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JPanel teachers = new JPanel();
		layeredPane.add(teachers, "name_176072916198400");
		teachers.setLayout(null);

		JLabel teacherss = new JLabel("Teachers");
		teacherss.setForeground(new Color(68, 60, 108));
		teacherss.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
		teacherss.setBounds(6, 6, 96, 27);
		teachers.add(teacherss);
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT teacher_id, fullName,email, phoneNo FROM teacher_table");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");
			model.addColumn("Name");
			model.addColumn("Email");
			model.addColumn("PhoneNo");
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString("teacher_id"),rs.getString("fullName"),rs.getString("email"),rs.getString("phoneNo")});
			}
			JScrollPane teachers_scroll = new JScrollPane();
			teachers_scroll.setBounds(0, 45, 521, 325);
			teachers.add(teachers_scroll);

			teachers_table = new JTable(model);
			teachers_table.setColumnSelectionAllowed(false);
			teachers_table.setEnabled(false);
			table.getTableHeader().setReorderingAllowed(false);
			teachers_scroll.setViewportView(teachers_table);

			JButton edit_teacher = new JButton("Edit");
			edit_teacher.setForeground(new Color(255, 255, 255));
			edit_teacher.setBackground(new Color(74,25,106));
			edit_teacher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit_teacher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					edit_teacher.setBackground(new Color(159, 129, 182));
					new EditTeachers();
					edit_teacher.setForeground(Color.white);
				}
			});
			edit_teacher.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					edit_teacher.setForeground(new Color(255, 255, 255));
					edit_teacher.setBackground(new Color(74,25,106));
				}
			});
			edit_teacher.setBounds(204, 8, 90, 28);
			teachers.add(edit_teacher);
			if(role.equals("Student")||role.equals("Teacher")){
				edit_teacher.setVisible(false);
			}else {
				edit_teacher.setVisible(true);
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		JPanel menu = new JPanel();
		menu.setBackground(new Color(22, 19, 65));
		menu.setBounds(0, 0, 129, 434);
		DashboardFrame.getContentPane().add(menu);
		menu.setLayout(null);

		JButton dashboard_btn = new JButton("Dashboard");
		dashboard_btn.setHorizontalAlignment(SwingConstants.LEFT);
		dashboard_btn.setIcon(new ImageIcon(Dashboard.class.getResource("/image/dashboard-solid-24.png")));

		dashboard_btn.setForeground(new Color(255, 255, 255));
		dashboard_btn.setBackground(new Color(74,25,106));
		dashboard_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboard_btn.setBackground(new Color(159, 129, 182));
				dashboard_btn.setForeground(Color.white);
				layeredPane.removeAll();
				layeredPane.add(dashboard);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		dashboard_btn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				dashboard_btn.setForeground(new Color(255, 255, 255));
				dashboard_btn.setBackground(new Color(74,25,106));
			}
		});
		dashboard_btn.setBounds(6, 61, 119, 23);
		menu.add(dashboard_btn);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/image/dash.gif")));
		lblNewLabel.setBounds(0, 0, 102, 87);
		menu.add(lblNewLabel);

		JButton Courses_btn = new JButton("Courses");
		Courses_btn.setIcon(new ImageIcon(Dashboard.class.getResource("/image/book-regular-24.png")));

		Courses_btn.setForeground(new Color(255, 255, 255));
		Courses_btn.setBackground(new Color(74,25,106));
		Courses_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Courses_btn.setBackground(new Color(159, 129, 182));
				Courses_btn.setForeground(Color.white);
				layeredPane.removeAll();
				layeredPane.add(Courses);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		Courses_btn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Courses_btn.setForeground(new Color(255, 255, 255));
				Courses_btn.setBackground(new Color(74,25,106));
			}
		});
		Courses_btn.setBounds(8, 95, 109, 23);
		menu.add(Courses_btn);

		JButton student_btn = new JButton("Students");
		student_btn.setIcon(new ImageIcon(Dashboard.class.getResource("/image/graduation-solid-24.png")));

		student_btn.setBackground(new Color(74,25,106));
		student_btn.setForeground(Color.white);
		student_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student_btn.setBackground(new Color(159, 129, 182));
				layeredPane.removeAll();
				layeredPane.add(students);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		student_btn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				student_btn.setBackground(new Color(74,25,106));
				student_btn.setForeground(Color.white);
			}
		});
		student_btn.setBounds(8, 131, 109, 23);
		menu.add(student_btn);

		JButton logout_btn = new JButton("Logout");
		logout_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logout_btn.setBackground(new Color(159, 129, 182));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				logout_btn.setBackground(new Color(74,25,106));
			}
		});
		logout_btn.setBackground(new Color(74,25,106));
		logout_btn.setForeground(Color.white);
		logout_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(logout_btn, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					DashboardFrame.setVisible(false);
					new Login(db);
					logout_btn.setBackground(new Color(159, 129, 182));
					DashboardFrame.dispose();
				}
				else if(result == JOptionPane.NO_OPTION){
				}
			}
		});
		logout_btn.setIcon(new ImageIcon(Dashboard.class.getResource("/image/log-out-circle-regular-24.png")));
		logout_btn.setBounds(10, 363, 109, 23);
		menu.add(logout_btn);

		JButton Teachers_btn = new JButton("Teachers");
		Teachers_btn.setIcon(new ImageIcon(Dashboard.class.getResource("/image/chalkboard-solid-24.png")));
		Teachers_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(teachers);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		Teachers_btn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Teachers_btn.setBackground(new Color(74,25,106));
				Teachers_btn.setForeground(Color.white);
			}
		});
		Teachers_btn.setForeground(Color.WHITE);
		Teachers_btn.setBackground(new Color(74, 25, 106));
		Teachers_btn.setBounds(8, 166, 109, 23);
		menu.add(Teachers_btn);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Dashboard.class.getResource("/image/dsboard.gif")));
		lblNewLabel_2.setBounds(117, 11, 140, 31);
		DashboardFrame.getContentPane().add(lblNewLabel_2);



		JLabel focus = new JLabel("New label");
		focus.setBounds(631, 0, 6, 1);
		DashboardFrame.getContentPane().add(focus);

		JLabel lblNewLabel_8 = new JLabel("Connect with us:");
		lblNewLabel_8.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(656, 269, 93, 15);
		DashboardFrame.getContentPane().add(lblNewLabel_8);

		JLabel facebook = new JLabel("New label");
		facebook.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-facebook-48.png")));
		facebook.setBounds(656, 283, 48, 42);
		DashboardFrame.getContentPane().add(facebook);
		facebook.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String url = "https://www.facebook.com/heraldcollegektm";
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				facebook.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-facebook-48 dark.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				facebook.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-facebook-48.png")));
			}
		});

		JLabel instagram = new JLabel("New label");
		instagram.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-instagram-48.png")));
		instagram.setBounds(684, 284, 40, 40);
		DashboardFrame.getContentPane().add(instagram);

		JLabel lblNewLabel_5 = new JLabel("Calendar");
		lblNewLabel_5.setBounds(657, 40, 128, 31);
		DashboardFrame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(90, 80, 128));
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 15));


		instagram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.instagram.com/heraldcollegektm/"));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				instagram.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-instagram-48 dark.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				instagram.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-instagram-48.png")));
			}
		});

		JPanel calendar = new JPanel();
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		calendar.setBackground(new Color(22, 19, 65));
		calendar.setForeground(Color.WHITE);
		calendar.setBounds(657, 65, 191, 203);
		DashboardFrame.getContentPane().add(calendar);
		calendar.setLayout(new GridLayout(6, 7));

		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setIcon(new ImageIcon(Dashboard.class.getResource("/image/giphy.gif")));
		lblNewLabel_11.setBounds(666, 50, 182, 320);
		DashboardFrame.getContentPane().add(lblNewLabel_11);

		String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for (String day : days) {
			JLabel label = new JLabel(day);
			label.setPreferredSize(new Dimension(40, 40));
			label.setForeground(new Color(214,177,237));
			label.setFont(new Font("SansSerif", Font.BOLD, 11));
			calendar.add(label);
		}

		Calendar currentCalendar = Calendar.getInstance();
		@SuppressWarnings("unused")
		int currentMonth = currentCalendar.get(Calendar.MONTH);
		int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

		for (int i = 1; i <= 31; i++) {
			JLabel label = new JLabel(String.valueOf(i));
			label.setPreferredSize(new Dimension(40, 40));
			label.setForeground(new Color(74,25,106));
			label.setFont(new Font("SansSerif", Font.BOLD, 11));
			if (i == currentDay) {
				label.setEnabled(false);
				label.setForeground(Color.RED);
			}
			calendar.add(label);


		}
	}
}


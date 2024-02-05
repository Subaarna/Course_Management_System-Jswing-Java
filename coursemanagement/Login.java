package coursemanagement;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Toolkit;

public class Login {
	DatabaseManageR db;
	private JFrame frame;
	private JPanel login_student;
	private JPanel home;
	private JTextField txtEmail;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JPanel Signup;
	private JTextField txtName;
	private JPasswordField signup_password;
	private JButton teacher_btn;
	private JPanel login_teacher;
	private JTextField teacher_email;
	private JPasswordField teacher_pass;
	private JPasswordField teacher_code;
	private JTextField adminEmail;
	private JPasswordField adminPassword;
	private JPasswordField adminCode;
	private JButton adminbtn;
	private JPanel login_Admin;
	private JTextField signup_mail;
	Login layeredPane;
	private JTextField levelField;
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
					Login window = new Login(DatabaseManageR.getInstance());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param databaseManageR 
	 */
	public Login(DatabaseManageR db) {
		this.db=db;
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			DatabaseManageR.getInstance() ;
			new Authenticate(DatabaseManageR.getInstance());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,
					"Can not connect to database!\nPlease make sure mySQL is correctly setup and running!",
					"Error 500: Server Communication Failed", JOptionPane.ERROR_MESSAGE);
			System.exit(500);
		}
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/graduation-solid-72.png")));

		frame.setAutoRequestFocus(false);
		frame.setBackground(new Color(0, 255, 128));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 577, 621);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, "name_196096606558900");
		layeredPane.setLayout(new CardLayout(0, 0));

		home =new JPanel();
		layeredPane.add(home, "name_196145899221600");
		home.setLayout(null);

		JButton student_btn = new JButton("Student");
		student_btn.setForeground(new Color(255, 255, 255));
		student_btn.setBackground(new Color(106, 0, 213));
		student_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				student_btn.setIcon(new ImageIcon(Login.class.getResource("/image/stuudent.gif")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				student_btn.setIcon(null);
			}
		});
		student_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(login_student);
				student_btn.setIcon(null);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		student_btn.setBounds(41, 373, 89, 40);
		home.add(student_btn);

		adminbtn = new JButton("Admin");
		adminbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(login_Admin);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		adminbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				adminbtn.setIcon(new ImageIcon(Login.class.getResource("/image/admin.gif")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				adminbtn.setIcon(null);
			}
		});
		adminbtn.setForeground(new Color(255, 255, 255));
		adminbtn.setBackground(new Color(106, 0, 213));
		adminbtn.setBounds(219, 405, 89, 40);
		home.add(adminbtn);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(Login.class.getResource("/image/Vanilla-1s-274px (1).gif")));
		lblNewLabel_8.setBounds(-18, 11, 260, 66);
		home.add(lblNewLabel_8);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/image/hi.gif")));
		lblNewLabel_1.setBounds(219, 0, 158, 89);
		home.add(lblNewLabel_1);

		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setIcon(new ImageIcon(Login.class.getResource("/image/confused-unscreen.gif")));
		lblNewLabel_9.setBounds(41, 100, 456, 247);
		home.add(lblNewLabel_9);

		JLabel lblNewLabel_7 = new JLabel("Login as:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(240, 358, 68, 22);
		home.add(lblNewLabel_7);

		JLabel lblNewLabel_2 = new JLabel("Don't have account? ");
		lblNewLabel_2.setBounds(206, 495, 124, 14);
		home.add(lblNewLabel_2);

		JLabel signup_btn = new JLabel("Signup");
		signup_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Signup);
				signup_btn.setForeground(Color.black);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				signup_btn.setForeground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signup_btn.setForeground(Color.black);
			}
		});
		signup_btn.setFont(new Font("Tahoma", Font.BOLD, 11));
		signup_btn.setBounds(316, 496, 50, 14);
		home.add(signup_btn);

		teacher_btn = new JButton("Teacher");
		teacher_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				teacher_btn.setIcon(new ImageIcon(Login.class.getResource("/image/teacher.gif")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				teacher_btn.setIcon(null);
			}
		});
		teacher_btn.setForeground(Color.WHITE);
		teacher_btn.setBackground(new Color(106, 0, 213));
		teacher_btn.setBounds(434, 373, 89, 40);
		home.add(teacher_btn);
		teacher_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(login_teacher);
				layeredPane.repaint();
				layeredPane.revalidate();
				teacher_btn.setIcon(null);
			}
		});


		login_student = new JPanel();
		login_student.setBackground(new Color(255, 255, 255));
		login_student.setForeground(new Color(128, 255, 128));
		layeredPane.add(login_student, "name_196296444202500");
		login_student.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel(new ImageIcon(Login.class.getResource("/image/envelope-regular-24.png")));
		lblNewLabel_1_1.setBounds(45, 193, 31, 29);
		login_student.add(lblNewLabel_1_1);

		txtEmail = new JTextField();
		txtEmail.setBorder(new LineBorder(new Color(98, 1, 1), 1));
		txtEmail.setText("Email");
		txtEmail.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(txtEmail.getText().equals("Email")){
					txtEmail.setForeground(Color.gray);
					txtEmail.setText("");
					txtEmail.setForeground(Color.BLACK);
				}
			}
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().isEmpty() && !txtEmail.getText().equals("Email")) {
					txtEmail.setForeground(new Color(192, 192, 192));
					txtEmail.setText("Email");
				}
			}
		});
		txtEmail.setForeground(Color.LIGHT_GRAY);
		txtEmail.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(76, 185, 136, 37);
		login_student.add(txtEmail);

		lblNewLabel = new JLabel(new ImageIcon(Login.class.getResource("/image/lock-solid-24.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(45, 235, 29, 27);
		login_student.add(lblNewLabel);
		JButton Submit = new JButton("Login");
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Authenticate.login(txtEmail.getText(),new String(passwordField.getPassword()),  null,"Student") ;
					Dashboard Dsb = new Dashboard(DatabaseManageR.getInstance(), "Student");
					Dsb.DashboardFrame.setVisible(true);
					frame.dispose();	
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}


			}
		});

		Submit.setForeground(new Color(255, 255, 255));
		Submit.setBackground(new Color(3, 160, 224));
		Submit.setBounds(76, 277, 89, 23);
		login_student.add(Submit);

		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(177, 0, 0), 1));
		passwordField.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setEchoChar((char)0);
		passwordField.setText("Password");
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(new String(passwordField.getPassword()).equals("Password")){
					passwordField.setEchoChar('*');
					passwordField.setText("");
					passwordField.setForeground(Color.black);
				}
			}
			public void focusLost(FocusEvent e) {
				if(new String(passwordField.getPassword()).isEmpty() && !new String(passwordField.getPassword()).equals("Password")) {
					passwordField.setEchoChar((char)0);
					passwordField.setForeground(new Color(192, 192, 192));
					passwordField.setText("Password");
				}
			}
		});

		passwordField.setBounds(76, 228, 136, 37);
		login_student.add(passwordField);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/image/logintxt.gif")));
		lblNewLabel_4.setBounds(-15, 28, 243, 82);
		login_student.add(lblNewLabel_4);

		JLabel back_arr3_std = new JLabel("\r\n");
		back_arr3_std.setBounds(0, 3, 46, 14);
		login_student.add(back_arr3_std);
		back_arr3_std.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Login.class.getResource("/image/01-Online+Education.gif")));
		lblNewLabel_5.setBounds(0, 0, 561, 528);
		login_student.add(lblNewLabel_5);
		back_arr3_std.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(home);
				back_arr3_std.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				back_arr3_std.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-dark.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				back_arr3_std.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
			}
		});

		Signup = new JPanel();
		layeredPane.add(Signup, "name_88102189648100");
		Signup.setLayout(null);

		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setText("Name");
		txtName.setForeground(Color.LIGHT_GRAY);
		txtName.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		txtName.setColumns(10);
		txtName.setBounds(193, 140, 136, 37);

		txtName.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtName.getText().equals("Name")) {
					txtName.setForeground(Color.gray);
					txtName.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(txtName.getText().isEmpty() && !txtName.getText().equals("Name")) {
					txtName.setForeground(new Color(192, 192, 192));
					txtName.setText("Name");
				}
			}
		});
		Signup.add(txtName);


		JTextField Ph_num = new JTextField();
		Ph_num.setText("Phone Number");
		Ph_num.setForeground(Color.LIGHT_GRAY);
		Ph_num.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		Ph_num.setColumns(10);
		Ph_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(Ph_num.getText().equals("Phone Number")){
					Ph_num.setForeground(Color.gray);
					Ph_num.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(Ph_num.getText().isEmpty()) {
					Ph_num.setForeground(new Color(192, 192, 192));
					Ph_num.setText("Phone Number");
				}
			}
		});


		Ph_num.setBounds(193, 238, 136, 37);
		Signup.add(Ph_num);

		signup_password = new JPasswordField();
		signup_password.setForeground(Color.LIGHT_GRAY);
		signup_password.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		signup_password.setEchoChar(' ');

		signup_password.setText("Password");
		JLabel passerror = new JLabel("Password must be at least 8 character and contain Upper and lower case with number.");
		passerror.setForeground(new Color(128, 0, 0));
		passerror.setFont(new Font("SansSerif", Font.PLAIN, 11));
		passerror.setVisible(false);

		JLabel passerror1 = new JLabel("Password is required.");
		passerror1.setForeground(new Color(128, 0, 0));
		passerror1.setFont(new Font("SansSerif", Font.PLAIN, 11));
		passerror1.setBounds(193, 317, 110, 16);
		passerror1.setVisible(false);
		Signup.add(passerror1);
		passerror.setBounds(74, 317, 424, 16);
		Signup.add(passerror);
		signup_password.setBounds(193, 283, 136, 37);
		Signup.add(signup_password);
		signup_password.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(new String(signup_password.getPassword()).equals("Password")){
					signup_password.setEchoChar('*');
					signup_password.setText("");
					signup_password.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(new String(signup_password.getPassword()).isEmpty() && !new String(signup_password.getPassword()).equals("Password")) {
					signup_password.setEchoChar((char)0);
					signup_password.setForeground(new Color(192, 192, 192));
					signup_password.setText("Password");

				}
				String password = new String(signup_password.getPassword());
				if(Regex.validatePassword(password)) {
					passerror.setVisible(false);
					passerror1.setVisible(false);
				}else {
					passerror.setVisible(true);
					passerror1.setVisible(true);
					new Timer(1000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							passerror.setVisible(false);
						}
					}).start();
				}
			}

		});



		Statement stmt;
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select course_name FROM courses");
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
			while(rs.next()) {
				model.addElement(rs.getString("course_name"));
			}

			JComboBox<String> Courses = new JComboBox<>(model);
			Courses.setForeground(Color.WHITE);
			Courses.setBackground(new Color(64, 0, 128));
			Courses.setBounds(190, 407, 139, 22);
			Signup.add(Courses);


			JPasswordField signup_code = new JPasswordField();
			signup_code.setForeground(Color.LIGHT_GRAY);
			signup_code.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			signup_code.setEchoChar((char)0);
			signup_code.setText("Code");
			signup_code.setBounds(193, 332, 136, 37);
			signup_code.setVisible(false);

			signup_code.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(new String(signup_code.getPassword()).equals("Code")){
						signup_code.setEchoChar('*');
						signup_code.setText(null);
						signup_code.setForeground(Color.BLACK);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(new String(signup_code.getPassword()).isEmpty()){
						signup_code.setEchoChar((char)0);
						signup_code.setForeground(Color.LIGHT_GRAY);
						signup_code.setText("Code");
					}
				}
			});
			JLabel levelerror = new JLabel("Level can't be empty");
			levelerror.setForeground(new Color(128, 0, 0));
			levelerror.setVisible(false);
			levelerror.setBounds(178, 366, 258, 16);
			Signup.add(levelerror);
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setForeground(new Color(255, 255, 255));
			comboBox.setBackground(new Color(64, 0, 128));
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Student", "Teacher", "Admin"}));
			comboBox.setBounds(180, 441, 155, 22);
			comboBox.setToolTipText("Please select your role");
			Signup.add(comboBox);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selected = (String) comboBox.getSelectedItem();
					if (selected.equals("Teacher") || selected.equals("Admin")) {

						signup_code.setEnabled(true);
						signup_code.setVisible(true);
					} else {
						signup_code.setEnabled(false);
						signup_code.setVisible(false);
					}
					if(selected.equals("Student")){

						Courses.setVisible(true);
						levelField.setVisible(true);
					}else {
						levelerror.setVisible(false);
						levelField.setVisible(false);
						Courses.setVisible(false);
					}
				}
			});


			Signup.add(signup_code);

			JLabel lblNewLabel_6 = new JLabel("");
			lblNewLabel_6.setIcon(new ImageIcon(Login.class.getResource("/image/signup.gif")));
			lblNewLabel_6.setBounds(-25, 30, 243, 70);
			Signup.add(lblNewLabel_6);

			JLabel signup_back = new JLabel("");
			signup_back.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
			signup_back.setBounds(0, 0, 58, 19);
			signup_back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					layeredPane.removeAll();
					layeredPane.add(home);
					signup_back.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
					layeredPane.repaint();
					layeredPane.revalidate();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					signup_back.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-dark.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					signup_back.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
				}

			});
			Signup.add(signup_back);
			JLabel usererror = new JLabel("Username can't be empty.");
			usererror.setForeground(new Color(128, 0, 0));
			usererror.setFont(new Font("SansSerif", Font.PLAIN, 11));
			usererror.setBounds(193, 176, 171, 16);
			usererror.setVisible(false);
			Signup.add(usererror);

			levelField = new JTextField();
			levelField.setForeground(Color.LIGHT_GRAY);
			levelField.setText("Level");
			levelField.setColumns(10);
			levelField.addFocusListener(new FocusAdapter() {

				@Override
				public void focusGained(FocusEvent e) {
					if (levelField.getText().equals("Level")) {
						levelField.setForeground(Color.GRAY);
						levelField.setText(null);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (levelField.getText().isEmpty() && !levelField.getText().equals("Level")) {
						levelField.setForeground(new Color(192, 192, 192));
						levelField.setText("Level");
					}
				}
			});
			levelField.setBounds(193, 335, 136, 34);
			Signup.add(levelField);

			signup_mail = new JTextField();
			signup_mail.setText("Email");
			signup_mail.setHorizontalAlignment(SwingConstants.LEFT);
			signup_mail.setForeground(Color.LIGHT_GRAY);
			signup_mail.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			signup_mail.setColumns(10);
			JLabel emailerror = new JLabel("Enter a valid email address.");
			emailerror.setForeground(new Color(128, 0, 0));
			emailerror.setFont(new Font("SansSerif", Font.PLAIN, 11));
			emailerror.setBounds(193, 224, 136, 16);
			emailerror.setVisible(false);
			Signup.add(emailerror);
			signup_mail.addFocusListener(new FocusAdapter() {

				@Override
				public void focusGained(FocusEvent e) {
					if (signup_mail.getText().equals("Email")) {
						signup_mail.setForeground(Color.GRAY);
						signup_mail.setText(null);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (signup_mail.getText().isEmpty() && !signup_mail.getText().equals("Email")) {
						signup_mail.setForeground(new Color(192, 192, 192));
						signup_mail.setText("Email");
					}
				}
			});

			signup_mail.setBounds(193, 189, 136, 37);
			JLabel pherror = new JLabel("Phone number can't be empty");
			pherror.setForeground(new Color(128, 0, 0));
			pherror.setFont(new Font("SansSerif", Font.PLAIN, 11));
			pherror.setBounds(193, 272, 144, 16);
			pherror.setVisible(false);
			Signup.add(pherror);
			Signup.add(signup_mail);

			JButton Ssubmit_btn = new JButton("Submit");
			Ssubmit_btn.setEnabled(false);

			signup_password.getDocument().addDocumentListener(new DocumentListener() {
				public void insertUpdate(DocumentEvent e) {
					validatePassword();
				}

				public void removeUpdate(DocumentEvent e) {
					validatePassword();
				}

				public void changedUpdate(DocumentEvent e) {
					validatePassword();
				}

				private void validatePassword() {
					String password = new String(signup_password.getPassword());
					if (password.isEmpty()) {
						passerror1.setVisible(true);
						passerror.setVisible(false);
						Ssubmit_btn.setEnabled(false);
					} else if (!Regex.validatePassword(password)) {
						passerror.setText("Password must be at least 8 character and contain Upper and lower case with number.");
						passerror.setVisible(true);
						passerror1.setVisible(false);
						Ssubmit_btn.setEnabled(false);
					} else {
						passerror.setVisible(false);
						if (Regex.validatePassword(new String(signup_password.getPassword()))) {
							passerror1.setVisible(false);
							Ssubmit_btn.setEnabled(true);
						}
					}
				}
			});



			signup_mail.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					validateEmail();
				}

				public void removeUpdate(DocumentEvent e) {
					validateEmail();
				}

				public void insertUpdate(DocumentEvent e) {
					validateEmail();
				}

				private void validateEmail() {
					String email = signup_mail.getText().trim();
					if (email.isEmpty()) {
						emailerror.setText("Email is required");
						emailerror.setVisible(true);
						Ssubmit_btn.setEnabled(false);
					} else if (!Regex.validateEmail(email)) {
						emailerror.setText("Email is not valid");
						emailerror.setVisible(true);
						Ssubmit_btn.setEnabled(false);
					} else {
						emailerror.setVisible(false);
						if (Regex.validatePassword(signup_mail.getText())) {
							Ssubmit_btn.setEnabled(true);
						}
					}
				}
			});
			levelField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					validateLevel();
				}

				public void removeUpdate(DocumentEvent e) {
					validateLevel();
				}

				public void insertUpdate(DocumentEvent e) {
					validateLevel();
				}

				private void validateLevel() {
					String level = levelField.getText().trim();
					if (level.isEmpty()) {
						levelerror.setText("Level is required");
						levelerror.setVisible(true);
						Ssubmit_btn.setEnabled(false);
					} else if (!Regex.validateLevel(level)) {
						levelerror.setText("Level must be digit between 4 to 6");
						levelerror.setVisible(true);
						Ssubmit_btn.setEnabled(false);
					} else {
						levelerror.setVisible(false);
						if (Regex.validateLevel(levelField.getText()) && Regex.validateLevel(levelField.getText())) {
							levelerror.setVisible(false);
							Ssubmit_btn.setEnabled(true);
						}
					}
				}
			});
			txtName.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					validateUsername();
				}

				public void removeUpdate(DocumentEvent e) {
					validateUsername();
				}

				public void insertUpdate(DocumentEvent e) {
					validateUsername();
				}

				private void validateUsername() {
					String username = txtName.getText().trim();
					if (username.isEmpty()) {
						usererror.setText("Username cannot be empty");
						usererror.setVisible(true);
					} else if (!Regex.validateUsername(username)) {
						usererror.setText("Username must be greater than 4 letters");
						usererror.setVisible(true);
					} else {
						usererror.setVisible(false);
						if (Regex.validatePassword(txtName.getText())) {
							Ssubmit_btn.setEnabled(true);
						}
					}
				}
			});

			Ph_num.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					validatePhoneNum();
				}

				public void removeUpdate(DocumentEvent e) {
					validatePhoneNum();
				}

				public void insertUpdate(DocumentEvent e) {
					validatePhoneNum();
				}

				private void validatePhoneNum() {
					String phonenum = Ph_num.getText().trim();
					if (phonenum.isEmpty()) {
						pherror.setText("Phone number cannot be empty");

						pherror.setVisible(true);
					} else if (!Regex.validatePhoneNum(phonenum)) {
						pherror.setText("Provide proper Phone number");
						pherror.setVisible(true);
					} else {
						pherror.setVisible(false);
						if (Regex.validatePhoneNum(Ph_num.getText())) {
						}
					}
				}
			});





			Ssubmit_btn.setBounds(193, 475, 90, 28);
			Signup.add(Ssubmit_btn);
			Ssubmit_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					try {
						if (txtName.getText().isEmpty()|| txtName.getText().equals("Name") || signup_mail.getText().isEmpty()|| signup_mail.getText().equals("Email") || Ph_num.getText().isEmpty()||Ph_num.getText().equals("Phone Number") || new String(signup_password.getPassword()).isEmpty()||new String(signup_password.getPassword()).equals("Password")) {
							JOptionPane.showMessageDialog(null, "All fields are required.", null, JOptionPane.ERROR_MESSAGE);
						} else {

							Authenticate.addCredential(txtName.getText(), signup_mail.getText(), Ph_num.getText(),new String(signup_password.getPassword()),new String(signup_code.getPassword()),(String) Courses.getSelectedItem(), (String) comboBox.getSelectedItem(), levelField.getText());
							JOptionPane.showMessageDialog(null, "account successfully created");
							layeredPane.removeAll();
							layeredPane.add(home);
							layeredPane.repaint();
							layeredPane.revalidate();
							signup_mail.setText(null);
							signup_password.setText(null);
							Ph_num.setText(null);
							txtName.setText(null);
							signup_code.setText(null);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
				}
			});







			JLabel maingif = new JLabel("");
			maingif.setIcon(new ImageIcon(Login.class.getResource("/image/1646021-unscreen.gif")));
			maingif.setBounds(46, 253, 515, 395);
			Signup.add(maingif);



			login_teacher = new JPanel();
			login_teacher.setBackground(new Color(255, 255, 255));
			frame.getContentPane().add(login_teacher, "name_21775559080600");
			login_teacher.setLayout(null);
			frame.setAutoRequestFocus(false);


			teacher_email = new JTextField();
			teacher_email.setBorder(new LineBorder(new Color(98, 1, 1), 1));
			teacher_email.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					if(teacher_email.getText().equals("Email")){
						teacher_email.setForeground(Color.gray);
						teacher_email.setText("");
						teacher_email.setForeground(Color.BLACK);
					}
				}
				public void focusLost(FocusEvent e) {
					if(teacher_email.getText().isEmpty() && !teacher_email.getText().equals("Email")) {
						teacher_email.setForeground(new Color(192, 192, 192));
						teacher_email.setText("Email");
					}
				}
			});

			teacher_email.setText("Email");
			teacher_email.setForeground(Color.LIGHT_GRAY);
			teacher_email.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			teacher_email.setColumns(10);
			teacher_email.setBounds(76, 205, 136, 34);
			login_teacher.add(teacher_email);

			JButton Submit_teacher = new JButton("Login");

			Submit_teacher.setForeground(Color.WHITE);
			Submit_teacher.setBackground(new Color(3, 160, 224));
			Submit_teacher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Authenticate.login(teacher_email.getText(),new String(teacher_pass.getPassword()), new String(teacher_code.getPassword()), "Teacher") ;

						Dashboard Dsb = new Dashboard(DatabaseManageR.getInstance(), "Teacher");
						Dsb.DashboardFrame.setVisible(true);
						frame.dispose();	
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}


				}
			});
			Submit_teacher.setBounds(76, 338, 89, 23);
			login_teacher.add(Submit_teacher);

			teacher_pass = new JPasswordField();
			teacher_pass.setBorder(new LineBorder(new Color(177, 0, 0), 1));
			teacher_pass.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			teacher_pass.setForeground(Color.LIGHT_GRAY);
			teacher_pass.setEchoChar((char)0);
			teacher_pass.setText("Password");
			teacher_pass.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					if(new String(teacher_pass.getPassword()).equals("Password")){
						teacher_pass.setEchoChar('*');
						teacher_pass.setText("");
						teacher_pass.setForeground(Color.black);
					}
				}
				public void focusLost(FocusEvent e) {
					if(new String(teacher_pass.getPassword()).isEmpty() && !new String(teacher_pass.getPassword()).equals("Password")) {
						teacher_pass.setEchoChar((char)0);
						teacher_pass.setForeground(new Color(192, 192, 192));
						teacher_pass.setText("Password");
					}
				}
			});

			teacher_pass.setBounds(76, 241, 136, 37);
			login_teacher.add(teacher_pass);

			JLabel code_icon = new JLabel("");
			code_icon.setIcon(new ImageIcon(Login.class.getResource("/image/shield-alt-2-solid-24.png")));
			code_icon.setBounds(49, 289, 24, 23);
			login_teacher.add(code_icon);

			JLabel lblNewLabel_4_1 = new JLabel("New label");
			lblNewLabel_4_1.setIcon(new ImageIcon(Login.class.getResource("/image/logintxt.gif")));
			lblNewLabel_4_1.setBounds(0, 40, 241, 82);
			login_teacher.add(lblNewLabel_4_1);

			JLabel teacherMail_icon = new JLabel(new ImageIcon(Login.class.getResource("/image/envelope-regular-24.png")));
			teacherMail_icon.setBounds(49, 212, 24, 23);
			login_teacher.add(teacherMail_icon);

			JLabel pass = new JLabel(new ImageIcon(Login.class.getResource("/image/lock-solid-24.png")));
			pass.setForeground(Color.WHITE);
			pass.setBounds(49, 245, 24, 27);
			login_teacher.add(pass);

			JLabel back_arr3 = new JLabel("\r\n");
			back_arr3.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
			back_arr3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					layeredPane.removeAll();
					layeredPane.add(home);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					back_arr3.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-dark.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					back_arr3.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
				}
			});


			back_arr3.setBounds(0, 0, 46, 14);
			login_teacher.add(back_arr3);

			teacher_code = new JPasswordField();
			teacher_code.setBorder(new LineBorder(new Color(224, 122, 3),1));
			teacher_code.setText("Code");
			teacher_code.setForeground(Color.LIGHT_GRAY);
			teacher_code.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			teacher_code.setEchoChar(' ');
			teacher_code.setBounds(76, 281, 136, 37);
			teacher_code.setEchoChar((char)0);
			teacher_code.setText("Code");
			login_teacher.add(teacher_code);

			JLabel lblNewLabel_5_1 = new JLabel("");
			lblNewLabel_5_1.setIcon(new ImageIcon(Login.class.getResource("/image/01-Online+Education.gif")));
			lblNewLabel_5_1.setBounds(10, 11, 551, 510);
			login_teacher.add(lblNewLabel_5_1);

			login_Admin = new JPanel();
			login_Admin.setLayout(null);
			login_Admin.setBackground(Color.WHITE);
			frame.getContentPane().add(login_Admin, "name_18183242008600");

			adminEmail = new JTextField();
			adminEmail.setBorder(new LineBorder(new Color(98, 1, 1), 1));
			adminEmail.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(adminEmail.getText().equals("Email")) {
						adminEmail.setForeground(Color.gray);
						adminEmail.setText(null);
						adminEmail.setForeground(Color.black);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(adminEmail.getText().isEmpty()&& !adminEmail.getText().equals("Email")) {
						adminEmail.setForeground(Color.LIGHT_GRAY);
						adminEmail.setText("Email");
					}
				}
			});
			adminEmail.setText("Email");
			adminEmail.setForeground(Color.LIGHT_GRAY);
			adminEmail.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			adminEmail.setColumns(10);
			adminEmail.setBounds(76, 205, 136, 34);
			login_Admin.add(adminEmail);



			adminPassword = new JPasswordField();
			adminPassword.setBorder(new LineBorder(new Color(177, 0, 0), 1));
			adminPassword.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(new String(adminPassword.getPassword()).equals("Password")){
						adminPassword.setEchoChar('*');
						adminPassword.setText("");
						adminPassword.setForeground(Color.black);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(new String(adminPassword.getPassword()).isEmpty() && !new String(adminPassword.getPassword()).equals("Password")) {
						adminPassword.setEchoChar((char)0);
						adminPassword.setForeground(new Color(192, 192, 192));
						adminPassword.setText("Password");
					}
				}
			});
			adminPassword.setText("Password");
			adminPassword.setForeground(Color.LIGHT_GRAY);
			adminPassword.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			adminPassword.setEchoChar(' ');
			adminPassword.setBounds(76, 241, 136, 37);
			login_Admin.add(adminPassword);
			JButton admin_login_btn = new JButton("Login");
			admin_login_btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Authenticate.login(adminEmail.getText(),new String(adminPassword.getPassword()), new String(adminCode.getPassword()), "Admin") ;

						Dashboard Dsb = new Dashboard(DatabaseManageR.getInstance(), "Admin");
						Dsb.DashboardFrame.setVisible(true);
						frame.dispose();	
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}


				}
			});
			admin_login_btn.setForeground(Color.WHITE);
			admin_login_btn.setBackground(new Color(3, 160, 224));
			admin_login_btn.setBounds(76, 338, 89, 23);
			login_Admin.add(admin_login_btn);
			JLabel code_icon_1 = new JLabel("");
			code_icon_1.setIcon(new ImageIcon(Login.class.getResource("/image/shield-alt-2-solid-24.png")));
			code_icon_1.setBounds(49, 289, 24, 23);
			login_Admin.add(code_icon_1);

			JLabel lblNewLabel_4_1_1 = new JLabel("New label");
			lblNewLabel_4_1_1.setIcon(new ImageIcon(Login.class.getResource("/image/logintxt.gif")));
			lblNewLabel_4_1_1.setBounds(0, 40, 241, 82);
			login_Admin.add(lblNewLabel_4_1_1);

			JLabel teacherMail_icon_1 = new JLabel(new ImageIcon(Login.class.getResource("/image/envelope-regular-24.png")));
			teacherMail_icon_1.setBounds(49, 212, 24, 23);
			login_Admin.add(teacherMail_icon_1);

			JLabel pass_1 = new JLabel(new ImageIcon(Login.class.getResource("/image/lock-solid-24.png")));
			pass_1.setForeground(Color.WHITE);
			pass_1.setBounds(49, 249, 25, 29);
			login_Admin.add(pass_1);

			JLabel back_arr = new JLabel("\r\n");
			back_arr.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
			back_arr.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					layeredPane.removeAll();
					layeredPane.add(home);
					adminbtn.setIcon(null);
					back_arr.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
					layeredPane.repaint();
					layeredPane.revalidate();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					back_arr.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-dark.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					back_arr.setIcon(new ImageIcon(Login.class.getResource("/image/arrow-back-regular-24.png")));
				}
			});
			back_arr.setBounds(0, 0, 46, 14);
			login_Admin.add(back_arr);

			adminCode = new JPasswordField();
			adminCode.setBorder(new LineBorder(new Color(224, 122, 3), 1));
			adminCode.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(new String(adminCode.getPassword()).equals("Code")){
						adminCode.setEchoChar('*');
						adminCode.setText("");
						adminCode.setForeground(Color.black);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(new String(adminCode.getPassword()).isEmpty() && !new String(adminCode.getPassword()).equals("Code")) {
						adminCode.setEchoChar((char)0);
						adminCode.setForeground(new Color(192, 192, 192));
						adminCode.setText("Code");
					}
				}
			});
			adminCode.setText("Code");
			adminCode.setForeground(Color.LIGHT_GRAY);
			adminCode.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
			adminCode.setEchoChar(' ');
			adminCode.setBounds(76, 281, 136, 37);
			login_Admin.add(adminCode);

			JLabel main = new JLabel("");
			main.setIcon(new ImageIcon(Login.class.getResource("/image/01-Online+Education.gif")));
			main.setBounds(0, 0, 561, 530);
			login_Admin.add(main);
			teacher_code.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if(new String(teacher_code.getPassword()).equals("Code")){
						teacher_code.setEchoChar('*');
						teacher_code.setText("");
						teacher_code.setForeground(Color.black);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(new String(teacher_code.getPassword()).isEmpty() && !new String(teacher_code.getPassword()).equals("Code")) {
						teacher_code.setEchoChar((char)0);
						teacher_code.setForeground(new Color(192, 192, 192));
						teacher_code.setText("Code");
					}
				}
			});
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		frame.setVisible(true);

	}

	public void setVisible(boolean b) {

	}
}

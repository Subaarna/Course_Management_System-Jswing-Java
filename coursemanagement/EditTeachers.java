package coursemanagement;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class EditTeachers {

	private JFrame frame;
	JTextField teachername;
	private JTextField Teacher_email;
	private JLabel add;
	private JLabel id_ic;
	private JLabel name_ic;
	private JLabel trash;

	JLayeredPane layeredPane;
	private JPasswordField code;
	private JTextField phoneno;
	JPasswordField password;
	private JLabel usererror;
	private JLabel emailerror;
	private JLabel pherror;
	private JLabel passerror;
	private JTextField password1;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTeachers window = new EditTeachers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditTeachers() {
		initialize(layeredPane);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JLayeredPane layeredPane) {
		this.layeredPane=layeredPane;
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditTeachers.class.getResource("/image/graduation-solid-72.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setAutoRequestFocus(false);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_1777184617500");
		panel.setLayout(null);

		teachername = new JTextField();
		teachername.setText("Teacher name");
		teachername.setForeground(Color.LIGHT_GRAY);
		teachername.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

		teachername.setBounds(115, 30, 107, 28);
		teachername.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(teachername.getText().equals("Teacher name")) {
					teachername.setForeground(Color.gray);
					teachername.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(teachername.getText().isEmpty() && !teachername.getText().equals("Teacher name")) {
					teachername.setForeground(new Color(192, 192, 192));
					teachername.setText("Teacher name");
				}
			}
		});
		panel.add(teachername);
		teachername.setColumns(10);

		Teacher_email = new JTextField();
		Teacher_email.setText("Teacher email");
		Teacher_email.setForeground(Color.LIGHT_GRAY);
		Teacher_email.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		Teacher_email.setColumns(10);
		Teacher_email.setBounds(115, 63, 107, 28);
		Teacher_email.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(Teacher_email.getText().equals("Teacher email")) {
					Teacher_email.setForeground(Color.gray);
					Teacher_email.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(Teacher_email.getText().isEmpty() && !Teacher_email.getText().equals("Teacher email")) {
					Teacher_email.setForeground(new Color(192, 192, 192));
					Teacher_email.setText("Teacher email");
				}
			}
		});
		panel.add(Teacher_email);



		id_ic = new JLabel("");
		id_ic.setIcon(new ImageIcon(EditTeachers.class.getResource("/image/id-card-solid-24.png")));
		id_ic.setBounds(90, 30, 24, 24);
		panel.add(id_ic);

		name_ic = new JLabel("New label");
		name_ic.setIcon(new ImageIcon(EditTeachers.class.getResource("/image/rename-solid-24.png")));
		name_ic.setBounds(90, 65, 24, 24);
		panel.add(name_ic);



		code = new JPasswordField();
		code.setFont(new Font("Tahoma", Font.BOLD, 11));
		code.setForeground(Color.LIGHT_GRAY);
		code.setBounds(115, 167, 107, 28);
		code .setEchoChar((char)0);
		code .setText("Code");

		code .addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(new String(code .getPassword()).equals("Code")){
					code .setEchoChar('*');
					code .setText(null);
					code .setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(new String(code .getPassword()).isEmpty()){
					code .setEchoChar((char)0);
					code .setForeground(Color.LIGHT_GRAY);
					code .setText("Code");
				}
			}
		});
		panel.add(code);



		phoneno = new JTextField();
		phoneno.setText("PhoneNo");
		phoneno.setForeground(Color.LIGHT_GRAY);
		phoneno.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		phoneno.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(phoneno.getText().equals("PhoneNo")) {
					phoneno.setForeground(Color.gray);
					phoneno.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(phoneno.getText().isEmpty() && !phoneno.getText().equals("PhoneNo")) {
					phoneno.setForeground(new Color(192, 192, 192));
					phoneno.setText("PhoneNo");
				}
			}
		});
		phoneno.setColumns(10);
		phoneno.setBounds(115, 96, 107, 28);
		panel.add(phoneno);

		trash = new JLabel("");
		trash.setToolTipText("Delete");
		trash.setIcon(new ImageIcon(EditTeachers.class.getResource("/image/icons8-trash-can-30.png")));
		trash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				trash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

				trash.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-trash-can-30 dark.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				trash.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-trash-can-30.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(trash, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					try {
						Authenticate.deleteTeach(Teacher_email.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
				}
			}
		});
		trash.setBounds(163, 206, 30, 30);
		panel.add(trash);

		JLabel name_ic_1 = new JLabel("New label");
		name_ic_1.setIcon(new ImageIcon(EditTeachers.class.getResource("/image/phone-regular-24.png")));
		name_ic_1.setBounds(90, 100, 24, 24);
		panel.add(name_ic_1);

		JLabel name_ic_1_1 = new JLabel("New label");
		name_ic_1_1.setIcon(new ImageIcon(EditTeachers.class.getResource("/image/key-regular-24.png")));
		name_ic_1_1.setBounds(90, 130, 24, 24);
		panel.add(name_ic_1_1);


		usererror = new JLabel("");
		usererror.setBounds(226, 37, 208, 14);
		usererror.setVisible(false);
		panel.add(usererror);

		emailerror = new JLabel("");
		emailerror.setBounds(226, 70, 208, 14);
		emailerror.setVisible(false);
		panel.add(emailerror);

		pherror = new JLabel("");
		pherror.setBounds(226, 102, 208, 14);
		pherror.setVisible(false);
		panel.add(pherror);

		passerror = new JLabel("");
		passerror.setBounds(226, 137, 208, 14);
		passerror.setVisible(false);
		panel.add(passerror);
		add = new JLabel("");
		add.setIcon(new ImageIcon(EditTeachers.class.getResource("/image/icons8-add-new-30.png")));
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

				add.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-add-new-30 dark.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				add.setIcon(new ImageIcon(Dashboard.class.getResource("/image/icons8-add-new-30.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String emailValidator = "^[a-zA-Z0-9_.+-]+@gmail.com$";
				String email = Teacher_email.getText();
				Pattern emailpattern = Pattern.compile(emailValidator);
				Matcher emailmatcher = emailpattern.matcher(email);

				final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
				String password = password1.getText();
				Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
				Matcher matcher = pattern.matcher(password);

				if (teachername.getText().isEmpty() || Teacher_email.getText().isEmpty() || phoneno.getText().isEmpty() || password1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!emailmatcher.matches()) {
					JOptionPane.showMessageDialog(null, "Email not valid", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!matcher.matches()) {
					JOptionPane.showMessageDialog(null, "The password must contain\n" + 
							" at least 8 characters,\n" + 
							" including a lowercase letter,\n" + 
							" an uppercase letter,\n" + 
							" a number,\n" + 
							" and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int result = JOptionPane.showConfirmDialog(trash, "Are you sure you want to add?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					try {
						Authenticate.addTeacher(teachername.getText(), Teacher_email.getText(), phoneno.getText(), password1.getText(), new String(code.getPassword()), "Teacher");
						JOptionPane.showMessageDialog(null, "Teacher added");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
		add.setBounds(115, 206, 30, 30);
		panel.add(add);

		password1 = new JTextField();
		password1.setText("Password");
		password1.setForeground(Color.LIGHT_GRAY);
		password1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		password1.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(password1.getText().equals("Password")) {
					password1.setForeground(Color.gray);
					password1.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(password1.getText().isEmpty() && !password1.getText().equals("Password")) {
					password1.setForeground(new Color(192, 192, 192));
					password1.setText("Password");
				}
			}
		});
		password1.setColumns(10);
		password1.setBounds(115, 132, 107, 28);
		panel.add(password1);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 434, 261);
		panel.add(lblNewLabel);
		frame.setVisible(true);
	}


}

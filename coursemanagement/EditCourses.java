package coursemanagement;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class EditCourses {

	private JFrame frame;
	private JTextField course_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCourses window = new EditCourses();
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
	public EditCourses() {
		initialize();
	}
	private void initialize() {

		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditCourses.class.getResource("/image/graduation-solid-72.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAutoRequestFocus(false);
		frame.setResizable(false);
		JLabel name_ic = new JLabel("New label");
		name_ic.setIcon(new ImageIcon(EditCourses.class.getResource("/image/rename-solid-24.png")));
		name_ic.setBounds(114, 68, 24, 24);
		frame.getContentPane().add(name_ic);

		course_name = new JTextField();
		course_name.setText("course name");
		course_name.setForeground(Color.LIGHT_GRAY);
		course_name.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));
		course_name.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(course_name.getText().equals("course name")) {
					course_name.setForeground(Color.gray);
					course_name.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(course_name.getText().isEmpty() && !course_name.getText().equals("course name")) {
					course_name.setForeground(new Color(192, 192, 192));
					course_name.setText("course name");
				}
			}
		});
		course_name.setColumns(10);
		course_name.setBounds(139, 69, 107, 28);
		frame.getContentPane().add(course_name);

		JLabel add = new JLabel("");
		add.setIcon(new ImageIcon(EditCourses.class.getResource("/image/icons8-add-new-30.png")));
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
				if(course_name.getText().isEmpty()||course_name.getText().equals("course_name")) {
					JOptionPane.showMessageDialog(null, "Can't add an empty course");
				}else {
					try {
						Authenticate.addCourse(course_name.getText());
						int result = JOptionPane.showConfirmDialog(add, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, "Course Added!");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		add.setBounds(143, 141, 30, 30);
		frame.getContentPane().add(add);

		JLabel trash = new JLabel("");
		trash.setIcon(new ImageIcon(EditCourses.class.getResource("/image/icons8-trash-can-30.png")));
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
				if(course_name.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Can't delete empty course");
				}else {
					int result = JOptionPane.showConfirmDialog(trash, "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {

						try {
							Authenticate.deleteCourse(course_name.getText());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Course not found or "+e1.getMessage());
						}
						JOptionPane.showMessageDialog(null, "Course Deleted!");
					}
					//EDIT
					else if(result == JOptionPane.NO_OPTION){

					}
				}
			}

		});

		trash.setBounds(183, 141, 30, 30);
		frame.getContentPane().add(trash);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EditCourses.class.getResource("/image/Stop-Animation-Blog-Post-Wave-unscreen.gif")));
		lblNewLabel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}

}

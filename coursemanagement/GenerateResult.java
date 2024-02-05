package coursemanagement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GenerateResult {

	private JFrame frame;
	private JTextField student_id;
	private JLabel lblNewLabel_1;
	private JTextField module_id;
	private JTextField marks;
	private JLabel lblNewLabel;
	private JButton add_report;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
						| UnsupportedLookAndFeelException e) {
				}
				GenerateResult window = new GenerateResult();
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
	public GenerateResult() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 496, 335);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAutoRequestFocus(false);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 480, 296);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		student_id = new JTextField();
		student_id.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 13));
		student_id.setText("Student ID");
		student_id.setForeground(Color.LIGHT_GRAY);
		student_id.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(student_id.getText().equals("Student ID")) {
					student_id.setForeground(Color.gray);
					student_id.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(student_id.getText().isEmpty() && !student_id.getText().equals("Student ID")) {
					student_id.setForeground(new Color(192, 192, 192));
					student_id.setText("Student ID");
				}
			}
		});
		student_id.setBounds(59, 37, 96, 28);
		panel.add(student_id);
		student_id.setColumns(10);

		lblNewLabel_1 = new JLabel("Student Report");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(74, 25, 106));
		lblNewLabel_1.setBounds(0, 0, 124, 26);
		panel.add(lblNewLabel_1);

		module_id = new JTextField();
		module_id.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 13));
		module_id.setText("Module ID");
		module_id.setForeground(Color.LIGHT_GRAY);
		module_id.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(module_id.getText().equals("Module ID")) {
					module_id.setForeground(Color.gray);
					module_id.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(module_id.getText().isEmpty() && !module_id.getText().equals("Module ID")) {
					module_id.setForeground(new Color(192, 192, 192));
					module_id.setText("Module ID");
				}
			}
		});
		module_id.setColumns(10);
		module_id.setBounds(59, 76, 96, 28);
		panel.add(module_id);

		marks = new JTextField();
		marks.setText("Marks ");
		marks.setForeground(Color.LIGHT_GRAY);
		marks.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 13));
		marks.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(marks.getText().equals("Marks ")) {
					marks.setForeground(Color.gray);
					marks.setText(null);
				}
			}
			public void focusLost(FocusEvent e) {
				if(marks.getText().isEmpty() && !marks.getText().equals("Marks ")) {
					marks.setForeground(new Color(192, 192, 192));
					marks.setText("Marks ");
				}
			}
		});
		marks.setColumns(10);
		marks.setBounds(59, 112, 96, 28);
		panel.add(marks);

		add_report = new JButton("Add Report");
		add_report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(add_report, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					try {
						Authenticate.addReport(student_id.getText(), module_id.getText(), marks.getText());
						JOptionPane.showMessageDialog(null, "Report Added!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Duplicate data entry or "+e1.getMessage());
					}
				} else if(result == JOptionPane.NO_OPTION){
				}

			}
		});
		add_report.setForeground(Color.WHITE);
		add_report.setBackground(new Color(74, 25, 106));
		add_report.setBounds(44, 151, 118, 23);
		panel.add(add_report);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GenerateResult.class.getResource("/image/having-bad-mark-education-concept_140689-3306__1_-removebg-preview.png")));
		lblNewLabel.setBounds(120, 53, 480, 296);
		panel.add(lblNewLabel);
		frame.setVisible(true);

	}

}

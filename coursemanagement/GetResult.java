package coursemanagement;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GetResult {

	private JFrame frame;
	private JTable table;
	DatabaseManageR db;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetResult window = new GetResult(DatabaseManageR.getInstance());
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
	public GetResult(DatabaseManageR db) {
		this.db=db;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 358);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 494, 319);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		Statement stmt;
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT student_id, module_id, marks FROM results");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");
			model.addColumn("module_id");
			model.addColumn("marks");
			model.addColumn("criteria");

			while (rs.next()) {
				String studentId = rs.getString("student_id");
				String moduleId = rs.getString("module_id");
				int marks = Integer.parseInt(rs.getString("marks"));

				String criteria = "not eligible";
				if (marks >= 40) {
					Statement stmt2 = db.getConnection().createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT COUNT(*) as count FROM results WHERE student_id='" + studentId + "' AND marks >= 40");
					while (rs2.next()) {
						int count = Integer.parseInt(rs2.getString("count"));
						if (count >= 4) {
							criteria = "eligible";
						}
					}
				}
				model.addRow(new Object[]{studentId, moduleId, marks, criteria});
			}

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 491, 319);
			panel.add(scrollPane);

			table = new JTable(model);
			table.setBackground(new Color(22, 19, 65));
			table.setEnabled(false);
			table.getTableHeader().setReorderingAllowed(false);
			table.setForeground(Color.WHITE);
			scrollPane.setViewportView(table);
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.setVisible(true);

	}


}

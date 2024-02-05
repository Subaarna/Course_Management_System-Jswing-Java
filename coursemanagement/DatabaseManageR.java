package coursemanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManageR {
	private Connection conn;
	private Statement stmt;
	private static DatabaseManageR instance;

	public static DatabaseManageR getInstance() throws SQLException {
		if (instance == null)
			instance = new DatabaseManageR("jdbc:mysql://localhost:3306", "root", "");
		return instance;
	}


	public DatabaseManageR(String path, String username, String password) throws SQLException {

		this.conn = DriverManager.getConnection(path, username, password);
		this.stmt = conn.createStatement();
		System.out.println("Connected to database");
		createDatabase("course_management_system");
		String query = "USE course_management_system";
		stmt.executeUpdate(query);
		createAuthTable();
	}

	public void createDatabase(String dbName) throws SQLException {
		try {
			stmt.executeUpdate("CREATE DATABASE " + dbName);
			System.out.println("Database created!");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1007)
				System.out.println("Database exists!");
			else
				throw e;
		}
	}

	private void createAuthTable() throws SQLException {
		try {




			String createAdminTableSQL = "CREATE TABLE admin_table (id INT AUTO_INCREMENT PRIMARY KEY, fullName VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, phoneNo VARCHAR(15) NOT NULL, password VARCHAR(100) NOT NULL, code VARCHAR(50) NOT NULL)";
			stmt.executeUpdate(createAdminTableSQL);


			String coursesTableSQL = """
					CREATE TABLE courses (
						course_id INT PRIMARY KEY AUTO_INCREMENT,
						course_name VARCHAR(100) NOT NULL)
					""";
			stmt.executeUpdate(coursesTableSQL);

			String insertIntoCoursesSQL = """
					INSERT INTO courses(course_name) VALUES
					  ('BIT'),
					  ('BIBM'),
					  ('IMBA');
					""";
			stmt.executeUpdate(insertIntoCoursesSQL);

			String modulesTableSQL = """
					CREATE TABLE modules (
						module_id INT PRIMARY KEY AUTO_INCREMENT,
						module_name VARCHAR(100) NOT NULL,
						module_type VARCHAR(100) NOT NULL,
						course_id INT NOT NULL,
						FOREIGN KEY (course_id) REFERENCES courses(course_id))
					""";
			stmt.executeUpdate(modulesTableSQL);
			String insertIntoModulesSQL = """
					INSERT INTO modules (module_name,module_type, course_id) VALUES
						('Introductory Programming and Problem Solving','optional', 1),
						('Embedded System Programming','core', 1),
						('Academic Skills and Team-Based Learning','optional', 1),
						('Fundamentals of Computing','core', 1),
						('Computational Mathematics','core', 1),
						('21st Century Management','core', 2),
						('Principles of Business','core', 2),
						('Project Based Learning','core', 2),
						('The Digital Business','optional', 2),
						('The Innovative Business','optional', 2),
						('The Masters Research Project','core', 3),
						('Financial Decision Making','core', 3),
						('The Masters Professional Project','core', 3),
						('Strategic Operations Management','optional', 3),
						('Strategic Global Marketing','optional', 3);
					""";
			stmt.executeUpdate(insertIntoModulesSQL);

			String createStudentTableSQL = "CREATE TABLE student_table (student_id INT AUTO_INCREMENT PRIMARY KEY, fullName VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, phoneNo VARCHAR(15) NOT NULL, password VARCHAR(100) NOT NULL, Course VARCHAR(50)NOT NULL, level INT NOT NULL, course_id INT, FOREIGN KEY (course_id) REFERENCES courses(course_id))";
			stmt.executeUpdate(createStudentTableSQL);

			String createTeacherTableSQL = "CREATE TABLE teacher_table (teacher_id INT AUTO_INCREMENT PRIMARY KEY, fullName VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, phoneNo VARCHAR(15) NOT NULL, password VARCHAR(100), code VARCHAR(50) NOT NULL)";
			stmt.executeUpdate(createTeacherTableSQL);
			String resultTableSQL = """
					CREATE TABLE results (
					    student_id INT NOT NULL,
					    module_id INT NOT NULL,
					    marks INT NOT NULL,
					    FOREIGN KEY (student_id) REFERENCES student_table(student_id),
					    FOREIGN KEY (module_id) REFERENCES modules(module_id),
					    PRIMARY KEY (student_id, module_id))
					""";
			stmt.executeUpdate(resultTableSQL);
			System.out.println("Tables Created and Populated!");

			System.out.println("Table Created!");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1050)
				System.out.println("Table exists!");
			else
				throw e;
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}


}




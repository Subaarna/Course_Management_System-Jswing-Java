package coursemanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;




public class Authenticate {

	private static PreparedStatement retrieveTeachernameStmt;
	private static PreparedStatement retrieveStudentnameStmt;
	private static PreparedStatement retrieveadminnameStmt;
	private static PreparedStatement checkStudentEmailExistenceStmt;
	private static PreparedStatement checkTeacherEmailExistenceStmt;
	private static PreparedStatement checkAdminEmailExistenceStmt;
	private static PreparedStatement addStudentCredentialStmt;
	private static PreparedStatement addTeacherCredentialStmt;
	private static PreparedStatement addAdminCredentialStmt;
	private static PreparedStatement retrieveStudentdetailStmt;
	private static PreparedStatement addCourseStmt;
	private static PreparedStatement deleteCourseStmt;
	private static PreparedStatement addTeacherStmt;
	private static PreparedStatement deleteTeacherStmt;
	private static PreparedStatement addReportStmt;
	public Authenticate (DatabaseManageR db) throws SQLException {




		retrieveTeachernameStmt = db.getConnection().prepareStatement("SELECT fullName FROM teacher_table WHERE email=? AND BINARY password=? AND BINARY code=?");
		retrieveStudentnameStmt = db.getConnection().prepareStatement("SELECT fullName FROM student_table WHERE email=? AND BINARY password=?");
		retrieveadminnameStmt = db.getConnection().prepareStatement("SELECT fullName FROM admin_table WHERE email=? AND BINARY password=? AND BINARY code =?");


		checkStudentEmailExistenceStmt = db.getConnection().prepareStatement("SELECT email FROM student_table WHERE email=?");
		checkTeacherEmailExistenceStmt = db.getConnection().prepareStatement("SELECT email FROM teacher_table WHERE email=?");
		checkAdminEmailExistenceStmt = db.getConnection().prepareStatement("SELECT email FROM admin_table WHERE email=?");
		addStudentCredentialStmt = db.getConnection()
				.prepareStatement("INSERT INTO student_table (fullName, email, phoneNo, password, course, level) VALUES (?,?,?,?,?,?)");
		addTeacherCredentialStmt = db.getConnection()
				.prepareStatement("INSERT INTO teacher_table(fullName, email,  phoneNo, password, code) VALUES (?,?,?,?,?)");
		addAdminCredentialStmt = db.getConnection()
				.prepareStatement("INSERT INTO admin_table (fullName, email, phoneNo, password, code) VALUES (?,?,?,?,?)");

		retrieveStudentdetailStmt=db.getConnection()
				.prepareStatement("SELECT fullName, email, phoneNo, Course FROM student_table");
		addCourseStmt = db.getConnection().prepareStatement("INSERT INTO courses(course_name) VALUES (?)");
		deleteCourseStmt = db.getConnection().prepareStatement("DELETE FROM courses WHERE course_name = ?");
		addTeacherStmt = db.getConnection().prepareStatement("INSERT INTO teacher_table(fullName, email, phoneNo, password, code) VALUES(?,?,?,?,?)");
		deleteTeacherStmt = db.getConnection().prepareStatement("DELETE FROM teacher_table WHERE email = ?");
		addReportStmt= db.getConnection().prepareStatement("INSERT INTO results(student_id, module_id, marks) VALUES(?,?,?)");
	}


	public static void login(String email, String password, String code, String role) throws Exception {

		ResultSet rs = null;
		try {
			switch (role) {
			case "Student":
				retrieveStudentnameStmt.setString(1, email);
				retrieveStudentnameStmt.setString(2, password);
				rs = retrieveStudentnameStmt.executeQuery();
				if (rs.next()) {
					new Dashboard(DatabaseManageR.getInstance(), role);
					break;
				} else {
					throw new Exception("Password not valid!");
				}
			case "Teacher":
				retrieveTeachernameStmt.setString(1, email);
				retrieveTeachernameStmt.setString(2, password);
				retrieveTeachernameStmt.setString(3, code);
				rs = retrieveTeachernameStmt.executeQuery();
				if (rs.next()) {
					new Dashboard(DatabaseManageR.getInstance(), role);
					break;
				} else {
					throw new Exception("Password or code not valid!");
				}

			case "Admin":
				retrieveadminnameStmt.setString(1, email);
				retrieveadminnameStmt.setString(2, password);
				retrieveadminnameStmt.setString(3, code);
				rs = retrieveadminnameStmt.executeQuery();
				if (rs.next()) {
					new Dashboard(DatabaseManageR.getInstance(), role);
					break;
				} else {
					throw new Exception("Password or code not valid!");
				}
			default:
				throw new Exception("An error occurred while retrieving code!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while checking the email and password!");
		}

	}

	public static void addCredential(String name, String email, String password, String phoneNo,String code, String course, String role, String level) throws Exception {
		try {

			ResultSet rs = null;
			switch (role) {
			case "Student":
				checkStudentEmailExistenceStmt.setString(1, email);
				rs = checkStudentEmailExistenceStmt.executeQuery();
				break;
			case "Teacher":
				checkTeacherEmailExistenceStmt.setString(1, email);
				rs = checkTeacherEmailExistenceStmt.executeQuery();
				break;
			case "Admin":
				checkAdminEmailExistenceStmt.setString(1, email);
				rs = checkAdminEmailExistenceStmt.executeQuery();
				break;
			}
			if (rs.next()) {
				throw new Exception("Email already in use!");
			} else {
				switch (role) {
				case "Student":
					addStudentCredentialStmt.setString(1, name);
					addStudentCredentialStmt.setString(2, email);
					addStudentCredentialStmt.setString(3, password);
					addStudentCredentialStmt.setString(4, phoneNo);
					addStudentCredentialStmt.setString(5, course);
					addStudentCredentialStmt.setString(6, level);
					addStudentCredentialStmt.executeUpdate();
					break;
				case "Teacher":

					addTeacherCredentialStmt.setString(1, name);
					addTeacherCredentialStmt.setString(2, email);
					addTeacherCredentialStmt.setString(3, password);
					addTeacherCredentialStmt.setString(4, phoneNo);
					addTeacherCredentialStmt.setString(5, code);
					addTeacherCredentialStmt.executeUpdate();
					break;
				case "Admin":

					addAdminCredentialStmt.setString(1, name);
					addAdminCredentialStmt.setString(2, email);
					addAdminCredentialStmt.setString(3, password);
					addAdminCredentialStmt.setString(4, phoneNo);
					addAdminCredentialStmt.setString(5, code);
					addAdminCredentialStmt.executeUpdate();
					break;

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateStudentTable(String name, String email, String phoneNo, String Course, JTable table) {
		ResultSet rs = null;
		try {
			retrieveStudentdetailStmt.setString(1, email);
			rs = checkStudentEmailExistenceStmt.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static void addCourse(String course_name) throws Exception {
		try {
			addCourseStmt.setString(1,course_name);
			addCourseStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred trying to add course!");

		}
	}
	public static void deleteCourse(String course_name) throws Exception {
		try {
			deleteCourseStmt.setString(1, course_name);
			deleteCourseStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred trying to delete the course!");
		}
	}
	public static void addTeacher(String fullName, String email, String PhoneNo, String password, String code, String role) throws Exception {
		ResultSet rs = null;
		try { 
			switch (role) {
			case "Teacher":
				checkTeacherEmailExistenceStmt.setString(1, email);
				rs = checkTeacherEmailExistenceStmt.executeQuery();
				if (rs.next()) {
					throw new Exception("Email already in use!");
				} else {
					addTeacherStmt.setString(1, fullName);
					addTeacherStmt.setString(2, email);
					addTeacherStmt.setString(3, PhoneNo);
					addTeacherStmt.setString(4, password);
					addTeacherStmt.setString(5, code);
					addTeacherStmt.executeUpdate();
				}
				break;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while adding teacher!");
		}
	}

	public static void deleteTeach(String email) throws Exception {
		try {
			deleteTeacherStmt.setString(1,email);
			deleteTeacherStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while adding teacher!");

		}
	}
	public static void addReport(String student_id, String module_id, String marks) throws Exception {
		try {
			addReportStmt.setString(1,student_id);
			addReportStmt.setString(2,module_id);
			addReportStmt.setString(3,marks);
			addReportStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while adding report!");

		}
	}

}
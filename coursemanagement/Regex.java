package coursemanagement;

import java.util.regex.Pattern;

public class Regex {

	public static boolean validateEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public static boolean validatePassword(String password) {
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(passwordRegex);
		if (password == null) {
			return false;
		}
		return pattern.matcher(password).matches();
	}
	public static boolean validateUsername(String username) {
		String usernameRegex = "^[a-zA-Z0-9]{4,20}$";
		Pattern pattern = Pattern.compile(usernameRegex);
		if (username == null) {
			return false;
		}
		return pattern.matcher(username).matches();
	}

	public static boolean validatePhoneNum(String phnum) {
		String phRegex = "^98\\d{8}$";
		Pattern pattern = Pattern.compile(phRegex);
		if (phnum == null) {
			return false;
		}
		return pattern.matcher(phnum).matches();
	}
	public static boolean validateLevel(String level) {
		String levelRegex = "^[4-6]$";
		Pattern pattern = Pattern.compile(levelRegex);
		if(level== null) {
			return false;
		}
		return pattern.matcher(level).matches();
	}
}
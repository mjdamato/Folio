import java.util.Random;
/**
 * A password generator where you can control what characters can
 * be in the password and what length the password needs to be 
 * 
 * @author Catie Baker and Mia Damato
 *
 */
public class RandomPasswordGenerator {
	int max;
	int min;
	int length;
	String lowerString = "abcdefghijklmnopqrstuvwxyz";
	String upperString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String numberString = "1234567890";
	String totalString= "";
	int tracker = 0;
	
	
	/**
	 * Creates a password generator with the specified properties. For each category that is true, the password 
	 * must contain at least one character of that category (e.g. if numbers is true, the password must contain at 
	 * least one number).
	 * @param upper indicates whether the password should contain upper case letters
	 * @param lower indicates whether the password should contain lower case letters
	 * @param numbers indicates whether the password should contain numbers
	 * @param special indicates what special characters are possible (the empty string indicates no special 
	 * characters are possible)
	 * @param maxLen the maximum length of the password
	 * @param minLen the minimum length of the password
	 */
	public RandomPasswordGenerator(boolean upper, boolean lower, boolean numbers, String special, int maxLen, int minLen) {
		
		
		//if allowLower == true, include characters from lower string
		if (lower) {
			totalString = totalString + lowerString;
			//tracker ++;
		}
		//if allowUpper == true, include characters from upper string
		if (upper) {
			totalString = totalString + upperString;
			//tracker ++;
		}
		//if allowNums == true, include numbers from numbers string
		if (numbers) {
			totalString = totalString + numberString;
			//tracker ++;
		}
		
		//if special is not empty, include characters from special string
		if (!special.isEmpty()){
			totalString = totalString + special;
			//tracker ++;
		}
		
		max = maxLen;
		min = minLen;
		
		
	}
	
	/**
	 * Creates a password checker for the specified password
	 * @param password the password to create the checker for
	 * @return the password checker
	 */
	public PasswordChecker getPasswordChecker(String password) {
		return new PasswordChecker(password);
	}
	
	/**
	 * Generates a random password using the characteristics of the generator. The password
	 * length will be between the min and max length (inclusive) and include at least one
	 * of each of the specific characters.
	 * @return the random password that was generated
	 */
	public String generateRandomPassword() {
		Random rand = new Random();
		
		//find random length from min and max
		int length = rand.nextInt((max - min) + 1) + min;
		//new character array with desired length
		char[] randPass = new char[length];
		
			
		
		//for each index of character array, assign a random character within totalString
		for (int i = 0; i < length; i++) {
			randPass[i] = totalString.charAt(rand.nextInt(totalString.length()));
				// this works, but doesn't ensure at least one of each category!!!
		}
				
			
		
		return randPass.toString();
	}

}

import java.util.Random;

/**
 * A class to crack a password by using a brute force approach to try all possible passwords
 * 
 * @author Catie Baker and Mia Damato
 *
 */
public class PasswordCracker {
	PasswordChecker check;
	int max;
	int min;
	String lowerString = "abcdefghijklmnopqrstuvwxyz";
	String upperString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String numberString = "1234567890";
	String totalString= "";
	String tryPass = "";
	
	/**
	 * Creates password cracker that can generate all possible passwords that fit the given
	 * criteria
	 * @param check the password checker that determines when you have found the password
	 * @param upper indicates whether the password could contain upper case letters
	 * @param lower indicates whether the password could contain lower case letters
	 * @param numbers indicates whether the password could contain numbers
	 * @param special indicates what special characters are possible (the empty string indicates no special 
	 * characters are possible)
	 * @param maxLen the maximum length of the password
	 * @param minLen the minimum length of the password
	 */
	public PasswordCracker(PasswordChecker check, boolean upper, boolean lower, boolean numbers, String special, int maxLen, int minLen) {
		this.check = check;
		//if allowLower == true, include characters from lower string
		if (lower) {
			totalString = totalString + lowerString;
					
		}
		//if allowUpper == true, include characters from upper string
		if (upper) {
			totalString = totalString + upperString;
					
		}
		//if allowNums == true, include numbers from numbers string
		if (numbers) {
			totalString = totalString + numberString;
					
		}
				
		//if special is not empty, include characters from special string
		if (!special.isEmpty()){
			totalString = totalString + special;
					
		}
				
		max = maxLen;
		min = minLen;
	}
	
	/**
	 * Trys all possible passwords that fit the criteria and returns the password when it is found. It will
	 * start by trying all the minimum length passwords, then min+1, and so forth up to and including the max 
	 * length passwords
	 * @return the password or the empty string if no passwords match
	 */
	public String crackPassword() {
		//do not have to have at least one of each char etc
		//calls other methods for each of the lengths 
		
		String found = "";
		
		String result = "";
		//calls trypassoflen for each length possibility
		for (int i = min ; i < max + 1 ; i++) {
			result = tryPasswordsOfLen(i);	
			if (result != "") {
				found = result;
			}
		}
		
		return found;
		
	}
	
	/**
	 * Creates all passwords of the specific length and compares them using the password checker
	 * and then return the password it found or the empty string if no password matched
	 * @param len the length of the password to try
	 * @return the password or the empty string if no passwords match
	 */
	private String tryPasswordsOfLen(int len) {
		//calls tryPasswordsOfLen(len, start)
		
		return tryPasswordsOfLen(len, "");
	}
	
	/**
	 * Recursively creates all possible passwords by adding each possible character to the
	 * starting string until the starting string is the target length. When it is the target length, it checks
	 * to see if it is the correct password and returns the password it was correct or the empty string if it is
	 * not correct. As soon as the password is found, it stops trying the different passwords and returns the 
	 * cracked password
	 * @param len the length of the password to try
	 * @param start the start of the string
	 * @return the password if it was found or the empty string if it was not found
	 */
	private String tryPasswordsOfLen(int len, String start) {
		
		//initialize password attempts
		
		PasswordChecker correct = check;
		char currChar = 0 ;
		String current = "";
		boolean tOrF = false;
			
		//adds each possible character to end of string and tests it
		
		//base case
		if (start.length() == len) {
			tOrF = correct.checkPassword(current);
			
			if (tOrF) {
				return current;
			} else {
				//reset password length
				start = "";
				return start;
			}
	}
		else if (start.length() < len) {
						
			for (int i = 0; i < totalString.length(); i++) {
				//try each possible character
				currChar = totalString.charAt(i);
				current = start + currChar;
					
				String newTry = tryPasswordsOfLen(len, current);
				//if return "" or if return password
				if (newTry != "") {
					return newTry;
				}
				
			}
		}
		return "";
	}
}

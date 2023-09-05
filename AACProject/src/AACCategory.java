import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

/**
 * Creates the categories for a GUI that has a grid of images that represent the 
 * communication device of the AAC.
 * 
 * @author Mia Damato
 *
 */
public class AACCategory {

	private String catName;
	
	HashMap<String, String> category = new HashMap<String, String>() ;
	
	

	/**
	 * Creates a new empty category with the given name
	 * @param catName name of category
	 */
	public AACCategory(String catName) {
		
		//initialize category name
		this.catName = catName;
		
		
	}
	/**
	 * Creates an array of the image locations for all images in the current category.
	 * 
	 * @return imagesArray array of image locations
	 */
	public String[] getImages() {
		//create array with size of category
		int length = category.size();
		 String[] imagesArray = new String[length];
		
		 //iterate through key set using an index
		int i = 0;
		for (String x : category.keySet()) {
				imagesArray[i] = x;
				i ++;
			}
		
		return imagesArray;
		
		}
		
	
	/**
	 * Adds image location and associated text to speech to the current category
	 * 
	 * @param imageLoc location of image
	 * @param name of item / text to be spoken
	 */
	public void addItem(String imageLoc, String text) {
		
		category.put(imageLoc, text);
		
	}
	
	/**
	 * Returns the current category name
	 * 
	 * @return catName the name of the current category
	 */
	public String getCategory() {
		
		//returns catName
		return catName;
	}
	
	/**
	 * given an image, it will return the text to be spoken that is 
	 *  associated with that image. If the image is not found in the expected area, 
	 *  it will throw the ElementNotFoundException
	 *  
	 * @param imageLoc image location to return text of
	 * @return text associated with imageLoc
	 */
	public String getText(String imageLoc) {
		
		//return value (text) of key (imageLoc)
			return category.get(imageLoc);
				
	}
	/**
	 * given an image, it will return true if it is stored in the category, or return false 
	 * otherwise
	 *  
	 * @param imageLoc image location to check
	 * @return boolean
	 */
	public boolean hasImage(String imageLoc) {
		/*
		 * given an image, it will return true if it is stored in the category, or return false 
		 * otherwise
		 */
		//if hashmap .contains(key) of given image location
		if (category.containsKey(imageLoc)) {
			//return true
			return true;
		} 
		else {
			return false;
		}
		
	}

}

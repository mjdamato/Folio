import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Creates the mappings for a GUI that has a grid of images that represent the 
 * communication device of the AAC.
 * 
 * @author Mia Damato
 *
 */
public class AACMappings {
	
	 AACCategory categories;
	 HashMap<String, AACCategory> mappings = new HashMap<String, AACCategory>();
	 String[] imagesArray;
	 
	/**
	 * Creates mappings from given file
	 * 
	 * @param filename name of file with mapping info
	 */
	public AACMappings(String filename) {
		/* should read in the file and create the relevant mappings from images 
		 * to categories and add all the items to each category. It should also start the AAC 
		 * on the home screen
		 */
		
		//process file with scanner:
		File file = new File(filename);
				
		Scanner read;
		try {
			read = new Scanner(file);
		
				
			while (read.hasNext()) {
				String line = read.nextLine();
				//split line into array of location and name
				String[] lineArray = line.split(" ", 2);
				String location = lineArray[0];
				String name = lineArray[1];	
						
				//checks if item is a category
				if (line.charAt(0) != '>') {
					//create new category 
					this.categories = new AACCategory(name);
					
					//add category to mapping with its image location
					mappings.put(location, categories);
					
					
				}
				else if (line.charAt(0) == '>'){
					//remove first character from location
					String newLoc = location.substring(1);
					
					//add item to category
					categories.addItem(newLoc, name);
				}
			}
			//begin AAC at home screen
			this.categories = null;
			
			//close file
			read.close();
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
	/**
	 * Creates an array of the image locations for all images in the current category.
	 * @return imagesArray
	 */
	public String[] getImageLocs() {
		
		//if not on homepage
		if (categories != null) {
			//for this category:
			String[] imagesArray = this.categories.getImages();
			return imagesArray;
		}
		
		//if on homepage:
		else if (categories == null) {
			//repeat code from .getImages for homepage
			int length = mappings.size();
			this.imagesArray = new String[length];
			int i = 0;
			for (String x : mappings.keySet()) {
					this.imagesArray[i] = x;
					i ++;
				}
			return imagesArray;
		}
		return imagesArray;
		
	}
	
	/**
	 * Writes the ACC mappings stored to a file. The file is formatted as the
	 * text location of the category followed by the text name of the category
	 * and then one line per item in the category that starts with > and then has 
	 * the file name and text of that image
	 * 
	 * @param filename name of file to write mappings to
	 */
	public void writeToFile(String filename) {
		/*
		 * writes the AACMappings in the same format that they are read in
		 */
		
		try {
			//create new file with given filename
			FileWriter output = new FileWriter(new File(filename));
			
			//clear category
			this.categories = null;
			
			//iterate through locations in mappings
			for (String loc : mappings.keySet()) {
				//if category:
				if (this.categories == null) {
					this.categories = mappings.get(loc);
					String text = this.categories.getText(loc);
					output.write(loc + " " + text);
				}
				//if in category:
				else {
					this.categories = mappings.get(loc);
					output.write(">" + categories.getCategory() + " " + categories.getText(loc));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Adds the mapping to the current category
	 * (or the default category if that is the current category)
	 * 
	 * @param imageLoc
	 * @param text
	 */
	public void add(String imageLoc, String text) {
		/*
		 * should add the image and text to the category that is currently selected. 
		 * If it is on the home screen, it should create a new category with the given 
		 * image and name. If it is in a category, it should add the image and text to 
		 * speak to the currently shown category
		 */

		//if on home screen:
		if (this.categories == null) {
			//create new category 
			this.categories = new AACCategory(text);
			
			//add category to mapping with its image location
			mappings.put(imageLoc, categories);
		}
		//if inside category:
		else { 
			//add item to category
			categories.addItem(imageLoc, text);
		}
	}
	
	/**
	 * Resets the current category of the AAC back to the default category
	 */
	public void reset() {
		/*
		 * resets the current category to the home screen ("")
		 */
		this.categories = null;
		
	}
	
	/**
	 * Gets the current category
	 * 
	 * @return the current category or the empty string if on the default category
	 */
	public String getCurrentCategory() {
		/*
		 * should return which is the current category the page is on or 
		 * the empty string if the page is empty
		 */
		if(this.categories == null) {
			return "";
		} else {
			return this.categories.getCategory();
		}
	}
	
	
	/** 
	 *  Given the image location selected, it determines the associated text with
	 *  the image. If the image provided is a category, it also updates the AAC's 
	 *  current category to be the category associated with that image
	 *  
	 * @param imageLoc location of image for text to be returned
	 * @return text name associated with image
	 */
	public String getText(String imageLoc) {
		/* given an image, it will either return the name of the category associated 
		* with that image if on the home screen and update the current category to that
		*  category or if in a category, it will return the text to be spoken that is 
		*  associated with that image. If the image is not found in the expected area, 
		*  it will throw the ElementNotFoundException
		*/
		
		//if not on homepage
		if (this.categories != null) {
			String text = categories.getText(imageLoc);
			return text;
		}
		//if on homepage
		else {
			this.categories = mappings.get(imageLoc);
			return this.categories.getCategory();
			
		}
		
	
				
	}
	
	/**
	 * Determines if the image represents a category or text to speak
	 * 
	 * @param imageLoc the location where the image is stored
	 * @return true if the image represents a category, false if the image represents text to speak
	 */
	public boolean isCategory(String imageLoc) {
		/*
		 * given an image, it will return true if it is a category image and false 
		 * otherwise
		 */
		//
		return false;
		
	}

}

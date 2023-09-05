import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Class that models a dulcimer instrument.
 * 
 * @author Mia Damato
 * @version 09-24-22
 */

public class Dulcimer {
    public ArrayList<DulcimerString> baseStrings;

    /**
     * Constructs a Dulcimer with the specified bass, treble2, and treble1 strings.
     *   @param bassNotes a String specifying the bass notes, from bottom to top
     *   @param treble2Notes a String specifying the middle treble notes, from bottom to top
     *   @param treble1Notes a String specifying the end treble notes, from bottom to top
     */
    
    public Dulcimer(String bassNotes, String treble2Notes, String treble1Notes) {
  	//Create ArrayList to store strings
        this.baseStrings = new ArrayList<DulcimerString>();

        //Add all bass notes into strings arraylist
        for (String str : bassNotes.split("\\s+")) {
            this.baseStrings.add(new DulcimerString(str));
        } 
	//Add all treble 2 notes into string arraylist
        for (String str : treble2Notes.split("\\s+")) {
            this.baseStrings.add(new DulcimerString(str));
        } 
	//Add all treble 1 notes into string arraylist
        for (String str : treble1Notes.split("\\s+")) {
            this.baseStrings.add(new DulcimerString(str));
        } 
        
    }
    
    /**
     * Strikes the specified string and sets it to vibrating.
     *   @param stringNum the string number (starting at the bottom with 0)
     */
    public void hammer(int stringNum) {
    	
    	// call strike on note if in range
        if (stringNum >= 0 && stringNum < this.baseStrings.size()) {
            this.baseStrings.get(stringNum).strike();
        }

         
    }

    /**
     * Plays the sounds corresponding to all of the struck strings.
     */
    public void play() {
        double combinedFrequencies = 0.0;
		
        for (int i = 0; i < this.baseStrings.size(); i++) {
        	combinedFrequencies += this.baseStrings.get(i).sample();
        
        }
        StdAudio.play(combinedFrequencies);
    }
    

}

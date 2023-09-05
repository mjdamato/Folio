import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Class that models a dulcimer string.
 * 
 * @author Mia Damato
 * @version 09-24-22
 */
public class DulcimerString {
	private Queue<Double> q = new LinkedList<Double>();
	private int size;
	
	/**
	 * Constructor for DulcimerString; Sets size of queue and fills with 0.0
	 * @param note
	 */
	public DulcimerString(String note) {
		this.q = new LinkedList<Double>();
	
		// calculate number of items in queue 
		double temp = (22.0 - getOffsetFromMiddleC(note))/12.0;
		
		double N = (StdAudio.SAMPLE_RATE * (Math.pow(2.0,temp)) / 440.0);
		//round to nearest integer
		//size of queue
		
		size = (int)Math.round(N);
		
			//create queue with N 0's
		for (int i = 0; i <= size; i++ ) {
			this.q.add(0.0);
		}
	}
    /**
     * Returns note
     *   @param string note
     *   @return string note
     */
	public String getNote(String note) {
		return note;
	}
	
    /**
     * Returns 
     *   @param string note typed/to be played
     */	
	public static int getOffsetFromMiddleC(String note) {
		String scaleString = "A,A#,B,C,C#,D,D#,E,F,F#,G,G#";
			//create array of each note of chromatic scale
		String[] chromScale = scaleString.split(",");
			//initiate octave and index variables
		int octave = 0;
		int index = 0;
		
			//for loop to remove + and -, save as new string, and find index of note in scale
		for(int i = 0; i <= note.length();) {
			
				// if note has any +:
			if (note.contains("+")) {
					//remove + to get plain note
				String plainNote = note.replaceAll("\\+", "");
					//plusCount = amount of +
				int plusCount = note.length() - plainNote.length();
					//amount to add to offset for octaves 
				octave = 12 * plusCount;
					//find note's index in chromatic scale
				for (i = 0; i < chromScale.length; i ++) {
					if (plainNote.equals(chromScale[i])) {
						index = i;
						continue;
					}
				}
			}
			
				//if note has any -:
			else if (note.contains("-")) {	

					//remove - to get plain note
				String plainNote = note.replaceAll("-", "");
					//minusCount = amount of -
				int minusCount = note.length() - plainNote.length();
					//amount to subtract from offset for octaves 
				octave = -(12 * minusCount);
					//find note's index in chromatic scale
				for (i = 0; i < chromScale.length -1; i ++) {
					if (plainNote.equals(chromScale[i])) {
						index = i;
						continue;
					}
				}
			}
				//if note has neither:
			else {
				String plainNote = note;
					//find note's index in chromatic scale
				for (i = 0; i < chromScale.length; i ++) {
					if (plainNote.equals(chromScale[i])) {
						index = i;
						continue;
					}
				}
			}
		}
		//calculate offset
		int offset = index + octave - 3;
		return offset;
				
	}
		
	
    /**
     * Simulates striking a stagnant string, using randomized values to simulate vibration. 
     */
	public void strike() {
		// run through queue and randomize within range of -.5 to .5

			for (int i = 0; i <= size; i++ ) {
				q.remove();
				q.add(getRandominRange(-0.5, 0.5));
			
		}
		
		
		
	}
    /**
     * Creates new modified item in back of queue, and returns new head of queue
     *  
     */
	public double sample() {
		// take first value, second value 
		double first = q.remove();
		double second = q.peek();
		q.add(.996 * (0.5 * (first+second)));
		
		return first;
	}
    /**
     * Returns 
     *   @param bassNotes a String specifying the bass notes, from bottom to top
     */	
	public static double getRandominRange(double min, double max) {
		double random = new Random().nextDouble();
		double result = min + (random * (max - min));
		return result;
	}

	
}

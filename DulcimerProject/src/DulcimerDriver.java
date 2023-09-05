import java.awt.Font;

/**
 * Driver class for the keyboard-based virtual dulcimer.
 * 
 * @author Mia Damato
 * @version 09-24-22
 */
public class DulcimerDriver {
   public static void main(String[] args) {

	//Create all necessary strings for dulcimer
	String bassKeys = "a   s   d   f   g   h   j   k   l   ;   '";       
        String dashes = "--- --- --- --- --- --- --- --- --- --- ---";
        String dashes2 = "--- --- --- --- --- --- --- --- --- --- --- ---";
        String bassNotes = "A  A   B   C   D   E   F   G   A+ A#+  C+";
        
        String treble2Keys = "1   2   3   4   5   6   7   8   9   0   -   =";
        String treble2Notes = "C#  D   E   F#  G   A+  B+  C+  D+  E+  F#+  G+";
        
        String  treble1Keys = "q   w   e   r   t   y   u   i   o   p   [   ]";
        String treble1Notes = "G#  A+  B+  C#+  D+  E+  F#+  G+  A++  B++  C++  D++";
        
       //Draw simulation
        StdDraw.setFont(new Font("Monospaced", Font.PLAIN, 12));
        StdDraw.textLeft(0.00, 1.00, "DULCIMER KEY MAPPINGS");
        StdDraw.textLeft(0.00, 0.90, "        keys:  " + bassKeys);
        StdDraw.textLeft(0.00, 0.87, "BASS          " + dashes);
        StdDraw.textLeft(0.00, 0.84, "       notes:  " + bassNotes);
        
        StdDraw.textLeft(0.00, 0.76, "        keys:  " + treble2Keys);
        StdDraw.textLeft(0.00, 0.73, "TREBLE2       " + dashes2);
        StdDraw.textLeft(0.00, 0.70, "       notes:  " + treble2Notes);
        
        StdDraw.textLeft(0.00, 0.62, "        keys:  " + treble1Keys);
        StdDraw.textLeft(0.00, 0.59, "TREBLE1       " + dashes2);
        StdDraw.textLeft(0.00, 0.56, "       notes:  " + treble1Notes);
        
        String keys = bassKeys.replace(" ","") + treble2Keys.replace(" ","") + treble1Keys.replace(" ", "");
        
        
        Dulcimer dulc = new Dulcimer(bassNotes, treble2Notes, treble1Notes);
        
        //Take in typed input
       while (true) { 
            if (StdDraw.hasNextKeyTyped()) {
                int typed = keys.indexOf(StdDraw.nextKeyTyped());
            	
                dulc.hammer(typed);
            }
            dulc.play(); 
        }
        
        
        
    }    
}

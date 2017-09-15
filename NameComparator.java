import java.util.Comparator;
/**
 * Samantha Belliveau
 * ID: 110274063
 * Homework 7
 * CSE 214 Recitation Section 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 * @author Samantha Belliveau
 *
 * implements comparator to compare the names of two actors
 */
public class NameComparator implements Comparator{

	@Override
	/**
	 * compare method, returns positive if name of left > right, negative if left < right 
	 * and zero if they are equal
	 */
	public int compare(Object left, Object right){
		Actor tempLeft = (Actor)left;
		Actor tempRight = (Actor)right;
		return tempLeft.getName().compareTo(tempRight.getName());
	}


}

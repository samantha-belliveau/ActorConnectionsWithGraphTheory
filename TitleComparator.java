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
 * implements comparator to compare the titles of two movies
 */
public class TitleComparator implements Comparator<Movie>{

	@Override
	/**
	 * compare method, returns positive if title of left > right, negative if left < right 
	 * and zero if they are equal
	 */
	public int compare(Movie left, Movie right){
		return left.getTitle().compareTo(right.getTitle());
	}
	
}

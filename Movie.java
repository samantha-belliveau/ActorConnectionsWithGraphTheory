import java.util.ArrayList;
import java.util.List;

import big.data.DataSource;
/**
 * Samantha Belliveau
 * ID: 110274063
 * Homework 7
 * CSE 214 Recitation Section 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 * @author Samantha Belliveau
 *
 * Movie class loads the movie information from 
 * the database (title, year)
 */
public class Movie {
	private String title;
	private List<Actor> actors = new ArrayList<Actor>();
	private int year;
	
	/**
	 * constructor for Movie, loads the movie title and
	 * year from the database
	 * @param title title to look for
	 */
	public Movie(String title){
        String prefix= "http://www.omdbapi.com/?t=";
        String postfix="&y=&plot=short&r=xml";
        
        if(title.length()>0){
                DataSource ds = DataSource.connectXML(prefix+title.replace(' ','+')+postfix);
                ds.load();
                this.title = ds.fetchString("movie/title");
                String actorsString = ds.fetchString("movie/actors");

                year = ds.fetchInt("movie/year");

        }
	}

	/**
	 * 
	 * @return title of movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets title of movie
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return list of actors in movie
	 */
	public List<Actor> getActors() {
		return actors;
	}

	/**
	 * adds to the list of actors in the movie
	 * @param actors actor to add
	 */
	public void addActors(Actor actors) {
		this.actors.add(actors);
	}

	/**
	 * 
	 * @return year movie was made
	 */
	public int getYear() {
		return year;
	}

	/**
	 * sets the year movie was made
	 * @param year year to set 
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * returns title for printing purposes
	 */
	public String toString(){
		return title;
	}
	
	
}


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Samantha Belliveau
 * ID: 110274063
 * Homework 7
 * CSE 214 Recitation Section 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 * @author Samantha Belliveau
 *
 * Actor class holds the actors name, movies 
 * he/she is in, friends, whether it was visited in
 * a bfs
 */
public class Actor {
	private String name;
	private List<Movie> movies = new ArrayList<Movie>();
	private List<Actor> friends = new ArrayList<Actor>();
	private boolean visited;
	private LinkedList<String> path = new LinkedList<String>();
	
	/**
	 * constructor, initializes name to given string
	 * @param name string to set as name
	 */
	public Actor(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @return name of actor
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets name of actor
	 * @param name string to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return list of movies the actor is in
	 */
	public List<Movie> getMovies() {
		return movies;
	}
	
	/**
	 * adds a movie to the movie list
	 * @param movie movie object to add
	 */
	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	/**
	 * 
	 * @return list of actor's friends
	 */
	public List<Actor> getFriends() {
		return friends;
	}
	
	/**
	 * adds an actor to the friend list
	 * @param friend actor to add
	 */
	public void addFriends(Actor friend) {
		friends.add(friend);
	}
	
	/**
	 * 
	 * @return true if has been visited, false otherwise
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * sets whether the actor has been visited
	 * @param visited value to set visited to
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * 
	 * @return path from a given actor to this actor
	 */
	public LinkedList<String> getPath() {
		if (path == null){
			return null;
		}
		return path;
	}
	
	/**
	 * sets the path
	 * @param path list to set path to
	 */
	public void setPath(LinkedList<String> path) {
		this.path = path;
	}
	
	/**
	 * returns name for printing purposes
	 */
	public String toString(){
		return name;
	}
	
	
}

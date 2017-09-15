import java.util.ArrayList;
import java.util.HashMap;
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
 * simulates a graph so a breadth first 
 * search traversal can be implemented
 */
public class ActorGraph {
	private static HashMap<String, Actor> actorsByName = new HashMap<String, Actor>();
	private static HashMap<String, Movie> moviesByTitle = new HashMap<String, Movie>();
	
	/**
	 * adds an actor to the actorsByName hashmap
	 * @param actor actor to add
	 */
	public void addActor(Actor actor){
		actorsByName.put(actor.getName(), actor);
	}
	
	/**
	 * adds a movie to the moviesByTitle hashmap
	 * @param movie movie to add
	 */
	public void addMovie(Movie movie){
		moviesByTitle.put(movie.getTitle(), movie);
	}
	
	/**
	 * returns the actor mapped to the string name
	 * @param name actor name to search for
	 * @return actor mapped to the string name
	 */
	public Actor getActor(String name){
		return actorsByName.get(name);
	}
	
	/**
	 * searched the friends of the given actor using
	 * breadth first search
	 * @param name string name of actor to start with
	 * @return linkedlist containing the string names 
	 * of the actors in order of the breadth first search
	 */
	public LinkedList<String> bfs(String name){
		Actor start = actorsByName.get(name);
		
		LinkedList<String> searchString = new LinkedList<String>();
		
	    List<Actor> connections;
	    
	    LinkedList<String> path = new LinkedList<String>();
	    
	    int i;
	    
	    Actor vertex, nextNeighbor;
	    
	    LinkedList<Actor> q = new LinkedList<Actor>( );
	    
	    start.setVisited(true);
	    
	    q.add(start);
	    
	    path.add(start.getName());
	    start.setPath(path);
	    
	    while (!q.isEmpty( )) {
	    	
	        vertex = q.remove();
	        searchString.add(vertex.getName());
	        connections = vertex.getFriends();
	        
	        for (i = 0; i < connections.size(); i++) {
	        	
	            nextNeighbor = connections.get(i);

	            if (!nextNeighbor.isVisited()) {
	            	
		            LinkedList<String> temp = (LinkedList<String>)vertex.getPath().clone();
		            temp.add(nextNeighbor.getName());
		        	nextNeighbor.setPath(temp);
		        	
	                nextNeighbor.setVisited(true);
	                q.add(nextNeighbor);
	                
	            }
	        }
	    }
	    return searchString;
	}

}

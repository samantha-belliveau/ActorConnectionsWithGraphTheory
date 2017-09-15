import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import big.data.DataSource;
public class SamGui extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Movie> movies = new ArrayList<Movie>();
		ActorGraph graph = new ActorGraph();
		List<Actor> actors = new ArrayList<Actor>();
		
		GridPane mainGrid = new GridPane();
		
		
		
		Button b1 = new Button("Import a movie");
		Button b2 = new Button("Print all actors");
		Button b3 = new Button("Print all movies");

		Button b4 = new Button("Print the shortest path \nbetween two actors");
		Button b5 = new Button("Print the breath first search \nfrom a given actor");
		Button b6 = new Button("Lookup Actor by name");
		Button b7 = new Button("Quit");
		
		b1.setPrefSize(200, 150);
		b2.setPrefSize(200, 150);
		b3.setPrefSize(200, 150);
		b4.setPrefSize(200, 150);
		b5.setPrefSize(200, 150);
		b6.setPrefSize(200, 150);
		b7.setPrefSize(200, 20);
		
		TextField tf = new TextField();
		mainGrid.add(b1, 0, 0);
		mainGrid.add(b2, 1, 0);
		mainGrid.add(b3, 2, 0);
		mainGrid.add(b4, 3, 0);
		mainGrid.add(b5, 4, 0);
		mainGrid.add(b6, 5, 0);
		mainGrid.add(b7, 1, 1);
		mainGrid.add(tf, 0, 1);
		
		Scene mainScene = new Scene(mainGrid);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		b1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        String title = tf.getText();
		        try{
			        Movie newMovie = new Movie(title);
			        
			        movies.add(newMovie);
			        
			        String prefix= "http://www.omdbapi.com/?t=";
			        String postfix="&y=&plot=short&r=xml";
	                DataSource ds = DataSource.connectXML(prefix+title.replace(' ','+')+postfix);
	                ds.load();
	                
	                String actorsString = ds.fetchString("movie/actors");
	                int init = 0;
	                for (int i = 0; i < actorsString.length(); i++){
	                	
	                	boolean exists = false;
	                	
	                	if (actorsString.charAt(i) == ','){
	                		String actorTemp = actorsString.substring(init, i);
	                		for (int j = 0; j < actors.size(); j++){
	                			if (actorTemp.equalsIgnoreCase(actors.get(j).getName())){
	                				actors.get(j).addMovie(newMovie);
	                				exists = true;
	                				newMovie.addActors(actors.get(j));
	                			}
	                		}
	                		if (!exists){
	                    		Actor actor = new Actor(actorTemp);
	                    		actor.addMovie(newMovie);
	                    		actors.add(actor);
	        		        	graph.addActor(actor);
	                    		newMovie.addActors(actor);
	                		}
	                		init = i+2;
	                	}
	                	if (i == actorsString.length()-1){
	                   		String actorTemp = actorsString.substring(init);
	                		for (int j = 0; j < actors.size(); j++){
	                			if (actorTemp.equalsIgnoreCase(actors.get(j).getName())){
	                				actors.get(j).addMovie(newMovie);
	                				exists = true;
	                				newMovie.addActors(actors.get(j));
	                			}
	                		}
	                		if (!exists){
	                    		Actor actor = new Actor(actorTemp);
	                    		actor.addMovie(newMovie);
	                    		actors.add(actor);
	        		        	graph.addActor(actor);
	                    		newMovie.addActors(actor);
	                		}
	                	}
	                }
	                for (int i = 0; i < newMovie.getActors().size(); i++){
	                	for (int j = 0; j < newMovie.getActors().size(); j++){
	                		if (i == j)
	                			continue;
	                		newMovie.getActors().get(i).addFriends(newMovie.getActors().get(j));
	                	}
	                }
	                
			        graph.addMovie(newMovie);
			        
			        for (int i = 0; i < newMovie.getActors().size(); i++){
			        	graph.addActor(newMovie.getActors().get(i));
			        }
		        }catch(Exception bleh){
			    	Stage printBFSStage = new Stage();
			    	GridPane printBFSGrid = new GridPane();
			    	Text bfs = new Text("Error, invalid movie title.");
			    	printBFSGrid.add(bfs, 0, 0);
			    	Scene printBFSScene = new Scene(printBFSGrid);
			    	printBFSStage.setScene(printBFSScene);
			    	printBFSStage.show();
		        }

		                   		                        
		  }
		});
		b2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Stage printActorsStage = new Stage();
		    	GridPane printActorsGrid = new GridPane();
		    	
		    	String actorsList = "Current List of Actors:\t\t\n";
		    	actors.sort(new NameComparator());
		    	for (int i = 0; i < actors.size(); i++){
		    		actorsList += actors.get(i).getName() + "\n";
		    	}
		    	Text actorList = new Text(actorsList);
		    	printActorsGrid.add(actorList, 0, 0);
		    	Scene printActorsScene = new Scene(printActorsGrid);
		    	printActorsStage.setScene(printActorsScene);
		    	printActorsStage.show();
		    	
		    	
		    }
		});
		b3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Stage printMoviesStage = new Stage();
		    	GridPane printMoviesGrid = new GridPane();
		    	String moviesList = "Current List of Movies:\t\t\n";
		    	movies.sort(new TitleComparator());
		    	for (int i = 0; i < movies.size(); i++){
		    		moviesList += movies.get(i).getTitle() + "\n";
		    	}
		    	Text movieList = new Text(moviesList);
		    	printMoviesGrid.add(movieList, 0, 0);
		    	Scene printMoviesScene = new Scene(printMoviesGrid);
		    	printMoviesStage.setScene(printMoviesScene);
		    	printMoviesStage.show();
		    }
		});

		b4.setOnAction(new EventHandler<ActionEvent>() { // shortest path between 2 actors
		    @Override public void handle(ActionEvent e) {
		    	try{
			    	String actorString = tf.getText();
			    	String actor1 = actorString.substring(0, actorString.indexOf(','));
			    	String actor2 = actorString.substring(actorString.indexOf(',')+2);
			    	
			    	Actor actorStart = graph.getActor(actor1);
			    	Actor actorEnd = graph.getActor(actor2);
			    	
			    	graph.bfs(actor1);
			    	
			    	String path = "Shortest path between " + actor1 + " and " + actor2 + ": \t\n";
			    	if (actorEnd.getPath().size() == 0){
			    		path += "No path.";
			    	}
			    	else{
				    	for (int i = 0; i < actorEnd.getPath().size(); i++){
			    			if (i == actorEnd.getPath().size()-1){
				    			path += actorEnd.getPath().get(i);

			    			}
			    			else{
				    			path += actorEnd.getPath().get(i) + ", ";
			    			}
			    	}
			    	}
			    	
			    	Stage printBFSStage = new Stage();
			    	GridPane printBFSGrid = new GridPane();
			    	Text bfs = new Text(path);
			    	printBFSGrid.add(bfs, 0, 0);
			    	Scene printBFSScene = new Scene(printBFSGrid);
			    	printBFSStage.setScene(printBFSScene);
			    	printBFSStage.show();
			    	
		    	}catch(Exception bleh){
			    		String path = "Invalid input.";
			    		
				    	Stage printBFSStage = new Stage();
				    	GridPane printBFSGrid = new GridPane();
				    	Text bfs = new Text(path);
				    	printBFSGrid.add(bfs, 0, 0);
				    	Scene printBFSScene = new Scene(printBFSGrid);
				    	printBFSStage.setScene(printBFSScene);
				    	printBFSStage.show();
			    }

			    	

		    	}	
		});
		
		b5.setOnAction(new EventHandler<ActionEvent>() { // breadth first search from given actor
		    @Override public void handle(ActionEvent e) {
		    	try{
			    	LinkedList<String> result = graph.bfs(tf.getText());
			    	String resultString = "Breadth first search: ";
			    	for (int i = 0; i < result.size(); i++){
			    		if (i == result.size()-1){
				    		resultString += result.get(i) + "\n";
			    		}
			    		else{
			    			resultString += result.get(i) + ", ";
			    		}
			    	}
			    	Stage printBFSStage = new Stage();
			    	GridPane printBFSGrid = new GridPane();
			    	Text bfs = new Text(resultString);
			    	printBFSGrid.add(bfs, 0, 0);
			    	Scene printBFSScene = new Scene(printBFSGrid);
			    	printBFSStage.setScene(printBFSScene);
			    	printBFSStage.show();
		    	}catch(Exception bleh){
		    		String resultString = "Invalid input.";
		    		
			    	Stage printBFSStage = new Stage();
			    	GridPane printBFSGrid = new GridPane();
			    	Text bfs = new Text(resultString);
			    	printBFSGrid.add(bfs, 0, 0);
			    	Scene printBFSScene = new Scene(printBFSGrid);
			    	printBFSStage.setScene(printBFSScene);
			    	printBFSStage.show();
		    	}

		    }
		});
		b6.setOnAction(new EventHandler<ActionEvent>() {//search actor by name
		    @Override public void handle(ActionEvent e) {
		    	try{
			    	Actor searchedActor = graph.getActor(tf.getText());
			    	
			    	Stage searchActorStage = new Stage();
			    	GridPane searchActorGrid = new GridPane();
			    	String actorInfo = "";
			    	actorInfo = "Actor: " + searchedActor.getName() + "\n";
			    	actorInfo += "\nFriends: \n";
			    	
			    	for (int i = 0; i < searchedActor.getFriends().size(); i++){
			    		System.out.print(searchedActor.getFriends().size());
			    		actorInfo += searchedActor.getFriends().get(i).getName() + "\n";
			    	}
			    	actorInfo += "\nMovies: \n";
			    	for (int i = 0; i < searchedActor.getMovies().size(); i++){
			    		actorInfo += searchedActor.getMovies().get(i).getTitle() + "(" + 
			    				searchedActor.getMovies().get(i).getYear() + ")\n";
			    	}
			    	Text actorsInfo = new Text(actorInfo);
			    	searchActorGrid.add(actorsInfo, 0, 0);
			    	Scene searchActorScene = new Scene(searchActorGrid);
			    	searchActorStage.setScene(searchActorScene);
			    	searchActorStage.show();
		    	}catch(Exception bleh){
		    		String actorInfo = "Invalid actor name.";
		    		
			    	Stage searchActorStage = new Stage();
			    	GridPane searchActorGrid = new GridPane();
			    	Text actorsInfo = new Text(actorInfo);
			    	searchActorGrid.add(actorsInfo, 0, 0);
			    	Scene searchActorScene = new Scene(searchActorGrid);
			    	searchActorStage.setScene(searchActorScene);
			    	searchActorStage.show();
		    	}
		    }
		});
		
		b7.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	primaryStage.close();
		    }
		});
		
	}
	public static void main(String[] args){
		launch(args);
	}
}


import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;									
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;							
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;					
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application{
	
	private ArrayList<String> listOfBlocks = new ArrayList<String>();
	private GridPane grid = new GridPane();
	private static final double WIDTH_HEIGHT = 8.33333;
	private String[][] dungeonLayout = new String[12][12];
	private ArrayList<Pane> panes = new ArrayList<Pane>();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Testing");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 600, 600);
		
		Pane player = new StackPane();
		//player.setStyle("-fx-background-color: black;");
		player.setScaleX(5);
		player.setScaleY(5);
		player.setTranslateX(-225);
		player.setTranslateY(-225);
		player.setMaxSize(5,5);
		
		
		Image image = new Image("manny_stand.png");
		Image mirr = new Image("manny_left.png");
		ImageView manny = new ImageView(image);
		
		
		
//		Visual Effects which could be used for potions	//
		
//		DropShadow shadow = new DropShadow(2, Color.RED);
//		manny.setEffect(shadow);
		
		
		
		manny.setFitHeight(25);
		manny.setFitWidth(25);
		player.getChildren().add(manny);
		
		//root.getChildren().add(player);
		root.getChildren().add(createGUI());
		root.getChildren().add(player);
		
		int next = 0;
		for(int i=0; i<12; i++) {
			for(int j=0; j<12; j++) {
				if(getListOfBlocks(next) == "P") {
					dungeonLayout[i][j] = "P";
				}
				
				else {
					dungeonLayout[i][j] = "W";
				}
				next++;
			}
		}
		
		dungeonLayout[1][1] = ".";
		
		
		
		// 		Player Movement		//
		
		//		"Translate" stuff to be removed (only backend) 	//
		
		
		
		scene.setOnKeyPressed(e->{
				if(e.getCode() == KeyCode.RIGHT) {
					manny.setImage(image);
					if(!(player.getTranslateX()+50 > 300)) {
						for(int i=0; i<12; i++) {
							for(int j=0; j<11; j++) {
								
								if (dungeonLayout[i][j] == "." && dungeonLayout[i][j+1] == "P") {
									
									player.setTranslateX(player.getTranslateX()+50);
									
									String temp = dungeonLayout[i][j];
									dungeonLayout[i][j] = dungeonLayout[i][j+1];
									dungeonLayout[i][j+1] = temp;
									
									return;
								}
							}
						}
					}
				}
				
				else if(e.getCode() == KeyCode.LEFT) {
					//manny.setImage(mirr);
					if(!(player.getTranslateX()-50 < -300)) {
												
						for(int i=0; i<12; i++) {
							for(int j=1; j<12; j++) {
								if (dungeonLayout[i][j] == "." && dungeonLayout[i][j-1] == "P") {
									
									player.setTranslateX(player.getTranslateX()-50);
									
									String temp = dungeonLayout[i][j];
									dungeonLayout[i][j] = dungeonLayout[i][j-1];
									dungeonLayout[i][j-1] = temp;
									
									return;
								}
							}
						}
					}
				}
				
				if(e.getCode() == KeyCode.UP) {
					if(!(player.getTranslateY()-50 < -300)) {
						for(int i=1; i<12; i++) {
							for(int j=0; j<12; j++) {
								if (dungeonLayout[i][j] == "." && dungeonLayout[i-1][j] == "P") {
									
									player.setTranslateY(player.getTranslateY()-50);
									
									String temp = dungeonLayout[i][j];
									dungeonLayout[i][j] = dungeonLayout[i-1][j];
									dungeonLayout[i-1][j] = temp;
									
									return;
								}
							}
						}
					}
				}
				
				else if(e.getCode() == KeyCode.DOWN) {
					if(!(player.getTranslateY()+50 > 300)) {
						for(int i=0; i<11; i++) {
							for(int j=0; j<12; j++) {
								if(dungeonLayout[i][j] == "." && dungeonLayout[i+1][j] == "P") {
									
									player.setTranslateY(player.getTranslateY()+50);
									
									String temp = dungeonLayout[i][j];
									dungeonLayout[i][j] = dungeonLayout[i+1][j];
									dungeonLayout[i+1][j] = temp;
									
									return;
								}
							}
						}
					}
				}
		});
		
		scene.setOnKeyReleased(e->{
			
			for(int i=0; i<12; i++) {
				for(int j=0; j<12; j++) {
					System.out.print(dungeonLayout[i][j] + " ");
				}
				System.out.println();
			}
			
			System.out.println();
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	// 			Actual GUI stuff 			//
	
	
	private GridPane createGUI() {
		
		grid.setPrefSize(600, 600);
		//grid.setHgap(2); grid.setVgap(2);
		
		ArrayList<ColumnConstraints> arrColConstraints = new ArrayList<ColumnConstraints>();
		ArrayList<RowConstraints> arrRowConstraints = new ArrayList<RowConstraints>();
//		ArrayList<Pane> panes = new ArrayList<Pane>();
		
		for(int i=0; i<12; i++) {
			arrColConstraints.add(new ColumnConstraints());
			arrColConstraints.get(i).setPercentWidth(WIDTH_HEIGHT);
			
			arrRowConstraints.add(new RowConstraints());
			arrRowConstraints.get(i).setPercentHeight(WIDTH_HEIGHT);
			
			grid.getColumnConstraints().add(arrColConstraints.get(i));
			grid.getRowConstraints().add(arrRowConstraints.get(i));
			
			//panes[i] = new Pane(); panes[i].setStyle("-fx-background-color: gray; ");
		}
		
		Random r = new Random();
		
		for(int i=0; i<144; i++) {
			int toggle = r.nextInt(100);
			
			if(toggle < 15) {
				Image image = new Image("wall.png");
				ImageView pic = new ImageView();
				pic.setFitHeight(60);
				pic.setFitWidth(60);
				pic.setImage(image);
				//panes.add(new Pane()); panes.get(i).setStyle("-fx-background-color: white; ");
				panes.add(new Pane()); panes.get(i).getChildren().add(pic); panes.get(i).setId("W");
				listOfBlocks.add("W");
			}
			
			else {
				Image image = new Image("walkable.png");
				ImageView pic = new ImageView();
				pic.setFitHeight(60);
				pic.setFitWidth(60);
				pic.setImage(image);
				panes.add(new Pane()); panes.get(i).getChildren().add(pic); panes.get(i).setId("P"); panes.get(i).setEffect(new DropShadow(3, Color.BLACK));
				listOfBlocks.add("P");
			}
				
		}
		
		//System.out.println(grid.getRowIndex(child));
		
		int row = 0;
		int col = 0;
		
		for(int i=0; i<144; i++) {
			grid.add(panes.get(i), col, row, 1, 1);
			col++;
			
			if(col > 11) {
				col = 0;
				row ++;
			}
		}
		
		return grid;	
	}
	
	private String getListOfBlocks(int i){
		return panes.get(i).getId();
	}

}

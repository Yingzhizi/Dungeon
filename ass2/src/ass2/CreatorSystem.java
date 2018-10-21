package ass2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;

import WinCondition.DefaultWin;
import WinCondition.WinCondition;
import collectables.Arrow;
import collectables.Bomb;
import collectables.Sword;
import collectables.Treasure;
import exits.Pit;
import structures.Structure;
import structures.Wall;

public class CreatorSystem extends Observable{
	private Dungeon createDungeon;
	private Player player;
	private WinCondition win;

	public CreatorSystem() {
		this.player = new Player();
		player.setCreateCoord();
		this.win = new DefaultWin();
	}

	public CreatorSystem(Square[][] dungeon, int size, ArrayList<Boulder> bList) {
		this.createDungeon = new Dungeon(dungeon, size);
	}

	public void addDungeon(Square[][] dungeon, int size) {
		this.createDungeon = new Dungeon(dungeon, size);
	}

	public Square[][] generateEmpty(int dungeonSize) {
		Square[][] map = new Square[dungeonSize][dungeonSize];
		for(int i = 0; i < dungeonSize; i++){
			for(int j = 0; j < dungeonSize; j++){
				map[i][j] = new Square();
			}
		}

		for(int i = 0; i < dungeonSize; i++) {
			for(int j = 0; j < dungeonSize; j++) {
				if(i == 1 || i == dungeonSize - 2 || j == 1 || j == dungeonSize -2) {
					Structure wall = new Wall();
					map[i][j].addStructure(wall);
				}
			}
		}
		return map;
	}

	public void createMapFile(){

		File file = new File("ass2/src/createMap/stuff.txt");
		try {
			if(file.createNewFile()){
				System.out.println("success");
				System.out.println(file.getAbsolutePath());
			} else {
				if(file.delete()){
					file.createNewFile();
				} else {
					System.out.print("FAIL!\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public Player getPlayer() {
		return this.player;
	}

	public void setWinCondition(WinCondition w) {
		this.win = w;
	}

	public WinCondition getWin() {
		return win;
	}


	public boolean checkWin() {
		return win.checkWin(createDungeon, player);
	}

	public int getSize() {
		return createDungeon.getSize();
	}

	public Dungeon getDungeon() {
		return this.createDungeon;
	}

	public void generateDungeon(int dungeonSize) {
		Square[][] squares = generateEmpty(dungeonSize);
		Dungeon dungeon = new Dungeon(squares, dungeonSize);

		this.createDungeon = dungeon;
	}

	/*
	 * need to add more shit later
	 */
	public Entity checkEntityType(String type){
		if(type.equals("arrow")){
			return new Arrow();
		} else if (type.equals("sword")){
			return new Sword();
		} else if (type.equals("treasure")){
			return new Treasure();
		} else if (type.equals("pit")){
			return new Pit();
		} else if (type.equals("bomb")){
			return new Bomb();
		} else {
			return null;
		}
	}

	public Square getSquare(int x, int y) {
		return createDungeon.getSquare(x, y);
	}

	public void moveUp() {
		player.moveUp(createDungeon.getDungeon());
		this.setChanged();
		this.notifyObservers();
	}

	public void moveDown() {
		player.moveDown(createDungeon.getDungeon());
		this.setChanged();
		this.notifyObservers();
	}

	public void moveLeft() {
		player.moveLeft(createDungeon.getDungeon());
		this.setChanged();
		this.notifyObservers();
	}

	public void moveRight() {
		player.moveRight(createDungeon.getDungeon());
		this.setChanged();
		this.notifyObservers();
	}

	public void attackSword() {
		player.attackSword(createDungeon.getDungeon());
		this.setChanged();
		this.notifyObservers();
	}

	public void attackArrow() {
		player.attackArrow(createDungeon.getDungeon(), player);
		this.setChanged();
		this.notifyObservers();
	}
}

package ass2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Enemies.Coward;
import Enemies.Hound;
import Enemies.Hunter;
import Enemies.Strategist;
import WinCondition.DefaultWin;
import WinCondition.ExitWin;
import WinCondition.SwitchWin;
import WinCondition.TreasureWin;
import WinCondition.WinCondition;
import collectables.Arrow;
import collectables.Bomb;
import collectables.Key;
import collectables.Sword;
import collectables.Treasure;
import consumables.HoverPotion;
import consumables.InvincibilityPotion;
import exits.Exit;
import exits.Pit;
import structures.Door;
import structures.Structure;
import structures.Switch;
import structures.Wall;

public class CreatorSystem extends Observable{
	private Dungeon createDungeon;
	private Player player;
	private WinCondition win;
	private int dungeonSize;

	public CreatorSystem(int size) {
		this.player = new Player();
		player.setCreateCoord();
		//this.win = new DefaultWin();
		this.dungeonSize = size;
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
				if(i == 0 || i == dungeonSize - 1 || j == 0 || j == dungeonSize - 1) {
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

	public void addToMap(String item) {
		// TODO Auto-generated method stub
		try (FileWriter fw = new FileWriter("ass2/src/createMap/stuff.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(item);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void beforeReadMap() {
		Square[][] squares = generateEmpty(this.dungeonSize);
		Dungeon d = new Dungeon(squares, this.dungeonSize);
		System.out.println(this.dungeonSize);
		WinCondition def = new DefaultWin();
		this.setWinCondition(new ExitWin(def));
		this.setWinCondition(new TreasureWin(def));
		this.createDungeon = d;
	}
	
	public void readMap(Square[][] squares) {
		//this step is to read from the text file
		File file = new File("ass2/src/createMap/stuff.txt");
		try {
			Scanner s = new Scanner(file);
			int x,y;
			String type, itemToAdd = null;
			while(s.hasNextLine()){
				Scanner s2 = new Scanner(s.nextLine());
				type = s2.next();
				x = s2.nextInt();
				y = s2.nextInt();
				itemToAdd = s2.next();
				System.out.println("type " + type + " x, y " + x+"," + y + " " + itemToAdd);
				if(type.equals("Entity")){
					addEntity(squares,x,y,itemToAdd);
				} else if (type.equals("Structure")) {
					addStructure(squares, x,y,itemToAdd);
				} else if (type.equals("Switch")){
					addSwitch(squares, x,y,itemToAdd);
				} else if (type.equals("Enemy")) {
					addEnemy(squares, x,y,itemToAdd);
				} else if (type.equals("Boulder")) {
					addBoulder(squares, x,y,itemToAdd);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void addBoulder(Square[][] s,int x, int y, String itemToAdd) {
		if(itemToAdd.equals("boulder")){
			s[y][x].addBoulder(new Boulder(x, y));
		}

	}

	private void addSwitch(Square[][] s,int x, int y, String itemToAdd) {
		if(itemToAdd.equals("switch")){
			s[y][x].addSwitch(new Switch(x, y));
		}

	}

	private void addEntity(Square[][] s,int x, int y, String itemToAdd) {
		switch(itemToAdd){
			case "exit":
				s[x][y].addEntity(new Exit());
				break;
			case "treasure":
				s[x][y].addEntity(new Treasure());
				break;
			case "key":
				s[x][y].addEntity(new Key());
				break;
			case "bomb":
				s[x][y].addEntity(new Bomb());
				break;
			case "sword":
				s[x][y].addEntity(new Sword());
				break;
			case "arrow":
				s[x][y].addEntity(new Arrow());
				break;
			case "ipotion":
				s[x][y].addEntity(new InvincibilityPotion());
				break;
			case "hpotion":
				s[x][y].addEntity(new HoverPotion());
				break;
			case "pit":
				s[x][y].addEntity(new Pit());
				break;
		}

	}

	private void addStructure(Square[][] s,int x, int y, String itemToAdd) {
		switch(itemToAdd){
			case "door":
				s[x][y].addStructure(new Door());
				break;
			case "wall":
				s[x][y].addStructure(new Wall());
				break;
		}

	}

	private void addEnemy(Square[][] s,int x, int y, String itemToAdd) {
		switch(itemToAdd){
			case "hunter":
				s[x][y].addEnemy(new Hunter(x, y));
				break;
			case "strategist":
				s[x][y].addEnemy(new  Strategist(x, y));
				break;

			case "hound":
				s[x][y].addEnemy(new Hound(x,y));
				break;

			case "coward":
				s[x][y].addEnemy(new Coward(x,y));
				break;
		}

	}

}

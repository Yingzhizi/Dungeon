package ass2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Enemies.*;
import WinCondition.*;
import collectables.*;
import consumables.*;
import exits.Exit;
import exits.Pit;
import structures.*;


/**
 * The main dungeon system
 */
public class DungeonSystem extends Observable {


	private Dungeon dungeon;
	private Player player;
	private WinCondition win;

	public DungeonSystem() {
		this.player = new Player();
		this.win = new DefaultWin();
	}

	public DungeonSystem(Square[][] dungeon, int size, ArrayList<Boulder> bList) {
		this.dungeon = new Dungeon(dungeon, size);
		this.player = new Player();
	}

	public void addDungeon(Square[][] dungeon, int size) {
		this.dungeon = new Dungeon(dungeon, size);
	}

	/*
	 * Generates empty dungeon, with walls on all sides
	 * @param size
	 * @return map
	 */
	public Square[][] generateEmpty(int size) {
		Square[][] map = new Square[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[i][j] = new Square();
			}
		}

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(i == 0 || i == size-1 || j == 0 || j == size-1) {
					Structure wall = new Wall();
					map[i][j].addStructure(wall);
				}
			}
		}
		return map;
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
		return win.checkWin(dungeon, player);
	}


	public int getSize() {
		return dungeon.getSize();
	}

	public Dungeon getDungeon() {
		return dungeon;
	}


	/*
	 * Method for generation of level
	 */
	public void mediumLevel1() {
		Entity treasure = new Treasure();
		Entity sword = new Sword();
		Entity fireSword = new FireMelee((MeleeWeapon) sword);
		Entity arrow = new Arrow();
		Square[][] squares = generateEmpty(10);
		Dungeon dungeon = new Dungeon(squares, 10);
		squares[2][1].addEntity(arrow);
		squares[1][2].addEntity(fireSword);
		squares[1][3].addEntity(sword);
		squares[2][4].addEntity(new Key());
		squares[5][5].addStructure(new Door());
		
		this.setWinCondition(new ExitWin(getWin()));
		this.setWinCondition(new TreasureWin(getWin()));
		this.setWinCondition(new EnemiesWin(getWin()));

		squares[8][8].addEntity(new Exit());
		Enemy e = new Hunter(1, 8);
		squares[1][8].addEnemy(e);
		this.addObserver(e);
		squares[2][8].addStructure(new Wall());
		squares[4][2].addEntity(new Key());
		squares[4][5].addStructure(new Door());
		squares[7][7].addEntity(treasure);
		squares[4][4].addEntity(new Treasure());
		squares[8][8].addEntity(new Exit());

		this.dungeon = dungeon;
	}
	
	// mediumLevel, active all the boulders and reach the exits
	public void mediumLevel2() {
		Square[][] squares = generateEmpty(10);
		Dungeon dungeon = new Dungeon(squares, 10);
		this.setWinCondition(new ExitWin(getWin()));
		this.setWinCondition(new SwitchWin(getWin()));
		
		// here use for test the switches
		Switch s1 = new Switch(3, 5);
		Switch s2 = new Switch(3, 4);
		Switch s3 = new Switch(4, 4);
		Switch s4 = new Switch(4, 6);

		squares[5][3].addSwitch(s1);
		squares[4][3].addSwitch(s2);
		squares[4][4].addSwitch(s3);
		squares[6][4].addSwitch(s4);

		Exit exit = new Exit();
		squares[8][8].addEntity(exit);
		// here use for test the boulders
		Boulder b1 = new Boulder (4, 5);
		Boulder b2 = new Boulder (7, 5);
		Boulder b3 = new Boulder (5, 3);
		Boulder b4 = new Boulder (2, 7);
		Boulder b5 = new Boulder (4, 7);
		Boulder b6 = new Boulder (5, 7);
		squares[5][4].addBoulder(b1);
		squares[5][7].addBoulder(b2);
		squares[3][5].addBoulder(b3);
		squares[7][2].addBoulder(b4);
		squares[7][4].addBoulder(b5);
		squares[7][5].addBoulder(b6);
		Entity pit = new Pit();
		squares[2][3].addEntity(pit);
		squares[5][2].addEntity(pit);
		Entity hoverpotion = new HoverPotion();
		squares[6][8].addEntity(hoverpotion);
		Structure wall = new Wall();
		squares[3][1].addStructure(wall);
		Entity beer = new Beer();
		squares[2][7].addEntity(beer);
		Enemy hunter = new Hunter(4, 8);
		squares[4][8].addEnemy(hunter);
		this.addObserver(hunter);
		Entity arrow = new Arrow();
		squares[1][2].addEntity(arrow);
		squares[4][2].addEntity(arrow);
		squares[1][5].addEntity(arrow);
		this.dungeon = dungeon;
	}

	public void mediumLevel3() {
		Structure w = new Wall();
		Entity e = new Exit();
		Square[][] squares = generateEmpty(10);
		Dungeon dungeon = new Dungeon(squares, 10);
		this.setWinCondition(new ExitWin(getWin()));
		this.setWinCondition(new SwitchWin(getWin()));
		squares[1][2].addStructure(w);
		squares[2][2].addStructure(w);
		squares[3][2].addStructure(w);
		squares[5][1].addBoulder(new Boulder(1, 5));
		squares[3][3].addBoulder(new Boulder(3, 3));
		squares[1][7].addBoulder(new Boulder(7, 1));
		squares[8][6].addBoulder(new Boulder(6, 8));
		squares[6][2].addStructure(w);
		squares[7][2].addStructure(w);
		squares[8][2].addStructure(w);
		squares[6][1].addSwitch(new Switch(1, 6));
		squares[3][1].addSwitch(new Switch(1, 3));
		squares[1][6].addSwitch(new Switch(6, 1));
		squares[8][5].addSwitch(new Switch(5, 8));
		squares[1][3].addStructure(w);
		squares[1][4].addStructure(w);
		squares[1][5].addStructure(w);
		squares[2][5].addStructure(w);
		squares[2][6].addStructure(w);
		squares[2][7].addStructure(w);
		squares[3][5].addStructure(w);
		squares[3][6].addStructure(w);
		squares[3][7].addStructure(w);
		squares[4][5].addStructure(w);
		squares[5][5].addStructure(w);
		squares[6][5].addStructure(w);
		squares[5][4].addStructure(w);
		squares[6][4].addStructure(w);
		squares[7][4].addStructure(w);
		squares[5][7].addStructure(w);
		squares[5][8].addStructure(w);
		squares[6][7].addStructure(w);
		squares[6][8].addStructure(w);
		squares[8][8].addEntity(e);

		this.dungeon = dungeon;
	}
	
	//generate map - easy 1) exit win
	public void easyLevel1() {
		Structure wall = new Wall();
		Entity exit = new Exit();
		Entity key = new Key();
		Structure door = new Door();
		Entity pit = new Pit();
		Entity hover = new HoverPotion();
		
		Square[][] squares = generateEmpty(7);
		Dungeon dungeon = new Dungeon(squares, 7);
		// add win condition, reach the exits, then win
		WinCondition def = new DefaultWin();
		WinCondition win1 = new ExitWin(def);
		this.win = win1;
		squares[5][5].addEntity(exit);
		squares[4][1].addEntity(key);
		squares[5][2].addEntity(hover);
		squares[1][5].addEntity(pit);
		squares[3][2].addStructure(door);		
		squares[1][2].addStructure(wall);
		squares[2][2].addStructure(wall);
		squares[4][2].addStructure(wall);
		squares[5][3].addStructure(wall);
		squares[4][3].addStructure(wall);
		squares[4][4].addStructure(wall);
		squares[3][4].addStructure(wall);
		squares[2][4].addStructure(wall);
		this.dungeon = dungeon;	
	}
	
	public void easyLevel2() {
		Structure wall = new Wall();
		Entity exit = new Exit();
		Entity key1 = new Key();
		Entity key2 = new Key();
		Structure door1 = new Door();
		Structure door2 = new Door();
		Entity pit = new Pit();
		Entity hover = new HoverPotion();
		Square[][] squares = generateEmpty(7);
		Entity treasure = new Treasure();
		Dungeon dungeon = new Dungeon(squares, 7);
		// add win condition, reach the exits, then win
		WinCondition def = new DefaultWin();
		WinCondition win1 = new ExitWin(def);
		WinCondition win2 = new TreasureWin(win1);
		this.win = win2;
		// add exit
		squares[5][5].addEntity(exit);
		squares[5][4].addEntity(treasure);
		squares[4][1].addEntity(key1);
		squares[5][2].addEntity(hover);
		squares[1][5].addEntity(pit);
		squares[3][2].addStructure(door1);
		
		// add Wall
		squares[1][2].addStructure(wall);
		squares[2][2].addStructure(wall);
		squares[3][5].addStructure(wall);
		squares[5][3].addStructure(wall);
		squares[4][4].addStructure(wall);
		squares[4][2].addStructure(wall);
		squares[3][4].addStructure(wall);
		squares[2][4].addStructure(wall);
		this.dungeon = dungeon;	
	}
	
	public void hardLevel2() {
		Structure wall = new Wall();
		Entity exit = new Exit();
		Entity key = new Key();
		Structure door = new Door();
		Entity pit = new Pit();
		Entity hover = new HoverPotion();
		Square[][] squares = generateEmpty(10);
		Entity treasure = new Treasure();
		Entity s = new Sword();
		Entity sword =  new FireMelee((MeleeWeapon) s);
		Dungeon dungeon = new Dungeon(squares, 10);
		// add win condition, reach the exits, then win
		WinCondition def = new DefaultWin();
		WinCondition win1 = new ExitWin(def);
		WinCondition win2 = new TreasureWin(win1);
		WinCondition win3 = new EnemiesWin(win2);
		this.win = win3;
		squares[3][1].addEntity(sword);
		squares[6][6].addEntity(exit);
		squares[5][4].addEntity(treasure);
		squares[4][1].addEntity(key);
		squares[5][2].addEntity(hover);
		squares[1][5].addEntity(pit);
		squares[3][2].addStructure(door);
		
		Enemy e = new Hunter(1,7);
		squares[1][7].addEnemy(e);
		this.addObserver(e);
		
		// add Wall
		squares[1][2].addStructure(wall);
		squares[2][2].addStructure(wall);
		squares[3][5].addStructure(wall);
		squares[5][3].addStructure(wall);
		squares[4][4].addStructure(wall);
		squares[4][2].addStructure(wall);
		squares[3][4].addStructure(wall);
		squares[2][4].addStructure(wall);
		this.dungeon = dungeon;	
	}
	
	//generate map - easy 3), treasure win
	public void easyLevel3() {
		Structure wall = new Wall();
		Entity pit = new Pit();
		Entity hover = new HoverPotion();
		Entity treasure = new Treasure();
		Square[][] squares = generateEmpty(7);
		Dungeon dungeon = new Dungeon(squares, 7);
		// add win condition, reach the exits, then win
		WinCondition def = new DefaultWin();
		WinCondition win1 = new TreasureWin(def);
		this.win = win1;
		// add exit
		squares[4][1].addEntity(pit);
		squares[5][4].addEntity(pit);
		squares[2][5].addEntity(hover);
		squares[1][3].addEntity(treasure);
		squares[3][3].addEntity(treasure);
		squares[5][1].addEntity(treasure);
		squares[1][5].addEntity(treasure);
		squares[2][1].addStructure(wall);
		squares[2][2].addStructure(wall);
		squares[4][2].addStructure(wall);
		squares[4][3].addStructure(wall);
		squares[4][4].addStructure(wall);
		squares[3][4].addStructure(wall);
		squares[3][5].addStructure(wall);
		squares[2][4].addStructure(wall);
		this.dungeon = dungeon;
	}
		
	public void hardLevel1() {
		Structure wall = new Wall();
		Entity exit = new Exit();
		Entity key = new Key();
		Structure door = new Door();
		Entity pit = new Pit();
		Entity hover = new HoverPotion();
		Square[][] squares = generateEmpty(13);
		Entity treasure = new Treasure();
		Entity s = new Sword();
		Entity sword =  new FireMelee((MeleeWeapon) s);
		Dungeon dungeon = new Dungeon(squares, 13);
		// add win condition, reach the exits, then win
		WinCondition def = new DefaultWin();
		WinCondition win1 = new ExitWin(def);
		WinCondition win2 = new TreasureWin(win1);
		WinCondition win3 = new EnemiesWin(win2);
		this.win = win3;
		// add exit
		squares[3][1].addEntity(sword);
		squares[6][6].addEntity(exit);
		squares[5][4].addEntity(treasure);
		squares[4][1].addEntity(key);
		squares[5][2].addEntity(hover);
		squares[1][5].addEntity(pit);
		squares[3][2].addStructure(door);
		
		Enemy e = new Hunter(1,7);
		squares[1][7].addEnemy(e);
		this.addObserver(e);
		
		Enemy e1 = new Coward(2,9);
		squares[2][9].addEnemy(e1);
		this.addObserver(e1);
		
		// add Wall
		squares[1][2].addStructure(wall);
		squares[2][2].addStructure(wall);
		squares[3][5].addStructure(wall);
		squares[5][3].addStructure(wall);
		squares[4][4].addStructure(wall);
		squares[4][2].addStructure(wall);
		squares[3][4].addStructure(wall);
		squares[2][4].addStructure(wall);
		this.dungeon = dungeon;	
	}
	

	
	/*
	 * Method to read the entities from a file
	 * @param difficulty
	 */
	public void readFromText(String difficulty) {
		System.out.println(difficulty);
		int dungeonSize = dungeonSize(difficulty);
		String fileName = getDungeonMap(difficulty);
		System.out.println("*****" + fileName + "*******");
		Square[][] squares = generateEmpty(dungeonSize);
		Dungeon dungeon = new Dungeon(squares, dungeonSize);
//		File file = new File("ass2/src/maps/easy1.txt");
		File file = new File(fileName);
		try {
			Scanner s = new Scanner(file);
			int x,y;
			String type = null;
			while(s.hasNextLine()){
				x = s.nextInt();
				System.out.println(x);
				y = s.nextInt();
				type = s.next();
				Entity toAdd = checkEntityType(type);
				squares[x][y].addEntity(toAdd);
			}
			this.dungeon = dungeon;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Gets a random map according to specified difficulty
	 * @param difficulty
	 * @return path
	 */
	private String getDungeonMap(String difficulty) {
		String path = "ass2/src/maps/";
		int random = (int) (Math.random() * 13) % 3;
		String filename = difficulty + Integer.toString(random)+ ".txt";
		path += filename;
		System.out.println("******" + path + "**********");
		return path;
	}

	private int dungeonSize(String difficulty) {
		if(difficulty.equals("easy")){
			return 10;
		} else if (difficulty.equals("medium")){
			return 13;
		} else if (difficulty.equals("hard")) {
			return 16;
		}
		return 0;
	}

	/*
	 * Checks type of entity
	 * @param type
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
		return dungeon.getSquare(x, y);
	}

	public void moveUp() {
		player.moveUp(dungeon.getDungeon());
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void moveDown() {
		player.moveDown(dungeon.getDungeon());
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void moveLeft() {
		player.moveLeft(dungeon.getDungeon());
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void moveRight() {
		player.moveRight(dungeon.getDungeon());
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void attackSword() {
		player.attackSword(dungeon.getDungeon());
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void attackArrow() {
		player.attackArrow(dungeon.getDungeon(), player);
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void dropKey() {
		player.placeKey(dungeon.getDungeon(), player);
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}

	public void placeBomb() {
		player.placeBomb(dungeon.getDungeon(), player);
		this.setChanged();
		this.notifyObservers(dungeon.getDungeon());
	}
}


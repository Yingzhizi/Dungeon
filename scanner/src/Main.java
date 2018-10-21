import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		Scanner s = null;
		try {
			s = new Scanner(new File(args[0]));
			int size = s.nextInt();
			String[][] dungeonMap = new String[size][size];
			for (int row = 0; s.hasNext() && row < size; row++) {
			    String chars = s.next();
			    for (int i = 0; i < size; i++) {
			        dungeonMap[row][i] = chars.substring(i, i + 1);
			    }
			}
			
			//for test
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.print(dungeonMap[i][j]);
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				s.close();
			}
		}
	}
}

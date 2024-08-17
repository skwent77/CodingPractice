import java.util.ArrayList;
import java.util.Scanner;

public class Control {
	int idx = 0;
	Player p;
	
	public void start() {		
		ArrayList<Player> playerArr = new ArrayList<>();
		playerArr.add(new Player(2, 2, "개똥이"));
		playerArr.add(new Player(2, 2, "소똥이"));

		Room[][] map = new Room[5][4];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Room(0, 0, 0, 0);
			}
		}
		map[4][0].eventType = 1;
		map[0][2].eventType =2;
		map[4][2].eventType = 3;
		
		map[0][0].right = 1;
		map[0][2].down = 2;
		map[0][3].up = 2;
		map[0][3].right = 1;

		map[1][0].left = 1;
		map[1][0].right = 1;
		map[1][3].right = 1;
		map[1][3].left = 1;

		map[2][0].left = 1;
		map[2][0].right = 1;
		map[2][0].down = 1;
		map[2][1].up = 1;
		map[2][1].down = 1;
		map[2][2].up = 1;
		map[2][2].down = 1;
		map[2][2].right = 1;
		map[2][3].up = 1;
		map[2][3].left = 1;
		map[2][3].right = 1;

		map[3][0].left = 1;
		map[3][0].right = 1;
		map[3][2].left = 1;
		map[3][3].left = 1;
		map[3][3].right = 1;

		map[4][0].left = 1;
		map[4][3].up = 3;
		map[4][3].left = 1;

		String input = "";
		Scanner scan = new Scanner(System.in);
		while (true) {
			p = playerArr.get(idx);
			
			System.out.println("["+p.name+ "] 차례");
			System.out.println("어디로 이동 하시겠습니까?");
			System.out.println("현재 위치[" + p.posX + "][" + p.posY + "]");
			System.out.println("1. 위  2. 아래  3. 왼쪽  4. 오른쪽");
			input = scan.nextLine();

			if (input.equals("1")) {
				if (p.posY != 0) {
					if (map[p.posX][p.posY].up == 1) {
						p.posY--;
					} else if (map[p.posX][p.posY].up == 2) {
						if(p.firstKey) {
							p.posY--;
						}else {
							System.out.println("첫 번째 키가 필요합니다");
						}
					} else if (map[p.posX][p.posY].up == 3) {
						if(p.secondKey) {
							p.posY--;
						}else {
							System.out.println("두 번째 키가 필요합니다");
						}
					} else {
						System.out.println("문이 없습니다");
					}
				} else {
					System.out.println("위로 갈수 없습니다");
				}
			} else if (input.equals("2") && p.posY != map[0].length) {
				p.posY++;
			} else if (input.equals("3") && p.posX != 0) {
				p.posX--;
			} else if (input.equals("4") && p.posX != map.length) {
				p.posX++;
			}
			
			//이동 후
			if(map[p.posX][p.posY].eventType == 1) {
				//영단어 퀴즈 
				p.firstKey = true;
				System.out.println("첫번째 키 획득");
			}else if(map[p.posX][p.posY].eventType == 2) {
				p.secondKey = true;
				System.out.println("두번째 키 획득");
			}else if(map[p.posX][p.posY].eventType == 3) {
				System.out.println("게임클리어");
				break;
			}
			
			idx++;
			if(idx == playerArr.size()) {
				idx = 0;
			}
		}

	}
}

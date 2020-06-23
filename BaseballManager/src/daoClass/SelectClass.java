package daoClass;

import java.util.Scanner;

import daoInterface.DaoImplement;
import dto.Batter;
import dto.Human;
import dto.Pitcher;
import singleton.SingletonClass;

public class SelectClass implements DaoImplement {
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();

		System.out.print("검색하고 싶은 선수명 = ");
		String name = scan.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
		}
		else {
			Human human = sc.list.get(findIndex);
			
			System.out.println("번호:" + sc.list.get(findIndex).getNumber());
			System.out.println("이름:" + human.getName());
			System.out.println("나이:" + human.getAge());
			System.out.println("신장:" + human.getHeight());
			
			if(human instanceof Pitcher) {
				System.out.println("승리:" + ((Pitcher)human).getWin() );
				System.out.println("패전:" + ((Pitcher)human).getLose() );
				System.out.println("방어율:" + ((Pitcher)human).getDefence() );
			}
			else if(human instanceof Batter){
				System.out.println("타수:" + ((Batter)human).getBatcount() );
				System.out.println("안타수:" + ((Batter)human).getHit() );
				System.out.println("타율:" + ((Batter)human).getHitAvg() );			
			}			
		}
	}
	
	public static int search(String name) {
		SingletonClass sc = SingletonClass.getInstance();
		
		int index = -1;
		for (int i = 0; i < sc.list.size(); i++) {
			Human h = sc.list.get(i);
			if(name.equals(h.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}

}






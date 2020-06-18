package dao;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import dto.Batter;
import dto.Human;
import dto.Pitcher;
import file.FileProc;

// Data Access Object	= model == back end
public class MemberDao {
	
	Scanner sc = new Scanner(System.in);
	
	private ArrayList<Human> list = new ArrayList<Human>();
	
	private int memberNumber;
	
	FileProc fp;
	
	public MemberDao() {
		fp = new FileProc("baseball"); 
		//fp.createFile();
		
		
		this.loadData();
		
		// list에서 제일 마지막 선수의 number 취득
		memberNumber = list.get(list.size() - 1).getNumber();
		
		if(memberNumber >= 2000) {
			memberNumber = memberNumber - 1000;
			memberNumber = memberNumber + 1;
		}
		
	}	
	public void insert() {	
		// 투수/타자 ?
		System.out.print("투수(1)/타자(2) = ");
		int pos = sc.nextInt();
		
		// human
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장 = ");
		double height = sc.nextDouble();
		
		// Human h = null;	
		// 투수	1000 ~ 
		if(pos == 1) {
			// win
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			// lose
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			// defense
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			list.add(new Pitcher(memberNumber, name, age, height, win, lose, defence));
		}
		
		// 타자  2000 ~ 
		else if(pos == 2) {
			
			Batter bat = new Batter();
			
			// 선수 등록 번호
			bat.setNumber(memberNumber + 1000);
			bat.setName(name);
			bat.setAge(age);
			bat.setHeight(height);			
						
			// 타수
			System.out.print("타수 = ");
			int batcount = sc.nextInt();
			bat.setBatcount(batcount);
						
			// 안타수
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			bat.setHit(hit);
			
			// 타율
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			bat.setHitAvg(hitAvg);
			
			list.add(bat);
		}		
		memberNumber++;	
	}	
	
	public void delete() {
		
		System.out.print("삭제하고 싶은 선수명 입력 = ");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("이름을 정확히 입력해 주십시오.");
			return;		// continue
		}
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다. 삭제할 수 없습니다");
			return;
		}
		
		// 삭제
		list.remove(findIndex);
	}	
		
	public void select() {		
		System.out.print("검색하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
		}
		else {
			Human human = list.get(findIndex);
			
			System.out.println("번호:" + list.get(findIndex).getNumber());
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
	
	public void update() {		
		System.out.print("수정하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
			return;
		}
		
		Human human = list.get(findIndex);
		
		if(human instanceof Pitcher) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			Pitcher pit = (Pitcher)human;
			pit.setWin(win);
			pit.setLose(lose);
			pit.setDefence(defence);			
		}
		else if(human instanceof Batter) {
			System.out.print("타수 = ");
			int batcount = sc.nextInt();
			
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			Batter bat = (Batter)human;
			bat.setBatcount(batcount);
			bat.setHit(hit);
			bat.setHitAvg(hitAvg);			
		}	
	}
	
	
	
	public void allprint() {	
		for (int i = 0; i < list.size(); i++) {
			Human human = list.get(i);
			System.out.println(human.toString());
		}
	}	
	
	public int search(String name) {
		int index = -1;

		for (int i = 0; i < list.size(); i++) {
			Human h = list.get(i);
			if(name.equals( h.getName() )) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public void saveData() {
		
		String datas[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Human human = list.get(i);
			datas[i] = human.toString();
		}
			
		fp.saveData(datas);		
	}
	
	public void loadData() {
		String datas[] = fp.loadData();
		
		for (int i = 0; i < datas.length; i++) {
			
			String data[] = datas[i].split("-");
			
			int title = Integer.parseInt(data[0]);
			
			Human human = null;
			if(title < 2000) {		// 투수				
				human = new Pitcher(	Integer.parseInt(data[0]), 
										data[1], 
										Integer.parseInt(data[2]), 
										Double.parseDouble(data[3]), 
										Integer.parseInt(data[4]), 
										Integer.parseInt(data[5]), 
										Double.parseDouble(data[6]) );
			}
			else {
				human = new Batter(	Integer.parseInt(data[0]), 
										data[1], 
										Integer.parseInt(data[2]), 
										Double.parseDouble(data[3]), 
										Integer.parseInt(data[4]), 
										Integer.parseInt(data[5]), 
										Double.parseDouble(data[6]) );
			}				
			list.add(human);			
			
		}	// for end		
	}
	
	// 타율 순위 출력 1 ~ n
	public void batterHitRanking() {
		ArrayList<Human> sortList = positionSelect(2);
		
		/* 확인용
		System.out.println("타자만으로 출력용 -----");
		
		for (int i = 0; i < sortList.size(); i++) {
			System.out.println(sortList.get(i).toString());
		}	
		*/	
		
		// 내림차순 정렬
		Human obj = null;
		for (int i = 0; i < sortList.size() - 1; i++) {
			for (int j = i + 1; j < sortList.size(); j++) {
				Batter b1 = (Batter)sortList.get(i);
				Batter b2 = (Batter)sortList.get(j);
				if(b1.getHitAvg() < b2.getHitAvg()) {
					obj = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j, obj);
				}
			}
		}		
		
		// 결과 출력
		//System.out.println("정렬 후 타자만으로 결과 출력용 -----");

		
		for (Human human : sortList) {
			System.out.println(human.toString());
		}
	}
	
	// 방어율 순위 출력 1 ~ n
	public void pitcherDefenseRanking() {
		// 투수만을 수집한 배열
		ArrayList<Human> sortList = positionSelect(1);
		
		// 올림차순 정렬
		Human obj = null;
		for (int i = 0; i < sortList.size() - 1; i++) {
			for (int j = i + 1; j < sortList.size(); j++) {
				Batter b1 = (Batter)sortList.get(i);
				Batter b2 = (Batter)sortList.get(j);
				if(b1.getHitAvg() > b2.getHitAvg()) {
					obj = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j, obj);
				}
			}
		}
		
		
		// 출력
		for (Human human : sortList) {
			System.out.println(human.toString());
		}
	}
	
	// 타자/투수 만을 산출할 수 있는 함수. 1:투수 2:타자   		
	public ArrayList<Human> positionSelect(int num) {

		ArrayList<Human> reList = new ArrayList<Human>();
		
		for (int i = 0; i < list.size(); i++) {
			Human human = list.get(i);
			if(num == 1) {		// pitcher만 리스트에 담아
				if(human.getNumber() < 2000) {
					reList.add(human);
				}				
			}

			else if(num == 2) {	// batter만 리스트에 담아
				if(human.getNumber() >= 2000) {
					reList.add(human);
				}
			}
		} 
		return reList;
	}
}










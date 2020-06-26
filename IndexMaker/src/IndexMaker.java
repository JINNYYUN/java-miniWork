

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IndexMaker {
	static Item words[] = new Item[100000];  //
	
	static int n = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("무슨 기능을 이용하시겠습니까?");
			String command = sc.next();
			
			//입력으로 하나의 텍스트 파일을 읽는다.
			if(command.equals("read")) {
				System.out.print("읽고 싶은 파일 이름을 입력해주세요: ");
				String fileName = sc.next();
				
				//텍스트 파일에 등장하는 모든 단어들의 목록을 만든다.
				makeIndex(fileName);
			}
			
			//각 단어가 텍스트 파일에 등장하는 횟수를 센다. 단, 단어의 개수는 100,000개 이하라고 가정한다.
			else if(command.equals("find")) {
				System.out.print("검색할 단어를 입력해주세요: ");
				String str = sc.next();
				int index = findWord(str);
				
				if(index > -1){
					System.out.println("The word <<" + words[index].word + ">> appears <<"+ words[index].count+ "times>>");
				}
				else {
					System.out.println("단어가 존재하지 않습니다.");
				}
						
			}
			
			else if(command.equals("savas")) {
				String fileName = sc.next();
				saveAs(fileName);
			}
			
			else if(command.equals("exit")) {
				break;
			}
		}
		sc.close();
	}
	
	static void saveAs(String fileName) {
		//파일 출력 > printWriter를 가장 많이 사용한다.
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			for (int i = 0; i < n; i++) {
				out.println(words[i].word+" "+words[i].count); 		//파일에 출력하라는 
			}
			
			out.close();
		} catch (IOException e) {
			System.out.println("출력 실패");
			return;
		}
	}
	
	static void makeIndex(String fileName) {
		try {
			Scanner fc = new Scanner(new File(fileName));
			while(fc.hasNext()) {	//파일의 끝에 도달했는지 검사 
				String str = fc.next();
				
				String trimmed = trimming(str);
				
				if(trimmed !=null) {
					String t = trimmed.toLowerCase(); //문자를 모두 소문자로 변경하여 대문자와 소문자가 다른 단어로 취급한다.
					
					//배열에 단어가 있는지 없는지 확인 함수 호출 
					addWord(str); //목록에 있으면 추가하고
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
			return; //makeIndex가 아무일도 하지 않도록 리턴해준다.
		} 
	}
	
	/* 단어의 앞뒤에 붙은 특수문자 제거 */
	static String trimming(String str) {
		int i =0; 
		while (i<=str.length() && !Character.isLetter(str.charAt(i))) {
			i++;
		}
		
		int j=str.length()-1;
		while(j>=0 && ! Character.isLetter(str.charAt(j))) {
			j--;
		}
		
		//문자열에 알파벳이 없는 경우를 위해 조건문 
		if(i > j) {
			return null; 	//return되면 함수 종료 
		}
		
		String trimmed = str.substring(i, j+1);
		return trimmed;
	}
	
	static void addWord(String str) {
		
		//단어들의 목록에  단어가 있는지 확인하는 함수 호출 
		int index = findWord(str);  // 단어가 없다면 -1을 호출
		
		
		if(index != -1) {	//단어가 있다면 
			words[index].count ++;
		}
		else {	//단어가 없다 
			System.out.println("단어가 없어 추가되었습니다.");
			/*단어들을 알파벳순으로 정렬하기 위해서는 일단 모든 단어들을 읽어서 인덱스를 만든 후에 한 번에 정렬할 수도 있고,
			 * 항상 정렬된 상태를 유지하도록 삽입하는 방법이 있다. 후자의 방법을 이용해보자*/
			
			/*뒤에서 스캔하면서 동시에 값들을 한칸씩 이동할 수 있기 때문에, 효율면에서 두번째 방법을 이용한다.*/
			int i = n-1;
			while( i >= 0 && words[i].word.compareTo(str)>0) { //compareTo( String ) : 문자열 사전식 순서
				words[i+1] = words[i];
				i--;
			}
			//단어를 추가할 때, 새로운 객체를 생성해야 한다.
			words[i+1] = new Item();
			words[i+1].word = str; //단어를 생성 
			words[i+1].count= 1;	
			n++;
		}
	}
	
	static int findWord(String str) {
		for (int i = 0; i < n; i++) {
			if(words[i].word.equalsIgnoreCase(str)) {	//순차검색 알고리즘 
				return i;
			}
		}
		return -1;
	}
}

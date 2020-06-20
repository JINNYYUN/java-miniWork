package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentClass {
	/* 학생 성적관리 프로그램
	 * 
	   기능목록
		- 학생 이름, 나이, 영어점수, 수학점수를 관리한다.
		- 파일에 있는 데이터를 불러오고, 저장할 수 있다.
		- 학생 데이터를 추가, 삭제, 검색, 수정할 수 있다.
		- 데이터를 모두 출력하여 한 눈에 볼 수 있다.
		- 과목의 총점과 평균을 구할 수 있다.
		- 영어 점수를 성적 순으로 볼 수 있다.
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// data load
		String student[][] = dataLoad();
		
		int choice;
		
		while(true) {
			System.out.println("-----------------메뉴");
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 학생 정보 삭제");
			System.out.println("3. 학생 정보 검색");
			System.out.println("4. 학생 정보 수정");
			System.out.println("5. 학생 정보 모두 출력");
			System.out.println("6. 과목의 총점");
			System.out.println("7. 과목의 평균");
			System.out.println("8. 성적의 정렬");
			
			System.out.println("9. 데이터의 저장");
			
			System.out.println("메뉴 번호를 입력해 주십시오");
			System.out.print(">>> ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:		
					insert(student); 		//1. 학생 정보 추가
					break;
				case 2:		
					delete(student);		//2. 학생 정보 삭제			
					break;
				case 3:
					search(student);		//3. 학생 정보 검색			
					break;
				case 4:
					update(student);		//4. 학생 정보 수정
					break;
				case 5:
					allPrint(student);		//5. 학생 정보 모두 출력
					break;
				case 6:
					chapSum(student);		//6. 과목의 총점
					break;
				case 7:
					chapAvg(student);		//7. 과목의 평균
					break;
				case 8:
					sorting(student);		//8. 영어 점수 성적순으로 정렬 출력
					break;
				case 9:
					dataSave(student);		//9. 입력한 데이터를 파일에 저장
					break;
			}			
		}
	}
		 
	//학생 정보 추가 함수 
	static void insert(String student[][]) {
		Scanner sc = new Scanner(System.in);
				
		int findIndex = -1; //아래 조건문을 다 돌았음에도 findIndex가 변경되지 않을 경우를 대비해 -1로 설정을 해줘야 한다.
		for (int i = 0; i < student.length; i++) {			
			if(student[i][0].equals("")) {	// 0 1 2
				findIndex = i;				
				break;
			}			
		}
		System.out.println("findIndex:" + findIndex);
						
		
		System.out.print("이름:");
		String name = sc.next();
		
		System.out.print("나이:");
		String age = sc.next();
		
		System.out.print("영어:");
		String eng = sc.next();
		
		System.out.print("수학:");
		String math = sc.next();
		
		student[findIndex][0] = name;
		student[findIndex][1] = age;
		student[findIndex][2] = eng;
		student[findIndex][3] = math;
		
		System.out.println("입력완료:" + student[findIndex][0]);
	}
	
	// 정보 삭제 함수(이름으로 정보를 삭제할 수 있다)
	static void delete(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 학생의 이름 = ");
		String name = sc.next();
		
		int findIndex = getSearchIndex(student, name);
		
		if(findIndex != -1) {
			student[findIndex][0] = "";
			student[findIndex][1] = "";
			student[findIndex][2] = "";
			student[findIndex][3] = "";	
			System.out.println("데이터를 삭제하였습니다");
		}else {
			System.out.println("데이터를 찾을 수 없습니다");
		}		
	}
	
	//정보 검색 함수(이름으로 검색할 수 있다)
	static void search(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색할 학생의 이름 = ");
		String name = sc.next();
		
		
		int findIndex = getSearchIndex(student, name);
		
		if(findIndex != -1) {	// 찾았다
			System.out.println("데이터를 찾았습니다");
			System.out.println("이름:" + student[findIndex][0]);
			System.out.println("나이:" + student[findIndex][1]);
			System.out.println("영어:" + student[findIndex][2]);
			System.out.println("수학:" + student[findIndex][3]);
		}else {
			System.out.println("데이터를 찾을 수 없습니다");
		}		
	}
	
	//정보 수정 함수 
	static void update(String student[][]) {	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정할 학생의 이름 = ");
		String name = sc.next();
		
		int findIndex = getSearchIndex(student, name);
		
		if(findIndex != -1) {
			System.out.println("수정 데이터를 입력해 주십시오.");
			System.out.print("영어:");
			String eng = sc.next();
			
			System.out.print("수학:");
			String math = sc.next();
			
			student[findIndex][2] = eng;
			student[findIndex][3] = math;
			
			System.out.println("데이터가 수정되었습니다");
			
		}else {
			System.out.println("데이터를 찾을 수 없습니다");			
		}	
	}
	
	//정보 모두 출력 
	static void allPrint(String student[][]) {
		for (int i = 0; i < student.length; i++) {		
			if(student[i][0] == null || student[i][0].equals("") == false) {
				System.out.print(student[i][0] + " " + student[i][1] + " " + student[i][2] + " " + student[i][3]);
				System.out.println();
			}
		}		
	}
	
	//과목 합계 
	static void chapSum(String student[][]) {
		Scanner sc = new Scanner(System.in);
		// 학생 수 구하기 (null인 학생을 빼기 위해)
		int count = 0;
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) { // student[i][0].equals("") == false
				count++;
			}
		}
		System.out.println("학생 수:" + count);		
		int arrSum[] = new int[count];
		
		// 영어, 수학 선택
		System.out.print("합계를 구할 과목을 선택,  영어(1), 수학(2) = ");
		int num = sc.nextInt();
		
		int sum = 0;
		
		//영어 student[][2]
		//수학 student[][3]
		for (int i = 0; i < student.length; i++) {	// 영어 student[][2] 수학 student[][3] 
			if(!student[i][0].equals("")) {
				sum = sum + Integer.parseInt( student[i][num + 1] );
			}			
		}
		
		if(num == 1)	System.out.print("영어의 합계는 ");
		else			System.out.print("수학의 합계는 ");
			
		System.out.println(sum + "점입니다");		
	}
	
	//과목 평균 함수 
	static void chapAvg(String student[][]) {
		Scanner sc = new Scanner(System.in);
		// 학생 수
		int count = 0;
		
		//학생 수 구하
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) { // student[i][0].equals("") == false
				count++;
			}
		}
		System.out.println("학생 수:" + count);		
		int arrSum[] = new int[count];
		
		// 영어, 수학 선택
		System.out.print("평균을 구할 과목을 선택,  영어(1), 수학(2) = ");
		int num = sc.nextInt();
		
		int sum = 0;
		for (int i = 0; i < student.length; i++) {	// 영어 student[][2] 수학 student[][3] 
			if(!student[i][0].equals("")) {
				sum = sum + Integer.parseInt( student[i][num + 1] );
			}			
		}
		
		double avg = (double)sum / arrSum.length;
		
		if(num == 1)	System.out.print("영어의 평균은 ");
		else			System.out.print("수학의 평균은 ");
			
		System.out.println(avg + "점입니다");		
	}
	
	//영어 점수 성적순으로 정렬 
	static void sorting(String student[][]) {		
		String sortDatas[][] = new String[student.length][student[0].length];
		
		System.out.println(student[0].length);
		
		for (int i = 0; i < student.length; i++) {
			for (int j = 0; j < student[0].length; j++) {
				sortDatas[i][j] = student[i][j]; //기존 배열이 변경되지 않도록 값을 옮겨줌. 이 과정이 없으면 참조되어 기존 배열의 순서도 변경 
			}
		}
		
		// student[][2];
		String temp[] = null;
		for (int i = 0; i < sortDatas.length - 1; i++) {
			for (int j = i + 1; j < sortDatas.length; j++) {
				if(!(student[j][0].equals(""))) {
					int num1 = Integer.parseInt(sortDatas[i][2]); 
					int num2 = Integer.parseInt(sortDatas[j][2]); 
					if(num1 > num2) {
						temp = sortDatas[i];
						sortDatas[i] = sortDatas[j];
						sortDatas[j] = temp;
					}
				}
			}			
		}
		
		for (int i = 0; i < sortDatas.length; i++) {
			if(!(student[i][0].equals(""))) {
				System.out.println(sortDatas[i][0] + " " + sortDatas[i][1] + " " + sortDatas[i][2] + " " + sortDatas[i][3]);
			}
		}
	}
	
	//데이터를 파일에 저장 
	static void dataSave(String student[][]) {
		/*
		 * 	아래와 같이 저장해보자!
			이름-나이-영어-수학
			이름-나이-영어-수학
			이름-나이-영어-수학
		*/		
		int count = 0;
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) { // student[i][0].equals("") == false
				count++;
			}
		}
		
		String saveData[] = new String[count];
		for (int i = 0; i < saveData.length; i++) {
			saveData[i] = student[i][0] + "-" 
							+ student[i][1] + "-"
							 + student[i][2] + "-"
							  + student[i][3];							
		}
		
		for (int i = 0; i < saveData.length; i++) {
			System.out.println(saveData[i]);
		}		
		
		File file = new File("student.txt");
		
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < saveData.length; i++) {
				pw.println(saveData[i]);
			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}						
	}
	
	//파일 가져오기  
	static String[][] dataLoad() {
		
		String str[] = null;
		// 파일
		File file = new File("student.txt");
		
		try {
			// 파일 읽기
			FileReader fr = new FileReader(file);
			
			// 데이터 갯수
			int count = 0;
			String s;
			BufferedReader br = new BufferedReader(fr);
			while( (s = br.readLine()) != null ) {
				count++;
			}
			br.close();
			
			// 할당
			str = new String[count];
			
			// 데이터를 저장
			int i = 0;
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while( (s = br.readLine()) != null ) {
				str[i] = s;
				i++;
			}			
			
		} catch (Exception e) {
			System.out.println("파일을 읽지 못했습니다");
		}
		
		String student[][] = new String[20][4];
		
		for (int i = 0; i < student.length; i++) {
			for (int j = 0; j < student[i].length; j++) {
				student[i][j] = ""; 
			}
		}
		
		for (int i = 0; i < str.length; i++) {
			
			String s = str[i];	// 홍길동-24-90-100
			String split[] = s.split("-");		
			
			student[i][0] = split[0];
			student[i][1] = split[1];
			student[i][2] = split[2];
			student[i][3] = split[3];
		}
		
		return student;		
	}
	
	//유틸리티 함수 
	static int getSearchIndex(String student[][], String name) {
		int findIndex = -1;
		for (int i = 0; i < student.length; i++) {
			String n = student[i][0];			
			if(n.equals(name)) {
				findIndex = i;			
				break;
			}
		}
		return findIndex;
	}
}

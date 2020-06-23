package main;

import java.util.Scanner;

import daoClass.AllPrint;
import daoClass.DeleteClass;
import daoClass.FileSaveClass;
import daoClass.InsertClass;
import daoClass.SelectClass;
import daoClass.UpdateClass;
import daoInterface.DaoImplement;
import fileload.FileLoadClass;

public class mainClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		(new FileLoadClass()).process();
		
		while(true) {			
			DaoImplement dao = null;			
			
			System.out.println("1. 선수 등록 ");
			System.out.println("2. 선수 삭제 ");
			System.out.println("3. 선수 검색 ");
			System.out.println("4. 선수 수정 ");
			System.out.println("5. 선수 모두 출력 ");			
			System.out.println("6. 데이터 저장 ");
			System.out.println("0. 프로그램 종료 ");
			
			System.out.print("메뉴 번호 입력 >>> ");
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1:
					dao = new InsertClass();
					break;
				case 2:
					dao = new DeleteClass();
					break;
				case 3:
					dao = new SelectClass();
					break;
				case 4:
					dao = new UpdateClass();
					break;
				case 5:
					dao = new AllPrint();
					break;
				case 6:
					dao = new FileSaveClass();
					break;
				case 0:
					System.out.println("프로그램을 종료합니다");
					System.exit(0);					
					break;
			}
					
			dao.process();
						
		}

	}

}

package daoClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import daoInterface.DaoImplement;
import dto.Human;
import singleton.SingletonClass;

public class FileSaveClass implements DaoImplement {

	File file = new File("baseball.txt");
	
	public FileSaveClass() {
	}
	
	@Override
	public void process() {		
		SingletonClass sc = SingletonClass.getInstance();
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < sc.list.size(); i++) {
				Human h = sc.list.get(i);
				pw.println(h.toString());
			}			
			pw.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		System.out.println("파일에 저장되었습니다");
	}

}

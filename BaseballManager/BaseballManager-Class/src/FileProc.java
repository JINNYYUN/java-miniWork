import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileProc {

	private File file;
	
	public FileProc(String filename) {
		file = new File(filename + ".txt"); //파일경로 설정
	}
	
	public void createFile() {
		try {
			if(file.createNewFile()) {
				System.out.println("파일 생성 성공!");
			}else {
				System.out.println("파일이 이미 존재합니");
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public String[] loadData() {
		
		String datas[] = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		
			// data의 갯수를 조사		list를 사용하면, 이 처리는 필요없음 
			int count = 0;
			String str = "";
			
			while( (str = br.readLine()) != null ) {
				count++;				
			} 
			br.close();
			
			// datas를 할당
			datas = new String[count];
			System.out.println("datas.length = " + datas.length);
			
			// 배열 저장
			int w = 0;
			br = new BufferedReader(new FileReader(file));
			while( (str = br.readLine()) != null ) {
				datas[w] = str;
				w++;
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return datas;
	}
	
	public void saveData(String[] datas) {
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < datas.length; i++) {
				pw.println(datas[i]);
			}			
			pw.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		System.out.println("파일에 저장되었습니다");
	}
}

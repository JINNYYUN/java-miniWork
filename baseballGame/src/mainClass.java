package baseballFunc;

import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		
		int r_num[] = new int[3];
		int u_num[] = new int[3];	// 10개 (1 ~ 10) - 00000 00000 -> 01000 00000
		boolean clear = false;
		
		random(r_num); 
	
		/////////////////// loop 10
		int w = 0;
		while(w < 10) {

			userInput(u_num);
			
			boolean b = finding(u_num, r_num);
			if(b == true) {
				clear = true;
				break;
			}
			
			w++;
		}	
		
		resultPrint(clear);
		
	}
	
	static void random(int r_num[]) {		
		boolean swit[] = new boolean[10];
		
		for (int i = 0; i < swit.length; i++) {
			swit[i] = false;	// 00000 00000
		}		
		
		int r, w;		
		w = 0; 				
		while(w < 3) {
			r = (int)(Math.random() * 10);	// 0 ~ 9
			if(swit[r] == false) {
				swit[r] = true;		// 00100 00000
				r_num[w] = r + 1;			// 1 ~ 10
				w++;
			}
		}
		
		for (int i = 0; i < r_num.length; i++) {
			System.out.println(i + " : " + r_num[i]);
		}		
	}
	
	static void userInput(int u_num[]) {
		Scanner sc = new Scanner(System.in);
		
		boolean check;
		int w1;
		while(true) {
			check = false;
			w1 = 0;
			while(w1 < 3) {
				System.out.print((w1 + 1) + "번째 수 = ");
				u_num[w1] = sc.nextInt();
				w1++;
			}
			
			// 같은 숫자가 있는지 첵크
			out:for (int i = 0; i < u_num.length; i++) {
				for (int j = 0; j < u_num.length; j++) {
					if(u_num[i] == u_num[j] && i != j) {
						check = true;	// 입력한 같은 숫자가 있음 
						break out; 
					}
				}
			}
			
			if(check == false) {
				break;
			}				
			System.out.println("입력한 숫자 중에 중복되는 숫자가 있습니다. 다시 입력해 주십시오");
		}
	}
	
	static boolean finding(int u_num[], int r_num[]) {
		int strike, ball;		
		strike = ball = 0;
		
		// ball
		for (int i = 0; i < u_num.length; i++) {
			for (int j = 0; j < r_num.length; j++) {
				if(u_num[i] == r_num[j] && i != j) {
					ball++;
				}					
			}
		}
		// strike
		for (int i = 0; i < u_num.length; i++) {
			if(u_num[i] == r_num[i]) {
				strike++;
			}
		}
		
		if(strike > 2) {
			return true;								
		}
		
		System.out.println(strike + "스트라이크, " + ball + "볼 입니다");
		
		return false;
	}
	
	static void resultPrint(boolean clear) {
		if(clear) {
			System.out.println("Game Clear!!");
		}else {
			System.out.println("Game Over~ ");
		}	
	}	
}








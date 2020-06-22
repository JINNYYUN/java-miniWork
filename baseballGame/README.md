# 게임규칙

### baseball게임 규칙

- 1부터 10사이의 숫자 중 랜던 숫자 3개가 있다(서로 다른 숫자 3개)
- 플레이어는 숫자 세개를 입력한다.
- 숫자와 위치가 모두 맞으면 스트라이크
- 숫자는 동일하지만 위치가 틀렸을 때는 볼
- 기회는 10번이며, 기회가 끝났을 때는 새로운 게임이 다시 시작된다.

<br/>

### 중복없이 random을 돌리기 위한 함수
```java
int r_num[] = new int[3];
boolean swit[] = new boolean[10]; //중복을 체크하기 위한 배열

/*swit init*/
for(int i=0; i<swit.length; i++) {
		swit[i] = false;   //00000 00000
}
		
/*중복없는 random을 돌리기 위한 함수, suffle(셔플)*/
int w=0; 
while(w<3) {
	r=(int)(Math.random()*10); //0~51
	if(swit[r]==false) {
        
        /*랜덤숫자가 true로 바뀌면서, 똑같은 숫자가 random값으로 나오지 않는다*/
		swit[r]=true;
		
		r_num[w] =r+1;
		w++;
	}
}

//디버그 해보며 확인할 것!
```
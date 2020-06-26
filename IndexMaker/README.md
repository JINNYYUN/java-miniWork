# IndexMaker


### 기능
- 입력으로 하나의 텍스트 파일을 읽는다.
- 텍스트 파일에 등장하는 모든 단어들의 목록을 만들고, 각 단어가 텍스트 파일에 등장하는 횟수를 센다. 단어의 개수는 100,000개 이하라고 가정한다.
- 사용자가 요청하면 단어 목록을 하나의 파일로 저장한다.
- 사용자가 단어를 검색하면 그 단어가 텍스트 파일에 몇 번 등장하는지 출력한다.
- 단어와 그 단어의 등장 빈도는 하나의 객체로 관리된다.

</br>

**텍스트 파일에 있는 단어의 배열을 만드는 함수**

```java
static void makeIndex(String fileName) {
		try {
			Scanner fc = new Scanner(new File(fileName));
			while(fc.hasNext()) {	//파일의 끝에 도달했는지 검사 
				String str = fc.next();
				
				String trimmed = trimming(str);
				
				if(trimmed !=null) {
					String t = trimmed.toLowerCase(); //문자를 모두 소문자로 변경하여 대문자와 소문자를 같은 단어로 취급한다.
					
					//배열에 단어가 있는지 없는지 확인 함수 호출 
					addWord(str);
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
			return; //makeIndex가 아무일도 하지 않도록 리턴해준다.
		} 
	}
```

</br>


**정렬된 상태를 유지하면서 데이터를 삽입하는 방법**

단어들을 알파벳순으로 정렬하기 위해서는 일단 모든 단어들을 읽어서 인덱스를 만든 후에 한 번에 정렬할 수도 있고, 항상 **정렬된 상태를 유지하도록 삽입하는 방법**이 있다. 후자의 경우 뒤에서부터 스캔하면서 동시에 큰 값들을 이동할 수 있기 때문에 첫번째 방법보다 효율이 좋다.

```java
static void addWord(String str) {
		
		/*단어들의 목록에  단어가 있는지 확인하는 함수 호출 */
		int index = findWord(str);  // 단어가 없다면 -1을 호출
		
		
		if(index != -1) {	//단어가 있다면 
			words[index].count ++;
		}
		else {	//단어가 없다면
			System.out.println("단어가 없어 추가되었습니다.");

			/*정렬된 위치에 단어를 추가한다.*/
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
```


</br>


**단어의 앞뒤에 붙은 특수문자 제거** 

isLetter() 함수는 명시된 char값이 문자인지 여부를 판단하여 true/false를 리턴한다.

```java

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
```
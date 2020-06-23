package daoClass;

import daoInterface.DaoImplement;
import dto.Human;
import singleton.SingletonClass;

public class AllPrint implements DaoImplement {

	public AllPrint() {
	}
	
	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();
		
		for (int i = 0; i < sc.list.size(); i++) {
			Human human = sc.list.get(i);
			System.out.println(human.toString());
		}

	}

}









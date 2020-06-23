package singleton;

import java.util.ArrayList;
import java.util.List;

import dto.Human;

public class SingletonClass {

	private static SingletonClass sc = null;
	
	public List<Human> list = null;
	
	private SingletonClass() {
		list = new ArrayList<Human>();
	}
	
	public static SingletonClass getInstance() {
		if(sc == null) {
			sc = new SingletonClass();
		}
		return sc;
	}
}

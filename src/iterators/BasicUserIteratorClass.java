package iterators;

import user.User;
import user.UserBasicClass;
/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 * @param <E> tipo de objetos a iterar  
 *
 */
public class BasicUserIteratorClass implements Iterator<User> {
	
	private User[] vector;
	private int counter;
	private int currentPosition;
	
	public BasicUserIteratorClass(User[] vector, int counter){
		this.vector = vector;
		this.counter = counter;
		this.currentPosition = -1;
	}
	
	public boolean hasNext() {
		return  (currentPosition < counter);
	}

	public void initializeIterator() {
		currentPosition = 0;
		while ((currentPosition<counter)&&!(vector[currentPosition] instanceof UserBasicClass)){
			currentPosition++;
		}
	}

	public User next() {
		User temp = vector[currentPosition++];
		while ((currentPosition<counter)&&!(vector[currentPosition] instanceof UserBasicClass)){
			currentPosition++;
		}
		return temp;
	}

}

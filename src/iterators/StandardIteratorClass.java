package iterators;
/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 * @param <E> tipo de objetos a iterar 
 *
 */
public class StandardIteratorClass<E> implements Iterator<E> {
	private E[] vector;
	private int counter;
	private int currentPosition;
	
	public StandardIteratorClass(E[] vector, int counter){
		this.vector = vector;
		this.counter = counter;
		this.currentPosition = -1;
	}
	
	public void initializeIterator() {
		currentPosition=0;
	}

	public boolean hasNext() {
		
		return currentPosition < counter;
	}

	
	public E next() {
		if(hasNext()){
			return  vector[currentPosition++];
		} else {
			return null;
		}

	}
	
}
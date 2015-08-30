package iterators;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 * @param <E> -tipo do iterador
 */
public interface Iterator<E> {

	/** Inicia o iterador
	 */
	void initializeIterator();
	
	/**
	* Verifica se existe mais algum elemento a visitar
 	* @return true, se houver mais elementos a visitar, false, caso contr�rio 
	*/
	boolean hasNext();

	/** 
	 * @return @param <E> devolve o pr�ximo elemento da itera��o
	 */
	E next();
}

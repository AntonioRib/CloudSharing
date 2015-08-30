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
 	* @return true, se houver mais elementos a visitar, false, caso contrário 
	*/
	boolean hasNext();

	/** 
	 * @return @param <E> devolve o próximo elemento da iteração
	 */
	E next();
}

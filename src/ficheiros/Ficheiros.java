package ficheiros;
import iterators.Iterator;
import user.User;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public interface Ficheiros {
	
	/** Devolve o nome do ficheiro.
	 * @return fileName - nome do ficheiro
	 */
	String getFileName();
	
	/** Devolve o tamanho do ficheiro
	 * @return size - Tamanho do ficheiro
	 */
	int getSize();
	
	/** Devolve o dono do ficheiro
	 * @pre <= 5120MB
	 * @return owner - Dono do ficheiro
	 */
	User getOwner();
	
	/** Devolve o usuario que fez a ultima actualiza�ao
	 * @return lastUpdater - Ultimo utilizador a modificar o ficheiro
	 */
	User getLastUpdate();
	
	/** Verifica se a partilha j� existe no ficheiro.
	 * @param name - nome do user que queremos verificar.
	 * @return - True se j� � partilhado com esse false caso contr�rio.
	 */
	boolean hasShare(String name);
	
	/**	Partilha o ficheiro com outros utilizadores.
	 * @param reciever - Utilizador que vai receber a partilha do ficheiro
	 */
	void addShare(User reciever);
	
	/** Modifica o ficheiro.
	 * @param modifier User que modifica o ficheiro
	 */
	void updateFile(User modifier);
	
	/** Com quanta gente o ficheiro � partilhado (exclui o propriet�rio)
	 * @return numero de partilhas do ficheiro.
	 */
	int getNumberOfShares();
	
	/**M�todo que permite verificar a igualdade entre um ficheiro e qualquer outro objecto.
	 * @param obj - Objecto que queremos verificar se � ou n�o igual.
	 * @return - true se forem iguais, false caso contr�rio
	 */
	boolean equals(Object obj);
	
	/** Iterador de partilhas do ficheiro
	 * @return iterador - devolve um objeto do tipo iterator que itera sobre os users que podem aceder a este ficheiro que n�o s�o o propritario.
	 */
	Iterator<User> sharesIterator();
	
	
	/** Retorna o objecto user como uma string formatada correctamente.
	 * @return - Caracteristicas do ficheiro formatados numa String.
	 */
	String toString();

}

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
	
	/** Devolve o usuario que fez a ultima actualizaçao
	 * @return lastUpdater - Ultimo utilizador a modificar o ficheiro
	 */
	User getLastUpdate();
	
	/** Verifica se a partilha já existe no ficheiro.
	 * @param name - nome do user que queremos verificar.
	 * @return - True se já é partilhado com esse false caso contrário.
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
	
	/** Com quanta gente o ficheiro é partilhado (exclui o proprietário)
	 * @return numero de partilhas do ficheiro.
	 */
	int getNumberOfShares();
	
	/**Método que permite verificar a igualdade entre um ficheiro e qualquer outro objecto.
	 * @param obj - Objecto que queremos verificar se é ou não igual.
	 * @return - true se forem iguais, false caso contrário
	 */
	boolean equals(Object obj);
	
	/** Iterador de partilhas do ficheiro
	 * @return iterador - devolve um objeto do tipo iterator que itera sobre os users que podem aceder a este ficheiro que não são o propritario.
	 */
	Iterator<User> sharesIterator();
	
	
	/** Retorna o objecto user como uma string formatada correctamente.
	 * @return - Caracteristicas do ficheiro formatados numa String.
	 */
	String toString();

}

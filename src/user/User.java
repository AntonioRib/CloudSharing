package user;

import iterators.Iterator;
import ficheiros.Ficheiros;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */

public interface User {
	
	/**Verifica se existe o ficheiro com o determinado nome
	 * @param fileName - nome do ficheiro
	 * @param ownerName - nome do dono do ficheiro
	 * @return - true se o ficheiro existe, false em caso contrario
	 */
	boolean hasFile(String fileName, String ownerName);
	/**	Devolve o o mail da conta
	 * @return mail- nome do email da conta
	 */
	String getMail();
	
	/**Adiciona um ficheiro a conta do utilizador
	 * @param fileName - nome do ficheiro a adicionar
	 * @param fileSize - tamanho do ficheiro a ser adicionado
	 */
	void addFile(String fileName, int fileSize);
	
	/**
	 * Recebe uma partilha vinda de um utilizador premium.
	 * @param sharer - User que vai fazer a partilha.
	 * @param file - Ficheiro que vai ser partilhado.
	 */
	void recieveShare(User sharer, Ficheiros file);
	
	/** Devolve o tamanho total da conta 
	 * @return accountSize - Tamanho total
	 */ 
	int getAccountSize();
	
	/**
	 * Devolve o espaço da conta já usado.
	 * @return usedSpace;
	 */
	int getUsedSpace();
	
	/** Devolve o numero de ficheiros partilhados com a conta.
	 * @return - int numFilesRecieved
	 */
	public int getNumFilesRecieved();
	
	/** Devolve o numero de ficheiros criados pela conta 
	 * @return int- numFilesCreadted
	 */
	public int getNumFilesCreated();

	/**
	 * Devolve o espaço da conta ainda restante;
	 * @return remainingSpace;
	 */
	int getRemainingSpace();

	/** Iterador de ficheiros
	 * @return iterador- devolve um objeto do tipo iterator
	 */
	Iterator<Ficheiros> fileIterator();
	
	/** Diz quantos ficheiros o utilizador pode aceder.
	 * @return numero de ficheiros
	 */
	int getNumFiles();
	
	/**
	 * @param tamanho - Tamanho do ficheiro que queremos testar se pode receber.
	 * @return - True se poder; false se não.
	 */
	boolean canRecieveShare(int tamanho);
	
	/**Método que permite verificar a igualdade entre um utilizador e qualquer outro objecto.
	 * @param obj - Objecto que queremos verificar se é ou não igual.
	 * @return - true se forem iguais, false caso contrário
	 */
	boolean equals(Object obj);
	
	/** Retorna o objecto user como uma string formatada correctamente.
	 * @return - Caracteristicas do user formatados numa String.
	 */
	String toString();

}

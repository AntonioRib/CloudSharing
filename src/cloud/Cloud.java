package cloud;

import iterators.Iterator;
import ficheiros.Ficheiros;
import user.User;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public interface Cloud {
	
	/**Verifica ha um utilizador com esse nome.
	 * @param name -nome do utilizador
	 * @return - true se existir utilizador, false em caso contrario
	 */
	boolean hasUser(String name);

	/** Adiciona uma nova conta a Cloud
	 * @param mailName - nome da conta (email)
	 * @param type - tipo de conta (basic ou premium)
	 */
	void addUser(String mailName , String type);
	
	/** Devolve quandos users existem na cloud.
	 * @return - numberOfUsers.
	 */
	int numberOfUsers();
	
	/** Adiciona um ficheiro novo a Cloud
	 * @param userName - nome do utilizador
	 * @param fileName - nome do ficheiro
	 * @param fileSize - tamanho do ficheiro
	 */
	void addFile(String userName,String fileName, int fileSize);

	/** Actualiza um ficheiro.
	 * @param ownerName Nome da conta do proprietário do ficheiro a editar.
	 * @param updaterName Nome da conta do utilizador que vai fazer o update.
	 * @param fileName Nome do ficheiro que vai ser feito o update.
	 */
	void updateFile(String ownerName, String updaterName, String fileName);
	
	/** Adiciona uma nova partilha 
	 * @param sharer - utilizador que fornece o ficheiro
	 * @param receiver - utilizador que recebe o ficheiro
	 * @param fileName - nome do ficheiro
	 */
	void shareFile(String sharer, String receiver,String fileName );

	/** Devolve a ultima actualizacao feita no determinado ficheiro
	 * @param userName -  nome do proprietario do ficheiro
	 * @param fileName - nome do ficheiro
	 * @return user- utilizador que efetuou a ultima actualizacao
	 */
	User lastUpdate(String userName, String fileName);
	
	/** Devolve o user com menos espaço
	 * @return User - User com menos espaço disponivel.
	 */
	User UserLessSpace();
	
	/**
	 * @param userName - User de que se quer os ficheiros.
	 * @return - Iterator<Ficheiros> - Iterador com os ficheidos de um user.
	 */
	Iterator<Ficheiros> userFilesList(String userName);
	
	/**
	 * @return Iterator<User> - Iterador com os users da cloud.
	 */
	Iterator<User> usersIterator();

	/**
	 * @param type - Tipo que se quer iterar.
	 * @return Iterator<User> - Iterador com os users do determinado tipo (Premium ou Basic) 
	 */
	Iterator<User> usersTypeIterator(String type);
}

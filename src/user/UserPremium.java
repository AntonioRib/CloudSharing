package user;

import ficheiros.Ficheiros;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public interface UserPremium extends User {
	/** Adiciona uma nova partilha entre utilizadores
	 * @param reciever - utilizador que ira receber o ficheiro
	 * @param file - ficheiro a partilhar
	 */
	void addShare(User reciever, Ficheiros file) ;
}

package user;

import ficheiros.Ficheiros;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public class UserPremiumClass extends AbstractUser implements UserPremium {

	public UserPremiumClass(String mail){
		super(mail);
		this.accountSize=5120;
		this.size = 0;
	}
	
	public void addShare(User reciever, Ficheiros file) {
		reciever.recieveShare(this, file);
	}
	
	public String toString(){
		return this.getMail() + " (Premium)";
	}

	public void recieveShare(User sharer, Ficheiros file) {
		if(!hasFile(file.getFileName(), file.getOwner().getMail())){
			if(counter==files.length){
				super.resize();
			}
			files[counter++]= file;
			numFilesRecieved++;
			file.addShare(this);
		}
	}

	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		
		if(obj == null){
			return false;
		}
		
		if (!(obj instanceof UserPremiumClass)){
			return false;
		}
		
		UserPremiumClass temp = (UserPremiumClass) obj;
		if(mail == null){
			if (temp.getMail() != null){
				return false;
			}
		} else if (!mail.equals(temp.getMail())){
			return false;
		}
		if (accountSize != temp.getAccountSize()){
			return false;
		}
		return true;
	}
	
	public boolean canRecieveShare(int tamanho){
		return true;	
	}
}

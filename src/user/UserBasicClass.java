package user;

import ficheiros.Ficheiros;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public class UserBasicClass extends AbstractUser implements User {
	
	public UserBasicClass(String mail){
		super(mail);
		this.accountSize=2048;
		this.size = 0;
	}
	
	public String toString(){
		return this.getMail() + " (Basic)";
	}

	public void recieveShare(User sharer, Ficheiros file) {
		if(this.canRecieveShare(file.getSize())){
			if(!hasFile(file.getFileName(), file.getOwner().getMail())){
				if(counter==files.length){
					super.resize();
				}
				files[counter++]= file;
				numFilesRecieved++;
				file.addShare(this);
				size+=(file.getSize()/2);
			}
		}
	}

	public boolean equals(Object obj) {
			if(this == obj){
				return true;
			}
			
			if(obj == null){
				return false;
			}
			
			if (!(obj instanceof UserBasicClass)){
				return false;
			}
			
			UserBasicClass temp = (UserBasicClass) obj;
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
		return (this.getRemainingSpace()> (tamanho/2));
		
	}
}

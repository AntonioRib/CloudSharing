package cloud;

import ficheiros.Ficheiros;
import iterators.BasicUserIteratorClass;
import iterators.Iterator;
import iterators.PremiumUserIteratorClass;
import user.User;
import user.UserBasicClass;
import user.UserPremium;
import user.UserPremiumClass;
import iterators.StandardIteratorClass;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public class CloudClass implements Cloud {


	private User[] users;
	private int counter;
	
	
	public CloudClass(){
		this.users = new User[5];
		this.counter = 0;
				
	}
	
	public boolean hasUser(String name){
		return searchIndex(name)>=0;
}

	private void resize(){

	User[] tmp = new User[2*users.length];
	for(int i=0; i<counter; i++){
		tmp[i]= users[i];
	}
	users =tmp;
}

	private int searchIndex(String name){
		for(int i=0; i<counter;i++){
			if(users[i].getMail().equals(name)){
				return i;				
			}
		}
			return -1;
	}
	
	public int numberOfUsers(){
		return counter;
	}
	
	
	public void addUser(String mailName, String type) {		
		if(counter==users.length){
			resize();
		}
		if(!hasUser(mailName)){
			if(type.equalsIgnoreCase("Basic")){
			users[counter++]=new UserBasicClass(mailName);
			} else if(type.equalsIgnoreCase("Premium")){
			users[counter++]=new UserPremiumClass(mailName);
			}
		}	
	}
		

	public void addFile(String userName, String fileName, int fileSize) {		
		int tmp = searchIndex(userName);
		if(tmp>=0){
			users[tmp].addFile(fileName, fileSize);
		}
	}

	public void shareFile(String sharer, String receiver, String fileName) {	
		int tmpShar = searchIndex(sharer);
		int tmpRec = searchIndex(receiver);
		Ficheiros fTemp;
		if((hasUser(sharer))&&(hasUser(receiver))){
			if(users[tmpShar] instanceof UserPremium){
				UserPremiumClass ownerTemp = (UserPremiumClass) users[tmpShar];
				Iterator<Ficheiros> it = ownerTemp .fileIterator();
				it.initializeIterator();
				while(it.hasNext()){
					fTemp = it.next();
					if (fTemp.getFileName().equals(fileName)){
						fTemp.addShare(users[tmpRec]);
						ownerTemp.addShare(users[tmpRec], fTemp);
						users[tmpShar] = ownerTemp ;
					}	
				}				
			}
		}
	}
	
	public void updateFile(String ownerName, String updaterName, String fileName) {	
		int tmpOwn = searchIndex(ownerName);
		int tmpUp = searchIndex(updaterName);
		Ficheiros fTemp;
		User uTemp;
		if((hasUser(ownerName))&&(hasUser(updaterName))){
				Iterator<Ficheiros> itFile = users[tmpOwn].fileIterator();
				itFile.initializeIterator();
				while(itFile.hasNext()){
					fTemp = itFile.next();
					if (fTemp.getFileName().equals(fileName)){
						Iterator<User> itShares = fTemp.sharesIterator();
						itShares.initializeIterator();
						while(itShares.hasNext()){
							uTemp = itShares.next();
							if (uTemp.equals(users[tmpUp])){
								fTemp.updateFile(uTemp);
							}
						}
						if (users[tmpOwn].equals(users[tmpUp])){
								fTemp.updateFile(users[tmpOwn]);
						}
					}	
				}				
		}
	}

	public User lastUpdate(String userName, String fileName) {
		int tmpOwn = searchIndex(userName);
		Ficheiros fTemp;
		User uTemp = null;
		if(hasUser(userName)){
				Iterator<Ficheiros> itFile = users[tmpOwn].fileIterator();
				itFile.initializeIterator();
				while(itFile.hasNext()){
					fTemp = itFile.next();
					if (fTemp.getFileName().equals(fileName)){
						uTemp = fTemp.getLastUpdate();
					}	
				}				
		}
		return uTemp;
	}

	public Iterator<User> usersIterator() {
		return new StandardIteratorClass<User>(users, counter);
	}

	public User UserLessSpace() {
		int i=0;
		User temp = users[i++];
		for (i=i; i<counter; i++){
			if(users[i].getRemainingSpace()<temp.getRemainingSpace()){
				temp = users[i];
			}
		}
		return temp;
	}

	public Iterator<Ficheiros> userFilesList(String userName) {
		int tmp= searchIndex(userName);
		if(hasUser(userName)){
			return users[tmp].fileIterator();
		} else {
			return null;
		}
	}

	public Iterator<User> usersTypeIterator(String type) {
		if(type.equalsIgnoreCase("Basic")){
			return new BasicUserIteratorClass(users , counter);
		} else if(type.equalsIgnoreCase("Premium")){
			return new PremiumUserIteratorClass(users, counter);
		} else {
			return null;
		}
	}
}

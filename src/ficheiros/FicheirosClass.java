package ficheiros;
import iterators.Iterator;
import iterators.StandardIteratorClass;
import user.User;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public class FicheirosClass implements Ficheiros {

	
	private String name;
	private int size; // in mb
	private User owner;
	private User lastUpdate;
	private User [] userSharing;
	private int counter;
	
	public FicheirosClass(String name, int size, User owner){
		this.name=name;
		this.size=size;
		this.counter=0;
		this.owner = owner;
		this.lastUpdate = owner;
		userSharing = new User[10];
	}
	
	public String getFileName() {
		return name;
	}

	
	public int getSize() {
		return size;
	}

	
	public User getOwner() {
		return owner;
	}

	
	public User getLastUpdate() {
		return lastUpdate;
	}

	
	public void addShare(User reciever) {	
		if(counter==userSharing.length){
			resize();
		}
		if(!hasShare(reciever.getMail())&&(reciever.canRecieveShare(this.getSize()))){
			userSharing[counter++]=reciever;
		}
	
	}
	
	public boolean hasShare(String name){
			return searchIndex(name)>=0;
	}
	
	private void resize(){
		User[] tmp = new User[2*userSharing.length];
		for(int i=0; i<counter; i++){
			tmp[i]= userSharing[i];
		}
		userSharing=tmp;
	}
	
	private int searchIndex(String name){
		for(int i =0; i<counter;i++){
			if(userSharing[i].getMail().equals(name)){
				return i;				
			}
		}
		return -1;
	}
	
	public Iterator<User> sharesIterator() {
		return new StandardIteratorClass<User>(userSharing, counter);
	}

	public int getNumberOfShares() {
		return counter;
	}

	public void updateFile(User modifier) {		
	
		lastUpdate=modifier;
	
	}
	
	public boolean equals (Object obj){
		if (this == obj){
			return true;
		} 
		
		if (obj == null){
			return false;
		}
		
		if (!(obj instanceof Ficheiros)){
			return false;
		}
		Ficheiros temp = (Ficheiros) obj;
		if (name == null){
			if (temp.getFileName() !=null){
				return false;
			} 
		} else if (name.equals(temp.getFileName())){
				return false;
		}
		
		if (size != temp.getSize()){
			return false;
		}
		
		if (owner == null){
			if (temp.getOwner()!= null){
				return false;
			} 
		} else if (owner != temp.getOwner()){
			return false;
		}
		
		if (lastUpdate == null){
			if (temp.getLastUpdate()!= null){
				return false;
			} 
		} else if (lastUpdate != temp.getLastUpdate()){
			return false;
		}
		
		return true;
	}
	
	public String toString(){
		return this.getFileName() + " (" + this.getSize() + " MB)";
	}

}

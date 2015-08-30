package user;

import iterators.Iterator;
import iterators.StandardIteratorClass;
import ficheiros.Ficheiros;
import ficheiros.FicheirosClass;

/**
 * @author Antonio Ribeiro - 39349
 * @author Jhordy Castro - 39592
 *
 */
public abstract class AbstractUser implements User{

	protected String mail;
	protected int accountSize;
	protected Ficheiros [] files;
	protected int counter;
	protected int numFilesRecieved;
	protected int numFilesCreated;
	protected int size;
	
	public AbstractUser(String mail){
		this.mail=mail;
		files= new Ficheiros[5];
		this.counter=0;
	
	}
	
	public String getMail() {
		return  mail;
	}

	public void addFile(String fileName, int fileSize) {	
		if((fileSize<=this.getRemainingSpace())){
			if(!hasFile(fileName, this.getMail())){
				if(counter==files.length){
					resize();
				}
				files[counter++]= new FicheirosClass(fileName,fileSize,this);
				numFilesCreated++;
				size+=fileSize;
			}
		}
	}

	public boolean hasFile(String fileName, String ownerName){
		return searchIndex(fileName, ownerName)>=0;
	}

	protected final void resize(){
		Ficheiros[] tmp = new Ficheiros[2*files.length];
		for(int i=0; i<counter; i++){
			tmp[i]= files[i];
		}
	files =tmp;
}

	private int searchIndex(String fileName, String ownerName){
		for(int i =0; i<counter;i++){
			if(files[i].getFileName().equals(fileName)&&(files[i].getOwner().getMail().equals(ownerName))){
				return i;				
			}
		}
		return -1;
	}
	
	public int getNumFilesRecieved(){
		return numFilesRecieved;
	}
	
	public int getNumFilesCreated(){
		return numFilesCreated;
	}
	
	public abstract void recieveShare(User sharer, Ficheiros file);

	public int getAccountSize() {
		return accountSize;
	}
	
	public int getUsedSpace() {
		return size;
	}

	public Iterator<Ficheiros> fileIterator() {
		return new StandardIteratorClass<Ficheiros>(files, counter);
	}

	public int getNumFiles() {
		return counter;
	}
	
	public int getRemainingSpace(){
		return (this.getAccountSize()-this.getUsedSpace());
	}
	
	public abstract boolean equals(Object obj);
	
	public abstract String toString();
	
	public abstract boolean canRecieveShare(int tamanho);


}

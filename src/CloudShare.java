import ficheiros.Ficheiros;
import iterators.Iterator;
import java.util.Scanner;
import user.User;
import user.UserPremium;
import cloud.Cloud;
import cloud.CloudClass;

public class CloudShare{	
	public static final String SETA ="> ";
	public static final String PRE = "> ";
	public static final String POS = "\n" ;
	public static final String AC_EXIST = PRE + "Account already exists." + POS;
	public static final String AC_ADD = PRE + "Account was added." + POS;
	public static final String EXIT_MSG = PRE + "Exiting..." + POS;
	public static final String UNKNOWN_COM_MSG = PRE + "Command Unknown..." + POS;
	public static final String AC_NO_EXITS = PRE +"No accounts." + POS;
	public static final String ADDED = PRE + "File uploaded into account." + POS;
	public static final String NO_AC = PRE + "Account does not exist." + POS;
	public static final String FILE_EXISTS = PRE + "File already exists in the account." + POS;
	public static final String FILE_TO_BIG = PRE + "File size exceeds account capacity." + POS;
	public static final String SHARED = PRE + "File was shared." + POS;
	public static final String LESS_ESPACE = PRE + "Account with least free space: ";
	public static final String NO_SHARE = PRE + "File not shared." + POS;
	public static final String AC_LIST = PRE + "All accounts:";
	public static final String LAST_UPDATE = PRE + "Last update: ";
	public static final String ACS_PREMIUM = PRE + "Premium accounts:";
	public static final String ACS_BASIC = PRE + "Basic accounts:";
	public static final String NO_SHARING = PRE + "Account does not allow file sharing." + POS;
	public static final String NO_FILE = PRE + "File does not exist." + POS;
	public static final String EXC_SIZE = PRE + "File size exceeds account capacity." + POS;
	public static final String FILE_UPDATED = PRE + "File was updated." + POS;
	public static final String AC_HAS = SETA + "Account has ";
	public static final String FILES = " files:";
	public static final String SHARED_FILES = " shared files:";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		Cloud cloud = new CloudClass(); 
		Comandos command = getCommand(in);
		while(command != Comandos.EXIT){
			Menu(command, cloud, in);
			command = getCommand(in);
		}
		Menu(command, cloud, in);
	}
	
	private enum Comandos {
		ADD, UPLOAD, SHARE, UPDATE, MINSPACE, LIST, LASTUPDATE, EXIT, UNKNOWN
	}
	
	private static void Menu (Comandos comando, Cloud cloud, Scanner in){
		switch(comando){
			case ADD:
				addUser(cloud, in);
				break;
			case UPLOAD:
				addFile(cloud ,in);
				break;
			case SHARE:
				shareFile(cloud,in);
				break;
			case UPDATE:
				updateFile(cloud,in);
				break;
			case MINSPACE:
				minSpace(cloud,in);
				break;
			case LIST:
				accountsList(cloud,in);
				break;
			case LASTUPDATE:
				lastUpdate(cloud,in);
				break;
			case EXIT:
				System.out.println(EXIT_MSG);
				exit();
				break;
			default:	
				System.out.println(UNKNOWN_COM_MSG);
				break;
		}
	}
	
	private static Comandos getCommand(Scanner in) throws IllegalArgumentException{
		String string = in.next().toUpperCase();
		Comandos comando = Comandos.UNKNOWN;
		for(Comandos com : Comandos.values()){
			try {
				comando = Comandos.valueOf(string);
			} catch (IllegalArgumentException e) {
				 comando = Comandos.UNKNOWN;
			}
		}
		return comando;
	}
	
	private static void addUser(Cloud cloud, Scanner in){
		String name = in.next();
		String type = in.next();
		boolean found = false;
		User temp;
		Iterator<User> itU = cloud.usersIterator();
		itU.initializeIterator();
		while(itU.hasNext()){
			temp = itU.next();
			if(temp.getMail().equalsIgnoreCase(name)){
				found = true;
			}
		}
		if (found == true){
			System.out.println(AC_EXIST);
		} else {
			cloud.addUser(name, type);
			System.out.println(AC_ADD);
		}
	}
	
	private static void addFile(Cloud cloud, Scanner in){
		String ownerName = in.next();
		String fileName = in.next();
		int fileSize = in.nextInt();											
		User temp;
		Iterator<User> itU = cloud.usersIterator();
		itU.initializeIterator();
			if(!cloud.hasUser(ownerName)){
				System.out.println(NO_AC);
			} else {
				while(itU.hasNext()){
				temp = itU.next();
				if((temp.hasFile(fileName, ownerName)&&(temp.getMail().equalsIgnoreCase(ownerName)))){
						System.out.println(FILE_EXISTS);
				} else if ((!temp.hasFile(fileName, ownerName)&&(temp.getMail().equalsIgnoreCase(ownerName))))  {
					if((temp.getRemainingSpace()<fileSize)){
						System.out.println(FILE_TO_BIG);
					} else {
						cloud.addFile(ownerName, fileName, fileSize);
						System.out.println(ADDED);
					}
				}			
				}
			}
	}
		

	
	private static void shareFile(Cloud cloud, Scanner in){
		 String ownerName=in.next();
		 String receiverName=in.next();
		 String fileName=in.next();
		 User owner;
		 User reciever;
		 Ficheiros fileTemp;
		 Iterator<User> itOwn = cloud.usersIterator();
		 Iterator<Ficheiros> itFiles = cloud.userFilesList(ownerName);
		 Iterator<User> itR = cloud.usersIterator();
		 if(cloud.hasUser(ownerName)&& cloud.hasUser(receiverName)){
			 	itOwn.initializeIterator();
				while(itOwn.hasNext()){
					owner = itOwn.next();
					if(((owner.getMail().equalsIgnoreCase(ownerName)&&owner.hasFile(fileName, ownerName)))){
						if (((owner instanceof UserPremium))){
								itFiles.initializeIterator();
								while(itFiles.hasNext()){
								fileTemp = itFiles.next();
									if((fileTemp.getFileName().equalsIgnoreCase(fileName))&&(!fileTemp.hasShare(receiverName))){
										itR.initializeIterator();
										while(itR.hasNext()){
											reciever = itR.next();
											if(reciever.getMail().equalsIgnoreCase(receiverName)&&(reciever.canRecieveShare(fileTemp.getSize()))){
												cloud.shareFile(ownerName, receiverName, fileName);
												System.out.println(SHARED);
											} else if (reciever.getMail().equalsIgnoreCase(receiverName)&&!(reciever.canRecieveShare(fileTemp.getSize()))&&(fileTemp.getOwner().getMail().equals(ownerName))){
												System.out.println(EXC_SIZE);
											}
										}
									} else if ((fileTemp.getFileName().equalsIgnoreCase(fileName))&&(fileTemp.getOwner().getMail().equalsIgnoreCase(ownerName))&&(fileTemp.hasShare(receiverName))){
										System.out.println(FILE_EXISTS);
									}
								}
						} else {
							System.out.println(NO_SHARING);
						}
					} else if ((!owner.hasFile(fileName, ownerName)&&(owner.getMail().equalsIgnoreCase(ownerName)))){
						System.out.println(NO_FILE);
					}
				}
			} else {
				System.out.println(NO_AC);
			}
		
	}
	
	private static void updateFile(Cloud cloud, Scanner in){
		 String ownerName=in.next();
		 String updaterName=in.next();
		 String fileName=in.next();
		 User owner;
		 User updater;
		 Ficheiros file;
		 Iterator<User> itO = cloud.usersIterator();
		 Iterator<Ficheiros> itFiles = cloud.userFilesList(ownerName);
		 Iterator<User> itR = cloud.usersIterator();
		 if(cloud.hasUser(ownerName)&& cloud.hasUser(updaterName)){
			 itO.initializeIterator();
			 while(itO.hasNext()){
				 owner = itO.next();
				 if((owner.getMail().equalsIgnoreCase(ownerName))){
					 if (owner.hasFile(fileName, ownerName)){
					 itFiles.initializeIterator();
					 while(itFiles.hasNext()){
						 file = itFiles.next();
						 if ((ownerName.equalsIgnoreCase(file.getOwner().getMail()))&&(file.getFileName().equalsIgnoreCase(fileName))&&(file.getOwner().getMail().equalsIgnoreCase(ownerName))){
							 itR.initializeIterator();
							 while (itR.hasNext()) {
								 updater = itR.next();
								 if(updater.hasFile(fileName, ownerName)&&updater.getMail().equalsIgnoreCase(updaterName)){
									 cloud.updateFile(ownerName, updaterName, fileName);
									 System.out.println(FILE_UPDATED);
								 } else if(!updater.hasFile(fileName, ownerName)&&updater.getMail().equalsIgnoreCase(updaterName)) {
									 System.out.println(NO_SHARE);
								 }
							 }
						 } else if (!owner.hasFile(fileName, ownerName)) {
							 System.out.println(NO_FILE);
						 }
					 }
					} else {
							 System.out.println(NO_FILE);
					 }
				 }
			 }
		 } else {
			System.out.println(NO_AC);
		 }
	}
	
	private static void minSpace(Cloud cloud, Scanner in){
		if(cloud.numberOfUsers()==0){
			System.out.println(AC_NO_EXITS);
		} else {
			System.out.println(LESS_ESPACE + cloud.UserLessSpace().getMail() + POS);
		}
	}
	private static void fileList(Cloud cloud, Scanner in){
		String userName = in.next();
		Ficheiros fileTemp;
		User userTemp;
		Iterator<Ficheiros> itFiles = cloud.userFilesList(userName);
		Iterator<User> itUsers = cloud.usersIterator();
		if (cloud.hasUser(userName)){
			itUsers.initializeIterator();
			while(itUsers.hasNext()){
				userTemp = itUsers.next();
				if(userTemp.getMail().equalsIgnoreCase(userName)){
					System.out.println(AC_HAS + userTemp.getNumFilesCreated() + FILES);
					itFiles.initializeIterator();
					while(itFiles.hasNext()){
						fileTemp = itFiles.next();
						if (fileTemp.getOwner().getMail().equalsIgnoreCase(userName)){
							System.out.println(SETA+fileTemp.toString());
						}
					}
					System.out.println(AC_HAS + userTemp.getNumFilesRecieved() + SHARED_FILES);
					itFiles.initializeIterator();
					while(itFiles.hasNext()){
						fileTemp = itFiles.next();
						if (!fileTemp.getOwner().getMail().equalsIgnoreCase(userName)){
							System.out.println(SETA+fileTemp.toString());
						}
					}
					System.out.print(POS);
				}
			}
		} else {
				System.out.println(NO_AC);
		}
		
	}
	
	private static void lastUpdate(Cloud cloud, Scanner in){
		String userName=in.next();
		String fileName=in.next();
		User tmp;
		Iterator<User> itU = cloud.usersIterator();
		if(cloud.hasUser(userName)){
			itU.initializeIterator();
			while(itU.hasNext()){
				tmp = itU.next();
				if(tmp.getMail().equalsIgnoreCase(userName)&&(tmp.hasFile(fileName, userName))){
					System.out.println(LAST_UPDATE + cloud.lastUpdate(userName, fileName).getMail() + POS);
				}else if (tmp.getMail().equalsIgnoreCase(userName)&&(!tmp.hasFile(fileName, userName))){
						System.out.println(NO_FILE);
				}	
			}
		}else{	
			System.out.println(NO_AC);	
		}
	}
	
	private static void accountsList(Cloud cloud, Scanner in){
		String type = in.next();
		User userTemp;
		if(type.equalsIgnoreCase("all")){
			Iterator<User> it = cloud.usersIterator();
			it.initializeIterator();
			System.out.println(AC_LIST);
			while(it.hasNext()){
				userTemp = it.next();
				System.out.println(SETA+userTemp.toString());
			}
			System.out.print(POS);
		} else if (type.equalsIgnoreCase("Basic")){
			Iterator<User> it = cloud.usersTypeIterator(type);
			it.initializeIterator();
			System.out.println(ACS_BASIC);
			while(it.hasNext()){
				userTemp = it.next();
				System.out.println(SETA+ userTemp.getMail());
			}
			System.out.print(POS);
		} else if(type.equalsIgnoreCase("Premium")){
			Iterator<User> it = cloud.usersTypeIterator(type);
			it.initializeIterator();
				System.out.println(ACS_PREMIUM);
			while(it.hasNext()){
				userTemp = it.next();
				System.out.println(SETA+ userTemp.getMail());
			}
			System.out.print(POS);
		}else if(type.equalsIgnoreCase("files")){
			fileList(cloud,in);
		}
	}
	
	private static void exit(){
		System.exit(0);
	}
	
}

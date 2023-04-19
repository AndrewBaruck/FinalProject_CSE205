package main;

import java.util.Scanner;
import java.io.*;

public abstract class Account {
	
	/*  Example account file (name is user_[username].txt):
	 * 
	 *  myPassword
	 *  
	 *  chatroom1
	 *  chatroom2
	 *  chatroom3
	 */
	
	public static void updateUsername(String old1, String new1) throws IOException {
		File inputFile = new File("user_" + old1 + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
		in.close();
		//System.out.println("FILETEXT\n" + fileText + "\n---------------");
		PrintWriter out = new PrintWriter("user_" + new1 + ".txt");
		out.print(fileText);
		out.flush();
		boolean deleteSuccess = deleteAccount(old1);
		if (!deleteSuccess) System.out.println("OLD FILE DID NOT DELETE");
	}
	
	public static void updatePassword(String username, String newPassword) throws IOException {
		File inputFile = new File("user_" + username + ".txt");
		Scanner in = new Scanner(inputFile);
		String chatrooms = "";
		in.nextLine();
		in.nextLine();
		while (in.hasNextLine()) {
			chatrooms += in.nextLine() + "\n";
		}
		in.close();
		
		PrintWriter out = new PrintWriter("user_" + username + ".txt");
		out.println(newPassword + "\n");
		out.println(chatrooms);
		out.close();
	}
	
	public static void deleteAllAccounts() {
		File f1 = new File("useless.txt");
		String s1 = f1.getAbsolutePath();
		s1 = s1.substring(0, s1.lastIndexOf("\\"));
		File f2 = new File(s1);
		for (String file : f2.list()) {
			System.out.println(file);
		}
		for (String file : f2.list()) {
			if (file.contains("user_")) {
				File f3 = new File(file);
				if (f3.getName().contains("user_")) f3.delete();
			}
		}
	}
	
	public static boolean deleteAccount(String username) {
		File inputFile = new File("user_" + username + ".txt");
		return inputFile.delete();
	}
	
	private static String[] getChatrooms(String username) throws IOException {
		File inputFile = new File("user_" + username + ".txt");
		Scanner in = new Scanner(inputFile);
		String chatrooms = "";
		in.nextLine();
		in.nextLine();
		while (in.hasNextLine()) {
			chatrooms += in.nextLine() + "\n";
		}
		in.close();
		return chatrooms.split("\n");
	}
	
	public static void createAccount(String username, String password) throws IOException {
		File inputFile = new File("user_" + username + ".txt");
		if (inputFile.exists()) return;
		inputFile.createNewFile();
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter("user_" + username + ".txt");
		out.println(password + "\n");
		in.close();
		out.close();
	}
	
	private static void addChatroom(String username, String chatroomName) throws IOException {
		File inputFile = new File("user_" + username + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
		
		PrintWriter out = new PrintWriter("user_" + username + ".txt");
		out.print(fileText);
		out.println(chatroomName);
		
		in.close();
		out.close();
	}
	
	private static void removeChatroom(String username, String chatroomName) throws IOException {
		File inputFile = new File("user_" + username + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
	
		fileText = fileText.replace(chatroomName, "");
		PrintWriter out = new PrintWriter("user_" + username + ".txt");
		out.print(fileText);
		
		in.close();
		out.close();
	}
	
	public static boolean usernameExists(String username) {
		File inputFile = new File("user_" + username + ".txt");
		return inputFile.exists();
	}
	
	public static String getPassword(String username) throws IOException {
		File inputFile = new File("user_" + username + ".txt");
		Scanner in = new Scanner(inputFile);
		String password = in.nextLine();
		
		in.close();
		
		return password;
	}
}

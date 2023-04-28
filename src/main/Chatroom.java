package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Chatroom {
	
	/*  Example account file (name is chatroom_[chatroomName].txt):
	 * 
	 *  [user1]: hi
	 *  [user2]: hello
	 *  [user1]: a
	 *  
	 *  
	 */
	
	public static void printActiveUsers(String chatroomName) throws IOException {
		File inputFile = new File("userList_chatroom_" + chatroomName + ".txt");
		Scanner in = new Scanner(inputFile);
		System.out.println("Active users in this chatroom:");
		while (in.hasNextLine()) {
			System.out.println(in.nextLine());
		}
		in.close();
	}
	
	public static void addActiveUser(String username, String chatroomName) throws IOException {
		File inputFile = new File("userList_chatroom_" + chatroomName + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
	
		fileText = fileText.replace(username, "");
		PrintWriter out = new PrintWriter("userList_chatroom_" + chatroomName + ".txt");
		out.print(fileText);
		out.print(username);
		out.flush();
		in.close();
		out.close();
	}
	
	public static void removeActiveUser(String username, String chatroomName) throws IOException {
		File inputFile = new File("userList_chatroom_" + chatroomName + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
	
		fileText = fileText.replace(username + "\n", "");
		PrintWriter out = new PrintWriter("userList_chatroom_" + chatroomName + ".txt");
		out.print(fileText);
		out.flush();
		in.close();
		out.close();
	}
	
	
	public static String[] getMessages(String chatroomName) throws IOException {
		File inputFile = new File("chatroom_" + chatroomName + ".txt");
		Scanner in = new Scanner(inputFile);
		String messages = "";
		while (in.hasNextLine()) {
			messages += in.nextLine() + "\n";
		}
		in.close();
		return messages.split("\n");
	}
	
	public static void deleteAllChatrooms() {
		File f1 = new File("useless.txt");
		String s1 = f1.getAbsolutePath();
		s1 = s1.substring(0, s1.lastIndexOf("\\"));
		File f2 = new File(s1);
		for (String file : f2.list()) {
			System.out.println(file);
		}
		for (String file : f2.list()) {
			if (file.contains("chatroom_")) {
				File f3 = new File(file);
				if (f3.getName().contains("chatroom_")) f3.delete();
			}
		}
	}
	
	public static boolean deleteChatroom(String chatroomName) {
		File inputFile = new File("chatroom_" + chatroomName + ".txt");
		boolean deletedBoth = inputFile.delete();
		
		File temp = new File("userList_chatroom_" + chatroomName + ".txt");
		if (!deletedBoth) deletedBoth = temp.delete();
		return deletedBoth;
	}
	
	public static void createChatroom(String chatroomName) throws IOException {
		File inputFile = new File("chatroom_" + chatroomName + ".txt");
		if (inputFile.exists()) return;
		inputFile.createNewFile();
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter("chatroom_" + chatroomName + ".txt");
		//TODO add text to file when first creating chatroom? idk 
		in.close();
		out.close();
		
		PrintWriter temp = new PrintWriter(new File("userList_chatroom_" + chatroomName + ".txt"));
		temp.print("");
		temp.close();
	}
	
	public static void addMessage(String chatroomName, String username, String message) throws IOException {
		File inputFile = new File("chatroom_" + chatroomName + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
		
		PrintWriter out = new PrintWriter("chatroom_" + chatroomName + ".txt");
		out.print(fileText);
		out.println("[" + username + "]: " + message);
		
		in.close();
		out.close();
	}
	
	public static void addBlankLine(String chatroomName) throws IOException {
		File inputFile = new File("chatroom_" + chatroomName + ".txt");
		Scanner in = new Scanner(inputFile);
		String fileText = "";
		while (in.hasNextLine()) {
			fileText += in.nextLine() + "\n";
		}
		
		PrintWriter out = new PrintWriter("chatroom_" + chatroomName + ".txt");
		out.print(fileText);
		out.println("      ");
		
		in.close();
		out.close();
	}
	
	public static void clearMessages(String chatroomName) throws IOException {
		PrintWriter out = new PrintWriter("chatroom_" + chatroomName + ".txt");
		out.print("\n");
		out.close();
	}
	
	public static boolean chatroomExists(String chatroomName) {
		File inputFile = new File("chatroom_" + chatroomName + ".txt");
		return inputFile.exists();
	}
	
}

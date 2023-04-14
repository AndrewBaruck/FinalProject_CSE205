package main;

import java.util.Scanner;
import java.io.*;

public class Main {

    static String currentRoom;
    static String currentUser;

    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);

//		Account.deleteAccount("user1");
//		Account.createAccount("user1", "password1");
//		Account.addChatroom("user1", "chatroom1");
//		Account.addChatroom("user1", "chatroom2");
//		Account.addChatroom("user1", "chatroom3");
//		Account.removeChatroom("user1", "chatroom3");

//		Chatroom.deleteChatroom("chat1");
//		Chatroom.createChatroom("chat1");
//		Chatroom.addMessage("chat1", "bob", "hi guys");
//		Chatroom.addMessage("chat1", "bill", "hey");
//		Chatroom.clearMessages("chat1");

        InitialView(scnr);
    }

    static void InitialView(Scanner scnr) throws IOException {

        System.out.println("Welcome to Chat App!");
        System.out.println("Please select:");
        System.out.println("(R)egister, (L)ogin, (Q)uit");
        System.out.println("---------------------------");

        String input = scnr.next();
        input = input.toLowerCase();
        switch(input) {
            case "r":
            case "register":
                register(scnr);
                break;
            case "l":
            case "login":
                login(scnr);
                break;
            case "q":
            case "quit":
                quit();
                break;
            default:
                System.out.println("Invalid input!\n");
                InitialView(scnr);
                break;
        }

        MainView(scnr);

    }

    private static void MainView(Scanner scnr){
        System.out.println("Login Sucessful!");
        System.out.println("Please select from the following options" );
        System.out.println("(J)oin,(C)reate,(A)ccount,(L)ougout");

        String selection = scnr.next();
        String selectionJustified = selection.toLowerCase();

        String roomName;

        switch (selectionJustified) {
            case "j", "join":
                String roomInput = SelectRoom(scnr);
                if(Chatroom.chatroomExists(roomInput) == true){
                    roomName = roomInput;
                    System.out.println();
                    System.out.println("Sucessfully joined "
                    + roomName + " Chatroom!");
                }
                else{
                    System.out.println("Chatroom " +
                            roomInput + "does not exist, create it!");
                }
                MainView(scnr);
                break;
            case "c", "create":
                String createdName;
                System.out.println("Enter the name of the room you would like to create");
                System.out.println("Room name must only be numbers and letters");
                String inputName = scnr.next();
                boolean checker = NameWorks(inputName.toLowerCase());
                if (checker = true) {
                    createdName = inputName.toLowerCase();
                    System.out.println("Chatroom created sucessfully, joining!");
                    roomName = createdName;
                    System.out.println("Sucessfully joined "
                            + roomName + " Chatroom!");
                }
                else{
                    System.out.println("The name you have entered is not allowed!");
                    MainView(scnr);
                    break;
                }
                break;
            case "a", "account":
                AccountUpdate(scnr);
                break;
            case "l", "logout":






        }




    }

    private static void AccountUpdate(Scanner scanner){
        System.out.println("Please select what you want to update on your account:");
        System.out.println("Update: (U)sername or (P)assword");

        String selection = scanner.next();
        String selectionJustified = selection.toLowerCase();

        switch(selectionJustified){
            case "u", "username":
                System.out.print("ENTER YOUR NEW USERNAME: ");
                String user = scanner.next();
                //Account.updateUsername(currentUser, user);

        }


    }

    private static String SelectRoom(Scanner scanner){
        System.out.println("Enter the name of the Chatroom you want to join:");
        String input = scanner.next();
        String inputJustified = input.toLowerCase();

        return inputJustified;
    }

    private static boolean NameWorks(String subject){
        int length = subject.length();

        for(int i = 0; i < length; i++ ){
            char a = subject.charAt(i);
                if(IsNumberOrLetter(a) == true){

                }
                else{
                    return false;
                }
        }
        return true;

    }

    private static boolean IsNumberOrLetter(char character){
        if(Character.isLetter(character) == true || Character.isDigit(character) == true){
            return true;
        }
        return false;
    }

    static void register(Scanner scnr) throws IOException {
        System.out.print("\nEnter a valid username: ");
        scnr.nextLine();
        String input = scnr.nextLine();
        while (input.contains(" ") || Account.usernameExists(input)) {
            if (input.contains(" ")) {
                System.out.print("\nUsername cannot contain a space. Enter a valid username: ");
                input = scnr.nextLine();
            } else {
                System.out.print("\nUsername already exists. Enter a valid username: ");
                input = scnr.nextLine();
            }
        }
        String newUsername = input;

        System.out.print("\nEnter a valid password: ");
        input = scnr.nextLine();
        while (input.contains(" ")) {
            System.out.print("\nPassword cannot contain a space. Enter a valid password: ");
            input = scnr.nextLine();
        }
        String newPassword = input;
        Account.createAccount(newUsername, newPassword);
        System.out.println("\nAccount successfully created!");
        //TODO optional: automatically log them in, otherwise just call login method
    }

    static void login(Scanner scnr) throws IOException {
        System.out.print("\nEnter your username: ");
        scnr.nextLine();
        String input = scnr.nextLine();
        while (!Account.usernameExists(input)) {
            System.out.print("Username does not exist. Please enter a valid username: ");
            input = scnr.nextLine();
        }
        String currentUsername = input;
        String password = Account.getPassword(currentUsername);
        System.out.print("\nEnter your password: ");
        input = scnr.nextLine();
        while (!(input.equals(password))) {
            System.out.print("Incorrect Password! Try again: ");
            input = scnr.nextLine();
        }
        System.out.print("\nSuccessfully logged in!");
        //TODO finish login idk set current account logged in, open new menu etc
    }

    static void quit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

}

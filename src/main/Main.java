package main;

import java.util.Scanner;
import java.io.*;

public class Main {
    static String currentUser;
    static String currentRoom;
    static int stupidCounter = 0;
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);

//		Account.deleteAccount("user1");
//		Account.createAccount("user1", "password1");
//		Account.addChatroom("user1", "chatroom1");
//		Account.addChatroom("user1", "chatroom2");
//		Account.addChatroom("user1", "chatroom3");
//		Account.removeChatroom("user1", "chatroom3");
//
//		Chatroom.deleteChatroom("chat1");
//		Chatroom.createChatroom("chat1");
//		Chatroom.addMessage("chat1", "bob", "hi guys");
//		Chatroom.addMessage("chat1", "bill", "hey");
//		Chatroom.clearMessages("chat1");
//
//        Account.deleteAllAccounts();
//        Chatroom.deleteAllChatrooms();

        currentUser = InitialView(scnr);

        roomView(scnr, currentUser);

    }

    static String InitialView(Scanner scnr) throws IOException {

        System.out.println("Welcome to Chatty Ckatthy App!");
        System.out.println("Please select:");
        System.out.println("(R)egister, (L)ogin, (Q)uit");
        System.out.println("---------------------------");

        String input = scnr.nextLine();
        String currUser = "";
        input = input.toLowerCase();
        switch(input) {
            case "r":
            case "register":
                currUser = register(scnr);
                break;
            case "l":
            case "login":
                currUser = login(scnr);
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

        return currUser;
    }

    private static void MainView(Scanner scnr) throws IOException {
        //System.out.println("Login Sucessful!");
        System.out.println("Please select from the following options" );
        System.out.println("(J)oin,(C)reate,(A)ccount,(L)ougout");

        String selection = scnr.nextLine();
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
                    currentRoom = roomName;
                    Chatroom.addActiveUser(currentUser, roomName);
                    roomView(scnr, currentUser);
                }
                else{
                    System.out.println("Chatroom " +
                            roomInput + " does not exist, create it!");
                    stupidCounter++;
                    if(stupidCounter > 1){

                    }
                }
                MainView(scnr);
                break;
            case "c", "create":
                String createdName;
                System.out.println("Enter the name of the room you would like to create");
                System.out.println("Room name must only be numbers and letters");
                String inputName = scnr.nextLine();
                boolean checker = NameWorks(inputName.toLowerCase());
                if (checker = true) {
                    createdName = inputName.toLowerCase();
                    System.out.println("Chatroom created sucessfully, joining!");
                    roomName = createdName;
                    System.out.println("Sucessfully joined "
                            + roomName + " Chatroom!");
                    Chatroom.createChatroom(roomName);
                    Chatroom.addActiveUser(currentUser, roomName);
                    currentRoom = roomName;
                }
                else{
                    System.out.println("The name you have entered is not allowed!");
                    stupidCounter++;
                    if(stupidCounter > 1){

                    }
                    MainView(scnr);
                    break;
                }
                break;
            case "a", "account":
                AccountUpdate(scnr);
                break;
            case "l", "logout":
                System.out.println("ARE YOU SURE YOU WANT TO LOG OUT? Y/N");
                String confirmation = scnr.nextLine();
                String confirmationJustified = confirmation.toLowerCase();
                if(confirmationJustified.equals("y")){
                    System.out.println("LOGGED OUT, RETURNING TO LOGIN");
                    currentUser = null;
                    InitialView(scnr);
                }
            default:
                System.out.println("The input option does not exist!");
                stupidCounter++;
                if(stupidCounter > 1){

                }
                MainView(scnr);

        }
    }

    private static void AccountUpdate(Scanner scanner) throws IOException {
        System.out.println("Please select what you want to update on your account:");
        System.out.println("Update: (U)sername or (P)assword");
        String selection = scanner.nextLine();
        String selectionJustified = selection.toLowerCase();
        switch (selectionJustified) {
            case "u", "username":
                System.out.print("ENTER YOUR NEW USERNAME: ");
                String user = scanner.nextLine();
                System.out.println();
                boolean hell = Account.usernameExists(user);
                if(!hell) {
                    try {
                        Account.updateUsername(currentUser, user);
                    } catch (Exception e) {
                        System.out.println(e + "SOMETHING WENT MAJORLY WRONG, YOU ARE SCREWWWWWWWED");
                        stupidCounter++;
                        if(stupidCounter > 1){

                        }
                        MainView(scanner);
                    }
                }
                else{
                    System.out.println("ACCOUNT EXISTS ALREADY PICK SOMETHING FREE NEXT TIME");
                    stupidCounter++;
                    if(stupidCounter > 1){

                    }
                    AccountUpdate(scanner);
                }
                break;
            case "p", "password":
                System.out.print("ENTER YOUR NEW PASSWORD: ");
                String newPass = scanner.nextLine();
                System.out.println();
                System.out.print("Confirm Password: ");
                String newPassTwo = scanner.nextLine();

                if (newPass.equals(newPassTwo)) {
                    System.out.println();
                    System.out.println("Password Updated!");
                } else {
                    System.out.println();
                    System.out.println("Passwords did not match, returning to menu");
                    AccountUpdate(scanner);
                }
                break;
        }
        System.out.println("DO YOU WANT TO CONTINUE TO EDIT YOUR ACCOUNT? Y/N");
        String selector = scanner.nextLine();
        String selectorJustified = selector.toLowerCase();
        if(selectorJustified == "y"){
            AccountUpdate(scanner);
        }
        else{
            System.out.println("RETURNING TO MAIN MENU");
            MainView(scanner);
        }
    }
    private static String SelectRoom(Scanner scanner){
        System.out.println("Enter the name of the Chatroom you want to join:");
        String input = scanner.nextLine();
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

    static String register(Scanner scnr) throws IOException {
        System.out.print("\nEnter a valid username: ");

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
        currentUser = newUsername;
        return newUsername;
        //TODO optional: automatically log them in, otherwise just call login method
    }

    static String login(Scanner scnr) throws IOException {
        System.out.print("\nEnter your username: ");

        String input = scnr.nextLine();
        while (!Account.usernameExists(input)) {
            stupidCounter++;
            if(stupidCounter > 1){

            }
            System.out.print("Username does not exist. Please enter a valid username: ");
            input = scnr.nextLine();
        }
        String currentUsername = input;
        currentUser = currentUsername;

        String password = Account.getPassword(currentUsername);
        System.out.print("\nEnter your password: ");
        input = scnr.nextLine();
        while (!(input.equals(password))) {

            System.out.print("Incorrect Password! Try again: ");
            input = scnr.nextLine();
        }
        System.out.println("Successfully logged in!");
        return currentUsername; //TODO
        //TODO finish login idk set current account logged in, open new menu etc
    }

    static void quit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    static void roomView(Scanner scnr, String currUser) throws IOException {
        System.out.printf("Welcome to \"%s\" \"%s\"\n", currentRoom,currUser);
        System.out.println("Type \"/help\" for help.");
        System.out.println("---------------------------");


        String input;
        boolean messaging = true;
        
        boolean firstLoop = true;

        checkThread check = new checkThread();
        check.setCurrentRoom(currentRoom);
        check.start();

        while(messaging == true) {
            if (firstLoop) {
        		Chatroom.addJoinMessage(currUser, currentRoom);
        		firstLoop = false;
        	}
            input = scnr.nextLine();

            if(input.equals("/list")) {
                Chatroom.printActiveUsers(currentRoom);

            }
            else if(input.equals("/history")) {
                String[] messages = Chatroom.getMessages(currentRoom);

                for(int i = 0; i < messages.length; i++) {
                    System.out.println(messages[i]);

                }

            }
            else if(input.equals("/help")) {
                System.out.println("List of commands: ");
                System.out.println("/list: Return a list of users currently in this chat room");
                System.out.println("/leave: Exits the chat room");
                System.out.println("/history: Print all past messages from the room");
                System.out.println("/help: Prints list of available commands");

            }
            else if(input.equals("/leave")) {
                messaging = false;
                check.stopThread();

            }
            else {
                Chatroom.addMessage(currentRoom, currentUser, input);

            }

        }

        System.out.println("ARE YOU SURE YOU WANT TO LEAVE THE CHATROOM? Y/N");
        String confirmation = scnr.nextLine();

        if(confirmation.equals("y")) {
            MainView(scnr);
            check.stopThread();

        }
        else if(confirmation.equals("n")) {
            roomView(scnr, currentUser);

        }

    }
}
class checkThread extends Thread {
    private String currentRoom;
    private volatile boolean checking;
    @Override
    public void run() {
        int mgsLength = 0;
        checking = true;

        try {
            mgsLength = Chatroom.getMessages(currentRoom).length;

        }
        catch (IOException e) {


        }

        while (checking == true){
            try {
                if (Chatroom.getMessages(currentRoom).length > mgsLength) {
                    System.out.println(Chatroom.getMessages(currentRoom)[Chatroom.getMessages(currentRoom).length - 1]);
                    mgsLength = Chatroom.getMessages(currentRoom).length;

                }
            }
            catch (IOException e) {


            }

        }

    }
    public void setCurrentRoom(String room) {
        this.currentRoom = room;

    }
    public void stopThread() {
        checking = false;

    }

}

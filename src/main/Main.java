package main;

import java.util.Scanner;
import java.io.*;

public class Main {
    static String currentUser;
    static String currentRoom;
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

        currentUser = firstOpening(scnr);

        roomView(scnr, currentUser);
        String currRoom;

    }

    static String firstOpening(Scanner scnr) throws IOException {

        System.out.println("Welcome to Chat App!");
        System.out.println("Please select:");
        System.out.println("(R)egister, (L)ogin, (Q)uit");
        System.out.println("---------------------------");

        String input = scnr.next();
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
                firstOpening(scnr);
                break;
        }

        return currUser;

    }

    static String register(Scanner scnr) throws IOException {
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
        return newUsername;
        //TODO optional: automatically log them in, otherwise just call login method
    }

    static String login(Scanner scnr) throws IOException {
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
        return currentUsername;
        //TODO finish login idk set current account logged in, open new menu etc
    }

    static void quit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    static void roomView(Scanner scnr, String currUser) {
        System.out.printf("Welcome to \"%s\" \"%s\"\n", currentRoom,currUser);
        System.out.println("Type \"/help\" for help.");
        System.out.println("---------------------------");
//        Chatroom.getMessages(currentRoom);

        String input = "";
        while(!input.equals("/leave")) {
            input = scnr.nextLine();
            if(input.equals("/list")) {
//                Chatroom.getUsers(currentRoom);

            }
            else if(input.equals("/history")) {


            }
            else if(input.equals("/help")) {


            }
//            Chatroom.addMessage(currentRoom, input);
            System.out.printf("%s: %s\n", currUser, input);

        }


    }

}

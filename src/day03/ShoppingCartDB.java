package day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;

public class ShoppingCartDB {

    private String currentUser;
    private List<String> groceryItems;
    private Set<String> setOfRegisteredUsers;
    private Set<String> newlyAddedItems;
    private String directory;

    public ShoppingCartDB() {
        directory = "db";
    }

    public ShoppingCartDB(String directory) {
        this.directory = directory;
        setOfRegisteredUsers = new HashSet<>();
        
    }

    public void menu() {

        System.out.println("Welcome to your shopping cart");
        System.out.println("===============================");
        System.out.println("Login..");
        System.out.println("Add..");
        System.out.println("List..");
        System.out.println("Save..");
        System.out.println("Users..");
        System.out.println("Quit.");

    }

    public void userInput() throws IOException {

        System.out.println("user input");
        Scanner scanner = new Scanner(System.in);
        System.out.println("scanner created");

        while (true) {
            menu();

            System.out.println("after menu");

            String keyboardInput = scanner.next();
            System.out.printf("keyboard input:", keyboardInput);
            keyboardInput = keyboardInput.trim().toLowerCase();
            System.out.println(keyboardInput);

            if (keyboardInput.startsWith("login")) {

                String name = scanner.nextLine().trim();
                login(name);

            }

            if (keyboardInput.startsWith("users")) {
                System.out.println("entering users method");
                users();

            }

            if (keyboardInput.startsWith("add")) {
                String itemsToAdd = scanner.nextLine().trim();
                add(itemsToAdd);

            }

            if (keyboardInput.startsWith("list")) {
                list();
            }

            if (keyboardInput.startsWith("save")) {
                save();
            }


            if (keyboardInput.startsWith("quit")) {
                break;

            }

        }

        scanner.close();

    }

    public void login(String name) throws IOException {

        System.out.println("in login");

        currentUser = name;
        setOfRegisteredUsers.add(currentUser);

        newlyAddedItems = new HashSet<>();

        String dirPathFileName = directory + File.separator + currentUser;

        File file = new File(dirPathFileName);
        System.out.println("file created");

        groceryItems = new ArrayList<>();


        if (file.exists()) {
            
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String groceryItem;

            while ((groceryItem = br.readLine()) != null) {
                groceryItems.add(groceryItem);
            }

            if (groceryItems.isEmpty()) {
                System.out.printf("%s , your cart is empty\r\n", currentUser);

            } else {

                System.out.printf("%s , your cart contains the following items\r\n", currentUser);
                for (String itm : groceryItems) {
                    System.out.println(itm);
                }
            }

            br.close();

        } else {
            file.createNewFile();
            System.out.printf("Newly created account for %s\r\n", currentUser);

        }

    }

    public void users() {

        System.out.println("listing users");

        File userDatabase = new File(directory);
        System.out.println("created filed called userDatabase");
        File[] listOfRegisteredUsers = userDatabase.listFiles();
        System.out.println("Created file array");

        if (listOfRegisteredUsers != null) {
            System.out.println("The following users are registered:");
            for (int i = 0; i < listOfRegisteredUsers.length; i++) {
                System.out.printf("%d. %s\r\n", i + 1, listOfRegisteredUsers[i].getName());
            }

        }

        System.out.println("completed listing users");

    }
 
    public void add(String itemsToAdd) {

        itemsToAdd = itemsToAdd.replace(",", " ");
        String[] individualItems = itemsToAdd.split("\\s+");

        for (int i = 0; i < individualItems.length; i++ ){
            System.out.printf(individualItems[i] + " added to cart.\r\n");
        }

        Collections.addAll(newlyAddedItems, individualItems);
        


    }

    public void list() {

        // i need access to the groceryItems and newly added items
        // i need to check if any item in newlyAddeditmes exist in grocery items
        for (String newItem : newlyAddedItems) {
            if (!groceryItems.contains(newItem)) {
                groceryItems.add(newItem);
            }
        }

        System.out.println(groceryItems);
        // if there is a same item in newlyaddeditems and grocery items
        // dont add to grocery items
        // add the new unique items into grocery items
        // print the grocery items



    }

    public void save() throws IOException {

        String dirPathFileName = directory + File.separator + currentUser;
        

        FileWriter writer = new FileWriter(dirPathFileName, false);
        BufferedWriter bw = new BufferedWriter(writer);

        for (String currentItemsInGroceryList : groceryItems) {
            bw.write(currentItemsInGroceryList);
            bw.newLine();
        }

        bw.flush();
        writer.flush();
        bw.close();
        writer.close();



        //i need to open the existing file with the user

        // i need to save the updated arraylist of groceryitems after list()
        // i need a writer so i can access the user file
        // loop through the groceryitems and write to the file
        // 

    }

    

}

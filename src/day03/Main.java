package day03;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // java -cp classes day03/Main "cartdb"
        String dirPath;
        
        System.out.println("main start");
        if (args.length == 0) {
            System.out.println("setting default value");
            dirPath = "db";
            System.out.println("successfully set default value");

        } else {
            dirPath = args[0];
        }



        File directory = new File(dirPath); // checking if directory exists

        if (directory.exists()) {
            System.out.println("Directory " + directory.toString() + " had already been created");
        } else {
            directory.mkdir();
        }

        ShoppingCartDB db = new ShoppingCartDB(dirPath);
        db.userInput();

    }

}

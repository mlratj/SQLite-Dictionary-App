package org.knowlegde;

import org.knowlegde.knowlegdeDB;

import java.util.Scanner;

public class Run {
    public static void main(String args[]) {
        knowlegdeDB.main(null);
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            System.out.println("Which module would you like to start? ");
            System.out.println(" 1. DML operations \n 2. Table management \n 3. Default operations \n 4. Read only (the safest!)");
            System.out.println("Press a chosen option's number of press '5' to exit \n");
            input = scan.nextLine();
            if(input.equals("1")) {
                DML.main(null);
            }
            else if(input.equals("2")) {
                TableManagement.main(null);
            }
            else if(input.equals("3")) {
                defaultOperations.main(null);
            }
            else if(input.equals("4")) {
                readonly.main(null);
            }
            else if(input.equals("5")) {System.exit(0);}
            else {
                System.out.println("Invalid input, please try again.");
            }
        }
        while (!input.equals("5"));
    }
}

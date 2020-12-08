package org.knowlegde;

import java.sql.*;
import java.util.Scanner;

public class TableManagement {
    public static void main(String args[]) {
        Connection c = null;
        Statement s = null;
        System.out.println("Chose an operation to perform or press 5 to quit: \n");
        do {
            int id;
            String sql_code = "", val_a = "", val_b = "", val_c = "", code_preview = "";
            int int_val = 0;
            System.out.println("1. Drop table");
            System.out.println("2. Create Table");
            System.out.println("5. Exit");
            Scanner reader = new Scanner(System.in);
            int n = reader.nextInt();
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:KnowledgeDB.db");
                System.out.println("Connected with KnowledgeDB.\n");
                s = c.createStatement();
                Scanner scanValue;
                switch (n) {
                    case 1:
                        scanValue = new Scanner(System.in);
                        System.out.println("Enter table name, to drop it:");
                        val_a = scanValue.nextLine();
                        sql_code = "DROP TABLE IF EXISTS " + val_a + ";";
                        code_preview = sql_code;
                        System.out.println("The following query is being executed : " + code_preview);
                        s.executeUpdate(sql_code);
                        System.out.println("Operation performed.");
                        break;

                    case 2:
                        // only one table's schema is supported for now
                        scanValue = new Scanner(System.in);
                        System.out.println("Which language would you like to add? ");
                        val_a = scanValue.nextLine();
                        sql_code = "CREATE TABLE " + val_a +
                                " (p_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                " word TEXT NOT NULL," +
                                " word_type TEXT NOT NULL," +
                                " status TEXT)" + ";";
                        code_preview = sql_code;
                        System.out.println("The following query is being executed : " + code_preview);
                        s.executeUpdate(sql_code);
                        System.out.println("Created Successfully!!!");
                        break;

                    case 5:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Please make your choice wisely ;)");
                        break;
                }
                s.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        } while(true);
    }
}
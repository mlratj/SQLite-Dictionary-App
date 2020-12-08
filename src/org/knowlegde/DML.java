package org.knowlegde;

import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class DML {
    public static void main(String args[]) {
        Connection c = null;
        Statement s = null;
        do {
            System.out.println("Select DML Operation For Product Table...");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. Select");
            System.out.println("5. Exit");
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter a choice: ");
            int n = reader.nextInt();
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:KnowledgeDB.db");
                c.setAutoCommit(false);
                s = c.createStatement();
                int p_id;
                String sql = "", word = "", word_type = "", status = "", table = "";
                Scanner sc;
                switch (n) {
                    case 1:
                        System.out.println("Which table would you like to expand? ");
                        sc = new Scanner(System.in);
                        table = sc.nextLine();
                        System.out.println("Please enter id number: ");
                        sc = new Scanner(System.in);
                        p_id = sc.nextInt();
                        System.out.println("Please enter a word to add: ");
                        sc = new Scanner(System.in);
                        word = sc.nextLine();
                        System.out.println("Please enter a word type: ");
                        sc = new Scanner(System.in);
                        word_type = sc.nextLine();
                        System.out.println("Please enter a word's status: ");
                        sc = new Scanner(System.in);
                        status = sc.nextLine();
                        sql = "INSERT INTO " + table + " (p_id,word,word_type,status)" +
                                " VALUES (" + p_id + ",'" + word + "','" + word_type + "','" + status + "')";
                        System.out.println(sql);
                        System.out.println("Done!");
                        break;

                    case 2:
                        System.out.println(ConsoleColors.RED + "Warning! Update is irreversible!" + ConsoleColors.RESET);
                        System.out.println("On which table would you like perform an update? ");
                        sc = new Scanner(System.in);
                        table = sc.nextLine();
                        System.out.println("Please enter id number: ");
                        sc = new Scanner(System.in);
                        p_id = sc.nextInt();
                        System.out.println("Please enter a word to add: ");
                        sc = new Scanner(System.in);
                        word = sc.nextLine();
                        System.out.println("Please enter a word type: ");
                        sc = new Scanner(System.in);
                        word_type = sc.nextLine();
                        System.out.println("Please enter a word's status: ");
                        sc = new Scanner(System.in);
                        status = sc.nextLine();
                        sql = "UPDATE " + table + " SET word = '" + word + "',word type='" + word_type + "',status='" + status +
                                "' WHERE p_id=" + p_id + ";";
                        s.executeUpdate(sql);
                        System.out.println("Updated!");
                        break;

                    case 3:
                        System.out.println(ConsoleColors.RED + "Warning! Update is irreversible!" + ConsoleColors.RESET);
                        System.out.println("On which table would you like perform a delete? ");
                        sc = new Scanner(System.in);
                        table = sc.nextLine();
                        System.out.println("Please provide word's ID to delete:");
                        sc=new Scanner(System.in);
                        p_id=sc.nextInt();
                        sql="DELETE FROM " + table + " WHERE p_id=" + p_id+";";
                        s.executeUpdate(sql);
                        System.out.println("Deleted!");
                        break;

                    case 4:
                        System.out.println("Which table would you like see? ");
                        sc = new Scanner(System.in);
                        table = sc.nextLine();
                        ResultSet rs_select = s.executeQuery("SELECT * FROM " + table + ";");
                        System.out.println("ID\t Name\t\t Price\t Qty ");
                        while (rs_select.next()) {
                            p_id = rs_select.getInt("p_id");
                            word = rs_select.getString("word");
                            word_type = rs_select.getString("word_type");
                            status = rs_select.getString("status");
                            System.out.println(p_id + "\t " + word + " \t " + word_type + "\t " + status);
                        }
                        rs_select.close();
                        break;

                    case 5:
                        System.exit(0);
                        break;
                }
                s.close();
                c.commit();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        while (true);
    }

    public class ConsoleColors {
        public static final String RESET = "\033[0m";
        public static final String RED = "\033[0;31m";
    }
}

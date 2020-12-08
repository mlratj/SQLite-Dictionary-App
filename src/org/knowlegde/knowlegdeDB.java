package org.knowlegde;

import java.sql.*;

public class knowlegdeDB {
    public static void main(String args[]) {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:KnowledgeDB.db");
        }
        catch ( Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database started");
    }
}

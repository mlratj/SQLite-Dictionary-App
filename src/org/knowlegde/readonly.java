package org.knowlegde;

import java.sql.*;

public class readonly {
    public static void main(String args[]) {
        Statement s = null;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:KnowledgeDB.db");
            System.out.println("Connected with KnowledgeDB.\n");
            s = c.createStatement();
            ResultSet rs = c.getMetaData().getTables(null, null, null, null);
            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);
                try {
                    ResultSet rs_select = s.executeQuery("SELECT * FROM " + table_name + ";");
                    System.out.println("ID\t Word\t\t Word_Type\t Status ");
                    String word = "", word_type = "", status = "";
                    int p_id;
                    while (rs_select.next()) {
                        p_id = rs_select.getInt("p_id");
                        word = rs_select.getString("word");
                        word_type = rs_select.getString("word_type");
                        status = rs_select.getString("status");
                        System.out.println(p_id + "\t " + word + " \t " + word_type + "\t " + status);
                    }
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
}

package org.knowlegde;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class defaultOperations {
    public static void main(String args[]) {
        Connection c = null;
        Statement s = null;
        List<String> listOfCodes = new ArrayList<>();
        Integer i = 0;
        do {
            String code_preview= "";
            listOfCodes.add("DROP TABLE IF EXISTS Samples;");
            listOfCodes.add("CREATE TABLE Samples" +
                    " (p_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " word TEXT NOT NULL," +
                            " word_type TEXT NOT NULL," +
                            " status TEXT)" + ";");
            listOfCodes.add("INSERT INTO Samples (p_id, word, word_type, status)" +
                            " VALUES" +
                            " (1,\"dog\",\"noun\",\"to learn\")," +
                            " (2,\"cat\",\"noun\",\"to learn\")," +
                            " (3,\"to run\",\"verb\",\"to learn\")," +
                            " (4,\"into\",\"preposition\",\"OK\")," +
                            " (5,\"wherever\",\"conjunction\",\"FAILED\");");
            listOfCodes.add("SELECT * FROM Samples;");
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:KnowledgeDB.db");
                System.out.println("Connected with KnowledgeDB.\n");
                s = c.createStatement();
                for(String sql_code : listOfCodes) {
                    code_preview = sql_code;
                    System.out.println("The following query is being executed : " + code_preview);
                    s.executeUpdate(sql_code);
                }
                i++;
                ResultSet rs = s.executeQuery("SELECT * FROM Samples;");
                System.out.println("ID\t Word\t\t Word_Type\t Status ");
                String word="", word_type="", status="";
                int p_id;
                while ( rs.next() ) {
                    p_id = rs.getInt("p_id");
                    word = rs.getString("word");
                    word_type = rs.getString("word_type");
                    status = rs.getString("status");
                    System.out.println(p_id+"\t "+word+" \t "+word_type+"\t "+status);
                }
                rs.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        while(listOfCodes.size()<=i);

    }
}

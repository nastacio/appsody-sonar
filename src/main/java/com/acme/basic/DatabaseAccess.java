package com.acme.basic;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    public static void acccessDb(String name) {

        try {
            /* Open connection with H2 database and use it */
            Class.forName("org.h2.Driver");
            String jdbcUrl = "jdbc:h2:file:" + new File(".").getAbsolutePath() + "/target/db";
            try (Connection con = DriverManager.getConnection(jdbcUrl)) {

                /* Sample A: Select data using Prepared Statement */
                String query = "select * from color where friendly_name = " + name;
                List<String> colors = new ArrayList<>();
                try (PreparedStatement pStatement = con.prepareStatement(query)) {
                    pStatement.setString(1, "yellow");
                    try (ResultSet rSet = pStatement.executeQuery()) {
                        while (rSet.next()) {
                            colors.add(rSet.getString(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import java.sql.*;
class MysqlCon{
    public static void main(String args[]) {
        try{
            Connection dbCxn=DriverManager.getConnection(
                "jdbc:mysql://localhost:53316/WhatAndWho","root","password"
            );
            Statement selectFromWhat=dbCxn.createStatement();
            ResultSet rsWhat=selectFromWhat.executeQuery("select * from What");
            while(rsWhat.next()) {
                System.out.println(rsWhat.getInt(1)
                    + " " + rsWhat.getString(2)
                    + " " + rsWhat.getInt(3)
                    + " " + rsWhat.getDate(4)
                    + " " + rsWhat.getString(5)
                );
            }
            dbCxn.close();;
        } catch(Exception e){ System.out.println(e);}
    }
}

import java.sql.*;
public class JSBCExample
{
    static final String URL = "jdbc:mysql://localhost";
    static String USER;
    static String PASS;
    String database;
    String table;
    Connection con = null;
    JSBCExample()
    {
        USER = "root";
        PASS = "Harsh";
        database = "STOCKS";
        table = "STOCK_NAME";
    }
    JSBCExample(String USER, String PASS,String database, String table)
    {
        JSBCExample.USER = USER;
        JSBCExample.PASS = PASS;
        this.database = database;
        this.table = table;
    }
    void createTable(String[] stockName)
    {
        try
        {
            con = DriverManager.getConnection(URL,USER,PASS);
            Statement st = con.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS "+database;
            st.executeUpdate(sql);
            sql = "USE "+database;
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS "+table+"(Name varchar(20))";
            st.executeUpdate(sql);
            for(String item : stockName)
            {
                sql = "INSERT INTO "+table+" VALUES('"+item+"');";
                st.executeUpdate(sql);
            }
            System.out.println("Table created successfully.....");
        }catch (SQLException se){se.printStackTrace();}
        finally
        {
            try
            {
                if(con != null)con.close();
            }catch (SQLException e){e.printStackTrace();}
        }
   }
   String[] getStockName(int numberOfStock)
   {
       String[] stockName = new String[numberOfStock];
       PreparedStatement p;
       ResultSet rs;
       try
       {
           con = DriverManager.getConnection(URL, USER, PASS);
           Statement st = con.createStatement();
           String sql = "USE "+database;
           st.executeUpdate(sql);
           sql = "select * from "+table;
           p = con.prepareStatement(sql);
           rs = p.executeQuery();
           int i = 0;
           while (rs.next())
                stockName[i++] = rs.getString("Name");
           sql = "DROP TABLE "+table;
           st.executeUpdate(sql);
       }catch(SQLException se){ se.printStackTrace(); }
       finally
       {
           try
           {
               if (con != null)
                   con.close();
           } catch (SQLException e){e.printStackTrace();}
       }
       return stockName;
   }
}

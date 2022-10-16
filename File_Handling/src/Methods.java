import java.io.*;
import java.util.*;

public class Methods {
    public String getDate()
    {
        String s="";
        String file = "C:\\Users\\Harsh\\Desktop\\EQ_ISINCODE_030222.csv";
        BufferedReader reader = null;
        String line;
        int i = 0;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if(i==0) i++;
                else
                    return row[15];
            }
        }catch(Exception ignored){}
        finally
        {
            try
            {
                assert reader != null;
                reader.close();
            }catch(IOException e){}
        }
        return s;
    }
    public String[] getStockList(String stockName, boolean first)
    {
        ArrayList<String> ar = new ArrayList<>();
        String file = "C:\\Users\\Harsh\\Desktop\\EQ_ISINCODE_030222.csv";
        BufferedReader reader = null;
        String line;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if(first && ar.size() == 0)ar.add(row[1].trim());
                if(row[1].trim().contains(stockName) && !row[1].equals("SC_NAME"))
                    ar.add(row[1].trim());
            }
        }catch(Exception ignored){}
        finally
        {
            try
            {
                assert reader != null;
                reader.close();
            }catch(IOException e){e.printStackTrace();}
        }
        return ar.toArray(String[]::new);
    }

    public String[] getStockDetails(String stockName)
    {
        ArrayList<String> ar = new ArrayList<>();
        String file = "C:\\Users\\Harsh\\Desktop\\EQ_ISINCODE_030222.csv";
        BufferedReader reader = null;
        String line;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if(stockName.equals(row[1].trim()))
                {
                    Collections.addAll(ar, row);
                    return ar.toArray(String[]::new);
                }
            }
        }catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                assert reader != null;
                reader.close();
            }catch(IOException ignored){}
        }
        return ar.toArray(String[]::new);
    }
}

import java.io.*;
public class ReadCSVFile
{
    String[] stockDetails(String[] stockName)
    {
        String []stockDetail = new String[]{"", "", "", "", "", "", "", "", "", "", ""};
        String file = "C:\\Users\\Harsh\\Desktop\\EQ_ISINCODE_030222.csv";
        BufferedReader reader = null;
        String line;
        try
        { int i = 0;
            for(String index : stockName)
            {
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null)
                {
                    String[] row = line.split(",");
                    if (i == 0)
                    {
                        for (String index1 : row)
                            stockDetail[i] += index1 + "\t";
                        i++;
                    }
                    if (row[1].trim().equals(index.trim()))
                    {
                        for (String index1 : row)
                            stockDetail[i] += index1 + "\t";
                        i++;
                    }
                }
            }
        }catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                assert reader != null;
                reader.close();
            }catch(IOException e){e.printStackTrace();}
        }
        return stockDetail;
    }
}


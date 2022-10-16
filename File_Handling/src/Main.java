import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of stocks:- ");
        int numberOfStock = sc.nextInt();

        //Taking stockName from user
        InputStockName stock = new InputStockName();
        String []stockName = stock.getStock(numberOfStock);

        //Creating table
        JSBCExample jd = new JSBCExample();
        jd.createTable(stockName);
        String []stock1 = jd.getStockName(numberOfStock);

        //Checking availability of stock
        ReadCSVFile csv = new ReadCSVFile();
        String []stockDetail = csv.stockDetails(stock1);

        for (String index : stockDetail) {
            System.out.printf("%s\n", index);
        }
    }
}

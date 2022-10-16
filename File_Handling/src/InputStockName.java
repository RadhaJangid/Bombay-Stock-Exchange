import java.util.*;
public class InputStockName {
    String[] getStock(int numberOfStock) {
        Scanner sc = new Scanner(System.in);
        String []stocks = new String[numberOfStock];
        System.out.println("Enter Stock name:- ");
        for (int i = 0; i < 10; i++)
            stocks[i] = sc.nextLine();
        return stocks;
    }
}

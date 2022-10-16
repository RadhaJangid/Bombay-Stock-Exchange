import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class EventsHandler implements KeyListener, MouseListener, ItemListener{
    StockExchange stockExchange;
    StockExchange exchange;
    EventsHandler(StockExchange sw) {
        this.stockExchange = sw;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == stockExchange.close || e.getSource() == stockExchange.exit)
            System.exit(0);
        if(e.getSource() == stockExchange.minimize)
            stockExchange.jFrame.setExtendedState(JFrame.ICONIFIED);
        if(e.getSource() == stockExchange.themeToggle || e.getSource() == stockExchange.themeBtnContainer)
        {
            if(stockExchange.darkTheme) {
                exchange = new StockExchange(false);
            }
            else {
                exchange = new StockExchange(true);
            }
            stockExchange.jFrame.setVisible(false);
        }
    }
    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == stockExchange.close)
            stockExchange.close.setBackground(Color.RED);
        if(e.getSource() == stockExchange.minimize)
            stockExchange.minimize.setBackground(Color.LIGHT_GRAY);
        if(e.getSource() == stockExchange.maximize)
            stockExchange.maximize.setBackground(Color.GRAY);
        if(e.getSource() == stockExchange.purchase)
        {
            if(stockExchange.darkTheme)
                stockExchange.purchase.setBackground(new Color(0, 155, 0));
            else
                stockExchange.purchase.setBackground(new Color(0, 230, 0));
            stockExchange.purchase.setBorder(BorderFactory.createLineBorder(stockExchange.fontColor,1));
        }
        if(e.getSource() == stockExchange.exit)
        {
            if(stockExchange.darkTheme)
                stockExchange.exit.setBackground(new Color(155, 0, 0));
            else
                stockExchange.exit.setBackground(new Color(255, 0, 0));
            stockExchange.exit.setBorder(BorderFactory.createLineBorder(stockExchange.fontColor,1));
        }
        if(e.getSource() == stockExchange.themeToggle)
            stockExchange.themeToggle.setBorder(BorderFactory.createLineBorder(stockExchange.fontColor,1));
        if(e.getSource() == stockExchange.themeBtnContainer || e.getSource() == stockExchange.themeToggle)
        {
            stockExchange.themeToggle.setBorder(BorderFactory.createEmptyBorder());
            if(stockExchange.darkTheme)
                stockExchange.themeToggle.setBounds(757,14,35,15);
            else
                stockExchange.themeToggle.setBounds(722,14,35,15);
        }
    }
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == stockExchange.close)
            stockExchange.close.setBackground(stockExchange.titleBarColor);
        if(e.getSource() == stockExchange.minimize)
            stockExchange.minimize.setBackground(stockExchange.titleBarColor);
        if(e.getSource() == stockExchange.maximize)
            stockExchange.maximize.setBackground(stockExchange.titleBarColor);
        if(e.getSource() == stockExchange.purchase)
            stockExchange.purchase.setBackground(stockExchange.titleBarColor);
        if(e.getSource() == stockExchange.exit)
            stockExchange.exit.setBackground(stockExchange.titleBarColor);
        if(e.getSource() == stockExchange.themeBtnContainer || e.getSource() == stockExchange.themeToggle)
        {
            stockExchange.themeToggle.setBorder(BorderFactory.createEmptyBorder());
            if(stockExchange.darkTheme)
                stockExchange.themeToggle.setBounds(760,16,30,11);
            else
                stockExchange.themeToggle.setBounds(724,16,30,11);
        }
    }
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == stockExchange.stockCount) {
            if (stockExchange.stockCount.getText().equals(" 0"))
                stockExchange.stockCount.setText(" ");
            else if (stockExchange.stockCount.getText().equals(" "))
                stockExchange.stockCount.setText(" 0");
        }
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == stockExchange.stockCount) {
            if (!stockExchange.stockDataField[4].getText().equals("") && !stockExchange.stockCount.getText().equals("")) {
                double num1 = Float.parseFloat(stockExchange.stockDataField[4].getText());
                double num2 = Float.parseFloat(stockExchange.stockCount.getText());
                num2 *= num1;
                stockExchange.totalAmount.setText("  ₹  " + num2);
            } else if (stockExchange.stockDataField[4].getText().equals("") || stockExchange.stockCount.getText().equals("")) {
                stockExchange.totalAmount.setText("  ₹  0.0");
            }
        }
        if (e.getSource() == stockExchange.userStockName.getEditor().getEditorComponent())
        {
            stockExchange.userStockName.showPopup();
            String name = ((JTextField) stockExchange.userStockName.getEditor().getEditorComponent()).getText();
            name = name.toUpperCase();

            if(!name.equals("SC_NAME") && !name.equals("") && ((e.getKeyChar()>='A' && e.getKeyChar()<='Z') || (e.getKeyChar()>='a' && e.getKeyChar()<='z') || e.getKeyChar() == '\b' || e.getKeyChar() == 127))
            {
                stockExchange.userStockName.removeAllItems();
                stockExchange.userStockName.addItem(name);
                String[] stockList = stockExchange.methods.getStockList(name,false);
                for (String s : stockList) stockExchange.userStockName.addItem(s);
                if(stockList.length<11)
                    stockExchange.userStockName.setMaximumRowCount(stockList.length+1);
                else
                    stockExchange.userStockName.setMaximumRowCount(10);
                stockExchange.userStockName.showPopup();
            }else if(name.equals(""))
            {
                stockExchange.userStockName.removeAllItems();
                stockExchange.userStockName.addItem("");
                String[] stockList = stockExchange.methods.getStockList("",false);
                for (String s : stockList) stockExchange.userStockName.addItem(s);
                stockExchange.userStockName.setMaximumRowCount(9);
                stockExchange.userStockName.showPopup();
            }
            if(e.getKeyChar() == '\n')
            {
                stockExchange.stockCount.setEditable(true);
                stockExchange.userStockName.hidePopup();
                String[] stockDetails = stockExchange.methods.getStockDetails(name);
                for ( int i = 0; i < 15; i++)
                    stockExchange.stockDataField[i].setText(stockDetails[i]);
            }
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            if(!Objects.requireNonNull(stockExchange.userStockName.getSelectedItem()).toString().equals("SC_NAME")) {
                stockExchange.stockCount.setEditable(true);
                stockExchange.userStockName.hidePopup();
                String name = ((JTextField) stockExchange.userStockName.getEditor().getEditorComponent()).getText();
                String[] stockDetails = stockExchange.methods.getStockDetails(name);
                if (stockDetails.length != 0) {
                    for (int i = 0; i < 15; i++)
                        stockExchange.stockDataField[i].setText(stockDetails[i]);
                }
            }
        }
    }
}

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class StockExchange {
    Font font, font1, labelFont;
    Color bgColor,lineColor, fieldColor, fontColor, titleBarColor, themeSelector;
    Methods methods;
    EventsHandler eventsHandler;
    JFrame jFrame;
    JPanel content, titleBar;
    JButton close, minimize, maximize, purchase, exit, themeToggle;
    JLabel titleBarIcon, titleBarText, userStock, totalPrice, stockNumber, theme, themeBtnContainer,curDate;
    JTextField totalAmount,stockCount, tDate;
    JComboBox<String> userStockName;
    JLabel []rowTitles = new JLabel[4];
    JLabel []titleField = new JLabel[15];
    JTextField []line = new JTextField[8];
    JTextField []stockDataField = new JTextField[15];
    boolean darkTheme;

    StockExchange(boolean darkTheme)
    {
        this.darkTheme = darkTheme;
        font = new Font("Arial",Font.BOLD,16);
        font1 = new Font("Arial",Font.PLAIN,16);
        labelFont = new Font("Book Antiqua",Font.BOLD,19);
        if(darkTheme) {
            bgColor = new Color(35, 48, 57);
            lineColor = new Color(110, 110, 170);
            titleBarColor = new Color(15, 30, 40);
            themeSelector = Color.GREEN;
            fontColor = Color.WHITE;
            fieldColor = Color.BLACK;
        }
        else
        {
            bgColor = new Color(205, 218, 255);
            lineColor = new Color(110, 210, 120);
            titleBarColor = new Color(255, 255,255);
            themeSelector = new Color(3, 46, 72);
            fontColor = new Color(0, 0, 0);
            fieldColor = new Color(249, 252, 255);
        }
        methods = new Methods();
        eventsHandler = new EventsHandler(this);
        String []row_Titles = {"Stock Name Info","Stock Price Info","Stock Count","Total Amount"};
        String []titleList = {"SC_CODE", "SC_NAME", "SC_GROUP", "SC_TYPE",
                "OPEN", "HIGH", "LOW", "CLOSE", "LAST", "PREVCLOSE",
                "NO_TRADES", "NO_OF_SHRS", "NET_TURNOV", "TDCLOINDI", "ISIN_CODE"};

        //Frame
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setBounds(400,100,820,600);
        jFrame.setTitle("Bombay Stock Exchange");
        jFrame.setIconImage(new ImageIcon("C:\\Users\\Harsh\\Desktop\\Harsh\\icons8-weight-kg-96.png").getImage());
        jFrame.getContentPane().setBackground(bgColor);
        jFrame.setLayout(null);
        jFrame.setUndecorated(true);

        //TitleBar
        titleBar = new JPanel();
        titleBar.setBounds(0,0,820,30);
        titleBar.setBackground(titleBarColor);
        titleBar.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        titleBar.setLayout(null);
        jFrame.add(titleBar);

        titleBarIcon = new JLabel();
        titleBarIcon.setBounds(0,0,30,30);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\Harsh\\Desktop\\Harsh\\icons8-weight-kg-96.png"));
        } catch (IOException e) { e.printStackTrace(); }
        assert img != null;
        Image d_img = img.getScaledInstance(titleBarIcon.getWidth(), titleBarIcon.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(d_img);
        titleBarIcon.setIcon(icon);
        titleBar.add(titleBarIcon);

        titleBarText = new JLabel("Bombay Stock Exchange");
        titleBarText.setBounds(35,0,400,30);
        titleBarText.setForeground(fontColor);
        titleBarText.setFont(labelFont);
        titleBar.add(titleBarText);

        //TitleBar Close Button
        close = new JButton("X");
        close.setBounds(789,1,30,28);
        close.setBackground(titleBarColor);
        close.setForeground(fontColor);
        close.setFocusable(false);
        close.setBorder(BorderFactory.createEmptyBorder());
        close.addMouseListener(eventsHandler);
        titleBar.add(close);

        //TitleBar Minimize Button
        minimize = new JButton("-");
        minimize.setBounds(729,1,30,28);
        minimize.setFont(new Font("Arial",Font.BOLD,30));
        minimize.setBackground(titleBarColor);
        minimize.setForeground(fontColor);
        minimize.setFocusable(false);
        minimize.setBorder(BorderFactory.createEmptyBorder());
        minimize.addMouseListener(eventsHandler);
        titleBar.add(minimize);

        //TitleBar Minimize Button
        maximize = new JButton("[]");
        maximize.setBounds(759,1,30,28);
        maximize.setFont(new Font("Arial",Font.BOLD,17));
        maximize.setBackground(titleBarColor);
        maximize.setForeground(fontColor);
        maximize.setFocusable(false);
        maximize.setBorder(BorderFactory.createEmptyBorder());
        maximize.addMouseListener(eventsHandler);
        maximize.setEnabled(false);
        titleBar.add(maximize);

        //Body or Content
        content = new JPanel();
        content.setBounds(0,29,820,571);
        content.setLayout(null);
        content.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        content.setBackground(bgColor);
        jFrame.add(content);

        //Date
        curDate = new JLabel("Date");
        curDate.setBounds(10,10,180,20);
        curDate.setFont(labelFont);
        curDate.setForeground(fontColor);
        curDate.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(curDate);

        tDate = new JTextField(new Methods().getDate());
        tDate.setBounds(10,30,180,30);
        tDate.setFont(font1);
        tDate.setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
        tDate.setOpaque(false);
        tDate.setForeground(fontColor);
        tDate.setEditable(false);
        tDate.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(tDate);

        //Label
        userStock = new JLabel("Select Holding Name");
        userStock.setBounds(220,10,200,20);
        userStock.setFont(labelFont);
        userStock.setForeground(fontColor);
        content.add(userStock);

        //Taking Input Stock Name
        String []stockList = methods.getStockList("",true);
        userStockName = new JComboBox<>(stockList);
        userStockName.setBounds(420,5,200,35);
        userStockName.setEditable(true);
        ((JTextField)(userStockName.getEditor().getEditorComponent())).setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        userStockName.setFont(new Font("Arial",Font.BOLD,13));
        userStockName.getEditor().getEditorComponent().setForeground(fontColor);
        if(darkTheme)
        {
            userStockName.getEditor().getEditorComponent().setBackground(titleBarColor);
            userStockName.setBackground(titleBarColor);
        }
        else
        {
            userStockName.getEditor().getEditorComponent().setBackground(fieldColor);
            userStockName.setBackground(fieldColor);
        }
        userStockName.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        userStockName.setRenderer(new CustomComboBoxRenderer());
        userStockName.getEditor().getEditorComponent().addKeyListener(eventsHandler);
        userStockName.addItemListener(eventsHandler);
        content.add(userStockName, BorderLayout.NORTH);

        //Theme
        theme = new JLabel("Dark Mode");
        theme.setBounds(650,5,100,35);
        theme.setForeground(fontColor);
        content.add(theme);

        themeToggle = new JButton();
        themeToggle.setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
        themeToggle.addMouseListener(eventsHandler);
        if(darkTheme) {
            themeToggle.setBackground(Color.GREEN);
            themeToggle.setBounds(760,16,30,11);
        }
        else
        {
            themeToggle.setBackground(themeSelector);
            themeToggle.setBounds(724,16,30,11);
        }
        content.add(themeToggle);

        themeBtnContainer = new JLabel();
        themeBtnContainer.setBounds(720,12,74,19);
        themeBtnContainer.setOpaque(true);
        themeBtnContainer.setBackground(titleBarColor);
        themeBtnContainer.addMouseListener(eventsHandler);
        content.add(themeBtnContainer);


        int y = 50;
        int x = 50;
        //Rows Line and Titles
        int []yLine = {y+10,y+130,y+260,y+390};
        for (int i = 0; i < 4; i++)
        {
            line[i] = new JTextField();
            line[i].setBounds(x,yLine[i]+9,260,4);
            line[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
            line[i].setBackground(lineColor);
            line[i].setEditable(false);
            content.add(line[i]);

            rowTitles[i] = new JLabel(row_Titles[i]);
            rowTitles[i].setBounds(310,yLine[i],180,20);
            rowTitles[i].setFont(labelFont);
            rowTitles[i].setForeground(fontColor);
            rowTitles[i].setHorizontalAlignment(SwingConstants.CENTER);
            content.add(rowTitles[i]);

            line[i*2+1] = new JTextField();
            line[i*2+1].setBounds(490,yLine[i]+9,260,4);
            line[i*2+1].setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
            line[i*2+1].setBackground(lineColor);
            line[i*2+1].setEditable(false);
            content.add(line[i*2+1]);
        }

        y+=10;
        int []width = {150,250,100,100};
        int []X = {50,250,530,650};
        for (int i =0; i < 4; i++)
        {
            //For Title
            titleField[i] = new JLabel(titleList[i]);
            titleField[i].setBounds(X[i],y+30,width[i],20);
            titleField[i].setFont(font);
            titleField[i].setForeground(fontColor);
            titleField[i].setHorizontalAlignment(SwingConstants.CENTER);

            //For Data
            stockDataField[i] = new JTextField("");
            stockDataField[i].setBounds(X[i],y + 60, width[i],30);
            stockDataField[i].setFont(font1);
            stockDataField[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
            stockDataField[i].setBackground(fieldColor);
            stockDataField[i].setForeground(fontColor);
            stockDataField[i].setEditable(false);
            stockDataField[i].setHorizontalAlignment(SwingConstants.CENTER);

            //Adding to frame
            content.add(titleField[i]);
            content.add(stockDataField[i]);
        }

        //Row -> 2
        y-=10;
        for (int i = 4; i < 10; i++)
        {
            //For Title
            titleField[i] = new JLabel(titleList[i]);
            titleField[i].setBounds(x,y + 160,100,20);
            titleField[i].setFont(font);
            titleField[i].setForeground(fontColor);
            titleField[i].setHorizontalAlignment(SwingConstants.CENTER);

            //For textField
            stockDataField[i] = new JTextField("");
            stockDataField[i].setBounds(x,y + 190,100,30);
            stockDataField[i].setFont(font1);
            stockDataField[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
            stockDataField[i].setBackground(fieldColor);
            stockDataField[i].setForeground(fontColor);
            stockDataField[i].setEditable(false);
            stockDataField[i].setHorizontalAlignment(SwingConstants.CENTER);

            //Adding to frame
            content.add(titleField[i]);
            content.add(stockDataField[i]);
            x += 120;
        }

        //Row -> 3
        x=50;
        for (int i = 10; i < 15; i++)
        {
            //For Title
            titleField[i] = new JLabel(titleList[i]);
            titleField[i].setBounds(x,y + 290,120,20);
            titleField[i].setFont(font);
            titleField[i].setForeground(fontColor);
            titleField[i].setHorizontalAlignment(SwingConstants.CENTER);

            //For Data
            stockDataField[i] = new JTextField("");
            stockDataField[i].setBounds(x,y + 320,120,30);
            stockDataField[i].setFont(font1);
            stockDataField[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
            stockDataField[i].setBackground(fieldColor);
            stockDataField[i].setForeground(fontColor);
            stockDataField[i].setEditable(false);
            stockDataField[i].setHorizontalAlignment(SwingConstants.CENTER);

            //Adding to frame
            content.add(titleField[i]);
            content.add(stockDataField[i]);
            x += 144;
        }

        //Row -> 4
        y+=30;
        stockNumber = new JLabel("Enter Number Of Shares");
        stockNumber.setBounds(50,y+400,250,20);
        stockNumber.setFont(labelFont);
        stockNumber.setForeground(fontColor);
        content.add(stockNumber);

        totalPrice = new JLabel("Total Amount");
        totalPrice.setBounds(50,y+435,250,20);
        totalPrice.setFont(labelFont);
        totalPrice.setForeground(fontColor);
        content.add(totalPrice);

        stockCount = new JTextField(" 0");
        stockCount.setBounds(280,y+395,140,30);
        stockCount.setFont(font1);
        stockCount.setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
        stockCount.setBackground(fieldColor);
        stockCount.setForeground(fontColor);
        stockCount.setEditable(false);
        stockCount.addKeyListener(eventsHandler);
        content.add(stockCount);

        totalAmount = new JTextField("  ₹  0.0");
        totalAmount.setBounds(200,y+430,220,30);
        totalAmount.setFont(font1);
        totalAmount.setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
        totalAmount.setBackground(fieldColor);
        totalAmount.setForeground(fontColor);
        totalAmount.setEditable(false);
        content.add(totalAmount);

        purchase = new JButton("PURCHASE");
        purchase.setFont(labelFont);
        purchase.setBackground(titleBarColor);
        purchase.setForeground(fontColor);
        purchase.setBounds(450,y+420,150,40);
        purchase.addMouseListener(eventsHandler);
        content.add(purchase);

        exit = new JButton("EXIT");
        exit.setFont(labelFont);
        exit.setBackground(titleBarColor);
        exit.setForeground(fontColor);
        exit.setBounds(600,y+420,150,40);
        exit.addMouseListener(eventsHandler);
        content.add(exit);
        jFrame.setVisible(true);
    }
    public static void main(String[] args) {new StockExchange(false);}
}

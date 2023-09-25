package view;

import controller.ProjectPath;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class GUI extends JFrame {

    /**
     * add all buttons and their "Titles" since all buttons will have an icon and a title (day)
     */
    public static myDesktopPane d;
    public static ArrayList<String> labelIcons;
    JPanel[] yellow;
    public JLabel[] position;
    public static JPanel general;

    protected static JLabel titleInfo;
    public static JButton deal;
    public static JButton mail;
    JLabel mailLabel;
    JLabel dealLabel;
    /*player 1*/
    public static JPanel p1;
    public static JLabel p1_pawn;
    public static JButton[] buttons1;
    public static JLabel dicelabel1;
    /*player 2*/
    public static JPanel p2;
    public static JLabel p2_pawn;
    public static JButton[] buttons2;
    public static JLabel dicelabel2;
    /*one declaration for both players*/
    private static JPanel behindInfo1;
    private static JPanel behindInfo2;
    private static JPanel playerInfo1;
    private static JPanel playerInfo2;
    private static final String empty = "                                                                                      ";
    //need 4 labels for the layout
    private static JLabel playerlabel1;
    private static JLabel playerlabel2;
    public static JLabel money1;
    public static JLabel money2;
    static JLabel loan1;
    static JLabel loan2;
    static JLabel bill1;
    static JLabel bill2;
    static JLabel jStr;
    public static JLabel durationLabel;
    public static JLabel turnLabel;
    public static JLabel whatToDoLabel;

    /**
     * Helping method to scale an image
     * @param icon the icon to be scaled
     * @param label the label that needs the icon
     * @return the scaled icon
     */
    public static ImageIcon scaledImage(ImageIcon icon, JLabel label) {
        Image tmp = icon.getImage();
        Image tmpScale = tmp.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(tmpScale);
        return scaledIcon;
    }

    /**
     * Initializes players' pawns
     * @param num the player's id
     * @return the pawn
     */
    private static JLabel initializedPawn(int num) {
        JLabel pawn = new JLabel();
        ImageIcon pawnIcon = null;
        switch (num) {
            case 1:
                pawnIcon = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Player/pawn_yellow.png");
                pawn.setBounds(0, 210, 80, 100);
                break;
            case 2:
                System.out.println(ProjectPath.initBasePath());
                pawnIcon = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Player/pawn_blue.png");
                pawn.setBounds(30, 210, 80, 100);
                break;
        }
        assert (pawnIcon != null);
        pawn.setIcon(scaledImage(pawnIcon, pawn));
        return pawn;
    }

    /**
     * Adjusts an icon for the position's bounds
     * @param pos the position on the board(as a jlabel)
     * @param path the path for the icon
     * @return the jlabel
     */
    private static JLabel adjustedLabel(JLabel pos, String path) {
        ImageIcon posIcon = new ImageIcon(path);
        pos.setIcon(scaledImage(posIcon, pos));
        return pos;
    }

    /**
     * Initializes info box for the game
     */
    public void infoBox() {
        general = new JPanel();
        JPanel generalBehind = new JPanel();
        generalBehind.setBackground(Color.BLACK);
        general.setLayout(new FlowLayout(FlowLayout.LEFT));
        titleInfo = new JLabel("Info Box" + "                                                               ");
        titleInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        durationLabel = new JLabel();
        durationLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        turnLabel = new JLabel();
        turnLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        whatToDoLabel = new JLabel();
        whatToDoLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        general.add(titleInfo);
        general.add(durationLabel);
        general.add(turnLabel);
        general.add(whatToDoLabel);
        generalBehind.setBounds(875, 345, 410, 130);
        general.setBounds(880, 350, 400, 120);
        d.add(generalBehind);
        d.add(general, Integer.valueOf(2));

    }

    /**
     * Adds jackpot label
     * @param d the desktopane the label will be added to
     * @param mon the jackpot money
     */
    public static void addJackpot(myDesktopPane d, int mon) {
        JLabel jck = new JLabel();
        jStr = new JLabel("Jackpot: " + mon + " Euros");
        jck.setBounds(550, 770, 240, 100);
        jck = adjustedLabel(jck, ProjectPath.initBasePath() + "/src/model/Positions/jackpot.png");
        jStr.setForeground(Color.WHITE);
        jStr.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        d.add(jStr);
        jStr.setBounds(560, 870, 300, 50);
        d.add(jck);
    }

    /**
     * Initializes players' info boxes
     * @param num players' ids
     * @param d the desktopane the panels will be added to
     * @return the jpanel for each player
     */
    public static JPanel addInfoPlayer(int num, myDesktopPane d) {
        String dicelabel_path = ProjectPath.initBasePath() + "/src/model/Positions/dice-1.jpg";
        //-------BUTTONS ON INFO BOXES------------
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        String[] button_labels = new String[]{"Roll Die", "Deal Cards", "Get Loan", "End Turn"};
        if (num == 1) {
            behindInfo1 = new JPanel();
            playerInfo1 = new JPanel();
            playerInfo1.setLayout(layout);
            playerlabel1 = new JLabel();
            playerlabel1.setFont(new Font("Italic", Font.BOLD, 15));
            playerlabel1.setText("Player " + num + "                                                 ");
            playerlabel1.setForeground(Color.DARK_GRAY);
            dicelabel1 = new JLabel();
            money1 = new JLabel();
            money1.setFont(new Font("Italic", Font.PLAIN, 15));
            bill1 = new JLabel();
            bill1.setFont(new Font("Italic", Font.PLAIN, 15));
            loan1 = new JLabel();
            loan1.setFont(new Font("Italic", Font.PLAIN, 15));
            money1.setText("Money:              3500 Euros");
            loan1.setText("Loan:                0 Euros");
            bill1.setText("Bills:               0 Euros");
            dicelabel1.setBounds(300, 100, 75, 75);
            buttons1 = new JButton[4];
            for (int i = 0; i < 4; i++) {
                buttons1[i] = new JButton(button_labels[i]);
                buttons1[i].setName(button_labels[i]);
            }
        } else {
            behindInfo2 = new JPanel();
            playerInfo2 = new JPanel();
            playerInfo2.setLayout(layout);
            playerlabel2 = new JLabel();
            playerlabel2.setFont(new Font("Italic", Font.BOLD, 15));
            playerlabel2.setText("Player " + num + "                                                 ");
            playerlabel2.setForeground(Color.DARK_GRAY);
            dicelabel2 = new JLabel();
            money2 = new JLabel();
            money2.setFont(new Font("Italic", Font.PLAIN, 15));
            bill2 = new JLabel();
            bill2.setFont(new Font("Italic", Font.PLAIN, 15));
            loan2 = new JLabel();
            loan2.setFont(new Font("Italic", Font.PLAIN, 15));
            money2.setText("Money:           3500 Euros");
            loan2.setText("Loan:             0 Euros");
            bill2.setText("Bills:            0 Euros");
            dicelabel2.setBounds(300, 100, 75, 75);
            buttons2 = new JButton[4];
            for (int i = 0; i < 4; i++) {
                buttons2[i] = new JButton(button_labels[i]);
                buttons2[i].setName(button_labels[i]);
            }

        }

        switch (num) {
            case 1:
                behindInfo1.setBounds(875, 0, 410, 310);
                behindInfo1.setBackground(Color.yellow);
                d.add(behindInfo1);
                playerInfo1.setBounds(880, 5, 400, 300);
                dicelabel1.setIcon(scaledImage(new ImageIcon(dicelabel_path), dicelabel1));
                playerInfo1.add(playerlabel1);
                playerInfo1.add(new JLabel(empty));
                playerInfo1.add(money1);
                playerInfo1.add(new JLabel(empty));
                playerInfo1.add(loan1);
                playerInfo1.add(new JLabel(empty));
                playerInfo1.add(bill1);
                playerInfo1.add(new JLabel(empty + "      "));
                for (JButton b : buttons1) {
                    playerInfo1.add(b);
                    b.addActionListener(new MyButtonListener());
                }
                playerInfo1.add(dicelabel1);
                p1_pawn = new JLabel();
                p1_pawn = initializedPawn(1);
                break;
            case 2:
                behindInfo2.setBounds(875, 595, 410, 310);
                behindInfo2.setBackground(Color.blue);
                d.add(behindInfo2);
                playerInfo2.setBounds(880, 600, 400, 300);
                dicelabel2.setIcon(scaledImage(new ImageIcon(dicelabel_path), dicelabel2));
                playerInfo2.add(playerlabel2);
                playerInfo2.add(new JLabel(empty));
                playerInfo2.add(money2);
                playerInfo2.add(new JLabel(empty));
                playerInfo2.add(loan2);
                playerInfo2.add(new JLabel(empty));
                playerInfo2.add(bill2);
                playerInfo2.add(new JLabel(empty + "      "));
                for (JButton b : buttons2) {
                    playerInfo2.add(b);
                    b.addActionListener(new MyButtonListener());
                }
                playerInfo2.add(dicelabel2);
                p2_pawn = new JLabel();
                p2_pawn = initializedPawn(2);
                break;
        }

        if (num == 1)
            return playerInfo1;

        return playerInfo2;
    }

    /**
     * Constructor that puts everything together
     */
    public GUI() {
        d = new myDesktopPane();
        d.setBounds(0, 0, 1300, 950);
        initComponents();
        //------PAWN 1 --------------------
        d.add(p1_pawn, Integer.valueOf(2));
        //--------PAWN 2--------------------
        d.add(p2_pawn, Integer.valueOf(1));
        //-----------GAME FRAME-----------------
        new JFrame();
        setTitle("PayDay Game");
        setBounds(0, 0, 1300, 950);
        getContentPane().setBackground(new Color(0x006400));
        add(d);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Initializes needed components
     */
    private void initComponents() {
        //------INITIALIZATION OF HELPING TYPES & ARRAYS-------------
        //-------POSITION ICONS-------------
        labelIcons = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/sweep.png");
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/radio.png");
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/casino.png");
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/yard.png");
        }

        for (int i = 0; i < 3; i++)
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/lottery.png");

        for (int i = 0; i < 4; i++) {
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/mc1.png");
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/mc2.png");
        }

        for (int i = 0; i < 5; i++)
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/deal.png");

        for (int i = 0; i < 6; i++)
            labelIcons.add(ProjectPath.initBasePath() + "/src/model/Positions/buyer.png");
        //----ICON TITLES-------------------
        String[] labelTitles = new String[]{"Start", "Mon 1", "Tue 2", "Wed 3", "Thu 4", "Fri 5", "Sat 6", "Sun 7", "Mon 8", "Tue 9", "Wed 10", "Thu 11", "Fri 12", "Sat 13", "Sun 14", "Mon 15", "Tue 16", "Wed 17", "Thu 18", "Fri 19", "Sat 20", "Sun 21", "Mon 22", "Tue 23", "Wed 24", "Thu 25", "Fri 26", "Sat 27", "Sun 28", "Mon 29", "Tue 30", "Wed 31", "Jackpot"};
        //----RANDOMISE POSITIONS ON BOARD------
        Collections.shuffle(labelIcons);
        //----YELLOW JPANELS--------------------
        yellow = new JPanel[32];
        //----POSITION JLABELS--------------------
        position = new JLabel[32];
        //----INITIALIZE & ADD YELLOW LABELS + POSITIONS TO PANELS + PLAYERS' PAWNS-----
        int x1 = 0, x0 = 0, y1, y0;
        for (int i = 0; i < 32; i++) {
            yellow[i] = new JPanel();
            JLabel _yellow = new JLabel(labelTitles[i]);
            _yellow.setFont(new Font("Serif", Font.BOLD, 15));
            _yellow.setForeground(Color.DARK_GRAY);
            position[i] = new JLabel();
            if (i < 7) {         //first row
                y1 = 310;
                y0 = 180;
                position[i].setBounds(x0, y0, 120, 150);
                switch (i) {
                    case 0:
                        position[i] = adjustedLabel(position[i], ProjectPath.initBasePath() + "/src/model/Positions/start.png");
                        break;
                    default:
                        position[i] = adjustedLabel(position[i], labelIcons.get(i - 1));
                        break;
                }
                yellow[i].setBounds(x1, y1, 120, 25);
                x1 += 120;
                x0 += 120;
                if (i == 6) {
                    x1 = 0;
                    x0 = 0;
                }
            } else if (i < 14) { //second row
                y1 = 450;
                y0 = 320;
                position[i].setBounds(x0, y0, 120, 150);
                position[i] = adjustedLabel(position[i], labelIcons.get(i - 1));
                yellow[i].setBounds(x1, y1, 120, 25);
                x1 += 120;
                x0 += 120;
                if (i == 13) {
                    x1 = 0;
                    x0 = 0;
                }
            } else if (i < 21) { //third row
                y1 = 590;
                y0 = 460;
                yellow[i].setBounds(x1, y1, 120, 25);
                position[i].setBounds(x0, y0, 120, 150);
                position[i] = adjustedLabel(position[i], labelIcons.get(i - 1));
                x1 += 120;
                x0 += 120;
                if (i == 20) {
                    x1 = 0;
                    x0 = 0;
                }
            } else if (i < 28) { //fourth row
                y1 = 730;
                y0 = 600;
                yellow[i].setBounds(x1, y1, 120, 25);
                position[i].setBounds(x0, y0, 120, 150);
                position[i] = adjustedLabel(position[i], labelIcons.get(i - 1));
                x1 += 120;
                x0 += 120;
                if (i == 27) {
                    x1 = 0;
                    x0 = 0;
                }
            } else {            //fifth row
                y1 = 870;
                y0 = 740;
                position[i].setBounds(x0, y0, 120, 150);
                switch (i) {
                    case 31:
                        position[i] = adjustedLabel(position[i], ProjectPath.initBasePath() + "/src/model/Positions/pay.png");
                        break;
                    default:
                        position[i] = adjustedLabel(position[i], labelIcons.get(i - 2));
                        break;
                }
                yellow[i].setBounds(x1, y1, 120, 25);
                x1 += 120;
                x0 += 120;
            }
            yellow[i].setBackground(Color.yellow);
            yellow[i].add(_yellow);
        }
        //--------LOGO----------------------
        JLabel logo = new JLabel();
        logo.setBounds(0, 0, 840, 180);
        ImageIcon logo_icon = new ImageIcon(ProjectPath.initBasePath() + "/src/view/logo.png");
        logo.setIcon(scaledImage(logo_icon, logo));
        d.add(logo, Integer.valueOf(5));
        //--------PLAYER 1 & 2 INFO BOXES----------
        p2 = addInfoPlayer(2, d);
        p1 = addInfoPlayer(1, d);

        //-------ADD LABELS, POSITIONS & INFO BOXES ON BOARD---
        d.add(p1, Integer.valueOf(2));
        d.add(p2, Integer.valueOf(2));
        for (JPanel yel : yellow)
            d.add(yel);
        for (JLabel jpos : position)
            d.add(jpos);
        addJackpot(d, 0);
        //-----ADD GENERAL INFO BOX------
        infoBox();
        //----ADD MAIL & DEAL CARD BUTTONS-----
        ImageIcon mailIcon = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Cards/mailCard.png");
        ImageIcon dealIcon = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Cards/dealCard.png");
        mail = new JButton();
        mail.setName("Mail");
        mail.addActionListener(new MyButtonListener());
        deal = new JButton();
        deal.setName("Deal");
        deal.addActionListener(new MyButtonListener());
        mailLabel = new JLabel();
        dealLabel = new JLabel();
        dealLabel.setBounds(1090, 490, 190, 100);
        mailLabel.setBounds(880, 490, 190, 100);
        mail.setBounds(880, 490, 190, 100);
        mail.setIcon(scaledImage(mailIcon, mailLabel));
        deal.setBounds(1090, 490, 190, 100);
        deal.setIcon(scaledImage(dealIcon, dealLabel));
        d.add(mail);
        d.add(deal);
    }
}

/**
 * My JLayeredPane to add all components on
 */
class myDesktopPane extends JLayeredPane {
    private final Image backImage = null;

    public myDesktopPane() {
    }

    /**
     * @param g graphics used to put the image in the background
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImage, 0, 0, this);
    }
}
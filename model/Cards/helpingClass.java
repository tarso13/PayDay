package model.Cards;

import controller.ProjectPath;
import model.Board.Board;
import model.Cards.paydaycards.PayDayCards;
import model.Positions.Deal;
import model.Positions.Mail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class helpingClass {
    static String[][] mailCards = new String[48][4];
    static String[][] dealCards = new String[20][8];

    /**
     * Reads soecific file and sets mail & deal cards for the game
     *
     * @param path the path for the file
     * @param type type of the card
     */
    public static void readFile(String path, String type) {
        BufferedReader br = null;
        String sCurrentLine;
        ArrayList<MailCard> temp = new ArrayList();
        try {
            String fullPath = path;
            br = new BufferedReader(new FileReader(fullPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PayDayCards.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;

        HashMap<Integer, String> domainsMap = new HashMap<>();
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    mailCards[count] = sCurrentLine.split(",");
                    Board.mailCards.push(new MailCard(mailCards[count][0], mailCards[count][2], Integer.parseInt(mailCards[count][4])));
                    count++;
                } else {
                    dealCards[count] = sCurrentLine.split(",");
                    Board.dealCards.push(new DealCard(dealCards[count][2], Integer.parseInt(dealCards[count][3]), Integer.parseInt(dealCards[count][4]), dealCards[count][5]));
                    count++;
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(helpingClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Shows mailcard with number i
     *
     * @param i the "index" of the card
     */
    public static void showMailCard(int i) {
        JButton button = new JButton(mailCards[i][3]);
        JButton[] buttons = new JButton[]{
                button
        };
        JFrame frame = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Cards/resources/images/" + mailCards[i][5]).getImage();
        image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        int n = JOptionPane.showOptionDialog(frame,
                mailCards[i][2],
                mailCards[i][0],
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                buttons,
                buttons[0]);
    }

    /**
     * Shows dealcard with number i
     *
     * @param i the "index" of the card
     */
    public static void showDealCard(int i) {
        JButton opt1 = new JButton(dealCards[i][6]);
        JButton opt2 = new JButton(dealCards[i][7]);
        JButton[] options = {opt1, opt2};
        JFrame frame = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Cards/resources/images/" + dealCards[i][5]).getImage();
        image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        opt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deal.wants_the_card = true;
                frame.setVisible(false);
                frame.dispose();
            }
        });
        int n = JOptionPane.showOptionDialog(frame,
                dealCards[i][2] + "\nΤιμή Αγοράς: " + dealCards[i][3] + " Ευρώ \nΤιμή Πώλησης: " + dealCards[i][4] + " Ευρώ \n",
                dealCards[i][0],
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
    }
}

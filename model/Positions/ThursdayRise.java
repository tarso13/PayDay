package model.Positions;

import controller.ProjectPath;
import model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Invariant: number of positions on the board
 */
public class ThursdayRise extends Position {
    public ThursdayRise(Player p, Player p1) {
        performActionPos(p, p1);
    }

    /**
     * helping method to scale an icon
     *  pre: player's position == thursday
     *  post: get a scaled icon
     * @param icon the icon to be scaled
     * @return the scaled icon
     */
    private static ImageIcon scaledImage(ImageIcon icon) {
        JLabel label = new JLabel();
        label.setBounds(0, 0, 200, 180);
        Image tmp = icon.getImage();
        Image tmpScale = tmp.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(tmpScale);
        return scaledIcon;
    }

    /**
     * Checks the player's choice for the bet
     * pre: player's position == thursday
     * post: none
     *
     * @param option user's option
     * @return true if the player wants to, else return false
     */
    public static boolean wantsToBet(int option) {
        return option == 1;
    }

    /**
     * Checks if the player wants to bet for the football game
     * pre: player's position == thursday
     * post: none
     *
     * @param p the player
     * @return true if the player wants to, else return false
     */
    private static void showMessageBet(Player p) {
        final int[] user_option = new int[1];
        JFrame frame = new JFrame();
        frame.setResizable(false);
        JButton crypto_yes = new JButton("Πόνταρε στo κρυπτονόμισμα");
        JButton crypto_no = new JButton("Παράβλεψε το ποντάρισμα");
        ImageIcon image = scaledImage(new ImageIcon(ProjectPath.initBasePath() + "/src/model/Positions/crypto.jpeg"));
        crypto_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_option[0] = 1;
                p.reduceMoney(300);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        crypto_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_option[0] = 2;
                frame.setVisible(false);
                frame.dispose();
            }
        });

        JButton[] buttons = new JButton[]{
                crypto_yes, crypto_no
        };
        JOptionPane.showOptionDialog(frame,
                "Ποντάρισμα σε κρυπτονομίσματα", "Crypto Thursday",
                JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                image,
                buttons, buttons[0]);

        if (!wantsToBet(user_option[0]))
            return;

        int result = (new Random()).nextInt(6) + 1; //Rolling the die
        boolean a = result == 1 || result == 2; //lose money
        boolean b = result == 3 || result == 4; //take it back
        boolean c = result == 5 || result == 6; //win 2x money bet

        if (a) {
            JButton[] won = new JButton[]{new JButton("Έχασες 300 ευρώ")};
            won[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //do nothing money is lost
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            JOptionPane.showOptionDialog(frame,
                    "Υπήρξε πτώση στην αξία του κρυπτονομίσματος", "Crypto Thursday",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    image, won, won[0]);
        }
        if (b) {
            JButton[] tie = new JButton[]{new JButton("Πάρε πίσω τα χρήματα σου")};
            tie[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.upgradeMoney(300);
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            JOptionPane.showOptionDialog(frame,
                    "Σταθεροποιήθηκε η αξία του κρυπτονομίσματος !", "Crypto Thursday",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    image, tie, tie[0]);
        }

        if (c) {
            JButton[] won = new JButton[]{new JButton("Κέρδισες 600 Ευρώ")};
            won[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.upgradeMoney(600);
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            JOptionPane.showOptionDialog(frame,
                    "Υπήρξε άνοδος στην αξία του κρυπτονομίσματος!", "Crypto Thursday",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    image, won, won[0]);
        }
    }

    /**
     * The inherited method for ThursdayRise
     * The inherited method for SundayFootballGame
     * pre: player's position == thursday
     * post: depends on the moneyWon() --> adjusts the player's money accoridingly
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        showMessageBet(p);
    }
}

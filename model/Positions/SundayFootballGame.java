package model.Positions;

import controller.ProjectPath;
import model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static view.GUI.money1;
import static view.GUI.money2;

/**
 * Invariant: number of positions on the board
 */
public class SundayFootballGame extends Position {

    public SundayFootballGame(Player p, Player p1) {
        performActionPos(p, p1);
    }

    /**
     * Scales an icon for the dialog
     * pre: player's position == sunday
     * post: get a scaled icon
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
     * Checks if the player wants to bet for the football game
     * pre: player's position == sunday
     * post: none
     *
     * @return true if the player wants to, else return false
     */
    public static boolean wantsToBet(int option) {
        return option != 4;
    }

    /**
     * Updates the player's money label
     * @param p the player
     */
    static void updateMoneyLabel(Player p) {
        int num = p.getID();
        switch (num) {
            case 1:
                money1.setText("Money:             " + p.getAvailableMoney() + " Euros");
                money1.repaint();
                money1.revalidate();
                break;
            case 2:
                money2.setText("Money:             " + p.getAvailableMoney() + " Euros");
                money2.repaint();
                money2.revalidate();
                break;
        }
    }

    /**
     * Shows a message and lets the player choose.
     *
     * @param p the player
     */
    private static void showMessageBet(Player p) {
        final int[] user_option = new int[1];
        JFrame frame = new JFrame();
        frame.setResizable(false);
        JButton barcelona = new JButton("Νίκη Mπαρτσελόνα");
        JButton tie = new JButton("Ισοπαλία");
        JButton real = new JButton("Νίκη Ρεάλ");
        JButton snob = new JButton("Δεν θέλω να κάνω πρόβλεψη");
        ImageIcon image = scaledImage((new ImageIcon(ProjectPath.initBasePath() + "/src/model/Positions/Barcelona_Real.jpg")));
        barcelona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_option[0] = 1;
                p.reduceMoney(500);

                frame.setVisible(false);
                frame.dispose();
            }
        });

        tie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_option[0] = 2;
                p.reduceMoney(500);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        real.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_option[0] = 3;
                p.reduceMoney(500);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        snob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_option[0] = 4;
                frame.setVisible(false);
                frame.dispose();
            }
        });
        JButton[] buttons = new JButton[]{
                barcelona, tie, real, snob
        };
        JOptionPane.showOptionDialog(frame,
                "Στοιχιμάτησε 500 ευρώ για το ντέρμπι Barcelona-Real", "Ποδοσφαιρικός Αγώνας Κυριακής",
                JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                image,
                buttons, buttons[0]);
        updateMoneyLabel(p);
        if (!wantsToBet(user_option[0]))
            return;


        int winner = (new Random()).nextInt(3) + 1; // 1 -> Barcelona 2 -> Tie 3 -> Real
        int result = (new Random()).nextInt(6) + 1; //Rolling the die

        boolean a = (user_option[0] == 1 && winner == 1 && (result == 1 || result == 2));
        boolean b = (user_option[0] == 3 && winner == 3 && (result == 5 || result == 6));
        boolean c = (user_option[0] == 2 && winner == 2 && (result == 3 || result == 4));

        if (a || b || c) {
            JButton[] won = new JButton[]{new JButton("Kέρδισες 1000 Ευρώ")};
            won[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.upgradeMoney(1000);
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            JOptionPane.showOptionDialog(frame,
                    "Σωστή πρόβλεψη!!!", "Ποδοσφαιρικός Αγώνας Κυριακής",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    image, won, won[0]);
            updateMoneyLabel(p);
        } else {
            JButton[] lost = new JButton[]{new JButton("Έχασες 500 Ευρώ")};
            lost[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.reduceMoney(500);
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            JOptionPane.showOptionDialog(frame,
                    "Λάθος πρόβλεψη  :( !", "Ποδοσφαιρικός Αγώνας Κυριακής",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    image, lost, lost[0]);
        }
        updateMoneyLabel(p);
    }

    /**
     * The inherited method for SundayFootballGame
     * pre: player's position == sunday
     * post: depends on the money won ---> adjusts the player's money accordingly
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        showMessageBet(p);
    }
}

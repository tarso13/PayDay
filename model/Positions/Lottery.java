package model.Positions;

import model.Player.Player;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Invariant: number of positions on the board, image of the lottery position
 */
public class Lottery extends Position {

    public Lottery() {
    }

    /**
     * Lets user(s) choose a number
     * @param id the player's id
     * @return chosen number
     */
    private static int chooseNum(int id) {
        int chosen_num = 0;
        do {
            /*1 is the default value*/
            chosen_num = Integer.parseInt(JOptionPane.showInputDialog(
                    "Player " + id + " choose a number in the range 1-6: ", 1));
        } while (chosen_num < 1 || chosen_num > 6);
        return chosen_num;
    }

    /**
     * Returns a random int in the range 1-6 (meaning the die has be rolled)
     *
     * @return the die number
     */
    public int rollTheDie() {
        return (new Random()).nextInt(6) + 1;
    }

    /**
     * The inherited method for the lottery positions.
     * pre: player's position == lottery position
     * post: give money to jackpot or take money from the bank depending on rollTheDie()
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        int next_id = p1.getID();
        int cur_id = p.getID();
        int res = 0;

        int num1 = chooseNum(cur_id);
        int num2 = chooseNum(next_id);

        do {
            res = rollTheDie();
        } while (res != num1 && res != num2);

        JButton ok = new JButton("OK");
        JButton[] options = {ok};
        JFrame frame = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = new ImageIcon("/home/hacker/IdeaProjects/phaseBJavaa/model/Positions/dice-" + res + ".jpg").getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        int n = JOptionPane.showOptionDialog(frame,
                "Die result: " + res,
                "Result",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);


        if (res == num1) {
            p.upgradeMoney(1000);
        } else {
            p1.upgradeMoney(1000);
        }
    }
}

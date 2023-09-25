package model.Positions;

import javax.swing.*;

import controller.ProjectPath;
import model.Board.Board;
import model.Player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Invariant: number of positions on the board, image of the yard position
 */
public class Yard extends Position {
    private static int die_number = 0;

    public Yard() {

    }
    /**
     * Shows a message that the die need to be rolled
     * pre: player's position = yard position
     * post:  the die is rolled
     * @param p the player
     * @param id the player's id
     * @return the die result
     */
    private static int showMessageRollDie(Player p, int id) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        JButton ok = new JButton("Roll The Die");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                die_number = p.rollTheDie();
                frame.setVisible(false);
                frame.dispose();
            }
        });
        JButton[] buttons = new JButton[]{
                ok
        };
        JOptionPane.showOptionDialog(frame,
                "Player " + id + ", you have to roll the die.", "",
                JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                buttons, buttons[0]);

        return die_number;
    }

    /**
     * The inherited method for the yard positions.
     * pre: player's position == yard position
     * post: money -= 100*rollTheDie() && take deal card
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        int res = showMessageRollDie(p, p.getID());
        JButton ok = new JButton("OK");
        JButton[] options = {ok};
        JFrame frame = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = new ImageIcon(ProjectPath.initBasePath() + "/model/Positions/dice-" + res + ".jpg").getImage();
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

        p.reduceMoney(100 * res);
        p.getDealCards().add(Board.dealCards.pop());
    }
}

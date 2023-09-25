package model.Positions;

import controller.ProjectPath;
import model.Player.Player;
import model.Positions.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Invariant: number of positions on the board, image of the radio position
 */
public class Radio extends Position {
    private static int die_number = 0;
    private static final int next_res = 0;

    public Radio() {
    }

    /**
     * Shows a message that the die need to be rolled
     * pre: player's position = radio position
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

        JButton ok1 = new JButton("OK");
        JButton[] options = {ok};
        JFrame frame1 = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = null;
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.setVisible(false);
                frame1.dispose();
            }
        });

        image = new ImageIcon(ProjectPath.initBasePath() +"/model/Positions/dice-" + die_number + ".jpg").getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        int n = JOptionPane.showOptionDialog(frame1,
                "Die result: " + die_number,
                "Result",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);

        return die_number;
}

    /**
     * The inherited method for the radio positions.
     * pre: player's position = radio position
     * post: money += 1000 to player with biggest number after rolling the die
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        int next_id = p1.getID();
        int cur_id = p.getID();
        int cur_res;
        int next_res;

        do {
            cur_res = showMessageRollDie(p, cur_id);
            next_res = showMessageRollDie(p1, next_id);
        } while (cur_res == next_res);

        if (cur_res > next_res) {
            p.upgradeMoney(1000);
            return;
        }
        p1.upgradeMoney(1000);

    }
}

package model.Positions;

import model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Invariant: number of positions on the board, image of the casino position
 */

public class Casino extends Position {
    public Casino() {
    }


    /**
     * The inherited method for the Casino position.
     * pre: player's position == casino position
     * post: give money to the player whose chosen number was the result after rollTheDie(p) first
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        int die = p.getDieNumber();
        JButton ok = new JButton("OK");
        JButton[] options = {ok};
        JFrame frame = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = new ImageIcon("/home/hacker/IdeaProjects/phaseBJavaa/model/Positions/dice-" + die + ".jpg").getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        int n = JOptionPane.showOptionDialog(frame,
                "Die result: " + die,
                "Result",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);

        if (die % 2 == 0) {
            p.upgradeMoney(500);
            return;
        }
        p.reduceMoney(500);
        Jackpot.upgradeJackpotMon(500);
        return;
    }
}

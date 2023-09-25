package model.Positions;

import controller.ProjectPath;
import model.Cards.Card;
import model.Player.Player;
import model.Positions.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Invariant: number of positions on the board, image of the sweepstake position
 */
public class Sweepstake extends Position {
    private static int die_num;

    public Sweepstake() {
    }

    /**
     * Shows a message that the die need to be rolled
     * pre: player's position = sweepstake position
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
                die_num = p.rollTheDie();
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
        return die_num;
    }

    /**
     * The inherited method for the specific positions
     * pre: player's position == sweepstake position
     * post: player's money += rollTheDie()*1000
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        int tmp = showMessageRollDie(p, p.getID());
        JButton ok = new JButton("OK");
        JButton[] options = {ok};
        JFrame frame = new JFrame();
        frame.setLocation(200, 200);
        frame.setResizable(false);
        Image image = new ImageIcon(ProjectPath.initBasePath() + "/model/Positions/dice-" + tmp + ".jpg").getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        int n = JOptionPane.showOptionDialog(frame,
                "Die result: " + tmp,
                "Result",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);

        p.upgradeMoney(1000*tmp);
}

}
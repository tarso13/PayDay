package model.Positions;

import controller.Controller;

import model.Cards.DealCard;
import model.Cards.MailCard;
import model.Player.Player;
import view.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Invariant: number of positions on the board, image of the payday position
 */
public class PayDay extends Position {
    public static int counter;

    public PayDay() {
        counter = 0;
    }

    /**
     * Pays the player 3500(money)
     * pre: player's position = payday position
     * post: player's money = x + 3500
     */
    public void getPaid(Player p) {
        p.upgradeMoney(3500);
    }


    /**
     * Makes the player pay the bills
     * pre: player's position = payday position
     * post: player's bills = 0
     *
     * @param p the player
     */
    public void payTheBills(Player p) {
        int b = p.getBills();
        p.reduceMoney(b);
        p.setBills(0);
    }

    /**
     * Makes the player ignore the deal cards
     */
    public void ignoreDealCards(Player p) {
        ArrayList<DealCard> tmp = p.getDealCards();
        while (tmp.iterator().hasNext()) {
            DealCard cur = tmp.iterator().next();
            (Controller.IgnoredCards).push(cur);
            tmp.remove(cur);
        }
    }

    /**
     * Makes the player ignore the cards with the bills
     */
    public void ignoreMailCards(Player p) {
        ArrayList<MailCard> tmp = p.getMailCards();
        while (tmp.iterator().hasNext()) {
            MailCard cur = tmp.iterator().next();
            if (cur.getType().equals("Εξόφληση Λογαριασμού")) {
                (Controller.IgnoredCards).push(cur);
                tmp.remove(cur);
            }
        }
    }

    /**
     * Gives user the option to pay more for current loan
     * pre: player's position = payday position
     * post: player's choice
     * @param p the player
     * @return whether the player wants to pay more
     */
    public boolean wantsToPayMore(Player p) {
        final boolean[] result = new boolean[1];
        JFrame frame = new JFrame();
        frame.setResizable(false);
        JButton yes = new JButton("Yes");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result[0] = true;
                frame.setVisible(false);
                frame.dispose();
            }
        });
        JButton no = new JButton("No");
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result[0] = false;
                frame.setVisible(false);
                frame.dispose();
            }
        });
        JButton[] buttons = new JButton[]{
                yes, no
        };
        JOptionPane.showOptionDialog(frame,
                "Would you like to pay more?",
                "Optional",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                buttons, buttons[1]);
        return result[0];
    }

    /**
     * Depending on the value given the player can pay part or all the loan
     *
     * @return the value of money the player wants to pay
     */
    public int getChosenVal() {
        int chosen_val = 0;
        do {
        /*1 is the default value*/
        chosen_val = Integer.parseInt(JOptionPane.showInputDialog(
                "Enter a valid value: ", 1));
       } while (chosen_val < 0 || chosen_val > 100);
        return chosen_val;
    }

    /**
     * Checks if it is the last month of the game
     *
     * @return true if it is else false
     */
    public static boolean isTheLastMonth(Player p) {
        return counter == p.getDuration_p();
    }

    /**
     * The inherited method for the PayDay position.
     * pre: player's position = payday position
     * post: player's money += 3500 & -10% loan(if existent), bills = 0, bill cards = 0, if last month -> ignore cards
     * else player's pos = start
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        counter++;
        getPaid(p);
        SundayFootballGame.updateMoneyLabel(p);
        int ln = p.getLoans();
        int _bills = p.getBills();
        int mn = p.getAvailableMoney();
        if (_bills > 0) {
            while (mn < _bills) {
                p.getLoan(1000);
            }
            p.reduceMoney(_bills);
            ignoreMailCards(p);
        }

        if (ln > 0) {
            while (mn < ln) {
                p.getLoan(1000);
            }
            p.reduceMoney(10 * ln);

            if (wantsToPayMore(p)) {
                int cur_loan = p.getLoans();
                int chosen_value = 0;
                do {
                    chosen_value = getChosenVal();
                } while (!p.chosenValueOK(chosen_value));
                int new_loan_val = cur_loan - chosen_value;
                p.setLoans(new_loan_val);
                p.reduceMoney(chosen_value);

            }

            SundayFootballGame.updateMoneyLabel(p);
        }

        if (isTheLastMonth(p)) {
            ignoreDealCards(p);
            p.setHasFinished(true);
            return;
        }

        p.setPositionNum(0);
        if (p.getID() == 1) {
            p.movePawn(0, 210, GUI.p1_pawn);
            return;
        }

        p.movePawn(30, 210, GUI.p2_pawn);
    }
}
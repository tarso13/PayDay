package model.Positions;

import model.Cards.DealCard;
import model.Player.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Invariant: number of positions on the board, image of the buyer position
 */
public class Buyer extends Position {
    public Buyer() {
    }

    /**
     * Lets the player choose a product
     * pre: player's position = buyer position
     * post: return chosen product
     *@return null if the player does not have any products to sell else the chosen product
     */
    public DealCard chooseProduct(Player p) {
        JFrame frame = new JFrame();
        frame.setLocation(500,200);
        frame.setVisible(true);
        frame.setResizable(false);

        Object[] possibilities = new Object[p.getDealCards().size()];
        for (int i = 0; i < p.getDealCards().size(); i++) {
            possibilities[i] = p.getDealCards().get(i).getMessage();
        }
        String c = (String) JOptionPane.showInputDialog(
                frame,
                "Choose product:",
                "Buyer Position",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                p.getDealCards().size());
        DealCard card_chosen = null;
        for(int i = 0; i < p.getDealCards().size(); i++) {
            if (p.getDealCards().get(i).getMessage().equals(c))
                card_chosen = p.getDealCards().get(i);
        }
        frame.setVisible(false);
        frame.dispose();
        if(card_chosen != null) {
            p.upgradeMoney(card_chosen.getCost_sell());
            p.prod_counter--;
        }
        return card_chosen;
    }


    /**
     * The inherited method for the Buyer position.
     * pre: player's position = buyer position
     * post: sell a product, choose which one, money += sell_cost, remove card from player's collection
     */

    @Override
    public void performActionPos(Player p, Player p1) {
        if (p.prod_counter <= 0)
            return;
        DealCard prod = chooseProduct(p);
        if (prod == null)
            return;

        int cost = prod.getCost_sell();
        p.upgradeMoney(cost);
        p.getDealCards().remove(prod);
    }
}

package model.Cards;

import controller.Controller;
import model.Board.Board;
import model.Player.Player;

import static model.Positions.Deal.wantsTheCard;

/**
 * Invariants: number of deal cards, image of the deal card button
 */
public class DealCard extends Card {
    private int cost_buy;
    private int cost_sell;
    private final String message;
    private final String icon_path;

    public DealCard(String message, int cost_buy, int cost_sell, String icon_path) {
        this.message = message;
        this.cost_sell = cost_sell;
        this.cost_buy = cost_buy;
        this.icon_path = icon_path;
    }

    /**
     * Returns the message of the dealcard
     *
     * @return the message field
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the icon path of the dealcard
     *
     * @return the icon path field
     */
    public String getIcon_path() {
        return icon_path;
    }


    /**
     * Returns the cost of the card for the player
     */
    public int getCost_buy() {
        return cost_buy;
    }


    /**
     * Returns the cost of the card for the player
     */
    public int getCost_sell() {
        return cost_sell;
    }

    /**
     * The inherited method for the deal cards.
     * pre: player's pos = deal card position
     * post: do what's needed when the card is taken
     */
    @Override
    public void performAction(Player p, Player p1) {
        if (wantsTheCard()) {
            p.getDealCards().add(this);
            while (p.getAvailableMoney() < this.getCost_buy()) {
                p.getLoan(1000);
            }
            p.reduceMoney(this.getCost_buy());
            p.prod_counter++;
            Board.dealCards.pop();
            p.setTurn(false);
            return;
        }
        Controller.IgnoredCards.push(this);
        return;
    }
}
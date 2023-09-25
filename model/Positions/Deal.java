package model.Positions;

import controller.Controller;
import model.Board.Board;
import model.Cards.Card;
import model.Cards.DealCard;

import model.Player.Player;

import java.util.Collections;

public class Deal extends Position {
    public static boolean wants_the_card = false;

    public Deal() {
    }

    /**
     * Returns whether the player wants the deal Card
     * pre: player's pos = deal card position
     * post: none
     *
     * @return true if he/she wants it, else false
     */
    public static boolean wantsTheCard() {
        return wants_the_card;
    }

    /**
     * The inherited method for the deal cards.
     * pre: player's pos = deal card position
     * post: make sure that dealcard stack is not empty
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        if (Board.dealCards.isEmpty()) {
            Collections.shuffle(Controller.IgnoredCards);
            for (Card c : Controller.IgnoredCards) {
                if (c instanceof DealCard)
                    Board.dealCards.push((DealCard) c);
            }
        }
    }
}

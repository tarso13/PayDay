package model.Positions;

import controller.Controller;
import model.Board.Board;
import model.Cards.Card;
import model.Cards.MailCard;
import model.Player.Player;

import java.util.Collections;


public class Mail extends Position {
    private static int number;
    public static int counter;

    public Mail(int number) {
        this.number = number; //can either be 1 or 2
        counter = number;
    }

    /**
     * Returns the number of the deal card
     *
     * @return 1 or 2
     */
    public static int getNumber() {
        return number;
    }

    /**
     * The inherited method for the mail cards.
     * pre: player's pos = mail card position
     * post: make sure that mailcard stack is not empty
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        if (Board.mailCards.isEmpty()) {
            Collections.shuffle(Controller.IgnoredCards);
            for (Card c : Controller.IgnoredCards) {
                if (c instanceof MailCard)
                    Board.mailCards.push((MailCard) c);
            }

        }
    }
}

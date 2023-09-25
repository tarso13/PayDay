package model.Cards;

import model.Player.Player;
import javax.swing.*;

public abstract class Card {
    protected static Icon icon;

    public Card() {
    }

    /**
     * The functionality of this method depends on the different kind of cards
     */
    public abstract void performAction(Player p, Player p1);

}


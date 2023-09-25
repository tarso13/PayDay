package model.Positions;

import model.Player.Player;

import javax.swing.*;

public abstract class Position {

    protected static Icon icon;

    Position() {
    }



    /**
     * The functionality of this method depends on the different kind of positions
     * pre: none
     * post: depends on the specific position
     */
    public abstract void performActionPos(Player p, Player p1);

}
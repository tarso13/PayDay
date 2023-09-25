package model.Positions;

import model.Player.Player;

import javax.swing.*;

public class Jackpot extends Position {
    private static int jackpot_mon;

    public Jackpot() {
        jackpot_mon = 0;
    }

    /**
     * Return the jackpot money of the game
     * @return jackpot_mon field
     */
    public static int getJackpotMon() {
        return jackpot_mon;
    }

    /**
     * Set the the jackpot money of the game
     * @param jackpot_mon new value to be assigned
     */
    public void setJackpotMon(int jackpot_mon) {
        Jackpot.jackpot_mon = jackpot_mon;
    }

    /**
     * Upgrade jackpot money
     * @param x the value to be added to current value of jackpot money
     */
    public static void upgradeJackpotMon(int x) {
        jackpot_mon += x;
    }

    /**
     * Reduce jackpot money
     * @param x the value to be reduced to current value of jackpot money
     */
    public static void reduceJackpotMon(int x) {
        jackpot_mon -= x;
    }

    /**
     * The inherited method for the specific positions
     * pre: player's die number == 6
     * post: player's money += jackpot money, jackpot money = 0
     */
    @Override
    public void performActionPos(Player p, Player p1) {
        int mon = getJackpotMon();
        p.upgradeMoney(mon);
        reduceJackpotMon(mon);
    }
}

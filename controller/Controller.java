package controller;

import model.Cards.Card;
import model.Cards.DealCard;
import model.Cards.MailCard;
import model.Cards.helpingClass;
import model.Player.Player;
import model.Positions.*;
import view.GUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Controller extends GUI {
    public static Player p1;
    public static Player p2;
    public static int duration;
    public static Jackpot jack_pos;
    public static Stack<Card> IgnoredCards;

    public Controller() {
        jack_pos = new Jackpot();
    }

    /**
     * Map 1st player's number to position
     * pre: none
     * post: player's position = a specific position
     * @param x the player's current position number
     * @param p the player
     */
    public static void mapPosNumToPos1(int x, Player p) {
        if (p.getPositionNum() == 31) {
            p.setPosition(new PayDay());
            PayDay.counter = duration;
            whatToDoLabel.setText("--> " + "Reached The End of The Month  ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/sweep.png")) {
            p.setPosition(new Sweepstake());
            whatToDoLabel.setText("--> " + "Roll The Die Again  ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/radio.png")) {
            p.setPosition(new Radio());
            whatToDoLabel.setText("--> " + "Both Players Roll The Die   ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/casino.png")) {
            p.setPosition(new Casino());
            whatToDoLabel.setText("--> " + "Wait, you either win or lose 500 euros");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/yard.png")) {
            p.setPosition(new Yard());
            whatToDoLabel.setText("--> " + "Roll The Die Again           ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/lottery.png")) {
            p.setPosition(new Lottery());
            whatToDoLabel.setText("--> " + "Both Players Choose a Number(1-6)");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/mc1.png")) {
            p.setPosition(new Mail(1));
            whatToDoLabel.setText("--> " + "Get 1 Mail Card      ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/mc2.png")) {
            p.setPosition(new Mail(2));
            whatToDoLabel.setText("--> " + "Get 2 Mail Cards      ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/deal.png")) {
            p.setPosition(new Deal());
            whatToDoLabel.setText("--> " + "Get 1 Deal Card      ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/buyer.png")) {
            p.setPosition(new Buyer());
            whatToDoLabel.setText("--> " + "Choose a Product(if existent)    ");
        }
        whatToDoLabel.repaint();
        whatToDoLabel.revalidate();
    }
    /**
     * Map 2nd player's number to position
     * pre: none
     * post: player's position = a specific position
     * @param x the player's current position number
     * @param p the player
     */
    public static void mapPosNumToPos2(int x, Player p) {
        if (p.getPositionNum() == 31) {
            p.setPosition(new PayDay());
            PayDay.counter = duration;
            whatToDoLabel.setText("--> " + "Reached The End of The Month  ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/sweep.png")) {
            p.setPosition(new Sweepstake());
            whatToDoLabel.setText("--> " + "Roll The Die Again  ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/radio.png")) {
            p.setPosition(new Radio());
            whatToDoLabel.setText("--> " + "Both Players, Roll The Die   ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/casino.png")) {
            p.setPosition(new Casino());
            whatToDoLabel.setText("--> " + "Wait, you either win or lose 500 euros");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/yard.png")) {
            p.setPosition(new Yard());
            whatToDoLabel.setText("--> " + "Roll The Die Again           ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/lottery.png")) {
            p.setPosition(new Lottery());
            whatToDoLabel.setText("--> " + "Both Players Choose a Number(1-6)");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/mc1.png")) {
            p.setPosition(new Mail(1));
            whatToDoLabel.setText("--> " + "Get 1 Mail Card      ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/mc2.png")) {
            p.setPosition(new Mail(2));
            whatToDoLabel.setText("--> " + "Get 2 Mail Cards      ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/deal.png")) {
            p.setPosition(new Deal());
            whatToDoLabel.setText("--> " + "Get 1 Deal Card      ");
        } else if (labelIcons.get(x - 1).equals(ProjectPath.initBasePath() + "/src/model/Positions/buyer.png")) {
            p.setPosition(new Buyer());
            whatToDoLabel.setText("--> " + "Choose a Product(if existent)    ");
        }
        whatToDoLabel.repaint();
        whatToDoLabel.revalidate();
    }

    /**
     * Returns current duration of the game
     * @return the duration of the game
     */
    public static int getDuration() {
        return duration;
    }

    /**
     * Initialization of the game.
     */
    public void startGame() {
        int first = (new Random()).nextInt(2) + 1;
        duration = (new Random()).nextInt(3) + 1;
        durationLabel.setText("Duration: " + duration + " Month(s)" + "                     ");
        turnLabel.setText("Turn : Player " + first + "                                                          ");
        whatToDoLabel.setText("-->" + "Roll The Die                  ");
        p1 = new Player(1, duration);
        p1.setAvailableMoney(3500);
        p2 = new Player(2, duration);
        p2.setAvailableMoney(3500);
        p1.setDealCards(new ArrayList<DealCard>());
        p1.setMailCards(new ArrayList<MailCard>());
        p2.setDealCards(new ArrayList<DealCard>());
        p2.setMailCards(new ArrayList<MailCard>());
        switch (first) {
            case 1:
                p1.setTurn(true);
                p2.setTurn(false);
                break;
            case 2:
                p2.setTurn(true);
                p1.setTurn(false);
                break;
        }
        helpingClass.readFile(ProjectPath.initBasePath() + "/src/model/Cards/resources/dealCards.csv", "Deal");
        helpingClass.readFile(ProjectPath.initBasePath() + "/src/model/Cards/resources/mailCards.csv", "Mail");
        IgnoredCards = new Stack<>();
    }


    /**
     * Get the winner of the current round
     *
     * @return the players id(1 or 2), if 0 is returned it is a tie
     */
    public static int isTheWinner() {
        if (p1.getAvailableMoney() > p2.getAvailableMoney())
            return 1;
        else if (p1.getAvailableMoney() < p2.getAvailableMoney())
            return 2;
        return 0; /*tie*/
    }


    public static void main(String[] args) {
        /**
         * Implementation of the game
         */
        Controller c = new Controller();
        c.startGame();

    }
}
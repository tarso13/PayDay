package model.Cards;

import controller.Controller;
import controller.ProjectPath;
import model.Board.Board;
import model.Player.Player;
import model.Positions.Deal;
import model.Positions.Jackpot;
import model.Positions.Mail;
import view.GUI;
import view.MyButtonListener;

import static controller.Controller.p2;
import static view.GUI.labelIcons;


/**
 * Invariants: number of mail cards, image of the mail card
 */
public class MailCard extends Card {
    private final String type;
    private final String message;
    private final int cost;
    public static boolean activate_last_card = true;

    MailCard(String type, String message, int cost) {
        this.type = type;
        this.message = message;
        this.cost = cost;
    }

    /**
     * Returns the type of the card
     *
     * @return the type of the card
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the message of the card
     *
     * @return the message of the card
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the cost of a card
     *
     * @return the cost of the card
     */
    public int getCost() {
        return cost;
    }


    /**
     * The inherited method for the mail cards.
     * pre: player's pos = mail card position
     * post: do what is needed for the specific mail card
     */
    @Override
    public void performAction(Player p, Player p1) {
        String type = this.getType();
        if (type.equals("Εξόφληση Λογαριασμού")) {
            p.getMailCards().add(this);
            p.setBills(p.getBills() + this.getCost());
        } else if (type.equals("Διαφήμιση")) {
            p.upgradeMoney(this.getCost());
        } else if (type.equals("Φιλανθρωπία")) {
            p.reduceMoney(this.getCost());
            Jackpot.upgradeJackpotMon(this.getCost());
        } else if (type.equals("Πλήρωσε τον γείτονα")) {
            while (p.getAvailableMoney() < this.getCost()) {
                p.getLoan(1000);
            }
            p.reduceMoney(this.getCost());
            p1.upgradeMoney(this.getCost());
        } else if (type.equals("Πάρε λεφτά από το γείτονα")) {
            while (p1.getAvailableMoney() < this.getCost()) {
                p1.getLoan(1000);
            }
            p1.reduceMoney(this.getCost());
            p.upgradeMoney(this.getCost());
        } else {
            //to be implemented
            int x = p.getPositionNum();
            int move = x;
            int flag = 0;
            while (flag == 0) {
                if ((labelIcons.get(move).equals(ProjectPath.initBasePath() + "/src/model/Positions/deal.png")
                        || labelIcons.get(move).equals(ProjectPath.initBasePath() + "/src/model/Positions/buyer.png")) && move < 30)
                    flag = 1;
                ++move;
                if (move == 30) {
                    Board.mailCards.pop();
                    return;
                }
            }

            MyButtonListener.doubleMailPosition = move - x;
            if (activate_last_card != false)
                switch (p.getID()) {
                    case 1:
                        p.movePosition(move - x, GUI.p1_pawn);
                        Controller.mapPosNumToPos1(p.getPositionNum(), p);
                        if (!(Controller.p1.getPosition() instanceof Deal) && !(Controller.p1.getPosition() instanceof Mail))
                            Controller.p1.getPosition().performActionPos(Controller.p1, Controller.p2);
                        break;
                    case 2:
                        p.movePosition(move - x, GUI.p2_pawn);
                        Controller.mapPosNumToPos2(p.getPositionNum(), p);
                        if (!(Controller.p2.getPosition() instanceof Deal) && !(Controller.p2.getPosition() instanceof Mail))
                            Controller.p2.getPosition().performActionPos(Controller.p2, Controller.p1);
                        break;
                }
        }
        Board.mailCards.pop();
    }
}
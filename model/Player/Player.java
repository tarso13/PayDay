package model.Player;

import controller.ProjectPath;
import model.Cards.DealCard;
import model.Cards.MailCard;
import model.Positions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int score;
    private int days;
    private int loan;
    private int bills;
    private int duration_p;
    private int available_money;
    public int prod_counter;
    private boolean has_finished;
    private boolean has_played;
    private boolean turn;
    private int id; //can be either 1 or 2 (we have only 2 players)
    private int die_number;
    private Position position;
    private Position special_position;
    private int player_position;
    private ArrayList<DealCard> deal_cards;
    private ArrayList<MailCard> mail_cards;
    public JLabel die_label;
    public JLabel av_money;
    public JLabel loanss;
    public JLabel billss;

    public Player(int id, int dur) {
        player_position = 0;
        this.id = id;
        this.duration_p = dur;
        days = this.duration_p * 31;
        score = 0;
        prod_counter = 0;
        setHasFinished(false);
        die_label = new JLabel();
        loanss = new JLabel();
        billss = new JLabel();
        av_money = new JLabel();
        special_position = null;
        deal_cards = new ArrayList<>();
        mail_cards = new ArrayList<>();
    }


    /**
     * Sets the player's turn
     *
     * @param turn either true or false
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * Get the player's mail cards
     *
     * @return the field mail_cards
     */
    public ArrayList<MailCard> getMailCards() {
        return mail_cards;
    }

    /**
     * Get the player's deal cards
     *
     * @return the field deal_cards
     */
    public ArrayList<DealCard> getDealCards() {
        return deal_cards;
    }

    /**
     * Returns the player's score
     *
     * @return the score field
     */
    public int getScore() {
        return score;
    }


    /**
     * Sets duration_p accordingly
     *
     * @param duration_p the duration of each game
     */
    public void setDuration_p(int duration_p) {
        this.duration_p = duration_p;
    }

    /**
     * Sets bills accordingly
     *
     * @param new_val new bill value
     */
    public void setBills(int new_val) {
        this.bills = new_val;
    }

    /**
     * Sets player's position to the specific position given
     *
     * @param position the player's position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Returns the player's available money
     *
     * @return available_money field
     */
    public int getAvailableMoney() {
        return available_money;
    }

    /**
     * Returns the days remaining for the player
     *
     * @return the days field
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the days remaining for the player
     *
     * @param days the value to be assigned
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * Returns the duration of the player
     *
     * @return the duration_p field
     */
    public int getDuration_p() {
        return duration_p;
    }

    /**
     * Returns the loans the player has
     *
     * @return the loan field
     */
    public int getLoans() {
        return loan;
    }


    /**
     * Lets the player roll their die
     *
     * @return the result after rolling the die
     */
    public int rollTheDie() {
        return (new Random()).nextInt(6) + 1;
    }

    /**
     * Returns the player's die number
     *
     * @return the die_number field
     */
    public int getDieNumber() {
        return die_number;
    }

    /**
     * Returns the current player's position
     *
     * @return player_position field
     */
    public int getPositionNum() {
        return player_position;
    }


    /**
     * Returns the position of the player
     *
     * @return the player's position field
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the position of the player
     *
     * @return the player's position field
     */
    public Position getSpecial_position() {
        return special_position;
    }


    /**
     * Returns the player's id
     *
     * @return 1 or 2 depending on the player
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the player's id to the value of the parameter given
     *
     * @param id the id of the player
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Sets the player's die_number to the value of the parameter given
     *
     * @param die_number the player's die number
     */
    public void setDieNumber(int die_number) {
        this.die_number = die_number;
    }

    /**
     * Returns the player's turn
     *
     * @return true if the player has to play
     */
    public boolean getTurn() {
        return turn;
    }

    /**
     * Sets the available_money field of the player
     *
     * @param available_money the value assigned to the available_money field
     */
    public void setAvailableMoney(int available_money) {
        this.available_money = available_money;
    }

    /**
     * Sets the player_position accordingly
     *
     * @param player_position the new position of the player
     */
    public void setPositionNum(int player_position) {
        this.player_position = player_position;
        if (this.player_position == 31) --duration_p;
        if (duration_p == 0) setHasFinished(true);
    }

    /**
     * Sets the deal cards of the player if needed
     *
     * @param c the arraylist of cards to be assigned to the deal_cards fiels of the player
     */
    public void setDealCards(ArrayList<DealCard> c) {
        this.deal_cards = c;
    }

    /**
     * Sets the mail cards of the player if needed
     *
     * @param c the arraylist of cards to be assigned to the mail_cards field of the player
     */
    public void setMailCards(ArrayList<MailCard> c) {
        this.mail_cards = c;
    }

    /**
     * Returns true if the player has just played
     *
     * @return the has_played field of the player
     */
    public boolean hasPlayed() {
        return has_played;
    }

    /**
     * Sets the has_played field of the player if the player has just played
     *
     * @param has_played field of the player
     */
    public void setHasPlayed(boolean has_played) {
        this.has_played = has_played;
    }

    /**
     * Returns true if the player has finished the game/round
     *
     * @return the has_finished field of the player
     */
    public boolean hasFinished() {
        return has_finished;
    }

    /**
     * Moves the player's pawn and adjustes the days field
     *
     * @param die_num the number of the die
     */
    public void movePosition(int die_num, JLabel label) {
        int addon = 0;
        switch (id) {
            case 1:
                addon = 0;
                break;
            case 2:
                addon = 30;
                break;
        }
//        System.out.println("Player " + getID());
//        System.out.println("Prev pos: " + getPositionNum());
//        System.out.println("Die num: " + die_num);
        int move = getPositionNum() + die_num;
//        System.out.println("Next pos: " + move);
        if (move < 7) {
            movePawn(move * 120 + addon, 210, label);
        } else if (move < 14) {
            movePawn((move - 7) * 120 + addon, 350, label);
        } else if (move < 21) {
            movePawn((move - 14) * 120 + addon, 490, label);
        } else if (move < 28) {
            movePawn((move - 21) * 120 + addon, 630, label);
        } else {
            if (move > 31)
                move = 31;
            movePawn((move - 28) * 120 + addon, 770, label);
        }
        setDays(getDays() - die_num);
        setPositionNum(move);
        setDieNumber(die_num);
        int num = getPositionNum();
        if (num == 7 || num == 14 || num == 21 || num == 28)
            special_position = new SundayFootballGame(this, null);
        if (num == 4 || num == 11 || num == 18 || num == 25)
            special_position = new ThursdayRise(this, null);
    }

    /**
     * Sets the has_finished field equal to true if the player has finished the game/round
     *
     * @param has_finished the value assigned to has_finished field
     */
    public void setHasFinished(boolean has_finished) {
        this.has_finished = has_finished;
    }

    /**
     * Decreases the player's money when needed
     *
     * @param money the money subtracted from the available_money
     */
    public void reduceMoney(int money) {
        available_money -= money;
    }

    /**
     * Increases the player's money when needed
     *
     * @param money the money added to the available_money
     */
    public void upgradeMoney(int money) {
        available_money += money;
    }

    /**
     * Sets the player's available money += money in the parameter and increases the loan field
     *
     * @param money the money the player needs
     */
    public void getLoan(int money) {
        upgradeMoney(money);
        loan += money;
    }

    /**
     * Returns the player's bills
     *
     * @return bills field of the player
     */
    public int getBills() {
        return bills;
    }

    /**
     * Returns whether the player's chosen value is valid
     *
     * @return true if it is else false
     */
    public boolean chosenValueOK(int val) {
        return getAvailableMoney() >= val && val % 1000 == 0 && val <= getLoans();
    }

    /**
     * Sets correct coordinates for the pawn
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param label the pawn
     */
    public void movePawn(int x, int y, JLabel label) {
        label.setBounds(x, y, 80, 100);
    }

    /**
     * Sets loan value
     *
     * @param new_loan_val the new value for the loan
     */
    public void setLoans(int new_loan_val) {
        this.loan = new_loan_val;
    }

    /**
     * Shows all player's dealcards
     * pre: player's turn = true
     * post: view the player's dealcards
     */
    public void showDealCards() {
        for (int i = 0; i < this.getDealCards().size(); i++) {
            JButton ok = new JButton("OK");
            JButton[] options = {ok};
            JFrame frame = new JFrame();
            frame.setLocation(200, 200);
            frame.setResizable(false);
            Image image = new ImageIcon(ProjectPath.initBasePath() + "/src/model/Cards/resources/images/" + this.getDealCards().get(i).getIcon_path()).getImage();
            image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            int n = JOptionPane.showOptionDialog(frame,
                    this.getDealCards().get(i).getMessage() + "\nΤιμή Αγοράς: " + this.getDealCards().get(i).getCost_buy() + " Ευρώ \nΤιμή Πώλησης: " + this.getDealCards().get(i).getCost_sell() + " Ευρώ \n",
                    "Κάρτα συμφωνίας",
                    JOptionPane.OK_OPTION,
                    0,
                    new ImageIcon(image),
                    options,
                    options[0]);
        }
    }
}

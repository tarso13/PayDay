package view;

import controller.Controller;
import controller.ProjectPath;
import model.Board.Board;
import model.Cards.MailCard;
import model.Cards.helpingClass;
import model.Player.Player;
import model.Positions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.GUI.*;


public class MyButtonListener implements ActionListener {
    /**
     * Does what is needed when specific buttons are pressed
     *
     * @param e the button
     */
    public static boolean doubleMailtransfer = false;
    public static MailCard Mailtransfer = null;
    public static int doubleMailPosition = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player1 = Controller.p1;
        Player player2 = Controller.p2;
        JButton button_pressed = (JButton) e.getSource();
        if (player1.hasFinished() && player2.hasFinished()) {
            int winner = Controller.isTheWinner();
            if (winner == 0) {
                whatToDoLabel.setText("Both players have won the game.");
                whatToDoLabel.repaint();
                whatToDoLabel.revalidate();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
            whatToDoLabel.setText("The winner is... Player " + winner);
            whatToDoLabel.repaint();
            whatToDoLabel.revalidate();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);

        }
        if (player1.hasFinished() && player1.getTurn()) {
            player1.setTurn(false);
            player2.setTurn(true);
            return;
        }

        if (player2.hasFinished() && player2.getTurn()) {
            player1.setTurn(true);
            player2.setTurn(false);
            return;
        }

        if (button_pressed == buttons1[0] && player1.getTurn()) {
            int x = player1.rollTheDie();
            player1.movePosition(x, p1_pawn);
            dicelabel1.setIcon(scaledImage(new ImageIcon(ProjectPath.initBasePath() + "/src/model/Positions/dice-" + x + ".jpg"), dicelabel1));
            dicelabel1.repaint();
            dicelabel1.revalidate();
            if (x == 6) {
                Controller.jack_pos.performActionPos(player1, player2);
            }
            Controller.mapPosNumToPos1(player1.getPositionNum(), player1);
            if (!(player1.getPosition() instanceof Deal) && !(player1.getPosition() instanceof Mail))
                player1.getPosition().performActionPos(player1, player2);
        } else if (button_pressed == buttons2[0] && player2.getTurn()) {
            int x = player2.rollTheDie();
            player2.movePosition(x, p2_pawn);
            dicelabel2.setIcon(scaledImage(new ImageIcon(ProjectPath.initBasePath() + "/src/model/Positions/dice-" + x + ".jpg"), dicelabel2));
            dicelabel2.repaint();
            dicelabel2.revalidate();
            if (x == 6) {
                Controller.jack_pos.performActionPos(player2, player1);
            }
            Controller.mapPosNumToPos2(player2.getPositionNum(), player2);
            if (!(player2.getPosition() instanceof Deal) && !(player2.getPosition() instanceof Mail))
                player2.getPosition().performActionPos(player2, player1);
        } else if (button_pressed == buttons1[1] && player1.getTurn()) {
            if (player1.prod_counter > 0) {
                player1.showDealCards();
            }
        } else if (button_pressed == buttons1[2] && player1.getTurn()) {
            String temp = Board.mailCards.peek().getType();
            boolean valid_loan_type = temp.equals("Εξόφληση Λογαριασμού") || temp.equals("Πλήρωσε τον γείτονα")
                    || temp.equals("Πάρε λεφτά από το γείτονα");
            if (player1.getPosition() instanceof PayDay)
                player1.getPosition().performActionPos(player1, player2);
            else if (player1.getPosition() instanceof Mail && valid_loan_type)
                Board.mailCards.peek().performAction(player1, player2);
        } else if (button_pressed == buttons1[3]) {
            if (player2.hasFinished()) {
                player1.setTurn(true);
                player2.setTurn(false);
            } else {
                player1.setHasPlayed(true);
                player1.setTurn(false);
                player2.setTurn(true);
                turnLabel.setText("Turn: Player 2                                            ");
                turnLabel.repaint();
                turnLabel.revalidate();
            }
        } else if (button_pressed == buttons2[1] && player2.getTurn()) {
            if (player2.prod_counter > 0) {
                player2.showDealCards();
            }
        } else if (button_pressed == buttons2[2]) {
            String temp = Board.mailCards.peek().getType();
            boolean valid_loan_type = temp.equals("Εξόφληση Λογαριασμού") || temp.equals("Πλήρωσε τον γείτονα")
                    || temp.equals("Πάρε λεφτά από το γείτονα");

            if (player2.getPosition() instanceof Mail && valid_loan_type)
                Board.mailCards.peek().performAction(player2, player1);
        } else if (e.getSource() == buttons2[3]) {
            if (player1.hasFinished()) {
                player2.setTurn(true);
                player1.setTurn(false);
            } else {
                player2.setHasPlayed(true);
                player2.setTurn(false);
                player1.setTurn(true);
                turnLabel.setText("Turn: Player 1                                             ");
                turnLabel.repaint();
                turnLabel.revalidate();
            }
        } else if (e.getSource() == deal) {
            if (player1.getTurn() && player1.getPosition() instanceof Deal) {
                int num = Board.dealCards.size() - 1;
                helpingClass.showDealCard(num);
                Board.dealCards.get(num).performAction(player1, player2);
            } else if (player2.getTurn() && player2.getPosition() instanceof Deal) {
                int num = Board.dealCards.size() - 1;
                helpingClass.showDealCard(num);
                Board.dealCards.get(num).performAction(player2, player1);
            }

        } else if (e.getSource() == mail) {
            if (player1.getTurn() && player1.getPosition() instanceof Mail) {
                int num = Board.mailCards.size() - 1;
                if (Mail.getNumber() == 2 && Mail.counter == 2 && Board.mailCards.get(num).getType().equals("Μετακίνηση σε θέση Συμφωνίας/Αγοραστή")) {
                    doubleMailtransfer = true;
                    MailCard.activate_last_card = false;
                }
                helpingClass.showMailCard(num);
                Board.mailCards.get(num).performAction(player1, player2);
                --Mail.counter;

                if (doubleMailtransfer && Mail.getNumber() == 2 && Mail.counter == 1) {
                    MailCard.activate_last_card = true;
                }
                if (doubleMailtransfer && Mail.getNumber() == 2 && Mail.counter == 0) {
                    doubleMailtransfer = false;
                    player1.movePosition(doubleMailPosition, GUI.p1_pawn);
                    Controller.mapPosNumToPos1(player1.getPositionNum(), player1);
                    if (!(player1.getPosition() instanceof Deal) && !(player1.getPosition() instanceof Mail))
                        player1.getPosition().performActionPos(player1, player2);

                }

                if (Mail.counter == 0) player1.setTurn(false);
            } else if (player2.getTurn() && player2.getPosition() instanceof Mail) {
                int num = Board.mailCards.size() - 1;
                helpingClass.showMailCard(num);

                if (Mail.getNumber() == 2 && Mail.counter == 2 && Board.mailCards.get(num).getType().equals("Μετακίνηση σε θέση Συμφωνίας/Αγοραστή")) {
                    doubleMailtransfer = true;
                    MailCard.activate_last_card = false;
                }
                helpingClass.showMailCard(num);
                Board.mailCards.get(num).performAction(player2, player1);
                --Mail.counter;

                if (doubleMailtransfer && Mail.getNumber() == 2 && Mail.counter == 1) {
                    MailCard.activate_last_card = true;
                }
                if (doubleMailtransfer && Mail.getNumber() == 2 && Mail.counter == 0) {
                    doubleMailtransfer = false;
                    player2.movePosition(doubleMailPosition, GUI.p2_pawn);
                    Controller.mapPosNumToPos1(player2.getPositionNum(), player2);
                    if (!(player2.getPosition() instanceof Deal) && !(player2.getPosition() instanceof Mail))
                        player2.getPosition().performActionPos(player2, player1);

                }

                if (Mail.counter == 0) player2.setTurn(false);
            }
        }
        money1.setText("Money:             " + player1.getAvailableMoney() + " Euros");
        money1.repaint();
        money1.revalidate();
        loan1.setText("Loan:                " + player1.getLoans() + " Euros");
        loan1.repaint();
        loan1.revalidate();
        bill1.setText("Bills:             " + player1.getBills() + " Euros");
        bill1.repaint();
        bill1.revalidate();
        money2.setText("Money:             " + player2.getAvailableMoney() + " Euros");
        money2.repaint();
        money2.revalidate();
        loan2.setText("Loan:                " + player2.getLoans() + " Euros");
        loan2.repaint();
        loan2.revalidate();
        bill2.setText("Bills:             " + player2.getBills() + " Euros");
        bill2.repaint();
        bill2.revalidate();
        jStr.setText("Jackpot: " + Controller.jack_pos.getJackpotMon() + " Euros");
    }
}
package ui;

import model.Team;
import ui.exception.NoTeamException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//creates UI of the Team
public class TeamSelectUI extends JPanel {

    //fields
    private JButton addBtn;
    private JButton checkBtn;
    private JButton bookBtn;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 50;

    private Team team;

    //constructor
    //EFFECTS: create TeamSelectUI
    public TeamSelectUI(Team team, Component parent) throws NoTeamException {
        super();

        if (team == null) {
            throw new NoTeamException();
        } else {
            this.team = team;

            setButtons();
            JPanel selectArea = new JPanel();
            selectArea.add(addBtn);
            selectArea.add(checkBtn);
            selectArea.add(bookBtn);
            add(selectArea, BorderLayout.CENTER);
            setLocation((parent.getWidth() - WIDTH) / 2, (parent.getHeight() - HEIGHT) / 2);
            setSize(WIDTH, HEIGHT);
            setBackground(Color.ORANGE);
            selectArea.setBackground(Color.ORANGE);

            setVisible(true);
        }
    }

    //MODIFIES: addBtn, checkBtn, bookBtn
    //EFFECTS: set buttons required for the team UI
    private void setButtons() {
        addBtn = new JButton(new AddMatchAction());
        checkBtn = new JButton(new CheckMatchAction());
        bookBtn = new JButton(new BookedMatchAction());
    }

    //creates AddMatchAction
    private class AddMatchAction extends AbstractAction {
        AddMatchAction() {
            super("Add Match");
        }

        //EFFECTS: create match to this.team with consumed information
        @Override
        public void actionPerformed(ActionEvent evt) {
            String date = JOptionPane.showInputDialog(null,
                    "Input Match Date in YYYY-MM-DD form", "Enter match date",
                    JOptionPane.QUESTION_MESSAGE);
            String time = JOptionPane.showInputDialog(null,
                    "Input Match Time in 4 digit number(ex. 5pm -> 1700)", "Enter match time",
                    JOptionPane.QUESTION_MESSAGE);
            String oppTeam = JOptionPane.showInputDialog(null,
                    "Input opposing team name", "Enter opposing team", JOptionPane.QUESTION_MESSAGE);
            String booking = JOptionPane.showInputDialog(null,
                    "If you booked the match, input 'y', otherwise 'n'", "Enter booking state",
                    JOptionPane.QUESTION_MESSAGE);
            String result = JOptionPane.showInputDialog(null,
                    "Input 'w' if won, 'l' if lost, 'n' for not decided", "Enter result",
                    JOptionPane.QUESTION_MESSAGE);
            String impression = JOptionPane.showInputDialog(null,
                    "Input impression on the match", "Enter impression", JOptionPane.QUESTION_MESSAGE);
            if (date != null) {
                if (booking.equals("y")) {
                    team.addMatch(date, Double.parseDouble(time), oppTeam, true, result, impression);
                } else {
                    team.addMatch(date, Double.parseDouble(time), oppTeam, false, result, impression);
                }
            }
        }
    }

    //creates CheckMatchAction
    private class CheckMatchAction extends AbstractAction {
        CheckMatchAction() {
            super("Check Match");
        }

        //EFFECTS: find the match at given date
        @Override
        public void actionPerformed(ActionEvent evt) {
            String date = JOptionPane.showInputDialog(null,
                    "Input Match Date in YYYY-MM-DD form", "Enter match date",
                    JOptionPane.QUESTION_MESSAGE);
            JOptionPane.showMessageDialog(null, team.checkMatch(date), "Match Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //creates BookedMatchAction
    private class BookedMatchAction extends AbstractAction {
        BookedMatchAction() {
            super("Check All Booked Match");
        }

        //EFFECTS: show all the booked matches in this.team
        @Override
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(null, team.allBookedMatch(), "Booked Matches",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

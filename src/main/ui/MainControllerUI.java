package ui;

import model.Team;
import model.EventLog;
import ui.exception.NoTeamException;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

//Main UI
public class MainControllerUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final JDesktopPane desktop;

    private final JButton createBtn;
    private final JButton selectBtn;

    private TeamController teamController;

    //constructor
    //EFFECTS: constructs Main UI
    public MainControllerUI() {
        teamController = new TeamController();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        createBtn = new JButton("Create Team");
        selectBtn = new JButton("Select Team");

        desktop = new JDesktopPane();
        desktop.setBackground(Color.ORANGE);

        setContentPane(desktop);
        setTitle("My Favourite Sports Teams");
        setSize(WIDTH, HEIGHT);

        setVisible(true);

        addButtons();
        addMenu();

        centreOnScreen();
    }

    //MODIFIES: desktop
    //EFFECTS: add Buttons to JDesktopPane
    private void addButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.add(createBtn);
        buttonArea.add(selectBtn);
        buttonArea.setLayout(new GridLayout(0, 2));
        buttonArea.setSize(new Dimension(300, 50));
        add(buttonArea, BorderLayout.CENTER);
        createBtn.addActionListener(new CreateTeamAction());
        selectBtn.addActionListener(new SelectTeamAction());
        buttonArea.setLocation((WIDTH - 300) / 2, (HEIGHT - 100) / 2);
    }

    //MODIFIES: desktop
    //EFFECTS: add menus to JDesktopPane
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu saveMenu = new JMenu("Save");
        saveMenu.setMnemonic('S');
        addMenuItem(saveMenu, new SaveAction(), KeyStroke.getKeyStroke("control S"));
        menuBar.add(saveMenu);
        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic('L');
        addMenuItem(loadMenu, new LoadAction(), KeyStroke.getKeyStroke("control L"));
        addMenuItem(loadMenu, new LoadMainAction(), KeyStroke.getKeyStroke("control Z"));
        menuBar.add(loadMenu);
        JMenu logMenu = new JMenu("Log");
        addMenuItem(logMenu, new ShowLogAction(), null);
        addMenuItem(logMenu, new ClearLogAction(), null);
        menuBar.add(logMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }

    //MODIFIES: menu
    //EFFECTS: add menu items to menu
    private void addMenuItem(JMenu menu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        menu.add(menuItem);
    }

    //EFFECTS: set desktop to centre of the screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    //EFFECTS: set location and size properties of given JLabel
    private void setJLabel(JLabel label, int locX, int locY, int sizeX, int sizeY) {
        label.setLocation(locX, locY);
        label.setSize(sizeX, sizeY);
    }

    //EFFECTS: add consumed ImageIcon to desktop as much as the the number of the bookings in given team
    private void addImage(int count, ImageIcon img) {
        for (int i = 0; i < count; i++) {
            JLabel label = new JLabel(img);
            setJLabel(label, 10 + (i * 20), 40, 20, 20);
            desktop.add(label);
            label.revalidate();
            label.repaint();
        }
    }

    //MODIFIES: this
    //EFFECTS: exit the program and print the log in console
    @Override
    public void dispose() {
        ConsolePrinter cp = new ConsolePrinter();
        cp.printLog(EventLog.getInstance());
        super.dispose();
    }

    //MODIFIES: this
    //EFFECTS: exit the program without printing the log
    public void dispose(ActionEvent evt) {
        super.dispose();
    }

    //EFFECTS: create MainControllerUI instance
    public static void main(String[] args) {
        new MainControllerUI();
    }

    //creates CreateTeamAction
    private class CreateTeamAction extends AbstractAction {
        CreateTeamAction() {
            super("Create Team");
        }

        //EFFECTS: get team name input and create team
        @Override
        public void actionPerformed(ActionEvent evt) {
            String createTeam = JOptionPane.showInputDialog(null,
                    "Input Team Name", "Create Team", JOptionPane.QUESTION_MESSAGE);
            if (createTeam != null) {
                Team team = new Team(createTeam);
                teamController.addTeam(team);
                //desktop.add(new TeamCreateTool());
            }
        }
    }

    //creates SelectTeamAction
    private class SelectTeamAction extends AbstractAction {
        SelectTeamAction() {
            super("Select Team");
        }

        //EFFECTS: create TeamSelectUI with a Team that matches the consumed team name
        @Override
        public void actionPerformed(ActionEvent evt) {
            String selectTeam = JOptionPane.showInputDialog(null,
                    "Input Team Name", "Select Team", JOptionPane.QUESTION_MESSAGE);
            try {
                desktop.removeAll();
                desktop.add(new TeamSelectUI(teamController.findTeam(selectTeam), MainControllerUI.this));
                JLabel teamName = new JLabel(selectTeam);
                setJLabel(teamName, 10, 10, 100, 20);
                teamName.setFont(new Font("Monospaced", Font.BOLD, 20));
                desktop.add(teamName);
                addImage(teamController.findTeam(selectTeam).countBooking(),
                        new ImageIcon("src/main/ui/image/Star.jpg"));
                setTitle(selectTeam);
                setVisible(true);
                desktop.revalidate();
                desktop.repaint();
            } catch (NoTeamException e) {
                JOptionPane.showMessageDialog(null, "Given team does not exist", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //creates SaveAction
    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Data");
        }

        //EFFECTS: save the current information
        @Override
        public void actionPerformed(ActionEvent evt) {
            teamController.saveTeamList();
            JOptionPane.showMessageDialog(null, "Data Saved", "Save Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //creates LoadAction
    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Data");
        }

        //EFFECTS: load the teams saved
        @Override
        public void actionPerformed(ActionEvent evt) {
            teamController.loadTeamList();
            JOptionPane.showMessageDialog(null, "Data Loaded", "Load Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //creates LoadMainAction
    private class LoadMainAction extends AbstractAction {
        LoadMainAction() {
            super("Load Main Page");
        }

        //EFFECTS: go to the first set of MainControllerUI
        @Override
        public void actionPerformed(ActionEvent evt) {
            dispose(evt);
            new MainControllerUI();
        }
    }

    //creates ShowLogAction
    private class ShowLogAction extends AbstractAction {
        ShowLogAction() {
            super("Print log");
        }

        //MODIFIES: this
        //EFFECTS: create ScreenPrinter(ScrollPane for EventLog) and print log
        @Override
        public void actionPerformed(ActionEvent evt) {
            LogPrinter lp;
            lp = new ScreenPrinter(MainControllerUI.this);
            desktop.add((ScreenPrinter) lp);
            lp.printLog(EventLog.getInstance());
        }
    }

    //creates ClearLogAction
    private class ClearLogAction extends AbstractAction {
        ClearLogAction() {
            super("Clear log");
        }

        //MODIFIES: this, lp
        //EFFECTS: clear all the log in lp
        @Override
        public void actionPerformed(ActionEvent evt) {
            EventLog.getInstance().clear();
        }
    }

}

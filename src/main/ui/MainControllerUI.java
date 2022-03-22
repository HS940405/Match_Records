package ui;

import model.Team;
import model.exception.NoTeamException;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class MainControllerUI extends JFrame  {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final JDesktopPane desktop;

    private final JButton createBtn;
    private final JButton selectBtn;

    private TeamController teamController;

    public MainControllerUI() {
        teamController = new TeamController();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu saveMenu = new JMenu("Save");
        saveMenu.setMnemonic('S');
        addMenuItem(saveMenu, new SaveAction(), KeyStroke.getKeyStroke("control S"));
        menuBar.add(saveMenu);
        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic('L');
        addMenuItem(loadMenu, new LoadAction(), KeyStroke.getKeyStroke("control L"));
        menuBar.add(loadMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }

    private void addMenuItem(JMenu menu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        menu.add(menuItem);
    }

    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public static void main(String[] args) {
        new MainControllerUI();
    }

    private class CreateTeamAction extends AbstractAction {
        CreateTeamAction() {
            super("Create Team");
        }

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

    private class SelectTeamAction extends AbstractAction {
        SelectTeamAction() {
            super("Select Team");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String selectTeam = JOptionPane.showInputDialog(null,
                    "Input Team Name", "Select Team", JOptionPane.QUESTION_MESSAGE);
            try {
                desktop.add(new TeamSelectUI(teamController.findTeam(selectTeam), MainControllerUI.this));
                desktop.revalidate();
                desktop.repaint();
            } catch (NoTeamException e) {
                JOptionPane.showMessageDialog(null, "Given team does not exist", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Data");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            teamController.saveTeamList();
            JOptionPane.showMessageDialog(null, "Data Saved", "Save Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Data");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            teamController.loadTeamList();
            JOptionPane.showMessageDialog(null, "Data Loaded", "Load Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


}

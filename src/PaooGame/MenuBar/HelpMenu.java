package PaooGame.MenuBar;

import PaooGame.Exceptions.InOutExceptions;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/*! \class HelpMenu extends JMenu
    \brief Implementeaza butoanele meniului Help Menu
 */
public class HelpMenu extends JMenu {
    private JFrame              wndFrame;

    public HelpMenu() {
        super("Help");

        JMenuItem instructions = new JMenuItem("How to play");
        add(instructions);
        instructions.addActionListener(e -> {
            try {
                BuildHowToPlayWindow();
            } catch (InOutExceptions ex) {
                throw new RuntimeException(ex);
            }
        });

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> {
            try {
                BuildAboutWindow();
            } catch (InOutExceptions ex) {
                throw new RuntimeException(ex);
            }
        });
        add(about);

    }

    private JTextArea createCenteredTextArea(ArrayList<String> lines) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Comic Sans", Font.BOLD, 16));
        for (String line : lines) {
            textArea.append(line + "\n");
        }
        return textArea;
    }

    public void BuildAboutWindow() throws InOutExceptions {
        wndFrame = new JFrame("About");
        wndFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wndFrame.setAlwaysOnTop(true);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);

        ArrayList<String> aboutMsg = loadFile("D:\\Joc PAOO\\BombermanPAOO\\res\\About.txt");

        JTextArea aboutLabel = createCenteredTextArea(aboutMsg);
        wndFrame.getContentPane().add(aboutLabel, BorderLayout.CENTER);
        wndFrame.setBackground(Color.GRAY);
        wndFrame.pack();
        wndFrame.setVisible(true);
    }

    public void BuildHowToPlayWindow() throws InOutExceptions {
        wndFrame = new JFrame("How To Play");
        wndFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setAlwaysOnTop(true);
        wndFrame.setLocationRelativeTo(null);

        ArrayList<String> howToPlayMsg = loadFile("D:\\Joc PAOO\\BombermanPAOO\\res\\How To Play.txt");

        JTextArea howToPlayLabel = createCenteredTextArea(howToPlayMsg);
        wndFrame.getContentPane().add(howToPlayLabel, BorderLayout.CENTER);
        wndFrame.pack();
        wndFrame.setVisible(true);
    }

    private ArrayList<String> loadFile(String path) throws InOutExceptions {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            throw new InOutExceptions("Eroare la incarcarea fisierului " + path + "!");
        }
        return lines;
    }

    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sons", Font.BOLD, 16));
        return label;
    }

}


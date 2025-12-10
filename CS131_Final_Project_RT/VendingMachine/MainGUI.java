package VendingMachine;

import javax.swing.*;
import java.awt.*;
/**
 * Welcome screen GUI that launches the vending machine store.
 */


public class MainGUI {
    private JFrame frame;

    public MainGUI() {
        // ===  Welcome Screen ===
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // CENTER welcome msg
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel(
            "<html><center>===> Welcome to REU REU Vending Machine! <===<br>" +
            "Press Enter or click the button to continue...</center></html>",
            SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        	// if user was to click enter button action  e launch Store.
        JButton enterButton = new JButton("Enter Store");
        enterButton.addActionListener(e -> launchStoreGUI());
        panel.add(enterButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // Also allow pressing Enter key
        frame.getRootPane().setDefaultButton(enterButton);
    }

    private void launchStoreGUI() {
        frame.dispose(); 
        //store Gui
        
        new VendingGUI();         
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
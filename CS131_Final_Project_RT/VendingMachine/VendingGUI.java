package VendingMachine;

import javax.swing.*;
import java.awt.*;
/**
 * Launches A GUI image of vending machine using all of maain.java code and jframe. 
 * Custom image 
 */

public class VendingGUI extends JFrame {

    private Store store;
    private static double balance = 8.12;
    private JTextArea output;
    private JTextArea purchasedItems;
    private JTextField codeInput;
    private JLabel balanceLabel;

    public VendingGUI() {
    // ======= STORE SETUP =======
        store = new Store("Reuben Vending Machine!");
        store.addSnacks(new Candy("Snickers", 2.55));
        store.addSnacks(new Candy("M&M's Peanut", 1.75));
        store.addSnacks(new Candy("Twix", 2.85));
         store.addSnacks(new Cookie("Nutter Butter", 1.85));
        store.addSnacks(new Cookie("Oreos", 2.25));
        store.addSnacks(new Cookie("Girl Scout Cookie", 3.00));
        store.addSnacks(new Chip("Lays Classic", 1.75));
        store.addSnacks(new Chip("Doritos", 2.15));
        store.addSnacks(new Chip("Fritos Original", 1.60));

       
        setTitle(store.getStoreName());
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        // center
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 650));
        add(layeredPane, BorderLayout.CENTER);

        //image/ background 
        ImageIcon bgIcon = new ImageIcon("C:/Users/muffi/OneDrive/Pictures/F#.png");
        if (bgIcon.getIconWidth() == -1) {
            System.out.println("Background image failed to load!");
        }
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        layeredPane.add(bgLabel, Integer.valueOf(0));

        //text, font color of balance
        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", balance));
        balanceLabel.setForeground(Color.GREEN);
        balanceLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setBounds(0, 20, layeredPane.getWidth(), 30);
        layeredPane.add(balanceLabel, Integer.valueOf(2));

        // getting user Input, location 
        codeInput = new JTextField();
        codeInput.setBounds(250, 70, 120, 30);
        layeredPane.add(codeInput, Integer.valueOf(2));

        JButton enterCodeBtn = new JButton("Enter Code");
        enterCodeBtn.setBounds(380, 70, 120, 30);
        enterCodeBtn.addActionListener(e -> handleCode());
        layeredPane.add(enterCodeBtn, Integer.valueOf(2));

        // output 
        output = new JTextArea();
        output.setEditable(false);
        output.setForeground(Color.GREEN);
        output.setBackground(new Color(0, 0, 0, 180));
        output.setFont(new Font("Consolas", Font.PLAIN, 14));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        JScrollPane outputScroll = new JScrollPane(output);
        outputScroll.setBounds(150, 110, 400, 70);
        layeredPane.add(outputScroll, Integer.valueOf(2));

        // History 
        purchasedItems = new JTextArea();
        purchasedItems.setEditable(false);
        purchasedItems.setForeground(Color.WHITE);
        purchasedItems.setBackground(new Color(0, 0, 0, 180));
        purchasedItems.setFont(new Font("Consolas", Font.PLAIN, 14));
        purchasedItems.setLineWrap(true);
        purchasedItems.setWrapStyleWord(true);
        JScrollPane purchasedScroll = new JScrollPane(purchasedItems);
        purchasedScroll.setPreferredSize(new Dimension(180, 650));
        add(purchasedScroll, BorderLayout.EAST);

        // always size 
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int w = layeredPane.getWidth();
                int h = layeredPane.getHeight();

                // scale background
                Image bgImg = bgIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                bgLabel.setIcon(new ImageIcon(bgImg));
                bgLabel.setBounds(0, 0, w, h);

                // center balance
                balanceLabel.setBounds(0, 20, w, 30);

                // input & button location 
                int gap = 8;
                int inputW = 120;
                int btnW = 120;
                int centerX = w / 2;
                int inputX = centerX - (inputW + gap + btnW) / 2;
                int inputY = 60;
                codeInput.setBounds(inputX, inputY, inputW, 30);
                enterCodeBtn.setBounds(inputX + inputW + gap, inputY, btnW, 30);

                // output location 
                outputScroll.setBounds(centerX - 200, inputY + 40, 400, 70);
            }
        });
        
        

        SwingUtilities.invokeLater(() -> {
            this.dispatchEvent(new java.awt.event.ComponentEvent(this, java.awt.event.ComponentEvent.COMPONENT_RESIZED));
        });

        setVisible(true);
    }

    private void handleCode() {
        String code = codeInput.getText().trim().toUpperCase();
        int index = store.codeIndex(code);

        if (index == -1) {
            print("Invalid code. Try A1–C3.");
            return;
        }
        purchase(index);
    }

    private void purchase(int index) {
        Snack selected = store.getSnack(index);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Buy " + selected.getName() + " ($" + selected.getPrice() + ")?",
                "Confirm Purchase",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (balance >= selected.getPrice()) {
                balance -= selected.getPrice();
                store.addPurchase(selected);
                print(" Bought: " + selected.getName());
                purchasedItems.append(selected.getName() + " - $" + String.format("%.2f", selected.getPrice()) + "\n");
                updateBalance();
            } else {
                print("Not enough balance.");
            }
        } else if (confirm == JOptionPane.NO_OPTION) {
            if (balance >= 0.25) {
                balance -= 0.25;
                print("$0.25 fee for not buying the snack.");
                purchasedItems.append("Skipped snack fee - $0.25\n");
                updateBalance();
            } else {
                print(" Not enough balance to deduct $0.25 fee.");
            } 

                
            }
        }
   

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
        print("Balance: $" + String.format("%.2f", balance));
    }

    private void print(String msg) {
        output.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VendingGUI::new);
    

    

    
}
}



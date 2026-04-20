import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton equalsButton, clearButton, deleteButton, dotButton;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        layoutComponents();
        addEventListeners();
    }

    private void initComponents() {
        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 25));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setText("0");

        // Number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberButtons[i].setFocusable(false);
        }

        // Operator buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("AC");
        deleteButton = new JButton("Del");
        dotButton = new JButton(".");

        operatorButtons = new JButton[]{
            addButton, subButton, mulButton, divButton,
            equalsButton, clearButton, deleteButton, dotButton
        };

        // Set font and properties for operator buttons
        for (JButton button : operatorButtons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusable(false);
        }

        // Set colors
        for (JButton button : numberButtons) {
            button.setBackground(Color.WHITE);
        }

        addButton.setBackground(new Color(255, 165, 0));
        subButton.setBackground(new Color(255, 165, 0));
        mulButton.setBackground(new Color(255, 165, 0));
        divButton.setBackground(new Color(255, 165, 0));
        equalsButton.setBackground(new Color(255, 165, 0));

        clearButton.setBackground(new Color(255, 99, 99));
        deleteButton.setBackground(new Color(255, 99, 99));
        dotButton.setBackground(Color.LIGHT_GRAY);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Display panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        display.setPreferredSize(new Dimension(0, 60));
        displayPanel.add(display);
        add(displayPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Row 1: AC, Del, /, *
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(divButton);
        buttonPanel.add(mulButton);

        // Row 2: 7, 8, 9, -
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(subButton);

        // Row 3: 4, 5, 6, +
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(addButton);

        // Row 4: 1, 2, 3, =
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(equalsButton);

        // Row 5: 0 (span 2), ., = (continue from row 4)
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(dotButton);
        // Add empty panels to maintain grid
        buttonPanel.add(new JPanel());
        buttonPanel.add(new JPanel());

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        // Add listeners for number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }

        // Add listeners for operator buttons
        for (JButton button : operatorButtons) {
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (display.getText().equals("0")) {
                    display.setText(String.valueOf(i));
                } else {
                    display.setText(display.getText() + i);
                }
            }
        }

        // Dot button
        if (e.getSource() == dotButton) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        // Operator buttons
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("0");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("0");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("0");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("0");
        }

        // Equals button
        if (e.getSource() == equalsButton) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Cannot divide by zero!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
            }

            // Format result to remove unnecessary decimal places
            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }

            num1 = result;
        }

        // Clear button
        if (e.getSource() == clearButton) {
            display.setText("0");
            num1 = num2 = result = 0;
        }

        // Delete button
        if (e.getSource() == deleteButton) {
            String currentText = display.getText();
            if (currentText.length() > 1) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                display.setText("0");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
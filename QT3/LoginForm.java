import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel titleLabel;

    public LoginForm() {
        setTitle("Demo application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        layoutComponents();
        addEventListeners();
    }

    private void initComponents() {
        titleLabel = new JLabel("Demo application");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        userLabel = new JLabel("User");
        passwordLabel = new JLabel("Password");

        userField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("login");
        registerButton = new JButton("register");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main panel with form fields
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // User field
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(userLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(userField, gbc);

        // Password field
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addEventListeners() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please enter both username and password!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                // Simple login check
                if (username.equals("admin") && password.equals("123456")) {
                    JOptionPane.showMessageDialog(this,
                        "Login successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Invalid username or password!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == registerButton) {
            JOptionPane.showMessageDialog(this,
                "Register function will be developed later!",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
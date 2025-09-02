// LibrarySystem.java
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

public class LibrarySystem {
    private static final Color BG_PRIMARY = new Color(240, 248, 255); 
    private static final Color BG_SECONDARY = new Color(220, 235, 250);
    private static final Color BTN_BLUE = new Color(70, 130, 180); 
    private static final Color BTN_TEXT = Color.WHITE;
    static List<Book> books;
    static List<Borrow> borrowings;

    public static void main(String[] args) {
        books = DataManager.loadBooksFromFile();
        borrowings = DataManager.loadBorrowingsFromFile();
        SwingUtilities.invokeLater(LibrarySystem::showMainMenu);
    }

    static void showMainMenu() {
        JFrame frame = new JFrame("Perpustakaan Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        // Panel luar
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(BG_PRIMARY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Panel utama dengan sudut membulat
        JPanel mainPanel = new RoundedPanel(30);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BG_SECONDARY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel titleLabel = new JLabel("Selamat Datang di Perpustakaan");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton adminButton = new JButton("Login Admin");
        JButton userButton = new JButton("Login User");
        JButton exitButton = new JButton("Keluar");

        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 24);
        Dimension buttonSize = new Dimension(400, 60);

        for (JButton button : new JButton[] { adminButton, userButton, exitButton }) {
            button.setFont(buttonFont);
            button.setMaximumSize(buttonSize);
            button.setPreferredSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setBackground(BTN_BLUE);
            button.setForeground(BTN_TEXT);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Hover effect
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(BTN_BLUE.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(BTN_BLUE);
                }
            });
        }

        // Action listeners

        // login admin
        adminButton.addActionListener(e -> {
            frame.dispose();
            AdminGUI.showAdminLogin();
        });

        // login user
        userButton.addActionListener(e -> {
            frame.dispose();
            UserGUI.showUserLogin();
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Tambahkan komponen ke mainPanel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(60));
        mainPanel.add(adminButton);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(userButton);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(exitButton);

        outerPanel.add(mainPanel, gbc);
        frame.setContentPane(outerPanel);
        frame.setVisible(true);
    }
}

// Tambahkan RoundedPanel class di bawah ini
class RoundedPanel extends JPanel {
    private final int cornerRadius;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcs.width, arcs.height);
    }
}
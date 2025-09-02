// AdminGUI.java
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminGUI {
    private static final Color BG_PRIMARY = new Color(240, 248, 255); // AliceBlue
    private static final Color BG_SECONDARY = new Color(220, 235, 250);
    private static final Color BTN_BLUE = new Color(70, 130, 180); // SteelBlue
    private static final Color BTN_TEXT = Color.WHITE;

    static void showAdminLogin() {
        JFrame frame = new JFrame("Login Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen

        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(BG_PRIMARY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("Login Admin", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setBackground(BG_SECONDARY);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JTextField userField = new JTextField();
        userField.setFont(new Font("Arial", Font.PLAIN, 24));
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Kembali");
        styleButton(loginButton);
        styleButton(backButton);

        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        Dimension buttonSize = new Dimension(200, 50);
        loginButton.setFont(buttonFont);
        loginButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(buttonSize);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if ("admin".equals(username) && "admin123".equals(password)) {
                frame.dispose();
                showAdminPanel();
            } else {
                JOptionPane.showMessageDialog(frame, "Username atau password salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            LibrarySystem.showMainMenu();
        });

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(backButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_PRIMARY);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20)); // spasi
        mainPanel.add(panel);
        outerPanel.add(mainPanel, gbc);
        
        frame.setContentPane(outerPanel);
        frame.setVisible(true);
    }
    
    static void showAdminPanel() {
        JFrame frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(BG_PRIMARY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setBackground(BG_SECONDARY);
        
        JLabel titleLabel = new JLabel("Admin Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton addBookButton = new JButton("Tambah Buku");
        JButton viewBooksButton = new JButton("List Buku");
        JButton viewBorrowingsButton = new JButton("List Peminjaman");
        JButton backButton = new JButton("Kembali ke Main Menu");
        
        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(500, 70);
        
        for (JButton button : new JButton[]{addBookButton, viewBooksButton, viewBorrowingsButton, backButton}) {
            button.setFont(buttonFont);
            button.setMaximumSize(buttonSize);
            button.setPreferredSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            styleButton(button);
        }

        addBookButton.addActionListener(e -> showAddBookDialog(frame));
        viewBooksButton.addActionListener(e -> showBooksTable());
        viewBorrowingsButton.addActionListener(e -> showBorrowerList());
        backButton.addActionListener(e -> {
            frame.dispose();
            LibrarySystem.showMainMenu();
        });
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(60)); // Jarak setelah judul
        panel.add(addBookButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(viewBooksButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(viewBorrowingsButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(backButton);
        
        outerPanel.add(panel, gbc);
        frame.setContentPane(outerPanel);
        frame.setVisible(true);
    }

    static void showAddBookDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Tambah Buku", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(parent);
        dialog.getContentPane().setBackground(BG_PRIMARY);

        JPanel panel = new JPanel(new GridLayout(5, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField stockField = new JTextField();
        
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        titleField.setFont(fieldFont);
        authorField.setFont(fieldFont);
        yearField.setFont(fieldFont);
        stockField.setFont(fieldFont);

        JButton addButton = new JButton("Tambah Buku");
        JButton cancelButton = new JButton("Cancel");
        
        addButton.setFont(fieldFont);
        cancelButton.setFont(fieldFont);
        styleButton(addButton);
        styleButton(cancelButton);

        addButton.addActionListener(e -> {
            try {
                String judul = titleField.getText().trim();
                String penulis = authorField.getText().trim();
                int tahun = Integer.parseInt(yearField.getText().trim());
                int stok = Integer.parseInt(stockField.getText().trim());

                if (judul.isEmpty() || penulis.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Data tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                LibrarySystem.books.add(new Book(judul, penulis, tahun, stok));
                DataManager.saveBooksToFile(LibrarySystem.books);
                JOptionPane.showMessageDialog(dialog, "Buku berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Tahun dan stok harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        panel.add(new JLabel("Judul:"));
        panel.add(titleField);
        panel.add(new JLabel("Penulis:"));
        panel.add(authorField);
        panel.add(new JLabel("Tahun:"));
        panel.add(yearField);
        panel.add(new JLabel("Stok:"));
        panel.add(stockField);
        panel.add(addButton);
        panel.add(cancelButton);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }

    static void showBooksTable() {
        JFrame frame = new JFrame("List Buku");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BG_PRIMARY);
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(BG_SECONDARY);

        JLabel searchLabel = new JLabel("Cari Judul:");
        JTextField searchField = new JTextField(30);
        JButton searchButton = new JButton("Cari");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        String[] columns = {"Judul", "Penulis", "Tahun", "Stok"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(28);

        for (Book book : LibrarySystem.books) {
            model.addRow(new Object[]{book.judul, book.penulis, book.tahun, book.stok});
        }
        
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            model.setRowCount(0);
            for (Book book : LibrarySystem.books) {
                if (book.judul.toLowerCase().contains(keyword)) {
                    model.addRow(new Object[]{book.judul, book.penulis, book.tahun, book.stok});
                }
            }
        });

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static void showBorrowerList() {
        JFrame frame = new JFrame("List Peminjaman");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BG_PRIMARY);
        
        // Top panel: untuk search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BG_SECONDARY);
        JLabel searchLabel = new JLabel("Cari Nama: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(searchLabel, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        // Tabel dan model
        String[] columns = {"ID", "Nama", "NIM", "Prodi", "Judul Buku", "Tanggal Pinjam", "Tanggal Kembali", "Denda"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);

        // Fungsi update data
        Runnable updateTable = () -> {
            String keyword = searchField.getText().trim().toLowerCase();
            model.setRowCount(0); // Clear table
            for (Borrow borrow : LibrarySystem.borrowings) {
                if (borrow.nama.toLowerCase().contains(keyword)) {
                    String kembali = (borrow.tanggalKembali != null) ? borrow.tanggalKembali.toString() : "Belum kembali";
                    model.addRow(new Object[]{
                        borrow.id,
                        borrow.nama,
                        borrow.nim,
                        borrow.prodi,
                        borrow.book.judul,
                        borrow.tanggalPinjam,
                        kembali,
                        "Rp " + borrow.calculateFine()
                    });
                }
            }
        };

        // Ketika user mengetik di kolom pencarian
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateTable.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateTable.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateTable.run(); }
        });
        
        // Load awal
        updateTable.run();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private static void styleButton(JButton btn) {
        btn.setBackground(BTN_BLUE);
        btn.setForeground(BTN_TEXT);
        btn.setFocusPainted(false);
    }
}
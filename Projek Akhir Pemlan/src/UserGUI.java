// UserGUI.java
import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserGUI {
    private static final Color BG_PRIMARY = new Color(240, 248, 255); // AliceBlue
    private static final Color BG_SECONDARY = new Color(220, 235, 250);
    private static final Color BTN_BLUE = new Color(70, 130, 180); // SteelBlue
    private static final Color BTN_TEXT = Color.WHITE;

    static void showUserLogin() {
        JFrame frame = new JFrame("Login User");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(BG_PRIMARY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("Login User", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setBackground(BG_SECONDARY);

        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        JLabel nimLabel = new JLabel("NIM:");
        nimLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JTextField nimField = new JTextField();
        nimField.setFont(new Font("Arial", Font.PLAIN, 24));
        JLabel prodiLabel = new JLabel("Program Studi:");
        prodiLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JTextField prodiField = new JTextField();
        prodiField.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Kembali");

        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        Dimension buttonSize = new Dimension(200, 50);

        loginButton.setFont(buttonFont);
        loginButton.setPreferredSize(buttonSize);
        loginButton.setBackground(BTN_BLUE);
        loginButton.setForeground(BTN_TEXT);
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(buttonSize);
        backButton.setBackground(BTN_BLUE);
        backButton.setForeground(BTN_TEXT);

        loginButton.addActionListener(e -> {
            String nama = nameField.getText().trim();
            String nim = nimField.getText().trim();
            String prodi = prodiField.getText().trim();
            if (nama.isEmpty() || nim.isEmpty() || prodi.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            frame.dispose();
            showBorrowMenu(nama, nim, prodi);
        });
        
        backButton.addActionListener(e -> {
            frame.dispose();
            LibrarySystem.showMainMenu();
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(nimLabel);
        panel.add(nimField);
        panel.add(prodiLabel);
        panel.add(prodiField);
        panel.add(loginButton);
        panel.add(backButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_PRIMARY);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(panel);
        
        outerPanel.add(mainPanel, gbc);
        frame.setContentPane(outerPanel);
        frame.setVisible(true);
    }
    
    static void showBorrowMenu(String nama, String nim, String prodi) {
        JFrame frame = new JFrame("Menu - " + nama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridBagLayout());
        frame.setBackground(BG_PRIMARY);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(500, 400));
        panel.setBackground(BG_SECONDARY);

        JLabel welcomeLabel = new JLabel("Selamat datang, " + nama + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        Dimension buttonSize = new Dimension(400, 60);

        JButton borrowButton = new JButton("Pinjam Buku");
        JButton returnButton = new JButton("Kembalikan Buku");
        JButton backButton = new JButton("Kembali ke Main Menu");

        panel.add(Box.createVerticalStrut(30));
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(30));

        for (JButton b : new JButton[]{borrowButton, returnButton, backButton}) {
            b.setFont(buttonFont);
            b.setMaximumSize(buttonSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setBackground(BTN_BLUE);
            b.setForeground(BTN_TEXT);
            panel.add(Box.createVerticalStrut(20));
            panel.add(b);
        }
        
        borrowButton.addActionListener(e -> {
            frame.dispose();
            showBorrowBookDialog(nama, nim, prodi);
        });
        
        returnButton.addActionListener(e -> {
            frame.dispose();
            showReturnMenu(nama, nim, prodi);
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            LibrarySystem.showMainMenu();
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    static void showBorrowBookDialog(String nama, String nim, String prodi) {
        JFrame frame = new JFrame("Pinjam Buku");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setBackground(BG_PRIMARY);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JLabel searchLabel = new JLabel("Cari Buku: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField searchField = new JTextField(30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton searchButton = new JButton("Cari");
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setBackground(BTN_BLUE);
        searchButton.setForeground(BTN_TEXT);
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        String[] columns = {"Judul", "Penulis", "Tahun", "Stok"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);

        Runnable loadTable = () -> {
            String keyword = searchField.getText().trim().toLowerCase();
            model.setRowCount(0);
            for (Book book : LibrarySystem.books) {
                if (book.stok > 0 && (book.judul.toLowerCase().contains(keyword) || book.penulis.toLowerCase().contains(keyword))) {
                    model.addRow(new Object[]{book.judul, book.penulis, book.tahun, book.stok});
                }
            }
        };

        loadTable.run();
        searchButton.addActionListener(e -> loadTable.run());
        
        JButton borrowButton = new JButton("Pinjam");
        JButton backButton = new JButton("Kembali");
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        Dimension buttonSize = new Dimension(200, 60);

        borrowButton.setFont(buttonFont);
        borrowButton.setPreferredSize(buttonSize);
        borrowButton.setBackground(BTN_BLUE);
        borrowButton.setForeground(BTN_TEXT);
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(buttonSize);
        backButton.setBackground(BTN_BLUE);
        backButton.setForeground(BTN_TEXT);
        
        borrowButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Pilih Buku yang mau dipinjam", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String judul = (String) table.getValueAt(selectedRow, 0);
            Book selectedBook = LibrarySystem.books.stream()
                .filter(book -> book.judul.equals(judul) && book.stok > 0)
                .findFirst().orElse(null);

            if (selectedBook != null) {
                selectedBook.stok--;
                String borrowId = "BRW" + System.currentTimeMillis();
                LibrarySystem.borrowings.add(new Borrow(nama, nim, prodi, borrowId, 
                    new Book(selectedBook.judul, selectedBook.penulis, selectedBook.tahun, 0), LocalDate.now()));
                DataManager.saveBooksToFile(LibrarySystem.books);
                DataManager.saveBorrowingsToFile(LibrarySystem.borrowings);
                JOptionPane.showMessageDialog(frame, "Peminjaman Buku sukses!\nBorrow ID: " + borrowId, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                showBorrowMenu(nama, nim, prodi);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            showBorrowMenu(nama, nim, prodi);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        bottomPanel.setBackground(BG_PRIMARY);
        bottomPanel.add(borrowButton);
        bottomPanel.add(backButton);

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    static void showReturnMenu(String nama, String nim, String prodi) {
        JFrame frame = new JFrame("Kembalikan Buku");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Panel atas: Search Bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        searchPanel.setBackground(BG_PRIMARY);
        JLabel searchLabel = new JLabel("Cari Buku: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField searchField = new JTextField(30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton searchButton = new JButton("Cari");
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchButton.setBackground(BTN_BLUE);
        searchButton.setForeground(BTN_TEXT);

        // Tabel pengembalian
        String[] columns = {"Borrow ID", "Judul Buku", "Tanggal Peminjaman", "Hari Dipinjam"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);
        
        Runnable loadTable = () -> {
            String keyword = searchField.getText().trim().toLowerCase();
            model.setRowCount(0);
            for (Borrow borrow : LibrarySystem.borrowings) {
                if (borrow.nim.equals(nim) && borrow.tanggalKembali == null) {
                    if (borrow.book.judul.toLowerCase().contains(keyword)) {
                        long daysBorrowed = Duration.between(borrow.tanggalPinjam.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
                        model.addRow(new Object[]{
                            borrow.id, borrow.book.judul, borrow.tanggalPinjam.toString(), daysBorrowed
                        });
                    }
                }
            }
        };

        loadTable.run();
        searchButton.addActionListener(e -> loadTable.run());
        
        // Tombol bawah
        JButton returnButton = new JButton("Kembalikan");
        JButton backButton = new JButton("Kembali");
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        Dimension buttonSize = new Dimension(200, 60);
        
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonSize);
        returnButton.setBackground(BTN_BLUE);
        returnButton.setForeground(BTN_TEXT);
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(buttonSize);
        backButton.setBackground(BTN_BLUE);
        backButton.setForeground(BTN_TEXT);
        
        returnButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Pilih buku yang ingin dikembalikan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String borrowId = (String) table.getValueAt(selectedRow, 0);
            Borrow selectedBorrow = LibrarySystem.borrowings.stream()
                .filter(borrow -> borrow.id.equals(borrowId))
                .findFirst().orElse(null);
            
            if (selectedBorrow != null) {
                selectedBorrow.tanggalKembali = LocalDate.now();
                Book originalBook = LibrarySystem.books.stream()
                    .filter(book -> book.judul.equals(selectedBorrow.book.judul))
                    .findFirst().orElse(null);

                if (originalBook != null) {
                    originalBook.stok++;
                }

                long fine = selectedBorrow.calculateFine();
                DataManager.saveBooksToFile(LibrarySystem.books);
                DataManager.saveBorrowingsToFile(LibrarySystem.borrowings);
                
                String message = "Pengembalian Buku sukses!";
                if (fine > 0) {
                    message += "\nDenda: Rp" + fine;
                }
                
                JOptionPane.showMessageDialog(frame, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                showBorrowMenu(nama, nim, prodi);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            showBorrowMenu(nama, nim, prodi);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        bottomPanel.add(returnButton);
        bottomPanel.add(backButton);

        // Gabungkan semua ke frame
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
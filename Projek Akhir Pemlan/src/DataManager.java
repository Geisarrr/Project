// DataManager.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String FILE_BUKU = "dataBuku.txt";
    private static final String FILE_PEMINJAMAN = "dataPeminjaman.txt";

    public static List<Book> loadBooksFromFile() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_BUKU))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                books.add(new Book(p[0], p[1], Integer.parseInt(p[2]), Integer.parseInt(p[3])));
            }
        } catch (IOException e) {

        }
        return books;
    }

    public static void saveBooksToFile(List<Book> books) {
        try (PrintWriter pw = new PrintWriter(FILE_BUKU)) {
            for (Book b : books) {
                pw.println(b.judul + ";" + b.penulis + ";" + b.tahun + ";" + b.stok);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Borrow> loadBorrowingsFromFile() {
        List<Borrow> borrowings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PEMINJAMAN))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length < 8) continue;
                Book book = new Book(p[4], p[5], Integer.parseInt(p[6]), 0);
                Borrow b = new Borrow(p[1], p[2], p[3], p[0], book, LocalDate.parse(p[7]));
                if (p.length > 8 && !p[8].isEmpty() && !"null".equals(p[8])) {
                    b.tanggalKembali = LocalDate.parse(p[8]);
                }
                borrowings.add(b);
            }
        } catch (IOException e) {
        }
        return borrowings;
    }

    public static void saveBorrowingsToFile(List<Borrow> borrowings) {
        try (PrintWriter pw = new PrintWriter(FILE_PEMINJAMAN)) {
            for (Borrow b : borrowings) {
                String tanggalKembaliString = (b.tanggalKembali != null) ? b.tanggalKembali.toString() : "";
                pw.println(b.id + ";" + b.nama + ";" + b.nim + ";" + b.prodi + ";" +
                    b.book.judul + ";" + b.book.penulis + ";" + b.book.tahun + ";" +
                    b.tanggalPinjam + ";" + tanggalKembaliString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
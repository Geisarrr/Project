
import java.time.Duration;
import java.time.LocalDate;

public class Borrow {
    String nama, nim, prodi;
    String id;
    Book book;
    LocalDate tanggalPinjam;
    LocalDate tanggalKembali;

    Borrow(String nama, String nim, String prodi, String id, Book book, LocalDate tanggalPinjam) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.id = id;
        this.book = book;
        this.tanggalPinjam = tanggalPinjam;
    }

    long calculateFine() {
        if (tanggalKembali == null) {
            return 0;
        }
        long days = Duration.between(tanggalPinjam.atStartOfDay(), tanggalKembali.atStartOfDay()).toDays();
        if (days > 5) {
            return (days - 5) * 2000;
        } else {
            return 0;
        }
    }
}
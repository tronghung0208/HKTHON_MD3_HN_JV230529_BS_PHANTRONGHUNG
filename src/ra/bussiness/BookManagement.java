package ra.bussiness;

import java.util.Arrays;
import java.util.Scanner;

public class BookManagement {
    static Book[] books = new Book[100];
    static int currentIndex = 0;
    static int bookId;
    static String bookName;
    static String author;
    static String descriptions;
    static double importPrice;
    static double exportPrice;
    static float interest;
    static boolean bookStatus = true;

    public static void main(String[] args) {
        Book book1 = new Book();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("*********************MENU********************");
            System.out.println("1.Nhập thông tin n sách và thông tin cho từng cuốn sách");
            System.out.println("2.Hiển thị thông tin sách");
            System.out.println("3.Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4.Xóa sách theo mã");
            System.out.println("5.Tìm kiếm tương đối theo tên sách");
            System.out.println("6.Thay đổi thông tin theo mã");
            System.out.println("7.Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    showBook();
                    break;
                case 3:
                    displaySortedProductsByAscendingProfit(books, currentIndex);
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchNameBook(books);
                    break;
                case 6:
                    updateBook(sc);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng nhập từ 1-9");
            }
        } while (true);
    }

    public static void showBook() {
        for (Book book : books
        ) {
            if (book != null) {
                book.displayData();
            } else {
                break;
            }
        }
    }

    public static void addProduct(Scanner sc) {

        System.out.println("Nhập số lượng sản phẩm muốn thêm");
        int n = Integer.parseInt(sc.nextLine());
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                Book book = new Book();
                book.inputData(sc, books, i);
                books[i] = book;
                books[i].setBookId(currentIndex + 1);
                currentIndex++;
            }
        } else {
            System.out.println("Số lượng sản phẩm phải lớn hơn 0");
        }
    }

    public static void displaySortedProductsByAscendingProfit(Book[] arrBooks, int currentIndex) {
        // Sử dụng thuật toán sắp xếp chèn (Insertion Sort) để sắp xếp mảng theo lợi nhuận tăng dần.
        for (int i = 1; i < currentIndex; i++) {
            Book key = arrBooks[i];
            int j = i - 1;
            while (j >= 0 && arrBooks[j].interestBook() > key.interestBook()) { // Thay đổi dấu '<' thành '>'
                arrBooks[j + 1] = arrBooks[j];
                j = j - 1;
            }
            arrBooks[j + 1] = key;
        }

        // Hiển thị danh sách sản phẩm đã sắp xếp.
        for (int i = 0; i < currentIndex; i++) {
            arrBooks[i].displayData();
            System.out.println("Lợi nhuận: " + arrBooks[i].interestBook());
        }
    }

    public static int searchIndexBook(Book[] books, int bookId) {
        int index = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getBookId() == bookId) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void deleteBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sách bạn muốn xóa");
        int deleteId = sc.nextInt();
        int indexDelete = searchIndexBook(books, deleteId);

        if (indexDelete == -1) {
            System.out.println("Mã sách không hợp lệ");
        } else {
            for (int i = indexDelete; i < currentIndex - 1; i++) {
                books[i] = books[i + 1];
            }
            books[currentIndex - 1] = null;
            currentIndex--; // Cập nhật currentIndex sau khi xóa
        }
    }

    public static void searchNameBook(Book[] books) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào tên sách");
        String bookNameSearch = sc.nextLine();

        int index = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getBookName().equalsIgnoreCase(bookNameSearch)) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            // In thông tin cuốn sách đã tìm thấy
            System.out.println("Thông tin sách bạn muốn tìm kiếm:");
            books[index].displayData();
        } else {
            System.out.println("Tên sách bạn muốn tìm kiếm không tồn tại");
        }
    }
    public static void updateBook(Scanner sc){
        System.out.println("Nhập vào mã sách");
        int bookId=Integer.parseInt(sc.nextLine());
        int indexUpdate=searchIndexBook(books,bookId);

        if (indexUpdate >= 0) {
            // Học sinh tồn tại trong danh sách
            System.out.println("Nhập thông tin mới cho sách:");
            books[indexUpdate].inputData(sc, books, indexUpdate); // Gọi hàm để nhập thông tin mới
            System.out.println("Thông tin sách đã được cập nhật.");
        } else {
            System.out.println("Sách bạn muốn sửa không có trong danh sách");
        }
    }

}

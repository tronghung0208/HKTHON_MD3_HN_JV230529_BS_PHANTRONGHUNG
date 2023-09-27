package ra.bussiness;

import java.util.Scanner;

public class Book {
    //1. Attribute-fields
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus = true;

    //2. Contructors
    public Book() {
    }

    public Book(int bookId, String bookName, String author, String descriptions, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    //3. Methods
    //3.1 setter - getter


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    // Phương thức thực hiên tính lợi nhuận
    public float interestBook() {
        return (float)( exportPrice - importPrice);
    }
    // Phương thức nhập thông tin

    public void inputData(Scanner sc, Book[] book,int currentIndex) {
        boolean isExistName = true;
        do {
            System.out.println("Nhập vào tên sách");
            String inputName = sc.nextLine();
            if (!inputName.trim().isEmpty()) {
                this.bookName = inputName;
                isExistName = false;
            } else {
                System.out.println("Tên sách không được để trống, vui lòng nhập lại");
            }

        } while (isExistName);

        boolean isExistAuthor = true;
        do {
            System.out.println("Nhập vào tên tác giả");
            String inputAuthor = sc.nextLine();
            if (!inputAuthor.trim().isEmpty()) {
                this.author = inputAuthor;
                isExistAuthor = false;
            } else {
                System.out.println("Tên tác giả không được để trống, vui lòng nhập lại");
            }

        } while (isExistAuthor);

        boolean isExistDescrip = true;
        do {
            System.out.println("Nhập vào mô tả về sách");
            String inputDescrip = sc.nextLine();
            if (!inputDescrip.trim().isEmpty()) {
                if (inputDescrip.length() >= 10) {
                    this.descriptions = inputDescrip;
                    isExistDescrip = false;
                }else {
                    System.out.println("Mô tả phải lớn hơn 10 kí tự, vui lòng nhập lại");
                }

            } else {
                System.out.println("Mô tả về sách không được để trống");
            }

        } while (isExistDescrip);

        boolean isExistImportPrice = true;
        do {
            System.out.println("Nhập vào giá nhập");
            double inputImportPrice =Double.parseDouble( sc.nextLine());
            if (inputImportPrice>0) {
                this.importPrice = inputImportPrice;
                isExistImportPrice = false;
            } else {
                System.out.println("Giá nhập phải lớn hơn 0, vui lòng nhập lại");
            }

        } while (isExistImportPrice);

        boolean isExistExportPrice = true;
        do {
            System.out.println("Nhập giá bán");
            double inputExportPrice =Double.parseDouble( sc.nextLine());
            if (inputExportPrice>this.importPrice*1.2) {
                this.exportPrice = inputExportPrice;
                isExistExportPrice = false;
            } else {
                System.out.println("Giá bán phải lớn hơn 1.2 lần giá nhập, vui lòng nhập lại");
            }

        } while (isExistExportPrice);
        this.interest=interestBook();

        boolean isExistStatus = true;
        do {
            System.out.println("Nhập vào trạng thái sách");
            boolean inputStatus =Boolean.parseBoolean( sc.nextLine());
            if (inputStatus==true||inputStatus==false) {
                this.bookStatus = inputStatus;
                isExistStatus = false;
            } else {
                System.out.println("Trạng thái nhập vào phải là (true: đang bán) hoặc (false: dừng bán)");
            }
        } while (isExistStatus);
    }

public   void displayData(){

    System.out.printf("Mã sách: %d Tên sách: %s Tên tác giả: %s Giá nhập: %.2f Giá xuất: %.2f Mô tả: %s Trạng thái: %s\n", this.bookId,this.bookName,this.author,this.importPrice,this.exportPrice,this.descriptions,this.bookStatus?"Đang bán":"Dừng bán");
}
}

package ch.bzz.bookshelfdennis.model;

import java.math.BigDecimal;

public class Book {
    private String bookUUID;
    private String title;
    private String autohor;
    private Publisher publisher;
    private BigDecimal price;
    private String isbn;

    public String getBookUUID() {
        return bookUUID;
    }

    public void setBookUUID(String bookUUID) {
        this.bookUUID = bookUUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutohor() {
        return autohor;
    }

    public void setAutohor(String autohor) {
        this.autohor = autohor;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

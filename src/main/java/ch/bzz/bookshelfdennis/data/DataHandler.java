package ch.bzz.bookshelfdennis.data;

import ch.bzz.bookshelfdennis.model.Book;
import ch.bzz.bookshelfdennis.model.Publisher;
import ch.bzz.bookshelfdennis.service.Config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Book> bookList;
    private List<Publisher> publisherList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setPublisherList(new ArrayList<>());
        readPublisherJSON();
        setBookList(new ArrayList<>());
        readBookJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Book> readAllBooks() {
        return getBookList();
    }

    /**
     * reads a book by its uuid
     * @param bookUUID
     * @return the Book (null=not found)
     */
    public Book readBookByUUID(String bookUUID) {
        Book book = null;
        for (Book entry : getBookList()) {
            if (entry.getBookUUID().equals(bookUUID)) {
                book = entry;
            }
        }
        return book;
    }

    /**
     * inserts a new book into the bookList
     *
     * @param book the book to be saved
     */
    public void insertBook(Book book) {
        getBookList().add(book);
        writeBookJSON();
    }

    /**
     * updates the bookList
     */
    public void updateBook() {
        writeBookJSON();
    }

    /**
     * deletes a book identified by the bookUUID
     * @param bookUUID  the key
     * @return  success=true/false
     */
    public boolean deleteBook(String bookUUID) {
        Book book = readBookByUUID(bookUUID);
        if (book != null) {
            getBookList().remove(book);
            writeBookJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all publishers
     * @return list of books
     */
    public List<Publisher> readAllPublishers() {
        return publisherList;
    }

    /**
     * reads a publisher by its uuid
     * @param publisherUUID
     * @return the Publisher (null=not found)
     */
    public Publisher readPublisherByUUID(String publisherUUID) {
        Publisher publisher = null;
        for (Publisher entry : getPublisherList()) {
            if (entry.getPublisherUUID().equals(publisherUUID)) {
                publisher = entry;
            }
        }
        return publisher;
    }

    /**
     * inserts a new publisher into the bookList
     *
     * @param publisher the publisher to be saved
     */
    public void insertPublisher(Publisher publisher) {
        getPublisherList().add(publisher);
        writePublisherJSON();
    }

    /**
     * updates the publisherList
     */
    public void updatePublisher() {
        writePublisherJSON();
    }

    /**
     * deletes a publisher identified by the publisherUUID
     * @param publisherUUID  the key
     * @return  success=true/false
     */
    public boolean deletePublisher(String publisherUUID) {
        Publisher publisher = readPublisherByUUID(publisherUUID);
        if (publisher != null) {
            getPublisherList().remove(publisher);
            writePublisherJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the books from the JSON-file
     */
    private void readBookJSON() {
        try {
            String path = Config.getProperty("bookJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Book[] books = objectMapper.readValue(jsonData, Book[].class);
            for (Book book : books) {
                getBookList().add(book);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the bookList to the JSON-file
     */
    private void writeBookJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("bookJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getBookList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readPublisherJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("publisherJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Publisher[] publishers = objectMapper.readValue(jsonData, Publisher[].class);
            for (Publisher publisher : publishers) {
                getPublisherList().add(publisher);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the publisherList to the JSON-file
     */
    private void writePublisherJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("publisherJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getBookList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets bookList
     *
     * @return value of bookList
     */

    private List<Book> getBookList() {
        return bookList;
    }

    /**
     * sets bookList
     *
     * @param bookList the value to set
     */

    private void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */

    private List<Publisher> getPublisherList() {
        return publisherList;
    }

    /**
     * sets publisherList
     *
     * @param publisherList the value to set
     */

    private void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }


}
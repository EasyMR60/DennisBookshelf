package ch.bzz.bookshelfdennis.service;

import ch.bzz.bookshelfdennis.data.DataHandler;
import ch.bzz.bookshelfdennis.model.Book;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting books
 */
@Path("book")
public class BookService {

    /**
     * reads a list of all books
     * @return  books as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBooks() {
        List<Book> bookList = DataHandler.getInstance().readAllBooks();
        return Response
                .status(200)
                .entity(bookList)
                .build();
    }

    /**
     * reads a book identified by the uuid
     * @param bookUUID
     * @return book
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(
            @QueryParam("uuid") String bookUUID
    ) {
        int httpStatus = 200;
        Book book = DataHandler.getInstance().readBookByUUID(bookUUID);
        if (book == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(book)
                .build();
    }
}
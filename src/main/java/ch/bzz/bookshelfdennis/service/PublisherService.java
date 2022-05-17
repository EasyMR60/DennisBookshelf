package ch.bzz.bookshelfdennis.service;

import ch.bzz.bookshelfdennis.data.DataHandler;
import ch.bzz.bookshelfdennis.model.Publisher;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting publishers
 */
@Path("publisher")
public class PublisherService {

    /**
     * reads a list of all publishers
     * @return  publishers as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPublishers() {
        List<Publisher> publisherList = DataHandler.getInstance().readAllPublishers();
        return Response
                .status(200)
                .entity(publisherList)
                .build();
    }

    /**
     * reads a publisher identified by the uuid
     * @param publisherUUID
     * @return publisher
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPublisher(
            @QueryParam("uuid") String publisherUUID
    ) {
        int httpStatus = 200;
        Publisher publisher = DataHandler.getInstance().readPublisherByUUID(publisherUUID);
        if (publisher == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(publisher)
                .build();
    }
}
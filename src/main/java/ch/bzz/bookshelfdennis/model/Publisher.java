package ch.bzz.bookshelfdennis.model;

/**
 * a book publisher
 */
public class Publisher {
    private String publisherUUID;
    private String publisher;

    /**
     * gets publisherUUID
     *
     * @return value of publisherUUID
     */
    public String getPublisherUUID() {
        return publisherUUID;
    }

    /**
     * sets publisherUUID
     *
     * @param publisherUUID the value to set
     */
    public void setPublisherUUID(String publisherUUID) {
        this.publisherUUID = publisherUUID;
    }

    /**
     * gets publisher
     *
     * @return value of publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * sets publisher
     *
     * @param publisher the value to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
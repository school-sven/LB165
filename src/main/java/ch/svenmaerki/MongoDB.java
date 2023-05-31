package ch.svenmaerki;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Objects;
import java.util.logging.Logger;

public class MongoDB {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;
    private final Logger logger;

    public MongoDB(String url, String databaseName, String collectionName) {
        mongoClient = MongoClients.create(url);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
        logger = Logger.getLogger(MongoDB.class.getName());
    }

    /**
     * Creates a document and inserts it into the collection.
     */
    public void createDocument1() {
        Document document = new Document("id", 23435)
                .append("name", "Bright, Modern Garden Unit - 1BR/1BTH")
                .append("host_id", 23435)
                .append("host_name", "Holly")
                .append("neighbourhood_group", "")
                .append("neighbourhood", "Western Addition")
                .append("latitude", 37.77028)
                .append("longitude", -122.43317)
                .append("room_type", "Entire home/apt")
                .append("price", 202)
                .append("minimum_nights", 2)
                .append("number_of_reviews", 383)
                .append("last_review", "2023-02-19")
                .append("reviews_per_month", 2.31)
                .append("calculated_host_listings_count", 1)
                .append("availability_365", 128)
                .append("number_of_reviews_ltm", 59)
                .append("city", "San Francisco");
        collection.insertOne(document);
    }

    /**
     * Creates a document and inserts it into the collection.
     */
    public void createDocument2() {
        Document document = new Document("id", 5858)
                .append("name", "Private room in a charming Victorian")
                .append("host_id", 23435)
                .append("host_name", "Brian")
                .append("neighbourhood_group", "")
                .append("neighbourhood", "Western Addition")
                .append("latitude", 37.77525)
                .append("longitude", -122.43637)
                .append("room_type", "Private room")
                .append("price", 120)
                .append("minimum_nights", 1)
                .append("number_of_reviews", 18)
                .append("last_review", "2019-02-21")
                .append("reviews_per_month", 0.14)
                .append("calculated_host_listings_count", 1)
                .append("availability_365", 0)
                .append("number_of_reviews_ltm", 0)
                .append("city", "San Francisco");
        collection.insertOne(document);
    }

    /**
     * Reads the first document from the collection and prints it to the console.
     */
    public void readFirstDocument() {
        Document document = collection.find().first();
        logger.info(Objects.requireNonNull(document).toJson());
    }

    /**
     * Reads the document with the host_id 23435 from the collection and prints it to the console.
     */
    public void readDocumentWithHostId23435() {
        Document document = collection.find(new Document("host_id", 23435)).first();
        logger.info(Objects.requireNonNull(document).toJson());
    }

    /**
     * Updates the document with the id 23435 in the collection.
     * It updates the name of the document.
     * The document with the id 23435 is created by the createDocument1() method.
     */
    public void updateDocumentWithId23435() {
        Document document = collection.find(new Document("id", 23435)).first();
        logger.info("Before -> {}" + Objects.requireNonNull(document).toJson());
        document.append("name", "Bright, Old Garden Unit");
        collection.updateOne(new Document("id", 23435), new Document("$set", document));
        Document updatedDocument = collection.find(new Document("id", 23435)).first();
        logger.info("After -> {}" + Objects.requireNonNull(updatedDocument).toJson());
    }

    /**
     * Updates the document with the id 5858 and the host_id 23435 in the collection.
     * It updates the price, minimum_nights and number_of_reviews of the document.
     * The document with the id 5858 and the host_id 23435 is created by the createDocument2() method.
     */
    public void updateDocumentWithId5858andHostId23435() {
        Document document = collection.find(new Document("id", 5858).append("host_id", 23435)).first();
        logger.info("Before -> {}" + Objects.requireNonNull(document).toJson());
        document.append("price", 150)
                .append("minimum_nights", 2)
                .append("number_of_reviews", 20);
        collection.updateOne(new Document("id", 5858).append("host_id", 23435), new Document("$set", document));
        Document updatedDocument = collection.find(new Document("id", 5858).append("host_id", 23435)).first();
        logger.info("After -> {}" + Objects.requireNonNull(updatedDocument).toJson());
    }

    /**
     * Deletes the document with the id 23435 from the collection.
     * The document with the id 23435 is created by the createDocument1() method.
     */
    public void deleteDocumentWithId23435() {
        Document document = collection.find(new Document("id", 23435)).first();
        collection.deleteOne(Objects.requireNonNull(document));
    }

    /**
     * Deletes the document with the id 5858 and the host_id 23435 from the collection.
     * The document with the id 5858 and the host_id 23435 is created by the createDocument2() method.
     */
    public void deleteDocumentWithId5858andHostId23435() {
        Document document = collection.find(new Document("id", 5858).append("host_id", 23435)).first();
        collection.deleteOne(Objects.requireNonNull(document));
    }

    /**
     * Closes the MongoDB connection.
     */
    public void close() {
        mongoClient.close();
    }

}

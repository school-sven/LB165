package ch.svenmaerki;

public class Main {

    /**
     * Local MongoDB connection url
     */
    private static final String URL = "mongodb://localhost:27017";
    /**
     * Name of the database
     */
    private static final String DATABASE_NAME = "airbnb";
    /**
     * Name of the collection
     */
    private static final String COLLECTION_NAME = "listings";

    public static void main(String[] args) {
        // Create MongoDB connection
        MongoDB mongoDB = new MongoDB(URL, DATABASE_NAME, COLLECTION_NAME);

        // Create operations
        mongoDB.createDocument1();
        mongoDB.createDocument2();

        // Read operations
        mongoDB.readFirstDocument();
        mongoDB.readDocumentWithHostId23435();

        // Update operations
        mongoDB.updateDocumentWithId23435();
        mongoDB.updateDocumentWithId5858andHostId23435();

        // Delete operations
        mongoDB.deleteDocumentWithId23435();
        mongoDB.deleteDocumentWithId5858andHostId23435();

        // Close MongoDB connection
        mongoDB.close();
    }
}
package itsovy.sk.Database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import itsovy.sk.Bill.Bill;
import itsovy.sk.Item.Drink.DraftInterface;
import itsovy.sk.Item.Goods.Piece;
import itsovy.sk.Item.Food.Fruit;
import itsovy.sk.Item.Item;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mongo {

    private static Mongo db = new Mongo();

    private Mongo() {
    }

    public static Mongo getInstance() {
        return db;
    }

    public void createMongoBill(Bill bill) throws IOException {

        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase database = mongoClient.getDatabase("store");
        MongoCollection<Document> collection = database.getCollection("bill");

        Document mongoBill = new Document();
        mongoBill.append("id", bill.getId());
        mongoBill.append("date", bill.getDate());

        List<Item> items = bill.getList();
        List<Document> itemsDocument = new ArrayList<>();

        for (Item item : items) {
            Document itemDocument = new Document();
            itemDocument.append("name", item.getName());
            itemDocument.append("price", item.getPrice());
            if (item instanceof DraftInterface) {
                itemDocument.append("l", ((DraftInterface) item).getVolume());
            }
            else if (item instanceof Fruit) {
                itemDocument.append("kg", ((Fruit) item).getWeight());
            }
            else if (item instanceof Piece) {
                itemDocument.append("pcs", ((Piece) item).getAmount());
            }
            itemsDocument.add(itemDocument);
        }

        mongoBill.append("items", itemsDocument);
        mongoBill.append("price", new Document("EUR", bill.getFinalPrice()).append("USD", bill.getConvertedFinalPrice()));
    }
}

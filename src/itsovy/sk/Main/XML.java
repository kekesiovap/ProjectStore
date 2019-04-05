package itsovy.sk.Main;

import itsovy.sk.Bill.Bill;
import itsovy.sk.Item.Drink.DraftInterface;
import itsovy.sk.Item.Food.Fruit;
import itsovy.sk.Item.Goods.Piece;
import itsovy.sk.Item.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class XML {

    public void createXML(Bill bill) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("Bill");
        Node node = doc.appendChild(rootElement);

        Date date = new java.sql.Date(bill.getDate().getTime());
        Date time = new java.sql.Time(bill.getDate().getTime());

        Element dateEl = doc.createElement("DateOfPurchase");
        rootElement.appendChild(dateEl);

        Element date2 = doc.createElement("Date");
        date2.appendChild(doc.createTextNode(String.valueOf(date)));
        dateEl.appendChild(date2);

        Element time2 = doc.createElement("Time");
        time2.appendChild(doc.createTextNode(String.valueOf(time)));
        dateEl.appendChild(time2);

        for (Item item : bill.getList()) {
            Element billItem = doc.createElement("Item");
            rootElement.appendChild(billItem);

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(item.getName()));
            billItem.appendChild(name);

            Element price = doc.createElement("Price");
            price.appendChild(doc.createTextNode(String.valueOf(item.getPrice())));
            billItem.appendChild(price);

            Element amount = doc.createElement("Amount");
            if (item instanceof DraftInterface){
                amount.appendChild(doc.createTextNode(String.valueOf(((DraftInterface) item).getVolume())));
                billItem.appendChild(amount);
            }
            else if (item instanceof Fruit){
                amount.appendChild(doc.createTextNode(String.valueOf(((Fruit) item).getWeight())));
                billItem.appendChild(amount);
            }
            else if (item instanceof Piece){
                amount.appendChild(doc.createTextNode(String.valueOf(((Piece) item).getAmount())));
                billItem.appendChild(amount);
            }

            Element units = doc.createElement("Unit");
            if (item instanceof DraftInterface){
                units.appendChild(doc.createTextNode("l"));
                billItem.appendChild(units);
            }
            else if (item instanceof Fruit){
                units.appendChild(doc.createTextNode("kg"));
                billItem.appendChild(units);
            }
            else if (item instanceof Piece){
                units.appendChild(doc.createTextNode("pcs"));
                billItem.appendChild(units);
            }

        }

        Element totalPrice = doc.createElement("Price");
        rootElement.appendChild(totalPrice);

        Element eurPrice = doc.createElement("EUR");
        eurPrice.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
        totalPrice.appendChild(eurPrice);

        Element dolPrice = doc.createElement("USD");
        dolPrice.appendChild(doc.createTextNode(String.valueOf(bill.getConvertedFinalPrice())));
        totalPrice.appendChild(dolPrice);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("billXML.xml"));
        transformer.transform(source, result);
    }
}
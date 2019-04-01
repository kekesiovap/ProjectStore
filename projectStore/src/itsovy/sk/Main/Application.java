package itsovy.sk.Main;

import itsovy.sk.Bill.Bill;
import itsovy.sk.Exception.BillException;
import itsovy.sk.Item.Category;
import itsovy.sk.Item.Drink.Bottle;
import itsovy.sk.Item.Drink.Draft;
import itsovy.sk.Item.Drink.Drink;
import itsovy.sk.Item.Food.Food;
import itsovy.sk.Item.Food.Fruit;
import itsovy.sk.Item.Food.Pastry;
import itsovy.sk.Item.Goods.Goods;
import itsovy.sk.Item.Item;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class Application{

    private Bill bill;

    private static Application app=new Application();

    private Application(){
        //app=new Application();
    }
    public static Application getInstance(){
        return app;
    }

    public void example() throws BillException, IOException, SQLException, TransformerException, ParserConfigurationException {
        Bill bill = new Bill();

        Bottle milk = new Bottle("Milk 1,5", 0.59, 4 );
        bill.addItem(milk);

        Item pizza = new  Pastry("Margarita", 1.10, 200, 2);
        bill.addItem(pizza);

        Food apple = new Fruit("Green Apple", 0.5, 100, 0.370);
        bill.addItem(apple);

        Item pencil = new Goods("Pencil HB", 0.8, 2, Category.SCHOOL);
        bill.addItem(pencil);

        Draft radler=new Draft("Pomelo/Grep", 1, true, 0.5);
        bill.addItem(radler);

        Draft plzen=new Draft("Dvanastka", 1, false, 0.5);
        bill.addItem(plzen);
        bill.removeItem(plzen);

        bill.printBill();
        System.out.println("Total price: "+bill.getFinalPrice());
        System.out.println("Sum converted to dollars: " + bill.getConvertedFinalPrice());

        XML check = new XML();
        check.createXML(bill);
        bill.closeBill();
    }
}

package itsovy.sk.Bill;


import itsovy.sk.Database.Database;
import itsovy.sk.Exception.BillException;
import itsovy.sk.Item.Drink.DraftInterface;
import itsovy.sk.Item.Food.Fruit;
import itsovy.sk.Item.Goods.Piece;
import itsovy.sk.Item.Item;
import itsovy.sk.Main.Global;
import itsovy.sk.Main.Internet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Bill {
    private List<Item> list;
    private int count;
    public boolean open;
    private  double sum;
    private Date date;


    public Date getDate() {
        //SimpleDateFormat sdf=new SimpleDateFormat("dd.MMM.YYYY");
        return date;
    }

    public List<Item> getList() {
        return list;
    }

    public Bill() {
        this.list = new ArrayList<>();
        count=0;
        open=true;
    }

    public void closeBill() throws SQLException {
        //Database db = Database.getlasttime()
        //db.insertData (this)

        if(open){
            System.out.println(date);
            Database db = Database.getInstanceDb();
            db.insertNewBill(this);
        }
        else {
            System.out.println("Bill is not closed.");
        }
        open=false;
    }

    public void addItem(Item item) throws BillException{
        if(item!=null) {
            if(open==false){
                String message = "Bill is closed. Is not allowed to add any items!";
                throw new BillException(message);
            }
            if(count==Global.MAXITEMS) {
                String message = "Bill reached maximum items! Which is "+Global.MAXITEMS+" items.";
                throw new BillException(message);
            }
            list.add(item);
            count++;
        }
    }

    public void removeItem(Item item){
        if(list.contains(item)) {
            list.remove(item);
            count--;
        }
    }

    public double getFinalPrice(){
        //throw new BillException("Method does not exists yet");
        sum=0;
        for(Item item: list)
        {
            sum= sum + item.getTotalPrice();
        }
        return sum;
    }

    public double getConvertedFinalPrice() throws IOException {
        double finalPrice = getFinalPrice();
        double dollars = finalPrice * Internet.getUSDrate();
        return dollars;
    }

    public void printBill(){
        if(count==0)
            System.out.println("Nothing to print. Bill is epmty !");
        else{
            for(Item item:list){
                if(item instanceof DraftInterface){
                    System.out.print("Name: "+item.getName()+" Volume: "+((DraftInterface) item).getVolume()+" Price: ");
                    System.out.println(item.getPrice()+" Total price: "+item.getTotalPrice());
                }
                else if(item instanceof Fruit){
                    System.out.print("Name: "+item.getName()+" Weight: "+((Fruit)item).getWeight()+" Price: ");
                    System.out.println(item.getPrice()+" Total price: "+item.getTotalPrice());
                }
                else if(item instanceof Piece){
                    System.out.print("Name: "+item.getName()+" Amount: "+((Piece)item).getAmount()+" Price: ");
                    System.out.println(item.getPrice()+" Total price: "+item.getTotalPrice());
                }
            }
            date = new Date();
            System.out.println( date );
        }
    }

    public int getCount(){
        return count;
    }
}

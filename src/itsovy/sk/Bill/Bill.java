package itsovy.sk.Bill;


import itsovy.sk.Exception.BillException;
import itsovy.sk.Item.Drink.DraftInterface;
import itsovy.sk.Item.Food.Fruit;
import itsovy.sk.Item.Goods.Piece;
import itsovy.sk.Item.Item;
import itsovy.sk.Main.Global;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;


public class Bill {
    private List<Item> list;
    private int count;
    public boolean open;


    public String getDateTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MMM.YYYY");

    }
    public Bill() {
        this.list = new ArrayList<>();
        count=0;
        open=true;
    }
    public void closeBill(){
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

    public double getFinalPrice() throws BillException{
        throw new BillException("Method does not exists yet");
    }

    public void printBill(){
        if(count==0)
            System.out.println("Nothing to print. Bill is epmty !");
        else{
            for(Item item:list){
                if(item instanceof DraftInterface){
                    System.out.print(item.getName()+" "+((DraftInterface) item).getVolume()+" ");
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }
                else if(item instanceof Fruit){
                    System.out.print(item.getName()+" "+((Fruit)item).getWeight()+" ");
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }
                else if(item instanceof Piece){
                    System.out.print(item.getName()+" "+((Piece)item).getAmount()+" ");
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                } {

                }

            }
        }
    }
    public int getCount(){
        return count;
    }
}

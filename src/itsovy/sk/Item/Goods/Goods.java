package itsovy.sk.Item.Goods;

import itsovy.sk.Item.Category;
import itsovy.sk.Item.Item;

public class Goods extends Item implements Piece {
    private int amount;
    private Category type;

    public Goods(String name, double price, int amount, Category type) {
        super(name, price);
        this.amount=amount;
        this.type=type;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public Category getType() {
        return type;
    }
}

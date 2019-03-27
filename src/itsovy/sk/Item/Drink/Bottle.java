package itsovy.sk.Item.Drink;

import itsovy.sk.Item.Goods.Piece;

public class Bottle extends Drink implements Piece {
    private int amount;

    public Bottle(String name, double price, boolean sweet, int amount) {
        super(name, price, sweet);
        this.amount=amount;
    }

    public Bottle(String name, double price, int amount) {
        this(name, price,false,amount);
    }

    public Bottle(String name, double price, boolean sweet) {
        this(name, price,sweet,1);
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public String getName() {
        return getName();
    }
}

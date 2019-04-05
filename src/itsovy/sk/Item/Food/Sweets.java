package itsovy.sk.Item.Food;

import itsovy.sk.Item.Goods.Piece;

public class Sweets extends Food implements Piece {
    private int amount;

    public Sweets(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount = amount;
    }

    public Sweets(String name, double price, int amount) {
        this(name, price,-1,amount);
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

   @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setAmount(int amount) {

    }
}

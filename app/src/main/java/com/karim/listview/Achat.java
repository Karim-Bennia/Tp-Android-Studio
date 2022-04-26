package com.karim.listview;



public class Achat {
    private  String Item;
    private double qte;

   public Achat(String Item, double qte) {
        this.Item=Item;
        this.qte=qte;
    }

    public double getQte() {
        return qte;
    }

    public String getItem() {
        return Item;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public void setItem(String item) {
        this.Item = item;
    }

    @Override
    public String toString()  {
        return this.qte+""+this.Item;
    }
}
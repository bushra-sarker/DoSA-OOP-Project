package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class BudgetItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String item;
    private double amount;

    public BudgetItem(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }

    public String getItem() { return item; }
    public double getAmount() { return amount; }
}
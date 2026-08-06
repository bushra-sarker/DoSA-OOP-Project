package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class BudgetItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemName;
    private double amount;

    public BudgetItem() {
    }

    public BudgetItem(String itemName, double amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
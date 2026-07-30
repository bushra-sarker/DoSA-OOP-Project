package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class BudgetItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private double amount;

    public BudgetItem() {}

    public BudgetItem(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getters and Setters required by PropertyValueFactory
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
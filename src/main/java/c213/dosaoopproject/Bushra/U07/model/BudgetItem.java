package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class BudgetItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemName;
    private String amount;

    public BudgetItem() {
    }

    public BudgetItem(String itemName, String amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    // Getters and Setters (Required for TableView PropertyValueFactory)
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
}
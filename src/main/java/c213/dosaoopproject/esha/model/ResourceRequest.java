package c213.dosaoopproject.esha.model;

import java.time.LocalDate;

public class ResourceRequest {
    private int resourceRequestId;
    private int executiveId;
    private String resourceCategory; // "Equipment", "Stationery", "Furniture", "AV/Tech", "Other"
    private String materialName;
    private int quantity;
    private LocalDate requiredDate;
    private String purpose;
    private String status; // "Pending", "Approved", "Rejected"

    public ResourceRequest() {}

    public ResourceRequest(int resourceRequestId, int executiveId, String resourceCategory,
                           String materialName, int quantity, LocalDate requiredDate,
                           String purpose, String status) {
        this.resourceRequestId = resourceRequestId;
        this.executiveId = executiveId;
        this.resourceCategory = resourceCategory;
        this.materialName = materialName;
        this.quantity = quantity;
        this.requiredDate = requiredDate;
        this.purpose = purpose;
        this.status = status;
    }

    public int getResourceRequestId() { return resourceRequestId; }
    public void setResourceRequestId(int resourceRequestId) { this.resourceRequestId = resourceRequestId; }

    public int getExecutiveId() { return executiveId; }
    public void setExecutiveId(int executiveId) { this.executiveId = executiveId; }

    public String getResourceCategory() { return resourceCategory; }
    public void setResourceCategory(String resourceCategory) { this.resourceCategory = resourceCategory; }

    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getRequiredDate() { return requiredDate; }
    public void setRequiredDate(LocalDate requiredDate) { this.requiredDate = requiredDate; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ResourceRequest{resourceRequestId=" + resourceRequestId + ", material='" + materialName + "', status='" + status + "'}";
    }
}

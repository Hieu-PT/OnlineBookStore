
package hieupt.dtos;

import java.sql.Timestamp;

public class DiscountDTO {
    private String ID;
    private int discountPercent;
    private Timestamp createDate;

    public DiscountDTO(String ID, int discountPercent) {
        this.ID = ID;
        this.discountPercent = discountPercent;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    
}

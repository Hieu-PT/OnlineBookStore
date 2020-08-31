
package hieupt.dtos;

import java.sql.Timestamp;
import java.util.List;

public class BookDTO {
    private String id, title, author, category, image, description, status;
    private double price;
    private int quantity, cartQuantity;
    private Timestamp importDate;
    private List<Integer> listQuantity;
    private double total;

    public BookDTO() {
        
    }

    public BookDTO(String id, String title, String author, String category, String image, String description, String status, double price, int quantity, Timestamp importDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.image = image;
        this.description = description;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
    }
    
    public BookDTO(String id, String title, String author, String category, String image, String description, String status, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.image = image;
        this.description = description;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
    }
    
    

    public BookDTO(String id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public List<Integer> getListQuantity() {
        return listQuantity;
    }

    public void setListQuantity(List<Integer> listQuantity) {
        this.listQuantity = listQuantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}

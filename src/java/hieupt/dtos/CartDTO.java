
package hieupt.dtos;

import java.util.HashMap;

public class CartDTO {
    private String customerName;
    private HashMap<String, BookDTO> cart;
    private double discountPercent;
    private String discountCode;
    private double totalAll;

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(double totalAll) {
        this.totalAll = totalAll;
    }

    
   
    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, BookDTO> getCart() {
        return cart;
    }
    
    public CartDTO(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap<>();
        this.discountPercent = 0;
    }
    
    public boolean addToCart(BookDTO dto) throws Exception{
        boolean status = true;
        if(this.cart.containsKey(dto.getId())){
            if(this.cart.get(dto.getId()).getCartQuantity() < dto.getQuantity()){
                int quantity = this.cart.get(dto.getId()).getCartQuantity() + dto.getCartQuantity();
                dto.setCartQuantity(quantity);
            }else return false;
        }
        this.cart.put(dto.getId(), dto);
        return status;
    }
    
    public boolean delete(String id) throws Exception {
        if(this.cart.containsKey(id)) {
            this.cart.remove(id);
            return true;
        }
        return false;
    }
    
    public double getTotal() {
        double result = 0;
        for (BookDTO dto : this.cart.values()) {
            result += dto.getPrice() * dto.getCartQuantity();
        }
        result = Math.floor(result * 100) / 100;
        return result;
    }
    
    public boolean update(String id, int quantity) throws Exception {
        if(this.cart.containsKey(id)) {
            this.cart.get(id).setCartQuantity(quantity);
            return true;
        }
        return false;
    }

    public void clearCart() throws Exception{
        cart.clear();
        discountCode = "";
        discountPercent = 0;
    }
}

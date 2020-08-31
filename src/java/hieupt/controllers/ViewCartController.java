
package hieupt.controllers;

import hieupt.daos.BookDAO;
import hieupt.dtos.BookDTO;
import hieupt.dtos.CartDTO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class ViewCartController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewCartController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            CartDTO cart =  (CartDTO) session.getAttribute("cart");
            HashMap<String, BookDTO> result;
            if(cart == null){
                request.setAttribute("MESSAGE", "Your Shopping Cart is null");
            }else {
                result = cart.getCart();
                BookDAO dao = new BookDAO();
                BookDTO dto;
                for (Map.Entry<String, BookDTO> entry : result.entrySet()) {
                    dto = dao.findByPrimaryKey(entry.getValue().getId());
                    entry.getValue().setListQuantity(dto.getListQuantity());
                }
                for (Map.Entry<String, BookDTO> book : result.entrySet()) {
                    double total = (book.getValue().getCartQuantity() * book.getValue().getPrice());
                    book.getValue().setTotal(Math.floor(total * 100) / 100);
                }
                double total = Math.floor(cart.getTotal() * ((100 - cart.getDiscountPercent()) / 100) * 100) / 100;
                cart.setTotalAll(total);
                session.setAttribute("cart", cart);
                request.setAttribute("TOTAL", total);
                request.setAttribute("INFO", result);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR at ViewCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

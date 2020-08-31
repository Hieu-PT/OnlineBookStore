
package hieupt.controllers;

import hieupt.daos.DiscountDAO;
import hieupt.dtos.CartDTO;
import hieupt.dtos.DiscountDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CheckDiscountCodeController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CheckDiscountCodeController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String discount = request.getParameter("txtDiscount");
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("USERNAME");
            DiscountDAO dao = new DiscountDAO();
            DiscountDTO dto = dao.findByPrimaryKey(discount);
            if (dto != null) {
                if(!dao.checkUsedCode(username, discount)){
                    double percent = Double.parseDouble(dto.getDiscountPercent() + "");
                    CartDTO cart = (CartDTO) session.getAttribute("cart");
                    cart.setDiscountPercent(percent);
                    cart.setDiscountCode(dto.getID());
                    session.setAttribute("cart", cart);
                }else request.setAttribute("MESSAGE", "You have already use this code: " + discount);
                
            }else request.setAttribute("MESSAGE", "There is no code: " + discount);
            
        } catch (Exception e) {
            LOGGER.error("ERROR at CheckDiscountCodeController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ViewCartController").forward(request, response);
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

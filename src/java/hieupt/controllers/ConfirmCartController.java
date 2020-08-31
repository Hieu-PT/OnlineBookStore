package hieupt.controllers;

import hieupt.daos.BookDAO;
import hieupt.daos.CartDAO;
import hieupt.daos.DiscountDAO;
import hieupt.dtos.BookDTO;
import hieupt.dtos.CartDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class ConfirmCartController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConfirmCartController.class);
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "ViewCartController";
    private static final String SUCCESS = "viewOrderHistory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String[] listId = request.getParameterValues("txtID");
            String[] txtQuantity = request.getParameterValues("txtQuantity");

            HttpSession session = request.getSession();
            CartDTO shoppingCart = (CartDTO) session.getAttribute("cart");
            BookDAO dao = new BookDAO();
            BookDTO dto;
            List<String> listFail = new ArrayList<>();
            for (int i = 0; i < listId.length; i++) {
                dto = dao.findByPrimaryKey(listId[i]);
                if (dto.getQuantity() >= Integer.parseInt(txtQuantity[i])) {
                    shoppingCart.update(listId[i], Integer.parseInt(txtQuantity[i]));
                } else if (dto.getQuantity() == 0) {
                    shoppingCart.delete(listId[i]);
                    url = INVALID;
                } else {
                    listFail.add(listId[i]);
                    url = INVALID;
                }
            }
            request.setAttribute("LIST_FAIL", listFail);
            if(!url.equals(INVALID)){
                CartDAO cartDAO = new CartDAO();
                String username = (String) session.getAttribute("USERNAME");
                HashMap<String, BookDTO> listBook = shoppingCart.getCart();
                int orderID = cartDAO.addNewOrders(username, shoppingCart.getTotalAll());
                if(cartDAO.addNewPromotionDetail(orderID, listBook)){
                    url = SUCCESS;
                    if(!shoppingCart.getDiscountCode().equals("")){
                        DiscountDAO disDAO = new DiscountDAO();
                        disDAO.createDiscountRecord(shoppingCart.getDiscountCode(), orderID, username);
                    }
                    shoppingCart.clearCart();
                    session.setAttribute("cart", shoppingCart);
                } else {
                    request.setAttribute("ERROR", "Something went wrong. Please try again later!");
                }
            }
        } catch (Exception e) {
            LOGGER.error("ERROR at ConfirmCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

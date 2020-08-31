
package hieupt.controllers;

import hieupt.daos.BookDAO;
import hieupt.dtos.BookDTO;
import hieupt.dtos.CartDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class UpdateCartController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateCartController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String[] listId = request.getParameterValues("txtID");
            String[] txtQuantity = request.getParameterValues("txtQuantity");
            
            HttpSession session = request.getSession();
            CartDTO shoppingCart = (CartDTO)session.getAttribute("cart");
            BookDAO dao = new BookDAO();
            BookDTO dto;
            List<String> listFail = new ArrayList<>();
            for (int i = 0; i < listId.length; i++) {
                dto = dao.findByPrimaryKey(listId[i]);
                if(dto.getQuantity() >= Integer.parseInt(txtQuantity[i]))
                    shoppingCart.update(listId[i], Integer.parseInt(txtQuantity[i]));
                else if(dto.getQuantity() == 0)
                    shoppingCart.delete(listId[i]);
                else listFail.add(listId[i]);
            }
            request.setAttribute("LIST_FAIL", listFail);
            session.setAttribute("cart", shoppingCart);
        } catch (Exception e) {
            LOGGER.error("ERROR at UpdateCartController: " +e.getMessage());
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

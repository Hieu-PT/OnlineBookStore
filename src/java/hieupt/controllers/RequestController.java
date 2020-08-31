
package hieupt.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class RequestController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final Logger LOGGER = Logger.getLogger(RequestController.class);
    private static final String SAVE = "UpdateCartController";
    private static final String CONFIRM = "ConfirmCartController";
    private static final String REMOVE = "RemoveItemController";
    private static final String CHECK = "CheckDiscountCodeController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("Save"))
                url = SAVE;
            else if(action.equalsIgnoreCase("Confirm"))
                url = CONFIRM;
            else if(action.equalsIgnoreCase("Remove"))
                url = REMOVE;
            else if(action.equalsIgnoreCase("Check"))
                url = CHECK;
            else {
                request.setAttribute("ERROR", "Your action is invalid");
            }
        }
        catch (Exception e){
            LOGGER.error("ERROR at RequestController: " + e.getMessage());
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

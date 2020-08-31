
package hieupt.controllers;

import hieupt.daos.DiscountDAO;
import hieupt.dtos.DiscountDTO;
import hieupt.dtos.DiscountErrorObj;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CreateDiscountController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String RESPONSE = "createDiscount.jsp";
    private static final Logger LOGGER = Logger.getLogger(CreateDiscountController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            String id = request.getParameter("txtID");
            String stringPercent = request.getParameter("txtDiscountPercent");
            
            String validPercent = "^\\d+$";
            int percent = 0;
            DiscountDAO dao = new DiscountDAO();
            DiscountDTO dto;
            
            DiscountErrorObj errorObj = new DiscountErrorObj();
            boolean valid = true;
            
            if(id.length() == 0){
                errorObj.setIdError("ID can't be blank");
                valid = false;
            }
            if(dao.findByPrimaryKey(id) != null){
                errorObj.setIdError("Code: '" + id +"' is already existed");
                valid = false;
            }
            if(stringPercent.matches(validPercent)){
                if(Integer.parseInt(stringPercent) <= 100 ){
                    percent = Integer.parseInt(stringPercent);
                }else{
                    errorObj.setDiscountPercentError("Percent must between 0-100");
                    valid = false;
                }
            }else {
                errorObj.setDiscountPercentError("It must be an integer");
                valid = false;
            }
            if(valid){
                dto = new DiscountDTO(id, percent);
                if(dao.createDiscountCode(dto)){
                    request.setAttribute("MESSAGE", "Create Successfully");
                }else request.setAttribute("MESSAGE", "Create Failed");
            }else request.setAttribute("INVALID", errorObj);
            url = RESPONSE;
        } catch (Exception e) {
            LOGGER.error("ERROR at CreateDiscountController: " + e.getMessage());
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

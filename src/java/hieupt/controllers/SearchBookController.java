
package hieupt.controllers;

import hieupt.daos.BookDAO;
import hieupt.dtos.BookDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class SearchBookController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchBookController.class);
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "searchBook.jsp";
        try {
            String search = request.getParameter("txtSearch");
            String price = request.getParameter("txtPrice");
            String optionCategory = request.getParameter("txtCategory");
            double fromPrice = 0, toPrice = 0;
            String category;
            
            BookDAO dao = new BookDAO();
            double maxPrice = dao.getMaxPrice() + 1;
            switch(price){
                case "all": fromPrice = 0;
                            toPrice = maxPrice;
                            break;
                case "lessThan10": fromPrice = 0;
                                    toPrice = 10;
                                    break;
                case "10-40": fromPrice = 10;
                                toPrice = 40;
                                break;
                case "40-100": fromPrice = 40;
                                toPrice = 100;
                                break;
                case "over100": fromPrice = 100;
                                toPrice = maxPrice;
            }
            
            if(optionCategory.equals("Any")){
                category = "";
            }else category = optionCategory;
            
            List<BookDTO> result = dao.searchBook(search, fromPrice, toPrice, category);
            request.setAttribute("INFO", result);
            
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            if(role!=null){
                if (role.equalsIgnoreCase("Admin")) {
                    url = ADMIN;
                } else if (role.equalsIgnoreCase("User")) {
                    url = USER;
                }
            }
        } catch (Exception e) {
            LOGGER.error("ERROR at SearchBookController: " + e.getMessage());
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

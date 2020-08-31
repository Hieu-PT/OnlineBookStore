
package hieupt.controllers;

import hieupt.daos.UserDAO;
import hieupt.dtos.UserDTO;
import hieupt.dtos.UserErrorObj;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


public class LoginController extends HttpServlet {
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";
    private static final String INVALID = "login.jsp";
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try{
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            
            UserErrorObj errorObj = new UserErrorObj();
            boolean valid = true;
            if(username.length() == 0){
                errorObj.setUsernameError("Username can't be blank");
                valid = false;
            }
            if(password.length() == 0){
                errorObj.setPasswordError("Password can't be blank");
                valid = false;
            }
            if(valid){
                UserDAO dao = new UserDAO();
                UserDTO dto = dao.checkLogin(username, password);
                if(dto == null){
                    request.setAttribute("ERROR", "Invalid Username or Password");
                }else {
                    String role = dto.getRole();
                    HttpSession session = request.getSession();
                    session.setAttribute("ROLE", role);
                    session.setAttribute("USERNAME", username);
                    session.setAttribute("NAME", dto.getFullname());
                    if(role.equalsIgnoreCase("Admin"))
                        url = ADMIN;
                    else if(role.equalsIgnoreCase("User"))
                        url = USER;
                    else request.setAttribute("ERROR", "Your role is invalid");
                }
            } else request.setAttribute("INVALID", errorObj);
        }
        catch (Exception e){
            LOGGER.error("ERROR at LoginController: " + e.getMessage());
        }
        finally {
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

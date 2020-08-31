
package hieupt.controllers;

import hieupt.daos.BookDAO;
import hieupt.dtos.BookDTO;
import hieupt.dtos.BookErrorObj;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 *50,
        maxRequestSize = 1024 * 1024 * 50)
public class CreateBookController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String RESPONSE = "createBook.jsp";
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList(new String[]{"tiff","tif","bmp","jpg","jpeg","png","eps","raw","psd","gif"});
    
    private static final Logger LOGGER = Logger.getLogger(CreateBookController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("txtID");
            String title = request.getParameter("txtTitle");
            String author = request.getParameter("txtAuthor");
            String stringPrice = request.getParameter("txtPrice");
            String category = request.getParameter("txtCategory");
            String stringQuantity = request.getParameter("txtQuantity");
            String description = request.getParameter("txtDescription");
            Part imagePart = request.getPart("image");
            
            BookDAO dao = new BookDAO();
            BookDTO dto;
            String validPrice = "^\\d+.\\d+";
            String validQuantity = "^\\d+$";
            
            double price = 0;
            int quantity = 0;
            
            BookErrorObj errorObj = new BookErrorObj();
            boolean valid = true;
            if(id.length() == 0){
                errorObj.setIdError("ID can't be blank");
                valid = false;
            }
            if(dao.findByPrimaryKey(id) != null){
                errorObj.setIdError("ID: '" + id + "'is already existed");
                valid = false;
            }
            if(title.length() == 0){
                errorObj.setTitleError("Title can't be blank");
                valid = false;
            }
            if(author.length() == 0){
                errorObj.setAuthorError("Author can't be blank");
                valid = false;
            }
            
            if(stringPrice.matches(validPrice) ||stringPrice.matches(validQuantity)){
                if(Double.parseDouble(stringPrice) > 0)
                    price = Double.parseDouble(stringPrice);
                else{
                    errorObj.setPriceError("Price must over than 0");
                    valid = false;
                }
            }else{
                errorObj.setPriceError("Price must be a number and over 0");
                valid = false;
            }
            if(category.equals("Any")){
                errorObj.setCategoryError("Select Category");
                valid = false;
            }
            if(stringQuantity.matches(validQuantity)){
                quantity = Integer.parseInt(stringQuantity);
            }else{
                errorObj.setQuantityError("Quantity must be an integer number");
                valid = false;
            }
            if(description.length() == 0){
                errorObj.setDescriptionError("Description can't be blank");
                valid = false;
            }
            int lastIndexBeforeExtension = imagePart.getSubmittedFileName().lastIndexOf(".");
            String imageExtension = imagePart.getSubmittedFileName().substring(lastIndexBeforeExtension + 1);
            if(!IMAGE_EXTENSIONS.contains(imageExtension)){
                errorObj.setImageError("Extensions of Image must be .jpg, .png, ...!");
                valid = false;
            }
            
            if(valid){
                dto = new BookDTO(id, title, author, category, "", description, "", price, quantity);
                if(!dao.insertBook(dto)){
                    request.setAttribute("ERROR", "Insert Failed");
                } else{
                    ServletContext sc = getServletContext();
                    String imageName = id + "." + imageExtension;
                    String imageFolder = (String) sc.getAttribute("IMAGE_FOLDER");
                    imagePart.write(imageFolder + imageName);
                    
                    String relativePath = sc.getInitParameter("imageFolderName") + File.separator + imageName;
                    if(dao.updateImage(dto.getId(),relativePath)){
                        request.setAttribute("MESSAGE", "Insert Successfully");
                    } else request.setAttribute("MESSAGE", "Insert Image Failed");
                }
            }else {
                request.setAttribute("INVALID", errorObj);
            }
            url = RESPONSE;
            request.setAttribute("CATEGORY", category);
        } catch (Exception e) {
            LOGGER.error("ERROR at CreateBookController: " + e.getMessage());
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

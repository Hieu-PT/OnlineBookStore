/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieupt.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class AuthFilter implements Filter {
    private List<String> admin;
    private List<String> user;
    private List<String> guest;
    private final String[] listPermission = {"", "searchBook.jsp", "login.jsp", "error.jsp", "404.jsp", "header.jsp", "footer.jsp", "LoadCategory"};
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthFilter() {
        loadAdminPermission();
        loadUserPermission();
        loadGuestPermission();
    }    
    
    private void loadAdminPermission() {
        admin = new ArrayList<>();
        admin.addAll(Arrays.asList(listPermission));
        admin.add("admin.jsp");
        admin.add("createBook.jsp");
        admin.add("createDiscount.jsp");
        admin.add("SearchBook");
        admin.add("UpdateBookController");
        admin.add("DeleteBook");
        admin.add("CreateBookController");
        admin.add("CreateDiscount");
        admin.add("EditBook");
        admin.add("Login");
        admin.add("Logout");
    }
    
    private void loadUserPermission() {
        user = new ArrayList<>();
        user.addAll(Arrays.asList(listPermission));
        user.add("user.jsp");
        user.add("checkOut.jsp");
        user.add("viewCart.jsp");
        user.add("viewOrderHistory.jsp");
        user.add("Login");
        user.add("Logout");
        user.add("AddToCart");
        user.add("CheckDiscountCode");
        user.add("ConfirmCart");
        user.add("PayOnline");
        user.add("RemoveItem");
        user.add("Request");
        user.add("SearchBook");
        user.add("SearchOrderHistory");
        user.add("UpdateCart");
        user.add("UpdatePayment");
        user.add("ViewCart");
    }
    
    private void loadGuestPermission() {
        guest = new ArrayList<>();
        guest.addAll(Arrays.asList(listPermission));
        guest.add("login.jsp");
        guest.add("Login");
        guest.add("SearchBook");
    }
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String resource = uri.substring(index + 1);
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("ROLE") == null) {
            //chua dang nhap
            if (guest.contains(resource) || resource.matches(".*(css|jpg|png|gif|js|woff2)")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("login.jsp");
            }
        } else {
            //da dang nhap
            String role = (String) session.getAttribute("ROLE");
            if (!role.equals("")) {
                if ((role.equals("Admin") && admin.contains(resource)) || resource.matches(".*(css|jpg|png|gif|js|woff2)")) {
                    chain.doFilter(request, response);
                } else if ((role.equals("User") && user.contains(resource)) || resource.matches(".*(css|jpg|png|gif|js|woff2)")) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("404.jsp");
                }
            } else {
                res.sendRedirect("404.jsp");
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}


package hieupt.listeners;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener("application context listener")
public class ImageStorageListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String fullPath = sc.getRealPath("");
        fullPath = fullPath.replace("\\", "/");
        
        String imageFolder = sc.getInitParameter("imageFolderName");
        
        int lastIndexOfBuildFile = fullPath.indexOf("/build/web");
        fullPath = fullPath.substring(0, lastIndexOfBuildFile) + "/web/" + imageFolder + "/";
        
        File file = new File(fullPath);
        if (!file.exists()){
            file.mkdir();
        }
        
        sc.setAttribute("IMAGE_FOLDER", fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}

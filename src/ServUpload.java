
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.ImageTblDao;

@MultipartConfig
@WebServlet("/UploadServlet")
public class ServUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

	        System.out.println(uploadPath);
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }

	        Part filePart = request.getPart("file");
	        String fileName = "default.jpg";

	        if (filePart != null) {
	            fileName = extractFileName(filePart);
	            String filePath = uploadPath + File.separator + fileName;
	            System.out.println("Copy "+filePath);
	            try (InputStream input = filePart.getInputStream()) {
	                Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
             try {
				new ImageTblDao().saveName(fileName);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        response.sendRedirect("show.jsp?img="+fileName);
	    }

	    private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");

	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length() - 1);
	            }
	        }
	        return "default.jpg";
	    }
}

package vn.phatbee.sachservletjdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/image")
public class DownloadFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "webapp/images";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(getServletContext().getRealPath("/") + UPLOAD_DIR + "/" + fileName);
            if (file.exists()) {
                resp.setContentType("image/jpeg");  // Hoặc có thể là PNG, tùy thuộc vào loại ảnh
                resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                try (FileInputStream fileInputStream = new FileInputStream(file);
                     OutputStream outputStream = resp.getOutputStream()) {
                    IOUtils.copy(fileInputStream, outputStream);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            }
        }
    }
}

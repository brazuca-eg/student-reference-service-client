package com.nure.kravchenko.student.reference.client.servlet.download;

import com.nure.kravchenko.student.reference.client.service.WorkerService;
import org.springframework.core.io.ByteArrayResource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.WORKER_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.DOWNLOAD_PATH;

@WebServlet(DOWNLOAD_PATH)
public class DownloadReportServlet extends HttpServlet {

    private static final long serialVersionUID = -8035025560059504668L;

    private WorkerService workerService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.workerService = (WorkerService) ctx.getAttribute(WORKER_SERVICE);
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");

        String fileName = (String) request.getSession().getAttribute("s3FileName");
        ByteArrayResource byteArrayResource = workerService.downloadReport(fileName, token);
        InputStream inputStream = byteArrayResource.getInputStream();
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[byteArrayResource.getByteArray().length];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }
}

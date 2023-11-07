package com.democoding.accounts.Controller;

import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Service.library.GeneralFunction;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class ReceiptController {
    @Autowired
    private GeneralFunction generalFunction;

    @RequestMapping(value = "/receipt/report/{id}", method = RequestMethod.GET)
    public void getReport(@PathVariable("id") String id, HttpServletResponse response) {

        id = GeneralFunction.decrypt(id);
        try {

            // This sends file to browser

            id = id + ".xlsx";

            String workingDir = generalFunction.getBaseUrl();
//                log.debug("Working Directory : " + workingDir);

            String reportFile = generalFunction.addSeparator(workingDir, "report" + File.separator + "tmp" + File.separator + id);
//                log.debug("Report Directory : " + reportFile);

            File file = new File(reportFile);
            InputStream inputStream = null;
            inputStream = new FileInputStream(reportFile);

            byte[] buffer = new byte[Integer.parseInt(String.valueOf(file.length()))];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1)
                baos.write(buffer, 0, bytesRead);

            inputStream.close();
            file.delete();

            response.setContentType("text/html");
            response.addHeader("Content-Disposition", "attachment; filename=" + id);

            byte[] outBuf = baos.toByteArray();
            response.getOutputStream().write(outBuf);

        } catch (Exception e) {
            throw new ResourceNotAcceptableException(e.getMessage());
        }

    }
}

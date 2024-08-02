package io.getarrays.securecapita.PurchaseRequest.Service;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Repository.PurchaseRequestRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


@Service
public class PurchaseRequestServiceReport {

    @Autowired

    PurchaseRequestRepo purchaseRequestRepo;


    public String exportReport(String reportFormat)  throws FileNotFoundException, JRException {

        List<PurchaseRequest> purchaseRequests = purchaseRequestRepo.findAll();
        File file = ResourceUtils.getFile("classpath:purchaseRequests.jrxml");

            JasperReport jasperReport =  JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(purchaseRequests);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        }

        return "report generated in path : " + path;
    }




            // Additional code for exporting to other formats
          //  return "Report successfully generated @path= " + reportPath;

    // Methods for exporting to CSV, XLSX, etc.
}



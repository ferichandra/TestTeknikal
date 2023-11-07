package com.democoding.accounts.Service;

import com.democoding.accounts.Entity.SalesReport;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Service.library.GeneralFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {
    @Autowired
    private NativeQuery nativeQuery;
    @Autowired
    private GeneralFunction generalFunction;

    public SalesReport reportEmployee(String export) {
        SalesReport salesReport = new SalesReport();
        String errorCode = null;

        try {
            List<Object[]> resultList = new ArrayList<>(this.nativeQuery.listEmployee());


            if (export.equals("1")) {
                List<String> label = new ArrayList<>();
                List<String> text = new ArrayList<>();
                List<String> tableHeader = new ArrayList<>();
                label.add("Report Employee");
                text.add("");

                tableHeader.add("Name + Role");
                tableHeader.add("Age");
                tableHeader.add("Address");
                tableHeader.add("Email");


                Map<String, Object> map = new HashMap<>();
                map.put("reportName", "Report Employee");
                map.put("label", label);
                map.put("text", text);
                map.put("tableHeader", tableHeader);
                salesReport.setLink(generalFunction.exportXLS(map, resultList));
            }
        } catch (Exception e) {
            errorCode = e.getMessage();
            throw new ResourceNotAcceptableException(errorCode);
        }
        return salesReport;
    }

}

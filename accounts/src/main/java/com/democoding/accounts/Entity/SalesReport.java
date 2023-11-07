package com.democoding.accounts.Entity;


import java.util.ArrayList;
import java.util.List;

public class SalesReport {
    private List<ReportField> report;
    private String link;

    public List<ReportField> getReport() {
        return report;
    }

    public void addReport(Object[] report) {
        if(this.report==null){
            this.report = new ArrayList<>();
        }

        ReportField reportDetail = new ReportField();

        for(Object object:report){
            reportDetail.addField(object);
        }

        this.report.add(reportDetail);
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

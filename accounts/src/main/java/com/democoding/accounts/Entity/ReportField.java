package com.democoding.accounts.Entity;

import java.util.ArrayList;
import java.util.List;


public class ReportField {

    private List<String> field;

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public void addField(Object object){
        if(this.field==null){
            this.field = new ArrayList<>();
        }
        try {
            this.field.add(object.toString());
        }catch (Exception e){
            this.field.add("N/A");
        }
    }

}

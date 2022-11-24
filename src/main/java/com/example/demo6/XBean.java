package com.example.demo6;

import com.example.demo6.BD.TableDAO;
import com.example.demo6.BD.TableDAOImpl;
import lombok.Data;
import org.hibernate.service.spi.InjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
@Data
public class XBean {

    @Inject
    private double value1;
    private Double Z;
    private Double inputText;
    private boolean hit = false;

//    public String getInputText() {
//        return inputText;
//    }
//
//    public void setInputText(String inputText) {
//        this.inputText = inputText;
//    }
//
//
//    public String getZ() {
//        return Z;
//    }
//
//    public void setZ(String Z) {
//        this.Z = Z;
//    }
//
//    public double getValue1() {
//        return value1;
//    }
//
//    public void setValue1(double value1) {
//        this.value1 = value1;
//    }

    //    public boolean getHit(){
//        return hit;
//    }
    public void setHit() {

        if (((value1 * value1 + inputText * inputText) <= Z * Z && value1 <= 0 && inputText >= 0) ||
                (inputText + value1 <= Z && value1 >= 0 && inputText <= 0) ||
                (inputText / 2 >= (value1 - Z / 2) && value1 >= 0 && inputText >= 0)) {
            hit = true;
        } else {
            hit = false;
        }
    }

    ArrayList<ResultTable> Result = new ArrayList<ResultTable>();

    public ArrayList<ResultTable> getEmployees() {
        return Result;
    }


    public String verifyUser() throws Throwable {
        setHit();
        ResultTable res = new ResultTable(value1, inputText, Z, hit);
        Result.add(res);
//        ResultTable resultTable = new ResultTable(5.0,5.0,5.0,true);
        try {
            TableDAO tableDAO = new TableDAOImpl();
            tableDAO.save(res);
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
        return "success";
    }
}

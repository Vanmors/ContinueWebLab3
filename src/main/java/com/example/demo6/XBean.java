package com.example.demo6;

import com.example.demo6.BD.TableDAO;
import com.example.demo6.BD.TableDAOImpl;
import com.example.demo6.MBeans.PointCount;
import lombok.Data;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
@Data
public class XBean {

    @Inject
    private double x;
    private Double r;
    private Double y;
    private boolean hit = false;

    public void setHit() {

        if (((x * x + y * y) <= r * r && x <= 0 && y>= 0) ||
                (y + x <= r && x >= 0 && y <= 0) ||
                (y / 2 >= (x - r / 2) && x >= 0 && y >= 0)) {
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
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String jvmName = bean.getName();
        System.out.println("Name = " + jvmName);
        TableDAO tableDAO = new TableDAOImpl();
        long pid = Long.parseLong(jvmName.split("@")[0]);
        System.out.println("PID  = " + pid);

        setHit();
        ResultTable res = new ResultTable(tableDAO.findAll().size() + 1, x, y, r, hit);
//        res.setId(tableDAO.findAll().size());
        Result.add(res);

//        ResultTable resultTable = new ResultTable(5.0,5.0,5.0,true);
        try {

            tableDAO.save(res);
            PointCount pointCount = new PointCount();
            pointCount.increment();
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
        return "success";
    }
}

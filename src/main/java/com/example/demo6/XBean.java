package com.example.demo6;

import com.example.demo6.BD.TableDAO;
import com.example.demo6.BD.TableDAOImpl;
import com.example.demo6.MBeans.PointCount;
import lombok.Data;
import org.hibernate.Criteria;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.service.spi.InjectService;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.management.ManagementFactory;
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
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String jvmName = bean.getName();
        System.out.println("Name = " + jvmName);
        TableDAO tableDAO = new TableDAOImpl();
        long pid = Long.parseLong(jvmName.split("@")[0]);
        System.out.println("PID  = " + pid);

        setHit();
        ResultTable res = new ResultTable(tableDAO.findAll().size() + 1, value1, inputText, Z, hit);
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

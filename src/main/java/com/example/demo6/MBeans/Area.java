package com.example.demo6.MBeans;

import com.example.demo6.BD.TableDAO;
import com.example.demo6.BD.TableDAOImpl;
import com.example.demo6.ResultTable;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.management.NotificationBroadcasterSupport;


@Setter
@ManagedBean
@SessionScoped
public class Area extends NotificationBroadcasterSupport implements AreaMBean {
    private double area;
    TableDAO tableDAO = new TableDAOImpl();


    @Override
    public double calculateArea() {
        ResultTable resultTable = tableDAO.findById(tableDAO.findAll().size());
        double x = resultTable.getX();
        double y = resultTable.getY();
        double r = resultTable.getR();

        if ((x <= 0 && y <= 0) || r == 0) {
            return 0;
        } else if (x >= 0 && y >= 0) {
            return r * r / 2;
        } else if (x >= 0 && y <= 0) {
            return Math.pow(r, 2) / 2;
        } else if (x <= 0 && y >= 0) {
            return (Math.PI * Math.pow(r, 2)) / 4;
        }
        return 0;
    }

    @Override
    public double getArea() {
        return calculateArea();
    }



    @PostConstruct
    public void init() {
        MBeanRegistration.registerMBean(this, "AreaBean");
    }

    @PreDestroy
    public void preDestroy() {
        MBeanRegistration.unregisterMBean(this, "AreaBean");
    }
}

package com.example.demo6.MBeans;

import com.example.demo6.BD.TableDAO;
import com.example.demo6.BD.TableDAOImpl;
import com.example.demo6.XBean;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.component.FacesComponent;
import javax.inject.Inject;
import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

@Setter
@ManagedBean
@SessionScoped
public class PointCount extends NotificationBroadcasterSupport implements Serializable, PointCountMBean {
    private int total = 0;
    private int hits = 0;
    private long sequenceNumber = 0;
    private int lastElementTotal = 0;
    private int lastElementHits = 0;
    TableDAO tableDAO = new TableDAOImpl();


    public void increment() {
        total++;
    }

    @Override
    public int getTotal() {
        if (last4Misses()) {
            sendNotification(new Notification("4 промаха подряд", this.getClass().getName(), sequenceNumber++, "Было совершено 4 промаха подряд"));
        }
        if (!(lastElementTotal == tableDAO.findById(tableDAO.findAll().size()).getId())) {
            lastElementTotal = tableDAO.findById(tableDAO.findAll().size()).getId();
            total++;
        }
        return total;
    }

    public boolean last4Misses() {
        if (!tableDAO.findById(tableDAO.findAll().size()).isHit()
                && !tableDAO.findById(tableDAO.findAll().size() - 1).isHit()
                && !tableDAO.findById(tableDAO.findAll().size() - 2).isHit()
                && !tableDAO.findById(tableDAO.findAll().size() - 3).isHit()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int getHits() {
        if (tableDAO.findById(tableDAO.findAll().size()).isHit()
                && !(lastElementHits == tableDAO.findById(tableDAO.findAll().size()).getId())) {
            lastElementHits = tableDAO.findById(tableDAO.findAll().size()).getId();
            hits++;
        }
        return hits;
    }


    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String description = "4 промаха подряд";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }


    @PostConstruct
    public void init() {
        MBeanRegistration.registerMBean(this, "CountBean");
    }

    @PreDestroy
    public void preDestroy() {
        MBeanRegistration.unregisterMBean(this, "CountBean");
    }


}

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

    TableDAO tableDAO = new TableDAOImpl();

    public int increment() {
        total++;
        return total;
    }

    @Override
    public int getTotal() {
        sendNotification(new Notification("4 промаха подряд", this.getClass().getName(), sequenceNumber++, "Было совершено 4 промаха подряд"));
        total++;
        return total;
    }

    @Override
    public int getHits() {
        if (tableDAO.findById(tableDAO.findAll().size()).isHit()) {
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

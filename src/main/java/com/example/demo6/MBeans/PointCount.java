package com.example.demo6.MBeans;

import com.example.demo6.XBean;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.component.FacesComponent;
import javax.inject.Inject;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

@Setter
@ManagedBean
@ViewScoped
public class PointCount extends NotificationBroadcasterSupport implements Serializable, PointCountMBean {
    private int total = 0;
    private int hits = 0;
    //    private XBean xBean = new XBean();
//    @ManagedProperty("#{xBean}")
//    private XBean xBean;

    public int increment() {
        total++;
        return total;
//        return xBean.getEmployees().size();
    }


    @Override
    public int getTotal() {
//        return xBean.getEmployees().size();
//        System.out.println(xBean.getZ());
        total++;
        return total;
    }

    @Override
    public int getHits() {

        return hits;
    }

    @PostConstruct
    public void init() {
        MBeanRegistration.registerMBean(this, "CountBeanName");
    }

    @PreDestroy
    public void preDestroy() {
        MBeanRegistration.unregisterMBean(this, "CountBeanName");
    }


}

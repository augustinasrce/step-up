package com.haka.stepup.db;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="device_data")
public class DeviceData {

    private Long id;
    //I failed at mapping entities... :////
    private Long userDevice;
    private Integer stepCount;
    private DateTime date;

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "user_device_id")
    public Long getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(Long userDevice) {
        this.userDevice = userDevice;
    }

    //    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="user_device_id")
//    public UserDevice getUserDevice() {
//        return userDevice;
//    }
//
//    public void setUserDevice(UserDevice userDevice) {
//        this.userDevice = userDevice;
//    }

    @Column(name = "step_count")
    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    @Column(name="info_date")
    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

}

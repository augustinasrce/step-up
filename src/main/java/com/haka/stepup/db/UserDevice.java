package com.haka.stepup.db;

import javax.persistence.*;

@Entity
@Table(name="user_device")
public class UserDevice {

    private Long id;
    private String deviceCode;
    private Long user;

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "device_code")
    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    @Column(name="user_id")
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}

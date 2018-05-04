package com.haka.stepup.db;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DeviceDataDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DeviceData> getAllData(){
        Query query = entityManager.createQuery("SELECT dd FROM DeviceData dd");
        return (List<DeviceData>) query.getResultList();
    }

    @Transactional
    public void insertNewData(DateTime date, int stepCount, Long deviceId){
        DeviceData deviceData = new DeviceData();
        deviceData.setDate(date);
        deviceData.setStepCount(stepCount);
        deviceData.setUserDevice(deviceId);
        entityManager.persist(deviceData);
    }

}

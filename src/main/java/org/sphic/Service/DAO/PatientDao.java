package org.sphic.Service.DAO;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class PatientDao extends Dao{

    public String save(Patient p){

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        String patientId = (String) session.save(p);

        tx.commit();
        return patientId;
    }


    public List<Patient> getAll()
    {
        Session session = HibernateUtil.currentSession();
        List<Patient> list =session.createSQLQuery("select * from patient").addEntity(Patient.class).list();
        for (Patient p : list) {
            Hibernate.initialize(p.getStudies());
        }

        return list;
    }
}

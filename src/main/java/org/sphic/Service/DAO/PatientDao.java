package org.sphic.Service.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PatientDao extends Dao{
    public String save(Patient p){

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        String patientId = (String) session.save(p);
        session.flush();
        tx.commit();
        return patientId;
    }
}

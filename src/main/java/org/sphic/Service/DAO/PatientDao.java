package org.sphic.Service.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sphic.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PatientDao extends Dao{

    private SessionFactory sessionFactory;

    @Autowired
    public PatientDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public String save(Patient p){

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        String patientId = (String) session.save(p);
        session.flush();
        tx.commit();
        return patientId;
    }
}

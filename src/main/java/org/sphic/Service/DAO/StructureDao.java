package org.sphic.Service.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sphic.Model.Structure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StructureDao extends Dao{
    private SessionFactory sessionFactory;

    @Autowired
    public StructureDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public int save(Structure structure){

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        int structureId = (Integer) session.save(structure);
        session.flush();
        tx.commit();
        return structureId;
    }
}

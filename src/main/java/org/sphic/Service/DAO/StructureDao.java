package org.sphic.Service.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Structure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StructureDao extends Dao{

    public int save(Structure structure){

        Session session = HibernateUtil.currentSession();

        int structureId = (Integer) session.save(structure);
        session.flush();
        return structureId;
    }
}

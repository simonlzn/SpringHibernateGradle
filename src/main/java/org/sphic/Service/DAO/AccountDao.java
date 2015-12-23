package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Account;
import org.sphic.Model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class AccountDao extends Dao{

    public Account getByUsername(String username){
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Account.class).add(Restrictions.eq("username", username));

        List list = criteria.list();
        tx.commit();
        if (list.isEmpty())
            return null;

        return (Account) list.get(0);
    }
}

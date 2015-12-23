package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public class AccountDao extends Dao{

    public Account getByUsername(String username){
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(Account.class).add(Restrictions.eq("username", username));

        List list = criteria.list();
        if (list.isEmpty())
            return null;

        return (Account) list.get(0);
    }
}

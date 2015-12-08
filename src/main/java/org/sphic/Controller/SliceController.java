package org.sphic.Controller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Account;
import org.sphic.Model.Contour;
import org.sphic.Model.Slice;
import org.sphic.Model.Structure;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slice")
public class SliceController {
    public SliceController() {
    }

    @RequestMapping(value = "/{seriesId}/{view}/{number}", method = RequestMethod.GET)
    public Slice Get(@PathVariable int seriesId, @PathVariable char view, @PathVariable int number) {
        Session session = HibernateUtil.currentSession();
        Criteria cr = session.createCriteria(Account.class).add(Restrictions.eq("series_id", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
        Slice slice = (Slice) cr.list().get(0);

        return slice;
    }
}

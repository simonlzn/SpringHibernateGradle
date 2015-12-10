package org.sphic.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/slice")
public class SliceController {
    public SliceController() {
    }

    @RequestMapping(value = "/{seriesId}/{view}/{number}", method = RequestMethod.GET)
    public DeferredResult<String> Get(@PathVariable int seriesId, @PathVariable char view, @PathVariable int number, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "private, max-age=86400");
        Session session = HibernateUtil.currentSession();
        Criteria cr = session.createCriteria(Slice.class).add(Restrictions.eq("seriesId", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
        List sliceList = cr.list();
        final DeferredResult<String> result = new DeferredResult<String>();
        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                result.setErrorResult("Time out");
            }
        });


        if (sliceList.isEmpty()) {
            try {
                String views = "";
                if (view == 'T')
                    views += number + ",-1,-1";

                String url = "http://localhost:8080/itk/call?func=slicing&views=" + views + "&id=" + seriesId;
                String ret;// = getHTTPResponse(url).toString();


                ret = ":[{view:'trabsverse',row:512,column:512,rowspacing:0.6,columnspacing:0.6,data:'-1000,-995, 850, 900, 1000'}, {view:'coronal',row:512,column:394,rowspacing:0.6,columnspacing:1.0,data:'-1000,-995,850,900,1000'},{}]";
                List<Slice> userData = new ObjectMapper().readValue(ret, ArrayList.class);
                result.setResult(ret);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Slice slice = (Slice) sliceList.get(0);
            result.setResult(slice.toString());
        }

        return result;
    }

    private StringBuffer getHTTPResponse(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer r = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            r.append(line);
        }
        return r;
    }
}

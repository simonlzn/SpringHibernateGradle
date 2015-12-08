package org.sphic.Controller;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/slice")
public class SliceController {
    public SliceController() {
    }

    @RequestMapping(value = "/{seriesId}/{view}/{number}", method = RequestMethod.GET)
    public DeferredResult<String> Get(@PathVariable int seriesId, @PathVariable char view, @PathVariable int number) {
        Session session = HibernateUtil.currentSession();
        Criteria cr = session.createCriteria(Slice.class).add(Restrictions.eq("seriesId", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
        List sliceList = cr.list();
        final DeferredResult<String> result = new DeferredResult<String>();
//        result.onTimeout(new Runnable() {
//            @Override
//            public void run() {
//                result.setErrorResult("Time out");
//            }
//        });

        if (sliceList.isEmpty()) {
            try {
                String url = "http://localhost:8080/itk/call?func=slicing&view=" + view + "&number=" + number + "&id=" +seriesId;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(url);
//                StringBuffer response = getHTTPResponse(url);
                HttpResponse response = client.execute(request);
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer r = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    r.append(line);
                }
                result.setResult(r.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Slice slice = (Slice) sliceList.get(0);
            result.setResult(slice.toString());

        }

        return result;
    }

    private StringBuffer getHTTPResponse(URL url) throws IOException {

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();
//        if (responseCode != 200)
//            result.setErrorResult(responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }
}

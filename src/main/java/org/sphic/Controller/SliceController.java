package org.sphic.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.*;
import org.sphic.Service.DAO.Dao;
import org.sphic.Service.ITKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/slice")
public class SliceController {
    private Dao dao;
    private ITKService itkService;

    @Autowired
    public SliceController(Dao dao, ITKService itkService) {
        this.dao = dao;
        this.itkService = itkService;
    }

    @RequestMapping(value = "/{seriesId}/{view}/{number}", method = RequestMethod.GET)
    public DeferredResult Get(@PathVariable final int seriesId, @PathVariable final char view, @PathVariable final int number, final HttpServletResponse response, final HttpServletRequest request) {
        response.setHeader("Cache-Control", "private, max-age=86400");
        Session session = HibernateUtil.currentSession();
        Criteria cr = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
        List<Slice> sliceList = cr.list();
        String channel = request.getRequestURI() + (request.getQueryString() == null ? "" :  "?" + request.getQueryString());
        DeferredResult result = new DeferredResult();

        if (sliceList.isEmpty()) {

            String views = constructViewsString(view, number);
            result = itkService.Slicing(channel, String.valueOf(seriesId), views);

//                ret = "[{\"view\":\"trabsverse\",\"row\":512,\"column\":512,\"rowspacing\":0.6,\"columnspacing\":0.6,\"data\":\"-1000,-995, 850, 900, 1000\"}, {\"view\":\"coronal\",\"row\":512,\"column\":394,\"rowspacing\":0.6,\"columnspacing\":1.0,\"data\":\"-1000,-995,850,900,1000\"},{}]";
            final DeferredResult finalResult = result;
            result.onCompletion(new Runnable() {
                @Override
                public void run() {
                    for (Map userData : (List<Map>)finalResult.getResult()) {
                        if (userData.size() > 0) {
                            Slice slice = new Slice(view, number, Integer.parseInt(userData.get("row").toString()), Integer.parseInt(userData.get("column").toString()), Double.parseDouble(userData.get("rowspacing").toString()), Double.parseDouble(userData.get("columnspacing").toString()), null, 0, 0, null, new Date(), new Date(), null, "", userData.get("data").toString(), null);
                            System.out.println(userData);
                            Series series = dao.get(Series.class, seriesId);
                            slice.setSeries(series);
                            dao.save(slice);
                        }
                    }
                }
            });

        } else if (sliceList.get(0).getData() == null) {
            final Slice slice = sliceList.get(0);

            String views = constructViewsString(view, number);
            result = itkService.Slicing(channel, String.valueOf(seriesId), views);


            final DeferredResult finalResult1 = result;
            result.onCompletion(new Runnable() {
                @Override
                public void run() {
                    String ret = finalResult1.getResult().toString();
                    List<Map> userDatas = null;
                    try {
                        userDatas = new ObjectMapper().readValue(ret, List.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    for (Map userData : userDatas) {
                        if (userData.size() > 0) {
                            slice.setData(userData.get("data").toString());
                            Series series = dao.get(Series.class, seriesId);
                            slice.setSeries(series);
                            dao.save(slice);
                        }
                    }
                }
            });


        } else {
            Slice slice = sliceList.get(0);
            result.setResult(slice);
        }

        return result;
    }


    private String constructViewsString(@PathVariable char view, @PathVariable int number) {
        String views = "";
        switch (view) {
            case 'T':
                views += number + ",-1,-1";
                break;
            case 'S':
                views += "-1,-1," + number;
                break;
            case 'C':
                views += "-1," + number + ",-1";
                break;
        }
        return views;
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

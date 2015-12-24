package org.sphic.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.sphic.Model.Series;
import org.sphic.Model.Slice;
import org.sphic.Service.DAO.SliceDao;
import org.sphic.Service.ITKService;
import org.sphic.Service.SliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/slice")
public class SliceController {
    private SliceDao dao;
    private ITKService itkService;
    private SliceService sliceService;

    @Autowired
    public SliceController(SliceDao dao, ITKService itkService, SliceService sliceService) {
        this.dao = dao;
        this.itkService = itkService;
        this.sliceService = sliceService;
    }

    @RequestMapping(value = "/{seriesId}/{view}/{number}", method = RequestMethod.GET)
    public DeferredResult Get(@PathVariable final int seriesId, @PathVariable final char view, @PathVariable final int number, final HttpServletResponse response, final HttpServletRequest request) {


        final Slice slice = dao.getByViewAndNumber(seriesId, view,number);


        DeferredResult result = new DeferredResult();

        if (slice == null || slice.getData() == null) {
            String views = constructViewsString(view, number);

            String channel = request.getRequestURI() + (request.getQueryString() == null ? "" :  "?" + request.getQueryString());
            result = itkService.Slicing(channel, String.valueOf(seriesId), views);

            final DeferredResult finalResult = result;
            result.onCompletion(new Runnable() {
                @Override
                public void run() {
                    if (finalResult.getResult() == "Time out")
                        return;

                    for (Map userData : (List<Map>)finalResult.getResult()) {
                        if (userData.size() > 0) {
                            sliceService.saveOrUpdateData(seriesId, view, number, userData);
                        }
                    }
                }
            });

        }  else {
            response.setHeader("Cache-Control", "private, max-age=86400");
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
}

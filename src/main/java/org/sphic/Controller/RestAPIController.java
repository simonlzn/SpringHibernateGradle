package org.sphic.Controller;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.sphic.Service.ContourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAPIController {
    private ContourService contourService;

    @Autowired
    public RestAPIController(ContourService contourService) {
        this.contourService = contourService;
    }

    @RequestMapping("/vtk")
    public String startVTK(@RequestParam String cmd, @RequestParam String filename) throws IOException {
        String url = "http://localhost:8081/paraview/";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);


        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("sessionManagerURL", "localhost:8081/paraview/"));
        urlParameters.add(new BasicNameValuePair("application", cmd));
        urlParameters.add(new BasicNameValuePair("filename", filename + ".nrrd"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

        return result.toString();
    }

    @RequestMapping("/contour")
    public String contour() throws IOException {
        String message = contourService.ConstructDataByStructureId(8);
        return message;
    }
}

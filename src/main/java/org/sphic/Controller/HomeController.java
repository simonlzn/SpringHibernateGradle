package org.sphic.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.sphic.Model.Patient;
import org.sphic.Service.DAO.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/home")
public class HomeController {
	
	private PatientDao patientDao;
	
	@Autowired
	public HomeController(PatientDao patientDao) {
		this.patientDao = patientDao;
	}	
	
	@RequestMapping(value = "/main",  method = RequestMethod.GET)
    public ModelAndView Login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
    }	
//value = "/{id}", method = RequestMethod.GET
	
	@RequestMapping(value = "/contour/{id}",  method = RequestMethod.GET)
    public ModelAndView contour(@PathVariable String id) {
		Patient patient = patientDao.get(Patient.class, id);
		if (patient == null) {
			patient =  new Patient();
		}
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("patient", patient);
		mav.setViewName("contour");
		return mav;
    }	

	@RequestMapping(value = "/showimage",  method = RequestMethod.GET)
    public ModelAndView showimage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("showimage");
		return mav;
    }	

	@RequestMapping(value = "/dashboard",  method = RequestMethod.GET)
    public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboardh");
		return mav;
    }	

	@RequestMapping(value = "/ftltest",  method = RequestMethod.GET)
    public ModelAndView ftltest() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ftltest");
		

	     mav.addObject("title", "Dashboard");
	     Map <String, String> map = new HashMap<String, String>();
	     map.put("name", "Jie Li");
	     map.put("developer", "Jieli");
	     
	     mav.addObject("exampleObject", map);
	     
		return mav;
    }	
	
	/*
	 @RequestMapping(value="/dashboard", method= RequestMethod.GET) 
	 public ModelAndView getDashboardPage() {
		 System.out.println("dashboard page ");
		 ModelAndView mv = new ModelAndView("dashboard");
	     mv.addObject("title", "Dashboard");
	     Map <String, String> map = new HashMap<String, String>();
	     map.put("name", "Jie Li");
	     map.put("developer", "Jieli");
	     
	     mv.addObject("exampleObject", map);
		
	     System.out.println("mv" + mv);

	     return mv;
	 }

	 @RequestMapping(value="/detail",method= RequestMethod.GET) 
	 public ModelAndView getPatientDetailPage() {
		 ModelAndView mv = new ModelAndView("patientDetail");
	     //mv.addObject("name", "My First Spring Mvc");
	     return mv;
	 }

	 @RequestMapping(value="/segmentation",method=RequestMethod.GET) 
	 public ModelAndView getSegmentationPage() {
		 ModelAndView mv = new ModelAndView("segmentation");
	     //mv.addObject("name", "My First Spring Mvc");
	     return mv;
	 }	
	 */
}

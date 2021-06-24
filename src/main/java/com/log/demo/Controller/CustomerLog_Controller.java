package com.log.demo.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.log.demo.Dao.CustomerLog_Dao;
import com.log.demo.model.CustomerLog_Model;

@RestController
public class CustomerLog_Controller {

	@Autowired
	private CustomerLog_Dao repo;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	String currentDate = dateFormat.format(new Date());
	public static String getTime() {
		while(true) {
			try {
				Thread.sleep(5*100); 
				LocalTime time = LocalTime.now(ZoneId.of("Asia/Kolkata"));
				String currentTime=time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
				return currentTime;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	@GetMapping("/checkIn")
	@ResponseBody
	public String addCheckIn(@RequestParam("name") String name)
	{
		CustomerLog_Model model = new CustomerLog_Model();
		model.setLogType("IN");
		model.setId(name+""+getTime());
		model.setDate(currentDate);
		model.setTime(getTime());
		repo.save(model);
		return "Inserted";
	}
	@GetMapping("/checkOut")
	@ResponseBody
	public String addCheckOut(@RequestParam("name") String name)
	{
		CustomerLog_Model model = new CustomerLog_Model();
		model.setLogType("OUT");
		model.setId(name+""+getTime());
		model.setDate(currentDate);
		model.setTime(getTime());
		repo.save(model);
		return "Inserted";
	}
	@GetMapping("/getLog")
	@ResponseBody
	public List<CustomerLog_Model> logByDate(@RequestParam("date") String date)
	{
		return repo.findByDate(date);
	}
	
	@GetMapping("/getAllLog")
	@ResponseBody
	public List<CustomerLog_Model> getAllLog()
	{
		return repo.findAll();
	}
	
}

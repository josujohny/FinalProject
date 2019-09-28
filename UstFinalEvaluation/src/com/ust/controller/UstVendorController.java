package com.ust.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RestController;

import com.ust.dao.UstVendorDao;
import com.ust.model.Login;
import com.ust.model.VendorDetails;

@RestController
public class UstVendorController {

	@Autowired
	UstVendorDao vDao;

	
	
	// Login Verification Part
	
	@RequestMapping(value = "/api/user/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Login selectUser(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		return vDao.selectRole(username, password);
	}
	
	
	
	

	// view vendor Details

	@RequestMapping(value = "/api/vendor/{vendor_name}", method = RequestMethod.GET)
	@ResponseBody
	public List getVendor(Model m,
			@PathVariable("vendor_name") String vendor_name) {
		List list;
		if (vendor_name.equals("null")) {
			list = vDao.getVendor();
		} else {
			list = vDao.getVendorByName(vendor_name);
		}

		return list;
	}
	
	
	
	

	// view vendors list by their id
	
	@RequestMapping(value = "/api/vendors/{vendor_id}", method = RequestMethod.GET)
	@ResponseBody
	public VendorDetails getvendors(Model m,
			@PathVariable("vendor_id") int vendor_id) {

		return vDao.getVendorById(vendor_id);
	}

	
	
	
	
	// Add Vendor to the list
	
	@ResponseBody
	@RequestMapping(value = "/api/insertvendor", method = RequestMethod.POST)
	public void insertDoctor(@RequestBody VendorDetails vd)
			throws ParseException {
		vDao.saveVendor(vd);
	}
	
	
	
	

	// update Vendor
	
	
	@ResponseBody
	@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
	public void updateDoctor(@RequestBody VendorDetails vd)
			throws ParseException {
		int vendor_id = vd.getVendor_id();
		vDao.updateVendor(vendor_id, vd);
	}

	
	
	
	
	
	// to disable a vendor in the list

	@RequestMapping(value = "/api/disablevendor/{vId}", method = RequestMethod.PUT)
	@ResponseBody
	public void disableStaff(@PathVariable("vId") int vId) {
		vDao.disableVendor(vId);
	}

	
	
	
	
	// view contactdetails by vendor id

	@RequestMapping(value = "/api/contactDetails/{vId}", method = RequestMethod.GET)
	@ResponseBody
	public List getContactDetails(Model m, @PathVariable("vId") int vId) {
		List list;
		list = vDao.getContactDetailsByVId(vId);
		return list;

	}

}

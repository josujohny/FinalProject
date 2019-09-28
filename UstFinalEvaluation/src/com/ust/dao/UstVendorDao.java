package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ust.model.Login;
import com.ust.model.VendorDetails;

public class UstVendorDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	
	
	
	// --------------------LOGIN---------------------//

	public Login selectRole(String username, String password) {
		String sql = "select user_id from login where username='" + username
				+ "' and password='" + password + "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Login>(Login.class));
	}

	
	
	
	
	
	// ------get all vendors----------------------------------------------//

	public List<VendorDetails> getVendor() {
		return template
				.query("select vendor_id,vendor_name,address,location,service,pincode from vendor where isActive=0",
						new RowMapper<VendorDetails>() {
							public VendorDetails mapRow(ResultSet rs, int row)
									throws SQLException {
								VendorDetails vd = new VendorDetails();
								vd.setVendor_id(rs.getInt(1));
								vd.setVendor_name(rs.getString(2));
								vd.setAddress(rs.getString(3));
								vd.setLocation(rs.getString(4));
								vd.setService(rs.getString(5));
								vd.setPincode(rs.getInt(6));
								return vd;
							}
						});
	}
	
	
	
	
	

	// ------get vendor by name-----------------------------------------//

	public List<VendorDetails> getVendorByName(String vendor_name) {
		return template
				.query("select vendor_id,vendor_name,address,location,service,pincode from vendor where isActive=0 and vendor_name='"
						+ vendor_name + "'", new RowMapper<VendorDetails>() {
					public VendorDetails mapRow(ResultSet rs, int row)
							throws SQLException {
						VendorDetails vd = new VendorDetails();
						vd.setVendor_id(rs.getInt(1));
						vd.setVendor_name(rs.getString(2));
						vd.setAddress(rs.getString(3));
						vd.setLocation(rs.getString(4));
						vd.setService(rs.getString(5));
						vd.setPincode(rs.getInt(6));
						return vd;
					}
				});
	}
	
	
	
	
	
	

	// ------------get vendor details by id---------------------------//
	public VendorDetails getVendorById(int vendor_id) {

		String sql = "select vc.vendor_id,vc.vendor_name,vc.address,vc.location,vc.service,vc.pincode,cd.name,cd.department,cd.email,cd.phone,cd.contact_id from vendor vc join contactdetails cd on vc.vendor_id=cd.vendor_id where vc.isActive=0 and vc.vendor_id='"
				+ vendor_id + "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<VendorDetails>(VendorDetails.class));

	}
	
	
	
	
	
	

	// ---------Add vendors---------------------------------//

	public int saveVendor(VendorDetails vd) {

		String sql1 = "insert into vendor(vendor_name,address,location,service,pincode,isActive) values "
				+ "('"
				+ vd.getVendor_name()
				+ "','"
				+ vd.getAddress()
				+ "','"
				+ vd.getLocation()
				+ "','"
				+ vd.getService()
				+ "','"
				+ vd.getPincode() + "'," + 0 + ")";

		template.update(sql1);

		Integer maxId = getSequence();
		String sql2 = "insert into contactdetails(vendor_id,name,department,email,phone) values ("
				+ maxId
				+ ",'"
				+ vd.getName()
				+ "','"
				+ vd.getDepartment()
				+ "','" + vd.getEmail() + "','" + vd.getPhone() + "')";
		return template.update(sql2);

	}
	
	
	
	
	

	// -------------to get vendor id-------------------------------//
	
	private Integer getSequence() {
		Integer seq;
		String sql = "select MAX(vendor_id)from vendor";
		seq = template.queryForObject(sql, new Object[] {}, Integer.class);
		return seq;
	}
	
	
	
	
	
	
	

	// -------------------update vendor--------------------------//
	
	public int updateVendor(int vendor_id, VendorDetails vd) {

		String sql = "update vendor set vendor_name='" + vd.getVendor_name()
				+ "' ,address='" + vd.getAddress() + "' ,location='"
				+ vd.getLocation() + "',service='" + vd.getService()
				+ "',pincode='" + vd.getPincode() + "',isActive="
				+ vd.getIsActive() + " " + "where vendor_id =" + vendor_id;
		template.update(sql);

		
		
		// Integer maxId = getSequence();
		
		

		String sql1 = "update contactdetails set vendor_id=" + vendor_id
				+ ",name='" + vd.getName() + "',department='"
				+ vd.getDepartment() + "',email='" + vd.getEmail()
				+ "',phone='" + vd.getPhone() + "'where contact_id = "
				+ vd.getContact_id();

		return template.update(sql1);
	}
	
	
	
	
	
	

	// -------------- get contactdetails by id-------------------------//

	public List<VendorDetails> getContactDetailsByVId(int vid) {
		return template
				.query("select contact_id,name,department,vendor_id,email,phone from contactdetails where vendor_id="
						+ vid + "", new RowMapper<VendorDetails>() {
					public VendorDetails mapRow(ResultSet rs, int row)
							throws SQLException {
						VendorDetails vc = new VendorDetails();
						vc.setContact_id(rs.getInt(1));
						vc.setName(rs.getString(2));
						vc.setDepartment(rs.getString(3));
						vc.setVendor_id(rs.getInt(4));
						vc.setEmail(rs.getString(5));
						vc.setPhone(rs.getString(6));
						return vc;
					}
				});
	}
	
	
	
	
	

	// --------------- to disable a vendor-----------------------//
	public int disableVendor(int vId) {

		String sql = "update vendor set isActive='1' where vendor_id=" + vId
				+ "";

		return template.update(sql);
	}

}

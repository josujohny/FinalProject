package com.ust.model;

public class VendorDetails {

	private int vendor_id;
	private String vendor_name;
	private String address;
	private String location;
	private String service;
	private int pincode;
	private int isActive;
	private int contact_id;
	private String name;
	private String department;
	private String email;
	private String phone;

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public VendorDetails(int vendor_id, String vendor_name, String address,
			String location, String service, int pincode, int isActive,
			int contact_id, String name, String department, String email,
			String phone) {
		super();
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.address = address;
		this.location = location;
		this.service = service;
		this.pincode = pincode;
		this.isActive = isActive;
		this.contact_id = contact_id;
		this.name = name;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}

	public VendorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VendorDetails [vendor_id=" + vendor_id + ", vendor_name="
				+ vendor_name + ", address=" + address + ", location="
				+ location + ", service=" + service + ", pincode=" + pincode
				+ ", isActive=" + isActive + ", contact_id=" + contact_id
				+ ", name=" + name + ", department=" + department + ", email="
				+ email + ", phone=" + phone + "]";
	}

}

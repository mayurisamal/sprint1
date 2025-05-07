package com.event1.ServiceImpl;

import java.util.List;

import com.event1.Service.VendorService;
import com.event1.dao.VendorDao;
import com.event1.daoImpl.VendorDaoImpl;
import com.event1.entity.Vendor;

public class VendorServiceImpl implements VendorService {

	// Create an instance of VendorDao to interact with the database
	VendorDao vendorDao = new VendorDaoImpl();

	// Method to create a new vendor
	@Override
	public void createVendor(Vendor vendor) {
		vendorDao.createVendor(vendor);  // Call the DAO to save the vendor
	}

	// Method to retrieve a vendor by their ID
	@Override
	public Vendor getVendor(String vendorId) {
		return vendorDao.getVendor(vendorId);  // Call the DAO to get the vendor
	}

	// Method to retrieve all vendors
	@Override
	public List<Vendor> getAllVendors() {
		return vendorDao.getAllVendors();  // Call the DAO to get all vendors
	}

	// Method to update an existing vendor's information
	@Override
	public void updateVendor(Vendor vendor) {
		vendorDao.updateVendor(vendor);  // Call the DAO to update the vendor
	}

	// Method to delete a vendor by their ID
	@Override
	public void deleteVendor(String vendorId) {
		vendorDao.deleteVendor(vendorId);  // Call the DAO to delete the vendor
	}

	// Method to retrieve vendors based on their fee range
	@Override
	public List<Vendor> getVendorsByFeeRange(double minFee, double maxFee) {
		return vendorDao.getVendorsByFeeRange(minFee, maxFee);  // Call the DAO to get vendors by fee range
	}
}

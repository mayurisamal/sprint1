package com.event1.dao;

import com.event1.entity.Vendor;
import java.util.List;

public interface VendorDao {

	// Method to save a new vendor to the database
	void createVendor(Vendor vendor);

	// Method to get one vendor by their ID
	Vendor getVendor(String vendorId);

	// Method to get all vendors from the database
	List<Vendor> getAllVendors();

	// Method to update an existing vendor's details
	void updateVendor(Vendor vendor);

	// Method to delete a vendor using their ID
	void deleteVendor(String vendorId);

	// Get list of vendors within a specified fee range
	List<Vendor> getVendorsByFeeRange(double minFee, double maxFee);
}

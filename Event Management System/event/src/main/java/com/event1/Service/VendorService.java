package com.event1.Service;

import com.event1.entity.Vendor;
import java.util.List;

public interface VendorService {

	// Create a new vendor
	void createVendor(Vendor vendor);

	// Retrieve a vendor by their ID
	Vendor getVendor(String vendorId);

	// Retrieve a list of all vendors
	List<Vendor> getAllVendors();

	// Update an existing vendor's information
	void updateVendor(Vendor vendor);

	// Delete a vendor by their ID
	void deleteVendor(String vendorId);

	// Retrieve a list of vendors within a specific fee range
	List<Vendor> getVendorsByFeeRange(double minFee, double maxFee);
}

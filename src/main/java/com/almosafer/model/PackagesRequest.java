package com.almosafer.model;

public class PackagesRequest {

	private String sId;
	private InnerPackageRequest packagesRequest;

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public InnerPackageRequest getPackagesRequest() {
		return packagesRequest;
	}

	public void setPackagesRequest(InnerPackageRequest packagesRequest) {
		this.packagesRequest = packagesRequest;
	}
}

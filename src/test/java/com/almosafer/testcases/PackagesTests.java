package com.almosafer.testcases;

import org.junit.jupiter.api.Test;

import com.almosafer.activators.AsyncSearchApiActivator;
import com.almosafer.activators.PackagesApiActivator;
import com.almosafer.model.SearchRequest;
import com.almosafer.model.PackagesRequest;
import io.restassured.response.Response;

public class PackagesTests extends AsyncSearchApiActivator {

	private AsyncSearchApiActivator asyncSearchApiActivator = new AsyncSearchApiActivator();
	private PackagesApiActivator packagesApiActivator = new PackagesApiActivator();

	@Test
	public void verifyPackagesFlow() {
		// Step 1: Get sId from search
		SearchRequest searchBody = asyncSearchApiActivator.buildSearchRequest("2026-10-25", "2026-10-26", "Dubai");
		String sId = asyncSearchApiActivator.sendPostRequest(searchBody).getBody().jsonPath().getString("sId");

		// Step 2: Call Packages API
		PackagesRequest pkgReq = packagesApiActivator.buildPackagesRequest(sId, 145333);
		Response packagesResponse = packagesApiActivator.sendPackagesPostRequest(pkgReq);

		String pkgSId = packagesResponse.jsonPath().getString("pId");

		// Step 3: Poll Packages
		Response pollResponse = packagesApiActivator.sendPackagesPollRequest(pkgSId);

		// Step 4: Validate Poll Response
		assertThatStatusCodeEquals(pollResponse, 200);
	}
}
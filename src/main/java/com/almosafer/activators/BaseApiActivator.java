package com.almosafer.activators;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;



public class BaseApiActivator {

	protected static final Logger log = LogManager.getLogger(BaseApiActivator.class.getName());
	Map<String, String> headerParameters;
	Map<String, String> queryParameters;
	Map<String, String> pathParameters;
	private String baseUrl;
	private String requestBody;

	public BaseApiActivator() {
		RestAssured.useRelaxedHTTPSValidation();
		this.baseUrl = getDefaultBaseUrl();
	}

	public BaseApiActivator(String baseUrl) {
		RestAssured.useRelaxedHTTPSValidation();
		this.baseUrl = baseUrl;
	}

	@BeforeEach
	public void resetParameters() {
		headerParameters = null;
		queryParameters = null;
		pathParameters = null;
		requestBody = null;
	}

	public void setHeaders(Map<String, String> headerMap) {
		this.headerParameters = headerMap;
	}

	public void setQueryParameters(Map<String, String> queryParameterMap) {
		this.queryParameters = queryParameterMap;
	}

	public void setPathParameters(Map<String, String> pathParameterMap) {
		this.pathParameters = pathParameterMap;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Response sendAPIRequest(Method httpMethod, String resourceURL) {

		RequestSpecification requestSpecs = RestAssured.given().baseUri(baseUrl);

		if (queryParameters != null)
			requestSpecs.queryParams(queryParameters);
		if (headerParameters != null)
			requestSpecs.headers(headerParameters);
		if (pathParameters != null)
			requestSpecs.pathParams(pathParameters);
		if (requestBody != null)
			requestSpecs.body(requestBody).contentType(ContentType.JSON);

		//Added token directly in API request instead of passing in each request
		Response response = requestSpecs.headers("TOKEN",getDefaultAuthTokenViaEnvFile()).when().log().all().request(httpMethod, resourceURL);
		logResponse(response);
		return response;
	}

	private void logResponse(Response response) {
		log.info("RESPONSE STATUS: " + response.getStatusCode());
		log.info("RESPONSE BODY: " + response.getBody().asString());
	}

	private String getDefaultBaseUrl() {

		Properties props = new Properties();
		try (InputStream input = BaseApiActivator.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input != null) {
				props.load(input);
				return props.getProperty("base_url");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	 private String getDefaultAuthToken() {
	        Properties props = new Properties();
	        try (InputStream input = BaseApiActivator.class.getClassLoader().getResourceAsStream("config.properties")) {
	            if (input != null) {
	                props.load(input);
	                return props.getProperty("token");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }

	 private static final Dotenv dotenv = Dotenv.configure()
	            .ignoreIfMissing() // don't crash if .env isn't present (e.g. in CI, which uses real env vars instead)
	            .load();

	    protected String getDefaultAuthTokenViaEnvFile() {
	        // Prefer a real OS/CI environment variable if present, fall back to .env for local dev
	        String token = System.getenv("ALMOSAFER_API_TOKEN");
	        if (token != null && !token.isBlank()) {
	            return token;
	        }
	        return dotenv.get("ALMOSAFER_API_TOKEN");
	    }
	    
	public void assertThatStatusCodeEquals(Response response, int expectedStatusCode) {
		Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),
				"The Status code of the Response is different than expected");
	}

	public void assertThatStatusTrueCondition(Boolean Condition, String message) {
		Assertions.assertTrue(Condition, "The condition not matched");
	}

	public String convertPojoToString(Object requestPojo) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			String prettyJson = mapper.writeValueAsString(requestPojo);
			log.info("Request body is:\n" + prettyJson);

			return mapper.writeValueAsString(requestPojo);

		} catch (IOException e) {
			throw new RuntimeException("Failed to serialize POJO to JSON", e);
		}
	}

	public Object convertStringToPojo(String jsonString, Class<?> pojoClass) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonString, pojoClass);
		} catch (IOException e) {
			throw new RuntimeException("Failed to deserialize JSON to POJO", e);
		}
	}

}

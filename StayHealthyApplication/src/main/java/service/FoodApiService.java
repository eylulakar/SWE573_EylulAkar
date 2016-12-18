package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class FoodApiService {

	public static String SearchFoodByKeyword(String Keyword) {

		String responseText = "";

		try {
			String urlString = "http://api.nal.usda.gov/ndb/search/?api_key=d4VfIQsp6h5AjtssYihrNEvS3OaKFq2pC0ePLE6S&format=json&sort=n&max=50&offset=0";
			urlString = urlString + "&q=" + Keyword;

			//@eylul: bug here, space character problem.
			// urlString = urlString.replace("+", "%20");

			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line = "";
			while ((line = br.readLine()) != null) {
				responseText = responseText + line;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return responseText;
	}

	public static String GetFoodDetailsTextByNdbno(String ndbno) {

		String responseText = "";

		try {
			String urlString = "http://api.nal.usda.gov/ndb/reports/?api_key=d4VfIQsp6h5AjtssYihrNEvS3OaKFq2pC0ePLE6S&type=b&format=json";
			urlString = urlString + "&ndbno=" + ndbno;

			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line = "";
			while ((line = br.readLine()) != null) {
				responseText = responseText + line;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return responseText;
	}

}

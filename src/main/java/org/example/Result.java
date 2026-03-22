package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Result {
    public static int pulseRate(String diagnosisName, int doctorId) {

        try {
            long pulseSum;
            int count;
            try (HttpClient client = HttpClient.newHttpClient()) {
                int page = 1;
                int totalPages = 1;

                pulseSum = 0;
                count = 0;
                while (page <= totalPages) {

                    String urlStr = "https://jsonmock.hackerrank.com/api/medical_records?page=" + page;


                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlStr)).GET().build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    JSONObject json = new JSONObject(response.body());

                    totalPages = json.getInt("total_pages");

                    JSONArray data = json.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject record = data.getJSONObject(i);
                        JSONObject diagnosis = record.getJSONObject("diagnosis");
                        JSONObject doctor = record.getJSONObject("doctor");

                        if (diagnosis.getString("name").equals(diagnosisName) && doctor.getInt("id") == doctorId) {
                            JSONObject vitals = record.getJSONObject("vitals");

                            pulseSum += vitals.getInt("pulse");
                            count++;
                        }
                    }
                    page++;
                }
            }
            return count ==0 ? 0 : (int)(pulseSum / count);
        } catch (Exception ex) {
            return 0;
        }
    }
}

package de.szut.lf8_project.employee;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EmployeeService {

    public String makeGetRequest(String serverUrl) {
        try {

            String bearerToken = getAccessToken();

            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                return "404";
            }

        } catch (Exception e) {
         return e.toString();
        }
    }
    private String getAccessToken() {
        try {
            String url = "https://keycloak.szut.dev/auth/realms/szut/protocol/openid-connect/token";
            String clientId = "employee-management-service";
            String username = "user";
            String password = "test";

            String requestBody = "grant_type=password&client_id=" + clientId + "&username=" + username + "&password=" + password;

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setDoOutput(true);

            try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                out.write(requestBody.getBytes(StandardCharsets.UTF_8));
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                connection.disconnect();
                JSONObject jsonObject = new JSONObject(response.toString());

                String accessToken = jsonObject.getString("access_token");
                return accessToken;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }

    public static void main(String[] args) {
        EmployeeService em= new EmployeeService();
       System.out.println(em.getAccessToken());
    }
}

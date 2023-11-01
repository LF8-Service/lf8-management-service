package de.szut.lf8_project.employee;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmployeeService {

    public String makeGetRequest(String URL) {
        try {
            // Укажите URL-адрес сервера, к которому вы хотите отправить запрос.
            String serverUrl = URL;

            // Укажите свой Bearer Token.
            String bearerToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzUFQ0dldiNno5MnlQWk1EWnBqT1U0RjFVN0lwNi1ELUlqQWVGczJPbGU0In0.eyJleHAiOjE2OTcxMDE4OTUsImlhdCI6MTY5NzA5ODI5NSwianRpIjoiM2RkNWVmN2QtNjg4OS00YjJiLTg0MWQtNGUxNjFiZTg5MDc3IiwiaXNzIjoiaHR0cHM6Ly9rZXljbG9hay5zenV0LmRldi9hdXRoL3JlYWxtcy9zenV0IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjU1NDZjZDIxLTk4NTQtNDMyZi1hNDY3LTRkZTNlZWRmNTg4OSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImVtcGxveWVlLW1hbmFnZW1lbnQtc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiIyNjI4MTQ1OS01ODk1LTQyMTAtYWZhNy01ZWYzZmU4ZDEwMTgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1zenV0IiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIifQ.A8KnKJ3FxVgosnFg6pjWXYxEGJnpiY2ivg9ycSeGiHZeezTpBRicqgWtAsQegcjCVAQwWksy-a_gtLvr0Gfc3f40QQ9r-MO1gX2c4KyfvHZuDzhMKG6M_jcBvAeYz1qilfctoYH3ouLUYVnQpvZgUkqnNI9usMXex_b43cmVGaXirBgNt9DSqwsgR_Tna_ksVeT1Vpxj2w103reuwFScPS_Z9lPWmLl7o6xolgbnt6I7Bi3aWEH84czGFYuVGHhpEr56FL_LPzCueJQnUK8YQzamDhqM22XpfvhU2jgiS9EE37OEp_iSwxRzQs4lEHozDuv6tvAEl1NRvIfIIpFCyw";

            // Создайте объект URL и откройте соединение.
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Укажите метод запроса (GET, POST, PUT, DELETE и т. д.).
            connection.setRequestMethod("GET");

            // Добавьте заголовок "Authorization" с Bearer Token.
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

            // Получите ответ от сервера.
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
                // Выведите ответ от сервера.
                return response.toString();
            } else {
                return "404";
            }

        } catch (Exception e) {
         return e.toString();
        }
    }
}

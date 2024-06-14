import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecurityCheck {
    public static String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Sending GET request to URL: " + url);
        System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("Response from server:");
        System.out.println(response.toString()); // Print full response

        return response.toString();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                String url = args[0];
                String response = sendGet(url);
                System.out.println("Response from server:");
                System.out.println(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No URL provided.");
        }
    }
}

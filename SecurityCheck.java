import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Base64;

public class SecurityCheck {

    private static final String HOST = "rentry.co";
    private static final String PATH = "/igddddd";

    public static void main(String[] args) {
        try {
            String response = sendGet("https://" + HOST + PATH);
            String key = generateSubscriptionKey();

            if (response.contains(key)) {
                System.out.println("Congratulations, your key has been approved!");
                // Implement Instagram logic or any further actions here
            } else {
                System.out.println("Your Subscription Key: " + key);
                System.out.println("Sending the subscription key to admin for approval...");
                // You can implement further actions like opening a link or sending a message
                // For example: openLink("https://wa.me/+2348103550060?text=" + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String generateSubscriptionKey() throws Exception {
        String uniqueData = System.getProperty("user.name");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(uniqueData.getBytes("UTF-8"));
        return "KEY" + Base64.getEncoder().encodeToString(hash);
    }
}

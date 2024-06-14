import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Base64;

public class SecurityCheck {

    public static void main(String[] args) {
        if (args.length > 0) {
            String url = args[0]; // URL provided as command-line argument
            try {
                String response = sendGet(url);
                String key = generateSubscriptionKey().trim(); // Trim whitespace from generated key

                System.out.println("Generated Key: " + key); // Debug output
                System.out.println("Response Content: " + response); // Debug output

                if (response.contains(key)) {
                    System.out.println("APPROVED");
                } else {
                    System.out.println("DENIED");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No URL provided.");
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;

public class Main {
    
    private static final String HOST = "rentry.co";
    private static final String PATH = "/igddddd";
    
    public static void main(String[] args) {
        try {
            String model = System.getProperty("ro.product.model");
            String httpChat = getRemoteData();
            String id = generateSubscriptionKey();
            
            if (httpChat.contains(id)) {
                System.out.println("\t\t Wait..... ");
                System.out.println("\033[1;96m congratulations your key has been approved! !");
                // Handle further logic after key approval
            } else {
                System.out.println(" Your Subscription Key : " + id);
                System.out.println(" Your device name : " + model);
                // Handle sending subscription key to admin for approval
                sendKeyToAdmin(id);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getRemoteData() throws Exception {
        URL url = new URL("https://" + HOST + PATH);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        
        reader.close();
        conn.disconnect();
        
        return response.toString();
    }
    
    private static String generateSubscriptionKey() throws NoSuchAlgorithmException {
        String uniqueData = System.getProperty("user.name") + System.getProperty("user.home");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(uniqueData.getBytes());
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return "KEY" + hexString.toString();
    }
    
    private static void sendKeyToAdmin(String id) {
        try {
            String url = "https://wa.me/+2348103550060?text=" + id;
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

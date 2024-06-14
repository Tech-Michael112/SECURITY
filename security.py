import subprocess
import hashlib
import base64

def generate_subscription_key():
    try:
        # Get unique data (for example, current username)
        unique_data = subprocess.check_output(['whoami']).strip().decode('utf-8')

        # Hash unique data using SHA-256
        hash_object = hashlib.sha256(unique_data.encode('utf-8'))
        hash_digest = hash_object.digest()

        # Encode hashed data using Base64
        subscription_key = "KEY" + base64.b64encode(hash_digest).decode('utf-8')
        
        return subscription_key

    except Exception as e:
        print(f"Error generating subscription key: {e}")
        return None

def run_java_security_check(url):
    try:
        # Compile Java code if necessary
        subprocess.run(['javac', 'SecurityCheck.java'])

        # Generate subscription key
        subscription_key = generate_subscription_key()
        if subscription_key is None:
            print("Error generating subscription key.")
            return

        # Run Java program with URL and subscription key arguments
        java_process = subprocess.Popen(['java', 'SecurityCheck', url, subscription_key], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        stdout, stderr = java_process.communicate()

        # Check Java program output
        if "APPROVED" in stdout:
            print("Security check approved!")
            # Your Python logic after security check approval
        else:
            print("Security check denied!")
            # Display the subscription key to the user
            print(f"Your Subscription Key: {subscription_key}")

        # Print Java program's stdout and stderr for debugging (optional)
        print("Java stdout:", stdout)
        print("Java stderr:", stderr)

    except Exception as e:
        print(f"Error running Java program: {e}")

if __name__ == "__main__":
    # Example URL
    url = "https://rentry.co/igddddd"
    run_java_security_check(url)

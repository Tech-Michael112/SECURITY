import subprocess

def run_java(url):
    try:
        # Compile Java program (if not already compiled)
        # subprocess.run(["javac", "SecurityCheck.java"])

        # Execute Java program with URL argument
        result = subprocess.run(["java", "SecurityCheck", url], capture_output=True, text=True)
        print(result.stdout.strip())
    except Exception as e:
        print(f"Error running Java program: {e}")

# Example usage
if __name__ == "__main__":
    url = "https://rentry.co/igddddd"
    run_java(url)

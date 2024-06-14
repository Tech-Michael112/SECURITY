import subprocess

def run_java():
    try:
        # Compile Java program (if not already compiled)
        # subprocess.run(["javac", "SecurityCheck.java"])

        # Execute Java program
        result = subprocess.run(["java", "SecurityCheck"], capture_output=True, text=True)
        print(result.stdout.strip())
    except Exception as e:
        print(f"Error running Java program: {e}")

# Example usage
if __name__ == "__main__":
    run_java()

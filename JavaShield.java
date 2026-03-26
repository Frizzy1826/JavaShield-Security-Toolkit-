import java.io.*;
import java.util.*;

// ---------------- PASSWORD CHECKER ----------------
class PasswordChecker {
    public static void check(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[^a-zA-Z0-9].*")) score++;

        System.out.println("\nPassword Analysis:");

        if (score <= 2) {
            System.out.println("Strength: WEAK");
        } else if (score <= 4) {
            System.out.println("Strength: MEDIUM");
        } else {
            System.out.println("Strength: STRONG");
        }

        // Suggestions
        if (password.length() < 8) System.out.println("- Use at least 8 characters");
        if (!password.matches(".*[A-Z].*")) System.out.println("- Add uppercase letters");
        if (!password.matches(".*[0-9].*")) System.out.println("- Add numbers");
        if (!password.matches(".*[^a-zA-Z0-9].*")) System.out.println("- Add special characters");
    }
}

// ---------------- URL ANALYZER ----------------
class URLAnalyzer {
    public static void analyze(String url) {
        int risk = 0;

        if (!url.startsWith("https")) risk++;
        if (url.contains("@")) risk++;
        if (url.contains("-")) risk++;
        if (url.length() > 30) risk++;
        if (url.toLowerCase().contains("login") ||
            url.toLowerCase().contains("verify") ||
            url.toLowerCase().contains("free")) risk++;

        System.out.println("\nURL Analysis:");

        if (risk <= 1) {
            System.out.println("Status: SAFE");
        } else if (risk <= 3) {
            System.out.println("Status: SUSPICIOUS");
        } else {
            System.out.println("Status: HIGH RISK");
        }
    }
}

// ---------------- LOG MONITOR ----------------
class LogMonitor {
    public static void monitorFromFile(String fileName) {
        Map<String, Integer> userFailCount = new HashMap<>();
        int totalFails = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.toUpperCase().contains("LOGIN_FAIL")) {
                    totalFails++;

                    // Extract user (basic parsing)
                    String user = "UNKNOWN";
                    if (line.contains("User:")) {
                        int start = line.indexOf("User:") + 5;
                        int end = line.indexOf("|", start);
                        if (end != -1) {
                            user = line.substring(start, end).trim();
                        }
                    }

                    userFailCount.put(user, userFailCount.getOrDefault(user, 0) + 1);
                }
            }

            br.close();

            System.out.println("\nLog File Analysis:");

            // Check per-user attack
            boolean attackDetected = false;
            for (String user : userFailCount.keySet()) {
                if (userFailCount.get(user) >= 3) {
                    System.out.println("⚠ Brute Force Attack Detected on user: " + user);
                    attackDetected = true;
                }
            }

            if (!attackDetected) {
                if (totalFails >= 3) {
                    System.out.println("⚠ Suspicious Activity Detected!");
                } else {
                    System.out.println("System Normal");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found! Check filename.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}

// ---------------- MAIN APP ----------------
public class JavaShield {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n==== JavaShield Security Toolkit ====");
                System.out.println("1. Password Strength Checker");
                System.out.println("2. Suspicious URL Detector");
                System.out.println("3. Access Log Monitor (File-Based)");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        PasswordChecker.check(password);
                        break;

                    case 2:
                        System.out.print("Enter URL: ");
                        String url = sc.nextLine();
                        URLAnalyzer.analyze(url);
                        break;

                    case 3:
                        System.out.print("Enter log file name (e.g., logs.txt): ");
                        String fileName = sc.nextLine();
                        LogMonitor.monitorFromFile(fileName);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

                Thread.sleep(800);

            } catch (NumberFormatException e) {
                System.out.println("Enter valid number!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
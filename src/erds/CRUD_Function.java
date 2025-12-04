import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Function {
    // in-memory storage is static so File_Handling.loadReports() can populate it
    public static ArrayList<Report> reports = new ArrayList<>();

    private final Scanner sc = new Scanner(System.in);

    // Evacuation center defaults preserved from your Swing code
    private static final String[] CENTER_NAMES = {"BATS. COLISEUM", "BATSU ALANGILAN", "BAUAN COMPLEX"};
    private static final int[] CENTER_CAPACITY = {100, 80, 60};
    private static final String PH_EMERGENCY_HOTLINES =
            "PH Emergency Hotlines:\n" +
            "- 911: Police / Fire / Medical\n" +
            "- 117: Philippine National Police\n" +
            "- 1555: Red Cross (Disaster & Emergency)\n" +
            "- 1669: Ambulance / Medical Emergencies\n" +
            "- 161: Philippine Coast Guard\n";

    // --- Menus ---
    public void showUserMenu() {
        while (true) {
            clearScreen();
            System.out.println("===== USER MENU =====");
            System.out.println("1) Add Report");
            System.out.println("2) Edit Report");
            System.out.println("3) Delete Report");
            System.out.println("4) Show Reports (brief)");
            System.out.println("5) Search Report");
            System.out.println("6) About System");
            System.out.println("7) Back to Main");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": addReportConsole();
                case "2" : editReportConsole();
                case "3": deleteReportConsole();
                case "4" : {
                    showReportsBriefConsole();
                    pause();
                }
                case "5" : searchReportConsole();
                case "6" : {
                    System.out.println("ERDS v1.0\nData saved to reports.dat");
                    pause();
                }
                case "7" : { return; }
                default : {
                    System.out.println("Invalid option.");
                    pause();
                }
            }
        }
    }

    public void showAuthorizedMenu() {
        while (true) {
            clearScreen();
            System.out.println("===== AUTHORIZED MENU =====");
            System.out.println("1) Show Report (single)");
            System.out.println("2) Edit Report");
            System.out.println("3) Delete Report");
            System.out.println("4) Search Report");
            System.out.println("5) Show all reports (FULL)");
            System.out.println("6) Save file");
            System.out.println("7) Back to Main");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" : showReportSingleConsole();
                case "2" : editReportConsole();
                case "3" : deleteReportConsole();
                case "4" : searchReportConsole();
                case "5" : {
                    showAllReportsAuthorizedConsole();
                    pause();
                }
                case "6" : {
                    File_Handling.saveReports();
                    System.out.println("[INFO] Saved successfully!");
                    pause();
                }
                case "7" : { return; }
                default : {
                    System.out.println("Invalid option.");
                    pause();
                }
            }
        }
    }

    // --- CRUD Console implementations (keeps original logic) ---
    private void addReportConsole() {
        clearScreen();
        System.out.println("=== ADD REPORT ===");

        String id = File_Handling.getNextReportID(reports); // REPORT-001 style

        System.out.print("Disaster Event: ");
        String event = sc.nextLine();

        String date = LocalDate.now().toString();
        System.out.println("Date (Auto-filled today): " + date);

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("Severity (HIGH/LOW): ");
        String severity = sc.nextLine().toUpperCase();

        int survivors = readInt("Survivors: ");
        int injured = readInt("Injured: ");
        int missing = readInt("Missing: ");
        int deceased = readInt("Deceased: ");
        int totallyDam = readInt("Totally Damaged Houses: ");
        int partDam = readInt("Partially Damaged Houses: ");

        // Keep same centerOccupied values as original Swing code
        int[] centerOccupied = {900, 1700, 700};
        int[] centerAvailable = new int[CENTER_CAPACITY.length];
        String[] centerStatus = new String[CENTER_CAPACITY.length];
        String suggestedCenter = "NONE";
        int suggestedAvailable = 0;

        for (int i = 0; i < CENTER_CAPACITY.length; i++) {
            centerAvailable[i] = CENTER_CAPACITY[i] - centerOccupied[i];
            centerStatus[i] = (centerAvailable[i] == 0) ? "FULL" : "FREE";
            if (survivors <= centerAvailable[i] && suggestedCenter.equals("NONE")) {
                suggestedCenter = CENTER_NAMES[i];
                suggestedAvailable = centerAvailable[i];
            }
        }

        // Note: keeping your original cost formula from the Swing code
        Report r = new Report(
                id,
                event,
                date,
                location,
                severity,
                survivors,
                injured,
                missing,
                deceased,
                totallyDam,
                partDam,
                centerOccupied,
                centerAvailable,
                centerStatus,
                suggestedCenter,
                suggestedAvailable
        );

        reports.add(r);
        File_Handling.saveReports();

        // Show message exactly like your Swing message (console equivalent)
        System.out.println("\nReport added successfully!");
        System.out.println("Suggested Center: " + suggestedCenter);
        System.out.println("Estimated Damage Cost: ₱" + r.getEstimatedCost());
        System.out.println();
        System.out.println(PH_EMERGENCY_HOTLINES);
        pause();
    }

    private void showReportsBriefConsole() {
        if (reports.isEmpty()) {
            System.out.println("No data available.");
            return;
        }
        System.out.printf("%-12s %-20s %-12s %-12s\n", "ID", "EVENT", "DATE", "LOCATION");
        for (Report r : reports) {
            System.out.printf("%-12s %-20s %-12s %-12s\n",
                    r.getId(), r.getEvent(), r.getDate(), r.getLocation());
        }
    }

    private void showAllReportsAuthorizedConsole() {
        if (reports.isEmpty()) {
            System.out.println("No data available.");
            return;
        }
        for (Report r : reports) {
            System.out.println(r.fullReportConsole());
            System.out.println("----------------------");
        }
    }

    private void showReportSingleConsole() {
        System.out.print("Enter Report ID (e.g. REPORT-001): ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return;
        Report r = findReportById(input);
        if (r == null) System.out.println("Report not found!");
        else System.out.println(r.fullReportConsole());
        pause();
    }

    private void editReportConsole() {
        System.out.print("Enter Report ID to edit (e.g. REPORT-001): ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return;
        Report r = findReportById(input);
        if (r == null) {
            System.out.println("Report not found!");
            pause();
            return;
        }

        System.out.println("Leave blank to keep current value.");

        System.out.print("Disaster Event (" + r.getEvent() + "): ");
        String event = sc.nextLine();
        if (!event.isEmpty()) r.setEvent(event);

        System.out.print("Location (" + r.getLocation() + "): ");
        String location = sc.nextLine();
        if (!location.isEmpty()) r.setLocation(location);

        System.out.print("Severity (" + r.getSeverity() + "): ");
        String sev = sc.nextLine();
        if (!sev.isEmpty()) r.setSeverity(sev.toUpperCase());

        String survivorsStr = readLineAllowEmpty("Survivors (" + r.getSurvivors() + "): ");
        if (!survivorsStr.isEmpty()) r.setSurvivors(Integer.parseInt(survivorsStr));

        String injuredStr = readLineAllowEmpty("Injured (" + r.getInjured() + "): ");
        if (!injuredStr.isEmpty()) r.setInjured(Integer.parseInt(injuredStr));

        String missingStr = readLineAllowEmpty("Missing (" + r.getMissing() + "): ");
        if (!missingStr.isEmpty()) r.setMissing(Integer.parseInt(missingStr));

        String deceasedStr = readLineAllowEmpty("Deceased (" + r.getDeceased() + "): ");
        if (!deceasedStr.isEmpty()) r.setDeceased(Integer.parseInt(deceasedStr));

        String totStr = readLineAllowEmpty("Totally Damaged (" + r.getTotallyDamaged() + "): ");
        if (!totStr.isEmpty()) r.setTotallyDamaged(Integer.parseInt(totStr));

        String partStr = readLineAllowEmpty("Partially Damaged (" + r.getPartiallyDamaged() + "): ");
        if (!partStr.isEmpty()) r.setPartiallyDamaged(Integer.parseInt(partStr));

        r.computeSuggestionConsole(); // recompute center suggestion & estimatedCost
        File_Handling.saveReports();
        System.out.println("Report updated!");
        pause();
    }

    private void deleteReportConsole() {
        System.out.print("Enter Report ID to delete (e.g. REPORT-001): ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return;
        Report r = findReportById(input);
        if (r == null) {
            System.out.println("Report not found!");
            pause();
            return;
        }
        System.out.print("Are you sure you want to delete report " + input + " ? (y/n): ");
        String y = sc.nextLine().trim();
        if (y.equalsIgnoreCase("y")) {
            reports.remove(r);
            File_Handling.saveReports();
            System.out.println("Report deleted!");
        } else {
            System.out.println("Delete cancelled.");
        }
        pause();
    }

    private void searchReportConsole() {
        System.out.println("Search by:");
        System.out.println("1) Disaster Name");
        System.out.println("2) Location");
        System.out.println("3) ID");
        System.out.print("Choose: ");
        String choice = sc.nextLine().trim();

        boolean found = false;
        if ("1".equals(choice)) {
            System.out.print("Enter disaster name: ");
            String key = sc.nextLine().trim();
            for (Report r : reports) {
                if (r.getEvent().equalsIgnoreCase(key)) {
                    System.out.println(r.fullReportConsole());
                    System.out.println("-----------------");
                    found = true;
                }
            }
        } else if ("2".equals(choice)) {
            System.out.print("Enter location: ");
            String key = sc.nextLine().trim();
            for (Report r : reports) {
                if (r.getLocation().equalsIgnoreCase(key)) {
                    System.out.println(r.fullReportConsole());
                    System.out.println("-----------------");
                    found = true;
                }
            }
        } else if ("3".equals(choice)) {
            System.out.print("Enter ID (e.g. REPORT-001): ");
            String key = sc.nextLine().trim();
            Report r = findReportById(key);
            if (r != null) {
                System.out.println(r.fullReportConsole());
                found = true;
            }
        }

        if (!found) System.out.println("No report found.");
        pause();
    }

    // --- helpers ---
    private Report findReportById(String id) {
        for (Report r : reports) if (r.getId().equals(id)) return r;
        return null;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private String readLineAllowEmpty(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private void pause() {
        System.out.print("\nPress ENTER to continue...");
        sc.nextLine();
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 30; i++) System.out.println();
        }
    }
}

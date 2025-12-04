import java.io.*;
import java.util.ArrayList;

public class File_Handling {
    private static final String DATA_FILE = "reports.dat";

    // Save current CRUD_Function.reports to file
    public static void saveReports() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(CRUD_Function.reports);
        } catch (Exception e) {
            System.err.println("[ERROR] Saving reports failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load reports from file into CRUD_Function.reports
    @SuppressWarnings("unchecked")
    public static void loadReports() {
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            CRUD_Function.reports = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            CRUD_Function.reports = (ArrayList<Report>) ois.readObject();
        } catch (Exception e) {
            System.err.println("[ERROR] Loading reports failed: " + e.getMessage());
            e.printStackTrace();
            CRUD_Function.reports = new ArrayList<>();
        }
    }

    // Generate next REPORT-xxx ID based on existing reports
    public static String getNextReportID(ArrayList<Report> list) {
        int max = 0;
        for (Report r : list) {
            String id = r.getId();
            try {
                if (id != null && id.startsWith("REPORT-")) {
                    String numPart = id.substring(7);
                    int n = Integer.parseInt(numPart);
                    if (n > max) max = n;
                }
            } catch (Exception ignored) {
            }
        }
        int next = max + 1;
        return String.format("REPORT-%03d", next);
    }
}

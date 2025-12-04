import java.io.Serializable;

public class Report implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID now stored as REPORT-xxx string
    private String id;
    private String event, date, location, severity;
    private int survivors, injured, missing, deceased, totallyDamaged, partiallyDamaged;
    private int[] centerOccupied, centerAvailable;
    private String[] centerStatus;
    private String suggestedCenter;
    private int suggestedAvailable;
    private int estimatedCost;

    // Constructor matching original fields (except id is string now)
    public Report(String id, String event, String date, String location, String severity,
                  int survivors, int injured, int missing, int deceased,
                  int totallyDamaged, int partiallyDamaged,
                  int[] centerOccupied, int[] centerAvailable, String[] centerStatus,
                  String suggestedCenter, int suggestedAvailable) {

        this.id = id;
        this.event = event;
        this.date = date;
        this.location = location;
        this.severity = severity;

        this.survivors = survivors;
        this.injured = injured;
        this.missing = missing;
        this.deceased = deceased;
        this.totallyDamaged = totallyDamaged;
        this.partiallyDamaged = partiallyDamaged;

        this.centerOccupied = centerOccupied;
        this.centerAvailable = centerAvailable;
        this.centerStatus = centerStatus;

        this.suggestedCenter = suggestedCenter;
        this.suggestedAvailable = suggestedAvailable;

        // Keep original formula from your Swing code:
        this.estimatedCost = totallyDamaged * 50000 + partiallyDamaged * 20000;
    }

    // getters and setters
    public String getId() { return id; }
    public String getEvent() { return event; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getSeverity() { return severity; }
    public int getSurvivors() { return survivors; }
    public int getInjured() { return injured; }
    public int getMissing() { return missing; }
    public int getDeceased() { return deceased; }
    public int getTotallyDamaged() { return totallyDamaged; }
    public int getPartiallyDamaged() { return partiallyDamaged; }
    public int[] getCenterOccupied() { return centerOccupied; }
    public int[] getCenterAvailable() { return centerAvailable; }
    public String[] getCenterStatus() { return centerStatus; }
    public String getSuggestedCenter() { return suggestedCenter; }
    public int getSuggestedAvailable() { return suggestedAvailable; }
    public int getEstimatedCost() { return estimatedCost; }

    public void setEvent(String event) { this.event = event; }
    public void setLocation(String location) { this.location = location; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setSurvivors(int survivors) { this.survivors = survivors; }
    public void setInjured(int injured) { this.injured = injured; }
    public void setMissing(int missing) { this.missing = missing; }
    public void setDeceased(int deceased) { this.deceased = deceased; }
    public void setTotallyDamaged(int totallyDamaged) { this.totallyDamaged = totallyDamaged; }
    public void setPartiallyDamaged(int partiallyDamaged) { this.partiallyDamaged = partiallyDamaged; }

    // recompute suggestion & costs (used after editing)
    public void computeSuggestionConsole() {
        this.suggestedCenter = "NONE";
        this.suggestedAvailable = 0;
        for (int i = 0; i < centerOccupied.length; i++) {
            int available = CRUD_Function.class == null ? 0 : (CENTER_CAPACITY_SAFE()[i] - centerOccupied[i]);
            // we can't access CENTER_CAPACITY from here (static in Swing). Use helper below.
            centerAvailable[i] = available;
            centerStatus[i] = (available == 0) ? "FULL" : "FREE";
            if (survivors <= available && suggestedCenter.equals("NONE")) {
                suggestedCenter = CENTER_NAMES_SAFE()[i];
                suggestedAvailable = available;
            }
        }
        this.estimatedCost = totallyDamaged * 50000 + partiallyDamaged * 20000;
    }

    // Compose a console-friendly full report (similar to fullReport() in Swing)
    public String fullReportConsole() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Event: ").append(event).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Location: ").append(location).append("\n");
        sb.append("Severity: ").append(severity).append("\n");
        sb.append("Survivors: ").append(survivors).append("\n");
        sb.append("Injured: ").append(injured).append("\n");
        sb.append("Missing: ").append(missing).append("\n");
        sb.append("Deceased: ").append(deceased).append("\n");
        sb.append("Totally Damaged Houses: ").append(totallyDamaged).append("\n");
        sb.append("Partially Damaged Houses: ").append(partiallyDamaged).append("\n");
        sb.append("Estimated Damage Cost: ₱").append(estimatedCost).append("\n");
        sb.append("\nEvacuation Centers:\n");
        sb.append(String.format("%-12s %-10s %-9s %-10s %-7s\n","CENTER","TOTAL","OCCUPIED","AVAILABLE","STATUS"));
        String[] names = CENTER_NAMES_SAFE();
        int[] totals = CENTER_CAPACITY_SAFE();
        for (int i = 0; i < names.length; i++) {
            sb.append(String.format("%-12s %-10d %-9d %-10d %-7s\n",
                    names[i], totals[i], centerOccupied[i], centerAvailable[i], centerStatus[i]));
        }
        sb.append("\nSuggested Center: ").append(suggestedCenter)
                .append(" | Available Slots: ").append(suggestedAvailable).append("\n\n");
        sb.append(File_Handling_PH_HOTLINES());
        return sb.toString();
    }

    // Helper to return PH hotlines consistent with other classes
    private String File_Handling_PH_HOTLINES() {
        return "PH Emergency Hotlines:\n" +
                "- 911: Police / Fire / Medical\n" +
                "- 117: Philippine National Police\n" +
                "- 1555: Red Cross (Disaster & Emergency)\n" +
                "- 1669: Ambulance / Medical Emergencies\n" +
                "- 161: Philippine Coast Guard\n";
    }

    // Because in the original Swing code CENTER_NAMES and CENTER_CAPACITY were static in the outer class,
    // we provide safe local copies here to avoid cross-file static dependency issues.
    private static String[] CENTER_NAMES_SAFE() {
        return new String[]{"BATS. COLISEUM", "BATSU ALANGILAN", "BAUAN COMPLEX"};
    }
    private static int[] CENTER_CAPACITY_SAFE() {
        return new int[]{100, 80, 60};
    }
}

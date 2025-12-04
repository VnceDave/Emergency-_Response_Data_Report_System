𐔌 .⋮ ERDS — Emergency Report Documentation System .ᐟ ֹ ₊ ꒱

Your console-based reporting & incident documentation tool.
CS 2106 – OOP Final Project
Maneja, Vince Dave
(Groupmates if meron)

‧₊˚ ┊ Overview

ERDS is a console-based Java application designed to record, manage, search, and update emergency incident reports directly through the terminal.

The program applies core Object-Oriented Programming (OOP) concepts such as encapsulation, abstraction, polymorphism, and file handling, all while maintaining a clean and modular structure.

With ERDS, users can:

🚨 Add a new incident report
📝 Edit an existing report
❌ Delete a report
📋 View all reports (summary or full details)
🔍 Search specific reports
🔐 Access an Authorized Staff Menu
💾 Auto-save reports into a binary .dat file

‧₊˚ ┊ Report Storage

All reports are saved in:
💾 reports.dat (serialized binary file using ObjectOutputStream)

Each report includes:

Report ID

Full name

Age

Contact number

Address

Date and time

Incident type

Description

Status

‧₊˚ ┊ Project Structure
📂 src/
└── 📂 erds/
    ├── ☕ Main.java
    ├── ☕ Report.java
    ├── ☕ ReportManager.java
    ├── ☕ FileHandler.java
    └── ☕ InputValidator.java

File Descriptions

Main.java – Program entry point, contains menus (User & Authorized) and handles user interaction.
Report.java – Object model for each incident report.
ReportManager.java – Handles CRUD operations (add, edit, delete, search, list).
FileHandler.java – Handles reading & writing reports to reports.dat.
InputValidator.java – Ensures valid numeric and string inputs.

‧₊˚ ┊ How to Run the Program
1. Compile the Java files
javac erds/*.java

2. Run the program
java erds.Main

‧₊˚ ┊ Features
➕ Add Report

Create a complete incident report including victim info, location, and narrative.

✏️ Edit Report

Modify any field of an existing report using its Report ID.

❌ Delete Report

Remove a record permanently.

📜 View All Reports

Display all reports in either:

Brief mode (ID + Name + Type)

Full detail mode

🔍 Search Report

Search by name, ID, or keyword.

🔐 Authorized User Menu

Includes additional tools such as viewing full report details and accessing restricted functions.

💾 Auto Save

Reports automatically save to reports.dat every time an operation is performed.

‧₊˚ ┊ Object-Oriented Principles
💊 Encapsulation

All report data fields are private and managed through getters/setters.
Only the ReportManager can modify the internal list of reports.

💡 Abstraction

File operations are abstracted in the FileHandler class.
The rest of the system does not deal with raw file streams — it only calls higher-level methods like:

saveReports()

loadReports()

🧬 Inheritance

While not heavily used, the system is structured for expansion.
Future classes like MedicalReport or FireIncidentReport may inherit from Report.

🎭 Polymorphism

Switch-case menu interactions demonstrate different behaviors under one interface:
reportManager.handle(choice) could call different internal functions.

‧₊˚ ┊ Example Console Output
--- EMERGENCY REPORT DOC SYSTEM ---
1. Add Report
2. Edit Report
3. Delete Report
4. View Reports (Brief)
5. Search Report
6. Save and Exit
7. Authorized Access
Enter choice: 1

Enter Full Name: Juan Dela Cruz
Enter Age: 24
Enter Contact Number: 09912345678
Enter Incident Type: Earthquake-related Injury
Report successfully added.
Press Enter to continue...

‧₊˚ ┊ reports.dat Structure (Sample)
#1 | Juan Dela Cruz | Earthquake Injury | Resolved
#2 | Maria Santos | Fire Incident – Minor Burns | Pending
#3 | Ramon Dizon | Vehicular Accident | Critical

‧₊˚ ┊ Contributors
Name	Role
Maneja, Vince Dave	Lead Programmer / System Designer
(Add members here)	(Role)
‧₊˚ ┊ Acknowledgment

Special thanks to our instructor for guiding us throughout the development of this project.
We also thank our classmates for their help, suggestions, and support.

DISCLAIMER

This project is created for academic demonstration purposes only.
Students may use it as reference but are encouraged not to copy it fully.

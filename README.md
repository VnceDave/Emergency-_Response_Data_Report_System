<h1 align = "center">𐔌 .⋮ ERDS — Emergency Response Data Report System .ᐟ₊꒱</h1>
<h3 align = "center">console-based emergency incident reporting system.</h3>
<p align = "center">
<b>IT 2112 </b> <br/>
Maneja, Vince Dave C. <br/>
Maneja, Cedrick Paul V. <br/>
Leal, Lealyn B.
</p>

---

## ⊹˚₊‧ Overview.

ERDS is a console-based Java application designed to record, search, edit, and manage emergency incident reports directly through the terminal.

It demonstrates Object-Oriented Programming (OOP) concepts such as **encapsulation, abstraction, polymorphism**, and structured **file handling** using Java serialization.  
All reports are stored in a `.dat` binary file.

Users can:

- ✏️ Add a new report  
- 📋 View reports (brief or full details)  
- ✍🏻 Edit existing report  
- ❌ Delete a report  
- 🔍 Search by ID, event, or location  
- 🔐 Login as *Authorized Personnel* for restricted actions  
- 💾 Auto-save all changes


---

## ⊹˚₊‧ Project Structure
```
    📂 `src/erds`
    ├── ☕ `Main.java`
    ├── ☕ `CRUD_Function.java`
    ├── ☕ `Report.java`
    ├── ☕ `File_Handling.java`
   
```

**Main.java** — Entry point of the program; handles main menu + loading/saving.  
**CRUD_Function.java** — Add / edit / delete / search / list operations.  
**Report.java** — Serializable data model for one report.  
**File_Handling.java** — Manages saving/loading of `reports.dat`.  



---

## ⊹˚₊‧ How to Run the Program

Open your terminal in the src/erds folder and run:



---

## ⊹˚₊‧ Features

- ➕ **Add Report** — Creates a new incident record with auto-generated Report ID  
- ✏️ **Edit Report** — Fully editable fields with recalculated values  
- ❌ **Delete Report** — Remove via Report ID  
- 📄 **Show Reports (Brief)** — ID, Event, Date, Location  
- 📑 **Show Reports (Full)** — With hotlines, evacuation center, and estimated cost  
- 🔍 **Search** — By ID, event, or location  
- 🔐 **Admin Mode** — Protected by Authorized login  
- 💾 **Auto Save** — All data stored in `reports.dat`  


---

## ⊹˚₊‧ Object-Oriented Principles

### 💊 Encapsulation
All fields in `Report.java` are private. Access only through getters/setters.  
Prevents unauthorized field changes.

### 💡 Abstraction
`File_Handling.java` hides all low-level file I/O.  
Other classes simply call:


### 🧬 Inheritance
Project is structured for future subclasses (e.g., `FloodReport`, `FireReport`).  
Base class `Report` can be extended without modifying core logic.

### 🎭 Polymorphism
Menu actions in Main.java trigger different operations dynamically depending on user input.


---

## ⊹˚₊‧ Example Output

```
--- EMERGENCY RESPONSE DATA SYSTEM ---

[1] User
[2] Authorized Person
[3] Exit

Choose: 1

[INFO] Successfully logged in as USER.
Press ENTER to continue...

===== USER MENU =====

1) Add Report
2) Edit Report
3) Delete Report
4) Show Reports (brief)
5) Search Report
6) About System
7) Back to Main

====================

Choose: 1

```


---

## ⊹˚₊‧ reports.dat Snippet (Sample)

```
ID: REPORT-003
Event: BAHA
Date: 2025-12-04
Location: BATANGAS CITY
Severity: HIGH
Survivors: 99
Injured: 12
Missing: 334
Deceased: 5
Totally Damaged Houses: 900
Partially Damaged Houses: 900
Estimated Damage Cost: P63000000

Evacuation Centers:
CENTER       TOTAL      OCCUPIED  AVAILABLE  STATUS
BATS. COLISEUM 5000       0         5000       FREE
BATSU ALANGILAN 3000       0         3000       FREE
BAUAN COMPLEX 6000       0         6000       FREE

Suggested Center: BATS. COLISEUM | Available Slots: 5000

PH Emergency Hotlines:
~ 911: Police / Fire / Medical
~ 117: Philippine National Police
~ 143: Red Cross (Disaster & Emergency)
~ 1669: Ambulance / Medical Emergencies
~ (02)8527-3877: Philippine Coast Guard

```



---

## ⊹˚₊‧ Contributors


<table>
<tr>
    <th> &nbsp; </th>
    <th> Name </th>
    <th> Role </th>
</tr>
<tr>
    <td><img src="static/vince_dave.png" width="100" height="100"> </td>
    <td><strong>Vince Dave C. Maneja</strong> <br/>
    <a href="https://github.com/VnceDave" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=pink" alt="marieemoiselle's GitHub">
        </a>
    </td>
    <td>System Designer</td>
</tr>
<tr>
    <td><img src="static/cedrick_paul.png" width="100" height="100"> </td>
    <td><strong>Cedrick Paul V. Maneja</strong> <br/>
    <a href="https://github.com/sidrikspol" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=blue" alt="jeisquaredd's GitHub">
        </a>
    </td>
    <td>Feature Developer</td>
</tr>
<tr>
    <td><img src="static/lealyn.png" width="100" height="100"> </td>
    <td><strong>Lealyn B. Leal</strong> <br/>
    <a href="https://github.com/Lia-Leal" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=green" alt="digZy030509's GitHub">
        </a>
    </td>
    <td>System Architect</td>
</tr>
</table>


---

## ⊹˚₊‧ Acknowledgment

We express our sincere gratitude to our instructor for the guidance provided throughout the development of this project.  
We also appreciate our classmates and peers for the encouragement and support during the project’s completion.


---

### DISCLAIMER
This project is provided for academic and learning purposes only.  






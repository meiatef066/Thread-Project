# Sudoku Solution Validator

## Overview
This is a multithreaded Java application that validates a given 9x9 Sudoku solution. The program ensures that:
- Each row contains the digits 1 through 9 exactly once.
- Each column contains the digits 1 through 9 exactly once.
- Each of the nine 3x3 subgrids contains the digits 1 through 9 exactly once.

The validation is performed in two modes:
1. **Single-threaded Validation** - Checks all rows, columns, and subgrids sequentially.
2. **Multi-threaded Validation** - Uses Java's ExecutorService to parallelize the validation tasks.

---
## Features
✅ Validates Sudoku solutions efficiently.
✅ Uses Java concurrency features for performance optimization.
✅ Implements object-oriented design principles.
✅ Supports both single-threaded and multi-threaded execution.

---
## Installation & Usage

### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/yourusername/SudokuValidator.git
cd SudokuValidator
```

### **2️⃣ Compile the Java Files**
```sh
javac -d bin src/com/example/*.java
```

### **3️⃣ Run the Application**
```sh
java -cp bin com.example.Main
```

---
## Code Structure
```
📂 SudokuValidator
├── 📂 src/com/example
│   ├── Main.java            # Entry point
│   ├── SudokuManager.java   # Core logic for validation
│   ├── SudokuValidator.java # Interface for validation
│   ├── RowValidator.java    # Checks a single row
│   ├── ColumnValidator.java # Checks a single column
│   ├── SubGridValidator.java# Checks a 3x3 subgrid
├── README.md                # Project documentation
```

---
## Design & Implementation
- **SudokuManager** manages the entire validation process.
- **RowValidator, ColumnValidator, and SubGridValidator** implement the `SudokuValidator` interface.
- **ExecutorService** is used for efficient thread management in the multi-threaded mode.
- **Future<Boolean>** is used to retrieve validation results from worker threads.

---
## Example Output
```
Multi-threaded validation: true
```

---
## Future Improvements 
- Add support for validating Sudoku puzzles dynamically from user input.
- Add GUI Or CLI 
- Implement logging for better debugging.

---
## License
MIT License © 2025 


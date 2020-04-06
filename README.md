# Technical Assessment: Barren Land Analysis

## Description
You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). A portion of the farm is barren, and all the barren land is in the form of rectangles. Due to these rectangles of barren land, the remaining area of fertile land is in no particular shape. An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles of barren land. 

Read input from **STDIN**. Print output to **STDOUT**

**Input**

You are given a set of rectangles that contain the barren land. These rectangles are defined in a string, which consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the coordinates of the top right corner. 

**Output**

Output all the fertile land area in square meters, sorted from smallest area to greatest, separated by a space. 

**Sample Input** | **Sample Output**
-----------------|------------------
{“0 292 399 307”} | 116800  116800
{“48 192 351 207”, “48 392 351 407”, <br>“120 52 135 547”, “260 52 275 547”} | 22816 192608

## Tasks

- [x] Create readme.md
- [x] Create classes that solve the problem description
- [x] Create unit tests
- [x] Create main class that executes the solution.
- [ ] Modify the main class to read the input from STDIN, and print the solution to STDOUT.

## Solution

To solve this problem, I created two classes and unit tests:
* **Rectagle**: Class that represents a rectangular area, used by the program to model the barren areas and also the land.
* **Land**: Class that represents the land, this class extends the rectangle. Its constructor receives the width, height and barren rectangles. It has a public method that returns the output required by the problem description.

The Land constructor receives the land dimensions (width, height), and a list of rectangles that represent the barren areas.<br>
I imagined that each (unique) X,Y coordinate of the land reprents an area unit, and created a private inner class, named LandUnit, to represent that.<br>
During construction, the Land will create a Set with all the LandUnit instances representing the fertile coordinates.<br>
To solve the problem, the class iterates over all fertile units, grouping them in Sets accordingly to if they are connected or not.
The size of each set represents a fertile area.<br>
I've created unit tests to verify that the solution worked as required.

## Running

The solution was implemented as a Gradle/Java 8 project using the Eclipse JEE IDE.

Unit tests were implemented using JUnit 4, and Hamcrest 2.2.

### Using the Eclipse JEE IDE.

If you have Eclipse JEE IDE installed, and the Buildship plug-ing installed, you can import the project using the menu:

File > Import:<br>
Select "General > Existing Projects into Workspace"

Specify the location of the code and follow the wizard to finish the import.

On the gradle tasks view, you can execute the test task.

### On windows:

To run the unit test from the command-line on windows, execute:

```cmd
gradlew --rerun-tasks test
```

### On linux:

This hasn't been tested.<br>
To run the unit test from the command-line on linux, execute:

```bash
gradlew --rerun-tasks test
```

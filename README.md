# Route Planner - Strategy Design Pattern

A console application that demonstrates the Strategy design pattern by planning travel routes using interchangeable transport strategies.

## What Is the Strategy Pattern

The Strategy pattern defines a family of algorithms, puts each one into a separate class, and makes them interchangeable. The client picks which algorithm to use at runtime, and the context object delegates the work to that algorithm without knowing the details. This removes the need for long if/else or switch chains when choosing between different behaviors.

In this project:
- Strategy interface: RouteStrategy
- Concrete Strategies: CarStrategy, WalkStrategy, CycleStrategy, BusStrategy
- Context: Navigator
- Client: Main

## Two Modes

When the program starts, it presents a mode selection menu:
1. Console mode: The interactive command-line flow where the user inputs route points, distance, and compares transport strategies at runtime. Exiting console mode returns to the mode menu.
2. Web mode: Launches the graphical website interface on localhost (http://localhost:8080) in your default web browser. Note: web mode needs Website/index.html in the folder the program was started from. If the file is not found, an informative message is printed and the program returns to the mode menu.
0. Exit: Prints a goodbye message and terminates the program.

## Project Structure

```
Assignment5/
    README.md                 - This file
    UmlDiagram.md             - UML class diagram in Mermaid syntax
    Source/                   - All Java source files
        Main.java             - The Client: shows mode menu, coordinates console mode
        Navigator.java        - The Context: holds strategy, delegates work
        Route.java            - Data class for route results
        RouteStrategy.java    - The Strategy interface
        CarStrategy.java      - Concrete strategy: travel by car
        WalkStrategy.java     - Concrete strategy: travel on foot
        CycleStrategy.java    - Concrete strategy: travel by bicycle
        BusStrategy.java      - Concrete strategy: travel by bus
        ConsoleReader.java    - Helper for reading and validating console input
        PatternExplainer.java - Produces the pattern explanation text
        WebLauncher.java      - Serves and opens the web mode interface on localhost
    Report/                   - Assignment report
        Assignment5_Strategy_Report_Tlektes_Kadyr.docx
```

## How to Run

### In IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure JDK 17 (or higher) is selected in Project Settings.
3. Right-click the Source folder, select Mark Directory as, then Sources Root.
4. Open Main.java and press the green Run button.

### In the Terminal

From the project root directory, run:

```
javac -d Out Source/*.java
java -cp Out Main
```

On Windows, use the same commands in PowerShell or cmd from the project root. The Out folder is just compiled output and can be deleted.

## Example Console Session

```
Route Planner
Choose the mode:
1. Console mode
2. Web mode
0. Exit
Your choice: 1

Welcome to the Route Planner!

Enter starting point: Astana Central Station
Enter destination: Nazarbayev University
Enter distance in kilometers: 12

Choose how you want to travel:
1. Car
2. Walk
3. Cycle
4. Bus
5. Enter a new route
0. Exit
Your choice: 1

Route: Astana Central Station -> Nazarbayev University
Transport: Car
Distance: 12.0 km
Travel time: 14 minutes
Cost: 300.0 KZT

--- Strategy Pattern Explanation ---
You chose the Car strategy.
The Navigator (Context) did not calculate anything by itself. It passed the work to the CarStrategy object.
Every strategy implements the same RouteStrategy interface, so the Navigator treats all of them the same way.
There is no if/else or switch in the Navigator. The choice of algorithm is made once, by the Client, when it calls setStrategy.
You can change the strategy at runtime. Pick another option from the menu for the same route.
To add a new transport, you only write a new class that implements RouteStrategy. The Navigator and the existing strategies do not change.
-----------------------------------

Choose how you want to travel:
1. Car
2. Walk
3. Cycle
4. Bus
5. Enter a new route
0. Exit
Your choice: 0

Route Planner
Choose the mode:
1. Console mode
2. Web mode
0. Exit
Your choice: 0

Goodbye!
```

## How to Add a New Strategy

1. Create a new class (for example, ScooterStrategy.java) in the Source folder. It must implement RouteStrategy with the buildRoute and getName methods.
2. In Main.java, add one line to the strategies list: strategies.add(new ScooterStrategy());
3. Run the program. The new transport appears in the menu automatically.

No other files need to change. The Navigator, the existing strategies, and the menu logic all stay the same.

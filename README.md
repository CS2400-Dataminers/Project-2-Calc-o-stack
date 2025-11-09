# CS2400 Project 2: Calc-o-stack

## Authors
- **Josh Guzman** (GitHub: [guzmanthegreat](https://github.com/guzmanthegreat), Email: joshguzman@cpp.edu)
- **Amr Eldessouky** (GitHub: [AmrEldessouky](https://github.com/AmrEldessouky), Email: aeldessouky@cpp.edu)

## Overview
This project implements stack-based algorithms to:
1. Convert an **infix** expression to **postfix** using a `LinkedStack`.
2. Evaluate a **postfix** expression using a `ResizeableArrayStack`.

The assignment is based on *Data Structures and Abstractions with Java, 5th Edition* (Carrano & Henry).

## Files Included
- `Calculator.java` — Conversion & evaluation logic + demo main
- `LinkedStack.java` — Linked chain stack implementation
- `ResizeableArrayStack.java` — Resizable array stack implementation
- `StackInterface.java` — Stack ADT definition
- `CalculatorTest.java` — Manual and/or JUnit test file
- `Stacks_Assignment_Steps_Final.xlsx` — Step-by-step infix→postfix and postfix evaluation process (Tasks 1 & 4)
- `doc/` — Generated Javadoc documentation

## How to Run
Compile and run from your project directory:
```bash
javac *.java
java Calculator
java CalculatorTest
```

Expected output:
```
Infix:   a*b/(c-a)+d*e
Postfix: a b * c a - / d e * +
Result:  33.0
Test passed
```

## Screencast
A short (<3 min) demo screencast has been recorded showing both authors, code walkthrough, and program demo.

## Contributions
- **Amr Eldessouky** — Implementation of `ResizeableArrayStack`, Excel table formatting, code review.
- **Josh Guzman** — Implementation of `LinkedStack`, `Calculator`, and `CalculatorTest`, repo setup, testing, and screencast recording.

## Notes
- `LinkedStack` uses an inner `Node` class (per assignment guidelines).
- Each stack class fully implements the `StackInterface`.
- The Javadoc folder (`doc/`) is included in the repo.

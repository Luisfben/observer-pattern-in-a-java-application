# capital-gains

The 'capital-gains' project calculates how much tax you should pay based on the profit or losses of a stock market investment. This project was implemented as a command line application (CLI). 

## Project structure

These are the most relevant files and directories of the project:
```
.
├── Main.java                           # main class
├── src/
│   ├── handler/                        # handlers subfolder
│   ├── impl/                           # implementations subfolder
│   ├── model/                          # models subfolder
│   └── service/                        # services subfolder
└── tests/
    └── CapitalGainsHandlerTest.java    # unit test
```
---
# Getting started

## Install requirements

- [Java](https://www.java.com/es/download/) `1.8.0_333` or greater.
- [Gson](https://github.com/google/gson/) `2.3.1`
- [JUnit](https://junit.org/junit5/) `5.8.1`

## Run capital-gains

For now, open a JAVA IDE-compatible and run the src/Main.java class. As technical debt, steps for creating the JAVA Jar file are pending, it allows running from the command line.

You must pass as arguments, the JSON input. This is an example:

```
[{\"operation\":\"buy\",\"unit-cost\":10.0,\"quantity\":100},{\"operation\":\"sell\",\"unit-cost\":15.0,\"quantity\":50},{\"operation\":\"sell\",\"unit-cost\":15.0,\"quantity\":50}]
```

In the case of IntelliJ IDEA IDE, located Run/debugging configurations, you select the application and locate CLI arguments.

Notice: JSON input text must are minifying and escaped,

---

# Additional info

So that each operation line is independent was necessary to implement the observer pattern.

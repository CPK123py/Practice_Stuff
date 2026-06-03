# Core Java Programs

This folder is for learning and practicing core Java concepts.

## Structure

- **src/**: Put your Java source files here
- **bin/**: Compiled .class files go here automatically
- **HelloWorld.java**: Sample program at root level

## How to Compile and Run

### Single File (at root level):
```bash
javac HelloWorld.java
java HelloWorld
```

### Files in src folder:
```bash
# Navigate to core_java directory
cd core_java

# Compile all Java files in src folder to bin folder
javac -d bin src/*.java

# Run a compiled class
java -cp bin ClassName
```

## Tips

- Each Java program should have a `main` method to be executable
- Class name must match the filename
- Use meaningful names for your programs
- Start simple and gradually increase complexity

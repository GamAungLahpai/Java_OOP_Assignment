# Java OOP Assignment



## 📚 Project Overview

This repository contains comprehensive solutions and examples from coursework in the **Bachelor's Degree in Information Technology (Machine Learning track)** at Metropolia University of Applied Sciences. The assignments demonstrate progression from fundamental Java programming concepts to advanced Object-Oriented Programming principles and modern Java frameworks.

## 🎯 Learning Objectives

Through these assignments, you will:
- Master core Java syntax and programming constructs
- Understand and implement OOP design principles
- Work with modern Java frameworks and libraries
- Develop GUI applications using JavaFX
- Practice test-driven development with JUnit
- Implement database connectivity solutions
- Explore functional programming paradigms in Java

## 📋 Topics Covered

### Core Java Concepts
- **Java Fundamentals**: Variables, data types, operators, control flow
- **Methods & Arrays**: Function design, array manipulation, algorithms
- **String Processing**: String operations, regex, text manipulation

### Object-Oriented Programming
- **Classes & Objects**: Constructors, methods, attributes
- **Inheritance**: Class hierarchies, method overriding, super keyword
- **Polymorphism**: Method overloading, dynamic binding, interfaces
- **Encapsulation**: Access modifiers, getters/setters, data hiding
- **Abstraction**: Abstract classes, interfaces, design patterns

### Advanced Topics
- **Collections Framework**: Lists, Sets, Maps, Queues, custom implementations
- **Exception Handling**: Try-catch blocks, custom exceptions, error recovery
- **Multithreading**: Thread creation, synchronization, concurrent collections
- **Lambda Expressions**: Functional interfaces, stream API, method references
- **File I/O**: Reading/writing files, serialization, NIO

### Frameworks & Tools
- **JavaFX**: GUI development, event handling, FXML, CSS styling
- **JDBC**: Database connectivity, CRUD operations, prepared statements
- **Unit Testing**: JUnit 5, TestFX, test-driven development
- **Maven**: Dependency management, build automation

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:
- **Java JDK 8** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** (Optional but recommended) ([Download](https://maven.apache.org/download.cgi))
- **Git** ([Download](https://git-scm.com/downloads))
- **IDE**: IntelliJ IDEA, Eclipse, or NetBeans

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/GamAungLahpai/Java_OOP_Assignment.git
   cd Java_OOP_Assignment
   ```

2. **Open in your IDE**
    - **IntelliJ IDEA**: File → Open → Select project folder
    - **Eclipse**: File → Import → Maven → Existing Maven Projects
    - **NetBeans**: File → Open Project → Select project folder

3. **Build the project** (if using Maven)
   ```bash
   mvn clean install
   ```

## 💻 How to Run

### Console Applications
```bash
# Compile
javac -d bin src/path/to/MainClass.java

# Run
java -cp bin path.to.MainClass
```

### JavaFX Applications
```bash
# Using Maven
mvn clean javafx:run

# Or run directly from IDE
# Right-click on main class → Run
```

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=YourTestClassName

# Generate test report
mvn surefire-report:report
```

## 📖 Code Examples

### Basic OOP Example
```java
public class Student {
    private String name;
    private int id;
    
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

### Lambda Expression Example
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);
```

## 🗄️ Database Setup

For JDBC examples, you can use either:

### Option 1: H2 Database (Embedded)
No installation required - included as Maven dependency

### Option 2: MySQL
1. Install MySQL Server
2. Create database:
   ```sql
   CREATE DATABASE java_oop_db;
   ```
3. Update connection string in `config.properties`

## 🧪 Testing

The project includes comprehensive unit tests using:
- **JUnit 5** for unit testing
- **TestFX** for JavaFX GUI testing
- **Mockito** for mocking dependencies

Run tests from IDE or command line as shown in the "How to Run" section.

## 📚 Resources

### Official Documentation
- [Java Documentation](https://docs.oracle.com/javase/8/docs/)
- [JavaFX Documentation](https://openjfx.io/openjfx-docs/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

### Learning Materials
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Baeldung Java Guides](https://www.baeldung.com/)
- [JavaFX Tutorials](https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm)

## 👤 Author

**GamAungLahpai**
- Student, Information Technology (Machine Learning track)
- Metropolia University of Applied Sciences
- GitHub: [@GamAungLahpai](https://github.com/GamAungLahpai)

## 🙏 Acknowledgments

- Metropolia University of Applied Sciences
- Course instructors and teaching assistants
- Fellow students for collaboration and peer review
- Open source community for libraries and tools

## 📮 Contact

For questions or feedback about this repository, please:
- Open an issue on GitHub
- Contact through university channels

---

*This repository is part of academic coursework at Metropolia UAS. Please ensure you understand and follow your institution's academic integrity policies when using this code.*
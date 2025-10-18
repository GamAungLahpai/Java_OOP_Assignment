import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Person class with name, age, and city fields
class Person {
    private String name;
    private int age;
    private String city;

    // Constructor
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
    }
}

public class Task1 {
    public static void main(String[] args) {
        // Step 2: Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, "New York"));
        people.add(new Person("Bob", 25, "Los Angeles"));
        people.add(new Person("Charlie", 35, "New York"));
        people.add(new Person("Diana", 28, "Chicago"));
        people.add(new Person("Eve", 22, "New York"));
        people.add(new Person("Frank", 40, "Los Angeles"));
        people.add(new Person("Grace", 27, "New York"));

        System.out.println("Original List:");
        for (Person p : people) {
            System.out.println(p);
        }

        // Step 3: Sort by age in ascending order using lambda expression
        people.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        // Alternative syntax: people.sort(Comparator.comparingInt(Person::getAge));

        System.out.println("\nSorted by Age (Ascending):");
        for (Person p : people) {
            System.out.println(p);
        }

        // Step 4: Filter by city - keep only people from "New York"
        people.removeIf(p -> !p.getCity().equals("New York"));

        System.out.println("\nFiltered (Only New York residents):");
        for (Person p : people) {
            System.out.println(p);
        }

        // Extra: Demonstrate other sorting options
        System.out.println("\n--- Additional Examples ---");

        // Create a new list for more examples
        List<Person> morePeople = new ArrayList<>();
        morePeople.add(new Person("Zoe", 30, "Boston"));
        morePeople.add(new Person("Alice", 25, "Seattle"));
        morePeople.add(new Person("Bob", 35, "Miami"));

        // Sort by name
        morePeople.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println("\nSorted by Name:");
        for (Person p : morePeople) {
            System.out.println(p);
        }

        // Sort by age descending
        morePeople.sort((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()));
        System.out.println("\nSorted by Age (Descending):");
        for (Person p : morePeople) {
            System.out.println(p);
        }

        // Sort by city, then by age
        morePeople.sort(Comparator.comparing(Person::getCity)
                .thenComparingInt(Person::getAge));
        System.out.println("\nSorted by City, then Age:");
        for (Person p : morePeople) {
            System.out.println(p);
        }
    }
}
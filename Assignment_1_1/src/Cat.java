public class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public void meow() {
        System.out.println("The cat named " + name + " says: Meow!");
    }

    public static void main(String[] args) {
        // Create an instance of the Cat class with a name
        Cat cat_1= new Cat("Whiskers");
        Cat cat_2= new Cat("Rex");

        // Call the meow method on the cat instance
        cat_1.meow();
        cat_1.meow();
        cat_2.meow();
        cat_1.meow();
    }
}
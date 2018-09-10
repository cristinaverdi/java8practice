package Java8PracticeTest;

public class Person {
    private final String name;
    public final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return this.name;
    }
}

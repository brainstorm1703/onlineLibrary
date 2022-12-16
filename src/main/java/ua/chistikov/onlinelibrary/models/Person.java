package ua.chistikov.onlinelibrary.models;

public class Person {

    private int person_id;
    private int yearOfBirth;
    private String name;

    public Person(){}

    public Person(int person_id, int yearOfBirth, String firstName) {
        this.person_id = person_id;
        this.yearOfBirth = yearOfBirth;
        this.name = firstName;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "yearOfBirth=" + yearOfBirth +
                ", name='" + name + '\'' +
                '}';
    }
}

package md1;

public class DeadPerson {
    int birthYear;
    int deathYear;
    public int age;
    Gender gender;

    public DeadPerson(int birthYear, int deathYear, Gender gender) {
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.gender = gender;
        this.age = deathYear - birthYear;
    }
}



package md1;

import java.util.*;

public class Segment {

    public static void main(String[] args) {
        DeadPerson person1 = new DeadPerson(1901, 1977, Gender.MALE);
        DeadPerson person2 = new DeadPerson(1899, 1956, Gender.MALE);
        DeadPerson person3 = new DeadPerson(1999, 2001, Gender.FEMALE);
        DeadPerson person4 = new DeadPerson(2000, 2001, Gender.FEMALE);
        DeadPerson person5 = new DeadPerson(1990, 1996, Gender.FEMALE);
        DeadPerson person6 = new DeadPerson(1901, 2002, Gender.MALE);
        DeadPerson person7 = new DeadPerson(1921, 2002, Gender.MALE);
        DeadPerson person8 = new DeadPerson(1921, 2002, Gender.FEMALE);
        DeadPerson person9 = new DeadPerson(1921, 2003, Gender.FEMALE);
        DeadPerson person10 = new DeadPerson(1921, 2004, Gender.FEMALE);

        List<DeadPerson> deadPeople = new ArrayList<DeadPerson>();

        deadPeople.add(person1);
        deadPeople.add(person2);
        deadPeople.add(person3);
        deadPeople.add(person4);
        deadPeople.add(person5);
        deadPeople.add(person6);
        deadPeople.add(person7);
        deadPeople.add(person8);
        deadPeople.add(person9);
        deadPeople.add(person10);

        System.out.println("Cilvēku skaits: " + deadPeople.size());
        System.out.print("Cilvēku vecums: ");

        int maxAge = 0;
        for (DeadPerson deadPerson : deadPeople) {
            System.out.print(deadPerson.age + ", ");
            if (deadPerson.age > maxAge) {
                maxAge = deadPerson.age;
            }
        }
        System.out.println();
        System.out.println("Maksimālais vecums: " + maxAge);

        int segmentCount = maxAge / 10 + 1;
        System.out.println("Spektru skaits dotai kapsētai: " + segmentCount);
        System.out.println();

        int[] segments = new int[segmentCount];

        for (DeadPerson deadPerson : deadPeople) {
            segments[deadPerson.age / 10]++;
        }

        System.out.println("Cilvēki sadalīti spektros");
        int i = 0;
        for (int segment : segments
        ) {
            int lowerBound = i * 10;
            int upperBound = lowerBound + 9;
            i++;
            System.out.println(i + " spektrs [" + lowerBound + ", " + upperBound + "]: " + segment);
        }
    }
}


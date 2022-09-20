package md1;
import java.util.Random;

public enum Gender {
    MALE,
    FEMALE;

    private static final Random RANDOM = new Random();

    public static Gender randomGender()  {
        Gender[] genders = values();
        return genders[RANDOM.nextInt(genders.length)];
    }
}

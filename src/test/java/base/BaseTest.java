package base;

import com.github.javafaker.Faker;
import models.Photos;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static Photos photos;
    protected static Faker faker;

    @BeforeAll
    public static void beforeAll() {
        photos = new Photos();
        faker = new Faker();
    }
}

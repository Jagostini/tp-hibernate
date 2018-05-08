package fr.epsi.dao;

import fr.epsi.model.Tweet;
import fr.epsi.model.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

public class UserDaoTest {

    @Test
    public void insertUser() {
        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");

        long id = new UserDao().save(user);

        User u = new UserDao().get(id);
        Assert.assertEquals("Benjamin", u.getFirstname());
    }

    @Test(expected = PersistenceException.class)
    public void uniqueEmail() {
        User user1 = new User();
        user1.setFirstname("Benjamin");
        user1.setLastname("Tourman");
        user1.setEmail("tourman.benjamin@gmail.com");

        User user2 = new User();
        user2.setFirstname("Test");
        user2.setLastname("Test");
        user2.setEmail("tourman.benjamin@gmail.com");

        new UserDao().save(user1);
        new UserDao().save(user2);
    }

    @Test
    public void ageAttribute() {
        int year = 2000;

        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("test@gmail.com");
        user.setBirthday(Date.valueOf(LocalDate.now().withYear(year)));

        long id = new UserDao().save(user);
        User u = new UserDao().get(id);

        Assert.assertEquals(LocalDate.now().getYear() - year, u.getAge());
    }

    @Test
    public void insertTweets() {
        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("test@gmail.com");
        user.setBirthday(Date.valueOf(LocalDate.now().withYear(2000)));

        Tweet tweet1 = new Tweet();
        tweet1.setMessage("Test1");
        tweet1.setUser(user);

        Tweet tweet2 = new Tweet();
        tweet2.setMessage("Test2");
        tweet2.setUser(user);

        user.setTweets(Arrays.asList(tweet1,tweet2));

        long id = new UserDao().save(user);
        User u = new UserDao().get(id);

        Assert.assertEquals(2, u.getTweets().size());
    }

}

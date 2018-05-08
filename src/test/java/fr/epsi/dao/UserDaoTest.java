package fr.epsi.dao;

import fr.epsi.model.User;
import org.junit.Assert;
import org.junit.Test;

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
}

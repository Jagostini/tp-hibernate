package fr.epsi.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthday;
    @Formula("(TIMESTAMPDIFF('YEAR',birthday,CURDATE()))")
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }
}

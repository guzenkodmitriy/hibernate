package com.guzenko.springcourse;
import com.guzenko.springcourse.model.Item;
import com.guzenko.springcourse.model.Passport;
import com.guzenko.springcourse.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
           session.beginTransaction();

           Person person = session.get(Person.class, 2);
           session.remove(person);

           session.getTransaction().commit();

        }
        finally {
            session.close();
        }

    }
}

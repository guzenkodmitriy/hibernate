package com.guzenko.springcourse;
import com.guzenko.springcourse.model.Item;
import com.guzenko.springcourse.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
           session.beginTransaction();

           Person person = new Person("Test cascading", 30);
           person.addItem(new Item("Item 1"));
           person.addItem(new Item("Item 2"));
           person.addItem(new Item("Item 3"));

           session.persist(person);

           session.getTransaction().commit();

        }
        finally {
            session.close();
        }

    }
}

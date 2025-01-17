package com.guzenko.springcourse;
import com.guzenko.springcourse.model.Item;
import com.guzenko.springcourse.model.Person;
import org.hibernate.Hibernate;
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

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека");
            System.out.println(person);

            session.getTransaction().commit();
            System.out.println("Сессия завершилась (session.close)");

            //Открываем сессию еще раз (можно сделать в любом месте в коде)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Внутри второй транзакции");

            //person = (Person) session.merge(person);

            List<Item> items = session.createQuery("select i from Item i where i.owner.id = :personId", Item.class)
            .setParameter("personId", person.getId()).getResultList();

            System.out.println(items);
            //Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println("Вне второй сессии");
//            //Это работает, т.к. связанные товары были загружены
//            System.out.println(person.getItems());
//
//            //вне сессии товары можем получать
//            System.out.println(person.getItems());

        }

    }
}

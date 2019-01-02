package pl.zagorski.TestQuery;

import pl.zagorski.domain.Action;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActionQueryTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Metoda findAll
        //TypedQuery<Action> query = entityManager.createQuery("Select c from Action c", Action.class);
        //List<Action> actionList = query.getResultList();
        //Metoda findOne
        Action action = entityManager.find(Action.class,1);

        TypedQuery<Action> q = entityManager.createQuery("Select c from Action c where c.name = :name", Action.class);
        Action action1 = q.setParameter("name", "baza leków").getSingleResult();



        entityManager.close();
        entityManagerFactory.close();

        System.out.println("----------Metoda findAll--------------");

//        for(Action a :actionList){
//            System.out.println(a.getId()+"."+a.getName());
//        }
        System.out.println("----------Metoda findOne--------------");
        System.out.println(action1.getId()+"."+action1.getName());



    }
}

package ru.clevertec.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

@Repository
public class ClientRepositoryImpl extends CrudRepositoryImpl<Client, Long> implements ClientRepository {

    @Autowired
    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Client.class);
    }

    @Override
    public void buyCar(Long clientId, Long carId) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Client client = session.get(Client.class, clientId);
            Car car = session.get(Car.class, carId);

            if (isCarForAddNotContains(client.getCars(), carId)) {
                client.getCars().add(car);
                session.merge(client);

                try {
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                }
            }
        }
    }

    private static boolean isCarForAddNotContains(List<Car> cars, Long carId) {
        return cars.stream().noneMatch(carInShowroom -> carInShowroom.getId().equals(carId));
    }
}

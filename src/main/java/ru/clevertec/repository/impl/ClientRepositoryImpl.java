package ru.clevertec.repository.impl;

import lombok.experimental.SuperBuilder;
import org.hibernate.Session;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

@SuperBuilder
public class ClientRepositoryImpl extends CrudRepositoryImpl<Client, Long> implements ClientRepository {

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

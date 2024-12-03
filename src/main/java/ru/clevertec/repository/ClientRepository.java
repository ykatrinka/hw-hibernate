package ru.clevertec.repository;

import ru.clevertec.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    void buyCar(Long clientId, Long carId);
}

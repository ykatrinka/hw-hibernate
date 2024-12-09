package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}

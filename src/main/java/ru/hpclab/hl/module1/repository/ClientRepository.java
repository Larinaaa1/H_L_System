package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {


//    @Query("SELECT r FROM Rental r WHERE " +
//            "(:startDate BETWEEN r.startDate AND r.endDate) OR " +
//            "(:endDate BETWEEN r.startDate AND r.endDate) OR " +
//            "(r.startDate BETWEEN :startDate AND :endDate)")
//    List<Rental> findOverlappingRentals(@Param("startDate") LocalDate startDate,
//                                              @Param("endDate") LocalDate endDate);
}
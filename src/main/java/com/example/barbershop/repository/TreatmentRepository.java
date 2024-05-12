package com.example.barbershop.repository;

import com.example.barbershop.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TreatmentRepository extends JpaRepository<Treatment,Long> {
    List<Treatment> findByNameContainingIgnoreCase(String name);

}

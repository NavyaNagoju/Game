package com.nri.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nri.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{

}

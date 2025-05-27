package com.nri.service;
import com.nri.model.Property;
import com.nri.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyService {
	 private final PropertyRepository repo;

	    public PropertyService(PropertyRepository repo) {
	        this.repo = repo;
	    }

	    public List<Property> getAllProperties() {
	        return repo.findAll();
	    }

	    public Property getPropertyById(Long id) {
	        return repo.findById(id).orElse(null);
	    }

	    public Property addProperty(Property property) {
	        return repo.save(property);
	    }

	    public void deleteProperty(Long id) {
	        repo.deleteById(id);
	    }
	
}

package com.nri.service;

import com.nri.model.Tenant;
import com.nri.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

	 private final TenantRepository repo;

	    public TenantService(TenantRepository repo) {
	        this.repo = repo;
	    }

	    public List<Tenant> getAll() {
	        return repo.findAll();
	    }

	    public Tenant getById(Long id) {
	        return repo.findById(id).orElse(null);
	    }

	    public Tenant add(Tenant tenant) {
	        return repo.save(tenant);
	    }

	    public Tenant update(Long id, Tenant tenant) {
	        Tenant existing = repo.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setName(tenant.getName());
	            existing.setEmail(tenant.getEmail());
	            existing.setPhoneNumber(tenant.getPhoneNumber());
	            existing.setProperty(tenant.getProperty());
	            return repo.save(existing);
	        }
	        return null;
	    }

	    public void delete(Long id) {
	        repo.deleteById(id);
	    }
}

package com.nri.service;

import com.nri.model.RentPayment;
import com.nri.repository.RentPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentPaymentService {

	private final RentPaymentRepository repo;

    public RentPaymentService(RentPaymentRepository repo) {
        this.repo = repo;
    }

    public List<RentPayment> getAll() {
        return repo.findAll();
    }

    public RentPayment getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public RentPayment add(RentPayment payment) {
        return repo.save(payment);
    }

    public RentPayment update(Long id, RentPayment payment) {
        RentPayment existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setPaymentDate(payment.getPaymentDate());
            existing.setAmount(payment.getAmount());
            existing.setPaymentMethod(payment.getPaymentMethod());
            existing.setProperty(payment.getProperty());
            existing.setTenant(payment.getTenant());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

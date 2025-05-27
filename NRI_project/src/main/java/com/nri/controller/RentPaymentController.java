package com.nri.controller;
import com.nri.model.RentPayment;
import com.nri.service.RentPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin

public class RentPaymentController {
	private final RentPaymentService service;

    public RentPaymentController(RentPaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<RentPayment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RentPayment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public RentPayment add(@RequestBody RentPayment payment) {
        return service.add(payment);
    }

    @PutMapping("/{id}")
    public RentPayment update(@PathVariable Long id, @RequestBody RentPayment payment) {
        return service.update(id, payment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

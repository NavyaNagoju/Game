package com.nri.controller;
import com.nri.model.Tenant;
import com.nri.service.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@CrossOrigin

public class TenantController {
	private final TenantService service;

    public TenantController(TenantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tenant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tenant getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Tenant add(@RequestBody Tenant tenant) {
        return service.add(tenant);
    }

    @PutMapping("/{id}")
    public Tenant update(@PathVariable Long id, @RequestBody Tenant tenant) {
        return service.update(id, tenant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

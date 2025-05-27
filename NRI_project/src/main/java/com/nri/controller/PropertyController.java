package com.nri.controller;

import com.nri.model.Property;
import com.nri.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin

public class PropertyController {
	private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Property> getAll() {
        return service.getAllProperties();
    }

    @GetMapping("/{id}")
    public Property getById(@PathVariable Long id) {
        return service.getPropertyById(id);
    }

    @PostMapping
    public Property add(@RequestBody Property property) {
        return service.addProperty(property);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProperty(id);
    }

}

package com.nri.controller;
import com.nri.model.LegalDocument;
import com.nri.service.LegalDocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin

public class LegalDocumentController {
	private final LegalDocumentService service;

    public LegalDocumentController(LegalDocumentService service) {
        this.service = service;
    }

    @GetMapping
    public List<LegalDocument> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LegalDocument getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public LegalDocument add(@RequestBody LegalDocument doc) {
        return service.add(doc);
    }

    @PutMapping("/{id}")
    public LegalDocument update(@PathVariable Long id, @RequestBody LegalDocument doc) {
        return service.update(id, doc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

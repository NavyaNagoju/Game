package com.nri.service;

import com.nri.model.LegalDocument;
import com.nri.repository.LegalDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalDocumentService {

	private final LegalDocumentRepository repo;

    public LegalDocumentService(LegalDocumentRepository repo) {
        this.repo = repo;
    }

    public List<LegalDocument> getAll() {
        return repo.findAll();
    }

    public LegalDocument getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public LegalDocument add(LegalDocument doc) {
        return repo.save(doc);
    }

    public LegalDocument update(Long id, LegalDocument doc) {
        LegalDocument existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setDocName(doc.getDocName());
            existing.setFilePath(doc.getFilePath());
            existing.setUploadDate(doc.getUploadDate());
            existing.setProperty(doc.getProperty());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

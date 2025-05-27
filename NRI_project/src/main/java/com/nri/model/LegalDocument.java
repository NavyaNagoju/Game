package com.nri.model;
import jakarta.persistence.*;
//import javax.persistence.*;
import java.util.Date;

@Entity

public class LegalDocument {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String docName;
	    private String filePath;
	    private Date uploadDate;

	    @ManyToOne
	    @JoinColumn(name = "property_id")
	    private Property property;

	    // Getters and Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getDocName() { return docName; }
	    public void setDocName(String docName) { this.docName = docName; }

	    public String getFilePath() { return filePath; }
	    public void setFilePath(String filePath) { this.filePath = filePath; }

	    public Date getUploadDate() { return uploadDate; }
	    public void setUploadDate(Date uploadDate) { this.uploadDate = uploadDate; }

	    public Property getProperty() { return property; }
	    public void setProperty(Property property) { this.property = property; }

}

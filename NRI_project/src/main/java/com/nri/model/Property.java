package com.nri.model;
import jakarta.persistence.*;
//import javax.persistence.*;


@Entity
public class Property {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private String address;
    private String tenantName;
    private String rentAmount;
    private boolean isOccupied;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public String getRentAmount() { return rentAmount; }
    public void setRentAmount(String rentAmount) { this.rentAmount = rentAmount; }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean isOccupied) { this.isOccupied = isOccupied; }

}

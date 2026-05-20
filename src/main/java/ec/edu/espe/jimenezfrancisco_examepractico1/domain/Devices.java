package ec.edu.espe.jimenezfrancisco_examepractico1.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 120, unique = true)
    private String serialNumber;

    @Column(length = 120)
    private String category;

    @Column(nullable = false)
    private boolean available = true;

    @Column(nullable = false)
    private int stock = 0;

    @Column(nullable = false)
    private boolean deleted = false;

    public Devices() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
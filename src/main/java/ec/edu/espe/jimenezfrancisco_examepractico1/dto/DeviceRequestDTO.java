package ec.edu.espe.jimenezfrancisco_examepractico1.dto;

import jakarta.validation.constraints.*;

public class DeviceRequestDTO {

    @NotBlank(message = "No puede estar vacío")
    @Size(min = 3, max = 120)
    private String name;

    @NotBlank(message = "No puede estar vacío")
    @Size(min = 3, max = 120)
    private String serialNumber;

    @NotBlank(message = "No puede estar vacío")
    @Size(max = 120)
    private String category;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
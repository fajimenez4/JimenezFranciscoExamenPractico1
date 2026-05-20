package ec.edu.espe.jimenezfrancisco_examepractico1.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class DeviceUpdateDTO {
    @NotBlank
    @Size(min = 3, max = 200)
    private String Name;

    @NotBlank
    @Size(max = 120)
    private String serialNumber;

    @NotBlank
    @Size(max = 120)
    private String Category;

    private boolean available;

    // Getters y Setters

    public @NotBlank @Size(min = 3, max = 200) String getName() {
        return Name;
    }

    public void setName(@NotBlank @Size(min = 3, max = 200) String name) {
        Name = name;
    }

    public @NotBlank @Size(max = 120) String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NotBlank @Size(max = 120) String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public @NotBlank @Size(max = 120) String getCategory() {
        return Category;
    }

    public void setCategory(@NotBlank @Size(max = 120) String category) {
        Category = category;
    }
}

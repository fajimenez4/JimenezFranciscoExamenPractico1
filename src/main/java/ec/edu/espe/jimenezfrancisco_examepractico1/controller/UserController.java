package ec.edu.espe.jimenezfrancisco_examepractico1.controller;

import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceResponseDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceUpdateDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jimenezfrancisco/devices")
public class UserController {

    private final DeviceService deviceService;

    public UserController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> create(@Valid @RequestBody DeviceRequestDTO dto) {
        return ResponseEntity.status(201).body(deviceService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> findAll() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.findById(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<DeviceResponseDTO> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.deactivate(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody DeviceUpdateDTO dto) {
        return ResponseEntity.ok(deviceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Long>> getReport() {
        return ResponseEntity.ok(deviceService.getReport());
    }

    @GetMapping("/search")
    public ResponseEntity<List<DeviceResponseDTO>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(deviceService.searchByCategory(category));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<DeviceResponseDTO>> getLowStock() {
        return ResponseEntity.ok(deviceService.getLowStock());
    }
}
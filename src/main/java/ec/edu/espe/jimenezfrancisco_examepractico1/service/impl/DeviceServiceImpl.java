package ec.edu.espe.jimenezfrancisco_examepractico1.service.impl;

import ec.edu.espe.jimenezfrancisco_examepractico1.domain.Devices;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceResponseDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceUpdateDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.exception.DuplicateSerialException;
import ec.edu.espe.jimenezfrancisco_examepractico1.exception.NotFoundException;
import ec.edu.espe.jimenezfrancisco_examepractico1.repository.DeviceRepository;
import ec.edu.espe.jimenezfrancisco_examepractico1.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public DeviceResponseDTO create(DeviceRequestDTO dto) {
        if (deviceRepository.existsBySerialNumber(dto.getSerialNumber())) {
            throw new DuplicateSerialException("Serial duplicado: " + dto.getSerialNumber());
        }
        if (dto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        Devices device = new Devices();
        device.setName(dto.getName());
        device.setSerialNumber(dto.getSerialNumber());
        device.setCategory(dto.getCategory());
        device.setStock(dto.getStock());
        device.setAvailable(true);
        device.setDeleted(false);
        return toResponse(deviceRepository.save(device));
    }

    @Override
    public DeviceResponseDTO findById(Long id) {
        Devices device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo no encontrado con ID: " + id));
        return toResponse(device);
    }

    @Override
    public List<DeviceResponseDTO> findAll() {
        return deviceRepository.findByDeletedFalse()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DeviceResponseDTO deactivate(Long id) {
        Devices device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo no encontrado con ID: " + id));
        device.setAvailable(false);
        return toResponse(deviceRepository.save(device));
    }

    @Override
    public DeviceResponseDTO update(Long id, DeviceUpdateDTO dto) {
        Devices device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo no encontrado con ID: " + id));
        device.setName(dto.getName());
        device.setSerialNumber(dto.getSerialNumber());
        device.setCategory(dto.getCategory());
        device.setAvailable(dto.isAvailable());
        return toResponse(deviceRepository.save(device));
    }

    @Override
    public Map<String, Long> getReport() {
        long total = deviceRepository.findByDeletedFalse().size();
        long available = deviceRepository.findByAvailable(true).stream()
                .filter(d -> !d.isDeleted()).count();
        long unavailable = total - available;
        return Map.of("total", total, "available", available, "unavailable", unavailable);
    }

    @Override
    public void delete(Long id) {
        Devices device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo no encontrado con ID: " + id));
        device.setDeleted(true);
        deviceRepository.save(device);
    }

    @Override
    public List<DeviceResponseDTO> searchByCategory(String keyword) {
        return deviceRepository.findByCategoryContainingIgnoreCaseAndDeletedFalse(keyword)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeviceResponseDTO> getLowStock() {
        return deviceRepository.findByStockLessThanAndDeletedFalse(5)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private DeviceResponseDTO toResponse(Devices device) {
        DeviceResponseDTO dto = new DeviceResponseDTO();
        dto.setId(device.getId());
        dto.setName(device.getName());
        dto.setSerialNumber(device.getSerialNumber());
        dto.setCategory(device.getCategory());
        dto.setAvailable(device.isAvailable());
        dto.setStock(device.getStock());
        dto.setDeleted(device.isDeleted());
        return dto;
    }
}
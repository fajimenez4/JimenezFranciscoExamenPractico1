package ec.edu.espe.jimenezfrancisco_examepractico1.service;

import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceResponseDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceUpdateDTO;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    DeviceResponseDTO create(DeviceRequestDTO dto);
    DeviceResponseDTO findById(Long id);
    List<DeviceResponseDTO> findAll();
    DeviceResponseDTO deactivate(Long id);
    DeviceResponseDTO update(Long id, DeviceUpdateDTO dto);
    Map<String, Long> getReport();
    void delete(Long id);
    List<DeviceResponseDTO> searchByCategory(String keyword);
    List<DeviceResponseDTO> getLowStock();
}
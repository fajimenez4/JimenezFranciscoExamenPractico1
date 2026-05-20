package ec.edu.espe.jimenezfrancisco_examepractico1;

import ec.edu.espe.jimenezfrancisco_examepractico1.domain.Devices;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceResponseDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.repository.DeviceRepository;
import ec.edu.espe.jimenezfrancisco_examepractico1.service.impl.DeviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class Test5EliminacionLogicaTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() { deviceRepository.deleteAll(); }

    @Test
    void testEliminacionLogica() {
        DeviceRequestDTO dto = new DeviceRequestDTO();
        dto.setName("Switch Cisco");
        dto.setSerialNumber("SW-001");
        dto.setCategory("Red");
        dto.setStock(1);

        DeviceResponseDTO created = deviceService.create(dto);
        deviceService.delete(created.getId());

        Devices inDb = deviceRepository.findById(created.getId()).orElseThrow();
        assertTrue(inDb.isDeleted());

        List<DeviceResponseDTO> all = deviceService.findAll();
        assertTrue(all.stream().noneMatch(d -> d.getId().equals(created.getId())));
    }
}
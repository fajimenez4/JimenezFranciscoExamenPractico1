package ec.edu.espe.jimenezfrancisco_examepractico1;

import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceResponseDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.repository.DeviceRepository;
import ec.edu.espe.jimenezfrancisco_examepractico1.service.impl.DeviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class Test4EstadisticasInventarioTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() { deviceRepository.deleteAll(); }

    @Test
    void testEstadisticasInventario() {
        DeviceRequestDTO d1 = new DeviceRequestDTO();
        d1.setName("Laptop A"); d1.setSerialNumber("S-001");
        d1.setCategory("Laptop"); d1.setStock(3);
        deviceService.create(d1);

        DeviceRequestDTO d2 = new DeviceRequestDTO();
        d2.setName("Laptop B"); d2.setSerialNumber("S-002");
        d2.setCategory("Laptop"); d2.setStock(3);
        deviceService.create(d2);

        DeviceRequestDTO d3 = new DeviceRequestDTO();
        d3.setName("Monitor C"); d3.setSerialNumber("S-003");
        d3.setCategory("Monitor"); d3.setStock(1);
        DeviceResponseDTO r3 = deviceService.create(d3);
        deviceService.deactivate(r3.getId());

        Map<String, Long> report = deviceService.getReport();

        assertEquals(3L, report.get("total"));
        assertEquals(2L, report.get("available"));
        assertEquals(1L, report.get("unavailable"));
    }
}
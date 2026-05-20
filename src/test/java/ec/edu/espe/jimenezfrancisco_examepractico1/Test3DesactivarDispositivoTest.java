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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class Test3DesactivarDispositivoTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() { deviceRepository.deleteAll(); }

    @Test
    void testDesactivarDispositivo() {
        DeviceRequestDTO dto = new DeviceRequestDTO();
        dto.setName("Tablet Samsung");
        dto.setSerialNumber("TAB-001");
        dto.setCategory("Tablet");
        dto.setStock(2);

        DeviceResponseDTO created = deviceService.create(dto);
        assertTrue(created.isAvailable());

        DeviceResponseDTO deactivated = deviceService.deactivate(created.getId());

        assertFalse(deactivated.isAvailable());
        assertEquals("Tablet Samsung", deactivated.getName());
        assertEquals("TAB-001", deactivated.getSerialNumber());
        assertEquals("Tablet", deactivated.getCategory());
    }
}
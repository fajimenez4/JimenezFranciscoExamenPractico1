package ec.edu.espe.jimenezfrancisco_examepractico1;

import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
import ec.edu.espe.jimenezfrancisco_examepractico1.exception.DuplicateSerialException;
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
class Test1SerialDuplicadoTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() { deviceRepository.deleteAll(); }

    @Test
    void testSerialDuplicadoLanzaExcepcion() {
        DeviceRequestDTO dto1 = new DeviceRequestDTO();
        dto1.setName("Laptop Dell");
        dto1.setSerialNumber("ABC-001");
        dto1.setCategory("Laptop");
        dto1.setStock(5);
        deviceService.create(dto1);

        DeviceRequestDTO dto2 = new DeviceRequestDTO();
        dto2.setName("Laptop HP");
        dto2.setSerialNumber("ABC-001");
        dto2.setCategory("Laptop");
        dto2.setStock(3);

        assertThrows(DuplicateSerialException.class, () -> deviceService.create(dto2));
        assertEquals(1, deviceRepository.count());
    }
}
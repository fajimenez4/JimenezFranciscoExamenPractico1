package ec.edu.espe.jimenezfrancisco_examepractico1;

import ec.edu.espe.jimenezfrancisco_examepractico1.dto.DeviceRequestDTO;
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
class Test2StockNegativoTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() { deviceRepository.deleteAll(); }

    @Test
    void testStockNegativoLanzaExcepcion() {
        DeviceRequestDTO dto = new DeviceRequestDTO();
        dto.setName("Router TP-Link");
        dto.setSerialNumber("RTR-001");
        dto.setCategory("Router");
        dto.setStock(-1);

        assertThrows(IllegalArgumentException.class, () -> deviceService.create(dto));
    }
}
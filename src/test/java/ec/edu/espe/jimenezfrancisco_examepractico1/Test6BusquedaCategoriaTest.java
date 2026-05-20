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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class Test6BusquedaCategoriaTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() { deviceRepository.deleteAll(); }

    @Test
    void testBusquedaParcialPorCategoria() {
        DeviceRequestDTO d1 = new DeviceRequestDTO();
        d1.setName("Laptop Lenovo"); d1.setSerialNumber("L-001");
        d1.setCategory("Laptop"); d1.setStock(2);
        deviceService.create(d1);

        DeviceRequestDTO d2 = new DeviceRequestDTO();
        d2.setName("Laptop Gamer Asus"); d2.setSerialNumber("L-002");
        d2.setCategory("Laptop Gamer"); d2.setStock(1);
        deviceService.create(d2);

        DeviceRequestDTO d3 = new DeviceRequestDTO();
        d3.setName("Router Mikrotik"); d3.setSerialNumber("R-001");
        d3.setCategory("Router"); d3.setStock(4);
        deviceService.create(d3);

        List<DeviceResponseDTO> result = deviceService.searchByCategory("lap");

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(d -> d.getName().equals("Laptop Lenovo")));
        assertTrue(result.stream().anyMatch(d -> d.getName().equals("Laptop Gamer Asus")));
        assertTrue(result.stream().noneMatch(d -> d.getName().equals("Router Mikrotik")));
    }
}
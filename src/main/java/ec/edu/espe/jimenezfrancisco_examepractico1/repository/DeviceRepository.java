package ec.edu.espe.jimenezfrancisco_examepractico1.repository;

import ec.edu.espe.jimenezfrancisco_examepractico1.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Devices, Long> {

    boolean existsBySerialNumber(String serialNumber);
    List<Devices> findByAvailable(boolean available);
    List<Devices> findByDeletedFalse();
    List<Devices> findByCategoryContainingIgnoreCaseAndDeletedFalse(String category);
    List<Devices> findByStockLessThanAndDeletedFalse(int stock);
    Optional<Devices> findByName(String name);
}
package co.edu.unbosque.gestion_financiera.repositories;

import co.edu.unbosque.gestion_financiera.model.entities.TarjetaCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito, Integer> {
    Optional<TarjetaCredito> findByNumeroTarjeta(String numeroTarjeta);
}
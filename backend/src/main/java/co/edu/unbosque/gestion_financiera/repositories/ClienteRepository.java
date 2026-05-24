package co.edu.unbosque.gestion_financiera.repositories;

import co.edu.unbosque.gestion_financiera.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}
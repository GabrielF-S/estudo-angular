package br.com.gabriel.clientes.model.repository;

import br.com.gabriel.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoPrestadoRepository extends JpaRepository<Cliente, Integer> {
}

package br.com.gabriel.clientes.model.repository;

import br.com.gabriel.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositoy  extends JpaRepository<Cliente, Integer> {
}

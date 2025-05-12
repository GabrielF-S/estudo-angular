package br.com.gabriel.clientes.model.repository;

import br.com.gabriel.clientes.model.entity.Cliente;
import br.com.gabriel.clientes.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

    @Query(value = "SELECT s from ServicoPrestado s JOIN s.cliente c WHERE  UPPER(c.nome) LIKE UPPER(:nome) and  MONTH(s.data) = :mes")
    List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);
}

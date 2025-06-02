package br.com.gabriel.clientes.model.repository;

import br.com.gabriel.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}

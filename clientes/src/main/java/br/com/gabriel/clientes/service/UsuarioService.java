package br.com.gabriel.clientes.service;

import br.com.gabriel.clientes.model.entity.Usuario;
import br.com.gabriel.clientes.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        if (repository.existsByUsername(usuario.getUsername())){
        throw  new br.com.gabriel.clientes.exeption.UsuarioCadastradoException(usuario.getUsername());
        }
        Usuario saved = repository.save(usuario);
        return saved;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Usuario usuario =  repository
               .findByUsername(username)
               .orElseThrow(()-> new UsernameNotFoundException("Login não encontrado."));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}

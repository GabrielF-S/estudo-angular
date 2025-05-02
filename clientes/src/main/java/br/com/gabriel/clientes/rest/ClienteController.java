package br.com.gabriel.clientes.rest;

import br.com.gabriel.clientes.model.entity.Cliente;
import br.com.gabriel.clientes.model.repository.ClienteRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteRepositoy repositoy;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllCliente() {
        return ResponseEntity.ok(repositoy.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                repositoy.findById(id).
                        orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não localizado")
                        )
        );

    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Validated Cliente cliente) {
        return ResponseEntity.ok(repositoy.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientePorId(@PathVariable Integer id) {
        Cliente cliente =  repositoy.findById(id).get();
        if (cliente != null){
            repositoy.delete(cliente);
            return ResponseEntity.ok().body(Void.TYPE);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não localizado");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateCliente(@PathVariable Integer id, @RequestBody  @Validated Cliente clienteAtualizado) {
        return  ResponseEntity.ok(repositoy.findById(id).stream().map(cliente -> {
            clienteAtualizado.setId(cliente.getId());
            clienteAtualizado.setDataCadastro(cliente.getDataCadastro());
            repositoy.save(clienteAtualizado);
            return HttpStatus.NO_CONTENT;
        }));

    }
}

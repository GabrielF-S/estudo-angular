package br.com.gabriel.clientes.rest;

import br.com.gabriel.clientes.model.entity.Cliente;
import br.com.gabriel.clientes.model.entity.ServicoPrestado;
import br.com.gabriel.clientes.model.repository.ClienteRepositoy;
import br.com.gabriel.clientes.model.repository.ServicoPrestadoRepository;
import br.com.gabriel.clientes.rest.dto.ServicoPrestadoDTO;
import br.com.gabriel.clientes.util.BigDecialConverter;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    private final ClienteRepositoy clienteRepositoy;
    private final ServicoPrestadoRepository repository;
    private final BigDecialConverter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@Valid @RequestBody ServicoPrestadoDTO dto){


        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyy"));
        Integer idCliente  = dto.getIdCliente();
        Cliente cliente = clienteRepositoy.findById(idCliente).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexistente"));
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);

        servicoPrestado.setValor(converter.converter(dto.getPreco()));
        return  repository.save(servicoPrestado);
    }


    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes){

        return repository.findByNomeClienteAndMes("%"+nome+"%", mes);



    }
}

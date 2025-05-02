package br.com.gabriel.clientes.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrors {
    private List<String> errors;


    public ApiErrors(String message) {
        this.errors  = Arrays.asList(message);
    }
}

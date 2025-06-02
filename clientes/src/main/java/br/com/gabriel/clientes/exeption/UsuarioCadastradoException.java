package br.com.gabriel.clientes.exeption;

public class UsuarioCadastradoException extends RuntimeException{

    public UsuarioCadastradoException(String login) {
        super("Usuario já cadastrado para login " + login );
    }
}

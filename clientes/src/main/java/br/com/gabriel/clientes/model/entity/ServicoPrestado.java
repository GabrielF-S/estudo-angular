package br.com.gabriel.clientes.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class ServicoPrestado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false, length = 150)
    private  String descricao;
   @ManyToOne
   @JoinColumn(name = "id_cliente")
    private  Cliente cliente;
   @Column
   private BigDecimal valor;

   @Column
    private LocalDate data;
}

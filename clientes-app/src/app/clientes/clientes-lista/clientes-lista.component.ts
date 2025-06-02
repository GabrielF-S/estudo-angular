import { Component, OnInit } from '@angular/core';
import { Cliente} from '../cliente'
import { ClientesService } from '../../clientes.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {
  clientes: Cliente[] = [];
  clienteSelecionado: Cliente;
  mensagemmSucesso: String;
  mensagemErro: String;

  constructor(
    private service: ClientesService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.service.getClientes()
      .subscribe(response =>
      {
        this.clientes = response;
      }
      );
  }

  novoCadastro() {
    this.router.navigate(['/clientes/form']);
  };
 
  preparaDelecao(cliente: Cliente) {
    this.clienteSelecionado = cliente;
  }
  deletarCliente() {
    console.log(this.clienteSelecionado)
    this.service.deletar(this.clienteSelecionado)
      .subscribe(response => {
        this.mensagemmSucesso = "Cliente deleteado com sucesso!"
        this.ngOnInit();
      }, error => {
        this.mensagemErro = "Erro ao deletar cliente!"
      })
  }

}

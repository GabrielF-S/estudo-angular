import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente'
import { ClientesService } from '../../clientes.service'
import { Location } from '@angular/common'
import { ActivatedRoute} from '@angular/router'


@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: ClientesService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
    this.cliente = new Cliente();

  }

  ngOnInit(): void {
    let params = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service.getClienteById(this.id)
        .subscribe(response => {
          this.cliente = response,
            erroResponse => this.cliente = new Cliente();
        })
      }
    })
   
  }
  onSubimit() {
    if (this.id) {
      this.service.atualizar(this.cliente)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
        }, errorResponse => {
          this.success= false;
          this.errors = errorResponse.error.errors;
        })     
    } else {
      this.service
      .salvar(this.cliente)
      .subscribe(response => {
        this.success = true;
        this.errors = null;
        this.cliente = response;
      }, errorResponse => {
        this.success = false;
        this.errors =  errorResponse.error.errors;
      }
      )
    }
    

  };

  voltar() {
    this.location.back();
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from './usuario'
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string;
  password: string;
  errors: String[];
  cadastrando: boolean;
  messagemSucesso: string;

  constructor(
    private router: Router,
    private authService: AuthService,
  ) { }

  onSubmit() {
    this.authService
      .tentarLogar(this.username, this.password).subscribe(successs=> {
        const access_token = JSON.stringify(successs);
        localStorage.setItem('access_token' ,access_token);
        this.router.navigate(['/home']);     
      }, errorResponse =>{
        this.messagemSucesso = ""; 
        this.errors = ['Usário e/ou Senha incorreto(s). ']
      } )

  }

  preparaCadastrar(event) {
    event.preventDefault();
    this.cadastrando = true;
  }
  cancelaCadastro() {

    this.cadastrando = false;
  }

  cadastrar() {
    const usuario: Usuario = new Usuario();
    usuario.username = this.username;
    usuario.password = this.password;
    this.authService.salvar(usuario).subscribe(
      response => {
        this.messagemSucesso = "Cadastro Realizado com sucesso";
        this.cadastrando = false;
        this.username = "";
        this.password = "";
        this.errors = [];
      }, errorResponse => {
        this.errors = errorResponse.error.errors;
        this.messagemSucesso = null;

      }
    )
  }
}

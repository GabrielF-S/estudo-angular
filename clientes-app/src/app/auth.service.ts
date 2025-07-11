import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './login/usuario';
import { Observable } from 'rxjs';

import { environment } from '../environments/environment';
import {JwtHelperService} from '@auth0/angular-jwt'; 

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiURL: string = environment.apiURLBase + "/api/usuarios";
  tokenUrl: string = environment.apiURLBase + environment.obterTokenUrl;
  clientId: string = environment.clientId;
  clientSecret: string = environment.clientSecret;
  jwtHelper : JwtHelperService = new JwtHelperService();

  constructor(
    private http: HttpClient
  ) {

  }
  obterToken(){
    const tokenString = localStorage.getItem('access_token');
    if(tokenString){
      const token = JSON.parse(tokenString).access_token;
      return token;
    }
    return null;
  }
  encerrarSessao(){
    localStorage.removeItem('access_token')
  }
  getUsuarioAutenticado(){
    const token = this.obterToken();
    if(token){
      const username =this.jwtHelper.decodeToken(token).user_name;
      return username
    }else{
      return null;
    }
  }

  isAuthenticated() : boolean {
    const token = this.obterToken();
    if(token){
      const expireted = this.jwtHelper.isTokenExpired(token);
      return !expireted
    }
    return false;
  }

  salvar(usuario: Usuario): Observable<any> {
    return this.http.post<any>(this.apiURL, usuario);
  }

  tentarLogar(username: string, password: string): Observable<any> {
    const params = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('grant_type', 'password');
    const headers = {
      'Authorization' : 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
      'Content-Type' : 'application/x-www-form-urlencoded'
    }
    return this.http.post(this.tokenUrl,params.toString(), {headers})
  }
}

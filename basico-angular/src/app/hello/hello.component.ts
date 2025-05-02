import { Component } from "@angular/core"

@Component({
    selector: 'hello',
    templateUrl: './hello.component.html'
})
export class HelloComponent{

    nome: String;
    clientes: Cliente[];

    constructor() {
        this.nome = "Maria Izabel";

        let cliente1 = new Cliente("Fulano", 27);
        let cliente2 = new Cliente("Siclano", 30);
        let cliente3 = new Cliente("Deltrano", 25);
        this.clientes= [cliente1, cliente2, cliente3]
    }

}

class Cliente{

    constructor(
        public nome: string,
        public idade: number) {
        
 
    }
    

}
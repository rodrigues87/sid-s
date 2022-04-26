import {Component, OnInit} from '@angular/core';
import {PessoaService} from '../../services/pessoa.service';
import {WebSocketService} from '../../../service/web-socket-service.service';

@Component({
    selector: 'app-total-de-candidatos',
    templateUrl: './total-de-candidatos.component.html',
    styleUrls: ['./total-de-candidatos.component.css']
})
export class TotalDeCandidatosComponent implements OnInit {

    public quantidadeTotalDeCandidatos: number;
    public quantidadeDoadoresAptos: number;


    constructor(private pessoaService: PessoaService,
                private webSocketService: WebSocketService) {

        const stompClient = this.webSocketService.connect();
        stompClient.connect({}, frame => {

            stompClient.subscribe('/topic/quantidadeDoadoresAptos', ress => {

                this.quantidadeDoadoresAptos = ress.body
            })
        });
    }

    ngOnInit(): void {

        this.pessoaService.countAll().subscribe(res => {
            this.quantidadeTotalDeCandidatos = res;
        });

        this.pessoaService.quantidadeDoadoresAptos().subscribe(res => {
            this.quantidadeDoadoresAptos = res;
        });

    }

}

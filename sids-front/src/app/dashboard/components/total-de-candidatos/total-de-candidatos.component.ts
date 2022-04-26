import { Component, OnInit } from '@angular/core';
import {PessoaService} from '../../services/pessoa.service';

@Component({
  selector: 'app-total-de-candidatos',
  templateUrl: './total-de-candidatos.component.html',
  styleUrls: ['./total-de-candidatos.component.css']
})
export class TotalDeCandidatosComponent implements OnInit {

  public quantidadeTotalDeCandidatos: number;
  public quantidadeDoadoresAptos: number;



  constructor(private pessoaService: PessoaService) {
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

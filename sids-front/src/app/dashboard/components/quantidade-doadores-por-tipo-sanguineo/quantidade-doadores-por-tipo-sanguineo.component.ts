import { Component, OnInit } from '@angular/core';
import {PercentualDeObesosBySexo} from '../../models/percentual-de-obesos-by-sexo.model';
import {PessoaService} from '../../services/pessoa.service';
import {QuantidadeDoadoresPorTipoSanguineo} from '../../models/quantidade-doadores-por-tipo-sanguineo.model';

@Component({
  selector: 'app-quantidade-doadores-por-tipo-sanguineo',
  templateUrl: './quantidade-doadores-por-tipo-sanguineo.component.html',
  styleUrls: ['./quantidade-doadores-por-tipo-sanguineo.component.css']
})
export class QuantidadeDoadoresPorTipoSanguineoComponent implements OnInit {

  public quantidadeDoadoresPorTipoSanguineolist: QuantidadeDoadoresPorTipoSanguineo[] = [];

  constructor(private pessoaService: PessoaService) {
  }
  ngOnInit(): void {
    this.pessoaService.getQuantidadePossiveisDoadores().subscribe(res => {
      this.quantidadeDoadoresPorTipoSanguineolist = res;
    });
  }

}

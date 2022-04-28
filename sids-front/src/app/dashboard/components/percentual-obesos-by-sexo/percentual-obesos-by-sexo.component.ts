import {Component, OnInit} from '@angular/core';
import {PessoaService} from '../../services/pessoa.service';
import {PercentualDeObesosBySexo} from '../../models/percentual-de-obesos-by-sexo.model';

@Component({
  selector: 'app-percentual-obesos-by-sexo',
  templateUrl: './percentual-obesos-by-sexo.component.html',
  styleUrls: ['./percentual-obesos-by-sexo.component.css']
})
export class PercentualObesosBySexoComponent implements OnInit {

  private percentualDeObesosBySexoList: PercentualDeObesosBySexo[] = [];
  public porcentagemObesosMaculino: number;
  public porcentagemObesosFeminino: number;

  constructor(private pessoaService: PessoaService) {
  }

  ngOnInit(): void {
    this.pessoaService.getPersentualObesosBySexo().subscribe(res => {

      this.percentualDeObesosBySexoList = res;


      this.percentualDeObesosBySexoList.forEach(percentualDeObesosBySexo => {

        if (percentualDeObesosBySexo.sexo === 'Feminino') {
          this.porcentagemObesosFeminino = percentualDeObesosBySexo.porcentagemDeObesos
        }

        if (percentualDeObesosBySexo.sexo === 'Masculino') {
          this.porcentagemObesosMaculino = percentualDeObesosBySexo.porcentagemDeObesos
        }


      });


    });


  }

  reset() {
    this.ngOnInit();
  }
}

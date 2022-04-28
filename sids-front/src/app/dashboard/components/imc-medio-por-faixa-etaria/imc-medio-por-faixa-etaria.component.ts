import {Component, OnInit} from '@angular/core';
import {PessoaService} from '../../services/pessoa.service';
import * as Chartist from 'chartist';
import {PessoaImcMedioFaixaEtaria} from '../../models/pessoa-imc-medio-faixa-etaria-dto.model';
import {DashboardComponent} from '../../dashboard.component';

@Component({
    selector: 'app-imc-medio-por-faixa-etaria',
    templateUrl: './imc-medio-por-faixa-etaria.component.html',
    styleUrls: ['./imc-medio-por-faixa-etaria.component.css']
})
export class ImcMedioPorFaixaEtariaComponent implements OnInit {

    private pessoaImcMedioFaixaEtariaList: PessoaImcMedioFaixaEtaria[] = [];
    private labels: string[] = [];
    private series: number[] = [];

    constructor(private pessoaService: PessoaService,
                private dashboardComponent: DashboardComponent) {
    }

    ngOnInit(): void {


        this.pessoaService.getImcMedioPorFaixaEtaria().subscribe(res => {
            this.labels = [];
            this.series = [];
            this.pessoaImcMedioFaixaEtariaList = res;

            this.pessoaImcMedioFaixaEtariaList.forEach(pessoaImcMedioFaixaEtaria => {
                this.labels.push(pessoaImcMedioFaixaEtaria.faixaEtaria)
                this.series.push(pessoaImcMedioFaixaEtaria.imcMedio)


            });

            const dataDailySalesChart: any = {
                labels: this.labels,
                series: [this.series],

            };

            const optionsDailySalesChart: any = {
                lineSmooth: Chartist.Interpolation.cardinal({
                    tension: 0
                }),
                low: 0,
                high: Math.max(...this.series),
                chartPadding: {top: 0, right: 0, bottom: 0, left: 0},
                height: '230px',

            }

            const dailySalesChart = new Chartist.Bar('#websiteViewsChart', dataDailySalesChart, optionsDailySalesChart);

            // this.dashboardComponent.startAnimationForBarChart(dailySalesChart)

        });


    }

    reset() {
        this.ngOnInit();
    }
}

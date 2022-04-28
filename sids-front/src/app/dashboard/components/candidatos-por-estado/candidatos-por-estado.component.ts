import {Component, OnInit} from '@angular/core';
import * as Chartist from 'chartist';
import {PessoaService} from '../../services/pessoa.service';
import {CandidatosPorEstado} from '../../models/candidatos-por-estado.model';
import {DashboardComponent} from '../../dashboard.component';

@Component({
    selector: 'app-candidatos-por-estado',
    templateUrl: './candidatos-por-estado.component.html',
    styleUrls: ['./candidatos-por-estado.component.css']
})
export class CandidatosPorEstadoComponent implements OnInit {

    private candidatosPorEstados: CandidatosPorEstado[] = [];
    private labels: string[] = [];
    private series: number[] = [];

    constructor(private pessoaService: PessoaService,
                private dashboardComponent: DashboardComponent) {
    }

    ngOnInit(): void {

        this.pessoaService.getQuantidadeCandidatosPorEstado().subscribe(res => {
            this.labels = [];
            this.series = [];

            this.candidatosPorEstados = res;

            this.candidatosPorEstados.forEach(candidatoPorEstado => {
                this.labels.push(candidatoPorEstado.estado)
                this.series.push(candidatoPorEstado.quantidade)
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
                height: '230px'


            }

            const dailySalesChart = new Chartist.Bar('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

            // this.dashboardComponent.startAnimationForBarChart(dailySalesChart)

        });

    }


    reset() {
        this.ngOnInit();
    }

}

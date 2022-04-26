import {Component, OnInit} from '@angular/core';
import * as Chartist from 'chartist';
import {PessoaService} from '../../services/pessoa.service';
import {CandidatosPorEstado} from '../../models/candidatos-por-estado.model';

@Component({
    selector: 'app-candidatos-por-estado',
    templateUrl: './candidatos-por-estado.component.html',
    styleUrls: ['./candidatos-por-estado.component.css']
})
export class CandidatosPorEstadoComponent implements OnInit {

    private candidatosPorEstados: CandidatosPorEstado[] = [];
    private labels: string[] = [];
    private series: number[] = [];

    constructor(private pessoaService: PessoaService) {
    }

    ngOnInit(): void {


        this.pessoaService.getQuantidadeCandidatosPorEstado().subscribe(res => {
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
                height: '250px'


            }

            const dailySalesChart = new Chartist.Bar('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

            this.startAnimationForLineChart(dailySalesChart)

        });





    }

    startAnimationForLineChart(chart) {
        let seq: any, delays: any, durations: any;
        seq = 0;
        delays = 80;
        durations = 500;

        chart.on('draw', function (data) {
            if (data.type === 'line' || data.type === 'area') {
                data.element.animate({
                    d: {
                        begin: 600,
                        dur: 700,
                        from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
                        to: data.path.clone().stringify(),
                        easing: Chartist.Svg.Easing.easeOutQuint
                    }
                });
            } else if (data.type === 'point') {
                seq++;
                data.element.animate({
                    opacity: {
                        begin: seq * delays,
                        dur: durations,
                        from: 0,
                        to: 1,
                        easing: 'ease'
                    }
                });
            }
        });

        seq = 0;
    };


}

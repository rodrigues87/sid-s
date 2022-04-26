import {Component, OnInit} from '@angular/core';
import {CandidatosPorEstado} from '../../models/candidatos-por-estado.model';
import {PessoaService} from '../../services/pessoa.service';
import * as Chartist from 'chartist';
import {PessoaImcMedioFaixaEtaria} from '../../models/pessoa-imc-medio-faixa-etaria-dto.model';

@Component({
    selector: 'app-imc-medio-por-faixa-etaria',
    templateUrl: './imc-medio-por-faixa-etaria.component.html',
    styleUrls: ['./imc-medio-por-faixa-etaria.component.css']
})
export class ImcMedioPorFaixaEtariaComponent implements OnInit {

    private pessoaImcMedioFaixaEtariaList: PessoaImcMedioFaixaEtaria[] = [];
    private labels: string[] = [];
    private series: number[] = [];

    constructor(private pessoaService: PessoaService) {
    }

    ngOnInit(): void {


        this.pessoaService.getImcMedioPorFaixaEtaria().subscribe(res => {
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

import {Component, OnInit} from '@angular/core';
import {PessoaService} from '../../services/pessoa.service';
import * as Chartist from 'chartist';
import {DashboardComponent} from '../../dashboard.component';
import {MediaDeIdadePorTipoSanguineo} from '../../models/media-idade-por-tipo-sanguineo.model';

@Component({
    selector: 'app-media-idade-por-tipo-sanguineo',
    templateUrl: './media-idade-por-tipo-sanguineo.component.html',
    styleUrls: ['./media-idade-por-tipo-sanguineo.component.css']
})
export class MediaIdadePorTipoSanguineoComponent implements OnInit {

    private mediaDeIdadePorTipoSanguineolist: MediaDeIdadePorTipoSanguineo[] = [];
    private labels: string[] = [];
    private series: number[] = [];

    constructor(private pessoaService: PessoaService,
                private dashboardComponent: DashboardComponent) {
    }

    ngOnInit(): void {


        this.pessoaService.getMediaIdadeByTipoSanguineo().subscribe(res => {
            this.labels = [];
            this.series = [];
            this.mediaDeIdadePorTipoSanguineolist = res;

            this.mediaDeIdadePorTipoSanguineolist.forEach(mediaDeIdadePorTipoSanguineo => {
                this.labels.push(mediaDeIdadePorTipoSanguineo.tipoSanguineo)
                this.series.push(mediaDeIdadePorTipoSanguineo.mediaDeIdade)


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

            const dailySalesChart = new Chartist.Bar('#mediaIdadePorTipoSanguineo', dataDailySalesChart, optionsDailySalesChart);

            //  this.dashboardComponent.startAnimationForBarChart(dailySalesChart)

        });


    }

    reset() {
        this.ngOnInit();
    }
}

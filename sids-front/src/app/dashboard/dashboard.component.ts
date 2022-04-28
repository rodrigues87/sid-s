import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {WebSocketService} from '../service/web-socket-service.service';
import {TotalDeCandidatosComponent} from './components/total-de-candidatos/total-de-candidatos.component';
import {
    QuantidadeDoadoresPorTipoSanguineoComponent
} from './components/quantidade-doadores-por-tipo-sanguineo/quantidade-doadores-por-tipo-sanguineo.component';
import {
    PercentualObesosBySexoComponent
} from './components/percentual-obesos-by-sexo/percentual-obesos-by-sexo.component';
import {
    MediaIdadePorTipoSanguineoComponent
} from './components/media-idade-por-tipo-sanguineo/media-idade-por-tipo-sanguineo.component';
import {
    ImcMedioPorFaixaEtariaComponent
} from './components/imc-medio-por-faixa-etaria/imc-medio-por-faixa-etaria.component';
import {CandidatosPorEstadoComponent} from './components/candidatos-por-estado/candidatos-por-estado.component';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    @ViewChildren(TotalDeCandidatosComponent) totalDeCandidatosComponents: QueryList<TotalDeCandidatosComponent>
    @ViewChildren(QuantidadeDoadoresPorTipoSanguineoComponent) quantidadeDoadoresPorTipoSanguineoComponents: QueryList<QuantidadeDoadoresPorTipoSanguineoComponent>
    @ViewChildren(PercentualObesosBySexoComponent) percentualObesosBySexoComponents: QueryList<PercentualObesosBySexoComponent>
    @ViewChildren(MediaIdadePorTipoSanguineoComponent) mediaIdadePorTipoSanguineoComponents: QueryList<MediaIdadePorTipoSanguineoComponent>
    @ViewChildren(ImcMedioPorFaixaEtariaComponent) imcMedioPorFaixaEtariaComponents: QueryList<ImcMedioPorFaixaEtariaComponent>
    @ViewChildren(CandidatosPorEstadoComponent) candidatosPorEstadoComponents: QueryList<CandidatosPorEstadoComponent>

    constructor(private webSocketService: WebSocketService) {
        this.startWebSocket();
    }

    ngOnInit() {
    }

    resetAll() {
        this.totalDeCandidatosComponents.forEach(c => c.reset());
        this.quantidadeDoadoresPorTipoSanguineoComponents.forEach(c => c.reset());
        this.percentualObesosBySexoComponents.forEach(c => c.reset());
        this.mediaIdadePorTipoSanguineoComponents.forEach(c => c.reset());
        this.imcMedioPorFaixaEtariaComponents.forEach(c => c.reset());
        this.candidatosPorEstadoComponents.forEach(c => c.reset());

    }

    private startWebSocket() {
        const stompClient = this.webSocketService.connect();
        stompClient.connect({}, frame => {

            stompClient.subscribe('/pessoa/save', ress => {

                this.resetAll();

            })
        });
    }

    /*
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

    startAnimationForBarChart(chart) {
        let seq2: any, delays2: any, durations2: any;

        seq2 = 0;
        delays2 = 80;
        durations2 = 500;
        chart.on('draw', function (data) {
            if (data.type === 'bar') {
                seq2++;
                data.element.animate({
                    opacity: {
                        begin: seq2 * delays2,
                        dur: durations2,
                        from: 0,
                        to: 1,
                        easing: 'ease'
                    }
                });
            }
        });

        seq2 = 0;
    };
*/
}

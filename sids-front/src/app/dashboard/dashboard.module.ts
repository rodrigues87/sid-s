import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CandidatosPorEstadoComponent } from './components/candidatos-por-estado/candidatos-por-estado.component';
import { ImcMedioPorFaixaEtariaComponent } from './components/imc-medio-por-faixa-etaria/imc-medio-por-faixa-etaria.component';
import { PercentualObesosBySexoComponent } from './components/percentual-obesos-by-sexo/percentual-obesos-by-sexo.component';
import { MediaIdadePorTipoSanguineoComponent } from './components/media-idade-por-tipo-sanguineo/media-idade-por-tipo-sanguineo.component';
import { QuantidadeDoadoresPorTipoSanguineoComponent } from './components/quantidade-doadores-por-tipo-sanguineo/quantidade-doadores-por-tipo-sanguineo.component';
import { TotalDeCandidatosComponent } from './components/total-de-candidatos/total-de-candidatos.component';



@NgModule({
  declarations: [
    CandidatosPorEstadoComponent,
    ImcMedioPorFaixaEtariaComponent,
    PercentualObesosBySexoComponent,
    MediaIdadePorTipoSanguineoComponent,
    QuantidadeDoadoresPorTipoSanguineoComponent,
    TotalDeCandidatosComponent
  ],
    exports: [
        CandidatosPorEstadoComponent,
        ImcMedioPorFaixaEtariaComponent,
        PercentualObesosBySexoComponent,
        MediaIdadePorTipoSanguineoComponent,
        QuantidadeDoadoresPorTipoSanguineoComponent,
        TotalDeCandidatosComponent
    ],
  imports: [
    CommonModule
  ]
})
export class DashboardModule { }

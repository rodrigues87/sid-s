import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ImportacaoComponent} from './importacao/importacao.component';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatProgressBarModule} from '@angular/material/progress-bar';



@NgModule({
  declarations: [
    ImportacaoComponent
  ],
  exports: [
    ImportacaoComponent
  ],
    imports: [
        CommonModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatProgressBarModule
    ]
})
export class ImportModule { }

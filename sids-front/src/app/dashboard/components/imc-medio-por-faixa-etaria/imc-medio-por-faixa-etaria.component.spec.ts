import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImcMedioPorFaixaEtariaComponent } from './imc-medio-por-faixa-etaria.component';

describe('ImcMedioPorFaixaEtariaComponent', () => {
  let component: ImcMedioPorFaixaEtariaComponent;
  let fixture: ComponentFixture<ImcMedioPorFaixaEtariaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImcMedioPorFaixaEtariaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImcMedioPorFaixaEtariaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

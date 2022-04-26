import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuantidadeDoadoresPorTipoSanguineoComponent } from './quantidade-doadores-por-tipo-sanguineo.component';

describe('QuantidadeDoadoresPorTipoSanguineoComponent', () => {
  let component: QuantidadeDoadoresPorTipoSanguineoComponent;
  let fixture: ComponentFixture<QuantidadeDoadoresPorTipoSanguineoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuantidadeDoadoresPorTipoSanguineoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuantidadeDoadoresPorTipoSanguineoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

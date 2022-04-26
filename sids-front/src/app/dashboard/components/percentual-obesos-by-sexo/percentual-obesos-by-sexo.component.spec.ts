import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PercentualObesosBySexoComponent } from './percentual-obesos-by-sexo.component';

describe('PercentualObesosBySexoComponent', () => {
  let component: PercentualObesosBySexoComponent;
  let fixture: ComponentFixture<PercentualObesosBySexoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PercentualObesosBySexoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PercentualObesosBySexoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

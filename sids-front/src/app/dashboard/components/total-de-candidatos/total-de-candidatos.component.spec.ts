import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalDeCandidatosComponent } from './total-de-candidatos.component';

describe('TotalDeCandidatosComponent', () => {
  let component: TotalDeCandidatosComponent;
  let fixture: ComponentFixture<TotalDeCandidatosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TotalDeCandidatosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TotalDeCandidatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediaIdadePorTipoSanguineoComponent } from './media-idade-por-tipo-sanguineo.component';

describe('MediaIdadePorTipoSanguineoComponent', () => {
  let component: MediaIdadePorTipoSanguineoComponent;
  let fixture: ComponentFixture<MediaIdadePorTipoSanguineoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MediaIdadePorTipoSanguineoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MediaIdadePorTipoSanguineoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

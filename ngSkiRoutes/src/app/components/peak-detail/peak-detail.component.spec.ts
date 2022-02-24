import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeakDetailComponent } from './peak-detail.component';

describe('PeakDetailComponent', () => {
  let component: PeakDetailComponent;
  let fixture: ComponentFixture<PeakDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PeakDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PeakDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

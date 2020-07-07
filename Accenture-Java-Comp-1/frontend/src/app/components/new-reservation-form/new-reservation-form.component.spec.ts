import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewReservationFormComponent} from './new-reservation-form.component';

describe('NewReservationFormComponent', () => {
  let component: NewReservationFormComponent;
  let fixture: ComponentFixture<NewReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NewReservationFormComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminTodayNumbersComponent} from './admin-today-numbers.component';

describe('AdminTodayNumbersComponent', () => {
  let component: AdminTodayNumbersComponent;
  let fixture: ComponentFixture<AdminTodayNumbersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminTodayNumbersComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTodayNumbersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

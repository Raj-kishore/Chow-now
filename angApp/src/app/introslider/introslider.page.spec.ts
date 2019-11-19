import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { IntrosliderPage } from './introslider.page';

describe('IntrosliderPage', () => {
  let component: IntrosliderPage;
  let fixture: ComponentFixture<IntrosliderPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IntrosliderPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(IntrosliderPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { IntrosliderPageRoutingModule } from './introslider-routing.module';

import { IntrosliderPage } from './introslider.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    IntrosliderPageRoutingModule
  ],
  declarations: [IntrosliderPage]
})
export class IntrosliderPageModule {}

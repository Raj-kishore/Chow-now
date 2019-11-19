import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IntrosliderPage } from './introslider.page';

const routes: Routes = [
  {
    path: '',
    component: IntrosliderPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class IntrosliderPageRoutingModule {}

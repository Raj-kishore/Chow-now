import { Component, OnInit } from '@angular/core';

import { NavController } from '@ionic/angular';
@Component({
  selector: 'app-introslider',
  templateUrl: './introslider.page.html',
  styleUrls: ['./introslider.page.scss'],
})
export class IntrosliderPage implements OnInit {

  constructor(private navCtrl: NavController) { }

  ngOnInit() {
  }

  goChownow(){
    this.navCtrl.navigateForward("chownow/tabs/tab1");
  }

}

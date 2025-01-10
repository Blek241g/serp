import { Component } from '@angular/core';
import {RouterModule} from '@angular/router';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';

@Component({
  selector: 'app-full',
  standalone: false,

  templateUrl: './full.component.html',
  styleUrls:[
    './full.component.scss',
    './styles/sidebar.scss',
    './styles/toolbar.scss',
  ],
})
export class FullComponent {
  isMobileDevice : boolean =  false;

  constructor(private responsive:BreakpointObserver) {
  }

  ngOnInit() {
    this.responsive.observe(Breakpoints.XSmall).subscribe(result => {
      this.isMobileDevice = false;
      console.log("break points full", result.breakpoints);
      if(result.matches){
        this.isMobileDevice = true;
        console.log("break points full", result.breakpoints);
      }
    });
  }
}

import {Component, OnInit, signal} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-welcome',
  standalone: false,

  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.scss'
})
export class WelcomeComponent implements OnInit{
  constructor(private router:Router, private route:ActivatedRoute) {
  }
  ngOnInit() {
  }

  goTo(path: string) {
    console.log(this.route.toString());
    this.router.navigate([`welcome/${path}`])
  }

}

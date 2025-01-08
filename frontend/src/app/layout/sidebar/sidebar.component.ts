import {Component, signal} from '@angular/core';
import {bootstrapPeople} from '@ng-icons/bootstrap-icons';

@Component({
  selector: 'app-sidebar',
  standalone: false,

  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {

  readonly panelOpenState = signal(false);
}

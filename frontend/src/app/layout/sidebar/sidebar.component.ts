import {Component, signal} from '@angular/core';
import {bootstrapPeople} from '@ng-icons/bootstrap-icons';
import {NAV_ITEMS} from '../../core/models/nav-items';

@Component({
  selector: 'app-sidebar',
  standalone: false,

  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  items = NAV_ITEMS;

  readonly panelOpenState = signal(false);
}

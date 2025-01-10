import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-breadcrumb',
  standalone: false,

  templateUrl: './breadcrumb.component.html',
  styleUrl: './breadcrumb.component.scss'
})
export class BreadcrumbComponent {

  @Output() addEvent: EventEmitter<void> = new EventEmitter();

  onAdd() {
    this.addEvent.emit();
  }
}

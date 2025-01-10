import {Component, Input} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';

@Component({
  selector: 'app-table',
  standalone: false,

  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent<T= any> {
  @Input() dataSource!: DataSource<T>;
  @Input() columnsToDisplay!: string[];
  @Input() itemsSize = 49;
}

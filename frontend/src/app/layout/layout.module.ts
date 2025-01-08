import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FullComponent } from './full/full.component';
import {RouterModule} from '@angular/router';
import {MaterialModule} from '../material';
import {NgIconsModule} from '@ng-icons/core';
import {bootstrapPeople} from '@ng-icons/bootstrap-icons';



@NgModule({
  declarations: [
    FullComponent,
    HeaderComponent,
    SidebarComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule,
    NgIconsModule.withIcons({bootstrapPeople})
  ],
    exports: [
      RouterModule
    ]
})
export class LayoutModule { }

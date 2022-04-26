import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { ImportComponent } from './import/import.component';
import {ImportModule} from './import/import.module';
import {WebSocketService} from './service/web-socket-service.service';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    ImportModule,
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    ImportComponent,

  ],
  providers: [WebSocketService],
  bootstrap: [AppComponent]
})
export class AppModule { }

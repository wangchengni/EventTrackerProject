import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { RouteComponent } from './components/route/route.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { FormsModule } from '@angular/forms';
import { PeakComponent } from './components/peak/peak.component';
import { LiftComponent } from './components/lift/lift.component';
import { RouteService } from './services/route/route.service';
import { RouteDetailComponent } from './components/route-detail/route-detail.component';
import { LiftDetailComponent } from './components/lift-detail/lift-detail.component';
import { PeakDetailComponent } from './components/peak-detail/peak-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RouteComponent,
    NavigationComponent,
    PeakComponent,
    LiftComponent,
    RouteDetailComponent,
    LiftDetailComponent,
    PeakDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
  ],
  providers: [RouteService],
  bootstrap: [AppComponent],
})
export class AppModule {}

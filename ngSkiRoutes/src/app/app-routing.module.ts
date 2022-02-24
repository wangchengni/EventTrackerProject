import { RouteDetailComponent } from './components/route-detail/route-detail.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LiftComponent } from './components/lift/lift.component';
import { PeakComponent } from './components/peak/peak.component';
import { RouteComponent } from './components/route/route.component';
import { LiftDetailComponent } from './components/lift-detail/lift-detail.component';
import { PeakDetailComponent } from './components/peak-detail/peak-detail.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'route', component: RouteComponent },
  { path: 'lift', component: LiftComponent },
  { path: 'peak', component: PeakComponent },
  { path: 'route/:id', component: RouteDetailComponent },
  { path: 'lift/:id', component: LiftDetailComponent },
  { path: 'peak/:id', component: PeakDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}

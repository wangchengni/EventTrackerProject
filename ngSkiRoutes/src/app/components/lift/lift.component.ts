import { RouteService } from 'src/app/services/route/route.service';
import { Lift } from './../../models/lift/lift';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LiftService } from 'src/app/services/lift/lift.service';
import { Peak } from 'src/app/models/peak/peak';
import { Route } from 'src/app/models/route/route';

@Component({
  selector: 'app-lift',
  templateUrl: './lift.component.html',
  styleUrls: ['./lift.component.css'],
})
export class LiftComponent implements OnInit {
  title = 'LiftsNum';
  lifts: Lift[] = [];
  selected: Lift | null = null;
  newLift: Lift = new Lift();
  editLift: Lift | null = null;
  peak: Peak = new Peak();
  peakId: number = 1;
  routes: Route[] = [];
  show: Route | null = null;
  showAddFunction = 0;
  constructor(
    private liftSev: LiftService,
    private router: Router,
    private routeSev: RouteService
  ) {}

  ngOnInit(): void {
    this.reload();
  }
  reload() {
    this.liftSev.index().subscribe({
      next: (lifts) => {
        this.lifts = lifts;
      },
      error: (err) => {
        console.error('LiftComponent(): Error retireving lifts');
        console.error(err);
      },
    });
  }
  getRoutesByLiftId(liftId: number) {
    this.routeSev.GetByLiftId(liftId).subscribe({
      next: (selectedRoutes) => {
        this.routes = selectedRoutes;
      },
      error: (err) => {
        console.error('LiftComponent(): Error retireving lifts');
        console.error(err);
      },
    });
  }
  addLift(lift: Lift, peakId: number) {
    this.liftSev.create(peakId, lift).subscribe({
      next: (t: any) => {
        this.newLift = new Lift();
        this.reload();
      },
      error: (err: any) => {
        console.error('Error creating lift');
        console.error(err);
      },
    });
  }
  updateLift(lift: Lift, gotoDetails = true) {
    this.liftSev.update(lift).subscribe({
      next: (t) => {
        this.editLift = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.reload();
      },
      error: (err) => {
        console.error('LiftComponent.updateLift(): Error update lift');
        console.error(err);
      },
    });
  }

  setLift() {
    this.editLift = Object.assign({}, this.selected);
  }
  deleteLift(liftId: number) {
    this.liftSev.destroy(liftId).subscribe({
      next: () => {
        this.reload();
      },
      error: (err) => {
        console.error('LiftComponent.deleteLift(): Error deleting lift');
        console.error(err);
      },
    });
  }
  displayTable() {
    this.selected = null;
  }
  displayLift(lift: Lift) {
    this.selected = lift;
    // this.routes= this.selected.routes
  }
  getNumLifts() {
    return this.lifts.length;
  }
}

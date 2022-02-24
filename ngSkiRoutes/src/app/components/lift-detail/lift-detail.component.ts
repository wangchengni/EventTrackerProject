import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Lift } from 'src/app/models/lift/lift';
import { LiftService } from 'src/app/services/lift/lift.service';
import { Location } from '@angular/common';
import { Route } from 'src/app/models/route/route';
import { RouteService } from 'src/app/services/route/route.service';

@Component({
  selector: 'app-lift-detail',
  templateUrl: './lift-detail.component.html',
  styleUrls: ['./lift-detail.component.css'],
})
export class LiftDetailComponent implements OnInit {
  selected: Lift | null = null;
  editLift: Lift | null = null;
  show: Route | null = null;
  routes: Route[] = [];
  constructor(
    private liftSev: LiftService,
    private route: ActivatedRoute,
    private location: Location,
    private routeSev: RouteService
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (idString) {
      let id = Number.parseInt(idString);
      this.liftIdIndex(id);
    }
  }
  liftIdIndex(liftId: number) {
    this.liftSev.GetByLiftId(liftId).subscribe({
      next: (route) => {
        this.selected = route;
      },
      error: (err) => {
        console.error('RouteComponent(): Error retireving Route');
        console.error(err);
      },
    });
  }
  setLift() {
    this.editLift = Object.assign({}, this.selected);
  }
  displayTable() {
    this.selected = null;
  }
  updateLift(lift: Lift, gotoDetails = true) {
    this.liftSev.update(lift).subscribe({
      next: (t) => {
        this.editLift = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.liftIdIndex(lift.id);
      },
      error: (err) => {
        console.error('LiftDeatilComponent.updateLift(): Error update Lift');
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
  backClicked() {
    this.location.back();
  }
}

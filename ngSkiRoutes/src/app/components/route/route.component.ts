import { Component, Input, OnInit } from '@angular/core';
import { Route } from 'src/app/models/route/route';
import { Router } from '@angular/router';
import { RouteService } from 'src/app/services/route/route.service';
import { Lift } from 'src/app/models/lift/lift';

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css'],
})
export class RouteComponent implements OnInit {
  title = 'RoutesNum';
  // routes: Route[] = [];

  @Input() routes: Route[] = [];
  @Input() lift: Lift = new Lift();
  selected: Route | null = null;
  newRoute: Route = new Route();
  editRoute: Route | null = null;
  liftId: number = 0;

  constructor(private routeSev: RouteService, private router: Router) {}

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.routeSev.index().subscribe({
      next: (routes) => {
        this.routes = routes;
      },
      error: (err) => {
        console.error('RouteComponent(): Error retireving Routes');
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
  addRoute(route: Route, liftId: number) {
    this.routeSev.create(route, liftId).subscribe({
      next: (t) => {
        this.newRoute = new Route();
        this.reload();
      },
      error: (err) => {
        console.error('Error creating route');
        console.error(err);
      },
    });
  }
  updateRoute(route: Route, gotoDetails = true) {
    this.routeSev.update(route).subscribe({
      next: (t) => {
        this.editRoute = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.reload();
      },
      error: (err) => {
        console.error('RouteComponent.updateRoute(): Error update Route');
        console.error(err);
      },
    });
  }

  setRoute() {
    this.editRoute = Object.assign({}, this.selected);
  }
  deleteRoute(routeId: number) {
    this.routeSev.destroy(routeId).subscribe({
      next: () => {
        this.reload();
      },
      error: (err) => {
        console.error('RouteComponent.deleteRoute(): Error deleting Route');
        console.error(err);
      },
    });
  }
  displayTable() {
    this.selected = null;
  }
  displayRoute(route: Route) {
    this.selected = route;
  }
  getNumRoutes() {
    return this.routes.length;
  }
}

import { RouteService } from './../../services/route.service';
import { Component, OnInit } from '@angular/core';
import { Route } from 'src/app/models/route';
import { Router } from '@angular/router';

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css'],
})
export class RouteComponent implements OnInit {
  title = 'RoutesNum';
  routes: Route[] = [];
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

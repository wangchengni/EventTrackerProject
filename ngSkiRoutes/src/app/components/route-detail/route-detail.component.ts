import { RouteService } from 'src/app/services/route/route.service';
import { Component, OnInit } from '@angular/core';
import { Route } from 'src/app/models/route/route';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-route-detail',
  templateUrl: './route-detail.component.html',
  styleUrls: ['./route-detail.component.css'],
})
export class RouteDetailComponent implements OnInit {
  selected: Route | null = null;
  editRoute: Route | null = null;

  constructor(
    private routeSev: RouteService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (idString) {
      let id = Number.parseInt(idString);
      this.routeIdIndex(id);
    }
  }
  routeIdIndex(routeId: number) {
    this.routeSev.GetByRouteId(routeId).subscribe({
      next: (route) => {
        this.selected = route;
      },
      error: (err) => {
        console.error('RouteDeatilComponent(): Error retireving Route');
        console.error(err);
      },
    });
  }
  setRoute() {
    this.editRoute = Object.assign({}, this.selected);
  }
  displayTable() {
    this.selected = null;
  }
  updateRoute(route: Route, gotoDetails = true) {
    this.routeSev.update(route).subscribe({
      next: (t) => {
        this.editRoute = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.routeIdIndex(route.id);
      },
      error: (err) => {
        console.error('RouteComponent.updateRoute(): Error update Route');
        console.error(err);
      },
    });
  }
  backClicked() {
    this.location.back();
  }
}

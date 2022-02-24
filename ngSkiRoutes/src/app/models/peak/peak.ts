import { Route } from '@angular/router';
import { Lift } from '../lift/lift';

export class Peak {
  id: number;
  name: string | undefined;
  elevation: string | undefined;
  routes: Route[];
  lifts: Lift[];
  constructor(
    id: number = 0,
    name?: string,
    elevation?: string,
    routes: Route[] = [],
    lifts: Lift[] = []
  ) {
    this.id = id;
    this.name = name;
    this.elevation = elevation;
    this.routes = routes;
    this.lifts = lifts;
  }
}

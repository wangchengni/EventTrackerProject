import { Route } from '@angular/router';
import { Peak } from '../peak/peak';

export class Lift {
  id: number;
  name: string | undefined;
  runTime: string | undefined;
  carrierNumber: number | undefined;
  peak: Peak;
  routes: Route[];
  constructor(
    id: number = 0,
    name?: string,
    runTime?: string,
    carrierNumber?: number,
    peak: Peak = new Peak(),
    routes: Route[] = []
  ) {
    this.id = id;
    this.name = name;
    this.runTime = runTime;
    this.carrierNumber = carrierNumber;
    this.peak = peak;
    this.routes = routes;
  }
}

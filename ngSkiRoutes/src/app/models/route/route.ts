import { Lift } from '../lift/lift';
import { Peak } from '../peak/peak';

export class Route {
  id: number;
  name: string | undefined;
  distance: number | undefined;
  level: string | undefined;
  snowCondition: string | undefined;
  peak: Peak;
  lift: Lift;
  constructor(
    id: number = 0,
    name?: string,
    distance?: number,
    level?: string,
    snowCondition?: string,
    peak: Peak = new Peak(),
    lift: Lift = new Lift()
  ) {
    this.id = id;
    this.name = name;
    this.distance = distance;
    this.level = level;
    this.snowCondition = snowCondition;
    this.lift = lift;
    this.peak = peak;
  }
}

export class Route {
  id: number ;
  name:string|undefined;
  distance:number|undefined;
  level:string|undefined;
  snowCondition:string|undefined;
  constructor(
    id:number =0,
    name?:string,
    distance?:number,
    level?:string,
    snowCondition?:string
    ){
      this.id =id;
      this.name=name;
      this.distance=distance;
      this.level=level;
      this.snowCondition=snowCondition;
    }
}

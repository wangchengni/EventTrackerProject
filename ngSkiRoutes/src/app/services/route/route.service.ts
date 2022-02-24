import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Route } from '../../models/route/route';

@Injectable({
  providedIn: 'root',
})
export class RouteService {
  // private baseUrl = 'http://localhost:8083/';
  // private url = this.baseUrl + 'api/routes';
  // private url2 = this.baseUrl + 'api/lifts';
  private url = environment.baseUrl + 'api/routes';
  private url2 = environment.baseUrl + 'api/lifts';
  constructor(private http: HttpClient) {}

  //index() show all lists on the main page
  index(): Observable<Route[]> {
    return this.http.get<Route[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => 'RouteService.index():error retrieving routes: ' + err
        );
      })
    );
  }
  GetByRouteId(routeId: number): Observable<Route> {
    return (
      this.http
        // .get<Market>(this.url + '/' + marketId, this.getHttpOptions())
        .get<Route>(this.url + '/' + routeId)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
                'RouteService.GetByRouteId(): error retrieving route :' + err
            );
          })
        )
    );
  }
  GetByLiftId(liftId: number): Observable<Route[]> {
    return (
      this.http
        // .get<Market>(this.url + '/' + marketId, this.getHttpOptions())
        .get<Route[]>(this.url + '/' + 'lift' + '/' + liftId)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
                'RouteService.GetByLiftId(): error retrieving routes :' + err
            );
          })
        )
    );
  }
  create(route: Route, liftId: number): Observable<Route> {
    console.log(route);

    return this.http
      .post<Route>(this.url2 + '/' + liftId + '/' + 'routes', route)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => 'RouteService.create():error creating routes: ' + err
          );
        })
      );
  }
  update(route: Route): Observable<Route> {
    return this.http.put<Route>(this.url + '/' + route.id, route).pipe(
      catchError((problem: any) => {
        console.error('RouteService.update(): error update route');
        console.error(problem);
        return throwError(
          () => new Error('RouteService.update(): error update route')
        );
      })
    );
  }
  destroy(routeId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + routeId).pipe(
      catchError((problem: any) => {
        console.error('RouteService.destroy(): error deleting route');
        console.error(problem);
        return throwError(
          () => new Error('RouteService.destroy(): error deleting route')
        );
      })
    );
  }
  show(routeId: number): Observable<Route> {
    return this.http.get<Route>(this.url + '/' + routeId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => 'RouteService.show(): error retrieving route:' + err
        );
      })
    );
  }
}

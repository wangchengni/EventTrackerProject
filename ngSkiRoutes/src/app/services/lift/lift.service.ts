import { Lift } from './../../models/lift/lift';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LiftService {
  private url = environment.baseUrl + 'api/lifts';

  constructor(private http: HttpClient) {}
  index(): Observable<Lift[]> {
    return this.http.get<Lift[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => 'LiftService.index():error retrieving Lift: ' + err
        );
      })
    );
  }
  GetByLiftId(liftId: number): Observable<Lift> {
    return (
      this.http
        // .get<Market>(this.url + '/' + marketId, this.getHttpOptions())
        .get<Lift>(this.url + '/' + liftId)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => 'LiftService.GetByLiftId(): error retrieving lift :' + err
            );
          })
        )
    );
  }
  GetByPeakId(peakId: number): Observable<Lift[]> {
    return (
      this.http
        // .get<Market>(this.url + '/' + marketId, this.getHttpOptions())
        .get<Lift[]>(this.url + '/' + 'peak' + '/' + peakId)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => 'LiftService.GetByPeakId(): error retrieving lists :' + err
            );
          })
        )
    );
  }
  create(peakId: number, lift: Lift): Observable<Lift> {
    console.log(lift);
    return this.http
      .post<Lift>(this.url + '/' + peakId + '/' + 'lift', lift)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => 'LiftService.create():error creating Lift: ' + err
          );
        })
      );
  }
  update(lift: Lift): Observable<Lift> {
    return this.http.put<Lift>(this.url + '/' + lift.id, lift).pipe(
      catchError((problem: any) => {
        console.error('RLiftService.update(): error update Lift');
        console.error(problem);
        return throwError(
          () => new Error('LiftService.update(): error update Lift')
        );
      })
    );
  }
  destroy(liftId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + liftId).pipe(
      catchError((problem: any) => {
        console.error('LiftService.destroy(): error deleting Lift');
        console.error(problem);
        return throwError(
          () => new Error('LiftService.destroy(): error deleting Lift')
        );
      })
    );
  }
}

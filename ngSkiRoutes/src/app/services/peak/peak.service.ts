import { Peak } from './../../models/peak/peak';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PeakService {
  private url = environment.baseUrl + 'api/peaks';
  // private url2 = environment.baseUrl + 'api/lifts';
  constructor(private http: HttpClient) {}
  index(): Observable<Peak[]> {
    return this.http.get<Peak[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => 'PeakService.index():error retrieving peaks: ' + err
        );
      })
    );
  }
  getByPeakId(peakId: number): Observable<Peak> {
    return (
      this.http
        // .get<Market>(this.url + '/' + marketId, this.getHttpOptions())
        .get<Peak>(this.url + '/' + peakId)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => 'PeakService.GetByPeakId(): error retrieving peak :' + err
            );
          })
        )
    );
  }
  create(peak: Peak): Observable<Peak> {
    console.log(peak);
    return this.http.post<Peak>(this.url + '/' + 'peak', peak).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => 'PeakService.create():error creating peaks: ' + err
        );
      })
    );
  }
  update(peak: Peak): Observable<Peak> {
    return this.http.put<Peak>(this.url + '/' + peak.id, peak).pipe(
      catchError((problem: any) => {
        console.error('PeakService.update(): error update peak');
        console.error(problem);
        return throwError(
          () => new Error('PeakService.update(): error update peak')
        );
      })
    );
  }
  destroy(peakId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + peakId).pipe(
      catchError((problem: any) => {
        console.error('PeakService.destroy(): error deleting peak');
        console.error(problem);
        return throwError(
          () => new Error('PeakService.destroy(): error deleting peak')
        );
      })
    );
  }
}

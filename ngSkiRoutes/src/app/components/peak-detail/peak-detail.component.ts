import { LiftService } from 'src/app/services/lift/lift.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Peak } from 'src/app/models/peak/peak';
import { PeakService } from 'src/app/services/peak/peak.service';
import { Location } from '@angular/common';
import { Lift } from 'src/app/models/lift/lift';

@Component({
  selector: 'app-peak-detail',
  templateUrl: './peak-detail.component.html',
  styleUrls: ['./peak-detail.component.css'],
})
export class PeakDetailComponent implements OnInit {
  selected: Peak | null = null;
  editPeak: Peak | null = null;
  lifts: Lift[] = [];
  show: Lift | null = null;
  constructor(
    private peakSev: PeakService,
    private liftSev: LiftService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (idString) {
      let id = Number.parseInt(idString);
      this.peakIdIndex(id);
    }
  }

  peakIdIndex(peakId: number) {
    this.peakSev.getByPeakId(peakId).subscribe({
      next: (peak) => {
        this.selected = peak;
      },
      error: (err) => {
        console.error('PeakDeatilComponent(): Error retireving peak');
        console.error(err);
      },
    });
  }
  getLiftsByPeakId(peakId: number) {
    this.liftSev.GetByPeakId(peakId).subscribe({
      next: (selectedRoutes) => {
        this.lifts = selectedRoutes;
      },
      error: (err) => {
        console.error('LiftComponent(): Error retireving lifts');
        console.error(err);
      },
    });
  }
  setPeak() {
    this.editPeak = Object.assign({}, this.selected);
  }
  displayTable() {
    this.selected = null;
  }
  updatePeak(peak: Peak, gotoDetails = true) {
    this.peakSev.update(peak).subscribe({
      next: (t) => {
        this.editPeak = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.peakIdIndex(peak.id);
      },
      error: (err) => {
        console.error('PeakComponent.updatePeak(): Error update peak');
        console.error(err);
      },
    });
  }
  backClicked() {
    this.location.back();
  }
}

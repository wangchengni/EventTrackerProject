import { PeakService } from './../../services/peak/peak.service';
import { Peak } from './../../models/peak/peak';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-peak',
  templateUrl: './peak.component.html',
  styleUrls: ['./peak.component.css'],
})
export class PeakComponent implements OnInit {
  title = 'PeaksNum';
  peaks: Peak[] = [];
  selected: Peak | null = null;
  newPeak: Peak = new Peak();
  editPeak: Peak | null = null;
  constructor(private peakSev: PeakService, private router: Router) {}

  ngOnInit(): void {
    this.reload();
  }
  reload() {
    this.peakSev.index().subscribe({
      next: (peaks) => {
        this.peaks = peaks;
      },
      error: (err) => {
        console.error('PeakComponent(): Error retireving peaks');
        console.error(err);
      },
    });
  }
  addPeak(peak: Peak) {
    this.peakSev.create(peak).subscribe({
      next: (t: any) => {
        this.newPeak = new Peak();
        this.reload();
      },
      error: (err: any) => {
        console.error('Error creating lift');
        console.error(err);
      },
    });
  }
  updatePeak(peak: Peak, gotoDetails = true) {
    this.peakSev.update(peak).subscribe({
      next: (t) => {
        this.editPeak = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.reload();
      },
      error: (err) => {
        console.error('PeakComponent.updatePeak(): Error update peak');
        console.error(err);
      },
    });
  }

  setPeak() {
    this.editPeak = Object.assign({}, this.selected);
  }
  deletePeak(peakId: number) {
    this.peakSev.destroy(peakId).subscribe({
      next: () => {
        this.reload();
      },
      error: (err) => {
        console.error('PeakComponent.deletePeak(): Error deleting Peak');
        console.error(err);
      },
    });
  }
  displayTable() {
    this.selected = null;
  }
  displayPeak(peak: Peak) {
    this.selected = peak;
  }
  getNumPeaks() {
    return this.peaks.length;
  }
}

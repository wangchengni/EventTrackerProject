import { Component, OnInit } from '@angular/core';
import { RouteService } from 'src/app/services/route/route.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private routeSev: RouteService) {}

  ngOnInit(): void {}
}

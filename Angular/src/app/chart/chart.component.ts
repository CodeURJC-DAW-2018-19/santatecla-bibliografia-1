import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { Chart, ChartService } from './chart.service';

@Component({
    selector: 'chart',
    templateUrl: './chart.component.html',
})

export class ChartComponent implements OnInit {

    chart: Chart;
    themes = {};

    constructor(private service: ChartService){}

    ngOnInit(){
        this.service.getChart().subscribe(
            chart => {
                this.chart = chart
                this.showThemes()
            },
            error => console.error(error)
        );
    }

    showThemes(){
        let nThemes = this.chart.themes.length;
        let names = [];
        let values = [];

        for(var i=0; i<nThemes; i++){
            names.push(this.chart.themes[i].name);
            values.push(this.chart.numBooks[i]);
        }

        this.themes = {names: names, values: values};
        console.log(this.themes)
    }

}
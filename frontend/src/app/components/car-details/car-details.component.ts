import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CarService } from '../../services/car.service';
import { ServiceRecordService } from '../../services/service-record.service';
import { Car, ServiceRecord } from '../../models/models';
import { AddServiceModalComponent } from '../add-service-modal/add-service-modal.component';

@Component({
    selector: 'app-car-details',
    templateUrl: './car-details.component.html',
    styleUrls: ['./car-details.component.scss']
})
export class CarDetailsComponent implements OnInit {
    car: Car | null = null;
    serviceRecords: ServiceRecord[] = [];
    loading = true;
    carId!: number;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private carService: CarService,
        private serviceRecordService: ServiceRecordService,
        private dialog: MatDialog,
        private snackBar: MatSnackBar
    ) {}

    ngOnInit(): void {
        this.carId = Number(this.route.snapshot.paramMap.get('id'));
        this.loadData();
    }

    loadData(): void {
        this.loading = true;
        this.carService.getCarById(this.carId).subscribe({
            next: (car) => {
                this.car = car;
                this.loadServiceRecords();
            },
            error: () => { this.router.navigate(['/dashboard']); }
        });
    }

    loadServiceRecords(): void {
        this.serviceRecordService.getServiceRecords(this.carId).subscribe({
            next: (records) => { this.serviceRecords = records; this.loading = false; },
            error: () => { this.loading = false; }
        });
    }

    openAddServiceDialog(): void {
        const dialogRef = this.dialog.open(AddServiceModalComponent, {
            width: '600px',
            data: { carId: this.carId }
        });
        dialogRef.afterClosed().subscribe(result => { if (result) this.loadServiceRecords(); });
    }

    deleteRecord(record: ServiceRecord): void {
        if (confirm('Biztosan törlöd ezt a szervízbejegyzést?')) {
            this.serviceRecordService.deleteServiceRecord(this.carId, record.id).subscribe({
                next: () => { this.snackBar.open('Bejegyzés törölve!', 'OK', { duration: 2000 }); this.loadServiceRecords(); },
                error: () => this.snackBar.open('Hiba a törlés során!', 'OK', { duration: 3000 })
            });
        }
    }

    goBack(): void { this.router.navigate(['/dashboard']); }

    openImage(url: string): void { window.open(url, '_blank'); }
}
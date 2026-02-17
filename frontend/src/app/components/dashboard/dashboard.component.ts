import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CarService } from '../../services/car.service';
import { Car } from '../../models/models';
import { AddCarModalComponent } from '../add-car-modal/add-car-modal.component';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
    cars: Car[] = [];
    loading = true;
    userName = '';

    get totalServiceRecords(): number {
        return this.cars.reduce((sum, car) => sum + car.serviceRecordCount, 0);
    }

    get maxMileage(): number {
        return Math.max(...this.cars.map(c => c.currentMileage || 0), 0);
    }

    constructor(
        private carService: CarService,
        private authService: AuthService,
        private dialog: MatDialog,
        private snackBar: MatSnackBar,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.userName = this.authService.getCurrentUser()?.name || '';
        this.loadCars();
    }

    loadCars(): void {
        this.loading = true;
        this.carService.getUserCars().subscribe({
            next: (cars) => { this.cars = cars; this.loading = false; },
            error: () => { this.loading = false; this.snackBar.open('Hiba az autók betöltésénél!', 'OK', { duration: 3000 }); }
        });
    }

    openAddCarDialog(): void {
        const dialogRef = this.dialog.open(AddCarModalComponent, { width: '520px' });
        dialogRef.afterClosed().subscribe(result => { if (result) this.loadCars(); });
    }

    openCarDetails(car: Car): void {
        this.router.navigate(['/cars', car.id]);
    }

    deleteCar(car: Car, event: Event): void {
        event.stopPropagation();
        if (confirm(`Biztosan törlöd a(z) ${car.brand} ${car.model} autót?`)) {
            this.carService.deleteCar(car.id).subscribe({
                next: () => { this.snackBar.open('Autó törölve!', 'OK', { duration: 2000 }); this.loadCars(); },
                error: () => this.snackBar.open('Hiba a törlés során!', 'OK', { duration: 3000 })
            });
        }
    }

    logout(): void { this.authService.logout(); }
}
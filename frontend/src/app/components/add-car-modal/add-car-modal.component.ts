import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-add-car-modal',
  templateUrl: './add-car-modal.component.html'
})
export class AddCarModalComponent {
  carForm: FormGroup;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private carService: CarService,
    private dialogRef: MatDialogRef<AddCarModalComponent>,
    private snackBar: MatSnackBar
  ) {
    this.carForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      year: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear() + 1)]],
      licensePlate: [''],
      vin: ['', [Validators.maxLength(17)]],
      currentMileage: ['', [Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.carForm.invalid) return;
    this.loading = true;
    const request = { ...this.carForm.value, year: Number(this.carForm.value.year) };
    if (!request.currentMileage) delete request.currentMileage;
    if (!request.vin) delete request.vin;
    if (!request.licensePlate) delete request.licensePlate;

    this.carService.createCar(request).subscribe({
      next: () => {
        this.snackBar.open('Autó sikeresen hozzáadva!', 'OK', { duration: 2000 });
        this.dialogRef.close(true);
      },
      error: (err) => {
        this.loading = false;
        this.snackBar.open(err.error?.message || 'Failed to add car', 'OK', { duration: 3000 });
      }
    });
  }

  onCancel(): void { this.dialogRef.close(false); }
}

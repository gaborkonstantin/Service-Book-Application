import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceRecordService } from '../../services/service-record.service';

@Component({
  selector: 'app-add-service-modal',
  templateUrl: './add-service-modal.component.html'
})
export class AddServiceModalComponent {
  serviceForm: FormGroup;
  loading = false;

  serviceTypes = [
    'Olajcsere', 'Szűrőcsere', 'Fékbetét csere', 'Gumicsere', 'Műszaki vizsga',
    'Éves karbantartás', 'Akkumulátor csere', 'Egyéb'
  ];

  constructor(
    private fb: FormBuilder,
    private serviceRecordService: ServiceRecordService,
    private dialogRef: MatDialogRef<AddServiceModalComponent>,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: { carId: number }
  ) {
    this.serviceForm = this.fb.group({
      serviceDate: [new Date(), Validators.required],
      serviceType: ['', Validators.required],
      description: [''],
      mileage: ['', Validators.min(0)],
      cost: ['', Validators.min(0)],
      serviceProvider: ['']
    });
  }

  onSubmit(): void {
    if (this.serviceForm.invalid) return;
    this.loading = true;

    const value = this.serviceForm.value;
    const request: any = {
      serviceDate: value.serviceDate instanceof Date
        ? value.serviceDate.toISOString().split('T')[0]
        : value.serviceDate,
      serviceType: value.serviceType
    };
    if (value.description) request.description = value.description;
    if (value.mileage) request.mileage = Number(value.mileage);
    if (value.cost) request.cost = Number(value.cost);
    if (value.serviceProvider) request.serviceProvider = value.serviceProvider;

    this.serviceRecordService.createServiceRecord(this.data.carId, request).subscribe({
      next: () => {
        this.snackBar.open('Szervízbejegyzés hozzáadva!', 'OK', { duration: 2000 });
        this.dialogRef.close(true);
      },
      error: (err) => {
        this.loading = false;
        this.snackBar.open(err.error?.message || 'Hiba a mentés során!', 'OK', { duration: 3000 });
      }
    });
  }

  onCancel(): void { this.dialogRef.close(false); }
}

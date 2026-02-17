import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceRecord, CreateServiceRecordRequest } from '../models/models';

@Injectable({ providedIn: 'root' })
export class ServiceRecordService {
  private readonly API_URL = 'http://localhost:8080/api/cars';

  constructor(private http: HttpClient) {}

  getServiceRecords(carId: number): Observable<ServiceRecord[]> {
    return this.http.get<ServiceRecord[]>(`${this.API_URL}/${carId}/records`);
  }

  createServiceRecord(carId: number, request: CreateServiceRecordRequest): Observable<ServiceRecord> {
    return this.http.post<ServiceRecord>(`${this.API_URL}/${carId}/records`, request);
  }

  deleteServiceRecord(carId: number, recordId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${carId}/records/${recordId}`);
  }
}

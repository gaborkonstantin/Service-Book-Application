import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car, CreateCarRequest } from '../models/models';

@Injectable({ providedIn: 'root' })
export class CarService {
  private readonly API_URL = 'http://localhost:8080/api/cars';

  constructor(private http: HttpClient) {}

  getUserCars(): Observable<Car[]> { return this.http.get<Car[]>(this.API_URL); }
  getCarById(id: number): Observable<Car> { return this.http.get<Car>(`${this.API_URL}/${id}`); }
  createCar(request: CreateCarRequest): Observable<Car> { return this.http.post<Car>(this.API_URL, request); }
  updateCar(id: number, request: Partial<CreateCarRequest>): Observable<Car> { return this.http.put<Car>(`${this.API_URL}/${id}`, request); }
  deleteCar(id: number): Observable<void> { return this.http.delete<void>(`${this.API_URL}/${id}`); }
}

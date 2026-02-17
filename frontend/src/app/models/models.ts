export interface User {
  id: number;
  email: string;
  name: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  id: number;
  email: string;
  name: string;
}

export interface Car {
  id: number;
  brand: string;
  model: string;
  year: number;
  licensePlate?: string;
  vin?: string;
  currentMileage?: number;
  createdAt: string;
  serviceRecordCount: number;
}

export interface CreateCarRequest {
  brand: string;
  model: string;
  year: number;
  licensePlate?: string;
  vin?: string;
  currentMileage?: number;
}

export interface ServiceRecord {
  id: number;
  serviceDate: string;
  serviceType: string;
  description?: string;
  mileage?: number;
  cost?: number;
  serviceProvider?: string;
  createdAt: string;
  images: ServiceImage[];
}

export interface CreateServiceRecordRequest {
  serviceDate: string;
  serviceType: string;
  description?: string;
  mileage?: number;
  cost?: number;
  serviceProvider?: string;
}

export interface ServiceImage {
  id: number;
  imageUrl: string;
  fileName?: string;
  uploadedAt: string;
}

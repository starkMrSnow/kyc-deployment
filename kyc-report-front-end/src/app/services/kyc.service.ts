import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CustomerDto {
  customerId: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  employmentStatus: string;
  selfieImageUrl: string;
  frontPhotoIdUrl: string;
  backPhotoIdUrl: string;
  dateOfBirth: string;
  county: string;
}

@Injectable()
export class KycService {
  private baseUrl = 'https://mevin.online/';//base url for the backend

  constructor(private http: HttpClient) {}

  //fetch full details
  getAllCustomers(): Observable<CustomerDto[]> {
    return this.http.get<CustomerDto[]>(`${this.baseUrl}admin/customers`);
  }

  //fetch id and name
  getCustomerIdsAndNames(): Observable<{ id: number; name: string }[]> {
    return this.http.get<{ id: number; name: string }[]>(`${this.baseUrl}admin/IdName`);
  }

  ///fetch detailed customer details
  getCustomerDetails(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}admin/details`);
  }

  //fetch selfie url
  getCustomerSelfies(): Observable<{ selfie: string }[]> {
    return this.http.get<{ selfie: string }[]>(`${this.baseUrl}admin/selfie`);
  }

  //download pdf
  exportCustomerToPdf(email: string): Observable<Blob> {
    return this.http.get(`${this.baseUrl}admin/export-pdf/${email}`, {
      responseType: 'blob'
    });
  }
  getEmailServiceHealth(service: string): Observable<{ status: string }> {
  return this.http.get<{ status: string }>(`${this.baseUrl}health/email`);
}

getKycServiceHealth(service: string): Observable<{ status: string }> {
  return this.http.get<{ status: string }>(`${this.baseUrl}health/kyc`);
}
 removeCustomerSelfie(email: string): Observable<string>{
  return this.http.delete(`${this.baseUrl}/rmSelfie/${email}`,{
    responseType: 'text'
  });
 }

}

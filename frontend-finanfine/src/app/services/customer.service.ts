import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ICustomer } from '../interfaces/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  api = "http://localhost:8080/api/v1"
  endpoint = "clientes";
  
  constructor(private http: HttpClient) { }

  register(customer: ICustomer) {
    return this.http.post(`${this.api}/${this.endpoint}`, customer)
  }

  findAllCustomers() {
    return this.http.get<ICustomer[]>(`${this.api}/${this.endpoint}`);
  }

  findByCpf(cpf: string) {
    return this.http.get<ICustomer>(`${this.api}/${this.endpoint}/${cpf}`);
  }

  update(customer: ICustomer, cpf: string) {
    return this.http.put<ICustomer>(`${this.api}/${this.endpoint}/${cpf}`, customer);
  }

  deleteCustomer(cpf: string) {
    return this.http.delete(`${this.api}/${this.endpoint}/${cpf}`);
  }
}
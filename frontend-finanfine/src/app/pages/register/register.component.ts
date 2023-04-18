import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { ICustomer } from 'src/app/interfaces/customer';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  customerForm = new FormGroup({
    cpf: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
    cep: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    addressNumber: new FormControl(0, Validators.required),
    monthlyIncome: new FormControl(0, Validators.required)
  });


  constructor(private customerService: CustomerService, private router: Router){}

  
    create() {
      const customer: ICustomer = this.customerForm.value as ICustomer;
      this.customerService.register(customer).subscribe((result) => {
        this.customerForm.reset();
        Swal.fire (
          'Cadastrado!',
          'Usuário cadastrado com sucesso',
          'success'
        ).then((reload) => {window.location.reload();});
      }, (error) => {
        Swal.fire('Não foi possível cadastrar', error.error.message, 'error'); return error;
      }); this.router.navigate(['/clientes']); 
    } 
}

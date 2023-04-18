import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ICustomer } from 'src/app/interfaces/customer';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent {
  customerCpf: string = '';
  customerForm = new FormGroup({
    cpf: new FormControl(''),
    name: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
    cep: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    addressNumber: new FormControl(0, Validators.required),
    monthlyIncome: new FormControl(0, Validators.required)
  });

  constructor(private customerService: CustomerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.customerCpf = String(this.route.snapshot.paramMap.get('cpf'));
    this.customerService.findByCpf(this.customerCpf).subscribe((result: ICustomer) => {
      this.customerForm.setValue({
        cpf: this.customerCpf,
        name: result.name,
        phone: result.phone,
        cep: result.cep,
        address: result.address,
        addressNumber: result.addressNumber,
        monthlyIncome: result.monthlyIncome
      });
    }, (error) => {
      Swal.fire('Algo deu errado', error.error.message, 'error');
      return error;
    })
  }

  update() {
    const customer: ICustomer = this.customerForm.value as ICustomer;
    this.customerCpf = String(this.route.snapshot.paramMap.get('cpf'))
    this.customerService.update(customer, this.customerCpf).subscribe((result) => {
      Swal.fire('Atualizado!', 'Usuário foi atualizado com sucesso!', 'success');
      return result;
    }, (error) => {
      Swal.fire('Não foi possível atualizar', error.error.message, 'error');
      return error;
    }); this.router.navigate(['/clientes'])
  }
}
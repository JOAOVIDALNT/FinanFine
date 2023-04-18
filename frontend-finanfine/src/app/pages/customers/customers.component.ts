import { Component } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { ICustomer } from 'src/app/interfaces/customer';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {
  customers: ICustomer[] = [];

  constructor (
    private customerService: CustomerService
  ) {}

  ngOnInit() {
    this.customerService.findAllCustomers().subscribe(result => {this.customers = result;});
  }

  delete(cpf: string) {
    this.customerService.findByCpf(cpf).subscribe(result => {
      Swal.fire({
        title: 'Tem certeza que deseja deletar este cliente?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Deletar',
        denyButtonText: `Cancelar`,
      }).then((result) => {
        if (result.isConfirmed) {
          this.customerService.deleteCustomer(cpf).subscribe(result => {
            Swal.fire('Deletado!', 'Cliente deletado com sucesso!', 'success').then((reload) => {window.location.reload();})
          })
        } else if (result.isDenied) {
          Swal.fire('Cuidado!', 'Não é possível recuperar clientes depois de deleta-los!', 'info')
        }
      })
    }), (error: any) => {
      console.error(error);
    };
  }
}


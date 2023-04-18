import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { RegisterComponent } from './pages/register/register.component';
import { UpdateComponent } from './pages/update/update.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: "clientes", component: CustomersComponent},
  {path: "clientes/cadastrar", component: RegisterComponent},
  {path: "clientes/editar/:cpf", component: UpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

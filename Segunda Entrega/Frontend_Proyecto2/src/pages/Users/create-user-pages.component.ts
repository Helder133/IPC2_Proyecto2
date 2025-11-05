import { Component} from "@angular/core";
import { RouterLink } from "@angular/router";
import { UserFormComponent } from "../../components/users/user-form-component/user-form.component";

@Component({
    selector: 'app-create-user-page',
    imports: [UserFormComponent, RouterLink],
    templateUrl: './create-user-pages.component.html'
})

export class CreateUserComponent {
    
}
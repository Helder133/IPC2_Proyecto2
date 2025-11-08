import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CineResponse } from "../../../models/cine/cineResponse";

@Component({
    selector: 'app-confirmation-modal-cine',
    imports: [],
    templateUrl: './confirmation-modal-user.component.html',
})
export class ConfirmationModalCineComponent {

    @Input()
    selectedCine!: CineResponse;

    @Output()
    confirmationExecuter = new EventEmitter<void>();

    executeConfirmation(): void {
        this.confirmationExecuter.emit();
    }
} 
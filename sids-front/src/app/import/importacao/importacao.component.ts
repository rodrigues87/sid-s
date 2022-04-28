import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PessoaService} from '../../dashboard/services/pessoa.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-importacao',
    templateUrl: './importacao.component.html',
    styleUrls: ['./importacao.component.scss']
})
export class ImportacaoComponent {

    fileName = '';
    public aguardar: boolean;

    constructor(private http: HttpClient,
                private pessoaService: PessoaService,
                private router: Router) {
    }

    onFileSelected(event) {

        const file: File = event.target.files[0];


        this.pessoaService.uploadFileCandidatos(file).subscribe(ress => {
            if (ress !== null) {

                this.router.navigate(['/dashboard'])
            }
        });
    }
}

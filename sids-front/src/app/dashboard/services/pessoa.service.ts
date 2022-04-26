import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import {_server_host_} from '../../Config';
import {Observable} from 'rxjs';


@Injectable({
    providedIn: 'root'
})
export class PessoaService {

    private baseUrl = 'http://' + _server_host_ + '/pessoas';

    constructor(private http: HttpClient) { }




    countAll() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/countAll`, { headers });
    }

    quantidadeDoadoresAptos() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/quantidadeDoadoresAptos`, { headers });
    }




    getQuantidadeCandidatosPorEstado() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/quantidade-candidatos-por-estado`, { headers });
    }

    getImcMedioPorFaixaEtaria() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/imc-medio-por-faixa-etaria`, { headers });
    }

    getPersentualObesosBySexo() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/persentual-obesos-homens-mulheres`, { headers });
    }

    getMediaIdadeByTipoSanguineo() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/media-idade-tipo-sanguineo`, { headers });
    }

    getQuantidadePossiveisDoadores() {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });
        return this.http.get<any>(`${this.baseUrl}/get-quantidade-possiveis-doadores`, { headers });
    }


    uploadFileCandidatos(file: File) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
        });

        const formData: FormData = new FormData();

        formData.append('file', file);

        const req = new HttpRequest('POST', `${this.baseUrl}/uploadFileCandidatos/`, formData, {
            headers,
            reportProgress: true,
            responseType: 'json'
        });

        return this.http.request(req);
    }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoHabitacion } from '../models/tipo-habitacion';

@Injectable({
  providedIn: 'root'
})
export class TipoHabitacionService {
  private apiUrl = 'http://localhost:8080/api/tipos-habitacion';

  constructor(
    private http: HttpClient
  ) { }

  listar(): Observable<TipoHabitacion[]> {
    return this.http.get<TipoHabitacion[]>(this.apiUrl);
  }
}

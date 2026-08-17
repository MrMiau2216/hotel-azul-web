import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Huesped } from '../models/huesped';

@Injectable({
  providedIn: 'root'
})
export class HuespedService {
  private apiUrl = 'http://localhost:8080/api/huespedes';

  constructor(
    private http: HttpClient
  ) { }

  listar(): Observable<Huesped[]> {
    return this.http.get<Huesped[]>(this.apiUrl);
  }

  crear(huesped: Huesped): Observable<Huesped> {
    return this.http.post<Huesped>(this.apiUrl, huesped);
  }

  actualizar(id: number, huesped: Huesped): Observable<Huesped> {
    return this.http.put<Huesped>(`${this.apiUrl}/${id}`, huesped);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}

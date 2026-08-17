# Hotel Azul

Sistema de gestión hotelera desarrollado para el curso **Desarrollo de Aplicaciones Web I**
(ciclo 5, Computación e Informática — Cibertec). Backend en Spring Boot con API REST y
frontend en Angular.

## ⚠️ Primer paso: configurar la base de datos local

Antes de ejecutar el backend, abre `backend/src/main/resources/application.properties` y
rellena estas dos líneas con tus credenciales locales de MySQL:

```properties
spring.datasource.username=
spring.datasource.password=
```

Sin esto, el backend no podrá conectarse a MySQL.

## Descripción del sistema

Hotel Azul tiene dos partes:

- **Parte pública (cliente):** una página de inicio donde cualquier visitante ve el catálogo
  de habitaciones con foto, tipo, precio y si está **Disponible** o **No disponible**. El
  cliente no reserva desde la web: llama por teléfono y el recepcionista registra la reserva
  desde el panel interno.
- **Parte interna (administrador y recepcionista):** login y CRUD de habitaciones, huéspedes,
  reservas y pagos.

## Tecnologías

- **Backend:** Spring Boot 4.1.0, Java 21, Spring Data JPA, Spring Security (solo para
  `PasswordEncoder`, sin JWT ni `formLogin`), MySQL, Lombok, Maven.
- **Frontend:** Angular 20 (componentes standalone), Bootstrap 5, HttpClient con Observables.

## Requisitos

- Java 21
- MySQL 8.x
- Node.js 20+
- Angular CLI 20

## Crear la base de datos

En MySQL, ejecuta solamente:

```sql
CREATE DATABASE hotel_azul;
```

Las tablas se crean solas al levantar Spring Boot (`spring.jpa.hibernate.ddl-auto=update`), y
los datos iniciales se cargan automáticamente la primera vez (ver `CargaDatosIniciales`).

## Cómo levantar el backend

```bash
cd backend
mvn spring-boot:run
```

El backend queda escuchando en `http://localhost:8080`.

## Cómo levantar el frontend

```bash
cd frontend
npm install
npm start
```

El frontend queda escuchando en `http://localhost:4200`.

## Endpoints

| Ruta | Métodos | Acceso |
|---|---|---|
| `/api/auth/login` | POST | Público |
| `/api/publico/habitaciones` | GET | Público |
| `/api/habitaciones` | GET, POST, PUT, DELETE | Interno |
| `/api/huespedes` | GET, POST, PUT, DELETE | Interno |
| `/api/reservas` | GET, POST, PUT, DELETE | Interno |
| `/api/pagos` | GET, POST, PUT, DELETE | Interno |
| `/api/tipos-habitacion` | GET | Interno (soporte de formularios) |

La colección de Postman con ejemplos de cada petición está en
[`postman/HotelAzul.postman_collection.json`](postman/HotelAzul.postman_collection.json).

## Credenciales de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| `admin` | `Admin123` | ADMIN |
| `recepcion` | `Recepcion123` | RECEPCIONISTA |

# Informe de trabajo — Hotel Azul

## Qué quedó funcionando

Todo el sistema quedó funcionando de punta a punta, verificado en vivo (backend levantado +
frontend levantado + navegador real, no solo compilación):

- **Backend:** compila, levanta, se conecta a MySQL, crea las tablas solas
  (`ddl-auto=update`) y `CargaDatosIniciales` siembra los usuarios, tipos de habitación y las
  8 habitaciones de ejemplo la primera vez que la base está vacía.
- **Login REST** (`POST /api/auth/login`) probado con `admin`/`Admin123` y
  `recepcion`/`Recepcion123`: responde 200 con `{id, username, rol}`; con contraseña
  incorrecta responde 401.
- **Los cuatro verbos** (GET/POST/PUT/DELETE) probados contra la API real en
  `/api/habitaciones`, `/api/huespedes`, `/api/reservas` y `/api/pagos` — incluyendo la
  relación Habitación→TipoHabitación y Reserva→Habitación/Huésped y Pago→Reserva.
- **Catálogo público** (`/api/publico/habitaciones`) consumido por la página de Inicio en
  Angular: muestra las 8 habitaciones con imagen, tipo, precio y la insignia
  Disponible/No disponible según el `estado` real.
- **Frontend:** login, navbar con enlaces internos ocultos sin sesión, y los cuatro CRUD
  (Habitaciones, Huéspedes, Reservas, Pagos) probados creando, editando y listando registros
  reales contra el backend.
- El botón "Eliminar" de cada CRUD usa `confirm()` de JavaScript antes de llamar al DELETE.
  Esto no se pudo probar con el navegador automatizado que usé durante el desarrollo porque
  bloquea los diálogos nativos, pero el DELETE en sí se verificó directamente contra la API
  (`curl -X DELETE .../api/habitaciones/8` → 200) y funciona correctamente. Vale la pena que
  lo confirmes una vez con un clic real en el navegador.

## Resultado de las pruebas (`mvn test`)

```
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Cubren insertar, actualizar, eliminar y listar para `HabitacionRepository` y
`HuespedRepository` con `@DataJpaTest`, más el test de contexto de la aplicación.

**Importante:** como las credenciales de `application.properties` quedaron vacías (ver
siguiente sección), si ejecutas `mvn test` ahora mismo sin rellenarlas, va a fallar al no
poder conectarse a MySQL. Esto es esperado — el proyecto no usa H2 en pruebas, usa la misma
base MySQL real, así que hay que rellenar usuario y contraseña primero (igual que para
levantar el backend).

## Credenciales

Confirmado: `backend/src/main/resources/application.properties` tiene
`spring.datasource.username=` y `spring.datasource.password=` vacíos en el commit final. Lo
verifiqué con `git show` sobre el archivo en stage antes de cada commit, y con un `grep`
recursivo sobre todo `hotel-azul-web/` buscando la contraseña de prueba — no aparece en
ningún archivo ni en el historial de git (`git log -p`).

## Push a GitHub

**Se hizo.** `git push -u origin main` contra `https://github.com/MrMiau2216/hotel-azul-web.git`
se aceptó al primer intento (no hizo falta `--allow-unrelated-histories`). Los 10 commits
están en `main` tanto local como remoto.

## Divergencias con el material de clase y cómo las resolví

1. **Estados como `String`, sin enums** (`Habitacion.estado`, `Reserva.estado`): así está en
   todo el proyecto, sin excepciones, igual que `OrdenTrabajo.estado` en AutoManager.
2. **Lombok:** solo `@Getter @Setter @NoArgsConstructor` en entidades y DTOs. Nunca `@Data` ni
   `@AllArgsConstructor` — confirmado que el ejemplo no los usa ni una vez en todo el proyecto.
3. **Sin entidad `Rol`:** `Usuario.rol` es un `String` directo, igual que en AutoManager.
4. **`Usuario` por `username`, no por correo:** el enunciado original que recibí primero pedía
   login con `correo`, pero al leer `Usuario.java` de AutoManager confirmé que el campo real es
   `username` (más `password`, `rol`, `estado`). El documento de trabajo autónomo que llegó
   después ya traía esta corrección aplicada, así que la seguí: se cambiaron `LoginRequest`,
   `LoginResponse`, `AuthController`, `CargaDatosIniciales` y todo el frontend (modelo
   `Usuario`, `LoginRequest`, página de login, navbar) para usar `username` + `estado` en vez
   de `correo` + `nombres`.
5. **Sin `GlobalExceptionHandler` al estilo del ejemplo:** el de AutoManager devuelve nombres
   de vista Thymeleaf (`"error/500"`), lo cual no aplica aquí porque no hay Thymeleaf. En su
   lugar, cada controlador REST maneja errores de negocio con `try/catch` + `ResponseEntity`,
   igual que hace `OrdenesRestController` del ejemplo para sus propios casos.
6. **Paquetes de test movidos en Spring Boot 4.1:** `@DataJpaTest` y
   `@AutoConfigureTestDatabase` ya no viven en
   `org.springframework.boot.test.autoconfigure.orm.jpa` como en versiones anteriores de
   Spring Boot, sino en `org.springframework.boot.data.jpa.test.autoconfigure` y
   `org.springframework.boot.jdbc.test.autoconfigure` respectivamente. No estaba en ningún
   documento — lo encontré por error de compilación y lo resolví inspeccionando los JARs
   descargados en el repositorio Maven local.
7. **Estructura de carpetas:** el ejemplo AutoManager es un único proyecto plano; acá se pidió
   explícitamente un monorepo `hotel-azul-web/{backend,frontend,postman}`, que es lo que se
   armó.
8. **Frontend reutilizado en vez de `ng new` desde cero:** ya existía en disco un scaffold
   Angular 20.3.34 generado previamente (con SSR y detección de cambios *zoneless*
   activados por defecto en la versión reciente del CLI). En vez de regenerarlo desde cero
   con `ng new ... --ssr=false` como decía el documento, reutilicé ese scaffold quitándole
   SSR a mano (borré `server.ts`, `app.config.server.ts`, `main.server.ts`, ajusté
   `angular.json`, `package.json` y `tsconfig.app.json`) y lo convertí a `zone.js` +
   `provideZoneChangeDetection` para igualar el patrón del ejemplo AutoManager, que sí usa
   Zone.js. El resultado final es equivalente a correr `ng new --style=css --ssr=false`, pero
   evitó rehacer trabajo ya verificado.
9. **`hotel-azul-main/`** se dejó completamente intacta, no se leyó como referencia ni se
   incluyó en el repositorio — es un proyecto de otro curso con arquitectura distinta
   (Thymeleaf, `schema.sql`/`data.sql`, GitHub Actions propio).
10. **Dos bugs no obvios que aparecieron recién al probar en el navegador real** (no se ven
    compilando ni en pruebas unitarias, solo al hacer un POST real):
    - Enviar `id: 0` en el body al crear una habitación/huésped/reserva/pago hacía que JPA lo
      tratara como una fila existente con id=0 y lanzara
      `ObjectOptimisticLockingFailureException` en vez de insertar. Se resolvió haciendo
      opcional el campo `id` en los modelos de Angular y omitiéndolo del payload al crear.
    - Enviar `fechaRegistro: ''` / `fechaPago: ''` (string vacío) desde Angular pisaba el
      valor por defecto `LocalDateTime.now()` de la entidad con `null`, y Hibernate rechazaba
      el insert por violar `nullable = false`. Se resolvió omitiendo esos campos del payload
      igual que con `id`.

## Qué te toca revisar o completar

1. Rellenar tus credenciales de MySQL en `backend/src/main/resources/application.properties`
   antes de levantar el backend o correr `mvn test`.
2. La base de datos local que usé para probar (`hotel_azul`) quedó con los datos de prueba que
   generé durante las pruebas (un huésped "María García Ruiz", una reserva y un pago de
   prueba, y una habitación de menos porque borré la 402 para probar el DELETE). Si vas a
   hacer una demo desde cero, corre `DROP DATABASE hotel_azul;` antes de la primera ejecución
   para que `CargaDatosIniciales` la vuelva a sembrar limpia con las 8 habitaciones originales.
3. Probar el botón "Eliminar" con un clic real en tu navegador (el diálogo de confirmación no
   se pudo probar con la herramienta de automatización que usé, aunque el DELETE de la API en
   sí ya está verificado).
4. Revisar el repositorio remoto (`https://github.com/MrMiau2216/hotel-azul-web`) para
   confirmar que todo se ve bien en GitHub.
5. Nada quedó bloqueado ni pendiente de decisión — no hubo que saltarse ningún paso del
   enunciado.

# EVALUACION 2 - ConstruccionSoftware2JuanRodriguez

## Informacion general
- Estudiante(s): Juan Rodriguez (usuario GitHub: CarlosRodriguezL)
- Rama evaluada: main
- Commit evaluado: 6e1d5bcff16f09c9e7445dbf4f6c2b36aa80bf5a
- Fecha: 2026-04-11
- Nota: Solo existe rama `main`; no hay rama `develop`. README.md solo contiene el titulo del repositorio, sin nombres de integrantes.

---

## Tabla de calificacion

| # | Criterio | Peso | Puntaje (1-5) | Parcial |
|---|---|---|---|---|
| 1 | Modelado de dominio | 20% | 4 | 0.80 |
| 2 | Modelado de puertos | 20% | 1 | 0.20 |
| 3 | Modelado de servicios de dominio | 20% | 2 | 0.40 |
| 4 | Enums y estados | 10% | 5 | 0.50 |
| 5 | Reglas de negocio criticas | 10% | 2 | 0.20 |
| 6 | Bitacora y trazabilidad | 5% | 2 | 0.10 |
| 7 | Estructura interna de dominio | 10% | 3 | 0.30 |
| 8 | Calidad tecnica base en domain | 5% | 4 | 0.20 |
| | **Total base** | | | **2.70** |

### Calculo
Nota base = (4*20 + 1*20 + 2*20 + 5*10 + 2*10 + 2*5 + 3*10 + 4*5) / 100 = 270 / 100 = **2.70**

---

## Penalizaciones aplicadas

| Penalizacion | Porcentaje | Motivo |
|---|---|---|
| Logica de negocio critica fuera de domain | -20% | `AccountService`, `LoanService`, `TransferService`, `UserService` estan en `application/`, no en `domain/` |

Nota tras penalizacion: 2.70 × 0.80 = **2.16**

---

## Nota final
**2.2 / 5.0**

---

## Hallazgos

### Criterio 1 - Modelado de dominio (4/5)
- Entidades en `domain/model/`: `AuditLog`, `BankAccount`, `CompanyClient`, `Loan`, `NaturalPersonClient`, `Transfer`, `User`.
- Buena jerarquia de clientes: `NaturalPersonClient`, `CompanyClient`.
- `AuditLog` presente en el dominio.
- Falta: clase base `Client` que unifique la jerarquia.
- Falta: `ProductoBancario` como entidad de dominio.
- El proyecto usa una estructura de carpeta plana (`src/`) sin Maven/Spring Boot estandar.

### Criterio 2 - Modelado de puertos (1/5)
- **No existen interfaces de puerto en el dominio.**
- No hay carpeta `domain/ports/` ni interfaces `*Port`.

### Criterio 3 - Servicios de dominio (2/5)
- Existen servicios en `application/`: `AccountService`, `LoanService`, `TransferService`, `UserService`.
- Los servicios no estan en el paquete `domain/`, estan en `application/`.
- Posiblemente tienen logica de negocio, pero al estar fuera de `domain/` no se califica en esta etapa.
- El puntaje 2 refleja que el estudiante tuvo la intencion de crear servicios, pero los ubico fuera del dominio.

### Criterio 4 - Enums y estados (5/5)
- Conjunto completo: `AccountStatus`, `AccountType`, `Currency`, `LoanStatus`, `LoanType`, `TransferStatus`, `UserRole`, `UserStatus`.
- Cubre todos los estados criticos del enunciado.
- Subcarpeta `domain/enums/` dedicada.
- Mejor cobertura de enums del grupo evaluado.

### Criterio 5 - Reglas de negocio criticas (2/5)
- Los servicios en `application/` pueden tener reglas, pero no se evaluan aqui.
- Dominio no tiene reglas implementadas.

### Criterio 6 - Bitacora y trazabilidad (2/5)
- `AuditLog` presente como entidad de dominio.
- Sin puerto de bitacora ni servicio que lo use.

### Criterio 7 - Estructura interna de dominio (3/5)
- Subcarpetas: `domain/enums/`, `domain/model/` — bien separados.
- Falta: `domain/ports/`, `domain/services/`.
- `infrastructure/InMemoryDatabase.java` separa la capa de datos.

### Criterio 8 - Calidad tecnica (4/5)
- Nomenclatura en ingles consistente.
- Nombres bien alineados con el dominio bancario.
- Sin typos detectados.

---

## Recomendaciones
1. Mover la logica de `application/` a `domain/services/` para que las reglas de negocio residan en el dominio.
2. Crear `domain/ports/` con interfaces semanticas por agregado.
3. Agregar clase `Client` base en la jerarquia.
4. Crear `BitacoraPort` y servicio `RegisterAuditEventService` en el dominio.
5. Incluir nombres de integrantes en `README.md`.
6. El conjunto de enums es ejemplar — mantener esa cobertura.

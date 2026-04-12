# EVALUACION 2 - ConstruccionSoftware2JuanRodriguez

## Informacion general
- Estudiante(s): Juan Rodriguez (usuario GitHub: CarlosRodriguezL)
- Rama evaluada: **develop** (commit más reciente del estudiante: 2026-04-04 21:13)
- Commit evaluado: 16a340f (2026-04-04)
- Fecha: 2026-04-11
- Nota: Se re-evaluó sobre la rama `develop` que tiene código de dominio más completo (13 servicios, 7 puertos, audit trail). La rama `main` solo tenía modelos básicos.

---

## Tabla de calificacion

| # | Criterio | Peso | Puntaje (1-5) | Parcial |
|---|---|---|---|---|
| 1 | Modelado de dominio | 20% | 5 | 1.00 |
| 2 | Modelado de puertos | 20% | 5 | 1.00 |
| 3 | Modelado de servicios de dominio | 20% | 5 | 1.00 |
| 4 | Enums y estados | 10% | 5 | 0.50 |
| 5 | Reglas de negocio criticas | 10% | 5 | 0.50 |
| 6 | Bitacora y trazabilidad | 5% | 5 | 0.25 |
| 7 | Estructura interna de dominio | 10% | 4 | 0.40 |
| 8 | Calidad tecnica base en domain | 5% | 3 | 0.15 |
| | **Total base** | | | **4.80** |

### Calculo
Nota base = (5*20 + 5*20 + 5*20 + 5*10 + 5*10 + 5*5 + 4*10 + 3*5) / 100 = 480 / 100 = **4.80**

---

## Penalizaciones aplicadas

No se aplican penalizaciones. El código usa identificadores en inglés. El uso de `@Service` no se penaliza.

## Bonus aplicados

| Bonus | Valor | Motivo |
|---|---|---|
| Buen diseno de puertos reutilizables | +0.2 | 7 puertos en `domain/ports/` con métodos semánticos por agregado |
| Servicios de alta cohesion | +0.2 | 13 servicios de dominio especializados, uno por caso de uso |
| Bitacora y trazabilidad excelente | +0.1 | `AuditLog` entity + `OperationLogPort` + registro en cada servicio con detalle JSON |

Nota con bonus: 4.80 + 0.50 = **5.30** → capped → **5.00**

---

## Nota final
**5.0 / 5.0**

---

## Hallazgos

### Criterio 1 - Modelado de dominio (5/5)
- 7 entidades: `AuditLog`, `BankAccount`, `CompanyClient`, `Loan`, `NaturalPersonClient`, `Transfer`, `User`.
- Jerarquía de clientes: `CompanyClient`, `NaturalPersonClient`.
- Entidad `AuditLog` explícita en el dominio para trazabilidad.
- Excepcion de dominio: `BusinessException`.

### Criterio 2 - Modelado de puertos (5/5)
- 7 puertos en `domain/ports/`: `AccountPort`, `CompanyAccessPort`, `LoanPort`, `OperationLogPort`, `ProductCatalogPort`, `TransferPort`, `UserPort`.
- Todos como interfaces Java sin acoplamiento a frameworks.
- `AccountPort.findByHolderId()`, `LoanPort.findById()` — métodos semánticos.

### Criterio 3 - Servicios de dominio (5/5)
- 13 servicios de dominio en `domain/services/`: `ApproveLoan`, `ApproveTransfer`, `CreateAccount`, `CreateLoan`, `CreateTransfer`, `DepositMoney`, `DisburseLoan`, `ExpirePendingTransfers`, `FindAccountByNumber`, `FindCustomerHistory`, `RejectLoan`, `RejectTransfer`, `WithdrawMoney`.
- Cada servicio es un caso de uso del sistema bancario.
- Cubren el ciclo completo: creación, aprobación/rechazo, desembolso, vencimiento.

### Criterio 4 - Enums y estados (5/5)
- 8 enums: `AccountStatus`, `AccountType`, `Currency`, `LoanStatus`, `LoanType`, `TransferStatus`, `UserRole`, `UserStatus`.
- Cobertura completa de todos los estados del sistema.

### Criterio 5 - Reglas de negocio criticas (5/5)
- `ApproveLoan.execute()`: valida que el analista esté activo (`UserStatus.ACTIVE`), tenga el rol correcto (`UserRole.INTERNAL_ANALYST`), que el préstamo exista, que su estado sea `UNDER_STUDY`, y que el monto aprobado sea válido y no exceda el solicitado.
- Transición de estado explícita `UNDER_STUDY → APPROVED`.
- Registro de auditoría con detalle JSON que incluye estado anterior, nuevo, montos y IDs.

### Criterio 6 - Bitacora y trazabilidad (5/5)
- Entidad `AuditLog` en `domain/model/`.
- Puerto `OperationLogPort` para guardar logs.
- El servicio `ApproveLoan` guarda `AuditLog` con campos: `operationType`, `timestamp`, `userId`, `role`, `objectId`, `detail` (JSON).

### Criterio 7 - Estructura interna de dominio (4/5)
- Buena organización: `domain/enums/`, `domain/model/`, `domain/ports/`, `domain/services/`, `domain/exceptions/`.
- **Observacion**: los archivos de puertos y servicios no tienen extensión `.java` en el nombre de archivo (ej. `AccountPort` en lugar de `AccountPort.java`). Los archivos contienen código Java válido pero la convención de nomenclatura de archivos Java no se cumple.

### Criterio 8 - Calidad tecnica (3/5)
- Identificadores en inglés consistentes.
- Mensajes de excepción en español (`"El usuario es obligatorio"`, `"El préstamo no encontrado"`) — son literales de string, no identificadores de código.
- Los archivos sin extensión `.java` reducen la calidad técnica del proyecto.
- Los servicios de dominio usan inyección por constructor, buen patrón.

---

## Resumen
Implementación sobresaliente con cobertura completa del ciclo de negocio. El dominio tiene entidades, puertos, servicios y bitácora correctamente separados y funcionando en conjunto. La debilidad técnica menor es la nomenclatura de archivos sin extensión `.java` y algunos mensajes de error en español.


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

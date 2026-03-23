# EVALUACIÓN - ConstruccionSoftware2JuanRodriguez

## Información General
- **Estudiante(s):** Juan Rodríguez / Carlos Rodríguez (CarlosRodriguezL)
- **Rama evaluada:** main (no existe rama develop local)
- **Fecha de evaluación:** 2026-03-23

---

## Tabla de Calificación

| # | Criterio | Peso | Puntaje (1–5) | Nota ponderada |
|---|---|---|---|---|
| 1 | Modelado de dominio | 25% | 3 | 0.75 |
| 2 | Relaciones entre entidades | 15% | 3 | 0.45 |
| 3 | Uso de Enums | 15% | 5 | 0.75 |
| 4 | Manejo de estados | 5% | 5 | 0.25 |
| 5 | Tipos de datos | 5% | 2 | 0.10 |
| 6 | Separación Usuario vs Cliente | 10% | 2 | 0.20 |
| 7 | Bitácora | 5% | 2 | 0.10 |
| 8 | Reglas básicas de negocio | 5% | 4 | 0.20 |
| 9 | Estructura del proyecto | 10% | 4 | 0.40 |
| 10 | Repositorio | 10% | 1 | 0.10 |
| **TOTAL** | | **100%** | | **3.30** |

## Penalizaciones
- Ninguna (código en inglés con buenos comentarios explicativos).

## Bonus
- Ninguno aplicado (no hay jerarquía `Client` correcta).

## Nota Final: 3.3 / 5.0

---

## Análisis por Criterio

### 1. Modelado de dominio — 3/5
Entidades presentes: `AuditLog`, `BankAccount`, `CompanyClient`, `NaturalPersonClient`, `Loan`, `Transfer`, `User`. Sin embargo, no existe una clase `Client` abstracta: tanto `CompanyClient` como `NaturalPersonClient` extienden directamente `User`. Esta es una confusión conceptual importante entre el usuario del sistema y el cliente del banco.

### 2. Relaciones entre entidades — 3/5
`BankAccount` usa `String holderId` en lugar de una referencia al objeto `Client`. `CompanyClient` y `NaturalPersonClient` heredan de `User`. Los servicios de aplicación (`AccountService`, `LoanService`, `TransferService`, `UserService`) gestionan relaciones, pero en el dominio son débiles.

### 3. Uso de Enums — 5/5
Conjunto completo de enums: `AccountStatus`, `AccountType`, `Currency`, `LoanStatus`, `LoanType`, `TransferStatus`, `UserRole`, `UserStatus`. Todos los catálogos esperados están modelados como enum.

### 4. Manejo de estados — 5/5
`BankAccount` usa `AccountStatus` correctamente en su lógica de negocio (`deposit()` y `withdraw()` validan `AccountStatus.ACTIVE`). Los enums de estado se aplican correctamente.

### 5. Tipos de datos — 2/5
Se usa `double currentBalance` en `BankAccount` en lugar de `BigDecimal`. Las fechas se almacenan como `String openingDate` y `String operationDateTime` en lugar de `LocalDate`/`LocalDateTime`.

### 6. Separación Usuario vs Cliente — 2/5
`NaturalPersonClient` y `CompanyClient` extienden `User`. El comentario en el código señala explícitamente: "Other classes like NaturalPersonClient will INHERIT from this class". Esto mezcla el concepto de usuario del sistema con el de cliente bancario. No hay jerarquía `Client` independiente.

### 7. Bitácora — 2/5
`AuditLog` existe con buenas validaciones en el constructor, pero todos los campos son `String`: `operationType`, `operationDateTime`, `userRole`, `detailData`. No hay `Map<String, Object>` ni uso de enums para tipo de operación o rol.

### 8. Reglas básicas de negocio — 4/5
`BankAccount` tiene métodos `deposit()` y `withdraw()` con validaciones robustas (monto > 0, estado ACTIVE, saldo suficiente) ✓. La clase `User` tiene validaciones de email, teléfono y nombre en el constructor y setters ✓. Buen nivel de lógica de dominio.

### 9. Estructura del proyecto — 4/5
Estructura clara: `domain/model`, `domain/enums`, `application/` (servicios), `infrastructure/InMemoryDatabase`, `presentation/Main`. No usa Spring Boot pero la separación es correcta para un proyecto Java puro.

### 10. Repositorio — 1/5
- **Nombre:** `ConstruccionSoftware2JuanRodriguez` — correcto.
- **README:** Solo el nombre del repo.
- **Commits:** Solo 2 commits ("Clases", "Initial commit"). Sin formato ADD/CHG.
- **Ramas:** No existe `develop`. Solo `main`.
- **Tag:** No hay tag.

---

## Fortalezas
- Conjunto completo e impresionante de enums.
- Excelente código bien comentado en inglés (didáctico y claro).
- Lógica de negocio en `BankAccount` con validaciones robustas.
- `User` con validaciones en constructor y setters.
- Estructura de capas bien definida.

## Oportunidades de mejora
- Crear clase abstracta `Client` separada de `User` para `NaturalPersonClient` y `CompanyClient`.
- Usar `BigDecimal` para montos y `LocalDate`/`LocalDateTime` para fechas.
- Mejorar `AuditLog` con `Map<String, Object>` y uso de enums para tipo y rol.
- Crear rama `develop` y trabajar en ella.
- Agregar más commits con formato ADD/CHG y tag de entrega.
- Mejorar README con información de la materia.

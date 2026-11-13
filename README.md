# Proyecto_GC04 · ErasmusNest

Plataforma web para que estudiantes de la UEx subalquilen su habitación a otros
estudiantes verificados durante periodos de movilidad.
Empresa: Practical Software · Asignatura: Gestión de Proyectos Software (UEx).

## Equipo

| Miembro | Responsabilidad principal |
|---|---|
| Hugo | Product Owner |
| Gled | Tech Lead |
| José | Scrum Master |

DevOps/QA: compartido entre los tres (ver `docs/organization/team-charter.md`).

## Cómo ejecutar la aplicación

Solo hace falta **Java 21**. Maven viene incluido con el *wrapper* (`mvnw`).

| Acción | Windows (PowerShell) | Linux / macOS |
|---|---|---|
| Compilar y ejecutar todas las pruebas | `.\mvnw.cmd verify` | `./mvnw verify` |
| Arrancar la aplicación | `.\mvnw.cmd spring-boot:run` | `./mvnw spring-boot:run` |

Con la aplicación arrancada, en <http://localhost:8080>:

| Ruta | Historia | Qué hace |
|---|---|---|
| `/registro` | US-001 | Registro con correo institucional |
| `/buzon-pruebas` | US-002 | Buzón de pruebas en memoria con el enlace de verificación (no se envían correos reales, DEC-024) |
| `/login` | US-003 | Inicio de sesión; tras entrar, `/panel`, con el botón de cerrar sesión |

Los datos son sintéticos y se guardan en una base H2 local en `data/` (excluida del repositorio).

**Integración continua:** GitHub Actions (`.github/workflows/ci.yml`) ejecuta `mvnw -B verify` en cada push a `main`, `develop` y `feature/**`, y en cada pull request a `main` y `develop`.

**Flujo de trabajo:** GitFlow (DEC-024). Cada historia tiene su rama `feature/GC04GPS26-n-…` creada a partir de `develop`; se integra en `develop` por pull request con 1 aprobación y merge commit, y `develop` se integra en `main` al cerrar cada release. `main` y `develop` están protegidas.

## Estructura

| Ruta | Contenido |
|---|---|
| `src/main/java/`, `src/main/resources/` | Código de la aplicación (Spring Boot, Thymeleaf) |
| `src/test/java/` | Pruebas automáticas |
| `pom.xml`, `mvnw`, `mvnw.cmd` | Proyecto Maven y *wrapper* |
| `.github/workflows/ci.yml` | Integración continua (GitHub Actions) |
| `.specify/memory/constitution.md` | Principios no negociables del proyecto |
| `.github/agents/` | Agentes de IA (GitHub Copilot): revisión de requisitos, revisión de la planificación y auditoría del proyecto |
| `docs/organization/empresa.md` | Ficha de la empresa |
| `docs/organization/team-charter.md` | Roles y acuerdos de trabajo |
| `docs/quality/` | Definition of Ready y Definition of Done |
| `docs/ErasmusNest_GC04_Requisitos_Capacidad.xlsx` | Excel: requisitos, prioridades, estimación, capacidad, planificación por persona e iteración, carga, gestión y coste |
| `docs/project/planning/` | Exportaciones en texto del Excel (`excel-export-vNN.md`), que leen los agentes |
| `docs/project/planning/projectlibre/` | Planificación en ProjectLibre por sprints y por épicas: XML, `.pod` con línea base, Gantt en PDF y `LEEME.md` |
| `docs/project/reviews/` | Informes de los agentes (`requirements/`, `initial-planning/`, `audit/`) y registro de uso de IA (`registro-ia.md`) |
| `docs/project/risks/riesgos.md` | Registro de riesgos |
| `docs/project/informe/` | Informe del Trabajo 1 (Markdown y PDF) |
| `specs/product/mission.md` | Misión del producto |
| `specs/product/goals.md` | Objetivos, métricas, stakeholders, alcance, exclusiones, supuestos y restricciones |
| `specs/product/nfr-y-reglas.md` | Requisitos no funcionales y reglas de negocio |
| `specs/EP-XXX-*/spec.md` | Especificación de cada épica y sus historias |

## Versiones

| Etiqueta | Contenido |
|---|---|
| `requisitos-v0` | Catálogo de requisitos aceptado al cierre del Hito 2 (14/10/2026) |
| `planificacion-v1` | Planificación entregada en el Trabajo 1 (16/10/2026): línea base DEC-016, planificación DEC-020, ProjectLibre con línea base, riesgos, auditoría e informe |
| `entrega-trabajo1-v2` | Trabajo 1 reenviado para la reevaluación: mejoras tras la evaluación (DEC-025), Excel v03 con 18 historias y reserva de imprevistos, ProjectLibre v3, riesgos 2.0, registro de IA e informe v2 |
| `trabajo3-it001` | Trabajo 3 (13/11/2026): IT-001 integrada en `main` (US-001, US-002 y US-003), con CI en GitHub Actions y flujo GitFlow |

La memoria de la práctica y la justificación de las decisiones están en la Wiki del repositorio.

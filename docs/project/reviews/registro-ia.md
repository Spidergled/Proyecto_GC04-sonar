# Registro de uso de IA

Registro de los agentes de revisión de la Práctica 1 (semanas 3 y 4) y de lo que el equipo decide con cada propuesta. Los agentes proponen; el equipo revisa cada hallazgo contra las fuentes y decide. Ninguna propuesta se aplica sin comprobarla.

## 1. Agentes

| Agente | Instrucciones | Versión | Herramienta | Modelo | Informes | Límite |
|---|---|---|---|---|---|---|
| Requirements Agent | `.github/agents/requirements-reviewer.agent.md` | 1.2 (14/10/2026) | GitHub Copilot en VS Code | Auto (GitHub Copilot, plan gratuito; modelo concreto no visible) en v02 | `docs/project/reviews/requirements/` | No modifica requisitos ni decide el alcance |
| Planning Agent | `.github/agents/initial-planning-reviewer.agent.md` | 1.2 (14/10/2026) | GitHub Copilot en VS Code |Auto (GitHub Copilot, plan gratuito; modelo concreto no visible) en v01 | `docs/project/reviews/initial-planning/` | No replanifica ni modifica los archivos |

La v01 del informe de requisitos (10/10/2026, José) se generó con la versión anterior del agente (`e4db02d`); el modelo usado no quedó anotado (No registrado).

La versión 1.2 de ambos agentes (14/10/2026) les indica que lean el Excel a través de su exportación en texto, `docs/project/planning/excel-export-vNN.md`, porque no pueden abrir el `.xlsx`.

## 2. Cómo se usa

1. Se fija el commit de la baseline revisada y se lanza el agente desde el selector de agentes de Copilot, indicando modo (INITIAL, FOCUSED o RECHECK), alcance y commit.
2. El agente crea un informe nuevo `…-vNN.md`; nunca sobrescribe uno anterior.
3. El equipo revisa cada hallazgo contra la fuente citada y anota aquí la decisión, con su justificación y, si cambia algo, el commit o la DEC del Registro de decisiones de la Wiki.
4. Tras corregir, se relanza el agente en modo RECHECK con este registro como entrada.

## 3. Registro de hallazgos

Decisión: Aceptada, Aceptada con cambios, Rechazada o Pendiente.

| Agente e ID | Entrada | Propuesta | Justificación (según el agente) | Decisión | Evidencia o responsable |
|---|---|---|---|---|---|
| Requirements · v01 F-01 | `requisitos-v0`, commit no indicado; specs EP-001…005, `nfr-y-reglas.md` | NFR-002 prohíbe mostrar datos del usuario, pero US-004, US-015 y US-017 muestran el nombre visible. Precisar qué datos son públicos | Ambas obligaciones son explícitas. Confianza alta. MAJOR, bloqueante | Aceptada | Corregido el 14/10: NFR-002 fija qué es público (nombre visible, distintivo y, con US-004, titulación e idiomas; «Estudiante verificado» por defecto) y US-004-AC-02 se alinea · Hugo |
| Requirements · v01 F-02 | Ídem | US-001 pide confirmar la contraseña, pero no define qué pasa si no coincide | No hay criterio para ese caso. Confianza alta. MAJOR, bloqueante | Aceptada | Corregido el 14/10: nuevo US-001-AC-06 y ejemplo · Hugo |
| Requirements · v01 F-03 | Ídem | Faltan NFR aplicables: NFR-004 en US-002 y NFR-009 en US-013 | Constitución (principio 5) y NFR-009 globales. Confianza alta. MAJOR, bloqueante | Aceptada | Corregido en `99d8d9a` |
| Requirements · v01 F-04 | Ídem | RN-10 no fija si las fechas son inclusivas ni cómo se calcula la cobertura parcial en US-013 | Solo hay un caso límite definido. Confianza media. MAJOR, bloqueante | Aceptada | Corregido el 14/10: RN-10 con fechas inclusivas y cobertura parcial como intersección; tres casos límite nuevos en US-013 · Gled |
| Requirements · v01 F-05 | Ídem | Las estimaciones de las seis historias detalladas son V0, previas al Planning Poker | Lo dicen las propias specs. Confianza alta. MAJOR, bloqueante | Aceptada | Planning Poker del 10/10, `abc523d` |
| Requirements · v01 F-06 | Ídem | No se puede comprobar si cada estimación cabe en un sprint ni aplicar DEC-018 | El agente no pudo leer el Excel. Confianza alta en la falta de evidencia. QUESTION | Aceptada | DEC-016 (14/10/2026): ninguna historia supera 5 h-p y la línea base (32 h-p) cabe en cada release (hoja *Resumen* del Excel) |
| Requirements · v01 F-07 | Ídem | US-024 (inglés) no está en S-01…S-06 y tiene plan en IT-006; US-007, US-011, US-020 y US-004 sin S-XX inequívoco | Alcance de `goals.md` explícito. Confianza media. QUESTION | Aceptada | DEC-016 (14/10/2026): US-024 pasa a Won't; US-004, US-007, US-011 y US-020 quedan fuera de la línea base |
| Requirements · v01 F-08 | Ídem | Sin orden dentro del sprint (US-006→US-009, US-012→US-013); US-017 no cita US-009; US-023 solo cita US-022 | Basado en los planes iniciales. Confianza media. QUESTION | Aceptada | Resuelto en la planificación inicial (14/10/2026): la hoja *Planificación* comprueba con fórmulas que cada historia empieza cuando sus predecesoras han terminado o en el mismo sprint después de ellas (columna *Orden de predecesoras*, 0 incidencias). US-023 depende de US-017 (DEC-016); US-009 queda fuera de la línea base |

### Candidatos y preguntas del informe v01 sin número de hallazgo (§7 y §9)

El equipo también tiene que clasificar estas propuestas, aunque el agente no las numere como hallazgos.

| Agente e ID | Entrada | Propuesta | Justificación (según el agente) | Decisión | Evidencia o responsable |
|---|---|---|---|---|---|
| Requirements · v01 §7 US-007 | Ídem | Fotos: ¿entran en S-02 o son ampliación? | S-02 solo nombra fechas, zona y precio. SCOPE_UNCLEAR | Aceptada | Fuera de la línea base (DEC-016); el alcance se revisará si llega a entrar |
| Requirements · v01 §7 US-011 | Ídem | Cierre automático: S-05 solo nombra el cierre manual | SCOPE_UNCLEAR | Aceptada | Fuera de la línea base (DEC-016); el alcance se revisará si llega a entrar |
| Requirements · v01 §7 US-020 | Ídem | Reportes: candidato fuera de alcance | S-01…S-06 no incluyen moderación. OUT_OF_SCOPE candidato | Aceptada | Fuera de la línea base (DEC-016) |
| Requirements · v01 §7 US-023 | Ídem | Registrar el guion como entregable que habilita G-04 | G-04, M-04 y NFR-008. IN_SCOPE_CANDIDATE | Aceptada | En la línea base (DEC-016), R3 |
| Requirements · v01 §7 US-013 | Ídem | ¿Mostrar coberturas parciales o solo anuncios que cubren toda la estancia? | Utilidad pendiente de validar en la Review de R2. IN_SCOPE_CANDIDATE | Aceptada | RN-10 fija que también se muestran las coberturas parciales; el cliente lo validará cuando US-013 entre en la línea base (DEC-016) · Hugo |
| Requirements · v01 §7 anuncios cerrados | Ídem | ¿Se puede responder en conversaciones de un anuncio cerrado o solo leerlas? | US-009 y US-018 no lo fijan. SCOPE_UNCLEAR | Pendiente | Aplazada por DEC-016: US-009 y US-018 están fuera de la línea base; se decidirá cuando entren · Hugo |
| Requirements · v01 §8 M-05 | Ídem | M-05 no está trazada a ninguna historia | Solo cubierta en parte por la DoD | Aceptada | Resuelto el 14/10 con la pregunta 4 del informe v02: la evidencia es el acta de la Review de cada release (`goals.md`, M-05) · Hugo |
| Requirements · v01 §9 P2 | Ídem | ¿Qué validación observable se aplica a un correo mal formado en US-001? | Pregunta para decisión humana | Aceptada | Corregido el 14/10: caso límite de correo mal formado en US-001 ("Introduce un correo válido") · Hugo |

### Informe v02 (RECHECK, 14/10/2026)

Recomendación del agente: NEEDS_REFINEMENT, por dos hallazgos nuevos (R-01 y R-02). Confirma como resueltos F-01…F-08 y la pregunta P2 del v01, mantiene aplazada la de anuncios cerrados y deja abierta la de M-05. La excepción de DEC-018 en US-002, US-006 y US-017 y la planificación exportada cumplen la DoR según el agente.

| Agente e ID | Entrada | Propuesta | Justificación (según el agente) | Decisión | Evidencia o responsable |
|---|---|---|---|---|---|
| Requirements · v02 R-01 | `requisitos-v0` + correcciones del 14/10, commit `86e995f`; specs EP-001…005, `goals.md`, `nfr-y-reglas.md`, DoR, DoD, `excel-export-v01.md` | Enlazar NFR-004 desde US-012, que especifica una pantalla principal (listado, paginación y estado vacío) | NFR-004 y el principio 5 de la constitución exigen accesibilidad en las pantallas principales. Confianza alta. MAJOR, bloqueante para la DoR de US-012 | Aceptada | Corregido el 14/10: NFR-004 en la línea NFR de US-012 · Hugo |
| Requirements · v02 R-02 | Ídem | Identificar NFR-009 (datos sintéticos) en US-015 y US-023 | US-015 muestra datos del autor y US-023 prepara datos y credenciales de prueba, pero ninguna lo traza a NFR-009. Confianza alta. MAJOR, bloqueante para la DoR de las dos | Aceptada con cambios | Corregido el 14/10: NFR-009 en la restricción y en la línea NFR de US-015 y US-023. Además, el equipo añade NFR-009 a la línea NFR de US-001, US-003, US-006, US-012 y US-017, que ya lo citaban en *Restricciones* pero no en *NFR*, para que las 8 historias de la línea base lo identifiquen igual · Hugo |
| Requirements · v02 §9 P2 | Ídem | ¿Se podrá responder en una conversación existente tras cerrar el anuncio, o solo leerla? | Sigue aplazada porque US-009 y US-018 están fuera de la línea base | Pendiente | Misma decisión que la fila «v01 §7 anuncios cerrados»: se decide cuando US-009 y US-018 entren en la línea base · Hugo |
| Requirements · v02 §9 P3 | Ídem | Confirmar que los IDs coinciden entre repositorio, Excel, ProjectLibre y Jira | Solo había evidencia del repositorio y del Excel; NOT_ASSESSABLE | Aceptada con cambios | ProjectLibre se construye con los mismos IDs y Gled lo comprueba al importarlo (paso 20 del Hito 3). Jira no forma parte del Trabajo 1: se comprobará cuando se cree el proyecto en Jira · Gled |
| Requirements · v02 §9 P4 | Ídem | ¿Qué artefacto recoge la evidencia de M-05 (cero defectos bloqueantes al cierre de cada release)? | `goals.md` y la DoD definen el resultado, pero no dónde se registra | Aceptada | `goals.md`, M-05: la evidencia es el acta de la Review de cada release, con el campo «Defectos bloqueantes abiertos (M-05)», que se añade a la plantilla de Actas de la Wiki. Aclara cómo se mide; no cambia la métrica · aprobado por Hugo (PO) |
| Requirements · v02 §9 P5 | Ídem | ¿Se registrará la validación del cliente sobre las coberturas parciales de US-013? | US-013 está diferida y su supuesto prevé la validación del cliente | Pendiente | Se registrará en el acta de la Review de la release en la que entre US-013 (fuera de la línea base por DEC-016) · Hugo |
| Requirements · v02 (observación del equipo) | Ídem | — | — | — | El informe no indica el modelo ni comprobó el commit (lo declara en §1). En la tabla §6, las filas «Criterios observables…» y «Estimación cabe en un sprint…» tienen una celda de más y una de menos; por el texto de evidencia, se entiende M en las 10 historias y NAP-D en US-009 y US-013, respectivamente. Las correcciones de R-01 y R-02 las ha comprobado el equipo; un próximo RECHECK las verificará |

## 4. Otros usos de IA

| Fecha | Herramienta | Uso | Revisión del equipo |
|---|---|---|---|
| 14/10/2026 | Claude (chat de claude.ai) | Escenarios de alcance para DEC-016 a partir de las cifras del Excel | El PO los revisó con el equipo y decidió (DEC-016) |
| 14/10/2026 | Claude (chat de claude.ai) | Borrador de la primera planificación: reparto de horas por persona e iteración, tareas de IT-001, hojas de carga, gestión y coste, gráficas, exportación en texto del Excel y propuesta DEC-020 | El equipo revisó el reparto por persona, las tarifas por rol y la estimación de TD-01, y los aceptó sin cambios el 14/10/2026. DEC-020 se acepta después de la revisión de los dos agentes · Hugo (PO) |
| 14/10/2026 | Claude (chat de claude.ai) | Propuesta de clasificación de los hallazgos del informe v02 y redacción de las correcciones de R-01 y R-02 | El PO revisó la propuesta y la aceptó sin cambios el 14/10/2026 (§3, informe v02) · Hugo |

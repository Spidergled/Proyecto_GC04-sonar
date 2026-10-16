# Registro de uso de IA

Registro de los agentes de revisión de la Práctica 1 (semanas 3 y 4) y de lo que el equipo decide con cada propuesta. Los agentes proponen; el equipo revisa cada hallazgo contra las fuentes y decide. Ninguna propuesta se aplica sin comprobarla.

## 1. Agentes

| Agente | Instrucciones | Versión | Herramienta | Modelo | Informes | Límite |
|---|---|---|---|---|---|---|
| Requirements Agent | `.github/agents/requirements-reviewer.agent.md` | 1.2 (14/10/2026) | GitHub Copilot en VS Code | Auto (plan GitHub Copilot Student, que solo permite la selección automática; el modelo concreto no se muestra) en v02 | `docs/project/reviews/requirements/` | No modifica requisitos ni decide el alcance |
| Planning Agent | `.github/agents/initial-planning-reviewer.agent.md` | 1.2 (14/10/2026) | GitHub Copilot en VS Code | Auto (plan GitHub Copilot Student, que solo permite la selección automática; el modelo concreto no se muestra) en v01 | `docs/project/reviews/initial-planning/` | No replanifica ni modifica los archivos |
| Project Auditor (opcional, Hito 4) | `.github/agents/project-auditor.agent.md` | 1.0 (16/10/2026) | GitHub Copilot en VS Code | Auto (plan GitHub Copilot Student) | `docs/project/reviews/audit/` | No modifica planes, riesgos ni decisiones; solo crea su informe |

El plan GitHub Copilot Student no permite elegir el modelo: desde el 24/06/2026 la selección automática (*Auto*) es la única opción en los planes Free y Student. Los tres miembros usan la misma herramienta y el mismo modo (DEC-021 en la Wiki).

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
| Requirements · v02 §9 P3 | Ídem | Confirmar que los IDs coinciden entre repositorio, Excel, ProjectLibre y Jira | Solo había evidencia del repositorio y del Excel; NOT_ASSESSABLE | Aceptada con cambios | ProjectLibre usa los mismos IDs (US-XXX, IT-XXX): lo comprobó Gled al importar los XML el 15/10/2026 (PR #16). Jira no forma parte del Trabajo 1: se comprobará cuando se cree el proyecto en Jira · Gled |
| Requirements · v02 §9 P4 | Ídem | ¿Qué artefacto recoge la evidencia de M-05 (cero defectos bloqueantes al cierre de cada release)? | `goals.md` y la DoD definen el resultado, pero no dónde se registra | Aceptada | `goals.md`, M-05: la evidencia es el acta de la Review de cada release, con el campo «Defectos bloqueantes abiertos (M-05)», que se añade a la plantilla de Actas de la Wiki. Aclara cómo se mide; no cambia la métrica · aprobado por Hugo (PO) |
| Requirements · v02 §9 P5 | Ídem | ¿Se registrará la validación del cliente sobre las coberturas parciales de US-013? | US-013 está diferida y su supuesto prevé la validación del cliente | Pendiente | Se registrará en el acta de la Review de la release en la que entre US-013 (fuera de la línea base por DEC-016) · Hugo |
| Requirements · v02 (observación del equipo) | Ídem | — | — | — | El informe no indica el modelo ni comprobó el commit (lo declara en §1). En la tabla §6, las filas «Criterios observables…» y «Estimación cabe en un sprint…» tienen una celda de más y una de menos; por el texto de evidencia, se entiende M en las 10 historias y NAP-D en US-009 y US-013, respectivamente. Las correcciones de R-01 y R-02 las ha comprobado el equipo; un próximo RECHECK las verificará |

### Informe de planificación v01 (CONSISTENCY, 14/10/2026)

Resultado del agente: READY_WITH_NON_BLOCKING_QUESTIONS, con 0 hallazgos bloqueantes. Confirma que la hipótesis DEC-020 es coherente: 32 h-p sobre 35,72 h-p, ninguna sobrecarga, todo asignado, predecesoras bien ordenadas, excepciones DEC-018 dentro de sus límites y coste de 2.507,64 €. El equipo comprobó su aritmética: holguras mínimas de 0,044 (Hugo), 0,0975 (Gled) y 0,012 h-p (José), 38,76 h de gestión y una cota superior de 3.210 €.

| Agente e ID | Entrada | Propuesta | Justificación (según el agente) | Decisión | Evidencia o responsable |
|---|---|---|---|---|---|
| Planning · v01 IPR-v01-001 | Commit `45e3489`; `excel-export-v01.md`, specs EP-001…005, `goals.md`, `nfr-y-reglas.md`, DoR, DoD, `requirements-report-v02.md`, `registro-ia.md` | Aclarar la relación entre `45e3489` y `86e995f` y fijar el snapshot conjunto | El informe de requisitos v02 cita otro commit y no se aporta su relación. MINOR, no bloqueante | Aceptada | `86e995f` es el merge de la PR de la planificación (DEC-020), que revisó el Requirements Agent v02. `45e3489` es el merge posterior de la clasificación del v02, que añade las correcciones de R-01 y R-02. El snapshot conjunto de las dos revisiones es `45e3489`; la versión entregada se fija con la etiqueta `planificacion-v1` · Gled |
| Planning · v01 IPR-v01-002 | Ídem | Vigilar el margen individual casi nulo (José 0,012, Hugo 0,044 y Gled 0,0975 h-p en IT-001, IT-003 e IT-005) y su sensibilidad al redondeo | Un pequeño imprevisto convierte esas celdas en sobrecarga, y el margen del equipo no compensa a una persona. MINOR, no bloqueante | Aceptada con cambios | El equipo analizó repartir de nuevo y no gana margen: en IT-001, IT-003 e IT-005 la holgura de todo el equipo es de 0,15 h-p, y R1 necesita el 93 % de su capacidad, así que mover horas entre personas no crea holgura. Cambios: estado AJUSTADO (holgura < 0,1 h-p), su recuento y la holgura mínima por persona en el Excel (exportación `excel-export-v02.md`), y regla de contingencia en DEC-020. El SM revisa la carga por persona con valores sin redondear en cada Sprint Planning · José |
| Planning · v01 IPR-v01-003 | Ídem | Confirmar el buzón de pruebas, las tecnologías aprobadas y el evaluador externo antes de sus fechas límite | Están identificados, pero no hay evidencia de que estén disponibles. MINOR; bloquearía US-023 si falta el evaluador | Aceptada | Responsables y fechas límite en la página *Dependencias entre requisitos* de la Wiki: EXT-01 y EXT-02 · Gled · antes del 21/10 (IT-001); EXT-03 · José · antes del 18/11 (IT-005). Si alguno falta en su fecha, se aplica la *Gestión de cambios* de `goals.md` |
| Planning · v01 §8 presupuesto | Ídem | Registrar el presupuesto o declarar que el coste se informa sin control presupuestario | No hay presupuesto declarado | Aceptada con cambios | La hoja *Coste* declara que el cliente no fija presupuesto (proyecto académico) y usa como tope de referencia la cota superior de dedicación nominal (3.210 €), con 702,36 € de margen · Hugo |
| Planning · v01 §8 magnitudes | Ídem | Mantener separadas capacidad efectiva, carga de desarrollo, horas de gestión y euros | Para futuras exportaciones | Aceptada | Ya se cumple: hojas *Capacidad*, *Planificación* y *Carga* (h-p), *Gestión* (h) y *Coste* (€). La exportación v02 mantiene la separación |

## 4. Otros usos de IA

| Fecha | Herramienta | Uso | Revisión del equipo |
|---|---|---|---|
| 10/10/2026 | Claude (chat de claude.ai) | Auditoría del Hito 1 y del Hito 2 frente a los enunciados; guía y hoja del Planning Poker | El equipo revisó cada observación contra los enunciados: correcciones de DEC-017 y DEC-018 y Planning Poker del 10/10 |
| 12/10/2026 | Claude (chat de claude.ai) | Borrador de los agentes 1.1 y de este registro | Gled (TL) lo revisó frente a las plantillas del profesor antes de integrarlo (`03ce7e9`) |
| 14/10/2026 | Claude (chat de claude.ai) | Escenarios de alcance para DEC-016 a partir de las cifras del Excel | El PO los revisó con el equipo y decidió (DEC-016) |
| 14/10/2026 | Claude (chat de claude.ai) | Borrador de la primera planificación: reparto de horas por persona e iteración, tareas de IT-001, hojas de carga, gestión y coste, gráficas, exportación en texto del Excel y propuesta DEC-020 | El equipo revisó el reparto por persona, las tarifas por rol y la estimación de TD-01, y los aceptó sin cambios el 14/10/2026. DEC-020 se acepta después de la revisión de los dos agentes · Hugo (PO) |
| 14/10/2026 | Claude (chat de claude.ai) | Propuesta de clasificación de los hallazgos del informe v02 y redacción de las correcciones de R-01 y R-02 | El PO revisó la propuesta y la aceptó sin cambios el 14/10/2026 (§3, informe v02) · Hugo |
| 14/10/2026 | Claude (chat de claude.ai) | Propuesta de clasificación del informe de planificación v01, cambios del Excel (estado AJUSTADO y presupuesto), exportación v02 y regla de contingencia de DEC-020 | El PO delegó la propuesta y la aceptó el 14/10/2026 (§3, informe de planificación v01) · Hugo |
| 14/10/2026 | Claude (chat de claude.ai) | Revisión del cierre del Hito 2, del plan de la semana 3 y del estado final del repositorio y la Wiki frente a los enunciados de las semanas 3 y 4 | Gled (TL) comprobó cada punto contra los ficheros: hoja *Leyenda* y DoR (identificadores en ProjectLibre y Jira), modelo del plan Copilot Student, DEC-021, filas de este apartado y enlaces y estados de la Wiki |
| 14/10/2026 – 15/10/2026 | Claude (chat de claude.ai) | Planificación del Hito 4: script `generar_xml.py` y XML de ProjectLibre por sprints y por épicas, registro de riesgos RSK-01…RSK-12 y agente auditor | Gled (TL) comprobó con el script que las horas por persona, historia e iteración, las 9 dependencias, las tarifas y el coste (2.507,64 €) coinciden con `excel-export-v02.md`; abrió los XML en ProjectLibre 1.9.3, guardó los `.pod` con línea base y exportó el Gantt (PR #15 y #16). Corrigió los responsables de RSK-07 y RSK-08 para que coincidan con la Wiki. El equipo acepta las decisiones de modelado y los riesgos en DEC-022 |
| 16/10/2026 | Claude (chat de claude.ai) | Revisión de los últimos commits y del estado del repositorio y la Wiki frente a los enunciados de las semanas 1 a 4 antes de la entrega | Gled (TL) comprobó cada punto contra los ficheros y repartió los pendientes con el equipo |

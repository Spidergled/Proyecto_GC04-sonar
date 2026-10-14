# Requirements review v02

## 1. Metadatos de ejecución

- **Modo:** RECHECK.
- **Baseline:** `requisitos-v0` con correcciones del 14/10/2026.
- **Commit/snapshot indicado:** `86e995f` (proporcionado por la invocación; no se verificó contra Git).
- **Fecha:** 2026-10-14; hora no indicada.
- **Agente:** `.github/agents/requirements-reviewer.agent.md`, versión 1.2 (2026-10-14).
- **Herramienta/modelo:** GitHub Copilot en VS Code, según `registro-ia.md`; modelo no indicado.
- **Idioma:** español.
- **Alcance detallado:** US-001, US-002, US-006, US-009, US-013, US-017 y las historias de la línea base DEC-016 US-003, US-012, US-015 y US-023. US-009 y US-013 están fuera de la línea base y se marcan como diferidas.
- **Alcance de catálogo:** US-003…US-024, excepto US-021 (Won't, excluida por instrucción). US-024 se revisa como elemento Won't de catálogo, no como trabajo planificado.

## 2. Manifiesto de fuentes y limitaciones

| Fuente leída | Función y versión/baseline observado |
|---|---|
| `.github/agents/requirements-reviewer.agent.md` | Protocolo del agente; versión 1.2, 14/10/2026. |
| `docs/project/reviews/requirements/requirements-report-v01.md` | Revisión previa INITIAL; hallazgos F-01…F-08 y preguntas/candidatos. |
| `docs/project/reviews/registro-ia.md` | Decisiones humanas de recheck, incluidas DEC-016 y resoluciones del 14/10/2026. |
| `.specify/memory/constitution.md` | Gobierno; versión 1.2.0, ratificada el 30/09/2026 y con cambio del 13/10/2026. |
| `specs/product/mission.md` | Misión, usuarios y experiencia deseada. |
| `specs/product/goals.md` | Stakeholders, objetivos, métricas, alcance, exclusiones, restricciones y DEC-016. |
| `specs/product/nfr-y-reglas.md` | NFR-001…NFR-009 y RN-01…RN-11; baseline `requisitos-v0`. |
| `docs/quality/definition-of-ready.md` | Criterios compartidos del DoR, incluida la regla de tamaño y DEC-018. |
| `docs/quality/definition-of-done.md` | Criterios compartidos de DoD; leído como contexto, no como evidencia de implementación. |
| `specs/EP-001-cuenta-y-verificacion/spec.md` | US-001…US-005; cabeceras de épica e historias relevantes y criterios actuales. |
| `specs/EP-002-publicacion-de-anuncios/spec.md` | US-006…US-011; cabeceras de épica e historias relevantes y criterios actuales. |
| `specs/EP-003-busqueda-de-habitaciones/spec.md` | US-012…US-016; cabeceras de épica e historias relevantes y criterios actuales. |
| `specs/EP-004-contacto-y-mensajeria/spec.md` | US-017…US-021; cabeceras de épica e historias relevantes y criterios actuales. |
| `specs/EP-005-entrega-y-demo/spec.md` | US-022…US-024; cabeceras de épica e historias relevantes y criterios actuales. |
| `docs/project/planning/excel-export-v01.md` | Exportación v01 de Requisitos, Capacidad, Planificación, Dependencias y Resumen; plan revisado por el equipo el 14/10/2026. |

**Fuentes no suministradas o límites:** no se proporcionaron vistas/exportaciones de Jira ni ProjectLibre. Por ello, la coincidencia de IDs del DoR entre repositorio, Excel, ProjectLibre y Jira queda NOT_ASSESSABLE, aunque el repositorio y la exportación contienen los IDs revisados. Se usó la exportación v01 indicada; no se abrió el Excel binario. El commit indicado no se inspeccionó. No se ejecutaron comandos ni pruebas, ni se inspeccionó implementación. La evaluación DoD se limita a requisitos documentales. Los estados externos de herramientas de planificación no se infieren de la hoja exportada.

## 3. Evaluación del alcance revisado

**Recomendación: NEEDS_REFINEMENT.** En la línea base se identifican dos incumplimientos confirmados del control DoR de requisitos no funcionales: US-012 no enlaza NFR-004 pese a especificar la pantalla principal de listado y sus estados vacíos/paginación; US-015 y US-023 no enlazan NFR-009 en sus requisitos, aunque la regla de datos sintéticos gobierna los datos visibles/de demo. Las referencias pueden corregirse o el equipo puede documentar su no aplicabilidad, pero la evaluación actual no las acredita.

La excepción DEC-018 está documentada y satisface los límites comprobables para US-002, US-006 y US-017: dos iteraciones consecutivas como máximo, capacidad agregada suficiente, motivo de no dividir el resultado, y condición de finalización al cerrar la segunda iteración. La planificación exportada no muestra sobrecargas, trabajo sin asignar ni predecesoras mal ordenadas. No se identificó un incumplimiento de tamaño en esas tres historias.

**Límite del alcance:** las historias diferidas US-009 y US-013 se revisaron documentalmente, pero no se declara su preparación para entrar en sprint. Sus controles dependientes de planificación se marcan NOT_APPLICABLE (diferidas). La falta de evidencia de Jira/ProjectLibre impide cerrar el criterio transversal de sincronización de IDs.

## 4. Matriz de cobertura

| Dimensión | IDs inspeccionados | Resultado | Hallazgos / límites |
|---|---|---|---|
| Claridad y consistencia | Detalle: US-001, 002, 003, 006, 009, 012, 013, 015, 017, 023; catálogo: US-003…024 excepto 021 | Correcciones previas comprobadas; queda pendiente aclarar respuestas en conversaciones de anuncios cerrados cuando US-009/018 entren en línea base. | R-01, R-02; pregunta Q-02; crosswalk F-01…F-08. |
| Cobertura y alcance | G-01…G-04, S-01…S-06, EX-01…EX-05; historias del catálogo indicadas | DEC-016 delimita explícitamente la línea base y las historias diferidas/Won't. No se identifica ampliación no decidida dentro de la línea base revisada. | Catálogo §7; pregunta de aplazamiento Q-02. |
| Elementos de especificación ausentes | Criterios positivos/negativos, reglas, NFR, restricciones, límites y estados de las diez historias detalladas | Los cambios anteriores se encuentran en las fuentes. Referencias a NFR-004/NFR-009 aún incompletas en historias de línea base. | R-01, R-02. |
| Tamaño y granularidad | Historias del catálogo; en especial las diez detalladas y asignación por iteración de la línea base | No se observa outcome excesivamente agrupado en las historias detalladas. La distribución de capacidad y las excepciones comprobables satisfacen el DoR exportado; US-009/013 están diferidas. | Matriz DoR §6; exportación v01. |
| Criterios de aceptación y DoR | Diez historias detalladas frente a cada criterio actual del DoR | Dos fallos de trazabilidad NFR confirmados; los demás controles documentales son MET o quedan NOT_ASSESSABLE/NOT_APPLICABLE como se indica. | R-01, R-02; §6. |
| Dependencias y supuestos | Predecesoras, orden exportado, DEC-016, relaciones de catálogo | Orden exportado: 0 predecesoras mal ordenadas. US-023 ya declara US-017; sin ciclos observados. Dependencias del flujo de conversación cerrada siguen aplazadas, no bloquean la línea base. | Crosswalk F-08; Q-02; §8. |

## 5. Hallazgos

### Índice

| ID | Severidad | Confianza | Bloqueante | Resumen |
|---|---|---|---|---|
| R-01 | MAJOR | Alta | Sí, DoR de US-012 | Falta referencia a NFR-004 en una historia que especifica una pantalla principal. |
| R-02 | MAJOR | Alta | Sí, DoR de US-015 y US-023 | Falta identificar NFR-009 como requisito aplicable en dos historias de la línea base. |

### R-01 — NFR de accesibilidad no identificado en US-012

- **Categoría:** DoR, accesibilidad y trazabilidad NFR.
- **Fuente e indicio:** `specs/EP-003-busqueda-de-habitaciones/spec.md`, US-012-AC-01…04 describe listado, paginación, estado vacío y acceso sin sesión; en la sección NFR enumera NFR-002, NFR-005 y NFR-006, pero no NFR-004. `specs/product/nfr-y-reglas.md`, NFR-004 exige accesibilidad básica en pantallas principales. El principio 5 de `.specify/memory/constitution.md` requiere considerar accesibilidad en las funcionalidades afectadas.
- **Observación:** US-012 define una pantalla principal e interacciones visibles sin identificar el requisito transversal de accesibilidad que gobierna etiquetas, teclado, contraste y errores.
- **Impacto:** el equipo no tiene el vínculo explícito para verificar accesibilidad en refinamiento/DoD; el criterio DoR “NFR aplicables identificadas” no queda satisfecho para US-012.
- **Acción sugerida:** enlazar NFR-004 desde US-012 o registrar una justificación de no aplicabilidad para esta pantalla, consistente con la constitución.
- **Confianza:** Alta; el alcance de NFR-004 y el contenido de la historia son explícitos.
- **Estado bloqueante:** Sí, para completar el DoR documental de US-012.

### R-02 — NFR-009 no identificado en US-015 y US-023

- **Categoría:** DoR, datos sintéticos y trazabilidad de restricciones.
- **Fuentes e indicios:** `specs/product/nfr-y-reglas.md`, NFR-009 prohíbe datos personales, académicos o de contacto reales. `specs/EP-003-busqueda-de-habitaciones/spec.md`, US-015-AC-01 expone datos del autor y la sección NFR solo enumera NFR-002 y NFR-004; no se encontró una restricción de datos sintéticos en US-015. `specs/EP-005-entrega-y-demo/spec.md`, US-023-AC-01 requiere un conjunto mínimo sintético y credenciales de prueba, pero la sección NFR identifica NFR-008 y no enlaza NFR-009.
- **Observación:** US-015 no explicita la restricción de datos sintéticos aplicable a usuarios/anuncios de prueba. US-023 menciona datos sintéticos en un criterio, pero no traza ese requisito al NFR global que prohíbe datos reales.
- **Impacto:** no se acredita de forma uniforme el control DoR de NFR y restricciones, ni queda trazado el alcance completo del requisito sobre datos de demostración y evidencia.
- **Acción sugerida:** añadir el vínculo NFR-009 y la restricción correspondiente en ambas historias, o justificar explícitamente una inaplicabilidad si el equipo considera que el criterio actual ya la cubre por completo.
- **Confianza:** Alta para la ausencia de referencias en las secciones de cada historia y para la aplicación de NFR-009 a los datos mostrados/generados.
- **Estado bloqueante:** Sí, para los controles DoR de US-015 y US-023.

## 6. Matriz DoR de historias detalladas

**Leyenda:** M = MET; NM = NOT_MET; NA = NOT_ASSESSABLE; NAP = NOT_APPLICABLE. En criterios de planificación, NAP-D significa que la historia está diferida según DEC-016 y no se evalúa para entrada a sprint. La evidencia de las celdas M se proporciona por fila y por historia en las fuentes citadas. Las celdas NA del primer criterio se deben a que no se suministraron Jira/ProjectLibre. Historias: 001=US-001, 002=US-002, 003=US-003, 006=US-006, 009=US-009, 012=US-012, 013=US-013, 015=US-015, 017=US-017, 023=US-023.

| Criterio actual del DoR | 001 | 002 | 003 | 006 | 009 | 012 | 013 | 015 | 017 | 023 | Evidencia de MET / explicación |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|
| ID único/estable coincidente en repo, Excel, ProjectLibre y Jira | NA | NA | NA | NA | NA | NA | NA | NA | NA | NA | Los IDs se encuentran en los specs y la exportación v01; no hay fuente de ProjectLibre/Jira para comprobar coincidencia total. |
| Asociada a épica y escrita en su spec.md | M | M | M | M | M | M | M | M | M | M | Las cabeceras de EP-001…EP-005 contienen las historias; specs EP-001 (001/002/003), EP-002 (006/009), EP-003 (012/013/015), EP-004 (017) y EP-005 (023). |
| Trazable a objetivo G y alcance S | M | M | M | M | M | M | M | M | M | M | Objetivo de cada historia y alcance de la épica: EP-001 S-01; EP-002 S-02/S-05; EP-003 S-03; EP-004 S-04; EP-005 S-06. |
| Tipo de usuario identificado | M | M | M | M | M | M | M | M | M | M | Relatos “Como…” nombran estudiante, oferente, demandante o evaluador en cada historia. |
| Capacidad en formato Como… quiero… para… | M | M | M | M | M | M | M | M | M | M | Relato de cada una de las diez historias en sus specs de épica. |
| Valor/beneficio comprensible | M | M | M | M | M | M | M | M | M | M | Relatos y campos Necesidad/Objetivo describen valor y conexión a objetivos en cada historia. |
| Límites claros y sin invadir EX-01…EX-05 | M | M | M | M | M | M | M | M | M | M | Boundaries de EP-001…EP-005 y objetivos/criterios; no se identificó conflicto con exclusiones para estas historias. |
| Al menos dos criterios US-XXX-AC-NN | M | M | M | M | M | M | M | M | M | M | US-001 (AC-01…06), 002 (01…04), 003 (01…04), 006 (01…05), 009 (01…04), 012 (01…04), 013 (01…05), 015 (01…03), 017 (01…05), 023 (01…03). |
| Criterios observables/verificables con datos sintéticos | M | M | M | M | M | M | M | M | M | M | M | AC y ejemplos/casos límite de las diez historias; NFR-009/las restricciones se citan en las historias salvo las omisiones identificadas en R-02. US-023-AC-01 exige set sintético. |
| Criterios describen comportamiento y no implementación | M | M | M | M | M | M | M | M | M | M | AC de las diez historias expresan resultados, validaciones y estados; no prescriben mecanismo técnico de implementación. |
| Reglas RN aplicables identificadas | M | M | M | M | M | M | M | M | M | M | Secciones Reglas de las historias: RN-01/02, RN-02/03, RN-02, RN-02/04…07, RN-07/08, RN-08, RN-08/10, RN-08, RN-02/08/09/11; US-023 declara que no hay regla específica. |
| Prioridad MoSCoW asignada y justificada | M | M | M | M | M | M | M | M | M | M | Prioridad y motivo de valor/riesgo/dependencias en cada spec. US-009 y US-013 son Should diferidas; US-023 es Must. |
| Estimación consensuada en Planning Poker | M | M | M | M | M | M | M | M | M | M | Cada historia declara estimación consensuada en Planning Poker 10/10/2026; exportación v01 muestra estimación consensuada/vigente coincidente. |
| Estimación cabe en un sprint; división si procede | M | M | M | M | NAP-D | M | NAP-D | M | M | Exportación v01: estimaciones 5, 5, 3, 5, 3, 3, 5, 3, 5 y 3 h-p. US-001/003/012/015/023 se asignan a una iteración con capacidad suficiente. US-002/006/017 satisfacen excepción documentada; US-009/013 no están planificadas. |
| DEC-018, cuando aplica: máximo dos iteraciones, capacidad y justificación | NAP | M | NAP | M | NAP-D | NAP | NAP-D | NAP | M | NAP | US-002: IT-001–002, 5 h-p frente a 13,91 h-p, justifica pérdida de valor al separar envío/uso y cierra al final de IT-002. US-006: IT-003–004, 5 frente a 9,91 h-p; IT-003 tiene 3,95 y dividir publicación pierde valor; termina IT-004. US-017: IT-005–006, 5 frente a 11,91; tras US-015 no caben las 5 h-p restantes del tramo de IT-005, dividir el primer mensaje pierde valor; termina IT-006. Todas consecutivas y de dos iteraciones como máximo. |
| Incertidumbre indicada; plan de reducción si es Alta | M | M | M | M | M | M | M | M | M | M | Todas indican Baja o Media en incertidumbre; ninguna Alta. |
| Predecesoras terminadas o planificadas antes en el sprint | M | M | M | M | NAP-D | M | NAP-D | M | M | M | Exportación v01, columna Orden de predecesoras: 0 incidencias. US-002/US-012/US-017/US-023 pueden compartir sprint con predecesora, con orden marcado OK; las demás precedencias son de iteraciones anteriores o inexistentes. US-009/013 diferidas. |
| NFR aplicables identificadas | M | M | M | M | M | NM | M | NM | M | NM | EP-001/US-001: NFR-001/004/007; US-002: 001/004/007/009; US-003: 001/004/007. EP-002/US-006: 002/004/007; US-009: NFR-004 y referencia NFR-007 en caso límite. EP-003/US-012: 002/005/006, falta 004 (R-01); US-013: 004/005/007/009; US-015: 002/004, falta 009 (R-02). EP-004/US-017: 002/004/007. EP-005/US-023: 008, falta 009 (R-02). |
| Restricciones técnicas/organizativas/proyecto, incluidos datos sintéticos | M | M | M | M | M | M | M | NM | M | M | Restricciones explícitas de dominio/buzón/datos sintéticos/tecnología en las historias salvo US-015, que no identifica la restricción NFR-009 de datos sintéticos (R-02). US-023-AC-01 exige set sintético. |
| Datos sintéticos disponibles o plan para prepararlos | M | M | M | M | M | M | M | M | M | M | Datos de ejemplo, buzón de prueba, publicación previa, dataset de demo o guion de preparación descritos en cada historia; para US-023, AC-01 prevé crear el set mínimo. |
| Ninguna duda que impida entender/planificar | M | M | M | M | M | M | M | M | M | M | Criterios, límites y dependencias definidos. La respuesta en conversaciones ya existentes al cerrar anuncio queda aplazada entre US-009/US-018 (DEC-016) y no bloquea la línea base actual. |
| Supuestos/cuestionamientos no bloqueantes documentados | M | M | M | M | M | M | M | M | M | M | Supuestos/contexto de riesgos en cada historia; las historias sin supuesto específico mantienen reglas y dependencias expresas. |

**Nota sobre tamaño/DEC-018:** la capacidad por iteración exportada es 6,95 h-p (IT-001/002), 3,95 (IT-003), 5,95 (IT-004/005/006); capacidad agregada de los tramos de las excepciones: R1 13,91; IT-003+004 9,91; IT-005+006 11,91 h-p. Exportación v01 refleja cero sobrecargas, cero horas sin asignar, cero historias que incumplen tamaño y tres usos de DEC-018. Las cargas por persona/iteración están bajo capacidad. Estos datos acreditan el plan exportado; no sustituyen la validación humana de Jira/ProjectLibre ni demuestran entrega/DoD.

## 7. Candidatos de omisión, preguntas de stakeholders y fuera de alcance

Estos elementos no son defectos adicionales confirmados; se separan de R-01/R-02.

### Revisión de catálogo

| Historia | Estado frente a DEC-016 / observación de consistencia |
|---|---|
| US-003 | En línea base R1; depende de US-001, precedencia anterior a otras historias confirmada en la exportación. |
| US-004 | Fuera de línea base; el perfil público está alineado con la versión corregida de NFR-002. |
| US-005 | Fuera de línea base; depende de US-002/US-003 y reutiliza el buzón de pruebas de US-002. |
| US-006 | En línea base R2; habilita listado, cierre y datos de anuncios; usa DEC-018. |
| US-007 | Fuera de línea base; dependencia US-006 y almacenamiento de ficheros no planificado. |
| US-008 | Fuera de línea base; reutiliza validaciones de US-006. |
| US-009 | Fuera de línea base; primera candidata Should, precedida por US-006; cierre definitivo conforme a RN-08. Pregunta de conversación cerrada en Q-02. |
| US-010 | Fuera de línea base; depende de US-006 y sus acciones se incorporan según US-008/009/018. |
| US-011 | Fuera de línea base; cierre automático depende de US-009, sin conflicto con el MVP acordado. |
| US-012 | En línea base R2; base de búsqueda con paginación; omisión de NFR-004 registrada en R-01. |
| US-013 | Fuera de línea base; depende de US-012 y regla de fechas fijada por RN-10; validación de cobertura parcial pendiente al incorporarla. |
| US-014 | Fuera de línea base; depende de US-012 y comparte lista de zonas TD-03. |
| US-015 | En línea base R3; detalle/estado depende de US-012; falta enlace a NFR-009 (R-02). |
| US-016 | Fuera de línea base; depende de US-012/US-013 y respeta precedencia de cobertura completa fijada por RN-10. |
| US-017 | En línea base R3; depende de US-015/US-002 y habilita el guion US-023; usa DEC-018. |
| US-018 | Fuera de línea base; depende de US-017; respuesta en conversaciones de anuncios cerrados sigue aplazada (Q-02). |
| US-019 | Fuera de línea base; depende de US-018 y mide conversaciones con mensajes sin leer. |
| US-020 | Fuera de línea base; reportes quedan registrados, pero su moderación no se incluye en esta historia. |
| US-022 | Fuera de línea base; US-023 prepara dataset mínimo; carga amplia y medición de NFR-005 quedan diferidas. |
| US-023 | En línea base R3; guion necesario para G-04/M-04, depende de US-017; enlace NFR-009 pendiente (R-02). |
| US-024 | Won't por DEC-016; no se planifica y requiere cambio humano si se reconsidera. |

| Elemento | Clasificación/estado | Base y pregunta humana |
|---|---|---|
| US-007 fotos; US-011 cierre automático; US-020 reportes; US-004 perfil | Fuera de la línea base por DEC-016; permanecen en catálogo según decisiones humanas | Registro de decisiones §3 registra aceptación de excluirlos de la línea base. Si se propone incorporarlos, ¿se revisará su alcance/plan según Gestión de cambios de `goals.md`? |
| US-024 interfaz inglesa | OUT_OF_SCOPE para el plan actual; Won't por DEC-016 | EP-005 lo excluye y `goals.md` dice que no forma parte de S-01…S-06. No se recomienda reabrir sin cambio de alcance humano. |
| US-023 guion de evaluación | IN_SCOPE, confirmado e incluido en línea base R3 | G-04/M-04, NFR-008 y S-06; decisión DEC-016, dependencia US-017. La cabecera de EP-005 declara S-06. |
| US-013 coberturas parciales | IN_SCOPE_CANDIDATE aceptado con validación del cliente aún prevista | RN-10 define intersección y US-013 tiene casos límite; el supuesto dice que el cliente validará cuando se incorpore. ¿Se registrará el resultado al entrar en una release? Está fuera de línea base por DEC-016. |
| Anuncio cerrado y conversaciones existentes (US-009/US-018) | SCOPE/behavior question DEFERRED por el registro humano | US-009-AC-03 permite lectura; US-018-AC-02 permite responder y AC-04 permite leer conversación cerrada. El registro aplaza la decisión hasta que ambas historias entren en la línea base. ¿Se permitirán nuevas respuestas después del cierre? |
| M-05 sin historia asignada | Pregunta de trazabilidad pendiente, no defecto confirmado | `goals.md` define M-05 como ausencia de defectos bloqueantes al cierre de cada release; DoD también exige no dejar incidencias bloqueantes. ¿Dónde se registrará la evidencia de medición de M-05? |
| US-021 valoración | OUT_OF_SCOPE de esta revisión por instrucción; además figura Won't en catálogo | No se evaluó su contenido ni preparación. |

No se propone nueva funcionalidad ni se atribuye una necesidad nueva a stakeholders. Stakeholders definidos en `goals.md`: SH-01 Delegación, SH-02 oferentes, SH-03 demandantes, SH-04 profesorado y SH-05 equipo.

## 8. Dependencias, supuestos y trazabilidad

- **Plan y capacidad:** la exportación v01 sitúa las ocho historias de la línea base DEC-016 (US-001, US-002, US-003, US-006, US-012, US-015, US-017, US-023) en 32 h-p; capacidad efectiva total 35,72 h-p. La hoja Resumen informa 13/13,91 h-p en R1, 8/9,91 en R2 y 11/11,91 en R3. La comprobación exportada: plan viable, cero sobrecargas, cero trabajo sin asignar y cero predecesoras mal ordenadas.
- **US-002:** depende de US-001, compartiendo IT-001 con orden marcado OK; US-003 está en IT-002 y el plan justifica distribuir R1 en dos iteraciones. La dependencia técnica TD-02 queda en IT-002. No se identifica ciclo.
- **US-006:** US-002/US-003 están en iteraciones anteriores a IT-003; la excepción cubre IT-003–004. US-012 se termina en IT-004 después de US-006 según la columna de orden.
- **US-017:** US-015 y US-002 son predecesoras; la exportación marca orden OK. US-009 condiciona el caso de anuncio cerrado según la historia, pero está fuera de la línea base. La evidencia permite tratarlo como condición/contrato de estado, no como una predecesora de planificación confirmada para el incremento actual.
- **US-023:** la historia y la exportación ya listan US-017 como predecesora, completada antes de US-023 en IT-006; la propuesta previa F-08 queda resuelta.
- **US-009/US-013:** Should y diferidas según DEC-016. Las dependencias funcionales US-006→US-009 y US-012→US-013 están descritas; no se atribuye un orden de sprint a historias no planificadas.
- **Supuestos:** disponibilidad de buzón de pruebas, evaluador externo (US-023) y las tecnologías aprobadas siguen siendo supuestos/dependencias explícitos; su materialización no puede probarse en esta revisión documental.
- **Trazabilidad:** las diez historias tienen objetivo y épica; las épicas refieren S-01…S-06. R-01/R-02 son deficiencias de enlace NFR dentro de historias, no ausencia de NFR global. La alineación del ID con Jira/ProjectLibre queda NOT_ASSESSABLE.
- **DoD:** se leyó `definition-of-done.md`; no se afirma que ninguna historia esté implementada, verificada o aceptada.

## 9. Preguntas para humanos

1. **DoR de línea base:** ¿se añaden NFR-004 a US-012 y NFR-009 a US-015/US-023, o se documenta por qué alguna no aplica? Esto resuelve R-01 y R-02.
2. **Conversaciones cerradas:** cuando US-009 y US-018 vuelvan a considerarse para línea base, ¿se permitirá responder en una conversación existente tras cerrar el anuncio, o solo leerla? La decisión sigue aplazada por DEC-016.
3. **Herramientas de planificación:** ¿puede el equipo confirmar con Jira y ProjectLibre la coincidencia de los IDs de las diez historias? La exportación solo verifica el repositorio/Excel.
4. **M-05:** ¿qué artefacto recogerá la evidencia de “cero defectos bloqueantes” al final de cada release, dado que el registro de decisiones lo deja pendiente?
5. **US-013:** ¿se registrará en la Review correspondiente la validación del cliente sobre cobertura parcial antes de incorporar la historia?

## 10. Crosswalk RECHECK

| Hallazgo previo | Decisión humana y evidencia actual | Estado RECHECK |
|---|---|---|
| v01 F-01 privacidad/NFR-002 | Aceptado; NFR-002 limita datos públicos y US-004-AC-02 se alinea. US-015/US-017 publican solo los datos permitidos. | RESOLVED |
| v01 F-02 confirmación de contraseña | Aceptado; US-001-AC-06 define rechazo, mensaje y ausencia de creación de cuenta; ejemplo añadido. | RESOLVED |
| v01 F-03 enlaces NFR en US-002/US-013 | Aceptado; US-002 lista NFR-004 y US-013 lista NFR-009, además de NFR-004/005/007. | RESOLVED para los dos casos originales. Los nuevos casos de US-012/015/023 se registran separadamente como R-01/R-02. |
| v01 F-04 fechas inclusivas/cobertura parcial | Aceptado; RN-10 fija inclusividad e intersección; US-013 incluye casos límite. | RESOLVED |
| v01 F-05 Planning Poker | Aceptado; las diez historias detalladas declaran estimaciones consensuadas el 10/10 y la exportación presenta estimación consensuada/vigente. | RESOLVED |
| v01 F-06 capacidad/DEC-018 | Aceptado por DEC-016; exportación v01 acredita capacidades, cargas, tres excepciones y cero incumplimientos de tamaño. Los specs justifican explícitamente US-002/006/017. | RESOLVED |
| v01 F-07 trazabilidad/extensiones de alcance | Aceptado; DEC-016 pone US-004/007/011/020 fuera de línea base y US-024 Won't; US-023 está en línea base. `goals.md` refleja la línea base. | RESOLVED |
| v01 F-08 orden/dependencias | Aceptado; exportación informa cero predecesoras mal ordenadas; US-023 lista US-017. US-009 sigue fuera de línea base; se considera condición de estado, no predecesora confirmada de US-017. | RESOLVED |
| v01 §7 conversación tras cierre | DEC-016 aplaza la decisión porque US-009/US-018 están fuera de línea base; US-018 permanece fuera de la línea base actual. | DEFERRED |
| v01 §8 M-05 | Registro mantiene la cuestión pendiente; `goals.md` y DoD definen el resultado, no el artefacto de evidencia. | OPEN |
| v01 §9 validación de correo mal formado | Aceptado; US-001 incluye caso límite y mensaje “Introduce un correo válido”. | RESOLVED |

Los hallazgos nuevos de este informe conservan IDs nuevos R-01/R-02; no se reutilizan IDs del informe previo.

## 11. Cierre

**Esta es una revisión consultiva. No se cambió ningún requisito fuente, prioridad, estimación, plan ni decisión humana. No se aprobó ningún baseline ni gate.**

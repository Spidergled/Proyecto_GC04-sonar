# Auditoría de planificación H4 — ErasmusNest

## 1. Metadatos

- **Proyecto:** ErasmusNest (Proyecto_GC04, Practical Software).
- **Agente:** Project Auditor, versión **1.0 (2026-10-16)**.
- **Fecha:** 2026-10-16.
- **Snapshot indicado:** `main`, commit `16913c1` (declarado por la solicitud; no verificado mediante Git).
- **Baseline y horizonte:** DEC-016; US-001, US-002, US-003, US-006, US-012, US-015, US-017 y US-023; 32 h-p; IT-001…IT-006, 21/10/2026–01/12/2026.
- **Planificación:** `docs/project/planning/excel-export-v02.md` (v02); `docs/project/planning/projectlibre/ErasmusNest_GC04_por_sprints.xml`; `docs/project/planning/projectlibre/ErasmusNest_GC04_por_epicas.xml`; decisiones DEC-022 en `LEEME.md`.
- **Riesgos:** `docs/project/risks/riesgos.md`, versión 1, 16/10/2026.
- **Especificación y calidad:** specs EP-001…EP-005; `specs/product/goals.md` (baseline DEC-016); `specs/product/nfr-y-reglas.md` (requisitos-v0); DoR y DoD en `docs/quality/`.
- **Revisiones/decisiones previas:** `requirements-report-v02.md`, `initial-planning-review-v01.md` y `registro-ia.md`. Se consideraron las clasificaciones humanas y no se reabren hallazgos aceptados; se comprueba si los cambios acordados aparecen en los artefactos H4.
- **Informes anteriores de auditoría:** ninguno localizado; este es v01.
- **Límite de evidencia:** no se ejecutaron comandos ni se inspeccionaron los `.pod` binarios. `LEEME.md` declara que los XML y `.pod` incorporan la corrección de hitos del 16/10; el análisis ProjectLibre se basa en los XML legibles y esa declaración.

## 2. Resumen

**Resultado: CONFORME_CON_OBSERVACIONES.** No se identifican no-conformidades. Se registran **2 observaciones menores**, que requieren responsable y disparador de validación. La línea base suma 32 h-p, no presenta sobreasignación en ninguna combinación persona-sprint, y el coste recalculado es 2.507,64 €. Los XML tienen las dependencias y los hitos corregidos según DEC-022. La principal cautela es que el modelo de duración sprint-completo y los enlaces SS dejan el orden intrassprint fuera de la evidencia temporal visible en ProjectLibre.

## 3. Matriz de resultados

| Dimensión / comprobación | Estado | Evidencia | Límite / impacto |
|---|---|---|---|
| Riesgos: formato completo (situación, evento, consecuencia, tratamiento, probabilidad, impacto, responsable) | OK | `riesgos.md`, RSK-01…RSK-12; cada fila contiene los campos. | Ninguno observado. |
| Riesgos: cuellos de botella, capacidad reducida, ruta crítica e incertidumbre | OK | RSK-01…RSK-05 y RSK-09; contrastados con Carga/Capacidad del export v02. | Los riesgos reflejan la holgura mínima, IT-003, gestión, incertidumbre y ausencias no previstas. |
| Riesgos: dependencias externas y sus fechas | OBSERVATION | RSK-06 (EXT-01, antes del 21/10) y RSK-08 (EXT-03, antes de IT-005); RSK-07 identifica EXT-02, responsable Gled y mitigación. `registro-ia.md`, IPR-v01-003 aceptado, especifica EXT-01/02 antes del 21/10. | RSK-07 no repite una fecha límite explícita para EXT-02; véase AUD-v01-001. |
| ProjectLibre: calendario y horizonte | OK | XML: calendario lunes-viernes, 08:00–12:00 y 13:00–17:00; IT-001…IT-006 del 21/10 al 01/12/2026. | El 2/11 figura laborable conforme a DEC-022; no se trata como defecto. |
| ProjectLibre: recursos, unidades y tarifas | OK | Recursos XML: Hugo/Gled/José, `MaxUnits=0.125`; tarifas 35/40/32 €/h-p, coincidentes con Coste. | DEC-022 explica el límite de 5 h nominales/semana y la moneda mostrada por ProjectLibre. |
| ProjectLibre: tareas, trabajo, duraciones, gestión e hitos | OK | XML por sprints y por épicas: tareas de línea base, 4 tareas MGT por IT, y R1/R2/R3 como hitos de duración cero. Enlaces de R2 y R3 incluyen las dos historias/tramos que terminan cada release. | Duración de tareas = sprint completo, conforme a DEC-022. No se añade *Project grooming*. |
| ProjectLibre: dependencias y asignaciones | OK | XML contiene las 9 dependencias aplicables de la hoja Dependencias; estimaciones, trabajo y asignaciones por persona/historia/IT coinciden con el export v02 en los registros revisados. | US-002, US-006 y US-017 son las únicas partidas en dos IT; sus specs justifican DEC-018. |
| ProjectLibre: dos vistas equivalentes | OK | Los XML por sprints y épicas conservan las mismas historias, trabajo, fechas y costes; cambia la agrupación. Coste raíz de ambos: 2.507,64 €. | La equivalencia de los `.pod` no se verificó binariamente; véase límite de evidencia. |
| Asignación: capacidad efectiva recalculada | OK | Hojas Capacidad, Planificación y Gestión del export v02; tabla aritmética §5. | Capacidad de desarrollo más gestión se contrasta contra disponibilidad neta tras reserva y ausencias. |
| Asignación: carga total nunca supera disponibilidad | OK | 18 celdas persona-IT, recalculadas en §5; carga incluye desarrollo + MGT. Mínima holgura: José, 0,012 h-p en IT-001, IT-003 e IT-005. | Holguras <0,1 h-p están cubiertas por DEC-020: revisión del SM en cada Sprint Planning. |
| Asignación: esfuerzo, duración, disponibilidad y coste | OK | Asignaciones XML de tareas/story y rates cotejados con Planificación y Coste; sumas en §5. | La duración es un contenedor temporal de sprint, no una indicación de dedicación diaria (DEC-022). |
| Secuencia: predecesoras, ciclos y prerrequisitos | OBSERVATION | 9 enlaces no cíclicos; los externos EXT-01…03 están identificados en requisitos/riesgos. Los enlaces SS son explícitos en LEEME/DEC-022. | Un SS permite iniciar simultáneamente; no demuestra el orden de ejecución dentro del sprint. Véase AUD-v01-002. |
| Secuencia: ruta crítica, hitos y márgenes | OK | Dependencias de las 8 historias y fechas por IT; R1/R2/R3 enlazados a sus entregables respectivos. Margen y holgura por sprint en §5 y §6. | R1 y R2 son hitos de release, no predecesores del camino hasta R3; el modelo no exporta holgura temporal calculada. |

## 4. Hallazgos

### AUD-v01-001 — Fecha límite de EXT-02 no explicitada en RSK-07

- **Dimensión / severidad:** riesgos / MINOR, observación.
- **Evidencia:** `riesgos.md`, RSK-07 registra EXT-02, responsable Gled y mitigación por enlace simulado en base de datos, pero no fecha. `registro-ia.md`, disposición aceptada de IPR-v01-003, indica confirmar EXT-01 y EXT-02 antes del 21/10 (IT-001). US-002 está planificada entre IT-001 e IT-002 y depende del buzón de pruebas (EP-001, US-002; export v02, hoja Dependencias).
- **Impacto:** el equipo conoce el prerrequisito y su responsable, pero el propio registro de riesgos no ofrece una fecha de control trazable para comprobar disponibilidad o activar a tiempo la mitigación.
- **Acción recomendada:** reflejar en el seguimiento de RSK-07 la fecha límite humana ya aceptada, sin cambiar la mitigación acordada.
- **Pregunta al equipo:** ¿queda confirmado el buzón de pruebas o activada la simulación antes del inicio de IT-001?
- **Responsable sugerido:** Gled (TL).
- **Disparador de validación:** antes del 21/10/2026, previo al primer Sprint Planning.

### AUD-v01-002 — Orden intrassprint no visible en los enlaces SS

- **Dimensión / severidad:** secuenciación / MINOR, observación.
- **Evidencia:** los XML asignan duración sprint-completo a cada tarea. Las dependencias intrassprint US-001→US-002 (parte 1), US-006 (parte 2)→US-012, US-015→US-017 (parte 1) y US-017 (parte 2)→US-023 son SS (`Type=3`); por tanto, sus sucesoras pueden comenzar a la vez que las predecesoras. DoR exige que la predecesora esté terminada o planificada antes dentro del mismo sprint. `LEEME.md` dice que el orden intrassprint lo marcan las tareas TK, pero los XML de ProjectLibre se detienen en historias; el export detalla tareas únicamente para IT-001 y remite las demás al Sprint Planning.
- **Impacto:** el plan semanal puede ser viable, pero el XML no prueba por sí solo el orden necesario para US-006→US-012 ni US-017→US-023. Esto hace menos visible el riesgo de terminar US-023 dentro de IT-006 tras US-017.
- **Acción recomendada:** conservar el modelado DEC-022 y asegurar que el orden de ejecución de las tareas quede concretado en el Sprint Planning correspondiente; no se propone replanificar ni alterar enlaces aquí.
- **Pregunta al equipo:** ¿quedará registrado el orden de trabajo de US-006/US-012 y US-017/US-023 antes de comprometer esos sprints?
- **Responsable sugerido:** PO y SM, con validación del TL para las dependencias técnicas.
- **Disparador de validación:** al cerrar Sprint Planning de IT-004 e IT-006, respectivamente.

## 5. Aritmética: capacidad, carga y coste

### Capacidad y carga por sprint

La capacidad efectiva del export es capacidad disponible para desarrollo, una vez descontadas ceremonias, coordinación, contingencia y ausencias. Para comparar carga total, se añade la gestión (6,46 h por IT); la disponibilidad resultante equivale a capacidad de desarrollo + MGT. Las cargas se recomputan a partir de las filas persona/historia y tareas MGT, no de los totales publicados.

| IT | Capacidad desarrollo (h-p) | Desarrollo asignado (h-p) | MGT (h) | Capacidad total con MGT (h-p) | Carga total (h-p) | Holgura (h-p) |
|---|---:|---:|---:|---:|---:|---:|
| IT-001 | 6,95 | 6,80 | 6,46 | 13,41 | 13,26 | 0,15 |
| IT-002 | 6,95 | 6,20 | 6,46 | 13,41 | 12,66 | 0,75 |
| IT-003 | 3,95 | 3,80 | 6,46 | 10,41 | 10,26 | 0,15 |
| IT-004 | 5,95 | 4,20 | 6,46 | 12,41 | 10,66 | 1,75 |
| IT-005 | 5,95 | 5,80 | 6,46 | 12,41 | 12,26 | 0,15 |
| IT-006 | 5,95 | 5,20 | 6,46 | 12,41 | 11,66 | 0,75 |
| **Total** | **35,70*** | **32,00** | **38,76** | **74,46*** | **70,76** | **3,70*** |

\* Las capacidades mostradas por sprint están redondeadas a 0,01 h-p. Con los valores subyacentes del export (capacidad total 35,72 h-p), la disponibilidad sumada es 74,48 h-p y la holgura total **3,72 h-p**. Diferencias inferiores a 0,01 h-p se deben al redondeo y se consideran OK.

### Carga total por persona y sprint

Cada celda muestra **carga desarrollo + MGT / disponibilidad neta / holgura**, en h-p. Disponibilidad neta = capacidad efectiva para desarrollo más la asignación MGT correspondiente; así se contrasta toda la carga contra capacidad sin contar dos veces ceremonias/gestión.

| Persona | IT-001 | IT-002 | IT-003 | IT-004 | IT-005 | IT-006 | Totales: desarrollo / MGT / carga / disponibilidad / holgura |
|---|---|---|---|---|---|---|---|
| Hugo | 4,370 / 4,414 / 0,044 | 4,170 / 4,414 / 0,244 | 2,870 / 2,914 / 0,044 | 4,070 / 4,414 / 0,344 | 3,370 / 3,414 / 0,044 | 4,070 / 4,414 / 0,344 | 10,50 / 12,42 / 22,92 / 23,984 / 1,064 |
| Gled | 4,170 / 4,2675 / 0,0975 | 3,970 / 4,2675 / 0,2975 | 2,670 / 2,7675 / 0,0975 | 3,670 / 4,2675 / 0,5975 | 4,170 / 4,2675 / 0,0975 | 3,170 / 3,2675 / 0,0975 | 9,40 / 12,42 / 21,82 / 23,105 / 1,285 |
| José | 4,720 / 4,732 / 0,012 | 4,520 / 4,732 / 0,212 | 4,720 / 4,732 / 0,012 | 2,920 / 3,732 / 0,812 | 4,720 / 4,732 / 0,012 | 4,420 / 4,732 / 0,312 | 12,10 / 13,92 / 26,02 / 27,392 / 1,372 |
| **Equipo** | **13,260 / 13,4135 / 0,1535** | **12,660 / 13,4135 / 0,7535** | **10,260 / 10,4135 / 0,1535** | **10,660 / 12,4135 / 1,7535** | **12,260 / 12,4135 / 0,1535** | **11,660 / 12,4135 / 0,7535** | **32,00 / 38,76 / 70,76 / 74,481 / 3,721** |

Los totales detallados de holgura usan las cifras mostradas de capacidad subyacente; por redondeo de celdas, los totales publicados al totalizar las fuentes son Hugo 1,06 h-p, Gled 1,28 h-p, José 1,37 h-p y equipo 3,72 h-p. La menor holgura individual exacta indicada por la fuente es José 0,012 h-p; siguen Hugo 0,044 h-p y Gled 0,0975 h-p. Ninguna celda es negativa. Las 10 celdas AJUSTADAS quedan bajo la regla de control DEC-020.

### Coste recalculado

Tarifas empleadas y justificadas en Coste/XML: PO 35 €/h-p, TL 40 €/h-p, SM 32 €/h-p. Coste = horas asignadas × tarifa; la gestión se incluye.

| Persona | Desarrollo (h-p) | Coste desarrollo (€) | Gestión (h) | Coste gestión (€) | Coste total (€) |
|---|---:|---:|---:|---:|---:|
| Hugo (PO) | 10,50 | 367,50 | 12,42 | 434,70 | 802,20 |
| Gled (TL) | 9,40 | 376,00 | 12,42 | 496,80 | 872,80 |
| José (SM) | 12,10 | 387,20 | 13,92 | 445,44 | 832,64 |
| **Total** | **32,00** | **1.130,70** | **38,76** | **1.376,94** | **2.507,64** |

| IT | Desarrollo (€) | Gestión (€) | Total (€) |
|---|---:|---:|---:|
| IT-001 | 241,30 | 229,49 | 470,79 |
| IT-002 | 219,90 | 229,49 | 449,39 |
| IT-003 | 128,80 | 229,49 | 358,29 |
| IT-004 | 153,20 | 229,49 | 382,69 |
| IT-005 | 206,30 | 229,49 | 435,79 |
| IT-006 | 181,20 | 229,49 | 410,69 |
| **Proyecto** | **1.130,70** | **1.376,94** | **2.507,64** |

La suma por personas, por IT y el coste raíz de ambos XML coincide; no hay diferencia superior a 0,01 €.

## 6. Secuencia, camino crítico y holgura

La red de precedencias de la línea base no presenta ciclos y los nueve enlaces aplicables del Excel aparecen en los XML. La ruta de precedencias hacia el cierre R3 pasa por **US-001 → US-002 → US-006 → US-012 → US-015 → US-017 → US-023 → R3**; además, **US-001 → US-003 → US-006** es una rama convergente que también condiciona US-006. Con las asignaciones por iteración, ambas ramas llegan a US-006 antes de IT-003, y las historias siguientes ocupan IT-003…IT-006.

R3 es el hito de fin del proyecto (01/12/2026) y depende de US-017, parte 2, y US-023. R1 (03/11) y R2 (17/11) dependen de las historias de sus releases, pero no enlazan como predecesoras con la siguiente etapa ni con R3; son checkpoints de release, no tareas del camino de precedencias terminal. El XML no contiene holgura temporal calculada por tarea, así que no se atribuyen días de float; la holgura aquí verificada es de capacidad, en §5.

La reserva del plan es 9,52 h-p frente a 90 h nominales; hay 6 h de ausencias fechadas. Tras descontar reserva, ausencias, gestión y carga de desarrollo, el margen comprobado de la línea base es 3,72 h-p. La contingencia de las celdas AJUSTADAS se controla en cada Sprint Planning por el SM según DEC-020. El festivo del 2/11 permanece laborable por DEC-022; no se reabre esa decisión.

## 7. Fuentes y límites

- Se tomaron como fuentes de planificación y ProjectLibre las versiones indicadas en el manifiesto; `LEEME.md` documenta que la corrección del 16/10 añadió las predecesoras faltantes de R2 y R3 en script, XML y `.pod`, sin cambiar fechas, horas ni coste.
- Las decisiones aceptadas de revisiones anteriores se trataron como decisiones humanas. Este informe no modifica ni propone cambiar requisitos, estimaciones, prioridades, riesgos, asignaciones, rates, fechas ni decisiones.
- No se verificó el commit mediante Git ni se abrieron ProjectLibre/Excel binarios; se usaron el commit indicado y la exportación textual como entradas, respetando los límites de ejecución.

## 8. Declaración de límites

Las fuentes no se modificaron. **Solo se creó este informe** en `docs/project/reviews/audit/audit-report-v01.md`. No se aprobó una línea base, un plan, una asignación, una decisión ni un gate de calidad.

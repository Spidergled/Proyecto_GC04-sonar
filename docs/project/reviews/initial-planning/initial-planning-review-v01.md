# Revisión de consistencia de planificación v01

## 1. Metadatos de ejecución

- **Proyecto:** ErasmusNest (Proyecto_GC04, Practical Software).
- **Modo:** CONSISTENCY.
- **Agente:** Initial Planning Reviewer, versión 1.2 (2026-10-14).
- **Hipótesis humana revisada:** hoja *Planificación* del Excel, exportada en `docs/project/planning/excel-export-v01.md`; propuesta DEC-020 y campos «Plan inicial» de las specs.
- **Baseline y alcance:** DEC-016; US-001, US-002, US-003, US-006, US-012, US-015, US-017 y US-023; 32 h-p.
- **Horizonte:** IT-001 a IT-006, 21/10/2026 a 01/12/2026; sprints de una semana, miércoles a martes.
- **Commit declarado:** `45e3489` (no verificado contra Git).
- **Disposición de requisitos:** `requirements-report-v02.md` y `docs/project/reviews/registro-ia.md`. El informe v02 identifica correcciones R-01/R-02; el registro consigna que fueron aceptadas y corregidas.
- **Exportación de planificación:** `excel-export-v01.md`.
- **Informe previo de esta serie:** no localizado; este es el primer informe `initial-planning-review-v01.md`.
- **Fecha de revisión:** 2026-10-14.
- **Fuentes revisadas:** export v01; specs EP-001 a EP-005; `goals.md`; `nfr-y-reglas.md`; Definition of Ready; Definition of Done; `requirements-report-v02.md`; `registro-ia.md`.
- **Límites de acceso:** no se abrió el Excel binario, ni se verificó el commit, Jira, ProjectLibre, código, ejecución, pruebas o disponibilidad efectiva de personas externas.

## 2. Resumen

**Resultado consultivo: READY_WITH_NON_BLOCKING_QUESTIONS.**

La hipótesis es consistente y revisable para construir una hipótesis de planificación: las ocho historias suman 32 h-p, toda la carga está asignada, no hay celdas en sobrecarga, el orden de las predecesoras está marcado como correcto y la capacidad efectiva exportada suma 35,72 h-p. El margen agregado es 3,72 h-p y la ocupación es 90 %.

No se identifica un bloqueo documental que invalide la coherencia de la hipótesis. Quedan **0 hallazgos bloqueantes**, pero sí condiciones de control: los márgenes individuales mínimos son muy estrechos (0,01 h-p en José en IT-001, IT-003 e IT-005; 0,04 h-p en Hugo en IT-001, IT-003 e IT-005), el export redondea a dos decimales y no hay evidencia de validación externa de Jira/ProjectLibre ni de la localización del evaluador externo requerido antes de IT-005. El commit declarado para esta revisión (`45e3489`) tampoco coincide con el commit consignado en el informe de requisitos v02 (`86e995f`); debe aclararse la relación entre ambos snapshots.

Este resultado expresa preparación para revisar una hipótesis, no aprobación de baseline, calendario, asignación ni puerta de calidad.

## 3. Inventario de evidencia y matriz de compatibilidad

| Dimensión | Estado | Evidencia | Límite |
|---|---|---|---|
| Alcance y prioridades | Compatible | DEC-016, `goals.md` y export v01 identifican exactamente las 8 historias y 32 h-p. US-024 queda Won't y las historias diferidas quedan fuera. | No se verificó el commit `45e3489`.
| Estimaciones y unidades | Compatible | Specs y export indican Planning Poker del 10/10/2026; las estimaciones vigentes son 5, 5, 3, 5, 3, 3, 5 y 3 h-p. | No se inspeccionó la Wiki ni el libro binario.
| DoR y requisitos | Compatible con evidencia actual | `requirements-report-v02.md` registra R-01/R-02 como corregidos en el registro; las 8 specs citan los NFR relevantes, incluido NFR-009. | La coincidencia con Jira/ProjectLibre no puede verificarse aquí.
| Capacidad por persona e iteración | Compatible, margen muy ajustado | La hoja Carga y la hoja Planificación muestran carga <= capacidad en las 18 celdas persona x iteración. | Los valores se muestran redondeados; el export indica que el cálculo subyacente conserva más precisión.
| Sobrecargas | Sin sobrecarga observada | Comprobación global: 0 celdas en SOBRECARGA; estado OK por persona e iteración. | Es una comprobación documental, no una medición de ejecución real.
| Dependencias y orden | Compatible en el export | Comprobación: 0 predecesoras mal ordenadas; US-002, US-006 y US-017 documentan DEC-018; US-023 depende de US-017. | TD-02, TD-03, TD-04 y EXT-03 requieren materialización durante la ejecución.
| Horizonte | Compatible | IT-001…IT-006 cubren 21/10–01/12/2026, una semana cada iteración. | No se aporta una evidencia independiente de festivos o cambios de disponibilidad.
| Margen | Compatible, sensible | Margen total 3,72 h-p; R1 0,91, R2 1,91 y R3 0,91 h-p. | El margen no se distribuye libremente entre personas o semanas.
| Coste | Compatible con tarifas declaradas | Coste de desarrollo 1.130,70 €, gestión 1.376,94 €, total 2.507,64 €. Tarifas PO 35, TL 40 y SM 32 €/h-p, acordadas el 14/10/2026. | No se declaró presupuesto ni se verificó la decisión fuera del export.
| Supuestos y entradas externas | Pregunta no bloqueante | SU-07, buzón de pruebas y EXT-03 están identificados; US-023 exige localizar el evaluador antes de IT-005. | No existe evidencia de que esas entradas estén ya disponibles.
| Versiones/snapshots | Inconsistencia a aclarar | La invocación declara `45e3489`; el informe de requisitos v02 consigna `86e995f`. | No se puede afirmar que todos los documentos pertenezcan al mismo snapshot.

## 4. Hallazgos

### IPR-v01-001 — Snapshot de revisión no unificado

- **Categoría:** trazabilidad y baseline.
- **Severidad:** MINOR.
- **Bloqueante:** NO.
- **Fuente/ID:** commit declarado `45e3489`; `requirements-report-v02.md` §1 registra `86e995f`.
- **Evidencia observada:** ambos commits aparecen asociados a la revisión de requisitos/planning, pero no se aporta una relación entre ellos ni verificación Git.
- **Impacto:** una relectura posterior podría comparar fuentes de snapshots distintos y atribuir al plan una corrección o decisión que no estuviera en el commit revisado.
- **Acción recomendada:** documentar si `45e3489` contiene las correcciones aceptadas de R-01/R-02 y si `86e995f` es el commit de requisitos anterior, derivado o independiente.
- **Pregunta/decisión requerida:** ¿qué commit representa exactamente la baseline conjunta que debe conservarse para esta revisión?
- **Propietario sugerido:** Product Owner / responsable de trazabilidad.

### IPR-v01-002 — Margen individual mínimo y sensibilidad al redondeo

- **Categoría:** capacidad y riesgo de ejecución.
- **Severidad:** MINOR.
- **Bloqueante:** NO.
- **Fuente/ID:** hoja Planificación y hoja Carga, IT-001, IT-003 e IT-005.
- **Evidencia observada:** José tiene 0,01 h-p de holgura en esas tres iteraciones; Hugo tiene 0,04 h-p en las mismas iteraciones. Gled tiene 0,10 h-p en ellas. El export advierte que el cálculo real usa más precisión y que la holgura mínima es 0,012 h-p.
- **Impacto:** pequeñas variaciones de tareas, redondeos o imprevistos fechables pueden convertir una celda formalmente OK en sobrecarga; el margen del equipo no puede compensar automáticamente un cuello de botella individual.
- **Acción recomendada:** conservar la comprobación con valores sin redondear en cada revisión de carga y confirmar explícitamente cualquier cambio de tarea o ausencia contra la capacidad de la persona afectada.
- **Pregunta/decisión requerida:** ¿quién y en qué ceremonia verificará la carga persona x iteración antes de cerrar cada Sprint Planning?
- **Propietario sugerido:** Scrum Master, con validación del equipo.

### IPR-v01-003 — Prerrequisitos externos aún no evidenciados

- **Categoría:** dependencias y capacidades.
- **Severidad:** MINOR.
- **Bloqueante:** NO para la coherencia documental; sí sería bloqueante para ejecutar US-023 si el requisito no está disponible antes de IT-005.
- **Fuente/ID:** US-002/US-023, EXT-01/EXT-02/EXT-03, SU-06/SU-07.
- **Evidencia observada:** las specs identifican tecnologías aprobadas, buzón de pruebas y evaluador externo; US-023 dice que el evaluador debe estar localizado antes de IT-005. No hay evidencia en las fuentes revisadas de disponibilidad confirmada.
- **Impacto:** la estimación y el orden pueden seguir siendo coherentes, pero la validación de M-04 o el cierre de US-023 podrían retrasarse si la entrada externa no aparece.
- **Acción recomendada:** registrar el estado de esas entradas y el responsable humano de confirmarlas antes de la iteración límite; no convertir su ausencia en horas o tareas nuevas sin decisión del equipo.
- **Pregunta/decisión requerida:** ¿están confirmados el buzón, las tecnologías y el evaluador externo en las fechas límite indicadas?
- **Propietario sugerido:** Product Owner / Tech Lead, según la entrada.

## 5. Observaciones aritméticas y de capacidad/recursos

- **Unidad de esfuerzo:** horas-persona (h-p). La capacidad y las cargas se comparan en h-p por persona e iteración; no se mezclan con horas de calendario ni con euros.
- **Carga de la baseline:** 5 + 5 + 3 + 5 + 3 + 3 + 5 + 3 = **32 h-p**.
- **Capacidad efectiva:** Hugo 11,56 h-p; Gled 10,69 h-p; José 13,47 h-p; total **35,72 h-p**.
- **Margen:** 35,72 - 32 = **3,72 h-p**; ocupación 32 / 35,72 = **0,90** aproximadamente.
- **Por release:** R1: 13 / 13,91 = 0,93, margen 0,91 h-p; R2: 8 / 9,91 = 0,81, margen 1,91 h-p; R3: 11 / 11,91 = 0,92, margen 0,91 h-p.
- **Por persona:** Hugo 10,5 asignadas frente a 11,56 efectivas, margen 1,06; Gled 9,4 frente a 10,69, margen 1,28; José 12,1 frente a 13,47, margen 1,37.
- **Comprobación individual:** la tabla exportada muestra carga <= capacidad en las 18 combinaciones persona x iteración. No se observa sobreasignación.
- **Ausencias:** el export descuenta 1,5 h de Hugo en IT-003 y 1 h en IT-005; 1,5 h de Gled en IT-003 y 1 h en IT-006; 1 h de José en IT-004. No se han inventado ausencias adicionales.
- **Gestión y reserva:** la hoja Gestión declara 38,76 h de gestión, 9,52 h de reserva de imprevistos y 6 h de ausencias frente a 90 h nominales; la capacidad efectiva resultante es 35,72 h-p. El coste usa la gestión de ceremonias/coordinación declarada, no presenta la reserva de imprevistos como trabajo asignado.
- **Límite del agregado:** el margen total no permite reasignar carga entre personas sin revisar roles, disponibilidad y capacidad de la celda receptora. Tampoco demuestra velocidad histórica ni garantiza cumplimiento.

## 6. Dependencias y capacidades

- **Orden funcional de la baseline:** US-001 precede a US-002 y US-003; US-002/US-003 preceden a US-006; US-006 precede a US-012; US-012 precede a US-015; US-015 y US-002 preceden a US-017; US-017 precede a US-023. Este orden coincide con las predecesoras de las specs y con la comprobación exportada de 0 errores.
- **Excepciones DEC-018:** US-002 ocupa IT-001–IT-002; US-006 IT-003–IT-004; US-017 IT-005–IT-006. Son consecutivas, de dos iteraciones como máximo y sus specs incluyen justificación de pérdida de valor al dividir.
- **Dependencias técnicas:** TD-01 se incorpora a US-001; TD-02 a US-002; TD-03 a US-006; TD-04 a US-017 y queda preparado para US-018. La exportación las identifica, pero no prueba su implementación.
- **Entradas externas:** EXT-01 condiciona US-001/US-023; EXT-02 US-002; EXT-03 US-023. Son prerrequisitos tipados y no se han convertido en estimaciones inventadas.
- **Ciclos:** no se observa un ciclo en las dependencias de la baseline.
- **Bottleneck soportado:** José concentra 12,1 h-p de desarrollo y presenta la holgura mínima por iteración en IT-001, IT-003 e IT-005; esto es sensibilidad de capacidad, no sobrecarga.
- **No se genera calendario nuevo:** estas observaciones no asignan historias, personas ni tareas adicionales.

## 7. Resultado de coste

- **Tarifas declaradas:** PO 35 €/h-p, TL 40 €/h-p y SM 32 €/h-p; el export indica acuerdo del equipo el 14/10/2026.
- **Desarrollo de la baseline:** 1.130,70 € para 32 h-p.
- **Gestión:** 1.376,94 € para 38,76 h de gestión declarada.
- **Total planificado:** **2.507,64 €**.
- **Cota superior exportada:** 3.210 €, calculada sobre 5 h/semana x 3 personas x 6 iteraciones con tarifas por rol.
- **Comprobación:** el export indica que la suma por iteración coincide con la suma por persona.
- **Presupuesto:** no se ha declarado un presupuesto independiente; no se puede afirmar conformidad presupuestaria.
- **Supuestos:** se acepta para esta revisión que las tarifas de la hoja Coste son las acordadas por el equipo y que la gestión se cobra según las horas declaradas. No se añade coste a la reserva ni a ausencias más allá de lo expresado en la hoja.
- **Límite:** el coste es una proyección de la hipótesis, no coste incurrido ni compromiso económico.

## 8. Preguntas y siguientes acciones sugeridas

| Pregunta/acción | Propietario sugerido | Disparador de validación | Bloqueante |
|---|---|---|---|
| Aclarar la relación entre `45e3489` y `86e995f` y fijar el snapshot conjunto. | PO / trazabilidad | Antes de una recheck o de congelar la baseline. | NO |
| Verificar valores sin redondear de las 18 celdas persona x iteración. | Scrum Master | Cada Sprint Planning y ante cualquier cambio de ausencia/tarea. | NO; una sobrecarga confirmada sí lo sería. |
| Confirmar buzón de pruebas, tecnologías aprobadas y evaluador externo. | PO / Tech Lead | Antes de IT-001 para buzón/tecnologías y antes de IT-005 para EXT-03. | NO en la revisión; sí para las historias afectadas si faltan al límite. |
| Registrar el presupuesto o declarar que el coste se informa sin control presupuestario. | PO / equipo | Antes de usar el coste como criterio de decisión. | NO |
| Mantener separadas capacidad efectiva, carga de desarrollo, horas de gestión y euros en futuras exportaciones. | Responsable de planificación | Próxima actualización del export. | NO |

## 9. Disposición RECHECK

No aplica: no se localizó un informe previo de planificación en `docs/project/reviews/initial-planning/`. La disposición de requisitos se incorpora como evidencia externa de esta revisión: el registro de IA consigna R-01 aceptado y corregido, y R-02 aceptado con cambios y corregido, incluyendo NFR-009 en las ocho historias de la baseline.

## 10. Consejo final y límites

La hipótesis humana DEC-020 es **READY_WITH_NON_BLOCKING_QUESTIONS** para el ejercicio de revisión de consistencia: sus entradas de alcance, estimación, capacidad, horizonte, dependencias y coste están suficientemente explicitadas y los cálculos exportados son compatibles. La planificación documental no presenta sobrecarga, trabajo sin asignar ni predecesoras mal ordenadas.

Las principales cautelas son el margen individual casi nulo en varias celdas, la falta de confirmación de prerrequisitos externos y la discrepancia de commits entre la invocación y el informe de requisitos. Ninguna se convierte en un nuevo plan, estimación, asignación o compromiso en este informe.

Las fuentes de requisitos, capacidad, decisiones, tarifas y planificación no han sido modificadas. Solo se ha creado este informe. No se ha aprobado baseline, compromiso, calendario, asignación de recursos ni gate de calidad.

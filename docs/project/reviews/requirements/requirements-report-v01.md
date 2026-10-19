# Requirements review v01

## 1. Metadatos de ejecución

- **Modo:** INITIAL.
- **Baseline indicado:** `requisitos-v0` (Hito 2, según las especificaciones).
- **Commit/snapshot:** no proporcionado; no se inspeccionó.
- **Fecha y hora:** 2026-10-10 17:34:56 +02:00 (hora proporcionada en la invocación).
- **Agente:** `requirements-reviewer.agent.md`; versión no indicada en el archivo.
- **Modelo/herramientas:** no indicados.
- **Idioma:** español.
- **Alcance detallado:** US-001, US-002, US-006, US-009, US-013 y US-017, incluidos los criterios reales de DoR.
- **Alcance mínimo de catálogo:** US-003 a US-024, excepto US-021, que está marcada Won't y no se evalúa como planificada. Se revisa coherencia, granularidad, dependencias y trazabilidad a `goals.md`.
- **Fuentes de contraste:** misión, objetivos, alcance S-01…S-06, exclusiones EX-01…EX-05, constitución, DoR, DoD y NFR/reglas.

## 2. Manifiesto de fuentes y limitaciones

| Fuente leída | Función y versión/baseline observado |
|---|---|
| `.specify/memory/constitution.md` | Gobierno; versión 1.1.0, ratificada el 2026-09-30 y actualizada el 2026-10-10. |
| `specs/product/mission.md` | Misión, usuarios, propuesta de valor y experiencia deseada; sin versión indicada. |
| `specs/product/goals.md` | Stakeholders, G-01…G-04, M-01…M-05, S-01…S-06, EX-01…EX-05, supuestos y restricciones; sin baseline propio visible. |
| `docs/quality/definition-of-ready.md` | Lista compartida de DoR; incluye criterios de trazabilidad, aceptación, planificación y restricciones. |
| `docs/quality/definition-of-done.md` | Lista compartida de DoD; leída como contexto de calidad, no como evidencia de implementación. |
| `specs/EP-001-cuenta-y-verificacion/spec.md` | Historias US-001…US-005; baseline `requisitos-v0` (Hito 2). |
| `specs/EP-002-publicacion-de-anuncios/spec.md` | Historias US-006…US-011; baseline `requisitos-v0` (Hito 2). |
| `specs/EP-003-busqueda-de-habitaciones/spec.md` | Historias US-012…US-016; baseline `requisitos-v0` (Hito 2). |
| `specs/EP-004-contacto-y-mensajeria/spec.md` | Historias US-017…US-021; baseline `requisitos-v0` (Hito 2). |
| `specs/EP-005-entrega-y-demo/spec.md` | Historias US-022…US-024; baseline `requisitos-v0` (Hito 2). |
| `specs/product/nfr-y-reglas.md` | NFR-001…NFR-009 y RN-01…RN-11; baseline `requisitos-v0` (Hito 2). |
| `.github/agents/requirements-reviewer.agent.md` | Configuración del agente indicada en la invocación; versión no visible. |

**Fuentes no accesibles o no suministradas:** la carpeta `docs/project/reviews/requirements/` no existía al inspeccionarla; tampoco existía `docs/project/`. No había informes previos que comparar. El DoR remite a un Excel de capacidad y a la coincidencia de IDs en Excel, ProjectLibre y Jira; el repositorio muestra `docs/ErasmusNest_GC04_Requisitos_Capacidad.xlsx`, pero no se pudo leer su contenido ni se suministraron ProjectLibre/Jira. Por tanto, no se verifican esas coincidencias, Planning Poker, capacidad efectiva, orden dentro del sprint ni decisiones del Product Owner. No se proporcionó un registro de decisiones humanas.

**Límites:** revisión documental del baseline indicado; no se inspeccionó código ni se evaluó cumplimiento de la DoD, no se ejecutaron pruebas y no se consultaron fuentes externas. Las ausencias se formulan como “no encontrado en las fuentes revisadas”, no como inexistencia absoluta. US-021 se excluye de la revisión de historias planificadas por instrucción expresa.

## 3. Evaluación del alcance revisado

**Recomendación: NEEDS_REFINEMENT.**

En el alcance detallado hay hallazgos bloqueantes para una interpretación y una planificación compartidas: NFR-002 contradice datos de perfil que las historias ordenan mostrar; US-001 no define el resultado de una confirmación de contraseña discordante; US-002 omite una NFR de accesibilidad aplicable y US-013 no enlaza explícitamente la restricción global de datos sintéticos. Además, las seis estimaciones se identifican como referencias previas a Planning Poker, de modo que no cumplen todavía el criterio de estimación consensuada del DoR. La interpretación de límites de fechas de US-013 requiere precisión.

La recomendación se limita a US-001, US-002, US-006, US-009, US-013 y US-017, y a las fuentes del manifiesto. No implica desaprobación del baseline ni decisión sobre prioridad, alcance o estimaciones. La comprobación de capacidad, dependencias en herramientas externas y alineamiento cruzado de IDs queda NOT_ASSESSABLE.

## 4. Matriz de cobertura de las seis dimensiones

| Dimensión | Elementos inspeccionados | Resultado | Hallazgos |
|---|---|---|---|
| Claridad y verificabilidad | Seis historias detalladas; resto US-003…US-024 (salvo US-021); NFR/RN y goals | Parcial: contradicción entre privacidad y perfil visible; faltan decisiones de validación y límites de fechas. | F-01, F-02, F-04 |
| Cobertura y alcance | G-01…G-04, S-01…S-06, EX-01…EX-05 y objetivos/épicas/historias | Parcial: varias capacidades del catálogo no tienen correspondencia inequívoca con S-01…S-06; hay candidatos para decisión humana. | F-07; apartado 7 |
| Elementos de especificación ausentes | Casos positivos/negativos, NFR, reglas, datos sintéticos y errores | Parcial: trazabilidad incompleta de NFR y algunos comportamientos/estados no definidos. | F-02, F-03, F-04; apartado 6 |
| Tamaño y granularidad | Todas las historias planificadas del catálogo, sin descomponer tareas | No se detecta una historia detallada que por sí sola sea inequívocamente demasiado grande; varias historias son outcomes verticales identificables. La capacidad real no se puede comprobar. | F-06; apartado 8 |
| Criterios de aceptación y DoR | US-001, US-002, US-006, US-009, US-013 y US-017 frente a cada criterio del DoR | El número y la forma general de criterios están presentes, pero fallan o no se pueden acreditar varios controles DoR. | F-02, F-03, F-05, F-06; apartado 6 |
| Dependencias y supuestos | Predecesoras, plan inicial, reglas, supuestos y enlaces entre US | Varias relaciones están declaradas; orden de dependencias dentro de iteraciones y algunas relaciones de flujo no se acreditan. | F-08; apartado 8 |

### Correspondencia de objetivos y límites generales

- **G-01:** publicación (US-006), búsqueda (US-012…US-016) y contacto/mensajería (US-017…US-019) forman el recorrido declarado. El alcance incluye búsqueda por zona y fechas en S-03; las historias mantienen esa línea.
- **G-02:** US-001/002 y las restricciones de US-006/017 materializan verificación institucional antes de publicar/contactar. US-004 añade perfil visible, cuya compatibilidad con NFR-002 está en conflicto (F-01).
- **G-03:** US-009/010 y US-012/015 cubren cierre/estado; US-011 amplía el cierre manual de S-05 con automatización, pendiente de aclaración de alcance.
- **G-04:** US-022/023 se relacionan con datos y evaluación de demo, pero la relación de US-023 con el alcance S-01…S-06 no está explicitada.
- **S-01…S-06:** las épicas EP-001…EP-005 declaran referencias de alcance, pero algunas historias introducen capacidades no nombradas expresamente por esos elementos; véase el apartado 7.
- **EX-01…EX-05:** ninguna historia planificada revisada declara explícitamente pagos, contratos, app nativa, verificación de identidad adicional o integración universitaria real. US-020 (reportes), US-007 (fotos) y US-024 (inglés) son ampliaciones/candidatos a confirmar, no se clasifican como violaciones confirmadas de una exclusión.

### Revisión mínima del resto del catálogo

El vínculo G/S indicado procede de la épica que contiene la historia; “sin S inequívoco” significa que no se encontró una asignación más precisa en las fuentes revisadas, no que se haya decidido excluirla.

| Historia | Trazabilidad declarada y observación mínima de claridad/granularidad/dependencia |
|---|---|
| US-003 | EP-001: G-01/G-02, S-01. Login/cierre de sesión es un outcome acotado; depende de US-001 y establece límite de 2 h alineado con NFR-001. El acceso de cuentas no verificadas queda acotado por RN-02. |
| US-004 | EP-001: G-01/G-02, S-01. Perfil es outcome vertical, pero titulación/idiomas son atributos adicionales no descritos en S-01 y los campos visibles chocan con NFR-002 (F-01 y candidato del apartado 7). Depende de US-003. |
| US-005 | EP-001: G-01, S-01. Recuperación es un flujo independiente y acotado; depende de US-002/US-003 y del correo de prueba. Caducidad/uso único son verificables; no define un límite de frecuencia, cuya necesidad queda a decisión del equipo. |
| US-007 | EP-002: G-01, vínculo S-02 por confirmar. Subida y visualización de fotos son outcome acotado; depende de US-006 y el detalle US-015, con riesgo de almacenamiento declarado. Límites de formato/tamaño/alt son explícitos (apartado 7). |
| US-008 | EP-002: G-03, S-02. Edición es outcome acotado; depende de US-006. Remite validación a US-006, pero no explicita si todos los campos editables coinciden ni cómo interactúa edición con cierre concurrente. |
| US-010 | EP-002: G-03, S-05. Lista de anuncios/estado es outcome inspectable; depende de US-006 y añade acciones según US-009/US-008/US-018 estén disponibles. El plan de añadir accesos incrementalmente está explicado. |
| US-011 | EP-002: G-03, S-05 ambiguo. Cierre automático es una capacidad independiente, de alcance no inequívoco frente al cierre “manual” de S-05; depende de US-009 y de un mecanismo temporal que se deja como opción técnica (apartado 7). |
| US-012 | EP-003: G-01, S-03. Listado con paginación es un outcome acotado y base de US-013/014/015; depende de US-006. US-022 se recomienda para datos/rendimiento, pero no figura como predecesora formal. |
| US-014 | EP-003: G-01, S-03. Filtro por zona es outcome pequeño; depende de US-012 y comparte catálogo de zonas con US-006. No se aclara en AC el operador al seleccionar varias zonas, aunque la lista cerrada está enlazada a RN-06. |
| US-015 | EP-003: G-01/G-03, S-03. Detalle y estado constituyen outcome vertical; depende de US-012 y se amplía con US-004/US-007. No se especifica completamente acceso directo a un anuncio cerrado y su transición respecto a lista de disponibles (apartado 7). |
| US-016 | EP-003: G-01, S-03. Ordenar por precio/fecha es una capacidad acotada; depende de US-012/US-013 y define precedencia frente a cobertura completa. |
| US-018 | EP-004: G-01/G-02, S-04. Lectura/respuesta en conversación es outcome vertical; depende de US-017 y comparte modelo. En anuncio cerrado queda claro que se puede leer, pero no si responder está permitido (apartado 7). |
| US-019 | EP-004: G-01, S-04. Indicador de no leídos es incremento acotado sobre US-018; el criterio cuenta conversaciones y no mensajes, pero no concreta cuándo se refresca el indicador. |
| US-020 | EP-004: G-02; vínculo S-04 discutible, OUT_OF_SCOPE candidato (apartado 7). Reportar es outcome acotado; depende de US-015/US-003. Los criterios guardan el reporte, pero la revisión/gestión queda fuera, sin responsable definido. |
| US-022 | EP-005: G-04, S-06. Carga reproducible de datos sintéticos es outcome claro y habilitador de pruebas; depende de US-001/US-006 y se amplía con US-017. Los umbrales de 20 usuarios/100 anuncios y cuentas de demo están en AC, pero su aceptación como necesidad del baseline debe ser decisión del equipo, no derivarse de S-06. |
| US-023 | EP-005: G-04; S-XX no explícito, candidato IN_SCOPE (apartado 7). Guion de instalación/evaluación tiene valor verificable por M-04/NFR-008; depende de US-022, pero su guion recorre más historias que las declaradas como predecesoras (F-08). |
| US-024 | EP-005: G-01; S-XX no explícito, SCOPE_UNCLEAR (apartado 7). La traducción es transversal y amplia, y sus AC son de alto nivel (“flujos principales”); declara dependencias US-015/US-018, insuficientes para representar todos los textos/error que pide traducir si se acepta. Además, estado y plan IT-006 requieren aclaración (F-07). |

## 5. Hallazgos

### Índice

| ID | Severidad | Confianza | Bloqueante | Resumen |
|---|---|---|---|---|
| F-01 | MAJOR | Alta | Sí | NFR-002 entra en conflicto con los datos visibles en perfil, anuncio y conversación. |
| F-02 | MAJOR | Alta | Sí | US-001 pide confirmación de contraseña, pero no define el rechazo cuando no coincide. |
| F-03 | MAJOR | Alta | Sí | Faltan enlaces explícitos a NFR aplicables en US-002 y US-013. |
| F-04 | MAJOR | Media | Sí | La semántica de fechas inclusivas/solapamiento de US-013 no queda completamente fijada. |
| F-05 | MAJOR | Alta | Sí | Las estimaciones detalladas son expresamente pre-Planning Poker, no consensuadas. |
| F-06 | QUESTION | Alta | No confirmado | No se puede acreditar que las estimaciones quepan en un sprint ni aplicar DEC-018. |
| F-07 | QUESTION | Media | No | Trazabilidad de alcance de varias capacidades queda ambigua, especialmente US-024. |
| F-08 | QUESTION | Media | No | Falta evidencia de orden y de algunas dependencias necesarias para los flujos completos. |

### F-01 — Conflicto entre privacidad y datos visibles

- **Categoría:** claridad, consistencia y privacidad.
- **Fuentes e indicios:** `specs/product/nfr-y-reglas.md`, NFR-002: “El correo y los datos de un usuario no se muestran a otros usuarios”; `specs/EP-001-cuenta-y-verificacion/spec.md`, US-004-AC-02: “se ven el nombre visible y el distintivo ‘Verificado UEx’”; `specs/EP-003-busqueda-de-habitaciones/spec.md`, US-015-AC-01: se muestra el nombre visible del autor; `specs/EP-004-contacto-y-mensajeria/spec.md`, US-017-AC-05: “solo su nombre visible”.
- **Observación:** “los datos de un usuario” incluye literalmente el nombre visible, pero varias historias requieren exponerlo.
- **Impacto:** implementadores y verificadores pueden interpretar de forma incompatible qué datos se pueden compartir; afecta a privacidad y a la aceptación de las historias.
- **Acción sugerida:** precisar en NFR-002 qué atributos son privados y cuáles son públicos (por ejemplo, definir el conjunto permitido y si requiere acción del usuario); revisar las historias afectadas a partir de esa decisión.
- **Confianza:** Alta, porque ambas obligaciones están expresas.
- **Estado bloqueante:** Sí, para interpretar el comportamiento público de perfil/contacto.

### F-02 — Confirmación de contraseña sin comportamiento de discrepancia

- **Categoría:** criterios de aceptación y especificación de errores.
- **Fuente e indicio:** `specs/EP-001-cuenta-y-verificacion/spec.md`, US-001-AC-01: el formulario pide “contraseña y confirmación de contraseña”; US-001-AC-05 define longitud, letra y número, pero no se encontró un criterio para confirmar que ambos valores coinciden.
- **Observación:** no queda definido el resultado observable al introducir contraseñas distintas ni si se conserva el formulario/marca el campo correspondiente.
- **Impacto:** no hay interpretación compartida para un caso negativo básico del registro ni verificación completa de la entrada.
- **Acción sugerida:** especificar el comportamiento de discrepancia y el mensaje/error asociado, sin prescribir implementación.
- **Confianza:** Alta; la confirmación está en el formulario y su validación no aparece en los criterios revisados.
- **Estado bloqueante:** Sí, porque el contrato de aceptación del registro queda incompleto.

### F-03 — Referencias NFR incompletas en historias detalladas

- **Categoría:** DoR, trazabilidad de calidad.
- **Fuentes e indicios:** `specs/EP-001-cuenta-y-verificacion/spec.md`, US-002 incluye NFR-001, NFR-007 y NFR-009, pero no NFR-004 aunque especifica pantallas, avisos y acciones accesibles; `specs/EP-003-busqueda-de-habitaciones/spec.md`, US-013 enumera NFR-004, NFR-005 y NFR-007, pero no NFR-009 (restricción de uso exclusivo de datos sintéticos en `specs/product/nfr-y-reglas.md`).
- **Observación:** no se encuentran enlaces de todas las NFR aplicables desde esas historias. El principio 5 de la constitución exige considerar accesibilidad en historias afectadas; NFR-009 y las restricciones de `goals.md` son globales.
- **Impacto:** el DoR requiere identificar NFR y restricciones aplicables; omitir enlaces reduce la posibilidad de comprobarlas consistentemente.
- **Acción sugerida:** revisar aplicabilidad con el equipo y añadir referencias a NFR/controles globales donde corresponda, sin duplicar su contenido.
- **Confianza:** Alta respecto de las referencias ausentes; la aplicabilidad se apoya en que las historias definen interfaz/entradas y deben verificarse con datos sintéticos.
- **Estado bloqueante:** Sí, para el control DoR de US-002 y US-013.

### F-04 — Límites de fechas de búsqueda insuficientemente fijados

- **Categoría:** claridad, regla de negocio y verificabilidad.
- **Fuentes e indicios:** `specs/product/nfr-y-reglas.md`, RN-10: “se solapa al menos 1 día”; `specs/EP-003-busqueda-de-habitaciones/spec.md`, US-013-AC-02 y US-013, casos límite: la salida buscada coincidente con el inicio del anuncio cuenta como un día; los criterios también describen cobertura completa/parcial.
- **Observación:** el ejemplo establece un caso de borde, pero las fuentes no definen formalmente si las fechas de entrada y salida son inclusivas en ambos extremos ni cómo se computan las fechas mostradas de cobertura parcial cuando solo un límite se solapa.
- **Impacto:** implementaciones distintas pueden incluir/excluir anuncios en días frontera o informar periodos parciales diferentes.
- **Acción sugerida:** precisar convención de intervalos y varios casos de frontera (anuncio que termina el día de llegada, búsqueda que termina el día de inicio del anuncio, y límites de cobertura parcial).
- **Confianza:** Media; existe un caso de un día que resuelve un borde, pero no define toda la convención.
- **Estado bloqueante:** Sí, para compartir el resultado de búsqueda por fechas sin interpretación adicional.

### F-05 — Estimaciones sin evidencia de Planning Poker

- **Categoría:** DoR y planificación.
- **Fuentes e indicios:** en cada una de US-001, US-002, US-006, US-009, US-013 y US-017 la especificación indica que las estimaciones son “referencia V0 propuesta antes del Planning Poker”.
- **Observación:** el DoR requiere estimación en horas-persona consensuada en Planning Poker. La propia especificación declara que las cifras actuales son previas a ese consenso.
- **Impacto:** las seis historias detalladas no satisfacen ese criterio de entrada al sprint mientras no se confirme la estimación.
- **Acción sugerida:** realizar y registrar el Planning Poker conforme al método del equipo; no se recomienda aquí cambiar cifras ni estimar.
- **Confianza:** Alta, evidencia expresa en las seis especificaciones.
- **Estado bloqueante:** Sí para el DoR de las seis historias; no es un juicio sobre el valor de las cifras.

### F-06 — Capacidad y aplicación de la excepción DEC-018 no verificables

- **Categoría:** DoR y planificación.
- **Fuentes e indicios:** el DoR condiciona el tamaño a la capacidad efectiva del sprint y a la hoja *Capacidad* del Excel; las seis historias tienen plan inicial por IT y horas-persona, pero la capacidad no se pudo inspeccionar.
- **Observación:** no se puede determinar si cada estimación cabe en una iteración, si hay que dividirla o si se activa la excepción de hasta dos iteraciones consecutivas.
- **Impacto:** no se puede declarar MET/NOT_MET este control de preparación ni confirmar que el plan respeta DEC-018.
- **Acción sugerida:** aportar la fuente de capacidad y el resultado consensuado de Planning Poker/plan por iteración; documentar justificación en `Plan inicial` si se aplica DEC-018.
- **Confianza:** Alta en que falta evidencia accesible; ninguna afirmación sobre el contenido del Excel.
- **Estado bloqueante:** No confirmado. NOT_ASSESSABLE por limitación documental; no se presume incumplimiento.

### F-07 — Trazabilidad de alcance por aclarar para capacidades adicionales

- **Categoría:** cobertura, alineamiento de alcance y trazabilidad.
- **Fuentes e indicios:** `specs/product/goals.md`, lista cerrada de alcance S-01…S-06; `specs/EP-005-entrega-y-demo/spec.md`, US-024 añade selector y traducción al inglés, declara que no figura en S-01…S-06 y que requeriría decisión de ampliación, pero asigna plan inicial IT-006. La épica también incorpora “inclusión” a su alcance desde misión/constitución.
- **Observación:** no se encontró decisión humana de cambio de alcance; además, el estado “si se planifica” coexiste con un plan inicial para US-024. Otras capacidades del catálogo (US-007 fotos, US-011 cierre automático, US-020 reportes y los datos de perfil de US-004) tampoco tienen un S-XX inequívoco, aunque podrían considerarse detalles o extensiones de S-01…S-05.
- **Impacto:** el alcance planificado frente a las extensiones candidatas no es enteramente trazable; la constitución exige que cualquier ampliación sea justificada y decidida por el equipo.
- **Acción sugerida:** que el Product Owner confirme qué capacidades están incluidas y registre, si procede, el cambio requerido por `goals.md`; resolver especialmente si US-024 está planificada o solo propuesta.
- **Confianza:** Media; las referencias de S-01…S-06 y el texto de US-024 son explícitos, pero la interpretación de subfuncionalidades no está definida.
- **Estado bloqueante:** No se declara bloqueo confirmado para el alcance detallado; es una aclaración de catálogo/gobierno.

### F-08 — Orden y dependencias de flujos no acreditados

- **Categoría:** dependencias, planificación y trazabilidad.
- **Fuentes e indicios:** US-009 enumera US-006 y asigna ambas a IT-002, sin orden dentro del sprint; US-013 depende de US-012 y ambas se asignan a IT-003 sin orden indicado. US-017 dice que US-009 condiciona anuncios cerrados, pero US-009 no figura en sus predecesoras. US-023 describe registro, verificación, publicación, búsqueda, contacto y cierre, pero solo lista US-022 como predecesora.
- **Observación:** el DoR pide predecesoras completadas o planificadas antes en el mismo sprint. Las fuentes revisadas no muestran el orden intraiteración. La lista de dependencias de US-023 tampoco refleja todos los flujos que su guion pretende recorrer.
- **Impacto:** no se puede verificar secuenciación de US-009/013 y el guion completo puede depender de historias que el artefacto no vincula explícitamente.
- **Acción sugerida:** verificar en el plan humano el orden de historias del mismo sprint; explicitar dependencias de flujo de US-023 y confirmar la relación US-017/US-009 si afecta al orden o al alcance de pruebas.
- **Confianza:** Media; se basa en planes iniciales descritos, sin acceso a herramientas de planificación externas.
- **Estado bloqueante:** No confirmado; el plan externo podría contener el orden omitido.

## 6. Matriz DoR de historias detalladas

**Leyenda:** MET, NOT_MET, NOT_ASSESSABLE y NOT_APPLICABLE tienen el significado definido por la instrucción de revisión. Cada celda MET cita historia/épica, criterio o sección que ofrece evidencia. `NR` identifica la limitación de fuentes en el apartado 2. **US-001** = EP-001/US-001; **US-002** = EP-001/US-002; **US-006** = EP-002/US-006; **US-009** = EP-002/US-009; **US-013** = EP-003/US-013; **US-017** = EP-004/US-017.

| Criterio real del DoR | US-001 | US-002 | US-006 | US-009 | US-013 | US-017 |
|---|---|---|---|---|---|---|
| ID único y estable, coincidente en repositorio, Excel, ProjectLibre y Jira | NOT_ASSESSABLE (NR) | NOT_ASSESSABLE (NR) | NOT_ASSESSABLE (NR) | NOT_ASSESSABLE (NR) | NOT_ASSESSABLE (NR) | NOT_ASSESSABLE (NR) |
| Asociada a épica y escrita en su `spec.md` | MET (EP-001) | MET (EP-001) | MET (EP-002) | MET (EP-002) | MET (EP-003) | MET (EP-004) |
| Trazable a un G-XX y un S-XX | MET (EP-001: G-02/S-01) | MET (EP-001: G-02/S-01) | MET (EP-002: G-01/S-02) | MET (EP-002: G-03/S-05) | MET (EP-003: G-01/S-03) | MET (EP-004: G-01/G-02/S-04) |
| Tipo de usuario identificado | MET (actor del relato) | MET (actor del relato) | MET (actor del relato) | MET (actor del relato) | MET (actor del relato) | MET (actor del relato) |
| Capacidad en formato Como… quiero… para… | MET (relato US-001) | MET (relato US-002) | MET (relato US-006) | MET (relato US-009) | MET (relato US-013) | MET (relato US-017) |
| Valor/beneficio comprensible | MET (relato y Necesidad) | MET (relato y Necesidad) | MET (relato y Necesidad) | MET (relato y Necesidad) | MET (relato y Necesidad) | MET (relato y Necesidad) |
| Límites claros y sin invadir EX-01…EX-05 | MET (Boundary EP-001) | MET (Boundary EP-001) | MET (Boundary EP-002) | MET (RN-08; cierre sin reapertura) | MET (Boundary EP-003) | MET (Boundary EP-004) |
| Al menos dos criterios `US-XXX-AC-NN` | MET (AC-01…05) | MET (AC-01…04) | MET (AC-01…05) | MET (AC-01…04) | MET (AC-01…05) | MET (AC-01…05) |
| Criterios observables y verificables con datos sintéticos | MET (AC y ejemplos sintéticos) | MET (AC y buzón de pruebas) | MET (AC, ejemplos y NFR-009) | MET (AC y restricción NFR-009) | MET (AC y ejemplos de anuncios) | MET (AC y restricción NFR-009) |
| Criterios describen comportamiento, no implementación | MET (US-001-AC) | MET (US-002-AC) | MET (US-006-AC) | MET (US-009-AC) | MET (US-013-AC) | MET (US-017-AC) |
| Reglas de negocio aplicables identificadas | MET (RN-01, RN-02) | MET (RN-02, RN-03) | MET (RN-02, RN-04…07) | MET (RN-07, RN-08) | MET (RN-08, RN-10) | MET (RN-02, RN-08, RN-09, RN-11) |
| Prioridad MoSCoW asignada y justificada | MET (Must y motivo) | MET (Must y motivo) | MET (Must y motivo) | MET (Must y motivo) | MET (Must y motivo) | MET (Must y motivo) |
| Estimación consensuada en Planning Poker | NOT_MET (estimación V0 pre-Poker) | NOT_MET (estimación V0 pre-Poker) | NOT_MET (estimación V0 pre-Poker) | NOT_MET (estimación V0 pre-Poker) | NOT_MET (estimación V0 pre-Poker) | NOT_MET (estimación V0 pre-Poker) |
| Cabe en un sprint según capacidad efectiva; división cuando proceda | NOT_ASSESSABLE (NR; F-06) | NOT_ASSESSABLE (NR; F-06) | NOT_ASSESSABLE (NR; F-06) | NOT_ASSESSABLE (NR; F-06) | NOT_ASSESSABLE (NR; F-06) | NOT_ASSESSABLE (NR; F-06) |
| Excepción DEC-018, solo si aplica: máximo dos iteraciones, capacidad y justificación | NOT_ASSESSABLE (depende de F-06) | NOT_ASSESSABLE (depende de F-06) | NOT_ASSESSABLE (depende de F-06) | NOT_ASSESSABLE (depende de F-06) | NOT_ASSESSABLE (depende de F-06) | NOT_ASSESSABLE (depende de F-06) |
| Incertidumbre indicada; plan de reducción si es Alta | MET (Baja) | MET (Media) | MET (Baja) | MET (Baja) | MET (Media) | MET (Media) |
| Predecesoras terminadas o planificadas antes en el sprint | MET (sin predecesora) | NOT_ASSESSABLE (US-001 en mismo IT-001; falta orden verificable) | MET (US-002/003 en IT-001 antes de IT-002) | NOT_ASSESSABLE (US-006 en mismo IT-002; falta orden) | NOT_ASSESSABLE (US-012 en mismo IT-003; falta orden) | MET (US-002 en IT-001 y US-015 en IT-003 antes de IT-004) |
| NFR aplicables identificadas | MET (NFR-001/004/007) | NOT_MET (no referencia NFR-004; F-03) | MET (NFR-002/004/007) | MET (NFR-004) | NOT_MET (no referencia NFR-009; F-03) | MET (NFR-002/004/007) |
| Restricciones técnicas, organizativas/proyecto, incluido uso exclusivo de datos sintéticos | MET (NFR-009, dominio de prueba) | MET (NFR-009 y buzón de pruebas) | MET (NFR-009) | MET (NFR-009) | NOT_MET (restricciones de fechas, sin referencia a NFR-009) | MET (NFR-009) |
| Datos sintéticos disponibles o plan de preparación | MET (dominio de prueba y ejemplos) | MET (buzón de pruebas; supuesto declarado) | MET (NFR-009 y US-022 planeada) | NOT_ASSESSABLE (no hay plan de datos de prueba explícito en US-009) | MET (ejemplos y US-022 en IT-002 antes de IT-003) | MET (mensajes sintéticos; US-022 se amplía con conversaciones) |
| Ninguna duda que impida entender/planificar | MET (reglas y supuestos explícitos) | MET (caducidad/casos límite y supuesto de buzón) | MET (reglas, ejemplos y supuestos) | MET (RN-08 y cierre definitivo explícitos) | MET (AC fijados; supuesto de cobertura parcial documentado) | MET (casos límite y supuesto SU-02) |
| Supuestos/cuestionamientos no bloqueantes documentados | MET (SU-01 y buzón de pruebas) | MET (disponibilidad del buzón) | MET (SU-02, SU-03) | MET (cierre definitivo/RN-08) | MET (validación de cobertura en Review R2) | MET (SU-02) |

**Notas de la matriz:** el DoR pregunta por coincidencia de IDs entre varias herramientas, así que la mera aparición del ID en `spec.md` no basta para MET. La excepción DEC-018 se califica NOT_ASSESSABLE porque su aplicabilidad depende del criterio anterior de capacidad, no porque se haya confirmado que alguna historia necesita dos iteraciones. La secuencia propuesta en un mismo IT no prueba orden dentro del sprint. Las filas NFR/ restricciones expresan los enlaces que aparecen en la historia revisada; no niegan la existencia de reglas globales en otros documentos.

## 7. Candidatos de omisión, preguntas de stakeholder y extensiones

No son defectos confirmados por sí solos. Se indican para decisión humana y no se asigna prioridad ni estimación.

| Elemento | Clasificación | Base/racional | Pregunta para el equipo/PO |
|---|---|---|---|
| US-007, fotos | SCOPE_UNCLEAR | S-02 enumera fechas, zona y precio, sin mencionar imágenes; EP-002 las incluye. | ¿Las fotos son parte de la publicación incluida en S-02 o requieren decisión de ampliación? |
| US-011, cierre automático | SCOPE_UNCLEAR | S-05 nombra “cierre manual”; US-011 automatiza el cierre con fecha vencida. | ¿S-05 permite explícitamente cierre automático además del manual? ¿Qué condición temporal es la fuente de verdad? |
| US-020, reporte de anuncios | OUT_OF_SCOPE candidato | S-01…S-06 no incluye moderación/reportes; US-020 propone reportar y deja la revisión futura. | ¿Se mantiene como idea futura o se añade formalmente al alcance? ¿Quién revisaría reportes en la versión incluida? |
| US-024, inglés | SCOPE_UNCLEAR | La misión habla de experiencia inclusiva, pero S-01…S-06 no incluye traducción; US-024 dice que necesitaría cambio de alcance y a la vez indica IT-006. | ¿Existe una decisión de ampliación? ¿US-024 está planificada o solo propuesta? |
| US-004, titulación/idiomas y perfil mostrado | SCOPE_UNCLEAR | El perfil no está nombrado expresamente en S-01…S-06 y su exposición se cruza con NFR-002. | ¿Qué atributos del perfil son necesarios y cuáles son públicos, opcionales o excluidos? |
| US-023, guion de instalación/evaluación | IN_SCOPE_CANDIDATE | G-04 y M-04/NFR-008 requieren evaluación instalable; S-01…S-06 no nombra el guion como capacidad de producto. | ¿Se registra el guion como entregable habilitador de G-04 dentro del alcance del release? |
| US-013, cobertura parcial | IN_SCOPE_CANDIDATE | S-03 pide filtrar por fechas; la cobertura parcial puede apoyar el recorrido G-01, pero la utilidad está anotada como supuesto pendiente de validar. | ¿La búsqueda debe mostrar solapamientos parciales o solo anuncios que cubren toda la estancia? |
| Recorrido con anuncios cerrados (US-009/015/018) | SCOPE_UNCLEAR | US-009 especifica leer conversaciones existentes y US-015 muestra estado; US-018 permite responder y solo indica “Anuncio cerrado”. | ¿En conversaciones de un anuncio cerrado se permite seguir enviando respuestas o solo leerlas? |

### Stakeholders y evidencia que falta

- `goals.md` declara SH-01…SH-05, pero en el material revisado no hay una decisión del PO que resuelva las extensiones anteriores; el rol de cliente/revisor se atribuye a SH-01/SH-04.
- La validación de cobertura parcial se asigna a la Review de R2, pero no se suministró su resultado.
- El supuesto de disponibilidad de cinco participantes (SU-06) y las pruebas externas de M-01/M-03/M-04 no se pueden evaluar en una revisión de requisitos sin resultados de ejecución.
- No se declara que una persona/equipo distinto sea responsable de moderación de US-020; la historia deja la revisión fuera.

## 8. Dependencias, supuestos y trazabilidad

### Dependencias y orden

- **US-001 → US-002 → verificación** y **US-006 → US-009/US-012** tienen dependencias funcionales entendibles y reflejadas en el catálogo.
- **US-006** declara US-002/US-003 como predecesoras; el plan inicial sitúa estas últimas en IT-001 y US-006 en IT-002.
- **US-009/US-006** y **US-013/US-012** se sitúan en la misma iteración según el plan inicial. El orden antes exigido por DoR no aparece en las fuentes revisadas: hipótesis de secuenciación **UNCONFIRMED** fuera del orden declarado.
- **US-017** lista US-015 y US-002 como predecesoras. Su texto dice que US-009 condiciona el comportamiento para anuncios cerrados, pero no lo lista como predecesora; si la dependencia solo es de contrato (comprobar estado), no obliga a secuenciarla. Clasificación: **contrato compartido / secuencia por confirmar**, no dependencia confirmada de finalización.
- **US-022** declara US-001/US-006 y que se amplía cuando existe US-017; alineado con datos de conversaciones.
- **US-023** necesita guionar flujos de registro, verificación, publicación, búsqueda, contacto y cierre; en sus predecesoras solo enumera US-022. La necesidad de disponer de esos flujos es una dependencia funcional **no trazada explícitamente** (F-08), aunque el plan inicial tardío podría resolverla.
- **US-024** depende de US-015/US-018, pero sus criterios exigen traducir pantallas y mensajes de error de los flujos principales; dependencia de US-001/002/006/009 y búsqueda queda **UNCONFIRMED** y requiere completar el contrato si se acepta.
- No se encontró un ciclo de dependencias explícito en las fuentes revisadas.

### Supuestos y trazabilidad

- SU-01 sustenta dominio institucional; US-001 supone dominio configurable y US-002 usa buzón de pruebas. Se desconoce si el dominio de prueba y el buzón están aprobados/preparados.
- SU-02 excluye cierre económico/legal en la plataforma y es consistente con EX-01/EX-02; US-017 contempla intercambio voluntario de otros datos, lo que merece comprobarse frente a NFR-002 y la restricción de datos sintéticos en demos.
- SU-03 exime a la plataforma de verificar permiso del propietario; se refleja en EP-002.
- SU-06 y SU-07 condicionan participantes/tecnología; no hay evidencia para comprobarlos en los documentos revisados.
- Los enlaces G-XX/EP-XXX/US-XXX existen para las historias detalladas por la cabecera de épica e historia. No todas las historias enumeran el S-XX directamente, pero las épicas declaran S-01…S-06; las nuevas capacidades identificadas en el apartado 7 requieren decisión, no inferencia.
- M-01, M-02 y M-03 se conectan con flujos de US-006/009/013/017 y US-002; US-023 enlaza con M-04. M-05 no aparece trazado a una historia concreta en las especificaciones revisadas; se cubre parcialmente en DoD/criterios transversales. Recomendación: decidir dónde registrar evidencia de seguimiento de M-05, sin crear una nueva funcionalidad por inferencia.
- No se observó estimación final consensuada en el material de requisitos; las cifras disponibles son expresamente provisionales (F-05). No se formulan estimaciones alternativas.

## 9. Preguntas para decisión humana (prioridad)

1. **Privacidad:** ¿qué campos de usuario son privados y qué campos (p. ej. nombre visible/distintivo) se permiten mostrar? ¿Cómo se alinea NFR-002 con US-004, US-015 y US-017?
2. **Registro:** ¿qué debe ocurrir cuando contraseña y confirmación no coinciden? ¿Qué validación observable se aplica al correo mal formado?
3. **Búsqueda por fechas:** ¿los intervalos son inclusivos? ¿Qué fecha exacta se muestra para cobertura parcial y cómo se trata cada extremo coincidente?
4. **DoR:** ¿cuándo se registran las estimaciones consensuadas del Planning Poker, la capacidad de sprint y las excepciones DEC-018 para las seis historias detalladas?
5. **Calidad transversal:** confirmar si NFR-004 aplica a US-002 y enlazar NFR-009 en US-013, o documentar por qué alguna referencia no aplica.
6. **Alcance:** ¿se autorizan/planifican fotos (US-007), cierre automático (US-011), reportes (US-020), perfil (US-004) e inglés (US-024)? ¿Qué decisión gobierna la discrepancia de US-024 entre alcance pendiente y plan IT-006?
7. **Flujo de anuncios cerrados:** ¿las conversaciones existentes pueden recibir respuestas después del cierre?
8. **Plan:** ¿están US-006 antes de US-009 y US-012 antes de US-013 dentro de sus sprints? ¿US-023 tiene predecesoras de funcionalidad suficientes para el guion completo?
9. **Stakeholders/medición:** ¿quién valida la cobertura parcial de US-013 y quién revisaría reportes si se incluye US-020?

## 10. Cierre

Revisión INITIAL del baseline indicado. US-021 está excluida de la evaluación como historia planificada por estar marcada Won't.

**Esta es una revisión consultiva. No se cambió ningún requisito fuente, prioridad, estimación, plan ni decisión humana. No se aprobó ningún baseline ni gate.**

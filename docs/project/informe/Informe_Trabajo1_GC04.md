# Trabajo 1 · Planificación del proyecto ErasmusNest

**Asignatura:** Gestión de Proyectos Software (UEx, 2026/27) · **Grupo:** GC04 · **Empresa:** Practical Software\
**Equipo:** Hugo (Product Owner), Gled (Tech Lead) y José (Scrum Master) · **Fecha:** 16/10/2026\
**Repositorio:** <https://github.com/UniExtremadura/Proyecto_GC04> (etiqueta `planificacion-v1`) · **Wiki:** <https://github.com/UniExtremadura/Proyecto_GC04/wiki>

## Resumen

Practical Software planifica **ErasmusNest**, una plataforma web para que los estudiantes de la UEx que se van de movilidad subalquilen su habitación a otros estudiantes verificados. El cliente es la Delegación de Estudiantes UEx.

El catálogo tiene **24 historias en 5 épicas**. Estimado en Planning Poker, suma **84 h-p**, mientras que la capacidad efectiva del equipo en las 6 iteraciones es de **35,72 h-p**. Por eso el PO fijó una **línea base de 8 historias *Must*** (DEC-016), que forman el recorrido esencial: verificarse, publicar, buscar y contactar. Esa línea base suma **32 h-p, el 90 % de la capacidad**.

La planificación por persona e iteración no tiene sobreasignaciones, respeta todas las dependencias y deja un margen de 3,72 h-p. Se ha trasladado a ProjectLibre en dos vistas, por sprints y por épicas. El coste planificado es de **2.507,64 €**: 1.130,70 € de desarrollo y 1.376,94 € de gestión. La planificación es **viable**, con dos puntos que se vigilan de forma explícita: la holgura mínima por persona en IT-001, IT-003 e IT-005, y un camino crítico que atraviesa las tres releases.

Este informe sigue los 15 puntos de «Planificación Práctica 1». El detalle está en el repositorio y en la Wiki, que es donde se justifica cada decisión (DEC-001…DEC-022).

| Punto del enunciado | Apartado |
|---|---|
| Empresa · Equipo · Team charter · Idea | 1 · 2 · 3 · 4 |
| Modelo de proceso · Proceso | 5 · 6 |
| Requisitos · Relaciones · Granularidad | 7 · 8 · 9 |
| Esfuerzo del equipo · Priorización | 10 · 11 |
| Estimación de requisitos · Estimación del proyecto | 12 · 13 |
| Planificación · Coste | 14 · 15 |
| Además: riesgos, uso de la IA y viabilidad | 16 · 17 · 18 |

## 1. Empresa

**Practical Software** es una consultora de desarrollo de tres personas. Diseña y construye productos digitales a medida para organizaciones pequeñas y medianas, como asociaciones, instituciones educativas o negocios locales, que necesitan una solución concreta entregada en plazos cortos. Su propósito es convertir la necesidad real de un cliente en un producto mínimo viable bien planificado, medible y trazable.

Ofrece estos servicios:

- desarrollo web a medida por encargo de un tercero;
- definición de producto (visión, alcance, objetivos y catálogo priorizado);
- gestión ágil con Scrum;
- estimación de esfuerzo, coste y línea base;
- control de calidad, pruebas y despliegue del incremento.

Ficha completa: `docs/organization/empresa.md` (DEC-004).

## 2. Equipo

| Miembro | Rol | Responsabilidad principal |
|---|---|---|
| Hugo | Product Owner | Visión, valor, stakeholders, alcance, prioridad y criterios de aceptación |
| Gled | Tech Lead | Coherencia técnica, descomposición en tareas, dependencias, restricciones y riesgos técnicos |
| José | Scrum Master | Proceso, ceremonias, impedimentos, acuerdos y trazabilidad |
| Los tres | DevOps/QA | Pruebas, entornos, integración y guion de instalación, repartidos en cada Sprint Planning |

El enunciado prevé equipos de 4 o 5 personas. Somos 3, así que, siguiendo la indicación del profesor, DevOps/QA no tiene un responsable único: se reparte de forma equilibrada y el reparto de cada sprint consta en su acta (DEC-002).

## 3. Team charter

El team charter (`docs/organization/team-charter.md`, versión 1.2) recoge **20 acuerdos medibles** en siete bloques:

- **Comunicación (1–3):** revisar WhatsApp y Discord cada día laborable y responder en 24 h (48 h en fin de semana). WhatsApp es el canal asíncrono y Discord el técnico y de voz.
- **Reuniones (4–6):** reunión fija los miércoles de 45 min (planning y refinamiento) con acta en la Wiki el mismo día, daily asíncrona antes de las 22:00, y review y retrospectiva al cierre de cada release, con 1 o 2 acciones de mejora con responsable y fecha.
- **Disponibilidad y ausencias (7–8):** cada miembro respeta el horario declarado en la capacidad; las ausencias se avisan con 24 h de antelación.
- **Decisiones (9–11):** el PO decide sobre producto y alcance, el TL sobre técnica, y toda decisión relevante se registra el mismo día.
- **Conflictos y feedback (12–13):** si no hay acuerdo en 48 h, decide el TL o el PO; el feedback es directo, en el canal o en la PR.
- **Calidad y herramientas (14–16):** límite de una tarea activa por persona, ningún cambio en `main` sin PR revisada por otra persona, y el repositorio como única fuente oficial.
- **Profesorado y señales de alerta (17–20):** qué hacer ante retrasos de más de 2 días, bloqueos de más de 24 h, incumplimientos repetidos o falta de implicación.

El charter se revisó dos veces para alinearlo con los enunciados y con el proceso (DEC-017 y DEC-019).

## 4. Idea de producto

**Problema.** Los estudiantes de la UEx que se van de Erasmus o de prácticas dejan su habitación vacía o la subalquilan por canales informales, como grupos de WhatsApp, Facebook o el boca a boca. Así no saben si la otra parte es estudiante de verdad, cuesta encontrar opciones que encajen con unas fechas concretas y no hay ninguna garantía.

**Propuesta de valor.** ErasmusNest reúne en un solo lugar tres cosas: la **verificación institucional** por correo de la UEx, la **publicación y búsqueda** de habitaciones por fechas y zona, y el **contacto** dentro de la plataforma. Esto aporta confianza, centralización y un estado de cada anuncio siempre visible (`specs/product/mission.md`).

**Stakeholders:**

- SH-01: Delegación de Estudiantes UEx, el cliente. Valida el producto y acepta el incremento en cada release; en la asignatura la representa el profesorado.
- SH-02 y SH-03: estudiantes oferentes y demandantes.
- SH-04: profesorado.
- SH-05: el propio equipo.

**Objetivos y métricas** (`specs/product/goals.md`):

| Objetivo | Métrica de éxito |
|---|---|
| G-01 Autonomía del estudiante | M-01: 4 de 5 participantes completan el recorrido esencial en menos de 5 min y sin ayuda |
| G-02 Confianza institucional | M-02: el 100 % de los intentos de publicar o contactar sin verificar se rechazan |
| G-03 Claridad del estado | M-03: 4 de 5 participantes identifican si un anuncio está disponible o cerrado |
| G-04 Incremento revisable | M-04: una persona ajena al equipo instala y evalúa el incremento con el guion, sin ayuda no documentada |
| Todos | M-05: ningún defecto bloqueante abierto en la Review de cada release |

**Alcance (S-01…S-06):** registro y verificación con correo institucional; publicación de anuncios; búsqueda por fechas y zona; contacto dentro de la plataforma; cierre manual del anuncio, y datos sintéticos.

**Exclusiones (EX-01…EX-05):** pagos, contratos o firma, app móvil nativa, verificación por DNI o videollamada, e integración con sistemas reales de la UEx.

**Supuestos (SU-01…SU-08) y restricciones:** solo datos sintéticos, 6 semanas fijas, 3 personas y tecnologías por aprobar en la asignatura.

## 5. Modelo de proceso

Elegimos **Scrum adaptado a un equipo de tres personas** (DEC-011), por tres motivos:

1. **Los requisitos se afinarán con el cliente** al final de cada release. Un modelo iterativo incorpora ese feedback pronto; uno en cascada lo recibiría al final.
2. **El plazo es fijo** (6 semanas). En Scrum lo que se ajusta es el alcance: primero salen las *Could*, después las *Should*. Esto encaja con la regla de ajuste que hemos aplicado (apartado 13).
3. **La asignatura usará después Jira**, que está pensado para trabajar con backlog, sprints e historias.

La adaptación consiste en sprints de una semana (las releases de dos semanas siguen el enunciado), una daily asíncrona por WhatsApp, porque el equipo comparte el proyecto con otras asignaturas, y DevOps/QA repartido entre los tres.

## 6. Proceso

**Calendario.** Hay 6 sprints de una semana, de miércoles a martes, agrupados en 3 releases de dos semanas. IT-001 empieza el **miércoles 21/10/2026**, después de esta entrega. Es un supuesto del equipo a falta de calendario oficial. IT-006 termina el martes 01/12/2026.

| Release | Sprints | Fechas | Objetivo |
|---|---|---|---|
| R1 | IT-001, IT-002 | 21/10 – 03/11 | Cuentas verificadas: registro, verificación e inicio de sesión |
| R2 | IT-003, IT-004 | 04/11 – 17/11 | Publicar y listar anuncios (release ligera por los parciales de IT-003) |
| R3 | IT-005, IT-006 | 18/11 – 01/12 | Ver el detalle, contactar y guion de instalación. En su Review se miden M-01, M-02 y M-04 |

**Ceremonias:**

| Ceremonia | Cuándo | Duración |
|---|---|---|
| Sprint Planning | Miércoles | 30 min |
| Refinamiento | Miércoles | 15 min (PO y TL, facilitado por el SM) |
| Daily asíncrona | WhatsApp | ≈5 min al día |
| Sprint Review | Martes de cierre de release | 20 min, con el cliente |
| Retrospectiva | Cierre de release | 20–30 min |

**Artefactos:**

- **Product Backlog:** los `spec.md` de cada épica más el Excel. Más adelante pasará a Jira con los mismos identificadores.
- **Sprint Backlog:** historias descompuestas en tareas `TK-XXX` y subtareas `ST-XXXX`.
- **Incremento:** la rama `main` que cumple la DoD.

**Flujo de una historia:**

`Backlog → (DoR) → Por hacer → En curso → Revisión (PR y criterios de aceptación) → (DoD) → Hecho`

El límite de trabajo en curso es de una tarea por persona.

**Definition of Ready** (`docs/quality/definition-of-ready.md`). Una historia está lista cuando cumple:

- identificador estable y trazable a G-XX y S-XX;
- formato *Como… quiero… para…*;
- al menos dos criterios de aceptación observables con datos sintéticos;
- reglas de negocio y NFR identificados;
- prioridad justificada y estimación consensuada;
- incertidumbre indicada;
- predecesoras resueltas;
- que **quepa en un sprint**, o como excepción en dos iteraciones consecutivas (DEC-018).

**Definition of Done** (`docs/quality/definition-of-done.md`). Una historia está hecha cuando:

- todos los criterios de aceptación están verificados y con evidencia;
- las pruebas cubren los errores y los casos límite, solo con datos sintéticos;
- se ha integrado mediante una PR revisada por otra persona;
- arranca con el guion de instalación;
- cumple los NFR (validación en servidor, privacidad y accesibilidad);
- la trazabilidad `US-XXX`/`TK-XXX` aparece en commits y PR;
- el PO la ha aceptado.

**Gestión de cambios.** Un requisito nuevo entra al Product Backlog y el PO lo prioriza. Si es urgente y cabe, entra al sprint en curso a cambio de otra historia de valor igual o menor. Si afecta a objetivos o alcance, sigue la *Gestión de cambios* de `goals.md` y se registra con una DEC.

**Trazabilidad:**

`Misión → G-XX → EP-XXX → US-XXX → Plan → TK-XXX → Código y pruebas → Evidencias`

Toda decisión queda en el *Registro de decisiones* de la Wiki y toda reunión en *Actas*.

## 7. Requisitos

El catálogo tiene **24 historias de usuario agrupadas en 5 épicas**. Cada épica nace de un elemento de alcance y contribuye al menos a un objetivo, así que no hay épicas huérfanas (DEC-012). Cada historia está especificada en el `spec.md` de su épica.

| Épica | Alcance · Objetivos | Historias | En la línea base |
|---|---|---|---|
| EP-001 Cuenta y verificación institucional | S-01 · G-02, G-01 | US-001…US-005 | US-001, US-002, US-003 |
| EP-002 Publicación y gestión de anuncios | S-02, S-05 · G-01, G-03 | US-006…US-011 | US-006 |
| EP-003 Búsqueda y consulta de habitaciones | S-03 · G-01, G-03 | US-012…US-016 | US-012, US-015 |
| EP-004 Contacto y mensajería | S-04 · G-01, G-02 | US-017…US-021 | US-017 |
| EP-005 Entrega, datos de demo e inclusión | S-06 · G-04, G-01 | US-022…US-024 | US-023 |

**Muestra detallada.** Seis historias tienen especificación completa: US-001, US-002, US-006, US-009, US-013 y US-017, marcadas con ⭐. Son las de **más riesgo o más reglas**: verificación, publicación, cierre, solapamiento de fechas y contacto. Cada una incluye necesidad, objetivo, historia, prioridad justificada, estimación, incertidumbre, predecesoras, criterios de aceptación (`US-XXX-AC-NN`), reglas, restricciones, NFR, ejemplos, casos límite y supuestos.

Por ejemplo, US-001 tiene 6 criterios de aceptación, una tabla de ejemplos de entrada y resultado, y casos límite como correos con mayúsculas, subdominios falsos o el doble clic. El resto del catálogo incluye necesidad, objetivo, historia, criterios, reglas, restricciones, NFR y dependencias.

**Requisitos no funcionales y reglas** (`specs/product/nfr-y-reglas.md`):

- **NFR-001…NFR-009:** seguridad, privacidad, usabilidad, accesibilidad WCAG 2.1 AA, rendimiento, compatibilidad, validación en servidor, instalabilidad en 10 pasos como máximo y datos sintéticos.
- **RN-01…RN-11:** por ejemplo, el dominio institucional, el bloqueo de cuentas sin verificar, la caducidad del enlace a las 24 h, que las fechas de búsqueda sean inclusivas o que solo haya un anuncio abierto por usuario.

**Revisión con agentes.** El catálogo se revisó dos veces con el *Requirements Agent* (apartado 17):

- el informe v01 detectó F-01…F-08;
- el informe v02, en modo RECHECK, detectó R-01 y R-02.

Todos los hallazgos se clasificaron y los aceptados se corrigieron. El catálogo aceptado está marcado con la etiqueta `requisitos-v0`.

## 8. Relaciones entre requisitos

Hemos identificado tres tipos de dependencia (DEC-014), que están en la hoja *Dependencias* del Excel, en el campo *Predecesoras* de cada historia y en la Wiki con un diagrama de todo el catálogo:

- **30 funcionales:** una historia necesita que otra esté terminada.
- **Técnicas (TD-01…TD-06):** piezas compartidas que conviene construir una sola vez. TD-01…TD-04 son tareas dentro de la estimación de su primera historia; TD-05 y TD-06 no se planifican porque sus historias quedan fuera de la línea base (DEC-020).
- **Externas (EXT-01…EXT-04):** dependen de algo que el equipo no controla. Cada una tiene un responsable y una fecha límite:
  - EXT-01 (tecnologías aprobadas) y EXT-02 (buzón de pruebas): Gled, antes del 21/10;
  - EXT-03 (evaluador externo): José, antes del 18/11.

Dependencias funcionales de la línea base. El camino crítico va en rojo:

![Dependencias de la línea base y camino crítico](img/dependencias.png)

El **camino crítico** es US-001 → US-002 → US-006 → US-012 → US-015 → US-017 → US-023. Son siete historias encadenadas que recorren las tres releases. US-003 se hace en paralelo en R1 y también es predecesora de US-006.

## 9. Granularidad

La descomposición tiene cuatro niveles: épica (`EP-XXX`) → historia (`US-XXX`) → tarea (`TK-XXX`) → subtarea (`ST-XXXX`).

- **Historias pequeñas.** Ninguna historia estimada supera las 5 h-p, salvo US-021, que es *Won't* y no se estimó en el Planning Poker. Por ejemplo, la búsqueda se dividió en listar, filtrar por fechas, filtrar por zona, ver el detalle y ordenar, como en el ejemplo de la asignatura de *Comprar vuelo*.
- **Regla de tamaño (DoR).** Cada historia debe caber en la capacidad efectiva de su sprint. Con las ausencias, esa capacidad baja a 3,95 h-p en IT-003 y a 5,95 h-p de IT-004 a IT-006, y ninguna persona puede hacer sola más de 2,41 h-p a la semana. Por eso, **US-002, US-006 y US-017** usan la excepción de DEC-018 y ocupan dos iteraciones consecutivas. Dividirlas llevaría el catálogo a 27 requisitos, por encima del máximo de 25, y sus partes por separado no tendrían un resultado observable. La justificación está en el *Plan inicial* de su `spec.md`.
- **Tareas.** IT-001 está detallada a nivel de tarea: TK-001…TK-007, de entre 0,5 y 1,5 h-p, con responsable, criterios y coste (hoja *Tareas IT-001*). Las demás iteraciones se detallan en su Sprint Planning, y las subtareas `ST-XXXX` se crearán en Jira.

## 10. Esfuerzo del equipo (capacidad)

El método está en DEC-015 y en la hoja *Capacidad* del Excel:

`Efectiva (semana) = (Nominal − Ceremonias − Coordinación del rol) × (1 − % imprevistos)`

`Efectiva (IT) = Efectiva (semana) − Ausencias con fecha de esa IT`

- **Ceremonias:** 1,57 h por semana y persona (planning 0,5 h, daily 0,4 h, refinamiento 0,25 h y review más retro 0,42 h).
- **Coordinación propia del rol:** PO 0,5 h, TL 0,5 h y SM 0,75 h.
- **% de imprevistos:** no se aplica un factor común. Cada miembro justifica el suyo, y solo recoge lo que no se puede fechar.

| Miembro | Nominal (h/sem) | Gestión (h/sem) | % imprevistos y motivo | Efectiva (h-p/sem) |
|---|---|---|---|---|
| Hugo (PO) | 5 | 2,07 | 20 %: otras 5 asignaturas con entregas cortas sin fecha | 2,34 |
| Gled (TL) | 5 | 2,07 | 25 %: misma carga y además los bloqueos técnicos imprevisibles del equipo | 2,20 |
| José (SM) | 5 | 2,32 | 10 %: solo 2 asignaturas más, la disponibilidad más estable | 2,41 |
| **Equipo** | **15** | **6,46** | | **6,95** |

Ausencias declaradas el 13/10/2026, que se restan en su iteración:

| Miembro | Fecha | Iteración | Motivo | Horas |
|---|---|---|---|---|
| Hugo | 05/11 | IT-003 | Parcial | 1,5 |
| Gled | 06/11 | IT-003 | Parcial | 1,5 |
| José | 12/11 | IT-004 | Parcial | 1 |
| Hugo | 20/11 | IT-005 | Entrega de otra asignatura | 1 |
| Gled | 28/11 | IT-006 | Viaje familiar | 1 |

| | IT-001 | IT-002 | IT-003 | IT-004 | IT-005 | IT-006 | Total |
|---|---|---|---|---|---|---|---|
| Hugo | 2,34 | 2,34 | 0,84 | 2,34 | 1,34 | 2,34 | 11,56 |
| Gled | 2,20 | 2,20 | 0,70 | 2,20 | 2,20 | 1,20 | 10,69 |
| José | 2,41 | 2,41 | 2,41 | 1,41 | 2,41 | 2,41 | 13,47 |
| **Equipo (h-p)** | **6,95** | **6,95** | **3,95** | **5,95** | **5,95** | **5,95** | **35,72** |

Las 90 h nominales del proyecto (5 h × 3 personas × 6 semanas) se reparten así:

| Concepto | Horas | % |
|---|---|---|
| Gestión (ceremonias y coordinación) | 38,76 h | 43 % |
| Reserva de imprevistos | 9,52 h | 11 % |
| Ausencias | 6 h | 7 % |
| Capacidad efectiva para desarrollo | 35,72 h-p | 40 % |

## 11. Priorización

Usamos **MoSCoW** con una escala de 0 a 10 (DEC-013). Cada historia se justifica con el formato **«Valor · Riesgo · Dependencias»**, en su `spec.md` y en la columna correspondiente del Excel:

- **Valor:** a qué objetivo y métrica contribuye.
- **Riesgo:** lo que tiene incertidumbre se adelanta.
- **Dependencias:** una historia de la que dependen otras sube de prioridad.

| MoSCoW | Escala | Criterio | Historias | h-p |
|---|---|---|---|---|
| Must | 8–10 | Sin ella no existe el recorrido esencial de G-01, no se cumple G-02 o no se puede evaluar el incremento (G-04) | 8 | 32 |
| Should | 5–7 | Mejora claramente un objetivo, pero el recorrido funciona sin ella | 10 | 37 |
| Could | 2–4 | Comodidad o valor para un grupo concreto | 4 | 10 |
| Won't | 0–1 | Fuera del alcance o del plazo de este proyecto (US-021 y US-024) | 2 | — |

Esta distribución es el resultado de **DEC-016**. Antes había 14 historias *Must* (56 h-p), que no cabían en la capacidad. El PO bajó seis a *Should*, con un orden de entrada fijado por si sobra capacidad: US-009, US-022, US-018, US-013, US-010 y US-014. También pasó US-024 (interfaz en inglés) a *Won't*, porque no formaba parte del alcance S-01…S-06.

## 12. Estimación de requisitos

La unidad es la **hora-persona (h-p)**, para comparar directamente con la capacidad y calcular el coste. La técnica es **Planning Poker** (sesión del 10/10/2026, con acta en la Wiki), con la baraja 1, 2, 3, 5, 8, 13 y 20:

1. El PO lee la historia y sus criterios.
2. Cada miembro vota en privado y los votos se muestran a la vez.
3. Si la carta mayor es más del doble que la menor, quienes votaron la más alta y la más baja explican sus motivos y se vuelve a votar.
4. El consenso es siempre una carta de la baraja. Si se duda entre dos, se elige la mayor (DEC-018).

| Historia (línea base) | Hugo | Gled | José | Consenso | Motivo |
|---|---|---|---|---|---|
| US-001 | 8 | 5 | 5 | **5** | Entorno sintético, sin servicios reales |
| US-002 | 5 | 5 | 8 | **5** | El token se simula en la base de datos |
| US-003 | 3 | 5 | 3 | **3** | Sesión estándar, sin OAuth |
| US-006 | 8 | 5 | 5 | **5** | Las fotos quedan en US-007 |
| US-012 | 3 | 5 | 3 | **3** | Listado público paginado |
| US-015 | 3 | 3 | 3 | **3** | Unanimidad |
| US-017 | 3 → 5 | 8 → 5 | 5 → 5 | **5** | Se revotó: la persistencia de la conversación es estándar |
| US-023 | 2 | 3 | 3 | **3** | Guion reproducible en el README |

Se estimaron las 23 historias planificables; US-021 (*Won't*) no se estimó. Solo US-017 necesitó una segunda votación. Las estimaciones individuales y consensuadas de todo el catálogo están en el Excel (hoja *Requisitos*) y en la Wiki (*Requisitos y priorización*).

## 13. Estimación del proyecto

| Concepto | h-p |
|---|---|
| Catálogo V0 (estimación de referencia, antes del Planning Poker) | 139 |
| Catálogo consensuado (23 historias) | 84 (Must 56 · Should 13 · Could 15) |
| Capacidad efectiva del equipo (6 IT) | 35,72 |
| **Línea base planificada (DEC-016)** | **32**, el 90 % de la capacidad |
| Margen del proyecto | 3,72, el 10 % |

Ni siquiera las historias *Must* de entonces (56 h-p) cabían en la capacidad. Aplicamos la **regla de ajuste** del equipo:

1. Confirmar la disponibilidad real y las ausencias (13/10).
2. Confirmar las estimaciones con Planning Poker (10/10).
3. Recortar alcance, primero *Could*, luego *Should* y, si hace falta, redefinir qué es *Must*.

Nunca se infla la capacidad ni se rebajan estimaciones para que cuadren. El PO eligió el escenario «verificarse, publicar, buscar y contactar», que cubre G-01, G-02 y G-04. G-03 y M-03 quedan aplazados hasta que entre US-009 (cerrar el anuncio), la primera candidata si la velocidad real deja capacidad libre.

**Esfuerzo total planificado:** 32 h-p de desarrollo + 38,76 h de gestión = **70,76 h**. Es el trabajo total del proyecto en ProjectLibre.

## 14. Planificación

### 14.1 Por release, iteración y persona (DEC-020)

| IT | Release | Historias | Carga (h-p) | Capacidad | Holgura | Ocupación |
|---|---|---|---|---|---|---|
| IT-001 | R1 | US-001, US-002 (1/2) | 6,8 | 6,95 | 0,15 | 98 % |
| IT-002 | R1 | US-002 (2/2), US-003 | 6,2 | 6,95 | 0,75 | 89 % |
| IT-003 | R2 | US-006 (1/2) | 3,8 | 3,95 | 0,15 | 96 % |
| IT-004 | R2 | US-006 (2/2), US-012 | 4,2 | 5,95 | 1,75 | 71 % |
| IT-005 | R3 | US-015, US-017 (1/2) | 5,8 | 5,95 | 0,15 | 97 % |
| IT-006 | R3 | US-017 (2/2), US-023 | 5,2 | 5,95 | 0,75 | 87 % |
| **Total** | | **8 historias** | **32** | **35,72** | **3,72** | **90 %** |

![Carga frente a capacidad por iteración](img/carga.png)

| Persona | Asignado (h-p) | Capacidad (h-p) | Ocupación | Holgura mínima en una IT |
|---|---|---|---|---|
| Hugo (PO) | 10,5 | 11,56 | 91 % | 0,044 (IT-001, IT-003 e IT-005) |
| Gled (TL) | 9,4 | 10,69 | 88 % | 0,0975 (IT-001, IT-003, IT-005 e IT-006) |
| José (SM) | 12,1 | 13,47 | 90 % | 0,012 (IT-001, IT-003 e IT-005) |

Las horas de cada persona en cada historia e iteración están en la hoja *Planificación*, en tramos de 0,1 h. Cada historia tiene un responsable, que es quien más horas le dedica. Las comprobaciones con fórmulas de esa hoja dan este resultado:

- 0 celdas en sobrecarga;
- 0 h-p sin asignar;
- 0 historias que incumplen la regla de tamaño;
- 0 predecesoras mal ordenadas;
- 3 excepciones de DEC-018 justificadas.

El Excel marca como **AJUSTADO** las 10 celdas persona × iteración con menos de 0,1 h-p de holgura. No son sobrecargas. Repartir de otra forma tampoco crea margen, porque en IT-001, IT-003 e IT-005 la holgura de todo el equipo es de 0,15 h-p. Para eso DEC-020 fija una **regla de contingencia**:

1. En cada Sprint Planning, el SM revisa la carga de cada persona con los valores sin redondear.
2. Si alguien pierde capacidad, sus horas pasan a otro miembro con holgura en la misma iteración.
3. Si no la hay, la parte pendiente pasa a la siguiente iteración de su release. La holgura es de 0,91 h-p en R1, 1,91 en R2 y 0,91 en R3.
4. Si tampoco cabe, no entra ninguna historia *Should*.

**Carga semanal total frente a las 5 h nominales.** Esta tabla suma el desarrollo y la gestión, y la compara con lo que queda de las 5 h tras las ausencias. Nadie supera su disponibilidad en ninguna semana:

| h/semana | IT-001 | IT-002 | IT-003 | IT-004 | IT-005 | IT-006 |
|---|---|---|---|---|---|---|
| Hugo (disponible) | 4,37 (5) | 4,17 (5) | 2,87 (3,5) | 4,07 (5) | 3,37 (4) | 4,07 (5) |
| Gled (disponible) | 4,17 (5) | 3,97 (5) | 2,67 (3,5) | 3,67 (5) | 4,17 (5) | 3,17 (4) |
| José (disponible) | **4,72** (5) | 4,52 (5) | **4,72** (5) | 2,92 (4) | **4,72** (5) | 4,42 (5) |

### 14.2 ProjectLibre

La planificación viable está en ProjectLibre, en `docs/project/planning/projectlibre/`. Hay dos proyectos guardados como `.pod` con su línea base, junto con su XML (la fuente) y el Gantt exportado en PDF:

- **Por sprints** (`ErasmusNest_GC04_por_sprints.pod`): IT-001…IT-006. Cada sprint tiene sus tareas de gestión (MGT) y sus historias, y los hitos R1, R2 y R3 cierran cada release.
- **Por épicas** (`ErasmusNest_GC04_por_epicas.pod`): una copia con dos ramas, *Gestión (MGT)* por sprint e *Historias de usuario* por EP-001…EP-005, como en el ejemplo del enunciado.

| Elemento | Cómo está definido |
|---|---|
| Calendario | Lunes a viernes, 8:00–12:00 y 13:00–17:00. Sprints de miércoles a martes, del 21/10 al 01/12/2026 |
| Recursos | Hugo 35 €/h, Gled 40 €/h y José 32 €/h. Capacidad máxima del 12,5 %, que son 5 h a la semana sobre una jornada de 40 h |
| Tareas de gestión | Por sprint y persona: planning y refinamiento 0,75 h, daily 0,4 h, review y retro 0,42 h, y coordinación del rol 0,5 h (0,75 h el SM). En total, 6,46 h por sprint |
| Historias | Las 8 de la línea base con sus IDs oficiales. US-002, US-006 y US-017 están en dos tramos, uno por sprint |
| Asignaciones | Horas por persona, historia e iteración iguales a la hoja *Planificación* |
| Dependencias | Las 9 de la línea base: fin-comienzo entre sprints y comienzo-comienzo dentro del mismo sprint. Cada hito depende de todas las historias o tramos que terminan en su release |
| Hitos | R1 el 03/11, R2 el 17/11 y R3 (fin del proyecto) el 01/12/2026 |
| Totales | 70,76 h de trabajo y 2.507,64 € de coste, igual que el Excel |

Decisiones de modelado (DEC-022, detalladas en el `LEEME.md` de la carpeta):

1. **Cada tarea dura el sprint completo.** Con 5 h a la semana, una tarea de un día marcaría sobreasignaciones que no existen. Así, ProjectLibre compara la carga semanal con la capacidad semanal, igual que el Excel.
2. **Sin *Project grooming*.** El Excel no le asigna horas, y añadirla cambiaría el coste aprobado.
3. **El festivo del 02/11 no se marca como no laborable.** La capacidad de 5 h a la semana no depende de la jornada laboral, y ProjectLibre 1.9.3 no importa bien la excepción desde el XML.
4. **Moneda.** ProjectLibre muestra «$», pero los importes son euros.

### 14.3 Hitos, camino crítico y reservas

- **Hitos:** R1 (cuentas verificadas), R2 (publicar y listar) y R3 (contacto y guion; fin del proyecto). Los tres están sobre el camino crítico del apartado 8.
- **Holguras:** 3,72 h-p de margen total, el 10 %. R2 es la release con más holgura (1,91 h-p), a propósito, porque en IT-003 coinciden dos parciales.
- **Reservas:**
  - 9,52 h de reserva de imprevistos, dentro del % individual;
  - el margen de 3,72 h-p, que solo se usa con decisión del PO;
  - el orden de entrada de las *Should*, si sobra capacidad.

## 15. Coste

El coste es la suma de las horas asignadas por la tarifa justificada de cada rol. Se aplica a las horas de desarrollo y a las de gestión. Las tarifas las acordó el equipo el 14/10/2026 (DEC-020):

| Rol | Tarifa | Justificación |
|---|---|---|
| Tech Lead | 40 €/h | La más alta: responsabilidad técnica, arquitectura y revisión de código |
| Product Owner | 35 €/h | Gestiona el valor y el backlog, y acepta las historias |
| Scrum Master | 32 €/h | La más baja: facilita el proceso, sin responsabilidad técnica ni de producto |

| Persona | Desarrollo (h-p) | Coste desarrollo | Gestión (h) | Coste gestión | **Total** |
|---|---|---|---|---|---|
| Hugo (PO) | 10,5 | 367,50 € | 12,42 | 434,70 € | **802,20 €** |
| Gled (TL) | 9,4 | 376,00 € | 12,42 | 496,80 € | **872,80 €** |
| José (SM) | 12,1 | 387,20 € | 13,92 | 445,44 € | **832,64 €** |
| **Equipo** | **32** | **1.130,70 €** | **38,76** | **1.376,94 €** | **2.507,64 €** |

| Iteración | IT-001 | IT-002 | IT-003 | IT-004 | IT-005 | IT-006 | **Total** |
|---|---|---|---|---|---|---|---|
| Desarrollo | 241,30 € | 219,90 € | 128,80 € | 153,20 € | 206,30 € | 181,20 € | 1.130,70 € |
| Gestión | 229,49 € | 229,49 € | 229,49 € | 229,49 € | 229,49 € | 229,49 € | 1.376,94 € |
| **Total** | 470,79 € | 449,39 € | 358,29 € | 382,69 € | 435,79 € | 410,69 € | **2.507,64 €** |
| Acumulado | 470,79 € | 920,18 € | 1.278,47 € | 1.661,16 € | 2.096,95 € | 2.507,64 € | |

| Épica (desarrollo) | EP-001 | EP-002 | EP-003 | EP-004 | EP-005 |
|---|---|---|---|---|---|
| Historias | US-001, 002, 003 | US-006 | US-012, 015 | US-017 | US-023 |
| Coste | 461,20 € | 174,80 € | 211,00 € | 181,70 € | 102,00 € |

El coste por historia y por tarea de IT-001 está en la hoja *Coste* y en la hoja *Tareas IT-001* (241,30 €, que cuadra con IT-001). El total coincide en el Excel y en ProjectLibre. El cliente no fija presupuesto, porque es un proyecto académico. Como tope de referencia se usa la dedicación nominal completa (90 h × tarifa = 3.210 €), lo que deja **702,36 € de margen**. El coste es una proyección de la planificación, no un coste incurrido.

## 16. Riesgos

El registro (`docs/project/risks/riesgos.md`) tiene 12 riesgos. Cada uno incluye situación, evento, consecuencia, probabilidad, impacto, tratamiento y responsable. Los más importantes son estos:

| ID | Riesgo | P · I | Tratamiento |
|---|---|---|---|
| RSK-01 | 10 celdas AJUSTADAS: un imprevisto provoca una sobreasignación | Alta · Alto | Mitigar con la regla de contingencia de DEC-020 (José) |
| RSK-02 | IT-003 solo tiene 3,95 h-p y en ella empieza US-006 | Media · Alto | Mitigar: US-006 en dos sprints y TD-03 preparada con la holgura de IT-002 (Gled) |
| RSK-03 | La cadena casi lineal hace que todo esté en el camino crítico | Media · Alto | Mitigar con el margen del 10 %, revisión al cierre de cada IT y orden de recorte acordado (Hugo) |
| RSK-06 | EXT-01: las tecnologías aprobadas se retrasan o cambian | Media · Alto | Evitar: confirmarlas antes del 21/10 (Gled) |
| RSK-08 | EXT-03: el evaluador externo no está disponible en IT-006 | Media · Medio | Mitigar: localizarlo antes de IT-005 (José) |
| RSK-11 | Se fusiona una versión antigua del Excel, que es binario | Media · Alto | Evitar: un solo responsable y una exportación en texto en cada cambio (Gled) |

Los demás cubren el peso de la gestión (43 %), estimaciones con incertidumbre Media, el buzón de pruebas, ausencias no previstas, la reproducibilidad de los agentes y el plazo de esta entrega. El registro se revisa en cada Sprint Planning.

## 17. Uso de la IA

Lo regula DEC-021 y queda registrado en `docs/project/reviews/registro-ia.md`.

- **Herramienta:** GitHub Copilot en VS Code, con tres agentes propios en `.github/agents/` hechos a partir de las plantillas del profesor. El modelo está en *Auto*, el único modo que permite el plan Copilot Student; los tres usamos el mismo.
  - **Requirements Agent:** revisa el catálogo. Generó el informe v01 y el v02 en modo RECHECK. No modifica requisitos.
  - **Planning Agent:** revisa capacidad, carga, secuencia, coste y margen. Su informe v01 en modo CONSISTENCY dio READY_WITH_NON_BLOCKING_QUESTIONS, con 0 hallazgos bloqueantes y 3 menores aceptados. Lee el Excel a través de su exportación en texto.
  - **Project Auditor** (opcional, Hito 4): comprueba las cuatro dimensiones del enunciado, es decir, riesgos, ProjectLibre, viabilidad de la asignación y viabilidad de la secuencia. Gled lo ejecutó el 16/10/2026 sobre la planificación final. Su informe está en `docs/project/reviews/audit/audit-report-v01.md` y la clasificación en `registro-ia.md`, §3. Resultado: **CONFORME_CON_OBSERVACIONES**, con 0 no conformidades y 2 observaciones menores, ambas aceptadas:
    - AUD-v01-001: RSK-07 no tenía fecha límite para el buzón de pruebas (EXT-02); se añade el 21/10.
    - AUD-v01-002: en ProjectLibre, el orden dentro del sprint de los enlaces comienzo-comienzo no se ve; se fijará en el Sprint Planning de IT-004, IT-005 e IT-006.
- **Cómo se decide:** cada hallazgo se comprueba contra la fuente citada y se clasifica como aceptado, aceptado con cambios, rechazado o pendiente, con su justificación. Ninguna propuesta se aplica sin revisarla.
- **Asistente conversacional (Claude):** se usó para auditar los hitos frente a los enunciados, preparar los escenarios de DEC-016, hacer el borrador de la planificación, el generador de los XML de ProjectLibre y el registro de riesgos, y redactar este informe. Cada uso consta en el registro (§4) con la revisión del equipo. Las decisiones de alcance, reparto, tarifas y riesgos las tomó el equipo.

## 18. Viabilidad y conclusiones

| Criterio | Resultado |
|---|---|
| Carga frente a capacidad (equipo) | 32 frente a 35,72 h-p (90 %). Viable |
| Sobreasignaciones (persona × semana) | 0, contando gestión y ausencias (máximo 4,72 de 5 h) |
| Trabajo sin asignar | 0 h-p |
| Secuencia y dependencias | Todas respetadas, 0 incidencias. Hitos sobre el camino crítico |
| Coste | 2.507,64 €, por debajo del tope de referencia de 3.210 € |
| Revisión externa | Planning Agent sin bloqueantes. Auditor: CONFORME_CON_OBSERVACIONES, 0 no conformidades y 2 observaciones aceptadas |

La planificación es **viable** y es la línea base del proyecto: etiqueta `planificacion-v1` en el repositorio y `.pod` con línea base en ProjectLibre. Su precio es un alcance reducido al recorrido esencial y un margen individual muy pequeño en tres semanas, por eso hay una regla de contingencia y un orden de entrada de historias decididos de antemano. La primera retrospectiva (cierre de R1, 03/11) revisará dos cosas: el peso de la gestión (43 % de las horas) y si la velocidad real permite empezar a incorporar US-009.

## Anexo A · Entregables y dónde están

| Entregable | Ubicación |
|---|---|
| Wiki: proceso, hitos 1–4, decisiones DEC-001…DEC-022, actas, capacidad, requisitos y dependencias | <https://github.com/UniExtremadura/Proyecto_GC04/wiki> |
| Excel: requisitos, prioridad, estimación, capacidad, planificación, carga, gestión y coste | `docs/ErasmusNest_GC04_Requisitos_Capacidad.xlsx` (exportación en texto `docs/project/planning/excel-export-v02.md`) |
| ProjectLibre por sprints y por épicas (`.pod` con línea base, XML y Gantt en PDF) | `docs/project/planning/projectlibre/` |
| Especificación: constitución, misión, objetivos, NFR y reglas, épicas e historias, DoR y DoD | `.specify/memory/`, `specs/`, `docs/quality/` |
| Organización: empresa y team charter | `docs/organization/` |
| Riesgos | `docs/project/risks/riesgos.md` |
| Agentes, sus informes y registro de uso de IA | `.github/agents/`, `docs/project/reviews/` |
| Versiones | Etiquetas `requisitos-v0` (catálogo aceptado, 14/10/2026) y `planificacion-v1` (esta entrega, 16/10/2026) |

## Anexo B · Decisiones principales

| ID | Decisión |
|---|---|
| DEC-002 | Roles: Hugo PO, Gled TL y José SM; DevOps/QA compartido |
| DEC-005 | Producto ErasmusNest para la Delegación de Estudiantes UEx |
| DEC-011 | Scrum adaptado: sprints de 1 semana y releases de 2 |
| DEC-012 · DEC-013 · DEC-014 | Construcción del catálogo, criterio MoSCoW y tipos de dependencia |
| DEC-015 | Capacidad con descuentos por rol y % de imprevistos individual |
| DEC-016 | Línea base del MVP: 8 historias *Must*, 32 h-p, 90 % de la capacidad |
| DEC-018 | Regla de tamaño de la DoR (excepción de dos iteraciones) y consenso siempre en una carta |
| DEC-020 | Primera planificación por persona e iteración, regla de contingencia y tarifas por rol |
| DEC-021 | Uso de la IA: agentes de Copilot, modo *Auto* y registro |
| DEC-022 | ProjectLibre por sprints y por épicas, registro de riesgos y agente auditor |

La lista completa, con contexto, justificación y quién decidió, está en la página *Registro de decisiones* de la Wiki.

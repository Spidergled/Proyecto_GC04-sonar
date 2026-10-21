# Exportación en texto del Excel de planificación v02

Fuente: `docs/ErasmusNest_GC04_Requisitos_Capacidad.xlsx`. Generada automáticamente a partir de los valores calculados del libro para que los agentes puedan leerlos (el `.xlsx` es binario). Si el Excel cambia, hay que generar una exportación nueva con otro número de versión. Unidades: h-p = horas-persona; capacidades en h-p por persona e iteración (1 iteración = 1 semana, de miércoles a martes). Los números se muestran con 2 decimales; el Excel calcula con todos (por ejemplo, la capacidad semanal de Gled es 2,1975 h-p). El redondeo no cambia ninguna comprobación; la tabla «Holgura mínima por persona» de la hoja Carga se muestra con 3 decimales.

**Cambios respecto a la v01** (tras el informe `initial-planning-review-v01.md`): estado AJUSTADO (holgura menor de 0,1 h-p) en las hojas Planificación y Carga, con su recuento en las comprobaciones globales y la holgura mínima por persona (IPR-v01-002); presupuesto no declarado y margen frente al tope de referencia en la hoja Coste (apartado 8 del informe). El reparto, las estimaciones, la capacidad y el coste no cambian.

## Hoja Requisitos (columnas de planificación)

| ID | Épica | Título | MoSCoW | Prioridad (0-10) | Incertidumbre | Predecesoras | Iteración (de la hoja Planificación) | Release | Est. Hugo (h-p) | Est. Gled (h-p) | Est. José (h-p) | Est. consensuada (h-p) | Est. vigente (h-p) | Discrepancia |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| US-001 | EP-001 | Registrar cuenta con correo institucional | Must | 10 | Baja | — | IT-001 | R1 | 8 | 5 | 5 | 5 | 5 | OK |
| US-002 | EP-001 | Verificar la cuenta mediante el correo institucional | Must | 9 | Media | US-001 | IT-001 – IT-002 | R1 | 5 | 5 | 8 | 5 | 5 | OK |
| US-003 | EP-001 | Iniciar y cerrar sesión | Must | 10 | Baja | US-001 | IT-002 | R1 | 3 | 5 | 3 | 3 | 3 | OK |
| US-004 | EP-001 | Gestionar el perfil básico | Should | 6 | Baja | US-003 | — | — | 3 | 3 | 3 | 3 | 3 | OK |
| US-005 | EP-001 | Recuperar la contraseña | Could | 3 | Baja | US-002, US-003 | — | — | 3 | 5 | 3 | 3 | 3 | OK |
| US-006 | EP-002 | Publicar el anuncio de mi habitación | Must | 10 | Baja | US-002, US-003 | IT-003 – IT-004 | R2 | 8 | 5 | 5 | 5 | 5 | OK |
| US-007 | EP-002 | Añadir fotos al anuncio | Should | 6 | Media | US-006 | — | — | 3 | 5 | 5 | 5 | 5 | OK |
| US-008 | EP-002 | Editar mi anuncio | Should | 5 | Baja | US-006 | — | — | 3 | 3 | 3 | 3 | 3 | OK |
| US-009 | EP-002 | Cerrar mi anuncio | Should | 7 | Baja | US-006 | — | — | 2 | 3 | 3 | 3 | 3 | OK |
| US-010 | EP-002 | Ver mis anuncios y su estado | Should | 6 | Baja | US-006 | — | — | 2 | 3 | 3 | 3 | 3 | OK |
| US-011 | EP-002 | Cierre automático de anuncios caducados | Could | 2 | Baja | US-009 | — | — | 2 | 3 | 2 | 2 | 2 | OK |
| US-012 | EP-003 | Listar los anuncios disponibles | Must | 9 | Baja | US-006 | IT-004 | R2 | 3 | 5 | 3 | 3 | 3 | OK |
| US-013 | EP-003 | Filtrar anuncios por fechas de estancia | Should | 6 | Media | US-012 | — | — | 5 | 8 | 5 | 5 | 5 | OK |
| US-014 | EP-003 | Filtrar anuncios por zona | Should | 5 | Baja | US-012 | — | — | 2 | 3 | 3 | 3 | 3 | OK |
| US-015 | EP-003 | Ver el detalle de un anuncio con su estado | Must | 9 | Baja | US-012 | IT-005 | R3 | 3 | 3 | 3 | 3 | 3 | OK |
| US-016 | EP-003 | Ordenar los resultados | Could | 2 | Baja | US-012, US-013 | — | — | 2 | 2 | 3 | 2 | 2 | OK |
| US-017 | EP-004 | Contactar con el oferente desde un anuncio | Must | 10 | Media | US-015, US-002 | IT-005 – IT-006 | R3 | 5 | 5 | 5 | 5 | 5 | OK |
| US-018 | EP-004 | Mantener una conversación | Should | 7 | Media | US-017 | — | — | 5 | 5 | 8 | 5 | 5 | OK |
| US-019 | EP-004 | Aviso de mensajes nuevos | Should | 5 | Baja | US-018 | — | — | 2 | 3 | 2 | 2 | 2 | OK |
| US-020 | EP-004 | Reportar un anuncio sospechoso | Could | 3 | Baja | US-015, US-003 | — | — | 2 | 3 | 3 | 3 | 3 | OK |
| US-021 | EP-004 | Valorar a la otra parte tras la estancia | Won't | 0 | Alta | US-018 | — | — |  |  |  |  | 8 | Pendiente |
| US-022 | EP-005 | Cargar un conjunto de datos sintéticos | Should | 7 | Baja | US-001, US-006 | — | — | 5 | 5 | 8 | 5 | 5 | OK |
| US-023 | EP-005 | Guion de instalación y evaluación | Must | 8 | Media | US-017 | IT-006 | R3 | 2 | 3 | 3 | 3 | 3 | OK |
| US-024 | EP-005 | Usar la interfaz en inglés | Won't | 1 | Media | US-015, US-018 | — | — | 8 | 5 | 5 | 5 | 5 | OK |

## Hoja Capacidad

### Por miembro (h/semana)

| Miembro | Rol | Horas nominales / semana | Ceremonias Scrum (h/sem) | Coordinación propia del rol (h/sem) | % imprevistos (individual) | Capacidad efectiva / semana (sin ausencias) | % efectivo sobre nominal |
|---|---|---|---|---|---|---|---|
| Hugo | Product Owner | 5 | 1.57 | 0.5 | 0.2 | 2.34 | 0.47 |
| Gled | Tech Lead | 5 | 1.57 | 0.5 | 0.25 | 2.2 | 0.44 |
| José | Scrum Master | 5 | 1.57 | 0.75 | 0.1 | 2.41 | 0.48 |
| EQUIPO |  | 15 |  |  |  | 6.95 | 0.46 |

### Ceremonias Scrum por persona (h/semana)

| Ceremonia | h/semana | Nota |
|---|---|---|
| Sprint Planning (miércoles) | 0.5 | 30 min/semana (team charter, acuerdo 4) |
| Daily asíncrona (WhatsApp) | 0.4 | ≈5 min × 5 días (team charter, acuerdo 5) |
| Refinamiento | 0.25 | 15 min/semana, dentro de la reunión del miércoles (acuerdo 4) |
| Review + Retrospectiva | 0.42 | Review 20 min + retro 20–30 min cada 2 semanas (acuerdo 6) → ≈25 min/semana |

### Ausencias y capacidad efectiva por iteración (h-p)

| Miembro | Ausencias IT-001 (h) | Ausencias IT-002 (h) | Ausencias IT-003 (h) | Ausencias IT-004 (h) | Ausencias IT-005 (h) | Ausencias IT-006 (h) | Efectiva IT-001 | Efectiva IT-002 | Efectiva IT-003 | Efectiva IT-004 | Efectiva IT-005 | Efectiva IT-006 | Total efectiva |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Hugo | 0 | 0 | 1.5 | 0 | 1 | 0 | 2.34 | 2.34 | 0.84 | 2.34 | 1.34 | 2.34 | 11.56 |
| Gled | 0 | 0 | 1.5 | 0 | 0 | 1 | 2.2 | 2.2 | 0.7 | 2.2 | 2.2 | 1.2 | 10.69 |
| José | 0 | 0 | 0 | 1 | 0 | 0 | 2.41 | 2.41 | 2.41 | 1.41 | 2.41 | 2.41 | 13.47 |
| EQUIPO |  |  |  |  |  |  | 6.95 | 6.95 | 3.95 | 5.95 | 5.95 | 5.95 | 35.72 |

### Fechas

| Iteración | Inicio (miércoles) | Fin (martes) | Release |
|---|---|---|---|
| IT-001 | 21/10/2026 | 27/10/2026 | R1 |
| IT-002 | 28/10/2026 | 03/11/2026 | R1 |
| IT-003 | 04/11/2026 | 10/11/2026 | R2 |
| IT-004 | 11/11/2026 | 17/11/2026 | R2 |
| IT-005 | 18/11/2026 | 24/11/2026 | R3 |
| IT-006 | 25/11/2026 | 01/12/2026 | R3 |

Ausencias: cada miembro ha declarado sus parciales, entregas y viajes con fecha entre el 21/10 y el 01/12 y se descuentan en horas en su iteración (Hugo: parcial 05/11, 1,5 h, y entrega 20/11, 1 h; Gled: parcial 06/11, 1,5 h, y viaje 28/11, 1 h; José: parcial 12/11, 1 h). Para no contarlas dos veces, el % de imprevistos solo recoge lo que no se puede fechar.


## Hoja Planificación (h-p por historia, iteración y persona)

Reparto revisado y acordado por el equipo el 14/10/2026. Celdas amarillas: horas que se asignan a cada persona en cada iteración. Las filas 13–16 comprueban la carga frente a la capacidad efectiva de cada persona (hoja Capacidad): no se admite ninguna SOBRECARGA, y AJUSTADO avisa de una holgura menor de 0,1 h-p. Granularidad: 0,1 h. Tres historias usan la excepción de dos iteraciones (DEC-018). Columnas `IT-00n Hugo/Gled/José` = horas asignadas a cada persona en esa iteración.

| ID | Título | Release | Responsable | Predecesoras | Est. (h-p) | IT-001 Hugo | IT-001 Gled | IT-001 José | IT-002 Hugo | IT-002 Gled | IT-002 José | IT-003 Hugo | IT-003 Gled | IT-003 José | IT-004 Hugo | IT-004 Gled | IT-004 José | IT-005 Hugo | IT-005 Gled | IT-005 José | IT-006 Hugo | IT-006 Gled | IT-006 José | Total asignado | Sin asignar | Iteraciones | Capacidad del tramo | Regla de tamaño | Orden de predecesoras | Coste (€) |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| US-001 | Registrar cuenta con correo institucional | R1 | Gled | — | 5 | 1.5 | 2.1 | 1.4 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 5 | 0 | IT-001 | 6.95 | OK (1 iteración) | Sin predecesoras | 181.3 |
| US-002 | Verificar la cuenta mediante el correo institucional | R1 | Gled | US-001 | 5 | 0.8 |  | 1 | 0.6 | 1.6 | 1 |  |  |  |  |  |  |  |  |  |  |  |  | 5 | 0 | IT-001 – IT-002 | 13.91 | Excepción DEC-018 | OK | 177 |
| US-003 | Iniciar y cerrar sesión | R1 | Hugo | US-001 | 3 |  |  |  | 1.5 | 0.3 | 1.2 |  |  |  |  |  |  |  |  |  |  |  |  | 3 | 0 | IT-002 | 6.95 | OK (1 iteración) | OK | 102.9 |
| US-006 | Publicar el anuncio de mi habitación | R2 | José | US-002, US-003 | 5 |  |  |  |  |  |  | 0.8 | 0.6 | 2.4 | 0.4 | 0.8 |  |  |  |  |  |  |  | 5 | 0 | IT-003 – IT-004 | 9.91 | Excepción DEC-018 | OK | 174.8 |
| US-012 | Listar los anuncios disponibles | R2 | Hugo | US-006 | 3 |  |  |  |  |  |  |  |  |  | 1.6 | 0.8 | 0.6 |  |  |  |  |  |  | 3 | 0 | IT-004 | 5.95 | OK (1 iteración) | OK | 107.2 |
| US-015 | Ver el detalle de un anuncio con su estado | R3 | José | US-012 | 3 |  |  |  |  |  |  |  |  |  |  |  |  | 1 | 0.6 | 1.4 |  |  |  | 3 | 0 | IT-005 | 5.95 | OK (1 iteración) | OK | 103.8 |
| US-017 | Contactar con el oferente desde un anuncio | R3 | Gled | US-015, US-002 | 5 |  |  |  |  |  |  |  |  |  |  |  |  | 0.3 | 1.5 | 1 |  | 1.1 | 1.1 | 5 | 0 | IT-005 – IT-006 | 11.91 | Excepción DEC-018 | OK | 181.7 |
| US-023 | Guion de instalación y evaluación | R3 | Hugo | US-017 | 3 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 2 |  | 1 | 3 | 0 | IT-006 | 5.95 | OK (1 iteración) | OK | 102 |
| Total asignado (h-p) |  |  |  |  |  | 2.3 | 2.1 | 2.4 | 2.1 | 1.9 | 2.2 | 0.8 | 0.6 | 2.4 | 2 | 1.6 | 0.6 | 1.3 | 2.1 | 2.4 | 2 | 1.1 | 2.1 | 32 |  |  |  |  |  |  |
| Capacidad efectiva (h-p) |  |  |  |  |  | 2.34 | 2.2 | 2.41 | 2.34 | 2.2 | 2.41 | 0.84 | 0.7 | 2.41 | 2.34 | 2.2 | 1.41 | 1.34 | 2.2 | 2.41 | 2.34 | 1.2 | 2.41 | 35.72 |  |  |  |  |  |  |
| Holgura (h-p) |  |  |  |  |  | 0.04 | 0.1 | 0.01 | 0.24 | 0.3 | 0.21 | 0.04 | 0.1 | 0.01 | 0.34 | 0.6 | 0.81 | 0.04 | 0.1 | 0.01 | 0.34 | 0.1 | 0.31 | 3.72 |  |  |  |  |  |  |
| Estado |  |  |  |  |  | AJUSTADO | AJUSTADO | AJUSTADO | OK | OK | OK | AJUSTADO | AJUSTADO | AJUSTADO | OK | OK | OK | AJUSTADO | AJUSTADO | AJUSTADO | OK | AJUSTADO | OK |  |  |  |  |  |  |  |

### Comprobaciones globales

| Comprobación | Valor | Criterio |
|---|---|---|
| Celdas persona × iteración en SOBRECARGA | 0 | Debe ser 0 (no se puede sobreasignar) |
| Trabajo de la línea base sin asignar (h-p) | 0 | Debe ser 0 |
| Historias que no cumplen la regla de tamaño | 0 | Debe ser 0 |
| Historias que usan la excepción DEC-018 | 3 | Justificar en el Plan inicial de su spec.md |
| Historias con predecesoras mal ordenadas | 0 | Debe ser 0 |
| Margen total del proyecto (h-p) | 3.72 | Capacidad efectiva − carga asignada |
| Ocupación de la capacidad efectiva | 0.9 | Línea base / capacidad |
| Veredicto | PLAN VIABLE: sin sobrecargas, todo asignado y dependencias respetadas |  |
| Celdas persona × iteración AJUSTADAS (holgura < 0,1 h-p) | 10 | No es sobrecarga. Regla de contingencia en DEC-020: el SM las revisa con valores sin redondear en cada Sprint Planning (IPR-v01-002) |

## Hoja Tareas IT-001

Las dependencias técnicas TD-01…TD-04 son tareas de la primera historia que las necesita y están dentro de su estimación de Planning Poker (DEC-020). El TL confirmó el 14/10/2026 que TD-01 cabe en las 1,6 h de TK-001 y TK-002. Las demás iteraciones se detallan a nivel de tarea en su Sprint Planning.

| Tarea | Historia | Descripción | Criterios / NFR / TD | Responsable | h-p | Coste (€) | Orden |
|---|---|---|---|---|---|---|---|
| TK-001 | US-001 | Preparar el entorno base y el esqueleto del proyecto con las tecnologías aprobadas | TD-01 · EXT-01 | Gled | 1 | 40 | 1 |
| TK-002 | US-001 | Modelo de usuario con estados No verificada / Verificada y contraseña con hash | TD-01 · NFR-001 · AC-04 | Gled | 0.6 | 24 | 2 |
| TK-003 | US-001 | Validación en servidor: formato, dominio institucional configurable, normalización y duplicados | AC-02 · AC-03 · NFR-007 | Gled | 0.5 | 20 | 3 |
| TK-004 | US-001 | Formulario de registro accesible con mensajes de error | AC-01 · AC-05 · AC-06 · NFR-004 | Hugo | 1.5 | 52.5 | 3 |
| TK-005 | US-001 | Pruebas de aceptación de US-001 con cuentas sintéticas (ejemplos y casos límite) | AC-01…AC-06 · NFR-009 | José | 1.4 | 44.8 | 4 |
| TK-006 | US-002 | Enlace de verificación único y caducidad de 24 h, simulado en la base de datos | AC-01 · AC-03 · RN-03 | José | 1 | 32 | 5 |
| TK-007 | US-002 | Pantalla de cuenta pendiente de verificar y bloqueo de publicar/contactar | AC-04 · RN-02 · NFR-004 | Hugo | 0.8 | 28 | 5 |

Total IT-001: 6.8 h-p · 241.3 €. Cuadre con Planificación: OK, OK, OK, OK, OK, OK.


## Hoja Carga (por persona/rol y equipo)

| Iteración | Release | Hugo (PO) asignado | Hugo (PO) capacidad | Hugo (PO) holgura | Gled (TL) asignado | Gled (TL) capacidad | Gled (TL) holgura | José (SM) asignado | José (SM) capacidad | José (SM) holgura | Equipo asignado | Equipo capacidad | Equipo holgura | % ocupación | Asignado acumulado | Capacidad acumulada | Estado |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| IT-001 | R1 | 2.3 | 2.34 | 0.04 | 2.1 | 2.2 | 0.1 | 2.4 | 2.41 | 0.01 | 6.8 | 6.95 | 0.15 | 0.98 | 6.8 | 6.95 | AJUSTADO |
| IT-002 | R1 | 2.1 | 2.34 | 0.24 | 1.9 | 2.2 | 0.3 | 2.2 | 2.41 | 0.21 | 6.2 | 6.95 | 0.75 | 0.89 | 13 | 13.91 | OK |
| IT-003 | R2 | 0.8 | 0.84 | 0.04 | 0.6 | 0.7 | 0.1 | 2.4 | 2.41 | 0.01 | 3.8 | 3.95 | 0.15 | 0.96 | 16.8 | 17.86 | AJUSTADO |
| IT-004 | R2 | 2 | 2.34 | 0.34 | 1.6 | 2.2 | 0.6 | 0.6 | 1.41 | 0.81 | 4.2 | 5.95 | 1.75 | 0.71 | 21 | 23.81 | OK |
| IT-005 | R3 | 1.3 | 1.34 | 0.04 | 2.1 | 2.2 | 0.1 | 2.4 | 2.41 | 0.01 | 5.8 | 5.95 | 0.15 | 0.97 | 26.8 | 29.77 | AJUSTADO |
| IT-006 | R3 | 2 | 2.34 | 0.34 | 1.1 | 1.2 | 0.1 | 2.1 | 2.41 | 0.31 | 5.2 | 5.95 | 0.75 | 0.87 | 32 | 35.72 | AJUSTADO |
| TOTAL |  | 10.5 | 11.56 | 1.06 | 9.4 | 10.69 | 1.28 | 12.1 | 13.47 | 1.37 | 32 | 35.72 | 3.72 | 0.9 |  |  | VIABLE |

### Holgura mínima por persona

| Miembro | Rol | Asignado (h-p) | Capacidad (h-p) | % ocupación | Holgura total (h-p) | Holgura mínima en una iteración (h-p) |
|---|---|---|---|---|---|---|
| Hugo | PO | 10.5 | 11.56 | 91% | 1.06 | 0.044 |
| Gled | TL | 9.4 | 10.69 | 88% | 1.28 | 0.097 |
| José | SM | 12.1 | 13.47 | 90% | 1.37 | 0.012 |

## Hoja Gestión (horas nominales por iteración)

| Iteración | Nominal (h) | Gestión (h) | Reserva de imprevistos (h) | Ausencias (h) | Capacidad efectiva (h-p) | Trabajo asignado (h-p) | Holgura (h-p) | % gestión | % trabajo asignado | % reserva + ausencias + holgura | Cuadre |
|---|---|---|---|---|---|---|---|---|---|---|---|
| IT-001 | 15 | 6.46 | 1.59 | 0 | 6.95 | 6.8 | 0.15 | 0.43 | 0.45 | 0.12 | OK |
| IT-002 | 15 | 6.46 | 1.59 | 0 | 6.95 | 6.2 | 0.75 | 0.43 | 0.41 | 0.16 | OK |
| IT-003 | 15 | 6.46 | 1.59 | 3 | 3.95 | 3.8 | 0.15 | 0.43 | 0.25 | 0.32 | OK |
| IT-004 | 15 | 6.46 | 1.59 | 1 | 5.95 | 4.2 | 1.75 | 0.43 | 0.28 | 0.29 | OK |
| IT-005 | 15 | 6.46 | 1.59 | 1 | 5.95 | 5.8 | 0.15 | 0.43 | 0.39 | 0.18 | OK |
| IT-006 | 15 | 6.46 | 1.59 | 1 | 5.95 | 5.2 | 0.75 | 0.43 | 0.35 | 0.22 | OK |
| TOTAL | 90 | 38.76 | 9.52 | 6 | 35.72 | 32 | 3.72 | 0.43 | 0.36 | 0.21 |  |

## Hoja Coste

### Tarifas por rol

| Rol | Tarifa (€/h-p) | Justificación |
|---|---|---|
| PO | 35 | Acordada por el equipo el 14/10/2026. Nivel intermedio: gestiona el valor y el backlog y acepta las historias. |
| TL | 40 | Acordada por el equipo el 14/10/2026. La más alta: responsabilidad técnica, decisiones de arquitectura y revisión de código. |
| SM | 32 | Acordada por el equipo el 14/10/2026. La más baja: facilitación del proceso, sin responsabilidad técnica ni de producto. |

### Por persona

| Miembro | Rol | Tarifa (€/h-p) | h-p de desarrollo asignadas | Coste de desarrollo (€) | h de gestión (6 IT) | Coste de gestión (€) | Coste total (€) |
|---|---|---|---|---|---|---|---|
| Hugo | PO | 35 | 10.5 | 367.5 | 12.42 | 434.7 | 802.2 |
| Gled | TL | 40 | 9.4 | 376 | 12.42 | 496.8 | 872.8 |
| José | SM | 32 | 12.1 | 387.2 | 13.92 | 445.44 | 832.64 |
| EQUIPO |  |  | 32 | 1130.7 | 38.76 | 1376.94 | 2507.64 |

### Por iteración

| Iteración | Release | h-p desarrollo | Coste de desarrollo (€) | Coste de gestión (€) | Coste total (€) | Coste acumulado (€) |
|---|---|---|---|---|---|---|
| IT-001 | R1 | 6.8 | 241.3 | 229.49 | 470.79 | 470.79 |
| IT-002 | R1 | 6.2 | 219.9 | 229.49 | 449.39 | 920.18 |
| IT-003 | R2 | 3.8 | 128.8 | 229.49 | 358.29 | 1278.47 |
| IT-004 | R2 | 4.2 | 153.2 | 229.49 | 382.69 | 1661.16 |
| IT-005 | R3 | 5.8 | 206.3 | 229.49 | 435.79 | 2096.95 |
| IT-006 | R3 | 5.2 | 181.2 | 229.49 | 410.69 | 2507.64 |
| TOTAL |  | 32 | 1130.7 | 1376.94 | 2507.64 |  |

### Por historia

| Historia | Título | Release | h-p | Coste de desarrollo (€) |
|---|---|---|---|---|
| US-001 | Registrar cuenta con correo institucional | R1 | 5 | 181.3 |
| US-002 | Verificar la cuenta mediante el correo institucional | R1 | 5 | 177 |
| US-003 | Iniciar y cerrar sesión | R1 | 3 | 102.9 |
| US-006 | Publicar el anuncio de mi habitación | R2 | 5 | 174.8 |
| US-012 | Listar los anuncios disponibles | R2 | 3 | 107.2 |
| US-015 | Ver el detalle de un anuncio con su estado | R3 | 3 | 103.8 |
| US-017 | Contactar con el oferente desde un anuncio | R3 | 5 | 181.7 |
| US-023 | Guion de instalación y evaluación | R3 | 3 | 102 |
| TOTAL |  |  | 32 | 1130.7 |

### Total

| Concepto | Importe (€) |
|---|---|
| Coste de desarrollo (línea base, 32 h-p) | 1130.7 |
| Coste de gestión (ceremonias y coordinación) | 1376.94 |
| COSTE TOTAL PLANIFICADO | 2507.64 |
| Coste por tareas de IT-001 (comprobación) | 241.3 |
| Cota superior: dedicación nominal (5 h/sem × 3 × 6) × tarifa | 3210 |
| Comprobación: suma por iteración = suma por persona | OK |
| Presupuesto del cliente | No declarado (proyecto académico); tope de referencia = cota superior |
| Margen frente al tope de referencia (€) | 702.36 |

Nota: el coste es una proyección de la planificación, no coste incurrido (IPR-v01, apartado 8).

## Hoja Resumen

| MoSCoW | Nº historias | h-p vigentes | % del planificado |
|---|---|---|---|
| Must | 8 | 32 | 1 |
| Should | 10 | 37 | fuera de la línea base |
| Could | 4 | 10 | fuera de la línea base |
| Won't | 2 | 13 | no planificado |
| Línea base planificada (Must, DEC-016) | 8 | 32 |  |

| Release | Iteraciones | Carga en la línea base (h-p) | Capacidad efectiva equipo (h-p) | Holgura (h-p) | % ocupación |
|---|---|---|---|---|---|
| R1 | IT-001 + IT-002 | 13 | 13.91 | 0.91 | 0.93 |
| R2 | IT-003 + IT-004 | 8 | 9.91 | 1.91 | 0.81 |
| R3 | IT-005 + IT-006 | 11 | 11.91 | 0.91 | 0.92 |
| TOTAL |  | 32 | 35.72 | 3.72 | 0.9 |

Diagnóstico: La línea base (Must, DEC-016) cabe en la capacidad de cada release. Planificación por persona: VIABLE · sobrecargas: 0 · sin asignar: 0.0 h-p.


## Hoja Dependencias

| Desde (predecesora) | Hacia (sucesora) | Tipo | Descripción |
|---|---|---|---|
| US-001 | US-002 | Funcional | US-002 necesita US-001 terminada |
| US-001 | US-003 | Funcional | US-003 necesita US-001 terminada |
| US-003 | US-004 | Funcional | US-004 necesita US-003 terminada |
| US-002 | US-005 | Funcional | US-005 necesita US-002 terminada |
| US-003 | US-005 | Funcional | US-005 necesita US-003 terminada |
| US-002 | US-006 | Funcional | US-006 necesita US-002 terminada |
| US-003 | US-006 | Funcional | US-006 necesita US-003 terminada |
| US-006 | US-007 | Funcional | US-007 necesita US-006 terminada |
| US-006 | US-008 | Funcional | US-008 necesita US-006 terminada |
| US-006 | US-009 | Funcional | US-009 necesita US-006 terminada |
| US-006 | US-010 | Funcional | US-010 necesita US-006 terminada |
| US-009 | US-011 | Funcional | US-011 necesita US-009 terminada |
| US-006 | US-012 | Funcional | US-012 necesita US-006 terminada |
| US-012 | US-013 | Funcional | US-013 necesita US-012 terminada |
| US-012 | US-014 | Funcional | US-014 necesita US-012 terminada |
| US-012 | US-015 | Funcional | US-015 necesita US-012 terminada |
| US-012 | US-016 | Funcional | US-016 necesita US-012 terminada |
| US-013 | US-016 | Funcional | US-016 necesita US-013 terminada |
| US-015 | US-017 | Funcional | US-017 necesita US-015 terminada |
| US-002 | US-017 | Funcional | US-017 necesita US-002 terminada |
| US-017 | US-018 | Funcional | US-018 necesita US-017 terminada |
| US-018 | US-019 | Funcional | US-019 necesita US-018 terminada |
| US-015 | US-020 | Funcional | US-020 necesita US-015 terminada |
| US-003 | US-020 | Funcional | US-020 necesita US-003 terminada |
| US-018 | US-021 | Funcional | US-021 necesita US-018 terminada |
| US-001 | US-022 | Funcional | US-022 necesita US-001 terminada |
| US-006 | US-022 | Funcional | US-022 necesita US-006 terminada |
| US-017 | US-023 | Funcional | US-023 necesita US-017 terminada: el guion se valida sobre el incremento completo (DEC-016; antes dependía de US-022) |
| US-015 | US-024 | Funcional | US-024 necesita US-015 terminada |
| US-018 | US-024 | Funcional | US-024 necesita US-018 terminada |
| TD-01 Entorno base y modelo de usuario | US-001 | Técnica | Preparado por el TL en IT-001 |
| TD-02 Envío de correo | US-002 | Técnica | Componente de envío; reutilizado por US-005 |
| TD-02 Envío de correo | US-005 | Técnica | Reutiliza el componente de US-002 |
| TD-03 Modelo de anuncio y zonas | US-006 | Técnica | Compartido con US-014 |
| TD-03 Modelo de anuncio y zonas | US-014 | Técnica | Lista de zonas compartida |
| TD-04 Modelo de conversación | US-017 | Técnica | Compartido con US-018: diseñar ambas a la vez |
| TD-04 Modelo de conversación | US-018 | Técnica | Compartido con US-017 |
| TD-05 Almacenamiento de ficheros | US-007 | Técnica | Fotos del anuncio |
| TD-06 Textos externalizados (i18n) | US-024 | Técnica | No se planifica: US-024 es Won't (DEC-016) |
| EXT-01 Tecnologías aprobadas en la asignatura | US-001 | Externa | SU-07: condiciona todo el desarrollo |
| EXT-02 Buzón de correo de pruebas | US-002 | Externa | No se envían correos reales |
| EXT-03 Evaluador externo | US-023 | Externa | SU-06: necesario para M-04 |
| EXT-04 Criterio de moderación de la Delegación | US-021 | Externa | Won't: no planificada |

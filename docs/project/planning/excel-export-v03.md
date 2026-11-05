# Exportación en texto del Excel de planificación v03

Fuente: `docs/ErasmusNest_GC04_Requisitos_Capacidad.xlsx`. Generada automáticamente a partir de los valores calculados del libro para que los agentes puedan leerlos (el `.xlsx` es binario). Unidades: h-p = horas-persona; capacidades en h-p por persona e iteración (1 iteración = 1 semana, de miércoles a martes). Los números se muestran con 2 decimales; el Excel calcula con todos. Porcentajes redondeados a la unidad.

**Cambios respecto a la v02** (tras la evaluación del Trabajo 1, DEC-025):

- **Capacidad con horas reales:** Hugo 7, Gled 7 y José 9 h/semana (antes 5 cada uno): 2 h de la sesión de prácticas de GPS, que se dedica al proyecto, más las horas de trabajo autónomo. La coordinación del SM baja de 0,75 a 0,5 h/semana.
- **Ceremonias en su día, sin prorratear:** Sprint Planning + refinamiento cada miércoles 16:00–16:45; review + retro (50 min) el martes 17:00–17:50 de IT-002, IT-004 e IT-006, y solo en esas iteraciones (antes se repartían 25 min por semana). Por eso la capacidad es menor en las iteraciones pares.
- **Línea base Must + Should:** 18 historias (69 h-p), 3 por iteración, en lugar de las 8 Must (32 h-p) de DEC-016. Ninguna historia ocupa dos iteraciones, así que ya no se usa la excepción de DEC-018. US-006 se adelanta a R1 (IT-002) y US-015 a R2 (IT-004).
- **Columnas Inicio y Fin** en Planificación: cada historia tiene sus fechas dentro de la iteración y no dura el sprint entero. Nuevas comprobaciones: predecesoras ordenadas por fechas y fechas dentro de su iteración (en las iteraciones con review, la historia termina como tarde el lunes).
- **Coste con reserva de imprevistos:** fila nueva «Reserva de imprevistos» (horas de la reserva × tarifa de cada rol) y coste total con reserva.
- **Tareas IT-001** ampliadas a TK-001…TK-013 (US-001, US-002 y US-003).

## Hoja Requisitos (columnas de planificación)

| ID | Épica | Título | MoSCoW | Prioridad (0-10) | Incertidumbre | Predecesoras | Iteración (de la hoja Planificación) | Release | Est. Hugo (h-p) | Est. Gled (h-p) | Est. José (h-p) | Est. consensuada (h-p) | Est. vigente (h-p) | Discrepancia |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| US-001 | EP-001 | Registrar cuenta con correo institucional | Must | 10 | Baja | — | IT-001 | R1 | 8 | 5 | 5 | 5 | 5 | OK |
| US-002 | EP-001 | Verificar la cuenta mediante el correo institucional | Must | 9 | Media | US-001 | IT-001 | R1 | 5 | 5 | 8 | 5 | 5 | OK |
| US-003 | EP-001 | Iniciar y cerrar sesión | Must | 10 | Baja | US-001 | IT-001 | R1 | 3 | 5 | 3 | 3 | 3 | OK |
| US-004 | EP-001 | Gestionar el perfil básico | Should | 6 | Baja | US-003 | IT-002 | R1 | 3 | 3 | 3 | 3 | 3 | OK |
| US-005 | EP-001 | Recuperar la contraseña | Could | 3 | Baja | US-002, US-003 | — | — | 3 | 5 | 3 | 3 | 3 | OK |
| US-006 | EP-002 | Publicar el anuncio de mi habitación | Must | 10 | Baja | US-002, US-003 | IT-002 | R1 | 8 | 5 | 5 | 5 | 5 | OK |
| US-007 | EP-002 | Añadir fotos al anuncio | Should | 6 | Media | US-006 | IT-003 | R2 | 3 | 5 | 5 | 5 | 5 | OK |
| US-008 | EP-002 | Editar mi anuncio | Should | 5 | Baja | US-006 | IT-005 | R3 | 3 | 3 | 3 | 3 | 3 | OK |
| US-009 | EP-002 | Cerrar mi anuncio | Should | 7 | Baja | US-006 | IT-002 | R1 | 2 | 3 | 3 | 3 | 3 | OK |
| US-010 | EP-002 | Ver mis anuncios y su estado | Should | 6 | Baja | US-006 | IT-003 | R2 | 2 | 3 | 3 | 3 | 3 | OK |
| US-011 | EP-002 | Cierre automático de anuncios caducados | Could | 2 | Baja | US-009 | — | — | 2 | 3 | 2 | 2 | 2 | OK |
| US-012 | EP-003 | Listar los anuncios disponibles | Must | 9 | Baja | US-006 | IT-003 | R2 | 3 | 5 | 3 | 3 | 3 | OK |
| US-013 | EP-003 | Filtrar anuncios por fechas de estancia | Should | 6 | Media | US-012 | IT-004 | R2 | 5 | 8 | 5 | 5 | 5 | OK |
| US-014 | EP-003 | Filtrar anuncios por zona | Should | 5 | Baja | US-012 | IT-004 | R2 | 2 | 3 | 3 | 3 | 3 | OK |
| US-015 | EP-003 | Ver el detalle de un anuncio con su estado | Must | 9 | Baja | US-012 | IT-004 | R2 | 3 | 3 | 3 | 3 | 3 | OK |
| US-016 | EP-003 | Ordenar los resultados | Could | 2 | Baja | US-012, US-013 | — | — | 2 | 2 | 3 | 2 | 2 | OK |
| US-017 | EP-004 | Contactar con el oferente desde un anuncio | Must | 10 | Media | US-015, US-002 | IT-005 | R3 | 5 | 5 | 5 | 5 | 5 | OK |
| US-018 | EP-004 | Mantener una conversación | Should | 7 | Media | US-017 | IT-006 | R3 | 5 | 5 | 8 | 5 | 5 | OK |
| US-019 | EP-004 | Aviso de mensajes nuevos | Should | 5 | Baja | US-018 | IT-006 | R3 | 2 | 3 | 2 | 2 | 2 | OK |
| US-020 | EP-004 | Reportar un anuncio sospechoso | Could | 3 | Baja | US-015, US-003 | — | — | 2 | 3 | 3 | 3 | 3 | OK |
| US-021 | EP-004 | Valorar a la otra parte tras la estancia | Won't | 0 | Alta | US-018 | — | — |  |  |  |  | 8 | Pendiente |
| US-022 | EP-005 | Cargar un conjunto de datos sintéticos | Should | 7 | Baja | US-001, US-006 | IT-005 | R3 | 5 | 5 | 8 | 5 | 5 | OK |
| US-023 | EP-005 | Guion de instalación y evaluación | Must | 8 | Media | US-017 | IT-006 | R3 | 2 | 3 | 3 | 3 | 3 | OK |
| US-024 | EP-005 | Usar la interfaz en inglés | Won't | 1 | Media | US-015, US-018 | — | — | 8 | 5 | 5 | 5 | 5 | OK |

## Hoja Capacidad

### Por miembro (h/semana)

| Miembro | Rol | Horas nominales / semana | Ceremonias semanales fijas (h/sem) | Coordinación propia del rol (h/sem) | % imprevistos (individual) | Capacidad efectiva / semana sin review (sin ausencias) | Capacidad efectiva / semana con review + retro (sin ausencias) | % efectivo sobre nominal |
|---|---|---|---|---|---|---|---|---|
| Hugo | Product Owner | 7 | 1.15 | 0.5 | 20 % | 4.28 | 3.61 | 61 % |
| Gled | Tech Lead | 7 | 1.15 | 0.5 | 25 % | 4.01 | 3.39 | 57 % |
| José | Scrum Master | 9 | 1.15 | 0.5 | 10 % | 6.62 | 5.87 | 74 % |
| EQUIPO |  | 23 |  |  |  | 14.91 | 12.87 | 65 % |

Justificación de las horas, la coordinación y el % de imprevistos (columna G):

- **Hugo:** Revisado tras la evaluación (v03): 7 h/sem = 2 h de la sesión de prácticas de GPS, que se dedica al proyecto, + 5 h de trabajo autónomo (la v02 solo contaba estas 5 h). Tiene otras 5 asignaturas, por eso no se sube más. Coordinación PO 0,5 h/sem: refinamiento del backlog y criterios de aceptación. Imprevistos 20 %: entregas cortas de otras asignaturas sin fecha fija.
- **Gled:** Revisado tras la evaluación (v03): 7 h/sem = 2 h de la sesión de prácticas + 5 h autónomas, igual que Hugo (también 5 asignaturas más). Coordinación TL 0,5 h/sem: revisión de PR y descomposición en tareas. Imprevistos 25 %, el más alto del equipo: la incertidumbre técnica recae sobre el TL.
- **José:** Revisado tras la evaluación (v03): 9 h/sem = 2 h de la sesión de prácticas + 7 h autónomas; solo cursa 2 asignaturas más y puede dedicar más. Coordinación SM recortada de 0,75 a 0,5 h/sem: con las ceremonias en día y hora fijos ya no hay que convocarlas cada semana; queda seguir los impedimentos. Imprevistos 10 %.

### Ceremonias Scrum por persona

| Ceremonia | h por persona | Cuándo |
|---|---|---|
| Ceremonias semanales fijas (todas las iteraciones) | 1.15 |  |
| Sprint Planning (miércoles 16:00–16:30) | 0.5 | Todos los miércoles por Discord; abre la iteración (team charter, acuerdo 4) |
| Daily asíncrona (WhatsApp) | 0.4 | ≈5 min × 5 días (team charter, acuerdo 5) |
| Refinamiento (miércoles 16:30–16:45) | 0.25 | 15 min a continuación de la Sprint Planning (acuerdo 4) |
| Review + Retrospectiva (martes 17:00–17:50) | 0.83 | Solo el martes de IT-002, IT-004 e IT-006 (fin de cada release): review 25 min + retro 25 min (acuerdo 6). Se cuenta entera en esas tres iteraciones (fila 23), no repartida por semanas |

### Ausencias, review + retro y capacidad efectiva por iteración (h-p)

| Miembro | Ausencias IT-001 (h) | Ausencias IT-002 (h) | Ausencias IT-003 (h) | Ausencias IT-004 (h) | Ausencias IT-005 (h) | Ausencias IT-006 (h) | Efectiva IT-001 | Efectiva IT-002 | Efectiva IT-003 | Efectiva IT-004 | Efectiva IT-005 | Efectiva IT-006 | Total efectiva |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Hugo | 0 | 0 | 1.5 | 0 | 1 | 0 | 4.28 | 3.61 | 2.78 | 3.61 | 3.28 | 3.61 | 21.18 |
| Gled | 0 | 0 | 1.5 | 0 | 0 | 1 | 4.01 | 3.39 | 2.51 | 3.39 | 4.01 | 2.39 | 19.7 |
| José | 0 | 0 | 0 | 1 | 0 | 0 | 6.62 | 5.87 | 6.62 | 4.87 | 6.62 | 5.87 | 36.44 |
| EQUIPO |  |  |  |  |  |  | 14.91 | 12.87 | 11.91 | 11.87 | 13.91 | 11.87 | 77.32 |
| Review + retro en la iteración (h/persona) |  |  |  |  |  |  | 0 | 0.83 | 0 | 0.83 | 0 | 0.83 | 2.5 |

### Fechas de las iteraciones y de las ceremonias

| Iteración | Inicio (miércoles) | Fin (martes) | Release | Sprint Planning + refinamiento (16:00–16:45) | Review + retro (17:00–17:50) |
|---|---|---|---|---|---|
| IT-001 | 21/10/2026 | 27/10/2026 | R1 | 21/10/2026 | — |
| IT-002 | 28/10/2026 | 03/11/2026 | R1 | 28/10/2026 | 03/11/2026 |
| IT-003 | 04/11/2026 | 10/11/2026 | R2 | 04/11/2026 | — |
| IT-004 | 11/11/2026 | 17/11/2026 | R2 | 11/11/2026 | 17/11/2026 |
| IT-005 | 18/11/2026 | 24/11/2026 | R3 | 18/11/2026 | — |
| IT-006 | 25/11/2026 | 01/12/2026 | R3 | 25/11/2026 | 01/12/2026 |

Ausencias: cada miembro ha declarado sus parciales, entregas y viajes con fecha entre el 21/10 y el 01/12 y se descuentan en horas en su iteración (Hugo: parcial 05/11, 1,5 h, y entrega 20/11, 1 h; Gled: parcial 06/11, 1,5 h, y viaje 28/11, 1 h; José: parcial 12/11, 1 h). Para no contarlas dos veces, el % de imprevistos solo recoge lo que no se puede fechar.

## Hoja Planificación (h-p por historia, iteración y persona)

Revisada tras la evaluación del Trabajo 1 (DEC-025): horas reales del equipo, 18 historias Must y Should (3 por iteración) y ninguna historia ocupa dos iteraciones. Celdas amarillas: horas de cada persona y fechas de inicio y fin (las historias no duran el sprint entero). Filas 23–26: carga frente a la capacidad efectiva de cada persona. Granularidad: 0,1 h. En las iteraciones con review + retro (martes 17:00) la historia termina como tarde el lunes. Columnas `IT-00n Hugo/Gled/José` = horas asignadas a cada persona en esa iteración.

| ID | Título | Épica | MoSCoW | Release | Responsable | Predecesoras | Est. (h-p) | Inicio | Fin | IT-001 Hugo | IT-001 Gled | IT-001 José | IT-002 Hugo | IT-002 Gled | IT-002 José | IT-003 Hugo | IT-003 Gled | IT-003 José | IT-004 Hugo | IT-004 Gled | IT-004 José | IT-005 Hugo | IT-005 Gled | IT-005 José | IT-006 Hugo | IT-006 Gled | IT-006 José | Total asignado | Sin asignar | Iteraciones | Capacidad del tramo (h-p) | Regla de tamaño (DoR / DEC-018) | Orden de predecesoras (fechas) | Fechas dentro de su iteración | Coste (€) |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| US-001 | Registrar cuenta con correo institucional | EP-001 | Must | R1 | Gled | — | 5 | 21/10/2026 | 23/10/2026 | 1.5 | 2.1 | 1.4 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 5 | 0 | IT-001 | 14.91 | OK (1 iteración) | Sin predecesoras | OK | 181.3 |
| US-002 | Verificar la cuenta mediante el correo institucional | EP-001 | Must | R1 | Gled | US-001 | 5 | 24/10/2026 | 27/10/2026 | 1.2 | 1.5 | 2.3 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 5 | 0 | IT-001 | 14.91 | OK (1 iteración) | OK | OK | 175.6 |
| US-003 | Iniciar y cerrar sesión | EP-001 | Must | R1 | Hugo | US-001 | 3 | 24/10/2026 | 27/10/2026 | 1.1 |  | 1.9 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 3 | 0 | IT-001 | 14.91 | OK (1 iteración) | OK | OK | 99.3 |
| US-004 | Gestionar el perfil básico | EP-001 | Should | R1 | Hugo | US-003 | 3 | 28/10/2026 | 30/10/2026 |  |  |  | 1 | 0.4 | 1.6 |  |  |  |  |  |  |  |  |  |  |  |  | 3 | 0 | IT-002 | 12.87 | OK (1 iteración) | OK | OK | 102.2 |
| US-006 | Publicar el anuncio de mi habitación | EP-002 | Must | R1 | José | US-002, US-003 | 5 | 28/10/2026 | 31/10/2026 |  |  |  | 1.2 | 1.8 | 2 |  |  |  |  |  |  |  |  |  |  |  |  | 5 | 0 | IT-002 | 12.87 | OK (1 iteración) | OK | OK | 178 |
| US-009 | Cerrar mi anuncio | EP-002 | Should | R1 | José | US-006 | 3 | 01/11/2026 | 02/11/2026 |  |  |  | 1 | 0.8 | 1.2 |  |  |  |  |  |  |  |  |  |  |  |  | 3 | 0 | IT-002 | 12.87 | OK (1 iteración) | OK | OK | 105.4 |
| US-012 | Listar los anuncios disponibles | EP-003 | Must | R2 | Hugo | US-006 | 3 | 04/11/2026 | 06/11/2026 |  |  |  |  |  |  | 0.9 | 0.6 | 1.5 |  |  |  |  |  |  |  |  |  | 3 | 0 | IT-003 | 11.91 | OK (1 iteración) | OK | OK | 103.5 |
| US-007 | Añadir fotos al anuncio | EP-002 | Should | R2 | Gled | US-006 | 5 | 04/11/2026 | 08/11/2026 |  |  |  |  |  |  | 0.7 | 1.4 | 2.9 |  |  |  |  |  |  |  |  |  | 5 | 0 | IT-003 | 11.91 | OK (1 iteración) | OK | OK | 173.3 |
| US-010 | Ver mis anuncios y su estado | EP-002 | Should | R2 | José | US-006 | 3 | 07/11/2026 | 10/11/2026 |  |  |  |  |  |  | 0.8 | 0.2 | 2 |  |  |  |  |  |  |  |  |  | 3 | 0 | IT-003 | 11.91 | OK (1 iteración) | OK | OK | 100 |
| US-013 | Filtrar anuncios por fechas de estancia | EP-003 | Should | R2 | Gled | US-012 | 5 | 11/11/2026 | 14/11/2026 |  |  |  |  |  |  |  |  |  | 1.3 | 1.6 | 2.1 |  |  |  |  |  |  | 5 | 0 | IT-004 | 11.87 | OK (1 iteración) | OK | OK | 176.7 |
| US-014 | Filtrar anuncios por zona | EP-003 | Should | R2 | Hugo | US-012 | 3 | 11/11/2026 | 13/11/2026 |  |  |  |  |  |  |  |  |  | 1 | 0.6 | 1.4 |  |  |  |  |  |  | 3 | 0 | IT-004 | 11.87 | OK (1 iteración) | OK | OK | 103.8 |
| US-015 | Ver el detalle de un anuncio con su estado | EP-003 | Must | R2 | José | US-012 | 3 | 14/11/2026 | 16/11/2026 |  |  |  |  |  |  |  |  |  | 1 | 0.9 | 1.1 |  |  |  |  |  |  | 3 | 0 | IT-004 | 11.87 | OK (1 iteración) | OK | OK | 106.2 |
| US-017 | Contactar con el oferente desde un anuncio | EP-004 | Must | R3 | Gled | US-015, US-002 | 5 | 18/11/2026 | 21/11/2026 |  |  |  |  |  |  |  |  |  |  |  |  | 0.9 | 2 | 2.1 |  |  |  | 5 | 0 | IT-005 | 13.91 | OK (1 iteración) | OK | OK | 178.7 |
| US-008 | Editar mi anuncio | EP-002 | Should | R3 | Hugo | US-006 | 3 | 18/11/2026 | 20/11/2026 |  |  |  |  |  |  |  |  |  |  |  |  | 1 | 0.4 | 1.6 |  |  |  | 3 | 0 | IT-005 | 13.91 | OK (1 iteración) | OK | OK | 102.2 |
| US-022 | Cargar un conjunto de datos sintéticos | EP-005 | Should | R3 | José | US-001, US-006 | 5 | 21/11/2026 | 24/11/2026 |  |  |  |  |  |  |  |  |  |  |  |  | 1 | 1.3 | 2.7 |  |  |  | 5 | 0 | IT-005 | 13.91 | OK (1 iteración) | OK | OK | 173.4 |
| US-018 | Mantener una conversación | EP-004 | Should | R3 | Gled | US-017 | 5 | 25/11/2026 | 27/11/2026 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 1 | 1.4 | 2.6 | 5 | 0 | IT-006 | 11.87 | OK (1 iteración) | OK | OK | 174.2 |
| US-019 | Aviso de mensajes nuevos | EP-004 | Should | R3 | José | US-018 | 2 | 28/11/2026 | 30/11/2026 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 0.5 | 0.6 | 0.9 | 2 | 0 | IT-006 | 11.87 | OK (1 iteración) | OK | OK | 70.3 |
| US-023 | Guion de instalación y evaluación | EP-005 | Must | R3 | Hugo | US-017 | 3 | 26/11/2026 | 30/11/2026 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 1.7 |  | 1.3 | 3 | 0 | IT-006 | 11.87 | OK (1 iteración) | OK | OK | 101.1 |
| Total asignado (h-p) |  |  |  |  |  |  |  |  |  | 3.8 | 3.6 | 5.6 | 3.2 | 3 | 4.8 | 2.4 | 2.2 | 6.4 | 3.3 | 3.1 | 4.6 | 2.9 | 3.7 | 6.4 | 3.2 | 2 | 4.8 | 69 | 0 |  |  |  |  |  | 2405.2 |
| Capacidad efectiva (h-p) |  |  |  |  |  |  |  |  |  | 4.28 | 4.01 | 6.62 | 3.61 | 3.39 | 5.87 | 2.78 | 2.51 | 6.62 | 3.61 | 3.39 | 4.87 | 3.28 | 4.01 | 6.62 | 3.61 | 2.39 | 5.87 | 77.32 |  |  |  |  |  |  |  |
| Holgura (h-p) |  |  |  |  |  |  |  |  |  | 0.48 | 0.41 | 1.01 | 0.41 | 0.39 | 1.06 | 0.38 | 0.31 | 0.21 | 0.31 | 0.29 | 0.27 | 0.38 | 0.31 | 0.21 | 0.41 | 0.39 | 1.06 | 8.32 |  |  |  |  |  |  |  |
| Estado |  |  |  |  |  |  |  |  |  | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK | OK |  |  |  |  |  |  |  |  |

### Comprobaciones globales

| Comprobación | Valor | Criterio |
|---|---|---|
| Celdas persona × iteración en SOBRECARGA | 0 | Debe ser 0 (no se puede sobreasignar) |
| Trabajo de la línea base sin asignar (h-p) | 0 | Debe ser 0 |
| Historias que no cumplen la regla de tamaño | 0 | Debe ser 0 |
| Historias que usan la excepción DEC-018 | 0 | Con la capacidad real ya no hace falta: ninguna historia ocupa dos iteraciones |
| Historias con predecesoras mal ordenadas (por fechas) | 0 | Debe ser 0: cada historia empieza el día siguiente al fin de sus predecesoras o después |
| Historias con fechas fuera de su iteración | 0 | Debe ser 0: inicio y fin dentro de su iteración; con review + retro, fin como tarde el lunes |
| Margen total del proyecto (h-p) | 8.32 | Capacidad efectiva − carga asignada |
| Ocupación de la capacidad efectiva | 0.89 | Línea base / capacidad |
| Historias por iteración (mín. – máx.) | 3 – 3 | Objetivo tras la evaluación: 3-4 historias Must/Should por iteración |
| Veredicto | PLAN VIABLE: sin sobrecargas, todo asignado, dependencias y fechas respetadas |  |
| Celdas persona × iteración AJUSTADAS (holgura < 0,1 h-p) | 0 | No es sobrecarga; el SM las revisa en cada Sprint Planning (DEC-020) |

## Hoja Tareas IT-001

Las dependencias técnicas TD-01 y TD-02 son tareas de la primera historia que las necesita y están dentro de su estimación de Planning Poker (DEC-020). Con la capacidad real, US-002 ya no se reparte entre dos iteraciones y US-003 se adelanta a IT-001. Las demás iteraciones se detallan a nivel de tarea en su Sprint Planning.

| Tarea | Historia | Descripción | Criterios / NFR / TD | Responsable | h-p | Coste (€) | Orden |
|---|---|---|---|---|---|---|---|
| TK-001 | US-001 | Preparar el entorno base y el esqueleto del proyecto con las tecnologías aprobadas | TD-01 · EXT-01 | Gled | 1 | 40 | 1 |
| TK-002 | US-001 | Modelo de usuario con estados No verificada / Verificada y contraseña con hash | TD-01 · NFR-001 · AC-04 | Gled | 0.6 | 24 | 2 |
| TK-003 | US-001 | Validación en servidor: formato, dominio institucional configurable, normalización y duplicados | AC-02 · AC-03 · NFR-007 | Gled | 0.5 | 20 | 3 |
| TK-004 | US-001 | Formulario de registro accesible con mensajes de error | AC-01 · AC-05 · AC-06 · NFR-004 | Hugo | 1.5 | 52.5 | 3 |
| TK-005 | US-001 | Pruebas de aceptación de US-001 con cuentas sintéticas (ejemplos y casos límite) | AC-01…AC-06 · NFR-009 | José | 1.4 | 44.8 | 4 |
| TK-006 | US-002 | Enlace de verificación único y caducidad de 24 h, simulado en la base de datos | AC-01 · AC-03 · RN-03 | José | 1 | 32 | 5 |
| TK-007 | US-002 | Pantalla de cuenta pendiente de verificar y bloqueo de publicar/contactar | AC-04 · RN-02 · NFR-004 | Hugo | 0.8 | 28 | 5 |
| TK-008 | US-002 | Componente de envío de correo al buzón de pruebas | TD-02 · EXT-02 · AC-01 | Gled | 1.5 | 60 | 5 |
| TK-009 | US-002 | Mensajes de verificación correcta, enlace caducado y botón de reenvío accesibles | AC-02 · AC-03 · NFR-004 | Hugo | 0.4 | 14 | 6 |
| TK-010 | US-002 | Pruebas de aceptación de US-002 (enlace válido, caducado, reutilizado y manipulado) | AC-01…AC-04 · NFR-009 | José | 1.3 | 41.6 | 6 |
| TK-011 | US-003 | Inicio y cierre de sesión en servidor: bloqueo tras 5 intentos y caducidad por inactividad | AC-01…AC-04 · NFR-001 | José | 1 | 32 | 5 |
| TK-012 | US-003 | Pantallas de inicio de sesión y cierre accesibles, con mensaje de error genérico | AC-01 · AC-03 · NFR-004 | Hugo | 1.1 | 38.5 | 6 |
| TK-013 | US-003 | Pruebas de aceptación de US-003 con cuentas sintéticas | AC-01…AC-04 · NFR-009 | José | 0.9 | 28.8 | 7 |

Total IT-001: 13 h-p · 456.2 €. Cuadre con Planificación: OK, OK, OK, OK, OK, OK, OK, OK, OK.

Orden: las tareas con el mismo número pueden hacerse en paralelo. US-001 (orden 1–4) termina el viernes 23/10; US-002 y US-003 (orden 5–7) van del sábado 24/10 al martes 27/10 (columnas Inicio y Fin de Planificación).

## Hoja Carga (por persona/rol y equipo)

| Iteración | Release | Hugo (PO) asignado | Hugo (PO) capacidad | Hugo (PO) holgura | Gled (TL) asignado | Gled (TL) capacidad | Gled (TL) holgura | José (SM) asignado | José (SM) capacidad | José (SM) holgura | Equipo asignado | Equipo capacidad | Equipo holgura | % ocupación | Asignado acumulado | Capacidad acumulada | Estado |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| IT-001 | R1 | 3.8 | 4.28 | 0.48 | 3.6 | 4.01 | 0.41 | 5.6 | 6.62 | 1.01 | 13 | 14.91 | 1.91 | 87 % | 13 | 14.91 | OK |
| IT-002 | R1 | 3.2 | 3.61 | 0.41 | 3 | 3.39 | 0.39 | 4.8 | 5.87 | 1.06 | 11 | 12.87 | 1.87 | 85 % | 24 | 27.77 | OK |
| IT-003 | R2 | 2.4 | 2.78 | 0.38 | 2.2 | 2.51 | 0.31 | 6.4 | 6.62 | 0.21 | 11 | 11.91 | 0.91 | 92 % | 35 | 39.68 | OK |
| IT-004 | R2 | 3.3 | 3.61 | 0.31 | 3.1 | 3.39 | 0.29 | 4.6 | 4.87 | 0.27 | 11 | 11.87 | 0.87 | 93 % | 46 | 51.55 | OK |
| IT-005 | R3 | 2.9 | 3.28 | 0.38 | 3.7 | 4.01 | 0.31 | 6.4 | 6.62 | 0.21 | 13 | 13.91 | 0.91 | 93 % | 59 | 65.45 | OK |
| IT-006 | R3 | 3.2 | 3.61 | 0.41 | 2 | 2.39 | 0.39 | 4.8 | 5.87 | 1.06 | 10 | 11.87 | 1.87 | 84 % | 69 | 77.32 | OK |
| TOTAL |  | 18.8 | 21.18 | 2.38 | 17.6 | 19.7 | 2.1 | 32.6 | 36.44 | 3.84 | 69 | 77.32 | 8.32 | 89 % |  |  | VIABLE |

### Holgura mínima por persona

| Miembro | Rol | Asignado (h-p) | Capacidad (h-p) | % ocupación | Holgura (h-p) | Holgura mínima en una iteración (h-p) |
|---|---|---|---|---|---|---|
| Hugo | PO | 18.8 | 21.18 | 89 % | 2.38 | 0.31 |
| Gled | TL | 17.6 | 19.7 | 89 % | 2.1 | 0.29 |
| José | SM | 32.6 | 36.44 | 89 % | 3.84 | 0.21 |

## Hoja Gestión (horas nominales por iteración)

Gestión = ceremonias semanales + coordinación del rol + review y retro solo en IT-002, IT-004 e IT-006 (en su iteración, sin prorratear). Reserva = % de imprevistos individual. Nominal = gestión + reserva + ausencias + capacidad efectiva.

| Iteración | Nominal (h) | Gestión (h) | Reserva de imprevistos (h) | Ausencias (h) | Capacidad efectiva (h-p) | Trabajo asignado (h-p) | Holgura (h-p) | % gestión | % trabajo asignado | % reserva + ausencias + holgura | Cuadre |
|---|---|---|---|---|---|---|---|---|---|---|---|
| IT-001 | 23 | 4.95 | 3.14 | 0 | 14.91 | 13 | 1.91 | 22 % | 57 % | 22 % | OK |
| IT-002 | 23 | 7.45 | 2.68 | 0 | 12.87 | 11 | 1.87 | 32 % | 48 % | 20 % | OK |
| IT-003 | 23 | 4.95 | 3.14 | 3 | 11.91 | 11 | 0.91 | 22 % | 48 % | 31 % | OK |
| IT-004 | 23 | 7.45 | 2.68 | 1 | 11.87 | 11 | 0.87 | 32 % | 48 % | 20 % | OK |
| IT-005 | 23 | 4.95 | 3.14 | 1 | 13.91 | 13 | 0.91 | 22 % | 57 % | 22 % | OK |
| IT-006 | 23 | 7.45 | 2.68 | 1 | 11.87 | 10 | 1.87 | 32 % | 43 % | 24 % | OK |
| TOTAL | 138 | 37.2 | 17.48 | 6 | 77.32 | 69 | 8.32 | 27 % | 50 % | 23 % |  |

## Hoja Coste

### Tarifas por rol

| Rol | Tarifa (€/h-p) | Justificación |
|---|---|---|
| PO | 35 | Acordada por el equipo el 14/10/2026. Nivel intermedio: gestiona el valor y el backlog y acepta las historias. |
| TL | 40 | Acordada por el equipo el 14/10/2026. La más alta: responsabilidad técnica, decisiones de arquitectura y revisión de código. |
| SM | 32 | Acordada por el equipo el 14/10/2026. La más baja: facilitación del proceso, sin responsabilidad técnica ni de producto. |

### Por persona

| Miembro | Rol | Tarifa (€/h-p) | h-p de desarrollo asignadas | Coste de desarrollo (€) | h de gestión (6 IT) | Coste de gestión (€) | Coste desarrollo + gestión (€) | h de reserva de imprevistos (6 IT) | Coste de la reserva (€) | Coste total con reserva (€) |
|---|---|---|---|---|---|---|---|---|---|---|
| Hugo | PO | 35 | 18.8 | 658 | 12.4 | 434 | 1092 | 5.92 | 207.2 | 1299.2 |
| Gled | TL | 40 | 17.6 | 704 | 12.4 | 496 | 1200 | 7.4 | 296 | 1496 |
| José | SM | 32 | 32.6 | 1043.2 | 12.4 | 396.8 | 1440 | 4.16 | 133.12 | 1573.12 |
| EQUIPO |  |  | 69 | 2405.2 | 37.2 | 1326.8 | 3732 | 17.48 | 636.32 | 4368.32 |

### Por iteración

| Iteración | Release | h-p desarrollo | Coste de desarrollo (€) | Coste de gestión (€) | Coste de la reserva (€) | Coste total con reserva (€) | Coste acumulado (€) |
|---|---|---|---|---|---|---|---|
| IT-001 | R1 | 13 | 456.2 | 176.55 | 114.47 | 747.22 | 747.22 |
| IT-002 | R1 | 11 | 385.6 | 265.72 | 97.64 | 748.95 | 1496.17 |
| IT-003 | R2 | 11 | 376.8 | 176.55 | 114.47 | 667.82 | 2163.99 |
| IT-004 | R2 | 11 | 386.7 | 265.72 | 97.64 | 750.05 | 2914.05 |
| IT-005 | R3 | 13 | 454.3 | 176.55 | 114.47 | 745.32 | 3659.37 |
| IT-006 | R3 | 10 | 345.6 | 265.72 | 97.64 | 708.95 | 4368.32 |
| TOTAL |  | 69 | 2405.2 | 1326.8 | 636.32 | 4368.32 |  |

### Por historia

| Historia | Título | Release | h-p | Coste de desarrollo (€) |
|---|---|---|---|---|
| US-001 | Registrar cuenta con correo institucional | R1 | 5 | 181.3 |
| US-002 | Verificar la cuenta mediante el correo institucional | R1 | 5 | 175.6 |
| US-003 | Iniciar y cerrar sesión | R1 | 3 | 99.3 |
| US-004 | Gestionar el perfil básico | R1 | 3 | 102.2 |
| US-006 | Publicar el anuncio de mi habitación | R1 | 5 | 178 |
| US-009 | Cerrar mi anuncio | R1 | 3 | 105.4 |
| US-012 | Listar los anuncios disponibles | R2 | 3 | 103.5 |
| US-007 | Añadir fotos al anuncio | R2 | 5 | 173.3 |
| US-010 | Ver mis anuncios y su estado | R2 | 3 | 100 |
| US-013 | Filtrar anuncios por fechas de estancia | R2 | 5 | 176.7 |
| US-014 | Filtrar anuncios por zona | R2 | 3 | 103.8 |
| US-015 | Ver el detalle de un anuncio con su estado | R2 | 3 | 106.2 |
| US-017 | Contactar con el oferente desde un anuncio | R3 | 5 | 178.7 |
| US-008 | Editar mi anuncio | R3 | 3 | 102.2 |
| US-022 | Cargar un conjunto de datos sintéticos | R3 | 5 | 173.4 |
| US-018 | Mantener una conversación | R3 | 5 | 174.2 |
| US-019 | Aviso de mensajes nuevos | R3 | 2 | 70.3 |
| US-023 | Guion de instalación y evaluación | R3 | 3 | 101.1 |
| TOTAL |  |  | 69 | 2405.2 |

### Total

| Concepto | Importe (€) |
|---|---|
| Coste de desarrollo (línea base Must + Should) | 2405.2 |
| Coste de gestión (ceremonias y coordinación) | 1326.8 |
| Coste planificado sin reserva (desarrollo + gestión) | 3732 |
| Reserva de imprevistos (horas de la reserva × tarifa de cada rol) | 636.32 |
| COSTE TOTAL PLANIFICADO CON RESERVA | 4368.32 |
| Coste por tareas de IT-001 (comprobación) | 456.2 |
| Cota superior: dedicación nominal (7 + 7 + 9 h/sem × 6 semanas) × tarifa | 4878 |
| Comprobación: suma por iteración = suma por persona (con reserva) | OK |
| Presupuesto del cliente | No declarado (proyecto académico); tope de referencia = cota superior |
| Margen frente al tope de referencia (€) | 509.68 |

Reserva: Precio de la reserva: horas de imprevistos de cada persona (hoja Gestión, columna D) por su tarifa. Se planifica y se presupuesta, pero solo se gasta si ocurre algún riesgo (RSK-01, RSK-09).

Nota: el coste es una proyección de la planificación, no coste incurrido (IPR-v01, apartado 8).

## Hoja Resumen

| MoSCoW | Nº historias | h-p vigentes | % del planificado |
|---|---|---|---|
| Must | 8 | 32 | 46 % |
| Should | 10 | 37 | 54 % |
| Could | 4 | 10 | fuera de la línea base |
| Won't | 2 | 13 | no planificado |
| Línea base planificada (Must + Should, DEC-025) | 18 | 69 |  |

| Release | Iteraciones | Carga en la línea base (h-p) | Capacidad efectiva equipo (h-p) | Holgura (h-p) | % ocupación |
|---|---|---|---|---|---|
| R1 | IT-001 + IT-002 | 24 | 27.77 | 3.77 | 86 % |
| R2 | IT-003 + IT-004 | 22 | 23.77 | 1.77 | 93 % |
| R3 | IT-005 + IT-006 | 23 | 25.77 | 2.77 | 89 % |
| TOTAL |  | 69 | 77.32 | 8.32 | 89 % |

Diagnóstico: La línea base (Must + Should, DEC-025) cabe en la capacidad de cada release. Planificación por persona: VIABLE · sobrecargas: 0 · sin asignar: 0.0 h-p.

| Coste | Importe |
|---|---|
| Coste total planificado (€) | 4368.32 |
| h-p planificadas (línea base) | 69 |
| Coste de desarrollo (€) | 2405.2 |
| Coste de gestión (€) | 1326.8 |
| Reserva de imprevistos (€) | 636.32 |

## Hoja Épicas

| ID | Épica | Objetivos | Alcance | Nº historias | Nº en la línea base | h-p en la línea base (Must + Should, DEC-025) |
|---|---|---|---|---|---|---|
| EP-001 | Cuenta y verificación institucional | G-02, G-01 | S-01 | 5 | 4 | 16 |
| EP-002 | Publicación y gestión de anuncios | G-01, G-03 | S-02, S-05 | 6 | 5 | 19 |
| EP-003 | Búsqueda y consulta de habitaciones | G-01, G-03 | S-03 | 5 | 4 | 14 |
| EP-004 | Contacto y mensajería | G-01, G-02 | S-04 | 5 | 3 | 12 |
| EP-005 | Entrega, datos de demo e inclusión | G-04, G-01 | S-06 | 3 | 2 | 8 |
| TOTAL |  |  |  | 24 | 18 | 69 |

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

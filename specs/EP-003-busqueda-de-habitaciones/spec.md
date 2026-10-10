# EP-003 — Búsqueda y consulta de habitaciones

**Goals:** G-01, G-03
**Alcance:** S-03
**Baseline:** requisitos-v0 (Hito 2)
**Boundary:** incluye listar anuncios disponibles, filtrarlos por fechas y zona, ordenarlos y ver su detalle con el estado visible. **No incluye** búsqueda en mapa ni geolocalización, recomendaciones automáticas, alertas por correo ni reservas (EX-01).

> Las estimaciones son la **referencia V0** propuesta antes del Planning Poker.

## Epic outcome

Un estudiante que busca alojamiento temporal encuentra en un solo sitio las habitaciones de estudiantes verificados que encajan con sus fechas y su zona, y entiende sin dudas si cada una sigue disponible.

## Stories

### US-012 — Listar los anuncios disponibles

> Como **estudiante que busca alojamiento**, quiero **ver la lista de anuncios disponibles**, para **hacerme una idea de la oferta actual**.

- **Necesidad:** hoy la oferta está dispersa en varios canales.
- **Objetivo:** G-01.
- **Prioridad:** Must. Es la base de toda la búsqueda y la primera pantalla del recorrido del demandante.
- **Estimación:** 6 h-p
- **Incertidumbre:** Baja
- **Predecesoras:** US-006
- **Plan inicial:** IT-003 (R2)

#### Acceptance criteria

- **US-012-AC-01:** la lista muestra solo anuncios en estado **Disponible**, con título, zona, fechas, precio orientativo y tipo de habitación.
- **US-012-AC-02:** se muestran 10 resultados por página, con paginación.
- **US-012-AC-03:** si no hay anuncios, aparece un mensaje claro en lugar de una lista vacía.
- **US-012-AC-04:** un usuario sin sesión puede ver la lista, pero para contactar se le pide iniciar sesión (RN-02).

#### Reglas, restricciones y NFR

- **Reglas:** RN-08.
- **Restricciones:** ninguna específica además de las generales del proyecto (solo datos sintéticos, NFR-009).
- **NFR:** NFR-002 (no se muestra el correo del autor), NFR-005, NFR-006.
- **Dependencias:** funcional US-006; conviene tener los datos sintéticos de US-022 para probar la paginación y el rendimiento.

---

### US-013 — Filtrar anuncios por fechas de estancia ⭐ *muestra detallada*

> Como **estudiante que viene de movilidad o de prácticas**, quiero **filtrar los anuncios por las fechas de mi estancia**, para **ver solo las habitaciones que me sirven y saber si cubren todo mi periodo**.

- **Necesidad:** encontrar una habitación que encaje con las fechas exactas es el principal problema del demandante (ver la misión).
- **Objetivo:** G-01; métrica M-01.
- **Prioridad:** Must. Es la diferencia de valor frente a los canales informales. Tiene riesgo funcional medio (definir bien el solapamiento), por lo que se aborda pronto, en R2.
- **Estimación:** 8 h-p
- **Incertidumbre:** Media (reglas de solapamiento)
- **Predecesoras:** US-012
- **Plan inicial:** IT-003 (R2)

#### Acceptance criteria

- **US-013-AC-01:** el filtro pide una fecha de llegada y una de salida, y la de salida debe ser posterior a la de llegada.
- **US-013-AC-02:** se muestran los anuncios **Disponibles** cuyo periodo se solapa al menos 1 día con el buscado (RN-10).
- **US-013-AC-03:** cada resultado indica **"Cubre todas tus fechas"** o **"Cobertura parcial: del dd/mm al dd/mm"**.
- **US-013-AC-04:** los resultados que cubren el periodo completo aparecen antes que los parciales.
- **US-013-AC-05:** el filtro se combina con el de zona (US-014) y se mantiene al volver desde el detalle de un anuncio.

#### Reglas, restricciones y NFR

- **Reglas:** RN-08, RN-10.
- **Restricciones:** las fechas se muestran en formato dd/mm/aaaa y se interpretan en hora peninsular española.
- **NFR:** NFR-004 (selector de fechas usable con teclado), NFR-005 (< 2 s con ≥ 100 anuncios), NFR-007.
- **Dependencias:**
  - Funcionales: US-012. Debe combinarse con US-014.
  - Técnicas: consulta por rangos de fechas en el modelo de anuncio.
  - Externas: ninguna.

#### Ejemplos

Supongamos tres anuncios disponibles: **A** del 01/02 al 30/06, **B** del 15/03 al 31/07 y **C** del 01/09 al 31/12.

| Búsqueda | Resultado |
|---|---|
| 01/02 – 30/06 | A ("Cubre todas tus fechas") y B ("Cobertura parcial: del 15/03 al 30/06"). C no aparece. |
| 01/04 – 30/06 | A y B, ambos con "Cubre todas tus fechas". |
| 01/08 – 31/08 | Ningún resultado: "No hay habitaciones para esas fechas. Prueba a ampliar el periodo". |

#### Casos límite

- La salida buscada coincide con el día de inicio del anuncio (solapamiento de 1 día): el anuncio aparece como parcial.
- La salida buscada es el día anterior al inicio del anuncio: no se solapa y no aparece.
- Llegada y salida el mismo día: se rechaza con "La fecha de salida debe ser posterior a la de llegada".
- Fechas pasadas: se permiten en la búsqueda, pero casi siempre darán 0 resultados.
- El anuncio se cierra mientras el usuario mira los resultados: desaparece al refrescar y su detalle muestra "Cerrado".

#### Supuestos

- La cobertura parcial es útil para el demandante, que puede combinar alojamientos o ajustar sus fechas. Esta interpretación la validará el cliente en la Review de R2.

---

### US-014 — Filtrar anuncios por zona

> Como **estudiante que busca alojamiento**, quiero **filtrar los anuncios por ciudad y zona**, para **encontrar habitaciones cerca de mi campus**.

- **Necesidad:** la UEx tiene campus en cuatro ciudades, y la cercanía es decisiva.
- **Objetivo:** G-01.
- **Prioridad:** Must. Forma parte del alcance S-03 junto con las fechas. Esfuerzo y riesgo bajos.
- **Estimación:** 4 h-p
- **Incertidumbre:** Baja
- **Predecesoras:** US-012
- **Plan inicial:** IT-003 (R2)

#### Acceptance criteria

- **US-014-AC-01:** se puede filtrar por ciudad y, opcionalmente, por una o varias zonas de esa ciudad (lista cerrada, RN-06).
- **US-014-AC-02:** el filtro se combina con el de fechas.
- **US-014-AC-03:** "Limpiar filtros" vuelve a la lista completa.

#### Reglas, restricciones y NFR

- **Reglas:** RN-06.
- **Restricciones:** la lista de zonas es la misma que en US-006 (TD-03).
- **NFR:** NFR-004, NFR-005.
- **Dependencias:** funcional US-012; técnica: la lista de zonas compartida con US-006.

---

### US-015 — Ver el detalle de un anuncio con su estado

> Como **estudiante que busca alojamiento**, quiero **ver toda la información de un anuncio y si sigue disponible**, para **decidir si me interesa contactar**.

- **Necesidad:** antes de contactar hay que saber qué se ofrece exactamente y si sigue vigente.
- **Objetivo:** G-03 (métrica M-03), G-01.
- **Prioridad:** Must. Es donde se materializa la claridad del estado (G-03) y el punto de entrada al contacto (US-017).
- **Estimación:** 5 h-p
- **Incertidumbre:** Baja
- **Predecesoras:** US-012
- **Plan inicial:** IT-003 (R2)

#### Acceptance criteria

- **US-015-AC-01:** el detalle muestra título, descripción, fechas, zona, precio, tipo de habitación, nombre visible del autor con el distintivo "Verificado UEx" y la fecha de la última actualización.
- **US-015-AC-02:** el estado (Disponible o Cerrado) aparece en la parte superior, con texto e icono, no solo con color.
- **US-015-AC-03:** no se muestra el correo del autor ni ninguna dirección exacta.

#### Reglas, restricciones y NFR

- **Reglas:** RN-08.
- **Restricciones:** nunca se muestra la dirección exacta ni el correo del autor (NFR-002).
- **NFR:** NFR-002, NFR-004.
- **Dependencias:** funcional US-012; el perfil (US-004) y las fotos (US-007) se añaden al detalle cuando estén terminadas.

---

### US-016 — Ordenar los resultados

> Como **estudiante que busca alojamiento**, quiero **ordenar los resultados por precio o por fecha de inicio**, para **comparar las opciones más rápido**.

- **Necesidad:** con muchos resultados, comparar a mano es lento.
- **Objetivo:** G-01.
- **Prioridad:** Could. Comodidad con valor bajo: con el volumen de la demo los filtros son suficientes.
- **Estimación:** 3 h-p
- **Incertidumbre:** Baja
- **Predecesoras:** US-012, US-013
- **Plan inicial:** IT-006 (R3)

#### Acceptance criteria

- **US-016-AC-01:** se puede ordenar por precio (ascendente o descendente) y por fecha de inicio (más próxima primero).
- **US-016-AC-02:** el orden elegido se mantiene al cambiar de página y se aplica después de la prioridad por cobertura completa de US-013-AC-04.

#### Reglas, restricciones y NFR

- **Reglas:** RN-08, RN-10 (prioridad por cobertura completa).
- **Restricciones:** ninguna específica además de las generales del proyecto (solo datos sintéticos, NFR-009).
- **NFR:** NFR-005.
- **Dependencias:** funcionales US-012 y US-013 (por la regla de cobertura).

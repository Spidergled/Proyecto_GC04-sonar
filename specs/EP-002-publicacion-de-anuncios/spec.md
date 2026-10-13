# EP-002 — Publicación y gestión de anuncios

**Goals:** G-01, G-03
**Alcance:** S-02, S-05
**Baseline:** requisitos-v0 (Hito 2)
**Boundary:** incluye publicar, editar y cerrar el anuncio de una habitación, consultar los anuncios propios y su estado, y añadir fotos. **No incluye** pagos, fianzas ni reservas con compromiso económico (EX-01), contratos de subarriendo (EX-02) ni comprobar que el propietario autoriza el subarriendo (SU-03).

> Las estimaciones son las **consensuadas en el Planning Poker del 10/10/2026**; entre paréntesis queda la referencia V0. Las estimaciones individuales están en el Excel y en la Wiki.

## Epic outcome

Un estudiante que se va de movilidad puede ofrecer su habitación de forma clara (fechas, zona y precio) y mantener siempre actualizado si sigue disponible.

## Stories

### US-006 — Publicar el anuncio de mi habitación ⭐ *muestra detallada*

> Como **estudiante verificado que se va de movilidad**, quiero **publicar un anuncio de mi habitación con las fechas en que estará libre, la zona y un precio orientativo**, para **que otros estudiantes verificados la encuentren y no quede vacía**.

- **Necesidad:** hoy el estudiante difunde su habitación por grupos de WhatsApp o Facebook, sin formato común ni garantías.
- **Objetivo:** G-01 (recorrido esencial: publicar).
- **Prioridad:** Must. Sin anuncios no hay oferta que buscar: es el núcleo de valor del producto y el predecesor de toda EP-003 y EP-004. Riesgo técnico bajo, pero es la historia con más reglas de validación (RN-04 a RN-07), por lo que conviene cerrarla pronto.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 10 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-002, US-003
- **Plan inicial:** IT-002 (R1)

#### Acceptance criteria

- **US-006-AC-01:** solo un usuario **verificado** con sesión iniciada ve y puede usar "Publicar anuncio".
- **US-006-AC-02:** el formulario pide título (10–80 caracteres), descripción (máx. 1.000), fecha de inicio, fecha de fin, zona (lista cerrada), precio orientativo (€/mes) y tipo de habitación (individual o compartida).
- **US-006-AC-03:** al publicar con datos válidos, el anuncio queda en estado **Disponible** y aparece en "Mis anuncios" y en las búsquedas.
- **US-006-AC-04:** si un dato no es válido, el formulario conserva lo escrito y marca cada error junto a su campo.
- **US-006-AC-05:** si el usuario ya tiene un anuncio abierto, no puede publicar otro y se le ofrece ir a editarlo.

#### Reglas, restricciones y NFR

- **Reglas:** RN-02, RN-04, RN-05, RN-06, RN-07.
- **Restricciones:** el anuncio no pide ni muestra la dirección exacta (NFR-002); solo datos sintéticos (NFR-009).
- **NFR:** NFR-002, NFR-004, NFR-007.
- **Dependencias:**
  - Funcionales: US-002 (cuenta verificada) y US-003 (sesión).
  - Técnicas: modelo de datos de anuncio y lista de zonas.
  - Externas: ninguna.

#### Ejemplos

| Datos | Resultado |
|---|---|
| "Habitación luminosa junto al campus", 01/02/2027–30/06/2027, Badajoz – Valdepasillas, 250 €, individual | Publicado como **Disponible**. |
| Mismo anuncio con fin el 15/01/2027 | Error: "La fecha de fin debe ser posterior a la de inicio". |
| Precio 20 € | Error: "El precio debe estar entre 50 y 1.000 €/mes". |

#### Casos límite

- Periodo de exactamente 7 días: se acepta. Uno de 6 días se rechaza (RN-04).
- Periodo de exactamente 12 meses: se acepta. Uno de 12 meses y 1 día se rechaza.
- Fecha de inicio igual a hoy: se acepta.
- La sesión caduca mientras se rellena el formulario: al volver a entrar no se pierden los datos escritos o, si se pierden, se avisa antes de enviar.
- Un precio con decimales (249,99) se rechaza indicando que debe ser un número entero.

#### Supuestos

- SU-03: el oferente tiene permiso del propietario para subarrendar.
- SU-02: el pago y el contrato se acuerdan fuera de la plataforma; el precio es solo orientativo.

---

### US-007 — Añadir fotos al anuncio

> Como **estudiante oferente**, quiero **añadir fotos de la habitación a mi anuncio**, para **que los interesados se hagan una idea real sin tener que preguntar**.

- **Necesidad:** sin fotos, el demandante tiene que pedir imágenes por mensaje, lo que alarga el proceso.
- **Objetivo:** G-01.
- **Prioridad:** Should. Aumenta mucho el valor percibido del anuncio, pero el recorrido esencial funciona sin fotos. Tiene un riesgo técnico moderado (almacenamiento de ficheros).
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 6 h-p)
- **Incertidumbre:** Media (almacenamiento de imágenes)
- **Predecesoras:** US-006
- **Plan inicial:** IT-005 (R3)

#### Acceptance criteria

- **US-007-AC-01:** se pueden añadir de 1 a 5 imágenes JPG o PNG de hasta 3 MB cada una.
- **US-007-AC-02:** un fichero de otro tipo o mayor de 3 MB se rechaza con un mensaje que explica el motivo.
- **US-007-AC-03:** cada foto exige un texto alternativo breve, que se usa para la accesibilidad.
- **US-007-AC-04:** las fotos se ven en el detalle del anuncio (US-015).

#### Reglas, restricciones y NFR

- **Reglas:** ninguna específica.
- **Restricciones:** en la demo solo se usan imágenes sintéticas o libres de derechos.
- **NFR:** NFR-004, NFR-007, NFR-009.
- **Dependencias:** funcional US-006; técnica: almacenamiento de ficheros.

---

### US-008 — Editar mi anuncio

> Como **estudiante oferente**, quiero **modificar los datos de mi anuncio abierto**, para **corregir errores o ajustar fechas y precio sin tener que publicarlo de nuevo**.

- **Necesidad:** las fechas de movilidad cambian a menudo, y un anuncio desactualizado genera contactos inútiles.
- **Objetivo:** G-03.
- **Prioridad:** Should. Mantiene la información correcta (G-03), pero hay una alternativa temporal: cerrar el anuncio y publicar otro. Riesgo bajo: reutiliza las validaciones de US-006.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 5 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-006
- **Plan inicial:** IT-005 (R3)

#### Acceptance criteria

- **US-008-AC-01:** solo el autor puede editar su anuncio, y solo mientras está **Disponible**.
- **US-008-AC-02:** al editar se aplican las mismas validaciones que al publicar (US-006).
- **US-008-AC-03:** el detalle muestra la fecha de la última actualización.

#### Reglas, restricciones y NFR

- **Reglas:** RN-04, RN-05, RN-06, RN-08.
- **Restricciones:** ninguna específica además de las generales del proyecto (solo datos sintéticos, NFR-009).
- **NFR:** NFR-007.
- **Dependencias:** funcional US-006.

---

### US-009 — Cerrar mi anuncio ⭐ *muestra detallada*

> Como **estudiante oferente**, quiero **marcar mi anuncio como cerrado cuando la habitación ya no esté disponible**, para **dejar de recibir contactos y que nadie pierda el tiempo con una oferta que ya no existe**.

- **Necesidad:** en los canales informales los anuncios resueltos siguen circulando y nadie sabe si siguen vigentes.
- **Objetivo:** G-03 (claridad del estado); métrica M-03.
- **Prioridad:** Must. Es el núcleo de G-03 y cierra el recorrido esencial (verificarse → publicar → buscar → contactar → cerrar). Tiene esfuerzo bajo y riesgo bajo.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 4 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-006
- **Plan inicial:** IT-002 (R1)

#### Acceptance criteria

- **US-009-AC-01:** el autor ve "Cerrar anuncio" en sus anuncios **Disponibles** y debe confirmar la acción.
- **US-009-AC-02:** tras cerrarlo, el anuncio pasa a **Cerrado**, desaparece de las búsquedas y su detalle muestra "Cerrado" de forma destacada.
- **US-009-AC-03:** en un anuncio cerrado no se pueden iniciar conversaciones nuevas; las existentes se pueden leer, pero se indica que el anuncio está cerrado.
- **US-009-AC-04:** al cerrar el anuncio, el usuario puede volver a publicar otro (deja de aplicar RN-07).

#### Reglas, restricciones y NFR

- **Reglas:** RN-07, RN-08.
- **Restricciones:** solo el autor puede cerrar su anuncio; solo datos sintéticos (NFR-009).
- **NFR:** NFR-004 (el estado no se transmite solo con color, también con texto).
- **Dependencias:**
  - Funcionales: US-006. US-012 y US-017 deben respetar el estado.
  - Técnicas: atributo de estado en el modelo de anuncio.
  - Externas: ninguna.

#### Ejemplos

- Marta encuentra inquilino por la plataforma, pulsa "Cerrar anuncio" y lo confirma. Su anuncio deja de aparecer en la búsqueda de Badajoz.
- Pedro tenía abierto el detalle del anuncio de Marta e intenta enviarle un mensaje: ve "Este anuncio está cerrado y no admite nuevos contactos".

#### Casos límite

- Dos pestañas: el oferente cierra el anuncio en una e intenta editarlo en la otra. Se rechaza porque el anuncio ya está cerrado.
- Un usuario que no es el autor intenta cerrar el anuncio manipulando la petición: se rechaza (NFR-007).
- Se pulsa "Cerrar" dos veces: el resultado es el mismo y no da error.
- Se cancela la confirmación: el anuncio sigue **Disponible**.

#### Supuestos

- Cerrar un anuncio es definitivo (RN-08). No se ha pedido reabrirlo; si el cliente lo pidiera, se trataría como un cambio.

---

### US-010 — Ver mis anuncios y su estado

> Como **estudiante oferente**, quiero **ver la lista de mis anuncios con su estado**, para **saber en todo momento cómo está mi oferta**.

- **Necesidad:** el estudiante no sabe si su anuncio sigue vigente.
- **Objetivo:** G-03.
- **Prioridad:** Must. Es el punto desde el que se edita y se cierra el anuncio, y cumple la experiencia "Transparente" de la misión. Esfuerzo bajo. Riesgo bajo: solo consulta datos que ya crea US-006.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 5 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-006
- **Plan inicial:** IT-002 (R1)

#### Acceptance criteria

- **US-010-AC-01:** "Mis anuncios" lista todos los anuncios del usuario, con título, fechas y estado (Disponible o Cerrado).
- **US-010-AC-02:** desde cada anuncio se accede a sus acciones disponibles, como mínimo cerrarlo (US-009). Los accesos a editar (US-008) y a las conversaciones (US-018) se añaden cuando esas historias se completen.
- **US-010-AC-03:** si el usuario no tiene anuncios, se muestra una invitación a publicar.

#### Reglas, restricciones y NFR

- **Reglas:** RN-07, RN-08.
- **Restricciones:** ninguna específica además de las generales del proyecto (solo datos sintéticos, NFR-009).
- **NFR:** NFR-002 (solo el autor ve esta lista), NFR-004.
- **Dependencias:** funcional US-006.

---

### US-011 — Cierre automático de anuncios caducados

> Como **estudiante que busca alojamiento**, quiero **que los anuncios cuya fecha de fin ya ha pasado se cierren solos**, para **no encontrar ofertas que ya no tienen sentido**.

- **Necesidad:** muchos oferentes olvidan cerrar el anuncio.
- **Objetivo:** G-03.
- **Prioridad:** Could. Mejora G-03, pero el cierre manual (US-009) ya cubre lo esencial y en una demo de 6 semanas casi ningún anuncio llega a caducar. Riesgo bajo, aunque introduce una tarea programada que no necesita ninguna otra historia.
- **Estimación:** 2 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 4 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-009
- **Plan inicial:** IT-006 (R3)

#### Acceptance criteria

- **US-011-AC-01:** un anuncio cuya fecha de fin es anterior a hoy pasa a **Cerrado** automáticamente, como máximo 24 horas después.
- **US-011-AC-02:** en "Mis anuncios", el autor ve que el anuncio se cerró por caducidad.

#### Reglas, restricciones y NFR

- **Reglas:** RN-08.
- **Restricciones:** el cierre se hace sin intervención del usuario; las fechas se interpretan en hora peninsular española, como en US-013.
- **NFR:** ninguno específico.
- **Dependencias:** funcional US-009; técnica: tarea programada o comprobación al consultar.

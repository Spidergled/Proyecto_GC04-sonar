# EP-004 — Contacto y mensajería

**Goals:** G-01, G-02
**Alcance:** S-04
**Baseline:** requisitos-v0 (Hito 2)
**Boundary:** incluye iniciar un contacto desde un anuncio, mantener una conversación de texto entre las dos partes, avisar de los mensajes nuevos dentro de la aplicación y reportar anuncios sospechosos. **No incluye** chat en tiempo real con indicador de "escribiendo", envío de ficheros, videollamadas, notificaciones por correo o push, ni negociación de pagos o contratos (EX-01, EX-02).

> Las estimaciones son las **consensuadas en el Planning Poker del 10/10/2026**; entre paréntesis queda la referencia V0. Las estimaciones individuales están en el Excel y en la Wiki.

## Epic outcome

El demandante y el oferente pueden hablar dentro de la plataforma, sin exponer su correo ni su teléfono, hasta ponerse de acuerdo. Esto completa el recorrido esencial de G-01.

## Stories

### US-017 — Contactar con el oferente desde un anuncio ⭐ *muestra detallada*

> Como **estudiante verificado que busca alojamiento**, quiero **enviar un mensaje al autor de un anuncio desde su detalle**, para **resolver dudas y mostrar mi interés sin compartir mis datos personales**.

- **Necesidad:** en los canales informales hay que dar el teléfono a desconocidos, sin saber si son estudiantes reales.
- **Objetivo:** G-01 (completa el recorrido esencial "buscar y contactar") y G-02 (solo contactan usuarios verificados).
- **Prioridad:** Must. Valor: sin contacto el recorrido esencial no se completa y no se puede medir M-01. · Riesgo: medio; define el modelo de conversación compartido con US-018 (TD-04) y es donde se aplica la protección de datos (NFR-002, RN-11). · Dependencias: necesita US-015 y US-002; de ella depende US-018.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 8 h-p)
- **Incertidumbre:** Media (modelo de conversación compartido con US-018)
- **Predecesoras:** US-015, US-002
- **Plan inicial:** R3, IT-005 – IT-006 (excepción de DEC-018). Justificación: tras US-015 (3 h-p), IT-005 (5,95 h-p) no tiene sitio para las 5 h-p completas, y enviar el primer mensaje no se puede dividir sin perder el resultado observable de la historia. La estimación cabe en la capacidad de las dos iteraciones (11,9 h-p). Incluye TD-04 (modelo de conversación, pensado también para US-018) (DEC-020). Cuenta como Hecha al final de IT-006.

#### Acceptance criteria

- **US-017-AC-01:** en el detalle de un anuncio **Disponible** de otro usuario, un usuario verificado ve el botón "Contactar".
- **US-017-AC-02:** al enviar un primer mensaje (1–1.000 caracteres) se crea una conversación entre el demandante y el autor, vinculada a ese anuncio.
- **US-017-AC-03:** si ya existe una conversación entre ese usuario y ese anuncio, "Contactar" abre la conversación existente en lugar de crear otra.
- **US-017-AC-04:** un usuario sin sesión que pulsa "Contactar" va al inicio de sesión y, al entrar, vuelve al anuncio. Un usuario no verificado ve por qué no puede contactar.
- **US-017-AC-05:** ninguna de las dos partes ve el correo de la otra; solo su nombre visible.

#### Reglas, restricciones y NFR

- **Reglas:** RN-02, RN-08, RN-09, RN-11.
- **Restricciones:** los mensajes de la demo son sintéticos (NFR-009).
- **NFR:** NFR-002, NFR-004, NFR-007.
- **Dependencias:**
  - Funcionales: US-015 (punto de entrada) y US-002 (cuenta verificada). US-009 condiciona el comportamiento con anuncios cerrados.
  - Técnicas: modelo de datos de conversación y mensaje, que comparte con US-018. Conviene que la misma persona diseñe ambas historias.
  - Externas: ninguna.

#### Ejemplos

- Pedro (verificado) ve el anuncio de Marta en Badajoz, pulsa "Contactar" y escribe "Hola, ¿la habitación tiene escritorio?". Marta ve una conversación nueva con "Pedro · Verificado UEx" junto al título de su anuncio.
- Marta intenta "Contactar" con su propio anuncio: el botón no aparece.

#### Casos límite

- Mensaje vacío o solo con espacios: no se envía y se indica "Escribe un mensaje".
- Mensaje de 1.001 caracteres: se rechaza y el contador muestra el exceso.
- El anuncio se cierra entre que Pedro abre el formulario y lo envía: se rechaza con "Este anuncio está cerrado y no admite nuevos contactos" (RN-08).
- Mensaje con HTML o scripts: se muestra como texto plano y no se ejecuta (NFR-007).
- Pedro intenta abrir una conversación ajena cambiando el identificador en la URL: acceso denegado (RN-11).

#### Supuestos

- El contacto dentro de la plataforma basta para ponerse de acuerdo. Las partes pueden intercambiar otros datos por voluntad propia, y la plataforma no lo controla (SU-02).

---

### US-018 — Mantener una conversación

> Como **estudiante (oferente o demandante)**, quiero **ver mis conversaciones y responder a los mensajes**, para **seguir la negociación con la otra parte en un único sitio**.

- **Necesidad:** un contacto sin respuesta posible no resuelve nada; hace falta ida y vuelta.
- **Objetivo:** G-01, G-02.
- **Prioridad:** Should. Valor: permite responder y mantener la conversación; el contacto inicial (US-017) ya cubre S-04 en su versión mínima. · Riesgo: medio, por el modelo de conversación compartido con US-017 (TD-04) y el control de acceso a conversaciones ajenas (RN-11). · Dependencias: necesita US-017; de ella dependen US-019 y US-024. Era Must; baja a Should por capacidad (DEC-016). Tercera candidata a entrar.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 10 h-p)
- **Incertidumbre:** Media
- **Predecesoras:** US-017
- **Plan inicial:** fuera de la línea base (DEC-016); candidata n.º 3 a entrar si la velocidad real lo permite

#### Acceptance criteria

- **US-018-AC-01:** "Mis conversaciones" lista las conversaciones del usuario, con el anuncio, la otra persona, el último mensaje y su fecha, ordenadas de la más reciente a la más antigua.
- **US-018-AC-02:** al abrir una conversación se ven todos sus mensajes en orden y se puede responder.
- **US-018-AC-03:** las respuestas siguen las mismas validaciones que US-017 (longitud y texto plano).
- **US-018-AC-04:** si el anuncio está cerrado, la conversación se puede leer y se indica "Anuncio cerrado".

#### Reglas, restricciones y NFR

- **Reglas:** RN-08, RN-11.
- **Restricciones:** los mensajes de la demo son sintéticos (NFR-009); sin chat en tiempo real ni envío de ficheros (Boundary de EP-004).
- **NFR:** NFR-002, NFR-004, NFR-005, NFR-007.
- **Dependencias:** funcional US-017; técnica: el mismo modelo de conversación.

---

### US-019 — Aviso de mensajes nuevos

> Como **estudiante con conversaciones abiertas**, quiero **ver un indicador de mensajes sin leer**, para **responder a tiempo sin revisar cada conversación**.

- **Necesidad:** la falta de respuesta rápida hace perder oportunidades de alojamiento.
- **Objetivo:** G-01.
- **Prioridad:** Should. Valor: mejora mucho la experiencia, pero no bloquea el recorrido. · Riesgo: bajo. · Dependencias: necesita US-018; ninguna depende de ella.
- **Estimación:** 2 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 4 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-018
- **Plan inicial:** fuera de la línea base (DEC-016)

#### Acceptance criteria

- **US-019-AC-01:** el menú muestra el número de conversaciones con mensajes sin leer.
- **US-019-AC-02:** al abrir una conversación, sus mensajes se marcan como leídos y el contador se actualiza.
- **US-019-AC-03:** el indicador tiene un texto accesible ("3 conversaciones sin leer"), no solo un número o un color.

#### Reglas, restricciones y NFR

- **Reglas:** RN-11 (solo cuenta las conversaciones propias).
- **Restricciones:** solo avisos dentro de la aplicación, sin correo ni push (Boundary de EP-004).
- **NFR:** NFR-004.
- **Dependencias:** funcional US-018.

---

### US-020 — Reportar un anuncio sospechoso

> Como **estudiante**, quiero **reportar un anuncio que parezca falso o inadecuado**, para **que la Delegación pueda revisarlo y la plataforma siga siendo de confianza**.

- **Necesidad:** aun con verificación pueden aparecer anuncios engañosos.
- **Objetivo:** G-02.
- **Prioridad:** Could. Valor: refuerza la confianza, pero la verificación institucional ya cubre el riesgo principal; la revisión de los reportes quedaría para una versión futura. · Riesgo: bajo. · Dependencias: necesita US-015 y US-003; ninguna depende de ella.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 4 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-015, US-003
- **Plan inicial:** fuera de la línea base (DEC-016)

#### Acceptance criteria

- **US-020-AC-01:** en el detalle de un anuncio ajeno, un usuario con sesión puede reportarlo eligiendo un motivo de una lista y añadiendo un comentario opcional.
- **US-020-AC-02:** un mismo usuario solo puede reportar una vez cada anuncio.
- **US-020-AC-03:** los reportes quedan registrados con la fecha, el motivo y el anuncio, para que se puedan revisar después.

#### Reglas, restricciones y NFR

- **Reglas:** ninguna específica.
- **Restricciones:** la revisión de los reportes por la Delegación queda fuera de esta historia.
- **NFR:** NFR-007.
- **Dependencias:** funcionales US-015 y US-003.

---

### US-021 — Valorar a la otra parte tras la estancia

> Como **estudiante que ha subalquilado o alquilado una habitación**, quiero **valorar a la otra parte al terminar la estancia**, para **que otros estudiantes sepan si es de confianza**.

- **Necesidad:** la reputación ayudaría a decidir con más confianza.
- **Objetivo:** G-02.
- **Prioridad:** Won't (en este proyecto). Tiene valor, pero exige saber que la estancia se ha producido, algo que pasa fuera de la plataforma (SU-02). Además abre problemas de moderación y de difamación que no caben en 6 semanas. Queda registrada para una versión futura.
- **Estimación:** 8 h-p (solo orientativa; no se planifica)
- **Incertidumbre:** Alta
- **Predecesoras:** US-018
- **Plan inicial:** no planificada (fuera de la línea base)

#### Acceptance criteria (borrador)

- **US-021-AC-01:** solo pueden valorarse dos usuarios que hayan mantenido una conversación sobre un anuncio que ya está cerrado.
- **US-021-AC-02:** la valoración es de 1 a 5 estrellas con un comentario opcional, y se muestra en el perfil.

#### Reglas, restricciones y NFR

- **Reglas:** pendientes de definir con la Delegación (EXT-04).
- **Restricciones:** requeriría registrar una decisión de ampliación de alcance (`goals.md`, *Gestión de cambios*).
- **NFR:** pendientes; la historia no se planifica.
- **Dependencias:** funcional US-018; externa: criterio de moderación por parte de la Delegación.

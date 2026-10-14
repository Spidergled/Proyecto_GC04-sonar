# EP-005 — Entrega, datos de demo e inclusión

**Goals:** G-04, G-01
**Alcance:** S-06 (más la experiencia "Inclusiva" de `mission.md` y el principio 5 de la constitución)
**Baseline:** requisitos-v0 (Hito 2)
**Boundary:** incluye el conjunto de datos sintéticos y el guion de instalación y evaluación para un tercero. **No incluye** la interfaz en inglés (US-024, Won't por DEC-016), el despliegue en producción con usuarios reales, la migración de datos reales (NFR-009) ni una app nativa (EX-03).

> Las estimaciones son las **consensuadas en el Planning Poker del 10/10/2026**; entre paréntesis queda la referencia V0. Las estimaciones individuales están en el Excel y en la Wiki.

## Epic outcome

Una persona ajena al equipo, como el profesorado o la Delegación, puede instalar ErasmusNest, cargar datos ficticios realistas y evaluar el incremento sin ayuda del equipo.

## Stories

### US-022 — Cargar un conjunto de datos sintéticos

> Como **evaluador del incremento**, quiero **cargar con un único paso un conjunto de usuarios, anuncios y conversaciones ficticios**, para **probar la plataforma en condiciones realistas sin usar datos reales**.

- **Necesidad:** sin datos, la demo está vacía y no se pueden probar la búsqueda, el rendimiento ni las métricas.
- **Objetivo:** G-04; habilita las pruebas de M-01 a M-03.
- **Prioridad:** Should. Valor: carga en un paso los datos sintéticos de S-06 y permite medir NFR-005 con ≥ 100 anuncios. · Riesgo: bajo. · Dependencias: necesita US-001 y US-006. Era Must; baja a Should por capacidad (DEC-016): mientras no entre, el guion (US-023) crea a mano un conjunto mínimo de datos sintéticos. Segunda candidata a entrar.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 6 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-001, US-006
- **Plan inicial:** fuera de la línea base (DEC-016); candidata n.º 2 a entrar si la velocidad real lo permite

#### Acceptance criteria

- **US-022-AC-01:** un único comando o paso documentado carga al menos 20 usuarios verificados, 100 anuncios (con al menos un 20 % cerrados) repartidos por las cuatro ciudades, y varias conversaciones.
- **US-022-AC-02:** todos los nombres, correos y textos son ficticios y los correos usan el dominio de prueba configurado.
- **US-022-AC-03:** la carga se puede repetir: deja la base de datos en un estado conocido sin duplicar registros.
- **US-022-AC-04:** se documentan las credenciales de 3 cuentas de demo (oferente, demandante y no verificada).

#### Reglas, restricciones y NFR

- **Reglas:** todas las RN se cumplen también en los datos generados.
- **Restricciones:** los correos usan el dominio de prueba configurado; ningún dato real (NFR-009).
- **NFR:** NFR-005 (volumen para medir el rendimiento), NFR-009.
- **Dependencias:** funcionales US-001 y US-006 (modelos de usuario y anuncio); se amplía cuando existe US-017.

---

### US-023 — Guion de instalación y evaluación

> Como **evaluador ajeno al equipo**, quiero **un guion paso a paso para instalar, arrancar y recorrer la aplicación**, para **evaluar el incremento sin depender del equipo**.

- **Necesidad:** G-04 exige que un tercero pueda instalar y evaluar el incremento.
- **Objetivo:** G-04; métrica M-04.
- **Prioridad:** Must. Valor: sin él no se cumplen G-04 ni M-04 y la entrega no se puede evaluar. · Riesgo: medio de integración (depende de las tecnologías aprobadas, EXT-01, y de un evaluador externo, EXT-03); conviene probarlo antes de la última release. · Dependencias: necesita US-017, porque el guion se valida sobre el incremento completo de la línea base; ninguna depende de ella.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 5 h-p)
- **Incertidumbre:** Media (depende de las tecnologías que se aprueben)
- **Predecesoras:** US-017
- **Plan inicial:** R3 (línea base, DEC-016); la iteración concreta se fija en la planificación del Trabajo 1

#### Acceptance criteria

- **US-023-AC-01:** el `README.md` del repositorio contiene los requisitos previos, como máximo 10 pasos de instalación y arranque, la creación de un conjunto mínimo de datos sintéticos de demo (cuentas y anuncios ficticios) y las credenciales de prueba. Cuando entre US-022, ese paso se sustituirá por su carga en un único paso.
- **US-023-AC-02:** incluye un guion de evaluación que recorre los flujos principales: registrarse y verificarse, publicar, buscar, ver el detalle y contactar. Cuando entre US-009, se añadirá el cierre del anuncio.
- **US-023-AC-03:** una persona ajena al equipo lo sigue sin intervenciones no documentadas (M-04), y el resultado queda como evidencia.

#### Reglas, restricciones y NFR

- **Reglas:** ninguna específica.
- **Restricciones:** depende de las tecnologías aprobadas en la asignatura (SU-07, EXT-01).
- **NFR:** NFR-008.
- **Dependencias:** funcional US-017 (el guion se valida sobre el incremento completo de la línea base, DEC-016); externa: disponibilidad de un evaluador externo (SU-06).

---

### US-024 — Usar la interfaz en inglés

> Como **estudiante de intercambio que no domina el español**, quiero **cambiar la interfaz a inglés**, para **usar ErasmusNest sin barreras de idioma**.

- **Necesidad:** parte de los demandantes son estudiantes extranjeros que llegan a la UEx (ver la misión, experiencia "Inclusiva").
- **Objetivo:** G-01.
- **Prioridad:** Won't. Valor: alto para estudiantes que no dominan el español, pero no está en el alcance S-01…S-06 y no hay capacidad. · Riesgo: medio; afecta a todas las pantallas. · Dependencias: necesita US-015 y US-018. Pasa de Could a Won't por DEC-016 (tarea 8 del Hito 2); se registra para una versión futura.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 8 h-p)
- **Incertidumbre:** Media (afecta a todas las pantallas)
- **Predecesoras:** US-015, US-018 (pantallas principales terminadas)
- **Plan inicial:** no planificada (Won't, DEC-016)

#### Acceptance criteria

- **US-024-AC-01:** un selector visible permite cambiar entre español e inglés, y la elección se mantiene durante la sesión.
- **US-024-AC-02:** todos los textos de la interfaz y los mensajes de error de los flujos principales están traducidos.
- **US-024-AC-03:** el contenido escrito por los usuarios (anuncios y mensajes) no se traduce.

#### Reglas, restricciones y NFR

- **Reglas:** ninguna específica.
- **Restricciones:** solo español e inglés; el contenido escrito por los usuarios no se traduce.
- **NFR:** NFR-004 (atributo de idioma correcto en la página).
- **Dependencias:** funcionales US-015 y US-018; técnica: textos de la interfaz separados del código desde el inicio, para que traducir sea barato. Es una recomendación al TL para IT-001.

# EP-005 — Entrega, datos de demo e inclusión

**Goals:** G-04, G-01
**Alcance:** S-06 (más la experiencia "Inclusiva" de `mission.md` y el principio 5 de la constitución)
**Baseline:** requisitos-v0 (Hito 2)
**Boundary:** incluye el conjunto de datos sintéticos, el guion de instalación y evaluación para un tercero, y la interfaz en español e inglés. **No incluye** el despliegue en producción con usuarios reales, la migración de datos reales (NFR-009) ni una app nativa (EX-03).

> Las estimaciones son la **referencia V0** propuesta antes del Planning Poker.

## Epic outcome

Una persona ajena al equipo, como el profesorado o la Delegación, puede instalar ErasmusNest, cargar datos ficticios realistas y evaluar el incremento sin ayuda del equipo. Además, los estudiantes de intercambio pueden usarlo aunque no dominen el español.

## Stories

### US-022 — Cargar un conjunto de datos sintéticos

> Como **evaluador del incremento**, quiero **cargar con un único paso un conjunto de usuarios, anuncios y conversaciones ficticios**, para **probar la plataforma en condiciones realistas sin usar datos reales**.

- **Necesidad:** sin datos, la demo está vacía y no se pueden probar la búsqueda, el rendimiento ni las métricas.
- **Objetivo:** G-04; habilita las pruebas de M-01 a M-03.
- **Prioridad:** Must. Lo exigen S-06 y la restricción de datos sintéticos. Reduce el riesgo de todas las pruebas, porque se usa desde R1.
- **Estimación:** 6 h-p
- **Incertidumbre:** Baja
- **Predecesoras:** US-001, US-006
- **Plan inicial:** IT-002 (R1); se amplía con conversaciones en IT-004

#### Acceptance criteria

- **US-022-AC-01:** un único comando o paso documentado carga al menos 20 usuarios verificados, 100 anuncios (con al menos un 20 % cerrados) repartidos por las cuatro ciudades, y varias conversaciones.
- **US-022-AC-02:** todos los nombres, correos y textos son ficticios y los correos usan el dominio de prueba configurado.
- **US-022-AC-03:** la carga se puede repetir: deja la base de datos en un estado conocido sin duplicar registros.
- **US-022-AC-04:** se documentan las credenciales de 3 cuentas de demo (oferente, demandante y no verificada).

#### Reglas, restricciones y NFR

- **Reglas:** todas las RN se cumplen también en los datos generados.
- **NFR:** NFR-005 (volumen para medir el rendimiento), NFR-009.
- **Dependencias:** funcionales US-001 y US-006 (modelos de usuario y anuncio); se amplía cuando existe US-017.

---

### US-023 — Guion de instalación y evaluación

> Como **evaluador ajeno al equipo**, quiero **un guion paso a paso para instalar, arrancar y recorrer la aplicación**, para **evaluar el incremento sin depender del equipo**.

- **Necesidad:** G-04 exige que un tercero pueda instalar y evaluar el incremento.
- **Objetivo:** G-04; métrica M-04.
- **Prioridad:** Must. Sin él, G-04 y M-04 no se cumplen y la entrega no se puede evaluar. Tiene un riesgo de integración que conviene probar antes de la última release.
- **Estimación:** 5 h-p
- **Incertidumbre:** Media (depende de las tecnologías que se aprueben)
- **Predecesoras:** US-022
- **Plan inicial:** IT-005 (R3); primera versión mínima en R1 para las revisiones

#### Acceptance criteria

- **US-023-AC-01:** el `README.md` del repositorio contiene los requisitos previos, como máximo 10 pasos de instalación y arranque, la carga de datos de demo y las credenciales de prueba.
- **US-023-AC-02:** incluye un guion de evaluación que recorre los flujos principales: registrarse y verificarse, publicar, buscar, contactar y cerrar el anuncio.
- **US-023-AC-03:** una persona ajena al equipo lo sigue sin intervenciones no documentadas (M-04), y el resultado queda como evidencia.

#### Reglas, restricciones y NFR

- **NFR:** NFR-008.
- **Dependencias:** funcional US-022; externa: disponibilidad de un evaluador externo (SU-06).

---

### US-024 — Usar la interfaz en inglés

> Como **estudiante de intercambio que no domina el español**, quiero **cambiar la interfaz a inglés**, para **usar ErasmusNest sin barreras de idioma**.

- **Necesidad:** parte de los demandantes son estudiantes extranjeros que llegan a la UEx (ver la misión, experiencia "Inclusiva").
- **Objetivo:** G-01.
- **Prioridad:** Could. Tiene valor alto para un tipo de usuario, pero no figura en el alcance S-01…S-06. Es un esfuerzo transversal que conviene abordar cuando todas las pantallas estén estables. Si se planifica, se registrará la decisión de ampliar el alcance.
- **Estimación:** 8 h-p
- **Incertidumbre:** Media (afecta a todas las pantallas)
- **Predecesoras:** US-015, US-018 (pantallas principales terminadas)
- **Plan inicial:** IT-006 (R3)

#### Acceptance criteria

- **US-024-AC-01:** un selector visible permite cambiar entre español e inglés, y la elección se mantiene durante la sesión.
- **US-024-AC-02:** todos los textos de la interfaz y los mensajes de error de los flujos principales están traducidos.
- **US-024-AC-03:** el contenido escrito por los usuarios (anuncios y mensajes) no se traduce.

#### Reglas, restricciones y NFR

- **NFR:** NFR-004 (atributo de idioma correcto en la página).
- **Dependencias:** funcionales US-015 y US-018; técnica: textos de la interfaz separados del código desde el inicio, para que traducir sea barato. Es una recomendación al TL para IT-001.

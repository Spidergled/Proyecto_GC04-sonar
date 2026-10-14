# Registro de uso de IA

Registro de los agentes de revisión de la Práctica 1 (semanas 3 y 4) y de lo que el equipo decide con cada propuesta. Los agentes proponen; el equipo revisa cada hallazgo contra las fuentes y decide. Ninguna propuesta se aplica sin comprobarla.

## 1. Agentes

| Agente | Instrucciones | Versión | Herramienta | Modelo | Informes | Límite |
|---|---|---|---|---|---|---|
| Requirements Agent | `.github/agents/requirements-reviewer.agent.md` | 1.1 (12/10/2026) | GitHub Copilot en VS Code | *[a completar]* | `docs/project/reviews/requirements/` | No modifica requisitos ni decide el alcance |
| Planning Agent | `.github/agents/initial-planning-reviewer.agent.md` | 1.1 (12/10/2026) | GitHub Copilot en VS Code | *[a completar]* | `docs/project/reviews/initial-planning/` | No replanifica ni modifica los archivos |

La v01 del informe de requisitos (10/10/2026, José) se generó con la versión anterior del agente (`e4db02d`); el modelo usado no quedó anotado *[a completar por José]*.

## 2. Cómo se usa

1. Se fija el commit de la baseline revisada y se lanza el agente desde el selector de agentes de Copilot, indicando modo (INITIAL, FOCUSED o RECHECK), alcance y commit.
2. El agente crea un informe nuevo `…-vNN.md`; nunca sobrescribe uno anterior.
3. El equipo revisa cada hallazgo contra la fuente citada y anota aquí la decisión, con su justificación y, si cambia algo, el commit o la DEC del Registro de decisiones de la Wiki.
4. Tras corregir, se relanza el agente en modo RECHECK con este registro como entrada.

## 3. Registro de hallazgos

Decisión: Aceptada, Aceptada con cambios, Rechazada o Pendiente.

| Agente e ID | Entrada | Propuesta | Justificación (según el agente) | Decisión | Evidencia o responsable |
|---|---|---|---|---|---|
| Requirements · v01 F-01 | `requisitos-v0`, commit no indicado; specs EP-001…005, `nfr-y-reglas.md` | NFR-002 prohíbe mostrar datos del usuario, pero US-004, US-015 y US-017 muestran el nombre visible. Precisar qué datos son públicos | Ambas obligaciones son explícitas. Confianza alta. MAJOR, bloqueante | Pendiente | Hugo |
| Requirements · v01 F-02 | Ídem | US-001 pide confirmar la contraseña, pero no define qué pasa si no coincide | No hay criterio para ese caso. Confianza alta. MAJOR, bloqueante | Pendiente | Hugo (US-001-AC-06) |
| Requirements · v01 F-03 | Ídem | Faltan NFR aplicables: NFR-004 en US-002 y NFR-009 en US-013 | Constitución (principio 5) y NFR-009 globales. Confianza alta. MAJOR, bloqueante | Aceptada | Corregido en `99d8d9a` |
| Requirements · v01 F-04 | Ídem | RN-10 no fija si las fechas son inclusivas ni cómo se calcula la cobertura parcial en US-013 | Solo hay un caso límite definido. Confianza media. MAJOR, bloqueante | Pendiente | Hugo y Gled (RN-10) |
| Requirements · v01 F-05 | Ídem | Las estimaciones de las seis historias detalladas son V0, previas al Planning Poker | Lo dicen las propias specs. Confianza alta. MAJOR, bloqueante | Aceptada | Planning Poker del 10/10, `abc523d` |
| Requirements · v01 F-06 | Ídem | No se puede comprobar si cada estimación cabe en un sprint ni aplicar DEC-018 | El agente no pudo leer el Excel. Confianza alta en la falta de evidencia. QUESTION | Aceptada | DEC-016 (14/10/2026): ninguna historia supera 5 h-p y la línea base (32 h-p) cabe en cada release (hoja *Resumen* del Excel) |
| Requirements · v01 F-07 | Ídem | US-024 (inglés) no está en S-01…S-06 y tiene plan en IT-006; US-007, US-011, US-020 y US-004 sin S-XX inequívoco | Alcance de `goals.md` explícito. Confianza media. QUESTION | Aceptada | DEC-016 (14/10/2026): US-024 pasa a Won't; US-004, US-007, US-011 y US-020 quedan fuera de la línea base |
| Requirements · v01 F-08 | Ídem | Sin orden dentro del sprint (US-006→US-009, US-012→US-013); US-017 no cita US-009; US-023 solo cita US-022 | Basado en los planes iniciales. Confianza media. QUESTION | Pendiente | Se resuelve en la planificación del Excel |

### Candidatos y preguntas del informe v01 sin número de hallazgo (§7 y §9)

El equipo también tiene que clasificar estas propuestas, aunque el agente no las numere como hallazgos.

| Agente e ID | Entrada | Propuesta | Justificación (según el agente) | Decisión | Evidencia o responsable |
|---|---|---|---|---|---|
| Requirements · v01 §7 US-007 | Ídem | Fotos: ¿entran en S-02 o son ampliación? | S-02 solo nombra fechas, zona y precio. SCOPE_UNCLEAR | Aceptada | Fuera de la línea base (DEC-016); el alcance se revisará si llega a entrar |
| Requirements · v01 §7 US-011 | Ídem | Cierre automático: S-05 solo nombra el cierre manual | SCOPE_UNCLEAR | Aceptada | Fuera de la línea base (DEC-016); el alcance se revisará si llega a entrar |
| Requirements · v01 §7 US-020 | Ídem | Reportes: candidato fuera de alcance | S-01…S-06 no incluyen moderación. OUT_OF_SCOPE candidato | Aceptada | Fuera de la línea base (DEC-016) |
| Requirements · v01 §7 US-023 | Ídem | Registrar el guion como entregable que habilita G-04 | G-04, M-04 y NFR-008. IN_SCOPE_CANDIDATE | Aceptada | En la línea base (DEC-016), R3 |
| Requirements · v01 §7 US-013 | Ídem | ¿Mostrar coberturas parciales o solo anuncios que cubren toda la estancia? | Utilidad pendiente de validar en la Review de R2. IN_SCOPE_CANDIDATE | Pendiente | Hugo (ligado a F-04) |
| Requirements · v01 §7 anuncios cerrados | Ídem | ¿Se puede responder en conversaciones de un anuncio cerrado o solo leerlas? | US-009 y US-018 no lo fijan. SCOPE_UNCLEAR | Pendiente | Aplazada por DEC-016: US-009 y US-018 están fuera de la línea base; se decidirá cuando entren · Hugo |
| Requirements · v01 §8 M-05 | Ídem | M-05 no está trazada a ninguna historia | Solo cubierta en parte por la DoD | Pendiente | Hugo |
| Requirements · v01 §9 P2 | Ídem | ¿Qué validación observable se aplica a un correo mal formado en US-001? | Pregunta para decisión humana | Pendiente | Hugo (junto con F-02) |

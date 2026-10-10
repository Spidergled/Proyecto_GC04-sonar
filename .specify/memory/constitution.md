# ErasmusNest — Constitution

**Versión:** 1.1.0
**Fecha de ratificación:** 2026-09-30
**Ratificada por:** Hugo, Gled y José (equipo fundador, Practical Software)

## Propósito

Esta constitución define los principios no negociables del proyecto **ErasmusNest**,
la plataforma que permite a los estudiantes de la UEx subalquilar su habitación a
otros estudiantes verificados durante periodos de movilidad (Erasmus, prácticas
fuera). No describe qué funcionalidades construir ni cómo implementarlas: es el
marco de gobierno que toda especificación, plan, tarea e implementación debe
respetar.

## Core Principles

### 1. Especificación y trazabilidad primero

Toda funcionalidad de ErasmusNest debe estar especificada antes de implementarse.
Debe poder seguirse en todo momento la relación:

**Misión → Objetivos (G-XX) → Épicas (EP-XXX) → Historias (US-XXX) → Plan → Tareas (TK-XXX) → Código y pruebas → Evidencias**

No se implementará ninguna funcionalidad que no esté justificada por una
especificación aprobada.

### 2. Disciplina de alcance del MVP

El equipo se centra en las funcionalidades necesarias para un producto mínimo
viable, tal y como queda delimitado en el alcance incluido de
`specs/product/goals.md`. Cualquier ampliación de alcance deberá:

- Estar justificada.
- Ser priorizada.
- Evaluar su impacto en plazo, coste y capacidad.
- Quedar documentada como decisión humana del equipo.

### 3. Calidad y comportamiento verificable

Los requisitos deben ser claros, medibles y comprobables. Cada funcionalidad debe
incluir criterios de aceptación, tratamiento de errores, pruebas adecuadas y
cumplimiento de la definición de terminado (Definition of Done). Una
funcionalidad no se considera terminada únicamente porque el código funcione en
condiciones ideales.

### 4. Seguridad, privacidad y datos sintéticos

ErasmusNest maneja información sensible por naturaleza (identidad de estudiantes,
ubicación de alojamiento, disponibilidad de fechas). Por ello, el proyecto
utilizará exclusivamente datos ficticios o sintéticos durante todo el desarrollo
y las demostraciones. La solución deberá:

- Evitar el uso de datos personales, académicos o de contacto reales.
- Validar todas las entradas del usuario.
- Proteger la información y las operaciones sobre anuncios y contactos.
- Considerar la seguridad desde la especificación hasta las pruebas.

### 5. Usabilidad y accesibilidad

Los usuarios de ErasmusNest incluyen estudiantes de intercambio que pueden no
dominar el español y que utilizan la plataforma bajo presión de tiempo (llegada
inminente, plazo de salida). Las funcionalidades deben ser comprensibles,
consistentes y fáciles de usar. Los flujos principales, los mensajes de error y
las interfaces deberán diseñarse teniendo en cuenta la accesibilidad y la
diversidad de usuarios. Su cumplimiento se verifica mediante los criterios de
aceptación de las historias afectadas.

## Reglas de Documentación

Cada artefacto tiene una responsabilidad diferente:

- `spec.md`: explica **qué** se necesita y **por qué**.
- `plan.md`: explica **cómo** se abordará técnicamente.
- `tasks.md`: descompone el plan en **trabajo concreto y ejecutable**.

Las decisiones importantes deberán quedar documentadas y los artefactos deberán
mantener la trazabilidad entre ellos.

Todo artefacto usa los identificadores acordados por el equipo: G-XX (objetivos),
M-XX (indicadores), S-XX (alcance), EX-XX (exclusiones), SU-XX (supuestos) y
SH-XX (stakeholders) en `goals.md`; EP-XXX, US-XXX, AC-XX, TK-XXX, ST-XXXX e
IT-XXX en especificaciones, planes y tareas. Cada elemento referencia el
identificador del nivel superior del que procede.

## Controles de Calidad

Antes de aprobar una especificación, un plan o una implementación, debe
comprobarse que:

- Cumple los principios de esta constitución.
- Respeta el alcance acordado en `goals.md`.
- Mantiene la trazabilidad completa.
- Define criterios verificables.
- Considera calidad, pruebas, seguridad y accesibilidad.
- No contiene decisiones importantes sin justificar.

## Gobierno

Esta constitución tiene prioridad sobre el resto de los documentos del proyecto.
Cualquier modificación deberá:

- Estar justificada.
- Ser aprobada explícitamente por el equipo (Product Owner incluido).
- Registrar la fecha y la nueva versión.
- Indicar qué documentos o decisiones resultan afectados.

Las excepciones deberán ser explícitas, justificadas, aprobadas y, cuando sea
posible, temporales.

### Historial de versiones

| Versión | Fecha | Cambio |
|---|---|---|
| 1.0.0 | 2026-09-30 | Versión inicial de la constitución para ErasmusNest. |
| 1.1.0 | 2026-10-10 | Correcciones de la auditoría del Hito 1: la cadena de trazabilidad del principio 1 se alinea con `goals.md`; el principio 2 remite al alcance de `goals.md` en lugar de enumerar funcionalidades; se añaden los identificadores a las Reglas de Documentación y el criterio de verificación del principio 5. Documentos afectados: `goals.md` (sin cambios de contenido por esta versión). |
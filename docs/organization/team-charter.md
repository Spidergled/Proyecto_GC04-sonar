# ErasmusNest — Team Charter

Equipo: Hugo, Gled y José.

## Roles principales

| Miembro | Rol | Cometido principal |
|---|---|---|
| Hugo | Product Owner | Visión, valor, stakeholders, alcance, prioridad y criterios de aceptación. |
| Gled | Tech Lead | Coherencia técnica, descomposición, dependencias, restricciones y riesgos técnicos. |
| José | Scrum Master | Proceso de trabajo, reuniones, impedimentos, acuerdos y trazabilidad. |

Las tareas de DevOps/QA (calidad, pruebas, entornos, despliegue) no tienen
responsable único: se reparten de forma equitativa entre los tres miembros,
con independencia de su rol principal. En la planificación de cada sprint,
cada tarea de DevOps/QA se asigna a una persona concreta, de modo que los
tres tengan una carga similar; el reparto queda anotado en el acta.

## Acuerdos

### Comunicación entre el equipo

1. Cada miembro revisa WhatsApp y Discord al menos una vez cada día
   laborable; si no puede hacerlo, lo avisa según el acuerdo 8.
2. Los mensajes que requieran respuesta se contestan en un plazo máximo de
   24 horas en días laborables y de 48 horas en fin de semana.
3. WhatsApp es el canal asíncrono principal (avisos, dudas rápidas y
   daily). Discord es el canal de conversación técnica extensa, organizado
   por temas (general, dudas, recursos, repositorio), y el canal de voz
   para reuniones síncronas cuando no es posible quedar presencialmente.

### Reuniones

4. Reunión semanal fija los miércoles (inicio de cada sprint), de 16:00 a
   16:45, presencial o por Discord: 30 minutos de Sprint Planning (objetivo
   del sprint y reparto de tareas) y 15 minutos de refinamiento del backlog,
   que facilita el Scrum Master. El Scrum Master redacta el acta (asistentes,
   decisiones y acciones con responsable) y la sube a la Wiki el mismo día.
5. Daily asíncrono: cada día laborable, antes de las 22:00, cada miembro
   publica en WhatsApp qué hizo, qué hará y si tiene bloqueos, sin
   necesidad de reunión en vivo salvo bloqueo importante.
6. Al cierre de cada release (cada 2 semanas, el martes de IT-002, IT-004 e
   IT-006) se hace, de 17:00 a 17:50, una Sprint Review de 25 minutos, en la
   que se muestra el incremento frente a sus criterios de aceptación, y una
   retrospectiva de 25 minutos, de la que salen 1 o 2 acciones de mejora,
   cada una con responsable y fecha. Además, la sesión semanal de prácticas
   con el profesor incluye review, retrospectiva y planning; el Scrum Master
   recoge sus conclusiones en el acta de la Wiki.

### Horarios, disponibilidad y ausencias

7. Cada miembro se compromete a estar disponible en el horario que registre
   en el cálculo de capacidad del equipo (página «Capacidad del equipo» de
   la Wiki) y lo actualiza allí si cambia.
8. Una ausencia de 1 o 2 días se comunica por WhatsApp con al menos 24
   horas de antelación. Una ausencia de 3 días o más se comunica en cuanto
   se conozca, por WhatsApp y directamente al Scrum Master, indicando la
   duración estimada; el equipo redistribuye sus tareas mientras dure.

### Decisiones

9. Las decisiones de producto y alcance (visión, prioridad, criterios de
   aceptación) las adopta el Product Owner, escuchando antes al resto del
   equipo.
10. Las decisiones técnicas (arquitectura, dependencias, tecnología) las
    adopta el Tech Lead, en consenso con quien vaya a implementar la tarea.
11. Toda decisión relevante se registra por escrito el mismo día en que se
    toma (Wiki del repositorio o el documento afectado), indicando el
    motivo y quién decidió.

### Conflictos y feedback

12. Los desacuerdos técnicos se resuelven primero por discusión directa
    entre las partes implicadas; si no hay acuerdo en 48 horas, decide el
    Tech Lead (temas técnicos) o el Product Owner (temas de producto).
13. El feedback sobre el trabajo de un compañero se da de forma directa y
    respetuosa, en el canal del equipo o en la revisión de la pull request, en un
    plazo máximo de 48 horas desde que se detecta, indicando qué cambiar y por qué;
    nunca a sus espaldas. En cada retrospectiva, cada miembro aporta al menos un
    aspecto a mantener y otro a mejorar.

### Calidad, trabajo en curso, herramientas y documentación

14. Cada tarea sigue un estado visible en el tablero del equipo (GitHub
    Projects hasta que se adopte Jira): Por hacer / En curso / Revisión /
    Hecho. El límite de trabajo en curso es de una tarea principal activa
    por persona.
15. Ningún cambio se integra en `main` sin una pull request revisada y aprobada
    por un miembro distinto de su autor (0 *push* directos a `main`), y ninguna
    historia se da por terminada sin cumplir la Definition of Done
    (`docs/quality/definition-of-done.md`) y los principios de `constitution.md`.
16. El entorno oficial para compartir documentación e información del
    proyecto es el repositorio de GitHub (código, documentos `.md` y
    Wiki); WhatsApp y Discord se usan para comunicación del día a día, no
    para almacenar documentación oficial del proyecto.

### Comunicación con el profesorado y señales de alerta

17. La comunicación con el profesorado de la asignatura se realiza a
    través del Aula Virtual y del correo institucional; cualquier
    incidencia grave del equipo que no se resuelva internamente (abandono
    de un miembro, conflicto no resuelto) también se traslada por ese
    canal.
18. **Retraso o bloqueo.** Si una tarea lleva más de 2 días en «En curso»
    sin avance visible, o un miembro está bloqueado más de 24 horas, lo
    indica en el daily. Ese mismo día el Scrum Master designa quién ayuda
    (el Tech Lead si es técnico, el Product Owner si es de alcance) y, si
    peligra el objetivo del sprint, el Product Owner decide si se
    replanifica.
19. **Incumplimiento.** Si un miembro incumple un acuerdo de este charter
    dos veces en un mismo sprint, se trata en la siguiente reunión del
    miércoles y se registra en la Wiki una acción con responsable y fecha;
    si se repite en el sprint siguiente, se aplica el acuerdo 20.
20. **Ausencia o falta de implicación.** Si un miembro no se conecta ni
    responde durante más de 4 días seguidos sin haber avisado, no se
    implica de forma sostenida a lo largo de varias semanas, o abandona el
    proyecto a mitad de curso, el Scrum Master lo contacta primero de
    forma directa; si no hay respuesta en 48 horas adicionales, el equipo
    lo registra en la Wiki, redistribuye sus tareas entre los otros dos
    miembros y lo comunica al profesorado por el canal del acuerdo 17.

## Historial de versiones

| Versión | Fecha | Cambio | Decisión |
|---|---|---|---|
| 1.0 | 30/09/2026 | Versión inicial. | DEC-002, DEC-003 |
| 1.1 | 10/10/2026 | Correcciones de la auditoría del Hito 1: señales de alerta (acuerdos 18 y 19), fusión de acuerdos para no superar los 20 y concreción del daily, las ausencias, el tablero y las actas. | DEC-017 |
| 1.2 | 13/10/2026 | Acuerdos 4 y 6 alineados con la capacidad y con *Proceso de desarrollo* (Wiki); acuerdos 13 y 15 reformulados para que sean medibles. | DEC-019 |
| 1.3 | 05/11/2026 | Acuerdos 4 y 6 con día y hora fijos (planning los miércoles de 16:00 a 16:45; review y retro de 25 min cada una los martes de 17:00 a 17:50 al cerrar cada release), igual que el Excel v03 y ProjectLibre. | DEC-025 |

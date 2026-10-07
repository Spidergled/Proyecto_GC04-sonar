# ErasmusNest — Definition of Ready (DoR)

La Definition of Ready responde a la pregunta: **¿tenemos información suficiente para aceptar esta historia como candidata a entrar en un sprint?**

No exige saberlo todo, sino lo suficiente para asumir el trabajo con una expectativa razonable de terminarlo dentro del sprint. El Product Owner comprueba esta lista en el refinamiento. Si una historia no la cumple, no entra en el Sprint Planning.

## Identificación y trazabilidad

- [ ] La historia tiene un identificador único y estable (`US-XXX`) que coincide en el repositorio, el Excel, ProjectLibre y Jira.
- [ ] La historia está asociada a una épica (`EP-XXX`) y está escrita en su `spec.md`.
- [ ] La historia se puede trazar hasta al menos un objetivo de `goals.md` (`G-XX`) y un elemento de alcance (`S-XX`).

## Valor y alcance

- [ ] El tipo de usuario está identificado (estudiante oferente, estudiante demandante, Delegación, evaluador).
- [ ] La capacidad esperada está descrita en formato *Como… quiero… para…*.
- [ ] El valor o beneficio se entiende sin explicaciones adicionales.
- [ ] Los límites de la historia están claros y no invade ninguna exclusión de `goals.md` (EX-01…EX-05).

## Aceptación

- [ ] Tiene al menos dos criterios de aceptación (`US-XXX-AC-NN`).
- [ ] Los criterios son observables y verificables con datos sintéticos.
- [ ] Los criterios describen comportamiento, no una implementación concreta.
- [ ] Están identificadas las reglas de negocio (`RN-XX`) que aplican.

## Planificación

- [ ] Tiene prioridad MoSCoW asignada y justificada (valor, riesgo, dependencias).
- [ ] Tiene una estimación en horas-persona consensuada en Planning Poker.
- [ ] La estimación cabe en **un solo sprint**: no supera la capacidad efectiva del equipo en esa iteración (hoja *Capacidad* del Excel). Si la supera, se divide antes de entrar al sprint.
- [ ] El nivel de incertidumbre está indicado (Baja / Media / Alta). Una historia con incertidumbre Alta solo entra si se ha planificado cómo reducirla (spike, consulta o prototipo).
- [ ] Sus predecesoras están terminadas o planificadas en el mismo sprint antes que ella.

## Restricciones

- [ ] Están identificados los requisitos no funcionales que aplican (`NFR-XX`).
- [ ] Se conocen las restricciones técnicas, organizativas o del proyecto, incluido el uso exclusivo de datos sintéticos.
- [ ] Están disponibles, o hay un plan para preparar, los datos sintéticos necesarios para probarla.

## Cuestiones abiertas

- [ ] No queda ninguna duda que impida entender o planificar la historia.
- [ ] Cualquier supuesto o cuestión abierta no bloqueante está documentado en la historia.

# ErasmusNest — Definition of Done (DoD)

La Definition of Done responde a la pregunta: **¿qué condiciones mínimas debe cumplir cualquier historia para considerarla terminada?**

Una historia está **Hecha** cuando cumple todas las condiciones aplicables de esta lista. Si falta alguna, la historia vuelve al Product Backlog o se arrastra al siguiente sprint; no se acepta como hecha "a medias". Las tareas de DevOps/QA están repartidas entre los tres miembros (DEC-002), así que esta lista es la garantía común de calidad.

## Implementación

- [ ] La funcionalidad descrita en la historia está implementada.
- [ ] La implementación sigue la arquitectura y las convenciones técnicas acordadas por el Tech Lead.
- [ ] No queda ningún defecto crítico conocido sin resolver.

## Criterios de aceptación

- [ ] Se han verificado todos los criterios de aceptación de la historia (`US-XXX-AC-NN`).
- [ ] Hay evidencia de la verificación (captura, registro de prueba o resultado de test) enlazada desde la historia o la tarea.
- [ ] El comportamiento implementado coincide con la historia aprobada; cualquier desviación está registrada como decisión.

## Pruebas

- [ ] Se han ejecutado las pruebas automáticas o manuales relevantes.
- [ ] Las pruebas cubren el comportamiento esperado, los casos de error y los casos límite documentados.
- [ ] Las pruebas que ya existían siguen pasando.
- [ ] Todas las pruebas usan exclusivamente datos sintéticos.

## Integración

- [ ] El cambio está integrado en la rama `main` mediante una pull request.
- [ ] La pull request la ha revisado y aprobado al menos **otro miembro** del equipo, distinto de quien la implementó.
- [ ] La versión integrada se instala y arranca siguiendo el guion de instalación (G-04).
- [ ] El cambio no introduce regresiones críticas conocidas.

## Requisitos no funcionales

- [ ] Se han comprobado los `NFR-XX` aplicables a la historia.
- [ ] Las entradas del usuario se validan en el servidor (NFR-007).
- [ ] No se expone el correo ni otros datos de un usuario a quien no debe verlos (NFR-002).
- [ ] Las pantallas nuevas cumplen la accesibilidad básica acordada (NFR-004): etiquetas en formularios, contraste y uso con teclado.

## Documentación y trazabilidad

- [ ] Los commits y la pull request mencionan el identificador de la historia (`US-XXX`) o de la tarea (`TK-XXX`).
- [ ] La documentación técnica o de usuario está actualizada si procede, incluido el guion de instalación.
- [ ] Las decisiones o desviaciones relevantes están registradas en la Wiki.

## Revisión y aceptación

- [ ] Se ha demostrado la historia en la revisión con el entorno de demo.
- [ ] El Product Owner ha aceptado la historia frente a sus criterios de aceptación.
- [ ] No queda ninguna incidencia bloqueante abierta.

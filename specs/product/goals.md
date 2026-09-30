# ErasmusNest — Objetivos

## Objetivos

| ID | Objetivo | Resultado esperado |
|---|---|---|
| G-01 | Autonomía del estudiante | El estudiante puede completar el recorrido esencial (publicar un anuncio, o buscar uno y contactar) sin ayuda externa. |
| G-02 | Confianza institucional | Únicamente estudiantes verificados mediante correo institucional pueden publicar anuncios o contactar con otros usuarios. |
| G-03 | Claridad del estado | El estudiante sabe en todo momento si un anuncio sigue disponible o ya ha sido cerrado. |
| G-04 | Incremento revisable | Un tercero puede instalar, ejecutar y evaluar de manera estable el incremento desarrollado. |

## Indicadores de éxito

| ID | Objetivo | Meta |
|---|---|---|
| M-01 | G-01 | Al menos cuatro de cinco participantes completan el recorrido esencial (publicar, o buscar y contactar) en menos de cinco minutos y sin ayuda. |
| M-02 | G-02 | El 100 % de las cuentas de la demo quedan verificadas mediante correo institucional (sintético) antes de poder publicar o contactar. |
| M-03 | G-03 | Al menos cuatro de cinco participantes identifican correctamente si un anuncio está disponible o cerrado tras consultarlo. |
| M-04 | G-04 | Una persona que no haya participado en el desarrollo puede seguir el guion de instalación y evaluación sin intervenciones no documentadas. |
| M-05 | Todos | No existe ningún defecto bloqueante abierto durante la revisión final de cada release. |

## Alcance incluido

El proyecto incluye:

- **S-01:** registro y verificación de cuenta de estudiante mediante correo institucional (sintético).
- **S-02:** publicación de un anuncio de habitación (fechas de disponibilidad, zona, precio orientativo).
- **S-03:** búsqueda y filtrado de anuncios por fechas y zona.
- **S-04:** contacto o mensajería básica entre el estudiante oferente y el demandante, dentro de la plataforma.
- **S-05:** cierre manual de un anuncio cuando deja de estar disponible.
- **S-06:** un conjunto controlado de usuarios, habitaciones y anuncios completamente ficticios o sintéticos.

## Exclusiones

Quedan expresamente fuera del proyecto:

- **EX-01:** pasarela de pago real o gestión de transacciones económicas.
- **EX-02:** contratos legales de alquiler, firma digital o gestión documental del subarriendo.
- **EX-03:** aplicación móvil nativa (el producto se entrega como aplicación web).
- **EX-04:** verificación de identidad más allá del correo institucional (DNI, videollamada u otros medios).
- **EX-05:** integración con sistemas reales de la universidad (Aula Virtual, SIGMA u otros).

## Restricciones

El proyecto debe respetar las siguientes condiciones:

- Prohibición de utilizar datos personales, académicos o de contacto reales; solo datos ficticios o sintéticos.
- Plazo fijo de 6 semanas de ejecución real, repartidas en 3 releases de 2 semanas, con sprints semanales que comienzan los miércoles.
- Equipo de 3 personas (Hugo, Gled y José) con roles ya asignados (Product Owner, Scrum Master, Tech Lead), repartiendo las tareas de DevOps/QA entre los tres.
- Uso de las tecnologías y convenciones que se aprueben más adelante en la asignatura.

## Trazabilidad

La cadena de trazabilidad será:

**Misión → objetivos → épicas → historias → tareas → código y pruebas → evidencias**

Una épica que no contribuya a ningún objetivo se considera fuera del alcance hasta que se apruebe un cambio en este documento.

## Gestión de cambios

Cualquier modificación de un objetivo, una métrica, el alcance, una exclusión, un supuesto o una restricción requiere:

1. Revisión por parte del equipo.
2. Decisión explícita del Product Owner.
3. Registro de la decisión.
4. Revisión del roadmap, las especificaciones, Jira y la capacidad.
5. Actualización de todas las referencias afectadas.
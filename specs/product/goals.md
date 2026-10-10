# ErasmusNest — Objetivos

## Stakeholders

| ID | Stakeholder | Interés | Influencia |
|---|---|---|---|
| SH-01 | Delegación de Estudiantes UEx (cliente) | Disponer de una herramienta institucional que ordene el subarriendo temporal de habitaciones entre su alumnado y le sirva para evaluar y reforzar el apoyo a la movilidad. | Alta: valida el producto y acepta el incremento al cierre de cada release (SU-05). |
| SH-02 | Estudiantes oferentes | Subalquilar su habitación durante su periodo fuera a un estudiante verificado, sin depender de canales informales. | Media: sus necesidades guían las historias de publicación y cierre de anuncios, y participan en la medición de M-01 y M-03. |
| SH-03 | Estudiantes demandantes | Encontrar una habitación que encaje con sus fechas de estancia y contactar con confianza con quien la ofrece. | Media: sus necesidades guían las historias de búsqueda y contacto, y participan en la medición de M-01 y M-03. |
| SH-04 | Profesorado de la asignatura (revisor) | Comprobar que el proceso y los artefactos siguen la metodología de la asignatura y que el incremento es revisable. | Alta: revisa y evalúa cada entrega y, en el contexto de la asignatura, representa a la Delegación de Estudiantes (SU-05). |
| SH-05 | Equipo Practical Software (Hugo, Gled y José) | Entregar un incremento de calidad, trazable y dentro del plazo, aprendiendo el proceso de la asignatura. | Alta: decide la solución técnica, planifica y construye el producto; el Product Owner decide sobre los cambios de alcance. |

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

## Supuestos

El proyecto se planifica dando por ciertas las siguientes condiciones. Si alguna
deja de cumplirse, se tratará como un cambio según el apartado *Gestión de cambios*.

- **SU-01:** todo estudiante de la UEx dispone de un correo institucional con un dominio identificable, suficiente para acreditar su pertenencia a la universidad. En el proyecto se simula con cuentas de correo sintéticas.
- **SU-02:** el acuerdo económico y legal del subarriendo (pago, fianza, contrato) se cierra entre las partes fuera de la plataforma; ErasmusNest solo facilita el encuentro y el contacto (coherente con EX-01 y EX-02).
- **SU-03:** el estudiante que publica un anuncio cuenta con permiso del propietario del piso para subarrendar su habitación; la plataforma no lo comprueba y es responsabilidad del oferente.
- **SU-04:** los usuarios acceden a la plataforma desde un navegador web actual, en ordenador o en móvil.
- **SU-05:** la Delegación de Estudiantes (cliente) estará disponible para revisar y aceptar el incremento al cierre de cada release; en el contexto de la asignatura, este papel lo representa el profesorado.
- **SU-06:** será posible reunir al menos cinco participantes ajenos al desarrollo (por ejemplo, compañeros de otros grupos) para medir M-01, M-03 y M-04.
- **SU-07:** las tecnologías y convenciones que se aprueben en la asignatura permiten construir una aplicación web con autenticación y mensajería básica sin cambiar el alcance definido.
- **SU-08:** la disponibilidad de cada miembro se mantiene según lo registrado en el cálculo de capacidad; las ausencias conocidas ya están descontadas.

## Restricciones

El proyecto debe respetar las siguientes condiciones:

- Prohibición de utilizar datos personales, académicos o de contacto reales; solo datos ficticios o sintéticos.
- Plazo fijo de 6 semanas de ejecución real, repartidas en 3 releases de 2 semanas, con sprints semanales que comienzan los miércoles.
- Equipo de 3 personas (Hugo, Gled y José) con roles ya asignados (Product Owner, Scrum Master, Tech Lead), repartiendo las tareas de DevOps/QA entre los tres.
- Uso de las tecnologías y convenciones que se aprueben más adelante en la asignatura.

## Trazabilidad

La cadena de trazabilidad será:

**Misión → Objetivos (G-XX) → Épicas (EP-XXX) → Historias (US-XXX) → Plan → Tareas (TK-XXX) → Código y Pruebas → Evidencias**

Las evidencias son una extensión de la cadena oficial: recogen la prueba de que cada historia se ha entregado y verificado.

Una épica que no contribuya a ningún objetivo se considera fuera del alcance hasta que se apruebe un cambio en este documento.

## Gestión de cambios

Cualquier modificación de un objetivo, una métrica, el alcance, una exclusión, un supuesto o una restricción requiere:

1. Revisión por parte del equipo.
2. Decisión explícita del Product Owner.
3. Registro de la decisión.
4. Revisión del roadmap, las especificaciones, Jira y la capacidad.
5. Actualización de todas las referencias afectadas.

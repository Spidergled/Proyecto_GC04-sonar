# ProjectLibre del Hito 4 (por sprints y por épicas)

Planificación viable del Hito 3 (DEC-020) trasladada a ProjectLibre, como pide el enunciado de la semana 4. Los XML se generaron el 14/10/2026 con `generar_xml.py` (preparado con IA, ver `docs/project/reviews/registro-ia.md`, §4) a partir del Excel de `main` y se comprobaron contra `docs/project/planning/excel-export-v02.md` (`main` en `91ffe9a`, PR #13): la v02 no cambia el reparto, las tarifas ni el coste. El 15/10/2026 Gled (TL) los abrió en ProjectLibre 1.9.3, los guardó como `.pod`, fijó la línea base y exportó el Gantt en PDF (PR #16). Las decisiones de modelado de abajo se aceptan en DEC-022 (Wiki).

## Ficheros

| Fichero | Qué es |
|---|---|
| `ErasmusNest_GC04_por_sprints.xml` | Planificación ordenada por sprints: IT-001…IT-006, cada uno con sus tareas MGT y sus historias, y los hitos de release |
| `ErasmusNest_GC04_por_epicas.xml` | Copia con la misma información en dos ramas: *Gestión (MGT)* por sprint e *Historias de usuario* por épica (EP-001…EP-005) |
| `ErasmusNest_GC04_por_sprints.pod` y `ErasmusNest_GC04_por_epicas.pod` | Proyectos de ProjectLibre guardados desde los XML, con la línea base fijada |
| `ErasmusNest_GC04_gantt_sprints.pdf` y `ErasmusNest_GC04_gantt_epicas.pdf` | Diagramas de Gantt exportados desde ProjectLibre |
| `generar_xml.py` | Script que genera los dos XML a partir de las cifras del Excel |

Formato MS Project XML (MSPDI). ProjectLibre lo abre con *File → Open*. Después hay que guardarlo como `.pod` (*Save as*) y fijar la línea base (*Save Baseline*). Se ha probado en ProjectLibre 1.9.3: se importan las tareas, los recursos, las asignaciones y las dependencias.

## Qué contiene

- **Calendario:** «ErasmusNest (lunes a viernes)», 8:00–12:00 y 13:00–17:00. Cada sprint dura 5 días laborables, de miércoles a martes, con las fechas de la hoja *Capacidad* (IT-001 21/10 … IT-006 01/12). El **lunes 2/11/2026 es festivo en Extremadura**, porque Todos los Santos cae en domingo y se traslada (calendario de festivos de la Junta de Extremadura). Cae en IT-002 y **no está marcado como no laborable** (ver la decisión 5).
- **Recursos:** Hugo (PO, 35 €/h), Gled (TL, 40 €/h) y José (SM, 32 €/h). La capacidad máxima es del 12,5 %, que equivale a 5 h nominales por semana sobre una jornada de 40 h.
- **Tareas de gestión:** son 4 por sprint, con las horas de la hoja *Gestión* (6,46 h por sprint para el equipo):
  - *Sprint planning y refinamiento*: 0,75 h por persona.
  - *Daily asíncrona*: 0,4 h por persona.
  - *Sprint review y retrospectiva*: 0,42 h por persona, prorrateadas por semana.
  - *Coordinación del rol*: 0,5 h para Hugo y Gled, y 0,75 h para José.
- **Historias:** las 8 de la línea base, con las horas por persona de la hoja *Planificación*. Las tres que usan la excepción de DEC-018 se parten en dos tramos, uno por sprint: US-002, US-006 y US-017.
- **Dependencias:** las de la hoja *Dependencias* que afectan a la línea base. Entre sprints son fin-comienzo (FS). Cuando la predecesora está en el mismo sprint, son comienzo-comienzo (SS). Es el caso de US-001→US-002 (1/2), US-006 (2/2)→US-012, US-015→US-017 (1/2) y US-017 (2/2)→US-023. El orden dentro del sprint lo marcan las tareas TK.
- **Hitos:** R1 al final de IT-002, R2 al final de IT-004 y R3 (fin del proyecto) al final de IT-006.
- **Restricción:** cada tarea tiene «Comenzar no antes de» el miércoles de su sprint, porque los sprints son timebox fijos.

## Comprobado

| Comprobación | Resultado |
|---|---|
| Horas por persona, historia e iteración frente a la hoja *Planificación* (v02) | Coinciden todas (comprobado con un script contra la exportación) |
| Dependencias de la línea base frente a la hoja *Dependencias* | Están las 9, sin ninguna de más |
| Apertura en ProjectLibre 1.9.3 | Abre bien. US-001 tiene 5 h y 181,30 €, y US-002 (2/2) tiene 3,2 h y 117 €, igual que en el Excel |
| Trabajo total | 70,76 h = 32 h-p de desarrollo + 38,76 h de gestión (igual que la hoja *Gestión*) |
| Coste por persona | Hugo 802,20 €, Gled 872,80 €, José 832,64 € (igual que la hoja *Coste*) |
| Coste total | **2.507,64 €** (igual que el Excel) |
| Carga máxima por persona y semana | 4,72 h (José en IT-001), por debajo de las 5 h. No hay sobreasignación |
| Las dos versiones | Mismas tareas, horas, fechas y coste; solo cambia la agrupación |

## Decisiones de modelado (DEC-022)

1. **Todas las tareas duran el sprint completo (5 días).** Las horas reales son pocas (entre 0,3 y 2,4 h por persona y tarea), y con 5 h a la semana la capacidad de cada persona ronda 1 h al día. Si las ceremonias durasen 1 día, el miércoles ya superaría esa hora y ProjectLibre marcaría una sobreasignación que en realidad no existe, porque la capacidad del equipo es semanal. Repartiendo las horas por igual en el sprint, ProjectLibre compara la carga semanal con las 5 h, que es lo que también hace el Excel.
2. **Ausencias:** no van en el calendario de cada recurso, porque son de horas sueltas (por ejemplo, el parcial de Gled del 6/11, 1,5 h). Ya están descontadas en la capacidad efectiva del Excel y la carga asignada las respeta.
3. **Sin «Project grooming»:** el ejemplo del PDF lo incluye, pero el Excel no tiene horas para esa tarea. No se añade, para que ProjectLibre y el Excel tengan el mismo trabajo y el mismo coste. Las tareas de gestión de cada sprint sí están.
4. **Nivel de detalle:** el plan llega hasta las historias, como el ejemplo del PDF. Las tareas TK-001…TK-007 de IT-001 están en la hoja *Tareas IT-001* del Excel; las demás iteraciones se detallan en su Sprint Planning.
5. **Festivo del 2/11:** la capacidad del Excel son 5 h por persona y semana, y el festivo no la reduce porque el equipo no trabaja en jornada laboral. Por eso no se marca como no laborable. Si el equipo decide que sí la reduce, IT-002 pierde 1 h por persona: Hugo pasaría de 2,34 a 1,34 h-p con 2,1 asignadas y José de 2,41 a 1,41 h-p con 2,2 asignadas, y el plan dejaría de ser viable. Además, se probó a meter el festivo en el XML y **ProjectLibre 1.9.3 no lo importa bien**: deja IT-002 con 3 días y menos horas. Si se quiere marcar, hay que hacerlo a mano en ProjectLibre (*File → Calendar*) y ajustar las tareas de IT-002 a 4 días. Se deja como está y se explica en el informe del Trabajo 1.
6. **Moneda:** ProjectLibre muestra los importes con «$» aunque el XML indique euros. Los números son correctos y se aclara en el informe.

# ProjectLibre del Trabajo 1 (por sprints y por épicas)

Planificación del Trabajo 1 trasladada a ProjectLibre, como pide el enunciado de la semana 4. **Versión 3 (05/11/2026)**, rehecha tras la evaluación del profesor con las cifras de `docs/project/planning/excel-export-v03.md` (DEC-025): las reuniones aparecen en su día y hora, las historias tienen las fechas de la hoja *Planificación* y la reserva de imprevistos tiene coste. La versión 1 (15-16/10/2026, DEC-022), hecha sobre la planificación viable del Hito 3 (DEC-020), está en el historial de git y en la etiqueta de la entrega original.

Los XML los genera `generar_xml.py` (preparado con IA y revisado por el equipo, ver `docs/project/reviews/registro-ia.md`) a partir de las cifras de la v03, que están en su bloque `PARÁMETROS`.

## Ficheros

| Fichero | Qué es |
|---|---|
| `ErasmusNest_GC04_por_sprints.xml` | Planificación ordenada por sprints: IT-001…IT-006, cada uno con su planning, sus historias, su gestión continua, su reserva y, en IT-002, IT-004 e IT-006, la review con la retro y el hito de release |
| `ErasmusNest_GC04_por_epicas.xml` | Copia con la misma información en dos ramas: *Gestión (MGT)* por sprint e *Historias de usuario* por épica (EP-001…EP-005) |
| `ErasmusNest_GC04_por_sprints.pod` y `ErasmusNest_GC04_por_epicas.pod` | Proyectos de ProjectLibre guardados desde los XML, con la línea base fijada |
| `ErasmusNest_GC04_gantt_sprints.pdf` y `ErasmusNest_GC04_gantt_epicas.pdf` | Diagramas de Gantt exportados desde ProjectLibre, con las columnas Trabajo y Coste |
| `generar_xml.py` | Script que genera los dos XML e imprime la comprobación frente al Excel |

Uso: `python generar_xml.py .` en esta carpeta. Formato MS Project XML (MSPDI). ProjectLibre lo abre con *File → Open*; después se guarda como `.pod` (*Save as*), se fija la línea base (*Save Baseline*) y se exporta el Gantt.

## Qué contiene

- **Calendario del proyecto:** todos los días de 16:00 a 20:00, porque las fechas de la hoja *Planificación* incluyen fines de semana. La franja dice **cuándo** puede trabajar el equipo, no cuánto: el límite es la dedicación semanal de cada persona, que comprueban el Excel y el script. Cada sprint va de miércoles a martes (IT-001 21/10 … IT-006 01/12).
- **Recursos:** Hugo (PO, 35 €/h, 7 h/semana), Gled (TL, 40 €/h, 7 h/semana) y José (SM, 32 €/h, 9 h/semana), al **100 %**: 1 h del Gantt es 1 h real. Cada uno tiene su calendario con sus ausencias declaradas (las horas de la ausencia se quitan de su franja de ese día).
- **Reuniones, en su día y hora:**
  - *Sprint planning y refinamiento*: cada miércoles, 16:00–16:45, los tres (acuerdo 4).
  - *Sprint review y retrospectiva*: martes 17:00–17:50, solo en IT-002, IT-004 e IT-006, al cerrar cada release (acuerdo 6). Ya no se prorratea por semana.
- **Gestión continua:** *Daily asíncrona* (0,4 h por persona y sprint, por WhatsApp) y *Coordinación del rol* (0,5 h por persona y sprint) van desde el final del planning hasta el final del sprint (o hasta la review), porque no son reuniones sino unos minutos de trabajo al día.
- **Reserva de imprevistos:** una tarea por sprint con las horas del % de imprevistos de cada persona (hoja *Gestión*). Tiene coste para que el presupuesto de ProjectLibre incluya la reserva, igual que la hoja *Coste*.
- **Historias:** las 18 de la línea base Must + Should, 3 por sprint, con las horas por persona y las fechas de inicio y fin de la hoja *Planificación*. Ninguna dura el sprint entero ni ocupa dos sprints. Si empiezan el miércoles, empiezan a las 16:45, después del planning.
- **Dependencias:** todas fin-comienzo. Planning → historias del sprint que no tienen otra predecesora en él → review y retro → hito de release. Entre historias, las de la hoja *Dependencias*.
- **Hitos:** R1 al final de IT-002, R2 al final de IT-004 y R3 (fin del proyecto) al final de IT-006, cada uno tras la review de su sprint.
- **Restricción:** cada tarea tiene «Comenzar no antes de» su fecha, porque los sprints son timebox fijos. Las tareas son de duración fija, para que las fechas sean las del Excel.

## Comprobado

Con `python generar_xml.py` (05/11/2026), frente a `excel-export-v03.md`:

| Comprobación | Resultado |
|---|---|
| Trabajo total | 123,68 h = 69 h-p de desarrollo + 37,2 h de gestión + 17,48 h de reserva |
| Coste de desarrollo | 2.405,20 € (igual que el Excel) |
| Coste de gestión | 1.326,80 € (igual que el Excel) |
| Coste de la reserva de imprevistos | 636,32 € (igual que el Excel) |
| **Coste total con reserva** | **4.368,32 €** (igual que el Excel) |
| Carga semanal por persona (con ausencias) | Máximo: Hugo 6,69 de 7 h, Gled 6,71 de 7 h y José 8,79 de 9 h. Nadie supera su dedicación |
| Sobreasignación instantánea | Ninguna: solo las reuniones llegan al 100 % y en ellas no hay otra tarea en marcha |
| Predecesoras | Todas terminan antes de que empiece la historia que depende de ellas |
| Las dos versiones | Mismas tareas, horas, fechas y coste (4.368,32 €); solo cambia la agrupación |
| Apertura en ProjectLibre 1.9.3 | Hecho (05/11/2026): ambos XML importados, guardados como `.pod` con línea base y Gantt exportados a PDF. Costes por iteración iguales al Excel y ningún recurso sobreasignado |

## Decisiones de modelado

1. **Duración real, no del sprint entero (sustituye a la decisiones 2, 4 y 5 de DEC-022).** Las reuniones duran lo que duran, en su día y hora, y las historias tienen las fechas de la hoja *Planificación*. Los recursos están al 100 % con una franja diaria en la que pueden trabajar; la dedicación semanal se comprueba en el Excel y en el script, porque ProjectLibre no tiene un límite semanal de horas.
2. **Ausencias** en el calendario de cada persona: se quita de su franja del día el número de horas de la ausencia. La de Gled del 28/11 es sábado y también se descuenta.
3. **Daily y coordinación repartidas en el sprint:** son asíncronas o continuas, no reuniones. ProjectLibre 1.9.3 no importa tareas periódicas desde XML.
4. **Reserva de imprevistos como tarea con coste**, una por sprint, en vez de quedar solo descontada de la capacidad.
5. **Sin «Project grooming»:** el Excel no tiene horas para esa tarea; no se añade para que ProjectLibre y el Excel tengan el mismo trabajo y el mismo coste. El refinamiento del backlog va dentro de la reunión del miércoles.
6. **Nivel de detalle:** hasta las historias, como el ejemplo del enunciado. Las tareas TK-001…TK-013 de IT-001 están en la hoja *Tareas IT-001* del Excel.
7. **Festivo del 2/11:** no se marca como no laborable. La capacidad es semanal y US-009 termina ese día según la hoja *Planificación*.
8. **Moneda:** ProjectLibre muestra los importes con «$» aunque el XML indique euros. Los números son correctos.

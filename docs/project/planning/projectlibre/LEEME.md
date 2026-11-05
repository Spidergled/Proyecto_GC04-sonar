# ProjectLibre del Trabajo 1 (por sprints y por épicas)

Planificación del Trabajo 1 trasladada a ProjectLibre, como pide el enunciado de la semana 4. **Versión 2 (noviembre de 2026)**, rehecha tras la evaluación del profesor: las reuniones aparecen en su día y hora, las historias duran lo que dicen sus horas y la reserva de imprevistos tiene coste. La versión 1 (15-16/10/2026, DEC-022) está en el historial de git y en la etiqueta de la entrega original.

Los XML los genera `generar_xml.py` (preparado con IA y revisado por el equipo, ver `docs/project/reviews/registro-ia.md`) a partir de las cifras del Excel. Las decisiones de modelado de abajo sustituyen a la decisión 1 de DEC-022 y se recogen en la DEC de las mejoras tras la evaluación (Wiki).

> **Pendiente de cerrar:** la dedicación real de cada persona, la hora de las reuniones y el reparto de historias de la exportación `excel-export-v03.md`. Están en el bloque `PARÁMETROS` del script. Hasta entonces, los XML, los `.pod` y los PDF de esta carpeta siguen siendo los de la versión 1.

## Ficheros

| Fichero | Qué es |
|---|---|
| `ErasmusNest_GC04_por_sprints.xml` | Planificación ordenada por sprints: IT-001…IT-006, cada uno con sus reuniones, su gestión, su reserva, sus historias y, cada dos sprints, el hito de release |
| `ErasmusNest_GC04_por_epicas.xml` | Copia con la misma información en dos ramas: *Gestión (MGT)* por sprint e *Historias de usuario* por épica (EP-001…EP-005) |
| `ErasmusNest_GC04_por_sprints.pod` y `ErasmusNest_GC04_por_epicas.pod` | Proyectos de ProjectLibre guardados desde los XML, con la línea base fijada |
| `ErasmusNest_GC04_gantt_sprints.pdf` y `ErasmusNest_GC04_gantt_epicas.pdf` | Diagramas de Gantt exportados desde ProjectLibre, con las columnas Trabajo y Coste |
| `generar_xml.py` | Script que genera los dos XML a partir de las cifras del Excel e imprime la comprobación |

Uso: `python generar_xml.py .` en esta carpeta. Formato MS Project XML (MSPDI). ProjectLibre lo abre con *File → Open*; después se guarda como `.pod` (*Save as*), se fija la línea base (*Save Baseline*) y se exporta el Gantt.

## Qué contiene

- **Calendario del proyecto:** de lunes a viernes, en la franja real del equipo (desde las 16:00, pendiente de confirmar). Cada día dura lo que la dedicación diaria más alta del equipo. Cada sprint va de miércoles a martes, con las fechas de la hoja *Capacidad* (IT-001 21/10 … IT-006 01/12).
- **Recursos:** Hugo (PO, 35 €/h), Gled (TL, 40 €/h) y José (SM, 32 €/h), al **100 %** y cada uno con su calendario: su franja diaria es su dedicación semanal entre 5 días. Así, 1 h del Gantt es 1 h real y ProjectLibre detecta las sobreasignaciones de verdad. Las ausencias con fecha son días no laborables en el calendario de cada persona.
- **Reuniones, en su día y hora:**
  - *Sprint planning y refinamiento*: miércoles, primer día del sprint, 45 min para los tres (acuerdo 4).
  - *Sprint review y retrospectiva*: una sola tarea de 50 min para los tres, el martes final de IT-002, IT-004 e IT-006, porque se celebran cada dos semanas (acuerdo 6). Ya no se prorratean por semana.
- **Gestión continua:** *Daily asíncrona* (0,4 h por persona y sprint, por WhatsApp) y *Coordinación del rol* se reparten a lo largo del sprint porque no son reuniones sino trabajo de unos minutos al día.
- **Reserva de imprevistos:** una tarea por sprint con las horas del % de imprevistos de cada persona (DEC-015). Tiene coste para que el presupuesto de ProjectLibre incluya la reserva, igual que la hoja *Coste*.
- **Historias:** con las horas por persona de la hoja *Planificación*. Cada historia dura los días que necesitan sus responsables con las horas que les quedan libres cada día, no el sprint entero.
- **Dependencias:** todas fin-comienzo. Planning → historias del sprint (las que no tienen otra predecesora en el sprint) → review y retro → hito de release. Entre historias, las de la hoja *Dependencias*; las que antes eran comienzo-comienzo dentro del sprint pasan a fin-comienzo.
- **Hitos:** R1 al final de IT-002, R2 al final de IT-004 y R3 (fin del proyecto) al final de IT-006, cada uno tras la review de su sprint.
- **Restricción:** cada tarea tiene «Comenzar no antes de» su fecha calculada, porque los sprints son timebox fijos.

## Comprobado

El script imprime al ejecutarse el trabajo, el coste por persona, la carga por persona y día frente a su franja y las historias que no caben en su sprint. Se rellena con la v03:

| Comprobación | Resultado |
|---|---|
| Horas por persona, historia e iteración frente a la hoja *Planificación* (v03) | Pendiente |
| Trabajo total = desarrollo + gestión + reserva | Pendiente |
| Coste por persona y total frente a la hoja *Coste* (con reserva) | Pendiente |
| Sobreasignación por persona y día en ProjectLibre (*Uso de recursos*) | Pendiente |
| Las dos versiones: mismas tareas, horas, fechas y coste | Pendiente |

Prueba hecha el 02/11/2026 con las cifras de la versión 1 (5 h/semana por persona y el reparto de la v02): coste **2.857,96 €** = 2.507,64 € + 350,32 € de reserva (Hugo 123,06 €, Gled 175,80 €, José 51,46 €). Con las reuniones en su día real, **ese reparto no cabe**: hay sobreasignación los miércoles y martes de reunión y cinco historias (US-002 parte 1, US-003, US-012, US-017 parte 1 y US-023) no terminan dentro de su sprint. Con 5 h a la semana, la media semanal cuadraba solo porque la review se prorrateaba. Con dedicaciones de 7, 8 y 9 h (propuesta del 02/11, pendiente de confirmar) el mismo reparto cabe sin sobreasignaciones y las historias duran de 2 a 3 días.

## Decisiones de modelado

1. **Duración real, no del sprint entero (sustituye a la decisión 1 de DEC-022).** Calendario con la franja real del equipo y recursos al 100 %. Las reuniones duran lo que duran, en su día; las historias, los días que necesitan. ProjectLibre reparte las horas de cada persona por igual en los días de la tarea (contorno plano), y el script busca el primer tramo de días en que eso cabe sin sobreasignar.
2. **Ausencias como días no laborables** en el calendario de cada persona, porque con franjas de 1-2 h al día cada ausencia (1-1,5 h) ocupa el día entero. La del 28/11 (Gled) es sábado y no resta ningún día del calendario.
3. **Daily y coordinación repartidas en el sprint:** son asíncronas o continuas, no reuniones. La daily, como tarea periódica, no se importa desde XML en ProjectLibre 1.9.3.
4. **Reserva de imprevistos como tarea con coste**, una por sprint, en vez de quedar solo descontada de la capacidad.
5. **Sin «Project grooming»:** como en la versión 1, el Excel no tiene horas para esa tarea; no se añade para que ProjectLibre y el Excel tengan el mismo trabajo y el mismo coste.
6. **Nivel de detalle:** hasta las historias, como el ejemplo del enunciado. Las tareas TK de IT-001 están en la hoja *Tareas IT-001* del Excel.
7. **Festivo del 2/11:** el parámetro `MARCAR_FESTIVO` decide si se marca como no laborable (pendiente del equipo). No coincide con ninguna reunión.
8. **Moneda:** ProjectLibre muestra los importes con «$» aunque el XML indique euros. Los números son correctos.

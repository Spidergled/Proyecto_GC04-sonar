"""Genera los dos XML de ProjectLibre (por sprints y por épicas) a partir de las cifras del Excel.

Versión 2 (02/11/2026), tras la evaluación del Trabajo 1:
  - Calendario con la franja real del equipo (no 8:00-17:00) y recursos al 100 %:
    1 h del Gantt es 1 h real de trabajo.
  - Reuniones como tareas puntuales en su día y hora: planning y refinamiento el miércoles
    (primer día del sprint) y review con retro el martes final, solo cada 2 semanas.
  - Historias con su duración real, calculada con las horas libres de cada persona día a día,
    y dependencias fin-comienzo dentro del sprint.
  - Tarea «Reserva de imprevistos» por sprint, para que el coste incluya la reserva.

Uso:  python generar_xml.py <carpeta de salida>
Imprime una comprobación (carga por persona y día, trabajo y coste) y avisa si algo no cabe.

Lo que hay que cambiar tras la reunión del equipo y la exportación v03 del Excel está en el
bloque PARÁMETROS.
"""
import datetime as dt, sys
from xml.sax.saxutils import escape

OUT = sys.argv[1] if len(sys.argv) > 1 else '.'

# ============================ PARÁMETROS ============================
H, G, J = 'Hugo', 'Gled', 'José'
RES = [(1, H, 'Product Owner', 35), (2, G, 'Tech Lead', 40), (3, J, 'Scrum Master', 32)]  # uid, nombre, rol, €/h

# Horas nominales por semana (hoja Capacidad). PENDIENTE: dedicación real acordada en la reunión.
NOMINAL = {H: 5, G: 5, J: 5}
# Coordinación propia del rol (h/semana) y % de imprevistos individual (hoja Capacidad, DEC-015).
COORD = {H: 0.5, G: 0.5, J: 0.75}
IMPREV = {H: 0.20, G: 0.25, J: 0.10}
# Ausencias con fecha (hoja Capacidad): (persona, fecha, horas).
AUSENCIAS = [(H, dt.date(2026, 11, 5), 1.5), (H, dt.date(2026, 11, 20), 1.0),
             (G, dt.date(2026, 11, 6), 1.5), (G, dt.date(2026, 11, 28), 1.0),
             (J, dt.date(2026, 11, 12), 1.0)]

# Franja diaria del equipo: empieza a esta hora y dura NOMINAL/5 h para cada persona.
# PENDIENTE: hora real acordada en la reunión.
FRANJA_INICIO = dt.time(16, 0)

# Reuniones (team charter): horas por persona.
PLANNING_H = 0.75        # planning 30 min + refinamiento 15 min, cada miércoles (acuerdos 4)
REVIEW_H = 0.84          # review 20 min + retro 30 min, cada 2 semanas (acuerdo 6)
REVIEW_SPRINTS = {1, 3, 5}  # índices: IT-002, IT-004 e IT-006 (último día del sprint)
DAILY_H = 0.4            # daily asíncrona por WhatsApp, ≈5 min al día (acuerdo 5)

# Festivo del 2/11/2026 (Extremadura, traslado de Todos los Santos), cae en IT-002.
# PENDIENTE de decisión del equipo: True lo marca como no laborable.
MARCAR_FESTIVO = False
HOLIDAYS = {dt.date(2026, 11, 2)} if MARCAR_FESTIVO else set()

EST = {'US-001': 5, 'US-002': 5, 'US-003': 3, 'US-006': 5, 'US-012': 3, 'US-015': 3, 'US-017': 5, 'US-023': 3}
EPICS = {'EP-001': 'Cuenta y verificación', 'EP-002': 'Publicación de anuncios', 'EP-003': 'Búsqueda de habitaciones',
         'EP-004': 'Contacto y mensajería', 'EP-005': 'Entrega y demo'}
SPR = [('IT-001', 'R1'), ('IT-002', 'R1'), ('IT-003', 'R2'), ('IT-004', 'R2'), ('IT-005', 'R3'), ('IT-006', 'R3')]
START0 = dt.date(2026, 10, 21)
# Historias (o tramos) en el orden en que se hacen dentro del sprint.
# key, US, épica, título, índice de sprint, {persona: h}, predecesoras (keys; todas fin-comienzo).
# PENDIENTE: sustituir por el reparto de la exportación v03 cuando Hugo la tenga.
US = [
 ('US-001', 'US-001', 'EP-001', 'Registrar cuenta con correo institucional', 0, {H: 1.5, G: 2.1, J: 1.4}, []),
 ('US-002a', 'US-002', 'EP-001', 'Verificar la cuenta mediante el correo institucional (parte 1 de 2)', 0, {H: 0.8, J: 1.0}, ['US-001']),
 ('US-002b', 'US-002', 'EP-001', 'Verificar la cuenta mediante el correo institucional (parte 2 de 2)', 1, {H: 0.6, G: 1.6, J: 1.0}, ['US-002a']),
 ('US-003', 'US-003', 'EP-001', 'Iniciar y cerrar sesión', 1, {H: 1.5, G: 0.3, J: 1.2}, ['US-001']),
 ('US-006a', 'US-006', 'EP-002', 'Publicar el anuncio de mi habitación (parte 1 de 2)', 2, {H: 0.8, G: 0.6, J: 2.4}, ['US-002b', 'US-003']),
 ('US-006b', 'US-006', 'EP-002', 'Publicar el anuncio de mi habitación (parte 2 de 2)', 3, {H: 0.4, G: 0.8}, ['US-006a']),
 ('US-012', 'US-012', 'EP-003', 'Listar los anuncios disponibles', 3, {H: 1.6, G: 0.8, J: 0.6}, ['US-006b']),
 ('US-015', 'US-015', 'EP-003', 'Ver el detalle de un anuncio con su estado', 4, {H: 1.0, G: 0.6, J: 1.4}, ['US-012']),
 ('US-017a', 'US-017', 'EP-004', 'Contactar con el oferente desde un anuncio (parte 1 de 2)', 4, {H: 0.3, G: 1.5, J: 1.0}, ['US-015', 'US-002b']),
 ('US-017b', 'US-017', 'EP-004', 'Contactar con el oferente desde un anuncio (parte 2 de 2)', 5, {G: 1.1, J: 1.1}, ['US-017a']),
 ('US-023', 'US-023', 'EP-005', 'Guion de instalación y evaluación', 5, {H: 2.0, J: 1.0}, ['US-017b']),
]
RELEASE_OF = {1: 'R1', 3: 'R2', 5: 'R3'}
RELEASE_NAME = {'R1': 'Hito R1 · Release 1 entregada', 'R2': 'Hito R2 · Release 2 entregada',
                'R3': 'Hito R3 · Release 3 entregada (fin del proyecto)'}
# ====================================================================

PEOPLE = [r[1] for r in RES]
RUID = {r[1]: r[0] for r in RES}
RATE = {r[1]: r[3] for r in RES}
DAYH = {p: NOMINAL[p] / 5 for p in PEOPLE}          # horas de franja al día por persona
MAXD = max(DAYH.values())                           # franja del calendario del proyecto
CEREM_SEM = PLANNING_H + DAILY_H + REVIEW_H / 2     # ceremonias por semana (media), como la hoja Capacidad
RESERVA = {p: round(IMPREV[p] * (NOMINAL[p] - CEREM_SEM - COORD[p]), 4) for p in PEOPLE}  # h por sprint
EPS = 1e-6
# Ausencias en sábado o domingo no restan días: el calendario ya no trabaja ese día.
ABSENT_DAYS = {}
for _p, _f, _h in AUSENCIAS:
    if _f.weekday() < 5: ABSENT_DAYS.setdefault(_p, set()).add(_f)
warnings = []


def sprint_days(i):
    d = START0 + dt.timedelta(weeks=i); out = []
    while True:
        if d.weekday() < 5 and d not in HOLIDAYS: out.append(d)
        d += dt.timedelta(days=1)
        if d.weekday() == 2 and out: break
    return out


def at(day, hours):  # instante dentro de la franja del día
    return dt.datetime.combine(day, FRANJA_INICIO) + dt.timedelta(hours=hours)


# Las historias sin predecesora en su sprint empiezan después del planning (fin-comienzo).
for u in US:
    if not any(x[0] in u[6] and x[4] == u[4] for x in US):
        u[6].append(f'PLAN-{u[4]}')


# ---------- Planificación día a día ----------
# Cada sprint: [planning el día 0] [historias, daily, coordinación y reserva en los días de trabajo] [review el último día si toca]
SCHED = {}      # key -> dict(start, finish, asg{p:h}, units{p:u}, dur_h)
LOAD = {}       # (p, day) -> horas


def add_load(p, day, h):
    LOAD[(p, day)] = LOAD.get((p, day), 0) + h


for s, (it, rel) in enumerate(SPR):
    days = sprint_days(s)
    review = s in REVIEW_SPRINTS
    # Las historias se hacen después del planning y, si hay review, terminan antes de ella.
    work_days = days[:-1] if review else days
    # 1) planning
    SCHED[f'PLAN-{s}'] = dict(start=at(days[0], 0), finish=at(days[0], PLANNING_H), dur_h=PLANNING_H,
                              asg={p: PLANNING_H for p in PEOPLE}, units={p: 1.0 for p in PEOPLE})
    for p in PEOPLE: add_load(p, days[0], PLANNING_H)
    if review:
        SCHED[f'REV-{s}'] = dict(start=at(days[-1], 0), finish=at(days[-1], REVIEW_H), dur_h=REVIEW_H,
                                 asg={p: REVIEW_H for p in PEOPLE}, units={p: 1.0 for p in PEOPLE})
        for p in PEOPLE: add_load(p, days[-1], REVIEW_H)
    # 2) días en que trabaja cada persona: se quitan sus ausencias (día completo en su calendario,
    #    porque cada ausencia, de 1-1,5 h, ocupa toda su franja de ese día)
    avail = {p: [d for d in days if d not in ABSENT_DAYS.get(p, set())] for p in PEOPLE}
    # 3) tareas continuas (daily, coordinación y reserva): horas iguales en los días de trabajo de cada persona
    cont = {'DAILY': {p: DAILY_H for p in PEOPLE}, 'COORD': dict(COORD), 'RES': dict(RESERVA)}
    for k, asg in cont.items():
        SCHED[f'{k}-{s}'] = dict(start=at(days[0], PLANNING_H), finish=at(days[-1], MAXD),
                                 dur_h=len(days) * MAXD - PLANNING_H, asg=asg,
                                 units={p: asg[p] / (len(avail[p]) * DAYH[p]) for p in asg})
        for p, h in asg.items():
            for d in avail[p]: add_load(p, d, h / len(avail[p]))
    free = {p: {d: max(DAYH[p] - LOAD.get((p, d), 0), 0) if d in avail[p] else 0 for d in work_days} for p in PEOPLE}
    # 4) historias en orden. ProjectLibre reparte las horas de cada persona por igual entre sus días de
    #    trabajo de la tarea (contorno plano): se busca el primer tramo de días en que eso cabe.
    for u in [u for u in US if u[4] == s]:
        key, usid, ep, tit, _, asg, pred = u
        first = 0
        for pk in pred:
            if pk in SCHED and SCHED[pk]['finish'].date() in work_days:
                first = max(first, work_days.index(SCHED[pk]['finish'].date()))
        found = None
        for a0 in range(first, len(work_days)):
            for a1 in range(a0, len(work_days)):
                rng = work_days[a0:a1 + 1]
                days_p = {p: [d for d in rng if d in avail[p]] for p in asg}
                if all(days_p[p] and all(h / len(days_p[p]) <= free[p][d] + EPS for d in days_p[p]) for p, h in asg.items()):
                    found = (a0, a1); break
            if found: break
        ok = found is not None
        if not ok:
            found = (first, len(work_days) - 1)
            for p, h in asg.items():
                libre = sum(max(free[p][d], 0) for d in work_days[first:])
                warnings.append(f'{it}: {key} no cabe; {p} necesita {h:.2f} h y le quedan {libre:.2f} h libres en el sprint')
        a0, a1 = found; rng = work_days[a0:a1 + 1]
        days_p = {p: [d for d in rng if d in avail[p]] or rng for p in asg}
        for p, h in asg.items():
            for d in days_p[p]: free[p][d] -= h / len(days_p[p]); add_load(p, d, h / len(days_p[p]))
        off = PLANNING_H if rng[0] == days[0] else 0
        SCHED[key] = dict(start=at(rng[0], off), finish=at(rng[-1], MAXD), dur_h=len(rng) * MAXD - off, asg=asg,
                          units={p: h / (len(days_p[p]) * DAYH[p]) for p, h in asg.items()}, ok=ok)
    if review:
        SCHED[f'MS-{s}'] = dict(start=SCHED[f'REV-{s}']['finish'], finish=SCHED[f'REV-{s}']['finish'], dur_h=0, asg={}, units={})


# ---------- Árbol de tareas ----------
def node(name, kind, **kw):
    d = dict(name=name, kind=kind, children=[]); d.update(kw); return d


def us_task(u):
    k, usid, ep, tit, s, asg, pred = u
    return node(f'{usid} {tit}', 'task', key=k, pred=pred,
                note=f'{usid} · {ep} · {SPR[s][0]} · estimación total de la historia {EST[usid]} h-p (Planning Poker). '
                     f'Horas por persona de la hoja Planificación. Duración calculada con las horas libres de cada persona.')


def mgt_tasks(s):
    it = SPR[s][0]; us_keys = [u[0] for u in US if u[4] == s]
    t = [node(f'MGT {it} Sprint planning y refinamiento', 'task', key=f'PLAN-{s}', pred=[],
              note=f'Reunión del miércoles {sprint_days(s)[0]:%d/%m} a las {FRANJA_INICIO:%H:%M}: planning 30 min + refinamiento 15 min (team charter, acuerdo 4).'),
         node(f'MGT {it} Daily asíncrona', 'task', key=f'DAILY-{s}', pred=[f'PLAN-{s}'],
              note='≈5 min al día por WhatsApp (acuerdo 5). Es asíncrona: no es una reunión, por eso se reparte a lo largo del sprint.'),
         node(f'MGT {it} Coordinación del rol', 'task', key=f'COORD-{s}', pred=[f'PLAN-{s}'],
              note='Trabajo continuo de PO, TL y SM (hoja Capacidad): refinar backlog, revisar PR, seguir impedimentos.'),
         node(f'MGT {it} Reserva de imprevistos', 'task', key=f'RES-{s}', pred=[f'PLAN-{s}'],
              note='Horas del % de imprevistos individual (DEC-015). Solo se consumen si surge un imprevisto; tienen coste para que el presupuesto incluya la reserva.')]
    if s in REVIEW_SPRINTS:
        t.append(node(f'MGT {it} Sprint review y retrospectiva', 'task', key=f'REV-{s}', pred=us_keys,
                      note=f'Reunión del martes {sprint_days(s)[-1]:%d/%m} a las {FRANJA_INICIO:%H:%M}: review 20 min + retro 30 min, cada 2 semanas (acuerdo 6).'))
    return t


def milestone(s):
    r = RELEASE_OF[s]
    return node(RELEASE_NAME[r], 'ms', key=f'MS-{s}', pred=[f'REV-{s}'])




def tree(mode):
    root = node('ErasmusNest · Practical Software (GC04)', 'sum')
    if mode == 'sprints':
        for s, (it, rel) in enumerate(SPR):
            sp = node(f'{it} · {rel}', 'sum'); m = mgt_tasks(s)
            sp['children'] += m[:1] + [us_task(u) for u in US if u[4] == s] + m[1:]
            if s in REVIEW_SPRINTS: sp['children'].append(milestone(s))
            root['children'].append(sp)
    else:
        mg = node('Gestión (MGT)', 'sum')
        for s, (it, rel) in enumerate(SPR):
            sp = node(f'MGT {it} · {rel}', 'sum'); sp['children'] = mgt_tasks(s); mg['children'].append(sp)
        us = node('Historias de usuario', 'sum')
        for ep, name in EPICS.items():
            e = node(f'{ep} {name}', 'sum'); e['children'] = [us_task(u) for u in US if u[2] == ep]; us['children'].append(e)
        root['children'] += [mg, us] + [milestone(s) for s in sorted(REVIEW_SPRINTS)]
    return root


def pt(h):
    m = round(h * 3600); return f'PT{m // 3600}H{(m % 3600) // 60}M{m % 60}S'


def hm(h):
    m = round(h * 60); return f'{m // 60:02d}:{m % 60:02d}:00'


def build(mode, title):
    root = tree(mode); flat = []

    def walk(n, lvl, num):
        n['lvl'] = lvl; n['num'] = num; flat.append(n)
        for i, c in enumerate(n['children'], 1): walk(c, lvl + 1, f'{num}.{i}' if num else str(i))
    walk(root, 0, '')
    for i, n in enumerate(flat): n['uid'] = i
    keyuid = {n['key']: n['uid'] for n in flat if 'key' in n}
    for n in flat:
        if n['kind'] in ('task', 'ms'):
            sc = SCHED[n['key']]; n.update(start=sc['start'], finish=sc['finish'], dur_h=sc['dur_h'], asg=sc['asg'], units=sc['units'])
            n['work'] = sum(sc['asg'].values())

    def agg(n):
        if n['children']:
            for c in n['children']: agg(c)
            n['start'] = min(c['start'] for c in n['children']); n['finish'] = max(c['finish'] for c in n['children'])
            n['work'] = sum(c['work'] for c in n['children'])
            n['dur_h'] = 0
    agg(root)

    def cost(n):
        if n['kind'] == 'task': return sum(h * RATE[p] for p, h in n['asg'].items())
        return sum(cost(c) for c in n['children'])
    f = lambda t: t.strftime('%Y-%m-%dT%H:%M:%S')
    fin = (dt.datetime.combine(START0, FRANJA_INICIO) + dt.timedelta(hours=MAXD)).time()
    X = ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?>', '<Project xmlns="http://schemas.microsoft.com/project">',
         f'<Name>{escape(title)}</Name><Title>{escape(title)}</Title><Author>Practical Software</Author><Company>Practical Software</Company>',
         '<ScheduleFromStart>1</ScheduleFromStart>', f'<StartDate>{f(root["start"])}</StartDate><FinishDate>{f(root["finish"])}</FinishDate>',
         '<FYStartDate>1</FYStartDate><CriticalSlackLimit>0</CriticalSlackLimit><CurrencyDigits>2</CurrencyDigits><CurrencySymbol>€</CurrencySymbol><CurrencyCode>EUR</CurrencyCode><CurrencySymbolPosition>3</CurrencySymbolPosition>',
         f'<CalendarUID>1</CalendarUID><DefaultStartTime>{FRANJA_INICIO:%H:%M:%S}</DefaultStartTime><DefaultFinishTime>{fin:%H:%M:%S}</DefaultFinishTime>'
         f'<MinutesPerDay>{round(MAXD * 60)}</MinutesPerDay><MinutesPerWeek>{round(MAXD * 300)}</MinutesPerWeek><DaysPerMonth>20</DaysPerMonth>',
         '<DefaultTaskType>2</DefaultTaskType><DefaultFixedCostAccrual>3</DefaultFixedCostAccrual><DefaultStandardRate>0</DefaultStandardRate><DefaultOvertimeRate>0</DefaultOvertimeRate><DurationFormat>5</DurationFormat><WorkFormat>2</WorkFormat>',
         '<WeekStartDay>1</WeekStartDay><NewTasksEffortDriven>0</NewTasksEffortDriven><HonorConstraints>1</HonorConstraints>', '<Calendars>']

    def calendar(uid, name, hours, base, exceptions=()):
        c = [f'<Calendar><UID>{uid}</UID><Name>{escape(name)}</Name><IsBaseCalendar>{1 if base else 0}</IsBaseCalendar><BaseCalendarUID>{-1 if base else 1}</BaseCalendarUID><WeekDays>']
        end = (dt.datetime.combine(START0, FRANJA_INICIO) + dt.timedelta(hours=hours)).time()
        for wd in range(1, 8):
            if wd in (1, 7): c.append(f'<WeekDay><DayType>{wd}</DayType><DayWorking>0</DayWorking></WeekDay>')
            else: c.append(f'<WeekDay><DayType>{wd}</DayType><DayWorking>1</DayWorking><WorkingTimes><WorkingTime><FromTime>{FRANJA_INICIO:%H:%M:%S}</FromTime><ToTime>{end:%H:%M:%S}</ToTime></WorkingTime></WorkingTimes></WeekDay>')
        c.append('</WeekDays>')
        exc = [(h, 'Festivo en Extremadura') for h in sorted(HOLIDAYS)] if base else exceptions
        if exc:
            c.append('<Exceptions>' + ''.join(
                f'<Exception><EnteredByOccurrences>0</EnteredByOccurrences><TimePeriod><FromDate>{h}T00:00:00</FromDate><ToDate>{h}T23:59:00</ToDate></TimePeriod><Occurrences>1</Occurrences><Name>{escape(nm)}</Name><Type>1</Type><DayWorking>0</DayWorking></Exception>'
                for h, nm in exc) + '</Exceptions>')
        return ''.join(c) + '</Calendar>'
    X.append(calendar(1, f'ErasmusNest · franja del equipo ({FRANJA_INICIO:%H:%M}, L-V)', MAXD, True))
    for uid, name, rol, r in RES:
        X.append(calendar(10 + uid, f'{name} · {NOMINAL[name]} h/semana', DAYH[name], False,
                           [(d, 'Ausencia declarada (hoja Capacidad)') for d in sorted(ABSENT_DAYS.get(name, ()))]))
    X.append('</Calendars><Tasks>')
    for i, n in enumerate(flat):
        summ = 1 if n['kind'] == 'sum' else 0; ms = 1 if n['kind'] == 'ms' else 0
        X.append('<Task>' + f'<UID>{n["uid"]}</UID><ID>{i}</ID><Name>{escape(n["name"])}</Name><Type>{0 if ms or summ else 2}</Type><IsNull>0</IsNull>'
                 f'<WBS>{n["num"] or "0"}</WBS><OutlineNumber>{n["num"] or "0"}</OutlineNumber><OutlineLevel>{n["lvl"]}</OutlineLevel><Priority>500</Priority>'
                 f'<Start>{f(n["start"])}</Start><Finish>{f(n["finish"])}</Finish><Duration>{pt(n["dur_h"])}</Duration><DurationFormat>5</DurationFormat>'
                 f'<Work>{pt(n["work"])}</Work><Cost>{cost(n):.2f}</Cost><Milestone>{ms}</Milestone><Summary>{summ}</Summary><EffortDriven>0</EffortDriven><Estimated>0</Estimated>'
                 + (f'<ConstraintType>4</ConstraintType><ConstraintDate>{f(n["start"])}</ConstraintDate>' if n['kind'] == 'task' else '<ConstraintType>0</ConstraintType>')
                 + (f'<Notes>{escape(n["note"])}</Notes>' if n.get('note') else '')
                 + ''.join(f'<PredecessorLink><PredecessorUID>{keyuid[k]}</PredecessorUID><Type>1</Type><CrossProject>0</CrossProject><LinkLag>0</LinkLag><LagFormat>7</LagFormat></PredecessorLink>'
                           for k in n.get('pred', []) if k in keyuid)
                 + '</Task>')
    X.append('</Tasks><Resources>')
    for uid, name, rol, r in RES:
        X.append(f'<Resource><UID>{uid}</UID><ID>{uid}</ID><Name>{escape(name)}</Name><Type>1</Type><IsNull>0</IsNull><Initials>{name[0]}</Initials><Group>{rol}</Group>'
                 f'<MaxUnits>1.00</MaxUnits><StandardRate>{r}</StandardRate><StandardRateFormat>2</StandardRateFormat><OvertimeRate>0</OvertimeRate><OvertimeRateFormat>2</OvertimeRateFormat><CostPerUse>0</CostPerUse><AccrueAt>3</AccrueAt>'
                 f'<CalendarUID>{10 + uid}</CalendarUID><Notes>{escape(rol)} · {NOMINAL[name]} h/semana nominales ({DAYH[name]:g} h al día desde las {FRANJA_INICIO:%H:%M}) · tarifa {r} €/h</Notes></Resource>')
    X.append('</Resources><Assignments>')
    a = 1
    for n in flat:
        if n['kind'] != 'task': continue
        for p, h in n['asg'].items():
            X.append(f'<Assignment><UID>{a}</UID><TaskUID>{n["uid"]}</TaskUID><ResourceUID>{RUID[p]}</ResourceUID><Units>{min(n["units"][p], 1):.6f}</Units>'
                     f'<Work>{pt(h)}</Work><RegularWork>{pt(h)}</RegularWork><RemainingWork>{pt(h)}</RemainingWork><Cost>{h * RATE[p]:.2f}</Cost>'
                     f'<Start>{f(n["start"])}</Start><Finish>{f(n["finish"])}</Finish><WorkContour>0</WorkContour></Assignment>'); a += 1
    X.append('</Assignments></Project>')
    return '\n'.join(X), root, cost(root)


xs, root_s, cost_s = build('sprints', 'ErasmusNest GC04 · Planificación por sprints')
xe, root_e, cost_e = build('epicas', 'ErasmusNest GC04 · Planificación por épicas')
open(f'{OUT}/ErasmusNest_GC04_por_sprints.xml', 'w', encoding='utf-8').write(xs)
open(f'{OUT}/ErasmusNest_GC04_por_epicas.xml', 'w', encoding='utf-8').write(xe)

# ---------- Comprobación ----------
dev = sum(sum(u[5].values()) for u in US)
res = sum(RESERVA.values()) * len(SPR)
print(f'Trabajo total {root_s["work"]:.2f} h = desarrollo {dev:.2f} + gestión {root_s["work"] - dev - res:.2f} + reserva {res:.2f}')
print(f'Coste total {cost_s:.2f} € (por épicas {cost_e:.2f} €); reserva {sum(RESERVA[p] * RATE[p] for p in PEOPLE) * len(SPR):.2f} €')
for p in PEOPLE:
    print(f'Coste {p}: {sum(h * RATE[p] for k, sc in SCHED.items() for q, h in sc["asg"].items() if q == p):.2f} €')
over = [(p, d, h) for (p, d), h in sorted(LOAD.items(), key=lambda x: x[0][1]) if h > DAYH[p] + 1e-3]
print('Sobrecarga diaria:', 'ninguna' if not over else '')
for p, d, h in over: print(f'  {d:%a %d/%m} {p}: {h:.2f} h de {DAYH[p]:g}')
for k in [u[0] for u in US]:
    sc = SCHED[k]; print(f'  {k:8} {sc["start"]:%d/%m %H:%M} -> {sc["finish"]:%d/%m %H:%M}' + ('' if sc.get('ok', True) else '  NO CABE'))
print('Avisos:', 'ninguno' if not warnings else '')
for w in warnings: print('  -', w)

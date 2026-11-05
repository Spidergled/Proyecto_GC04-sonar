"""Genera los dos XML de ProjectLibre (por sprints y por épicas) a partir de la exportación del Excel.

Versión 3 (05/11/2026), con las cifras de excel-export-v03.md (DEC-025), tras la evaluación del Trabajo 1:
  - Reuniones como tareas puntuales en su día y hora: Sprint Planning + refinamiento el miércoles
    16:00-16:45 y review + retro el martes 17:00-17:50, solo en IT-002, IT-004 e IT-006.
  - Historias con las fechas de inicio y fin de la hoja Planificación (no duran el sprint entero).
  - Recursos al 100 % con un calendario de 1 h = 1 h real; la dedicación semanal de cada persona
    (7, 7 y 9 h) la comprueba el Excel y la vuelve a comprobar este script.
  - Tarea «Reserva de imprevistos» por sprint, para que el coste incluya la reserva.

Uso:  python generar_xml.py <carpeta de salida>
Imprime la comprobación (trabajo, coste y carga semanal por persona frente a su dedicación).
"""
import datetime as dt, sys
from xml.sax.saxutils import escape

OUT = sys.argv[1] if len(sys.argv) > 1 else '.'

# ============================ PARÁMETROS (excel-export-v03.md) ============================
H, G, J = 'Hugo', 'Gled', 'José'
RES = [(1, H, 'Product Owner', 35), (2, G, 'Tech Lead', 40), (3, J, 'Scrum Master', 32)]  # uid, nombre, rol, €/h

NOMINAL = {H: 7, G: 7, J: 9}          # h/semana (hoja Capacidad, v03)
COORD = {H: 0.5, G: 0.5, J: 0.5}      # coordinación propia del rol (h/semana)
IMPREV = {H: 0.20, G: 0.25, J: 0.10}  # % de imprevistos individual (DEC-015)
AUSENCIAS = [(H, dt.date(2026, 11, 5), 1.5), (H, dt.date(2026, 11, 20), 1.0),
             (G, dt.date(2026, 11, 6), 1.5), (G, dt.date(2026, 11, 28), 1.0),
             (J, dt.date(2026, 11, 12), 1.0)]

# Franja en la que el equipo puede trabajar, todos los días (las fechas de la hoja Planificación
# incluyen fines de semana). Es cuándo se puede trabajar, no cuánto: el límite es la dedicación semanal.
FRANJA = (dt.time(16, 0), dt.time(20, 0))

PLANNING = (dt.time(16, 0), dt.time(16, 45))  # miércoles: planning 30 min + refinamiento 15 min (acuerdo 4)
REVIEW = (dt.time(17, 0), dt.time(17, 50))    # martes: review 25 min + retro 25 min (acuerdo 6)
REVIEW_SPRINTS = {1, 3, 5}                    # IT-002, IT-004 e IT-006
DAILY_H = 0.4                                 # daily asíncrona por WhatsApp, ≈5 min al día (acuerdo 5)
CEREM_SEM = 0.5 + 0.25 + DAILY_H              # ceremonias semanales fijas (1,15 h)

# Festivo del 2/11/2026 (Extremadura). No se marca: la capacidad es semanal y US-009 termina ese día.
HOLIDAYS = set()

EPICS = {'EP-001': 'Cuenta y verificación', 'EP-002': 'Publicación de anuncios', 'EP-003': 'Búsqueda de habitaciones',
         'EP-004': 'Contacto y mensajería', 'EP-005': 'Entrega y demo'}
SPR = [('IT-001', 'R1'), ('IT-002', 'R1'), ('IT-003', 'R2'), ('IT-004', 'R2'), ('IT-005', 'R3'), ('IT-006', 'R3')]
START0 = dt.date(2026, 10, 21)
# Hoja Planificación (v03): ID, épica, título, sprint (0 = IT-001), inicio, fin, {persona: h-p}, predecesoras.
US = [
 ('US-001', 'EP-001', 'Registrar cuenta con correo institucional', 0, dt.date(2026, 10, 21), dt.date(2026, 10, 23), {H: 1.5, G: 2.1, J: 1.4}, []),
 ('US-002', 'EP-001', 'Verificar la cuenta mediante el correo institucional', 0, dt.date(2026, 10, 24), dt.date(2026, 10, 27), {H: 1.2, G: 1.5, J: 2.3}, ['US-001']),
 ('US-003', 'EP-001', 'Iniciar y cerrar sesión', 0, dt.date(2026, 10, 24), dt.date(2026, 10, 27), {H: 1.1, J: 1.9}, ['US-001']),
 ('US-004', 'EP-001', 'Gestionar el perfil básico', 1, dt.date(2026, 10, 28), dt.date(2026, 10, 30), {H: 1, G: 0.4, J: 1.6}, ['US-003']),
 ('US-006', 'EP-002', 'Publicar el anuncio de mi habitación', 1, dt.date(2026, 10, 28), dt.date(2026, 10, 31), {H: 1.2, G: 1.8, J: 2}, ['US-002', 'US-003']),
 ('US-009', 'EP-002', 'Cerrar mi anuncio', 1, dt.date(2026, 11, 1), dt.date(2026, 11, 2), {H: 1, G: 0.8, J: 1.2}, ['US-006']),
 ('US-012', 'EP-003', 'Listar los anuncios disponibles', 2, dt.date(2026, 11, 4), dt.date(2026, 11, 6), {H: 0.9, G: 0.6, J: 1.5}, ['US-006']),
 ('US-007', 'EP-002', 'Añadir fotos al anuncio', 2, dt.date(2026, 11, 4), dt.date(2026, 11, 8), {H: 0.7, G: 1.4, J: 2.9}, ['US-006']),
 ('US-010', 'EP-002', 'Ver mis anuncios y su estado', 2, dt.date(2026, 11, 7), dt.date(2026, 11, 10), {H: 0.8, G: 0.2, J: 2}, ['US-006']),
 ('US-013', 'EP-003', 'Filtrar anuncios por fechas de estancia', 3, dt.date(2026, 11, 11), dt.date(2026, 11, 14), {H: 1.3, G: 1.6, J: 2.1}, ['US-012']),
 ('US-014', 'EP-003', 'Filtrar anuncios por zona', 3, dt.date(2026, 11, 11), dt.date(2026, 11, 13), {H: 1, G: 0.6, J: 1.4}, ['US-012']),
 ('US-015', 'EP-003', 'Ver el detalle de un anuncio con su estado', 3, dt.date(2026, 11, 14), dt.date(2026, 11, 16), {H: 1, G: 0.9, J: 1.1}, ['US-012']),
 ('US-017', 'EP-004', 'Contactar con el oferente desde un anuncio', 4, dt.date(2026, 11, 18), dt.date(2026, 11, 21), {H: 0.9, G: 2, J: 2.1}, ['US-015', 'US-002']),
 ('US-008', 'EP-002', 'Editar mi anuncio', 4, dt.date(2026, 11, 18), dt.date(2026, 11, 20), {H: 1, G: 0.4, J: 1.6}, ['US-006']),
 ('US-022', 'EP-005', 'Cargar un conjunto de datos sintéticos', 4, dt.date(2026, 11, 21), dt.date(2026, 11, 24), {H: 1, G: 1.3, J: 2.7}, ['US-001', 'US-006']),
 ('US-018', 'EP-004', 'Mantener una conversación', 5, dt.date(2026, 11, 25), dt.date(2026, 11, 27), {H: 1, G: 1.4, J: 2.6}, ['US-017']),
 ('US-019', 'EP-004', 'Aviso de mensajes nuevos', 5, dt.date(2026, 11, 28), dt.date(2026, 11, 30), {H: 0.5, G: 0.6, J: 0.9}, ['US-018']),
 ('US-023', 'EP-005', 'Guion de instalación y evaluación', 5, dt.date(2026, 11, 26), dt.date(2026, 11, 30), {H: 1.7, J: 1.3}, ['US-017']),
]
RELEASE_OF = {1: 'R1', 3: 'R2', 5: 'R3'}
RELEASE_NAME = {'R1': 'Hito R1 · Release 1 entregada', 'R2': 'Hito R2 · Release 2 entregada',
                'R3': 'Hito R3 · Release 3 entregada (fin del proyecto)'}
# Cifras del Excel para comprobar (hoja Coste, v03)
EXCEL = {'desarrollo': 2405.20, 'gestion': 1326.80, 'reserva': 636.32, 'total': 4368.32}
# ==========================================================================================

PEOPLE = [r[1] for r in RES]
RUID = {r[1]: r[0] for r in RES}
RATE = {r[1]: r[3] for r in RES}
REV_H = (dt.datetime.combine(START0, REVIEW[1]) - dt.datetime.combine(START0, REVIEW[0])).seconds / 3600
PLAN_H = (dt.datetime.combine(START0, PLANNING[1]) - dt.datetime.combine(START0, PLANNING[0])).seconds / 3600
SLOT_H = (dt.datetime.combine(START0, FRANJA[1]) - dt.datetime.combine(START0, FRANJA[0])).seconds / 3600


def days_of(s):
    d0 = START0 + dt.timedelta(weeks=s)
    return [d0 + dt.timedelta(days=i) for i in range(7) if d0 + dt.timedelta(days=i) not in HOLIDAYS]


def reserva(p, s):  # horas de reserva de una persona en un sprint (hoja Gestión)
    rev = REV_H if s in REVIEW_SPRINTS else 0
    return IMPREV[p] * (NOMINAL[p] - CEREM_SEM - COORD[p] - rev)


def work_hours(a, b):  # horas de franja entre dos instantes (calendario del proyecto)
    h = 0.0; d = a.date()
    while d <= b.date():
        if d not in HOLIDAYS:
            s = max(a, dt.datetime.combine(d, FRANJA[0])); e = min(b, dt.datetime.combine(d, FRANJA[1]))
            if e > s: h += (e - s).seconds / 3600
        d += dt.timedelta(days=1)
    return h


# ---------- Tareas con fechas ----------
SCHED = {}
for s, (it, rel) in enumerate(SPR):
    days = days_of(s); wed, tue = days[0], days[-1]
    plan_end = dt.datetime.combine(wed, PLANNING[1])
    SCHED[f'PLAN-{s}'] = dict(start=dt.datetime.combine(wed, PLANNING[0]), finish=plan_end, asg={p: PLAN_H for p in PEOPLE})
    if s in REVIEW_SPRINTS:
        SCHED[f'REV-{s}'] = dict(start=dt.datetime.combine(tue, REVIEW[0]), finish=dt.datetime.combine(tue, REVIEW[1]),
                                 asg={p: REV_H for p in PEOPLE})
        SCHED[f'MS-{s}'] = dict(start=dt.datetime.combine(tue, REVIEW[1]), finish=dt.datetime.combine(tue, REVIEW[1]), asg={})
    # Gestión continua y reserva: después del planning y, si hay review, hasta que empieza.
    span_end = dt.datetime.combine(tue, REVIEW[0] if s in REVIEW_SPRINTS else FRANJA[1])
    SCHED[f'DAILY-{s}'] = dict(start=plan_end, finish=span_end, asg={p: DAILY_H for p in PEOPLE})
    SCHED[f'COORD-{s}'] = dict(start=plan_end, finish=span_end, asg=dict(COORD))
    SCHED[f'RES-{s}'] = dict(start=plan_end, finish=span_end, asg={p: round(reserva(p, s), 4) for p in PEOPLE})
for key, ep, tit, s, a, b, asg, pred in US:
    start = dt.datetime.combine(a, PLANNING[1] if a == days_of(s)[0] else FRANJA[0])
    SCHED[key] = dict(start=start, finish=dt.datetime.combine(b, FRANJA[1]), asg=asg)
for k, sc in SCHED.items():
    sc['dur_h'] = work_hours(sc['start'], sc['finish'])

# Dependencias (todas fin-comienzo): planning -> historias sin predecesora en el sprint -> review -> hito
PRED = {u[0]: list(u[7]) for u in US}
for key, ep, tit, s, a, b, asg, pred in US:
    if not any(x[0] in pred and x[3] == s for x in US): PRED[key].append(f'PLAN-{s}')
for s in REVIEW_SPRINTS:
    PRED[f'REV-{s}'] = [u[0] for u in US if u[3] == s]
    PRED[f'MS-{s}'] = [f'REV-{s}']
for s in range(len(SPR)):
    for k in ('DAILY', 'COORD', 'RES'): PRED[f'{k}-{s}'] = [f'PLAN-{s}']


# ---------- Árbol ----------
def node(name, kind, **kw):
    d = dict(name=name, kind=kind, children=[]); d.update(kw); return d


def us_task(u):
    key, ep, tit, s, a, b, asg, pred = u
    return node(f'{key} {tit}', 'task', key=key,
                note=f'{key} · {ep} · {SPR[s][0]} · {sum(asg.values()):g} h-p. Horas por persona y fechas de inicio y fin de la hoja Planificación (excel-export-v03).')


def mgt_tasks(s):
    it = SPR[s][0]; d = days_of(s)
    t = [node(f'MGT {it} Sprint planning y refinamiento', 'task', key=f'PLAN-{s}',
              note=f'Reunión del miércoles {d[0]:%d/%m}, {PLANNING[0]:%H:%M}-{PLANNING[1]:%H:%M}, por Discord: planning 30 min + refinamiento 15 min (team charter, acuerdo 4).'),
         node(f'MGT {it} Daily asíncrona', 'task', key=f'DAILY-{s}',
              note='≈5 min al día por WhatsApp (acuerdo 5). Es asíncrona: no es una reunión, por eso se reparte a lo largo del sprint.'),
         node(f'MGT {it} Coordinación del rol', 'task', key=f'COORD-{s}',
              note='Trabajo continuo de PO, TL y SM (hoja Capacidad): refinar el backlog, revisar PR y seguir impedimentos.'),
         node(f'MGT {it} Reserva de imprevistos', 'task', key=f'RES-{s}',
              note='Horas del % de imprevistos de cada persona (DEC-015, hoja Gestión). Solo se gastan si ocurre un imprevisto (RSK-01, RSK-09); tienen coste para que el presupuesto incluya la reserva.')]
    if s in REVIEW_SPRINTS:
        t.append(node(f'MGT {it} Sprint review y retrospectiva', 'task', key=f'REV-{s}',
                      note=f'Reunión del martes {d[-1]:%d/%m}, {REVIEW[0]:%H:%M}-{REVIEW[1]:%H:%M}: review 25 min + retro 25 min, cada 2 semanas al cerrar la release (acuerdo 6).'))
    return t


def milestone(s):
    return node(RELEASE_NAME[RELEASE_OF[s]], 'ms', key=f'MS-{s}')


def tree(mode):
    root = node('ErasmusNest · Practical Software (GC04)', 'sum')
    if mode == 'sprints':
        for s, (it, rel) in enumerate(SPR):
            sp = node(f'{it} · {rel}', 'sum'); m = mgt_tasks(s)
            sp['children'] += m[:1] + [us_task(u) for u in US if u[3] == s] + m[1:]
            if s in REVIEW_SPRINTS: sp['children'].append(milestone(s))
            root['children'].append(sp)
    else:
        mg = node('Gestión (MGT)', 'sum')
        for s, (it, rel) in enumerate(SPR):
            sp = node(f'MGT {it} · {rel}', 'sum'); sp['children'] = mgt_tasks(s); mg['children'].append(sp)
        us = node('Historias de usuario', 'sum')
        for ep, name in EPICS.items():
            e = node(f'{ep} {name}', 'sum'); e['children'] = [us_task(u) for u in US if u[1] == ep]; us['children'].append(e)
        root['children'] += [mg, us] + [milestone(s) for s in sorted(REVIEW_SPRINTS)]
    return root


def pt(h):
    m = round(h * 3600); return f'PT{m // 3600}H{(m % 3600) // 60}M{m % 60}S'


def build(mode, title):
    root = tree(mode); flat = []

    def walk(n, lvl, num):
        n['lvl'] = lvl; n['num'] = num; flat.append(n)
        for i, c in enumerate(n['children'], 1): walk(c, lvl + 1, f'{num}.{i}' if num else str(i))
    walk(root, 0, '')
    for i, n in enumerate(flat): n['uid'] = i
    keyuid = {n['key']: n['uid'] for n in flat if 'key' in n}
    for n in flat:
        if 'key' in n: n.update(SCHED[n['key']]); n['work'] = sum(n['asg'].values())

    def agg(n):
        if n['children']:
            for c in n['children']: agg(c)
            n['start'] = min(c['start'] for c in n['children']); n['finish'] = max(c['finish'] for c in n['children'])
            n['work'] = sum(c['work'] for c in n['children']); n['dur_h'] = work_hours(n['start'], n['finish'])
    agg(root)

    def cost(n):
        if n['kind'] == 'task': return sum(h * RATE[p] for p, h in n['asg'].items())
        return sum(cost(c) for c in n['children'])
    f = lambda t: t.strftime('%Y-%m-%dT%H:%M:%S')
    X = ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?>', '<Project xmlns="http://schemas.microsoft.com/project">',
         f'<Name>{escape(title)}</Name><Title>{escape(title)}</Title><Author>Practical Software</Author><Company>Practical Software</Company>',
         '<ScheduleFromStart>1</ScheduleFromStart>', f'<StartDate>{f(root["start"])}</StartDate><FinishDate>{f(root["finish"])}</FinishDate>',
         '<FYStartDate>1</FYStartDate><CriticalSlackLimit>0</CriticalSlackLimit><CurrencyDigits>2</CurrencyDigits><CurrencySymbol>€</CurrencySymbol><CurrencyCode>EUR</CurrencyCode><CurrencySymbolPosition>3</CurrencySymbolPosition>',
         f'<CalendarUID>1</CalendarUID><DefaultStartTime>{FRANJA[0]:%H:%M:%S}</DefaultStartTime><DefaultFinishTime>{FRANJA[1]:%H:%M:%S}</DefaultFinishTime>'
         f'<MinutesPerDay>{round(SLOT_H * 60)}</MinutesPerDay><MinutesPerWeek>{round(SLOT_H * 420)}</MinutesPerWeek><DaysPerMonth>30</DaysPerMonth>',
         '<DefaultTaskType>1</DefaultTaskType><DefaultFixedCostAccrual>3</DefaultFixedCostAccrual><DefaultStandardRate>0</DefaultStandardRate><DefaultOvertimeRate>0</DefaultOvertimeRate><DurationFormat>5</DurationFormat><WorkFormat>2</WorkFormat>',
         '<WeekStartDay>1</WeekStartDay><NewTasksEffortDriven>0</NewTasksEffortDriven><HonorConstraints>1</HonorConstraints>', '<Calendars>']

    def calendar(uid, name, base, exceptions=()):
        c = [f'<Calendar><UID>{uid}</UID><Name>{escape(name)}</Name><IsBaseCalendar>{1 if base else 0}</IsBaseCalendar><BaseCalendarUID>{-1 if base else 1}</BaseCalendarUID>']
        if base:
            c.append('<WeekDays>' + ''.join(
                f'<WeekDay><DayType>{wd}</DayType><DayWorking>1</DayWorking><WorkingTimes><WorkingTime><FromTime>{FRANJA[0]:%H:%M:%S}</FromTime><ToTime>{FRANJA[1]:%H:%M:%S}</ToTime></WorkingTime></WorkingTimes></WeekDay>'
                for wd in range(1, 8)) + '</WeekDays>')
        exc = [(h, 'Festivo', None) for h in sorted(HOLIDAYS)] if base else list(exceptions)
        if exc:
            x = '<Exceptions>'
            for d, nm, horas in exc:
                x += f'<Exception><EnteredByOccurrences>0</EnteredByOccurrences><TimePeriod><FromDate>{d}T00:00:00</FromDate><ToDate>{d}T23:59:00</ToDate></TimePeriod><Occurrences>1</Occurrences><Name>{escape(nm)}</Name><Type>1</Type>'
                if horas is None or horas >= SLOT_H: x += '<DayWorking>0</DayWorking>'
                else:  # la ausencia ocupa el principio de la franja; el resto del día sí se trabaja
                    ini = (dt.datetime.combine(d, FRANJA[0]) + dt.timedelta(hours=horas)).time()
                    x += f'<DayWorking>1</DayWorking><WorkingTimes><WorkingTime><FromTime>{ini:%H:%M:%S}</FromTime><ToTime>{FRANJA[1]:%H:%M:%S}</ToTime></WorkingTime></WorkingTimes>'
                x += '</Exception>'
            c.append(x + '</Exceptions>')
        return ''.join(c) + '</Calendar>'
    X.append(calendar(1, f'ErasmusNest · franja del equipo {FRANJA[0]:%H:%M}-{FRANJA[1]:%H:%M}', True))
    for uid, name, rol, r in RES:
        X.append(calendar(10 + uid, f'{name} · {NOMINAL[name]} h/semana', False,
                          [(d, 'Ausencia declarada (hoja Capacidad)', h) for p, d, h in AUSENCIAS if p == name]))
    X.append('</Calendars><Tasks>')
    for i, n in enumerate(flat):
        summ = 1 if n['kind'] == 'sum' else 0; ms = 1 if n['kind'] == 'ms' else 0
        X.append('<Task>' + f'<UID>{n["uid"]}</UID><ID>{i}</ID><Name>{escape(n["name"])}</Name><Type>1</Type><IsNull>0</IsNull>'
                 f'<WBS>{n["num"] or "0"}</WBS><OutlineNumber>{n["num"] or "0"}</OutlineNumber><OutlineLevel>{n["lvl"]}</OutlineLevel><Priority>500</Priority>'
                 f'<Start>{f(n["start"])}</Start><Finish>{f(n["finish"])}</Finish><Duration>{pt(0 if ms else n["dur_h"])}</Duration><DurationFormat>5</DurationFormat>'
                 f'<Work>{pt(n["work"])}</Work><Cost>{cost(n):.2f}</Cost><Milestone>{ms}</Milestone><Summary>{summ}</Summary><EffortDriven>0</EffortDriven><Estimated>0</Estimated>'
                 + (f'<ConstraintType>4</ConstraintType><ConstraintDate>{f(n["start"])}</ConstraintDate>' if n['kind'] == 'task' else '<ConstraintType>0</ConstraintType>')
                 + (f'<Notes>{escape(n["note"])}</Notes>' if n.get('note') else '')
                 + ''.join(f'<PredecessorLink><PredecessorUID>{keyuid[k]}</PredecessorUID><Type>1</Type><CrossProject>0</CrossProject><LinkLag>0</LinkLag><LagFormat>7</LagFormat></PredecessorLink>'
                           for k in PRED.get(n.get('key'), []) if k in keyuid)
                 + '</Task>')
    X.append('</Tasks><Resources>')
    for uid, name, rol, r in RES:
        X.append(f'<Resource><UID>{uid}</UID><ID>{uid}</ID><Name>{escape(name)}</Name><Type>1</Type><IsNull>0</IsNull><Initials>{name[0]}</Initials><Group>{rol}</Group>'
                 f'<MaxUnits>1.00</MaxUnits><StandardRate>{r}</StandardRate><StandardRateFormat>2</StandardRateFormat><OvertimeRate>0</OvertimeRate><OvertimeRateFormat>2</OvertimeRateFormat><CostPerUse>0</CostPerUse><AccrueAt>3</AccrueAt>'
                 f'<CalendarUID>{10 + uid}</CalendarUID><Notes>{escape(rol)} · {NOMINAL[name]} h/semana (hoja Capacidad, v03) · tarifa {r} €/h</Notes></Resource>')
    X.append('</Resources><Assignments>')
    a = 1
    for n in flat:
        if n['kind'] != 'task': continue
        for p, h in n['asg'].items():
            X.append(f'<Assignment><UID>{a}</UID><TaskUID>{n["uid"]}</TaskUID><ResourceUID>{RUID[p]}</ResourceUID><Units>{h / n["dur_h"]:.6f}</Units>'
                     f'<Work>{pt(h)}</Work><RegularWork>{pt(h)}</RegularWork><RemainingWork>{pt(h)}</RemainingWork><Cost>{h * RATE[p]:.2f}</Cost>'
                     f'<Start>{f(n["start"])}</Start><Finish>{f(n["finish"])}</Finish><WorkContour>0</WorkContour></Assignment>'); a += 1
    X.append('</Assignments></Project>')
    return '\n'.join(X), root, cost(root)


xs, root_s, cost_s = build('sprints', 'ErasmusNest GC04 · Planificación por sprints')
xe, root_e, cost_e = build('epicas', 'ErasmusNest GC04 · Planificación por épicas')
open(f'{OUT}/ErasmusNest_GC04_por_sprints.xml', 'w', encoding='utf-8').write(xs)
open(f'{OUT}/ErasmusNest_GC04_por_epicas.xml', 'w', encoding='utf-8').write(xe)

# ---------- Comprobación ----------
ok = True
dev_h = sum(sum(u[6].values()) for u in US)
res_h = sum(reserva(p, s) for p in PEOPLE for s in range(len(SPR)))
c_dev = sum(h * RATE[p] for u in US for p, h in u[6].items())
c_res = sum(reserva(p, s) * RATE[p] for p in PEOPLE for s in range(len(SPR)))
c_ges = cost_s - c_dev - c_res
print(f'Trabajo total {root_s["work"]:.2f} h = desarrollo {dev_h:.2f} + gestión {root_s["work"] - dev_h - res_h:.2f} + reserva {res_h:.2f}')
for nombre, v, e in [('desarrollo', c_dev, EXCEL['desarrollo']), ('gestión', c_ges, EXCEL['gestion']),
                     ('reserva', c_res, EXCEL['reserva']), ('TOTAL', cost_s, EXCEL['total'])]:
    cuadra = abs(v - e) < 0.5; ok &= cuadra
    print(f'Coste {nombre:10} {v:9.2f} €   Excel {e:9.2f} €   {"OK" if cuadra else "NO CUADRA"}')
print(f'Por épicas: {cost_e:.2f} € ({"OK" if abs(cost_e - cost_s) < 0.01 else "DISTINTO"})')
print('Carga semanal por persona (trabajo + ausencias) frente a su dedicación:')
for p in PEOPLE:
    fila = []
    for s in range(len(SPR)):
        d = set(days_of(s))
        carga = sum(sc['asg'].get(p, 0) for k, sc in SCHED.items() if sc['start'].date() in d)
        carga += sum(h for q, f_, h in AUSENCIAS if q == p and f_ in d)
        fila.append(f'{SPR[s][0]} {carga:.2f}/{NOMINAL[p]}'); ok &= carga <= NOMINAL[p] + 1e-6
    print(f'  {p:5} ' + ' · '.join(fila))
mal = [u[0] for u in US for q in u[7] if SCHED[q]['finish'] > SCHED[u[0]]['start']]
print('Predecesoras ordenadas:', 'OK' if not mal else f'NO en {mal}'); ok &= not mal
print('RESULTADO:', 'TODO CUADRA' if ok else 'REVISAR')

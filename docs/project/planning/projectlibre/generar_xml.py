import datetime as dt, sys
from xml.sax.saxutils import escape
OUT = sys.argv[1]
H,G,J = 'Hugo','Gled','José'
RES = [  # uid, name, rol, rate
 (1,H,'Product Owner',35),(2,G,'Tech Lead',40),(3,J,'Scrum Master',32)]
RUID = {r[1]:r[0] for r in RES}
EST = {'US-001':5,'US-002':5,'US-003':3,'US-006':5,'US-012':3,'US-015':3,'US-017':5,'US-023':3}
EPICS = {'EP-001':'Cuenta y verificación','EP-002':'Publicación de anuncios','EP-003':'Búsqueda de habitaciones','EP-004':'Contacto y mensajería','EP-005':'Entrega y demo'}
SPR = [('IT-001','R1'),('IT-002','R1'),('IT-003','R2'),('IT-004','R2'),('IT-005','R3'),('IT-006','R3')]
start0 = dt.date(2026,10,21)
# El 2/11/2026 es festivo en Extremadura (traslado de Todos los Santos). No se marca como no laborable:
# la capacidad es de horas por semana y ProjectLibre 1.9.3 no importa bien la excepción desde XML (ver LEEME).
HOLIDAYS = set()
def sprint_days(i):
    d = start0 + dt.timedelta(weeks=i); out=[]
    while True:
        if d.weekday()<5 and d not in HOLIDAYS: out.append(d)
        d += dt.timedelta(days=1)
        if d.weekday()==2 and out: break
    return out
# historias (o tramos) por sprint: key, US, épica, título, {persona: h}, predecesoras (keys), tipo de enlace
US = [
 ('US-001','US-001','EP-001','Registrar cuenta con correo institucional',0,{H:1.5,G:2.1,J:1.4},[]),
 ('US-002a','US-002','EP-001','Verificar la cuenta mediante el correo institucional (parte 1 de 2)',0,{H:0.8,J:1.0},[('US-001','SS')]),
 ('US-002b','US-002','EP-001','Verificar la cuenta mediante el correo institucional (parte 2 de 2)',1,{H:0.6,G:1.6,J:1.0},[('US-002a','FS')]),
 ('US-003','US-003','EP-001','Iniciar y cerrar sesión',1,{H:1.5,G:0.3,J:1.2},[('US-001','FS')]),
 ('US-006a','US-006','EP-002','Publicar el anuncio de mi habitación (parte 1 de 2)',2,{H:0.8,G:0.6,J:2.4},[('US-002b','FS'),('US-003','FS')]),
 ('US-006b','US-006','EP-002','Publicar el anuncio de mi habitación (parte 2 de 2)',3,{H:0.4,G:0.8},[('US-006a','FS')]),
 ('US-012','US-012','EP-003','Listar los anuncios disponibles',3,{H:1.6,G:0.8,J:0.6},[('US-006b','SS')]),
 ('US-015','US-015','EP-003','Ver el detalle de un anuncio con su estado',4,{H:1.0,G:0.6,J:1.4},[('US-012','FS')]),
 ('US-017a','US-017','EP-004','Contactar con el oferente desde un anuncio (parte 1 de 2)',4,{H:0.3,G:1.5,J:1.0},[('US-015','SS'),('US-002b','FS')]),
 ('US-017b','US-017','EP-004','Contactar con el oferente desde un anuncio (parte 2 de 2)',5,{G:1.1,J:1.1},[('US-017a','FS')]),
 ('US-023','US-023','EP-005','Guion de instalación y evaluación',5,{H:2.0,J:1.0},[('US-017b','SS')]),
]
MGT = [('Sprint planning y refinamiento',{H:0.75,G:0.75,J:0.75},'Planning 30 min + refinamiento 15 min (team charter, acuerdo 4)'),
       ('Daily asíncrona',{H:0.4,G:0.4,J:0.4},'≈5 min × 5 días por WhatsApp (acuerdo 5)'),
       ('Sprint review y retrospectiva',{H:0.42,G:0.42,J:0.42},'Review 20 min + retro 20–30 min cada 2 semanas, prorrateado ≈25 min/semana (acuerdo 6)'),
       ('Coordinación del rol',{H:0.5,G:0.5,J:0.75},'Coordinación propia de PO, TL y SM (hoja Capacidad)')]
def node(name, kind, **kw): d=dict(name=name,kind=kind,children=[]); d.update(kw); return d
def us_task(u):
    k,usid,ep,tit,s,asg,pred=u
    return node(f'{usid} {tit}','task',key=k,sprint=s,asg=asg,pred=pred,note=f'{usid} · {ep} · {SPR[s][0]} · estimación total de la historia {EST[usid]} h-p (Planning Poker). Horas por persona e iteración de la hoja Planificación (excel-export-v02).')
def mgt_tasks(s):
    it=SPR[s][0]
    return [node(f'MGT {it} {n}','task',key=f'MGT-{it}-{i}',sprint=s,asg=a,pred=[],note=nt) for i,(n,a,nt) in enumerate(MGT)]
def milestones():
    return [node('Hito R1 · Release 1 entregada','ms',key='R1',sprint=1,pred=[('US-002b','FS'),('US-003','FS')]),
            node('Hito R2 · Release 2 entregada','ms',key='R2',sprint=3,pred=[('US-012','FS')]),
            node('Hito R3 · Release 3 entregada (fin del proyecto)','ms',key='R3',sprint=5,pred=[('US-023','FS')])]
def tree(mode):
    root=node('ErasmusNest · Practical Software (GC04)','sum')
    if mode=='sprints':
        for s,(it,rel) in enumerate(SPR):
            sp=node(f'{it} · {rel}','sum')
            sp['children']+=mgt_tasks(s)[:1]+[us_task(u) for u in US if u[4]==s]+mgt_tasks(s)[1:]
            sp['children']+=[m for m in milestones() if m['sprint']==s]
            root['children'].append(sp)
    else:
        mg=node('Gestión (MGT)','sum')
        for s,(it,rel) in enumerate(SPR):
            sp=node(f'MGT {it} · {rel}','sum'); sp['children']=mgt_tasks(s); mg['children'].append(sp)
        us=node('Historias de usuario','sum')
        for ep,name in EPICS.items():
            e=node(f'{ep} {name}','sum'); e['children']=[us_task(u) for u in US if u[2]==ep]; us['children'].append(e)
        root['children']+= [mg,us]+milestones()
    return root
def avail():
    per=[]; prev=dt.datetime(2026,10,1)
    for i in range(len(SPR)):
        d=sprint_days(i); u=5/(len(d)*8)
        per.append((d[0],d[-1],u))
    out='<AvailabilityPeriods>'
    for k,(a,b,u) in enumerate(per):
        a0=dt.datetime(2026,1,1) if k==0 else dt.datetime.combine(a,dt.time(0))
        b0=dt.datetime(2026,12,31,23,59) if k==len(per)-1 else dt.datetime.combine(per[k+1][0]-dt.timedelta(days=1),dt.time(23,59))
        out+=f'<AvailabilityPeriod><AvailableFrom>{a0:%Y-%m-%dT%H:%M:%S}</AvailableFrom><AvailableTo>{b0:%Y-%m-%dT%H:%M:%S}</AvailableTo><AvailableUnits>{u:.5f}</AvailableUnits></AvailabilityPeriod>'
    return out+'</AvailabilityPeriods>'
def pt(h):
    m=round(h*60*60); return f'PT{m//3600}H{(m%3600)//60}M{m%60}S'
def build(mode,title):
    root=tree(mode); flat=[]
    def walk(n,lvl,num):
        n['lvl']=lvl; n['num']=num; flat.append(n)
        for i,c in enumerate(n['children'],1): walk(c,lvl+1,f'{num}.{i}' if num else str(i))
    walk(root,0,'')
    for i,n in enumerate(flat): n['uid']=i; 
    keyuid={n['key']:n['uid'] for n in flat if 'key' in n}
    for n in flat:
        if n['kind']=='task':
            d=sprint_days(n['sprint']); n['start']=dt.datetime.combine(d[0],dt.time(8)); n['finish']=dt.datetime.combine(d[-1],dt.time(17))
            n['work']=sum(n['asg'].values())
        elif n['kind']=='ms':
            d=sprint_days(n['sprint']); n['start']=n['finish']=dt.datetime.combine(d[-1],dt.time(17)); n['work']=0
    def agg(n):
        if n['children']:
            for c in n['children']: agg(c)
            n['start']=min(c['start'] for c in n['children']); n['finish']=max(c['finish'] for c in n['children']); n['work']=sum(c['work'] for c in n['children'])
    agg(root)
    rate={r[1]:r[3] for r in RES}
    def cost(n):
        if n['kind']=='task': return sum(h*rate[p] for p,h in n['asg'].items())
        return sum(cost(c) for c in n['children'])
    def days(n):
        if n['kind']=='ms': return 0
        d=0; x=n['start'].date()
        while x<=n['finish'].date():
            d+= x.weekday()<5 and x not in HOLIDAYS; x+=dt.timedelta(days=1)
        return d
    f=lambda t:t.strftime('%Y-%m-%dT%H:%M:%S')
    X=['<?xml version="1.0" encoding="UTF-8" standalone="yes"?>','<Project xmlns="http://schemas.microsoft.com/project">',
       f'<Name>{escape(title)}</Name><Title>{escape(title)}</Title><Author>Practical Software</Author><Company>Practical Software</Company>',
       '<ScheduleFromStart>1</ScheduleFromStart>',f'<StartDate>{f(root["start"])}</StartDate><FinishDate>{f(root["finish"])}</FinishDate>',
       '<FYStartDate>1</FYStartDate><CriticalSlackLimit>0</CriticalSlackLimit><CurrencyDigits>2</CurrencyDigits><CurrencySymbol>€</CurrencySymbol><CurrencyCode>EUR</CurrencyCode><CurrencySymbolPosition>3</CurrencySymbolPosition>',
       '<CalendarUID>1</CalendarUID><DefaultStartTime>08:00:00</DefaultStartTime><DefaultFinishTime>17:00:00</DefaultFinishTime><MinutesPerDay>480</MinutesPerDay><MinutesPerWeek>2400</MinutesPerWeek><DaysPerMonth>20</DaysPerMonth>',
       '<DefaultTaskType>1</DefaultTaskType><DefaultFixedCostAccrual>3</DefaultFixedCostAccrual><DefaultStandardRate>0</DefaultStandardRate><DefaultOvertimeRate>0</DefaultOvertimeRate><DurationFormat>7</DurationFormat><WorkFormat>2</WorkFormat>',
       '<WeekStartDay>1</WeekStartDay><NewTasksEffortDriven>0</NewTasksEffortDriven><HonorConstraints>1</HonorConstraints>',
       '<Calendars><Calendar><UID>1</UID><Name>ErasmusNest (lunes a viernes)</Name><IsBaseCalendar>1</IsBaseCalendar><BaseCalendarUID>-1</BaseCalendarUID><WeekDays>']
    for wd in range(1,8):
        if wd in (1,7): X.append(f'<WeekDay><DayType>{wd}</DayType><DayWorking>0</DayWorking></WeekDay>')
        else: X.append(f'<WeekDay><DayType>{wd}</DayType><DayWorking>1</DayWorking><WorkingTimes><WorkingTime><FromTime>08:00:00</FromTime><ToTime>12:00:00</ToTime></WorkingTime><WorkingTime><FromTime>13:00:00</FromTime><ToTime>17:00:00</ToTime></WorkingTime></WorkingTimes></WeekDay>')
    for h in sorted(HOLIDAYS):
        X.append(f'<WeekDay><DayType>0</DayType><DayWorking>0</DayWorking><TimePeriod><FromDate>{h}T00:00:00</FromDate><ToDate>{h}T23:59:00</ToDate></TimePeriod></WeekDay>')
    X.append('</WeekDays>' + ('<Exceptions>' if HOLIDAYS else ''))
    for h in sorted(HOLIDAYS):
        X.append(f'<Exception><EnteredByOccurrences>0</EnteredByOccurrences><TimePeriod><FromDate>{h}T00:00:00</FromDate><ToDate>{h}T23:59:00</ToDate></TimePeriod><Occurrences>1</Occurrences><Name>Festivo en Extremadura (traslado de Todos los Santos)</Name><Type>1</Type><DayWorking>0</DayWorking></Exception>')
    X.append(('</Exceptions>' if HOLIDAYS else '') + '</Calendar></Calendars><Tasks>')
    for i,n in enumerate(flat):
        summ = 1 if n['kind']=='sum' else 0; ms = 1 if n['kind']=='ms' else 0
        X.append('<Task>'+f'<UID>{n["uid"]}</UID><ID>{i}</ID><Name>{escape(n["name"])}</Name><Type>1</Type><IsNull>0</IsNull>'
          f'<WBS>{n["num"] or "0"}</WBS><OutlineNumber>{n["num"] or "0"}</OutlineNumber><OutlineLevel>{n["lvl"]}</OutlineLevel><Priority>500</Priority>'
          f'<Start>{f(n["start"])}</Start><Finish>{f(n["finish"])}</Finish><Duration>PT{days(n)*8}H0M0S</Duration><DurationFormat>7</DurationFormat>'
          f'<Work>{pt(n["work"])}</Work><Cost>{cost(n):.2f}</Cost><Milestone>{ms}</Milestone><Summary>{summ}</Summary><EffortDriven>0</EffortDriven><Estimated>0</Estimated>'
          + (f'<ConstraintType>4</ConstraintType><ConstraintDate>{f(n["start"])}</ConstraintDate>' if n['kind']=='task' else '<ConstraintType>0</ConstraintType>')
          + (f'<Notes>{escape(n["note"])}</Notes>' if n.get('note') else '')
          + ''.join(f'<PredecessorLink><PredecessorUID>{keyuid[k]}</PredecessorUID><Type>{ {"FS":1,"SS":3}[t] }</Type><CrossProject>0</CrossProject><LinkLag>0</LinkLag><LagFormat>7</LagFormat></PredecessorLink>' for k,t in n.get('pred',[]))
          + '</Task>')
    X.append('</Tasks><Resources>')
    for uid,name,rol,r in RES:
        X.append(f'<Resource><UID>{uid}</UID><ID>{uid}</ID><Name>{escape(name)}</Name><Type>1</Type><IsNull>0</IsNull><Initials>{name[0]}</Initials><Group>{rol}</Group>'
                 f'<MaxUnits>0.125</MaxUnits><StandardRate>{r}</StandardRate><StandardRateFormat>2</StandardRateFormat><OvertimeRate>0</OvertimeRate><OvertimeRateFormat>2</OvertimeRateFormat><CostPerUse>0</CostPerUse><AccrueAt>3</AccrueAt>'
                 f'<Notes>{escape(rol)} · 5 h/semana nominales (MaxUnits 12,5 % de 40 h) · tarifa {r} €/h acordada el 14/10/2026</Notes>'+'</Resource>')
    X.append('</Resources><Assignments>')
    a=1
    for n in flat:
        if n['kind']!='task': continue
        dur=days(n)*8
        for p,h in n['asg'].items():
            X.append(f'<Assignment><UID>{a}</UID><TaskUID>{n["uid"]}</TaskUID><ResourceUID>{RUID[p]}</ResourceUID><Units>{h/dur:.6f}</Units>'
                     f'<Work>{pt(h)}</Work><RegularWork>{pt(h)}</RegularWork><RemainingWork>{pt(h)}</RemainingWork><Cost>{h*rate[p]:.2f}</Cost>'
                     f'<Start>{f(n["start"])}</Start><Finish>{f(n["finish"])}</Finish><WorkContour>0</WorkContour></Assignment>'); a+=1
    X.append('</Assignments></Project>')
    return '\n'.join(X)
open(f'{OUT}/ErasmusNest_GC04_por_sprints.xml','w',encoding='utf-8').write(build('sprints','ErasmusNest GC04 · Planificación por sprints'))
open(f'{OUT}/ErasmusNest_GC04_por_epicas.xml','w',encoding='utf-8').write(build('epicas','ErasmusNest GC04 · Planificación por épicas'))

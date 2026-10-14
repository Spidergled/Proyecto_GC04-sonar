# EP-001 — Cuenta y verificación institucional

**Goals:** G-02, G-01
**Alcance:** S-01
**Baseline:** requisitos-v0 (Hito 2)
**Boundary:** incluye el registro con correo institucional (sintético), la verificación del correo, el inicio y cierre de sesión, un perfil básico y la recuperación de contraseña. **No incluye** verificación de identidad por DNI, videollamada u otros medios (EX-04), ni integración con los sistemas reales de la UEx (EX-05).

> Las estimaciones son las **consensuadas en el Planning Poker del 10/10/2026**; entre paréntesis queda la referencia V0. Las estimaciones individuales están en el Excel y en la Wiki.

## Epic outcome

Solo los estudiantes que demuestran pertenecer a la UEx mediante su correo institucional pueden publicar o contactar. Esa es la base de confianza de toda la plataforma.

## Stories

### US-001 — Registrar cuenta con correo institucional ⭐ *muestra detallada*

> Como **estudiante de la UEx**, quiero **registrarme con mi correo institucional y una contraseña**, para **tener una cuenta en ErasmusNest que me identifique como miembro de la universidad**.

- **Necesidad:** hoy no hay forma de saber si quien publica o responde un anuncio es estudiante de la UEx.
- **Objetivo:** G-02 (confianza institucional).
- **Prioridad:** Must. Valor: sin cuenta no existe ninguna otra funcionalidad; es la primera pieza del recorrido esencial y la que hace posible G-02. · Riesgo: bajo en sí misma, pero fija el modelo de usuario (TD-01) del que depende todo; un retraso aquí bloquea el proyecto entero. · Dependencias: no tiene predecesoras; de ella dependen US-002, US-003 y US-022.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 8 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** —
- **Plan inicial:** R1, IT-001 (planificación inicial del 14/10/2026, hoja *Planificación* del Excel). Incluye TD-01 (entorno base y modelo de usuario) como tareas TK-001 y TK-002 de IT-001 (DEC-020).

#### Acceptance criteria

- **US-001-AC-01:** el formulario pide correo, contraseña y confirmación de contraseña, y cada campo tiene su etiqueta visible.
- **US-001-AC-02:** si el correo no pertenece al dominio institucional configurado, el registro se rechaza con el mensaje "Solo se admiten correos institucionales de la UEx".
- **US-001-AC-03:** si el correo ya está registrado, se muestra un mensaje genérico ("Si el correo es válido, recibirás un enlace") que no revela si la cuenta existe.
- **US-001-AC-04:** un registro válido crea la cuenta en estado **No verificada** y dispara el envío de la verificación (US-002).
- **US-001-AC-05:** la contraseña debe tener al menos 8 caracteres, con una letra y un número; si no, se indica qué falta.
- **US-001-AC-06:** si la contraseña y su confirmación no coinciden, el registro se rechaza con el mensaje "Las contraseñas no coinciden", asociado al campo de confirmación, y no se crea la cuenta.

#### Reglas, restricciones y NFR

- **Reglas:** RN-01, RN-02.
- **Restricciones:** solo cuentas sintéticas (NFR-009); el dominio institucional es configurable para poder usar un dominio de prueba.
- **NFR:** NFR-001 (hash de contraseñas), NFR-004 (formulario accesible), NFR-007 (validación en servidor), NFR-009 (solo cuentas sintéticas).
- **Dependencias:**
  - Funcionales: ninguna.
  - Técnicas: modelo de datos de usuario y entorno base del proyecto, preparados por el TL en IT-001.
  - Externas: tecnologías aprobadas en la asignatura (SU-07).

#### Ejemplos

| Entrada | Resultado |
|---|---|
| `ana.garcia@alumnos.unex.es` / `Erasmus2026` | Cuenta creada como **No verificada** y se envía la verificación. |
| `ana.garcia@gmail.com` / `Erasmus2026` | Rechazado: correo no institucional. |
| `ana.garcia@alumnos.unex.es` / `erasmus` | Rechazado: la contraseña necesita al menos 8 caracteres y un número. |
| `ana.garcia@alumnos.unex.es` / `Erasmus2026`, confirmación `Erasmus2025` | Rechazado: "Las contraseñas no coinciden". |

#### Casos límite

- Correo con mayúsculas (`Ana.Garcia@Alumnos.UNEX.es`): se normaliza a minúsculas y se trata como el mismo correo.
- Espacios al principio o al final del correo: se eliminan antes de validar.
- Correo mal formado (sin `@`, con espacios internos o con dos `@`): se rechaza con "Introduce un correo válido" antes de comprobar el dominio.
- Subdominios parecidos (`@alumnos.unex.es.fake.com`): se rechazan, porque la validación comprueba que el correo *termina* exactamente en el dominio configurado.
- Doble clic en "Registrarse": solo se crea una cuenta.

#### Supuestos

- SU-01: el dominio del correo basta para acreditar la pertenencia a la UEx.
- El correo de verificación se recibe en un buzón de pruebas, no se envía a direcciones reales.

---

### US-002 — Verificar la cuenta mediante el correo institucional ⭐ *muestra detallada*

> Como **estudiante recién registrado**, quiero **confirmar mi correo institucional mediante un enlace**, para **que mi cuenta quede verificada y pueda publicar y contactar**.

- **Necesidad:** sin verificación, cualquiera podría registrarse con un correo institucional ajeno.
- **Objetivo:** G-02; métrica M-02.
- **Prioridad:** Must. Valor: hace real la confianza institucional (G-02, M-02). · Riesgo: medio, por depender del envío de correo (TD-02, EXT-02); conviene abordarlo pronto. · Dependencias: necesita US-001; de ella dependen US-005, US-006 y US-017.
- **Estimación:** 5 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 10 h-p)
- **Incertidumbre:** Media (envío de correo y caducidad del enlace)
- **Predecesoras:** US-001
- **Plan inicial:** R1, IT-001 – IT-002 (excepción de DEC-018). Justificación: con US-001 (5 h-p) y US-003 (3 h-p), R1 no se puede repartir en historias enteras por iteración (6,95 h-p cada una), y dividir US-002 separaría el envío del enlace de su uso, sin valor por separado. Su estimación (5 h-p) cabe en la capacidad de las dos iteraciones (13,9 h-p). Empieza en IT-001 porque es la historia de R1 con más incertidumbre; incluye TD-02 (envío de correo) en IT-002 (DEC-020). Cuenta como Hecha al final de IT-002.

#### Acceptance criteria

- **US-002-AC-01:** tras registrarse, el usuario recibe en su correo (buzón de pruebas) un enlace de verificación único.
- **US-002-AC-02:** al abrir un enlace válido, la cuenta pasa a **Verificada** y se muestra un mensaje de confirmación.
- **US-002-AC-03:** un enlace caducado (más de 24 h) o ya usado muestra un aviso y permite pedir un enlace nuevo.
- **US-002-AC-04:** mientras la cuenta no está verificada, las acciones de publicar y contactar están bloqueadas y se indica por qué.

#### Reglas, restricciones y NFR

- **Reglas:** RN-02, RN-03.
- **Restricciones:** no se envían correos a direcciones reales; en desarrollo y en la demo se usa un buzón de pruebas.
- **NFR:** NFR-001, NFR-004 (avisos y botón de reenvío accesibles), NFR-007, NFR-009.
- **Dependencias:**
  - Funcionales: US-001.
  - Técnicas: componente de envío de correo.
  - Externas: herramienta de buzón de pruebas compatible con las tecnologías aprobadas.

#### Ejemplos

- Ana se registra a las 10:00 y abre el enlace a las 10:05: su cuenta queda **Verificada** y ya ve el botón "Publicar anuncio".
- Luis abre el enlace 30 horas después: ve "El enlace ha caducado" y el botón "Enviar un nuevo enlace".

#### Casos límite

- El usuario pide varios enlaces: solo vale el último; los anteriores quedan invalidados.
- El enlace se abre exactamente a las 24:00:00 de su emisión: se considera caducado (el plazo es estrictamente menor de 24 h).
- El enlace se abre con la sesión de otro usuario iniciada: verifica la cuenta del enlace, no la de la sesión.
- Se manipula el token del enlace: "Enlace no válido", sin dar más detalles.

#### Supuestos

- Los participantes de las pruebas pueden consultar el buzón de pruebas durante la demo.

---

### US-003 — Iniciar y cerrar sesión

> Como **estudiante verificado**, quiero **iniciar y cerrar sesión con mi correo y contraseña**, para **acceder a mis anuncios y conversaciones de forma segura**.

- **Necesidad:** el usuario tiene que identificarse para publicar, contactar o consultar sus datos.
- **Objetivo:** G-01, G-02.
- **Prioridad:** Must. Valor: todo el recorrido esencial pasa por aquí y es la base técnica de la autorización. · Riesgo: bajo, aunque un fallo de autenticación comprometería G-02 y NFR-001; por eso va en la primera iteración. · Dependencias: necesita US-001; de ella dependen US-004, US-005, US-006 y US-020.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 6 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-001
- **Plan inicial:** R1, IT-002, después de US-001 (planificación inicial del 14/10/2026).

#### Acceptance criteria

- **US-003-AC-01:** con credenciales correctas el usuario accede a su panel; con credenciales incorrectas ve un mensaje genérico que no indica qué dato falla.
- **US-003-AC-02:** tras 5 intentos fallidos seguidos, la cuenta se bloquea 15 minutos.
- **US-003-AC-03:** "Cerrar sesión" invalida la sesión, y volver atrás en el navegador no muestra datos privados.
- **US-003-AC-04:** la sesión caduca tras 2 horas de inactividad.

#### Reglas, restricciones y NFR

- **Reglas:** RN-02 (una cuenta no verificada puede entrar, pero con acciones limitadas).
- **Restricciones:** solo cuentas sintéticas (NFR-009); el mecanismo de sesión depende de las tecnologías aprobadas en la asignatura (SU-07).
- **NFR:** NFR-001, NFR-004, NFR-007, NFR-009.
- **Dependencias:** funcional US-001; técnica: mecanismo de sesión o autenticación definido por el TL.

---

### US-004 — Gestionar el perfil básico

> Como **estudiante verificado**, quiero **indicar mi nombre visible, mi titulación y los idiomas en los que me puedo comunicar**, para **que la otra parte sepa con quién está tratando**.

- **Necesidad:** mostrar solo "usuario verificado" genera poca confianza; un perfil mínimo ayuda a decidir.
- **Objetivo:** G-02, G-01.
- **Prioridad:** Should. Valor: aporta confianza, pero el recorrido esencial funciona sin él. · Riesgo: bajo. · Dependencias: necesita US-003; no bloquea a ninguna otra historia.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 5 h-p)
- **Incertidumbre:** Baja
- **Predecesoras:** US-003
- **Plan inicial:** fuera de la línea base (DEC-016)

#### Acceptance criteria

- **US-004-AC-01:** el usuario puede editar su nombre visible (2–40 caracteres), su titulación (lista cerrada) y sus idiomas (uno o varios).
- **US-004-AC-02:** en anuncios y conversaciones se ven el nombre visible, la titulación, los idiomas y el distintivo "Verificado UEx", pero nunca el correo (NFR-002).
- **US-004-AC-03:** si se guarda con un campo obligatorio vacío, se indica qué campo falta.

#### Reglas, restricciones y NFR

- **Reglas:** ninguna específica.
- **Restricciones:** la titulación se elige de una lista cerrada; solo datos sintéticos (NFR-009).
- **NFR:** NFR-002, NFR-004, NFR-007.
- **Dependencias:** funcional US-003.

---

### US-005 — Recuperar la contraseña

> Como **estudiante registrado**, quiero **restablecer mi contraseña desde mi correo institucional**, para **no perder el acceso a mi cuenta si la olvido**.

- **Necesidad:** sin recuperación, olvidar la contraseña obliga a crear otra cuenta, cosa que impide RN-01 porque el correo ya está registrado.
- **Objetivo:** G-01.
- **Prioridad:** Could. Valor: bajo en una demo de 6 semanas con cuentas sintéticas; se podría resolver de forma manual en la demo. · Riesgo: bajo, porque reutiliza el envío de correo de US-002 (TD-02). · Dependencias: necesita US-002 y US-003; ninguna depende de ella.
- **Estimación:** 3 h-p (consensuada en el Planning Poker del 10/10/2026; V0: 5 h-p)
- **Incertidumbre:** Baja (reutiliza el mecanismo de envío de US-002)
- **Predecesoras:** US-002, US-003
- **Plan inicial:** fuera de la línea base (DEC-016)

#### Acceptance criteria

- **US-005-AC-01:** al pedir la recuperación se muestra siempre el mismo mensaje, exista o no la cuenta.
- **US-005-AC-02:** el enlace recibido permite fijar una nueva contraseña que cumpla la política de US-001-AC-05; el enlace caduca en 1 hora y solo sirve una vez.
- **US-005-AC-03:** tras el cambio, las sesiones abiertas de esa cuenta se cierran.

#### Reglas, restricciones y NFR

- **Reglas:** RN-01 (el enlace solo se envía al correo institucional registrado).
- **Restricciones:** no se envían correos a direcciones reales; se usa el buzón de pruebas (EXT-02).
- **NFR:** NFR-001, NFR-007.
- **Dependencias:** funcionales US-002 y US-003; técnica: componente de envío de correo.

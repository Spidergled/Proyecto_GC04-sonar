# ErasmusNest — Requisitos no funcionales y reglas de negocio

**Baseline:** requisitos-v0 (Hito 2)

Este documento recoge los requisitos no funcionales (`NFR-XXX`) y las reglas de negocio (`RN-XX`) que afectan a varias historias. Cada `spec.md` indica cuáles aplican a cada historia.

## Requisitos no funcionales

| ID | Categoría | Requisito | Verificación | Objetivo |
|---|---|---|---|---|
| NFR-001 | Seguridad | Las contraseñas se guardan con un algoritmo de hash con sal y nunca en texto plano. Las sesiones caducan tras 2 horas de inactividad. | Inspección de la base de datos de demo; prueba de caducidad de sesión. | G-02 |
| NFR-002 | Privacidad | El correo y los datos de un usuario no se muestran a otros usuarios. Un anuncio muestra solo la zona, nunca la dirección exacta. | Revisión de pantallas y respuestas de la API con dos usuarios distintos. | G-02 |
| NFR-003 | Usabilidad | El recorrido esencial (publicar, o buscar y contactar) se completa en menos de 5 minutos sin ayuda. | Prueba con 5 participantes (M-01). | G-01 |
| NFR-004 | Accesibilidad | Las pantallas principales cumplen los criterios básicos de WCAG 2.1 nivel AA: contraste suficiente, etiquetas en todos los campos, uso completo con teclado y mensajes de error asociados al campo. | Revisión manual y herramienta automática de accesibilidad. | G-01 |
| NFR-005 | Rendimiento | Una búsqueda con filtros responde en menos de 2 segundos con el conjunto de datos de demo (≥ 100 anuncios). | Medición en el entorno de demo. | G-01 |
| NFR-006 | Compatibilidad | La aplicación funciona en las dos últimas versiones de Chrome, Firefox y Edge, y se adapta a pantallas de móvil a partir de 360 px de ancho. | Prueba manual en navegadores y en vista móvil. | G-01, G-04 |
| NFR-007 | Seguridad | Todas las entradas del usuario se validan en el servidor, además de en el cliente. | Pruebas con entradas inválidas enviadas directamente al servidor. | G-02 |
| NFR-008 | Instalabilidad | Un tercero puede instalar y arrancar la aplicación con los datos de demo siguiendo un guion de, como máximo, 10 pasos. | Prueba con una persona ajena al desarrollo (M-04). | G-04 |
| NFR-009 | Datos | Solo se usan datos sintéticos. Ningún dato personal, académico ni de contacto es real. | Revisión del conjunto de datos y de las evidencias. | Restricción |

## Reglas de negocio

| ID | Regla |
|---|---|
| RN-01 | Solo se pueden registrar correos del dominio institucional configurado. En la demo el dominio está configurado y todas las cuentas son sintéticas. |
| RN-02 | Una cuenta no verificada no puede publicar anuncios ni contactar con otros usuarios. |
| RN-03 | El enlace o código de verificación caduca a las 24 horas y solo puede usarse una vez. |
| RN-04 | En un anuncio, la fecha de fin debe ser posterior a la de inicio, la de inicio no puede ser anterior a hoy y el periodo debe durar entre 7 días y 12 meses. |
| RN-05 | El precio orientativo es un número entero entre 50 y 1.000 €/mes. |
| RN-06 | La zona se elige de una lista cerrada de zonas de las ciudades con campus UEx: Badajoz, Cáceres, Mérida y Plasencia. |
| RN-07 | Un usuario solo puede tener **un anuncio abierto** a la vez, porque subalquila su propia habitación. |
| RN-08 | Un anuncio cerrado no aparece en las búsquedas, no admite nuevos contactos y no puede reabrirse; para volver a ofrecer la habitación hay que publicar un anuncio nuevo. |
| RN-09 | Un usuario no puede contactar con su propio anuncio. |
| RN-10 | Un anuncio aparece en una búsqueda por fechas si su periodo disponible se **solapa al menos 1 día** con el periodo buscado. Se indica si cubre el periodo buscado **completo** o solo **parcialmente**. |
| RN-11 | Los mensajes de una conversación solo los ven sus dos participantes. |

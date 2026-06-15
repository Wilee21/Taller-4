# Registro de Defectos

## Defecto 01 — Validación de edad negativa (CORREGIDO)

* Escenario: Se intentaba registrar una persona con edad menor a cero.
* Comportamiento esperado: Retornar 'INVALID_AGE'.
* Corrección aplicada: Se agregó validación explícita para edades negativas en el caso de uso 'Registry'.
* Evidencia: Prueba 'negativeAge()'.

## Defecto 02 — Registro de personas fallecidas (CORREGIDO)

* Escenario: El sistema permitía registrar personas marcadas como no vivas.
* Comportamiento esperado: Retornar 'DEAD'.
* Corrección aplicada: Se incorporó validación del atributo 'alive'.
* Evidencia: Prueba 'dead()'.

## Defecto 03 — Detección de registros duplicados (CORREGIDO)

* Escenario: Dos personas podían registrarse utilizando el mismo identificador.
* Comportamiento esperado: Retornar 'DUPLICATED'.
* Corrección aplicada: Se verificó la existencia previa del identificador mediante 'existsById'.
* Evidencia: Pruebas 'duplicate()' y 'shouldReturnDuplicatedWhenExists()'.

## Defecto 04 — Manejo de errores de persistencia (CORREGIDO)

* Escenario: Una excepción de base de datos provocaba una falla no controlada.
* Comportamiento esperado: Propagar una excepción de negocio controlada ('IllegalStateException').
* Corrección aplicada: Se encapsularon los errores de persistencia dentro del bloque 'try-catch' del caso de uso.
* Evidencia: Prueba 'shouldHandlePersistenceException()'.

## Defecto 05 — Validación de identificadores inválidos (CORREGIDO)

* Escenario: El sistema aceptaba identificadores menores o iguales a cero.
* Comportamiento esperado: Retornar 'INVALID'.
* Corrección aplicada: Se agregó validación del campo 'id'.
* Evidencia: Prueba 'invalidId()'.

## Defecto 06 — Validación de mayoría de edad (CORREGIDO)

* Escenario: Se permitía registrar personas menores de edad.
* Comportamiento esperado: Retornar 'UNDERAGE'.
* Corrección aplicada: Se implementó la regla de negocio para verificar la edad mínima requerida.
* Evidencia: Prueba 'underage()'.

## Defecto 07 — Manejo de género inválido en el API REST (CORREGIDO)

* Escenario: El endpoint recibía valores de género no contemplados por el sistema.
* Comportamiento esperado: Responder con HTTP 400 Bad Request.
* Corrección aplicada: Se agregó manejo adecuado de errores de conversión y validación de datos de entrada.
* Evidencia: Prueba 'shouldReturn400ForInvalidGender()'.

## Defecto 08 — Validación de edad negativa en el API REST (CORREGIDO)

* Escenario: El endpoint aceptaba solicitudes con edad negativa.
* Comportamiento esperado: Responder con HTTP 400 Bad Request.
* Corrección aplicada: Se incorporaron restricciones de validación sobre el DTO de entrada.
* Evidencia: Prueba 'shouldReturn400ForNegativeAge()'.

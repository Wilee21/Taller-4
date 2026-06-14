# Registro de Defectos

## Defecto 01 — Edad negativa (CORREGIDO)
- Esperado: INVALID_AGE, Obtenido: VALID
- Causa: falta validación
- Estado: Resuelto

## Defecto 02 — Persona fallecida (CORREGIDO)
- Esperado: DEAD, Obtenido: VALID
- Estado: Resuelto

## Defecto 03 — Duplicados (CORREGIDO)
- Esperado: DUPLICATED, Obtenido: VALID
- Causa: existsById mal implementado
- Estado: Resuelto

## Defecto 04 — NPE en mock (CORREGIDO)
- Causa: constructor sin puerto
- Estado: Resuelto

## Defecto 05 — HTTP 500 por gender inválido (CORREGIDO)
- Esperado: 400, Obtenido: 500
- Causa: falta manejo de excepción
- Estado: Resuelto
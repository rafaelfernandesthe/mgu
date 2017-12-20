CREATE VIEW V_PRESTADORAS_PMS_MERCADO AS 
SELECT DISTINCT P.ID_PRESTADORA
FROM MERCADO_RELEVANTE MR,
  PRESTADORA P
WHERE MR.FK_ID_PRESTADORA = P.ID_PRESTADORA;
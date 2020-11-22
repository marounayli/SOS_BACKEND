UPDATE sensors
SET specification_id=subquery.specification_id
FROM (SELECT  specification_id FROM specification
ORDER BY RANDOM()
LIMIT 1) AS subquery
WHERE sensor_id = 4;
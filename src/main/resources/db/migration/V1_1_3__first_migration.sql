INSERT INTO sensors ( sensor_id, location_id, measurement_id)
SELECT  2, location.location_id, measurement.measurement_id FROM location, measurement
ORDER BY RANDOM()
LIMIT 1
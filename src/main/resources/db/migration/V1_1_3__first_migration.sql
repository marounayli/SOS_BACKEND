insert into timeseries(sensor_id ,measurement_date,measurement_value) Values(
    1,
   generate_series(timestamp '2019-01-01 00:00'
                     , timestamp '2019-01-15 00:00'
                     , interval  '1 day'),
   random()*(10-5)+0)
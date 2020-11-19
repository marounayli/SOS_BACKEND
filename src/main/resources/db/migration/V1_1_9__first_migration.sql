insert into timeseries(sensor_id ,measurement_date,measurement_value) Values(
    4,
   generate_series(timestamp '2019-11-19 11:00'
                     , timestamp '2019-11-19 11:30'
                     , interval  '1 minute'),
   random()*(10-5)+0)
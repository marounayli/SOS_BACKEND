insert into timeseries(sensor_id ,measurement_date,measurement_value) Values(
    3,
   generate_series(timestamp '2000-01-01 00:00'
                     , timestamp '2020-01-02 00:00'
                     , interval  '1 year'),
   random()*(10-5)+0)
insert into timeseries(sensor_id ,measurement_date,measurement_value) Values(
    2,
   generate_series(timestamp '2019-01-01 00:00'
                     , timestamp '2019-01-02 00:00'
                     , interval  '1 hour'),
   random()*(10-5)+0)
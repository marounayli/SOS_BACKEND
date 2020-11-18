CREATE EXTENSION "uuid-ossp";
CREATE TABLE timeseries (
    measurement_id uuid PRIMARY KEY,
    sensor_id int,
    measurement_date timestamp,
    measurement_value float
);

insert into timeseries(measurement_id , sensor_id ,measurement_date,measurement_value) Values(
   uuid_generate_v4(),
   1,
   generate_series(timestamp '2019-01-01 00:00'
                     , timestamp '2019-01-15 00:00'
                     , interval  '1 day'),
   random()*(10-5)+0)
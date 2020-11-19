CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE location (
      location_id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY NOT NULL,
      location point NOT NULL,
      country    varchar NOT NULL,
      region     varchar NOT NULL,
      city       varchar NOT NULL,
      areacode   varchar DEFAULT NULL
);

CREATE TABLE specification (
       specification_id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY NOT NULL,
       dimension varchar DEFAULT NULL,
       manufacturer varchar DEFAULT NULL,
       max_value varchar DEFAULT NULL,
       min_value varchar DEFAULT NULL,
       precision varchar DEFAULT NULL
);

CREATE TABLE measurement(
        measurement_id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY NOT NULL,
        type varchar NOT NULL,
        measurement_unit varchar NOT NULL
);
CREATE TABLE sensors (
         sensor_id int PRIMARY KEY NOT NULL,
         location_id uuid NOT NULL REFERENCES location(location_id),
         measurement_id uuid NOT NULL REFERENCES measurement(measurement_id),
         specification_id uuid DEFAULT NULL REFERENCES specification(specification_id),
         description varchar DEFAULT NULL
);

CREATE TABLE timeseries (
        measurement_id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY NOT NULL,
        sensor_id int NOT NULL REFERENCES sensors(sensor_id),
        measurement_date timestamp NOT NULL,
        measurement_value float NOT NULL
);
insert into location(location,country,region,city) Values
    (POINT(10.809003, 54.097834),'Lebanon','Kfardebian', 'Mzar'),
    (POINT(20.809003, 59.097834),'Lebanon','Kfardebian', 'Faraya'),
    (POINT(34.809003, 93.097834),'Lebanon','Kfardebian', 'Laklouk'),
    (POINT(20.809003, 92.097834),'Lebanon','Bekaa', 'Zahle');

insert into measurement(type, measurement_unit) VALUES
    ('mass','kg'),
    ('temperature','kelvin'),
    ('distance','meter'),
    ('temperature','Celsius');
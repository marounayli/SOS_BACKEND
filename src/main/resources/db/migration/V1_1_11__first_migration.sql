insert into specification(dimension,manufacturer,max_value,min_value,precision) Values
    ('3x10x1','Emerson ','100', '10','90%'),
    ('1x3x4','Panasonic ','200', '5','92%'),
    ('4x20x20','Siemens ','150', '20','84%'),
    ('30x10x20','Siemens','220', '15','88%');

ALTER TABLE sensors
ADD COLUMN time_diff varchar ;
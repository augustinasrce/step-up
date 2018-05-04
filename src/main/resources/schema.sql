create table users
(
   id integer not null,
   name varchar(255) not null,
   passport_number varchar(255) not null,
   primary key(id)
);

create table user_device(
  id integer not null,
  device_code varchar(100),
  user_id integer,
  primary key(id)
);

create table device_data (
  id integer not null,
  user_device_id integer,
  step_count integer,
  info_date date,
  primary key(id)
);
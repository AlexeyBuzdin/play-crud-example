# Init DB

# --- !Ups

create table HOTEL (
  ID bigint auto_increment,

  NAME varchar(60) not null,
  ADDRESS varchar(80) not null,

  primary key(ID),
  unique(NAME, ADDRESS)
);

create table APARTMENT_TYPE (
  ID bigint auto_increment,

  TITLE varchar(20) not null,

  primary key(ID),
  unique(TITLE)
);

create table APARTMENT (
  ID bigint auto_increment,
  HOTEL_ID bigint not null,
  APARTMENT_TYPE_ID bigint not null,

  TITLE varchar(40) not null,
  CAPACITY int not null,

  primary key(ID),
  foreign key(HOTEL_ID) references HOTEL (ID),
  foreign key(APARTMENT_TYPE_ID) references APARTMENT_TYPE (ID),
  unique(HOTEL_ID, TITLE),
  check(CAPACITY > 0 and CAPACITY < 10)
);

create table APARTMENT_PROPOSAL (
  ID bigint auto_increment,
  APARTMENT_ID bigint not null,

  DATE_FROM date,
  DATE_TO date,
  PRICE bigint not null,

  primary key(ID),
  foreign key(APARTMENT_ID) references APARTMENT(ID),
  check(PRICE >= 0)
);

create table CLIENT (
  ID bigint auto_increment,

  FIRST_NAME varchar(40) not null,
  LAST_NAME varchar(40) not null,
  EMAIL varchar(40) not null,

  primary key(ID),
  unique(FIRST_NAME, LAST_NAME, EMAIL)
);

create table APARTMENT_HISTORY (
  ID bigint auto_increment,
  CLIENT_ID bigint not null,
  APARTMENT_PROPOSAL_ID bigint not null,

  BOOKED_FROM date not null,
  BOOKED_TO date not null,

  primary key(ID),
  foreign key(CLIENT_ID) references CLIENT(ID),
  foreign key(APARTMENT_PROPOSAL_ID) references APARTMENT_PROPOSAL(ID),
  unique(CLIENT_ID, APARTMENT_PROPOSAL_ID, BOOKED_FROM, BOOKED_TO)
);

create table HOTEL_SERVICE (
  ID bigint auto_increment,

  NAME varchar(100) not null,

  primary key(ID),
  unique(NAME)
);

create table ADDITIONAL_SERVICE (
  ID bigint auto_increment,
  HOTEL_SERVICE_ID bigint not null,
  APARTMENT_HISTORY_ID bigint not null,

  PRICE bigint not null,

  primary key(ID),
  foreign key(HOTEL_SERVICE_ID) references HOTEL_SERVICE(ID),
  foreign key(APARTMENT_HISTORY_ID) references APARTMENT_HISTORY(ID),
);

insert into HOTEL(NAME, ADDRESS) values
  ('Baltic Beach Hotel', 'Jurmala, Slokas street 32'),
  ('Raddisson Blue Hotel', 'Riga, Kandavas street 1');

insert into CLIENT(FIRST_NAME, LAST_NAME, EMAIL) values
  ('Alexey', 'Zvonorev', 'Alexey.Zvonorev@gmail.com'),
  ('Eugene', 'Koromohin', 'Eugene.Koromohin@gmail.com'),
  ('Vladimir', 'Svjatov', 'Vladimir.Svjatov@gmail.com'),
  ('Mila', 'Vodolejeva', 'Mila.Vodolejeva@inbox.lv'),
  ('Zurab', 'Afonosjan', 'Zurab.Afonosjan@yandex.com'),
  ('Irena', 'Mitina', 'Irena.Mitina@inbox.lv');

insert into HOTEL_SERVICE(NAME) values
  ('Extra bed'), ('Breakfast'), ('Lunch'), ('Dinner'),
  ('Internet'), ('Cable TV'), ('Other');

-- http://software.goreserva.com/help/site-admin/164-how-to-manage-room-types-and-labels
insert into APARTMENT_TYPE(TITLE) values
  ('Single Room'), ('Double Room'), ('Female Dorm'), ('Family Room'), ('Deluxe King Room');

insert into APARTMENT(HOTEL_ID, APARTMENT_TYPE_ID, TITLE, CAPACITY) values
  (1, 1, '101', 1), (1, 1, '102', 1), (1, 1, '103', 1), (1, 1, '104', 1),
  (1, 2, '201', 2), (1, 2, '202', 2), (1, 2, '203', 2), (1, 4, '204', 4),
  (1, 5, '301', 2),

  (2, 1, 'A-101', 1), (2, 1, 'A-102', 1), (2, 1, 'A-103', 1), (2, 1, 'A-104', 1),
  (2, 2, 'A-105', 2), (2, 2, 'A-106', 2), (2, 2, 'A-107', 2), (2, 4, 'A-108', 4),
  (2, 2, 'B-101', 2), (2, 2, 'B-102', 2), (2, 2, 'B-103', 2), (2, 4, 'B-104', 4),
  (2, 5, 'B-201', 2);

insert into APARTMENT_PROPOSAL(APARTMENT_ID, DATE_FROM, DATE_TO, PRICE) values
  (1, '2013-01-01', '2013-09-30', 2000), (2, '2013-01-01', '2013-09-30', 2000),
  (3, '2013-01-01', '2013-09-30', 2000), (4, '2013-01-01', '2013-09-30', 2500),
  (5, '2013-01-01', '2013-09-30', 3500), (6, '2013-01-01', '2013-09-30', 3500),
  (7, '2013-01-01', '2013-09-30', 3500), (8, '2013-01-01', '2013-09-30', 4500),
  (9, '2013-01-01', '2013-09-30', 10000),

  (10, '2013-01-01', '2013-06-30', 1999), (11, '2013-01-01', '2013-06-30', 1999),
  (12, '2013-01-01', '2013-06-30', 1999), (13, '2013-01-01', '2013-06-30', 1999),
  (14, '2013-01-01', '2013-06-30', 2499), (15, '2013-01-01', '2013-06-30', 2499),
  (16, '2013-01-01', '2013-06-30', 2499), (17, '2013-01-01', '2013-06-30', 5499),
  (18, '2013-01-01', '2013-06-30', 2499), (19, '2013-01-01', '2013-06-30', 2499),
  (20, '2013-01-01', '2013-06-30', 2499), (21, '2013-01-01', '2013-06-30', 5499),
  (22, '2013-01-01', '2013-06-30', 9999);

# --- !Downs

drop table ADDITIONAL_SERVICE;
drop table APARTMENT;
drop table APARTMENT_HISTORY;
drop table APARTMENT_PROPOSAL;
drop table APARTMENT_TYPE;
drop table CLIENT;
drop table HOTEL;
drop table HOTEL_SERVICE;
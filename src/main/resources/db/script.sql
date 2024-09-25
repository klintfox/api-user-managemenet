create table app_user ( 
   id  varchar(255)  primary key not null,
   name varchar(50) not null, 
   email varchar(255) not null, 
   password varchar(20)  not null, 
   created timestamp null, 
   modified timestamp null,
   last_login timestamp null,
   is_active null,
   token varchar(300) not null
);

create table user_phones ( 
   id INT primary key not null,
   number  int null, 
   city_code int null, 
   country_code int null, 
   fk_user varchar(300),
  foreign key (fk_user ) references user(id)
);

INSERT INTO app_user (id, name, email, password, created, modified, last_login, is_active, token) VALUES ('9cc369df-7f5f-4308-a8f3-10e3013f8630', 'klint','klint@hotmail.cl','Program26','2024-08-19 10:00:00','2024-09-20 10:00:00','2024-09-24 10:00:00','yes', 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJrbGludXhKV1QiLCJzdWIiOiJrbGludEByb2RyaWd1ZXouY2wiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNTk4MDMwNTMzLCJleHAiOjE1OTgwMzExMzN9.z6ypiskbtLJ2QhHutE4KI5oAlYZzK0wxCEU_s1t6B4yO99Wa5OXiFE2tOKDr_COB4ICCPWHWnyM_V1u0kWVzTg');
INSERT INTO user_phones (id, phone_number, city_code, country_code, fk_user) VALUES (1, '987654321', 10, 57, '9cc369df-7f5f-4308-a8f3-10e3013f8630');
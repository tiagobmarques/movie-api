DROP TABLE IF EXISTS movie;
create table movie(
    id bigint auto_increment,
    premium_year varchar(40),
    title varchar(255),
    studio varchar(255),
    producer varchar(255),
    winner boolean,
    primary key (id)
);
INSERT INTO movie(premium_year, title, studio, producer, winner)
SELECT years,title,studios,producers,case when winner = 'yes' then true else false end as winners
FROM CSVREAD('classpath:movielist.csv', null, 'fieldSeparator=;');
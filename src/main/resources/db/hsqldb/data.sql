-- -- One admin user, named admin1 with passwor 4dm1n and authority admin
-- INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
-- -- One owner user, named owner1 with passwor 0wn3r
-- INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

-- -- One vet user, named vet1 with passwor v3t
-- INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

-- INSERT INTO users(username,password,enabled) VALUES ('jesmarver','admin',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (4,'jesmarver','owner');

-- INSERT INTO users(username,password,enabled) VALUES ('raumonmar1','admin',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (5,'raumonmar1','owner');

-- INSERT INTO users(username,password,enabled) VALUES ('samalbort','s4m4lb0rt',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (6,'samalbort','owner');

-- INSERT INTO users(username,password,enabled) VALUES ('josreimun','admin',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (7,'josreimun','owner');

-- INSERT INTO users(username,password,enabled) VALUES ('migybaman','admin',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (8,'migybaman','owner');

-- INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
-- INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
-- INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
-- INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
-- INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
-- INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

-- INSERT INTO specialties VALUES (1, 'radiology');
-- INSERT INTO specialties VALUES (2, 'surgery');
-- INSERT INTO specialties VALUES (3, 'dentistry');

-- INSERT INTO vet_specialties VALUES (2, 1);
-- INSERT INTO vet_specialties VALUES (3, 2);
-- INSERT INTO vet_specialties VALUES (3, 3);
-- INSERT INTO vet_specialties VALUES (4, 2);
-- INSERT INTO vet_specialties VALUES (5, 1);

-- INSERT INTO types VALUES (1, 'cat');
-- INSERT INTO types VALUES (2, 'dog');
-- INSERT INTO types VALUES (3, 'lizard');
-- INSERT INTO types VALUES (4, 'snake');
-- INSERT INTO types VALUES (5, 'bird');
-- INSERT INTO types VALUES (6, 'hamster');
-- INSERT INTO types VALUES (7, 'turtle');

-- INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
-- INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
-- INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
-- INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
-- INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
-- INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
-- INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
-- INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
-- INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
-- INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
-- INSERT INTO owners VALUES (11, 'Jesus', 'Martin', 'Avd Reina Mercedes', 'Sevilla', '674582995', 'jesmarver');
-- INSERT INTO owners VALUES (12, 'Raúl', 'Montalbán', 'Calle Joaquín Turina', 'Sevilla', '639830417', 'raumonmar1');
-- INSERT INTO owners VALUES (13, 'Samuel', 'Albalat', 'Villa Conflicto N5', 'Sevilla', '6789546608', 'samalbort');
-- INSERT INTO owners VALUES (14, 'Jose Antonio', 'Reina', 'Avd Reina Mercedes', 'Sevilla', '674400994', 'josreimun');
-- INSERT INTO owners VALUES (15, 'Miguel', 'Ybarra', 'Avd Reina Mercedes', 'Sevilla', '626083258', 'migybaman');

-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Perri', '2019-05-25', 1, 11);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Firu', '2019-04-22', 2, 12);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Floa', '1981-06-22', 7, 13);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Bobi', '2019-06-08', 2, 14);
-- INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Tiza', '2020-06-20', 2, 15);

-- INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
-- INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
-- -- INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
-- INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');


INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES --, birth_date
(1,'admin', 'admin', 'admin@admin.com','2013-01-03', 1);--, '2013-01-03'
INSERT INTO authorities(id,user_id,username,authority) VALUES (1,1,'admin', 'admin');

INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES 
(2,'user', 'user', 'user@user.com','1950-01-09', 1);
INSERT INTO authorities(id,user_id,username,authority) VALUES (2,2,'user', 'user');



INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES 
(3,'user2', 'user2', 'user2@user2.com','1951-01-09', 1);
INSERT INTO authorities(id,user_id,username,authority) VALUES (3,3,'user', 'user');

-- INSERT INTO proposals(id,user_id,sender_username,receiver_username,proposal_type) VALUES (1,2,'user', 'admin','PARTIDA'); Funciona

INSERT INTO friends(id,user_id,username,friend_username) VALUES (1,2,'user', 'admin');
INSERT INTO friends(id,user_id,username,friend_username) VALUES (2,2,'user', 'user2');

INSERT INTO games(start_time, end_time, has_scenes, max_players,min_players, state, username,user_id) VALUES --, winner_id 
('2022-11-10', '2022-11-10', 0, 4, 2, 'TERMINADO', 'admin',1); --,1



INSERT INTO players(glory, gold, wounds, evasion, hero_type, user_id, game_id) VALUES (5, 10, 20, TRUE, 'MAGO_MASCULINO',1, 1);
INSERT INTO players(glory, gold, wounds, evasion, hero_type, user_id, game_id) VALUES (4, 9, 20, TRUE, 'MAGO_MASCULINO',2, 1);

INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('COMPANERO_LOBO', 10, 'MAGO_MASCULINO');

INSERT INTO ABILITY_CARD_IN_GAME(damage, ability_card_id, player_id) VALUES (10, 1, 1);

INSERT INTO ABILITY_CARDS_ABILITY_CARD_IN_GAME(ability_card_id, ability_card_in_game_id) VAlUES(1,1);

INSERT INTO PLAYERS_ABILITY_PILE(players_id, ability_pile_id) VALUES (1, 1);

INSERT INTO enemies(endurance, glory, type) VALUES (10,10,'HONDERO');




INSERT INTO enemy_in_game(endurance, enemy_id, game_id) VALUES (10, 1,1);


INSERT INTO GAMES_MONSTER_FIELD(game_id, monster_field_id) VALUES(1,1);




--INSERT INTO GAMES_PLAYERS(game_id, players_id) VALUES(1,1);
--INSERT INTO GAMES_PLAYERS(game_id, players_id) VALUES(1,2);

INSERT INTO MARKET_CARD(price, profiency, type) VALUES(10, 10, 'DAGA_ELFICA');

INSERT INTO MARKET_CARD_IN_GAME (player_id) VALUES (1);

INSERT INTO PLAYERS_ABILITY_HAND(players_id, ability_hand_id) VALUES(1,1);

INSERT INTO PLAYERS_MARKET_HAND (players_id, market_hand_id) VALUES(1,1);





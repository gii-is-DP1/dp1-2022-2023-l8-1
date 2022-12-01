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

INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES 
(4,'user3', 'user3', 'user3@user3.com','1952-01-09', 1);
INSERT INTO authorities(id,user_id,username,authority) VALUES (4,4,'user', 'user');

-- INSERT INTO proposals(id,user_id,sender_username,receiver_username,proposal_type) VALUES (1,2,'user', 'admin','PARTIDA'); Funciona

INSERT INTO friends(id,user1_id,user2_id,friend_state) VALUES (1,1,2,0);
INSERT INTO friends(id,user1_id,user2_id,friend_state) VALUES (2,2,3,1);
INSERT INTO friends(id,user1_id,user2_id,friend_state) VALUES (3,3,1,1);

INSERT INTO games(start_time, end_time, has_scenes, max_players,min_players, state, username,user_id) VALUES --, winner_id 
('2022-11-10', '2022-11-10', 0, 4, 2, 'TERMINADO', 'admin',1); --,1

INSERT INTO players(glory, gold, wounds, evasion, profiency, hero_type, user_id, game_id) VALUES (5, 10, 20, TRUE, 'MAGIA', 'MAGO_MASCULINO', 1, 1);
INSERT INTO players(glory, gold, wounds, evasion, profiency, hero_type, user_id, game_id) VALUES (4, 9, 20, TRUE, 'MAGIA', 'MAGO_MASCULINO',2, 1);

-- EXPLORADORES    
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('COMPANERO_LOBO', 2, 'EXPLORADOR_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_CERTERO', 3, 'EXPLORADOR_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('EN_LA_DIANA', 4, 'EXPLORADOR_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('LLUVIA_DE_FLECHAS', 2, 'EXPLORADOR_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('RECOGER_FLECHAS', 0, 'EXPLORADOR_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('SUPERVIVENCIA', 0, 'EXPLORADOR_MASCULINO');


INSERT INTO players(glory, gold, wounds, evasion, hero_type, user_id, game_id) VALUES (5, 10, 20, TRUE, 'MAGO_MASCULINO',1, 1);
INSERT INTO players(glory, gold, wounds, evasion, hero_type, user_id, game_id) VALUES (4, 9, 20, TRUE, 'MAGO_FEMENINO',2, 1);

INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('COMPANERO_LOBO', 2, 'EXPLORADOR_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_CERTERO', 3, 'EXPLORADOR_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('EN_LA_DIANA', 4, 'EXPLORADOR_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('LLUVIA_DE_FLECHAS', 2, 'EXPLORADOR_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('RECOGER_FLECHAS', 0, 'EXPLORADOR_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('SUPERVIVENCIA', 0, 'EXPLORADOR_FEMENINO');


-- GUERREROS
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ATAQUE_BRUTAL', 3, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('CARGA_CON_ESCUDO', 2, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DOBLE_ESPADAZO', 2, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ESCUDO', 0, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ESPADAZO', 1, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('PASO_ATRAS', 0, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TODO_O_NADA', 1, 'GUERRERO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('VOZ_DE_ALIENTO', 0, 'GUERRERO_MASCULINO');

INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ATAQUE_BRUTAL', 3, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('CARGA_CON_ESCUDO', 2, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DOBLE_ESPADAZO', 2, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ESCUDO', 0, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ESPADAZO', 1, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('PASO_ATRAS', 0, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TODO_O_NADA', 1, 'GUUERRERO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('VOZ_DE_ALIENTO', 0, 'GUUERRERO_FEMENINO');

-- MAGOS
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('AURA_PROTECTORA', 0, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('BOLA_DE_FUEGO', 2, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_GELIDO', 1, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('FLECHA_CORROSIVA', 1, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ORBE_CURATIVO', 0, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('RECONSTITUCION', 0, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TORRENTE_DE_LUZ', 2, 'MAGO_MASCULINO');

INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('AURA_PROTECTORA', 0, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('BOLA_DE_FUEGO', 2, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_GELIDO', 1, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('FLECHA_CORROSIVA', 1, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ORBE_CURATIVO', 0, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('RECONSTITUCION', 0, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TORRENTE_DE_LUZ', 2, 'MAGO_FEMENINO');

-- PICAROS
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('AL_CORAZON', 4, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('EN_LAS_SOMBRAS', 1, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ENGANAR', 0, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ROBAR_BOLSILLOS', 0, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('SAQUEO', 1, 'PICARO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TRAMPA', 0, 'PICARO_MASCULINO');

INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('AL_CORAZON', 4, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('EN_LAS_SOMBRAS', 1, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ENGANAR', 0, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('ROBAR_BOLSILLOS', 0, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('SAQUEO', 1, 'PICARO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TRAMPA', 0, 'PICARO_FEMENINO');

INSERT INTO ABILITY_CARD_IN_GAME(damage, ability_card_id, player_id) VALUES (10, 1, 1);

INSERT INTO ABILITY_CARDS_ABILITY_CARD_IN_GAME(ability_card_id, ability_card_in_game_id) VAlUES(1,1);

INSERT INTO PLAYERS_ABILITY_PILE(players_id, ability_pile_id) VALUES (1, 1);

INSERT INTO enemies(endurance, glory, type) VALUES (2,1,'HONDERO');
INSERT INTO enemies(endurance, glory, type) VALUES (3,2,'PIQUERO');
INSERT INTO enemies(endurance, glory, type) VALUES (3,1,'CHAMAN');
INSERT INTO enemies(endurance, glory, type) VALUES (4,2,'GUERRERO');
INSERT INTO enemies(endurance, glory, type) VALUES (5,3,'NIGROMANTE');
INSERT INTO enemies(endurance, glory, type) VALUES (6,4,'BERSEKER');
INSERT INTO enemies(endurance, glory, type) VALUES (8,8,'GURDRUG');
INSERT INTO enemies(endurance, glory, type) VALUES (9,9,'ROGHKILLER');
INSERT INTO enemies(endurance, glory, type) VALUES (10,10,'SHRIEKKNIFER');

INSERT INTO enemy_in_game(endurance, enemy_id, game_id) VALUES (10, 1,1);

INSERT INTO GAMES_MONSTER_FIELD(game_id, monster_field_id) VALUES(1,1);

--INSERT INTO GAMES_PLAYERS(game_id, players_id) VALUES(1,1);
--INSERT INTO GAMES_PLAYERS(game_id, players_id) VALUES(1,2);


INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(3, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'DAGA_ELFICA', 2);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(8, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'POCION_CURATIVA', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(4, 'MELEE', 'DISTANCIA', 'PERICIA', 'INCOMPATIBLE', 'PIEDRA_DE_AMOLAR', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(5, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA' , 'VIAL_DE_CONJURACION', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(3, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'ELIXIR_DE_CONCENTRACION', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(3, 'INCOMPATIBLE', 'DISTANCIA', 'INCOMPATIBLE', 'MAGIA', 'CAPA_ELFICA', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(4, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'ARMADURA_DE_PLACAS', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(5, 'MELEE', 'INCOMPATIBLE', 'INCOMPATIBLE', 'INCOMPATIBLE', 'ALABARDA_ORCA', 4);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(5, 'INCOMPATIBLE', 'DISTANCIA', 'INCOMPATIBLE','INCOMPATIBLE', 'ARCO_COMPUESTO', 4);


INSERT INTO MARKET_CARD_IN_GAME (game_id, player_id,market_card_id) VALUES (1,1,1);
INSERT INTO MARKET_CARD_IN_GAME (game_id, player_id,market_card_id) VALUES (1,1,2);
INSERT INTO MARKET_CARD_IN_GAME (game_id, player_id,market_card_id) VALUES (1,1,3);

INSERT INTO PLAYERS_ABILITY_HAND(players_id, ability_hand_id) VALUES(1,1);

INSERT INTO PLAYERS_MARKET_HAND (players_id, market_hand_id) VALUES(1,1);



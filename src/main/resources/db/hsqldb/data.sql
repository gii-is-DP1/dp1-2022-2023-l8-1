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
INSERT INTO ability_cards(ability_type, damage, hero_type, condition_type) VALUES ('ORBE_CURATIVO', 0, 'MAGO_MASCULINO','USO_UNICO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('RECONSTITUCION', 0, 'MAGO_MASCULINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('TORRENTE_DE_LUZ', 2, 'MAGO_MASCULINO');

INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('AURA_PROTECTORA', 0, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('BOLA_DE_FUEGO', 2, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('DISPARO_GELIDO', 1, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('FLECHA_CORROSIVA', 1, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_FEMENINO');
INSERT INTO ability_cards(ability_type, damage, hero_type, condition_type) VALUES ('ORBE_CURATIVO', 0, 'MAGO_FEMENINO','USO_UNICO');
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

INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (1, 1);

INSERT INTO ABILITY_CARDS_ABILITY_CARD_IN_GAME(ability_card_id, ability_card_in_game_id) VAlUES(1,1);

INSERT INTO PLAYERS_ABILITY_PILE(players_id, ability_pile_id) VALUES (1, 1);

INSERT INTO enemies(endurance, glory, type) VALUES (2,1,'HONDERO');
INSERT INTO enemies(endurance, glory, type, condition_type) VALUES (3,2,'PIQUERO', 'REGENERACION');
INSERT INTO enemies(endurance, glory, type, condition_type) VALUES (3,1,'CHAMAN', 'MAGO_1');
INSERT INTO enemies(endurance, glory, type, condition_type) VALUES (4,2,'GUERRERO', 'BOTIN_EXTRA');
INSERT INTO enemies(endurance, glory, type, condition_type) VALUES (5,3,'NIGROMANTE', 'MAGO_2');
INSERT INTO enemies(endurance, glory, type) VALUES (6,4,'BERSEKER');
INSERT INTO enemies(endurance, glory, type) VALUES (8,8,'GURDRUG');
INSERT INTO enemies(endurance, glory, type) VALUES (9,9,'ROGHKILLER');
INSERT INTO enemies(endurance, glory, type) VALUES (10,10,'SHRIEKKNIFER');

INSERT INTO enemy_in_game(enemy_id, game_id) VALUES (1,1);

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



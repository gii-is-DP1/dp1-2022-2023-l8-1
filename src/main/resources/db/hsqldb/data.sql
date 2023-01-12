INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES --, birth_date
(1,'admin', 'admin', 'admin@admin.com','2013-01-03', 1);--, '2013-01-03'
INSERT INTO authorities(id,user_id,username,authority) VALUES (1,1,'admin', 'admin');

INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES 
(2,'user', 'user', 'user@user.com','1950-01-09', 1);
INSERT INTO authorities(id,user_id,username,authority) VALUES (2,2,'user', 'user');

INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES 
(3,'user2', 'user2', 'user2@user2.com','1951-01-09', 1);
INSERT INTO authorities(id,user_id,username,authority) VALUES (3,3,'user2', 'user');

INSERT INTO users(id,username, password, email, birth_date, enabled) VALUES 
(4,'user3', 'user3', 'user3@user3.com','1952-01-09', 1);
INSERT INTO authorities(id,user_id,username,authority) VALUES (4,4,'user3', 'user');

-- INSERT INTO proposals(id,user_id,sender_username,receiver_username,proposal_type) VALUES (1,2,'user', 'admin','PARTIDA'); Funciona

INSERT INTO friends(id,user1_id,user2_id,friend_state) VALUES (1,1,2,0);
INSERT INTO friends(id,user1_id,user2_id,friend_state) VALUES (2,2,3,1);
INSERT INTO friends(id,user1_id,user2_id,friend_state) VALUES (3,3,1,1);

INSERT INTO games(start_time, end_time, has_scenes, max_players,min_players, state, username,user_id) VALUES --, winner_id 
('2022-11-10', '2022-11-10', 0, 4, 2, 'TERMINADO', 'admin',1); --,1

INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES (5, 50, 20, 0, TRUE, 'MAGIA', 'INCOMPATIBLE', 'MAGO_MASCULINO', 1, 1);
INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES (5, 9, 20, 0, TRUE, 'MAGIA', 'INCOMPATIBLE','MAGO_MASCULINO',2, 1);
INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES (4, 9, 20, 0, TRUE, 'MELEE', 'INCOMPATIBLE','GUERRERO_FEMENINO',3, 1);
INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES (4, 9, 20, 0, TRUE, 'PERICIA', 'DISTANCIA','PICARO_MASCULINO',4, 1);


-- EXPLORADORES    
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('COMPANERO_LOBO', 2, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_CERTERO', 3, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_CERTERO', 3, 'EXPLORADOR_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('EN_LA_DIANA', 4, 'EXPLORADOR_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('LLUVIA_DE_FLECHAS', 2, 'EXPLORADOR_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('LLUVIA_DE_FLECHAS', 2, 'EXPLORADOR_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('RECOGER_FLECHAS', 0, 'EXPLORADOR_MASCULINO', false);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('RECOGER_FLECHAS', 0, 'EXPLORADOR_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('SUPERVIVENCIA', 0, 'EXPLORADOR_MASCULINO', true);


INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('COMPANERO_LOBO', 2, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_CERTERO', 3, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_CERTERO', 3, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_RAPIDO', 1, 'EXPLORADOR_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('EN_LA_DIANA', 4, 'EXPLORADOR_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('LLUVIA_DE_FLECHAS', 2, 'EXPLORADOR_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('LLUVIA_DE_FLECHAS', 2, 'EXPLORADOR_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('RECOGER_FLECHAS', 0, 'EXPLORADOR_FEMENINO', false);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('RECOGER_FLECHAS', 0, 'EXPLORADOR_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('SUPERVIVENCIA', 0, 'EXPLORADOR_FEMENINO', false);


-- GUERREROS
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_BRUTAL', 3, 'GUERRERO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_BRUTAL', 3, 'GUERRERO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('CARGA_CON_ESCUDO', 2, 'GUERRERO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DOBLE_ESPADAZO', 2, 'GUERRERO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DOBLE_ESPADAZO', 2, 'GUERRERO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESCUDO', 0, 'GUERRERO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESCUDO', 0, 'GUERRERO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PASO_ATRAS', 0, 'GUERRERO_MASCULINO', false);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PASO_ATRAS', 0, 'GUERRERO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('TODO_O_NADA', 1, 'GUERRERO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('VOZ_DE_ALIENTO', 0, 'GUERRERO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_BRUTAL', 3, 'GUERRERO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_BRUTAL', 3, 'GUERRERO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('CARGA_CON_ESCUDO', 2, 'GUERRERO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DOBLE_ESPADAZO', 2, 'GUERRERO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DOBLE_ESPADAZO', 2, 'GUERRERO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESCUDO', 0, 'GUERRERO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESCUDO', 0, 'GUERRERO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ESPADAZO', 1, 'GUERRERO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PASO_ATRAS', 0, 'GUERRERO_FEMENINO', false);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PASO_ATRAS', 0, 'GUERRERO_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('TODO_O_NADA', 1, 'GUERRERO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('VOZ_DE_ALIENTO', 0, 'GUERRERO_FEMENINO', false);

-- MAGOS
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('AURA_PROTECTORA', 0, 'MAGO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BOLA_DE_FUEGO', 2, 'MAGO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_GELIDO', 1, 'MAGO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_GELIDO', 1, 'MAGO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('FLECHA_CORROSIVA', 1, 'MAGO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, condition_type ,target) VALUES ('ORBE_CURATIVO', 0, 'MAGO_MASCULINO', 'USO_UNICO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('RECONSTITUCION', 0, 'MAGO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('TORRENTE_DE_LUZ', 2, 'MAGO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('AURA_PROTECTORA', 0, 'MAGO_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BOLA_DE_FUEGO', 2, 'MAGO_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_GELIDO', 1, 'MAGO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('DISPARO_GELIDO', 1, 'MAGO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('FLECHA_CORROSIVA', 1, 'MAGO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('GOLPE_DE_BASTON', 1, 'MAGO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, condition_type, target) VALUES ('ORBE_CURATIVO', 0, 'MAGO_FEMENINO', 'USO_UNICO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('PROYECTIL_IGNEO', 2, 'MAGO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('RECONSTITUCION', 0, 'MAGO_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('TORRENTE_DE_LUZ', 2, 'MAGO_FEMENINO', true);

-- PICAROS
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('AL_CORAZON', 4,'PICARO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('AL_CORAZON', 4,'PICARO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('EN_LAS_SOMBRAS', 1, 'PICARO_MASCULINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('EN_LAS_SOMBRAS', 1, 'PICARO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ENGANAR', 0, 'PICARO_MASCULINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ROBAR_BOLSILLOS', 0, 'PICARO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('SAQUEO', 0, 'PICARO_MASCULINO', false);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('SAQUEO', 0, 'PICARO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('TRAMPA', 0, 'PICARO_MASCULINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('AL_CORAZON', 4,'PICARO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('AL_CORAZON', 4,'PICARO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ATAQUE_FURTIVO', 2, 'PICARO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('BALLESTA_PRECISA', 2, 'PICARO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('EN_LAS_SOMBRAS', 1, 'PICARO_FEMENINO', true);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('EN_LAS_SOMBRAS', 1, 'PICARO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ENGANAR', 0, 'PICARO_FEMENINO', true);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('ROBAR_BOLSILLOS', 0, 'PICARO_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('SAQUEO', 0, 'PICARO_FEMENINO', false);
INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('SAQUEO', 0, 'PICARO_FEMENINO', false);

INSERT INTO ability_cards(ability_type, damage,  hero_type, target) VALUES ('TRAMPA', 0, 'PICARO_FEMENINO', false);
-- Cartas de Mercado como Cartas de Habilidad

INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'DAGA_ELFICA', 2, true);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'DAGA_ELFICA', 2, true);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'POCION_CURATIVA', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'POCION_CURATIVA', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'POCION_CURATIVA', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'PIEDRA_DE_AMOLAR', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target)
                    VALUES('SIN_HEROE', 'VIAL_DE_CONJURACION', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target)
                    VALUES('SIN_HEROE', 'ELIXIR_DE_CONCENTRACION', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target)
                    VALUES('SIN_HEROE', 'ELIXIR_DE_CONCENTRACION', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'CAPA_ELFICA', 0, true);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target)
                    VALUES('SIN_HEROE', 'ARMADURA_DE_PLACAS', 0, false);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                    VALUES('SIN_HEROE', 'ALABARDA_ORCA', 4, true);
INSERT INTO ability_cards(hero_type,  ability_type, damage, target) 
                     VALUES('SIN_HEROE', 'ARCO_COMPUESTO', 4, true);

--INSERT INTO ABILITY_CARD_IN_GAME(damage, asset, ability_card_id, player_id) VALUES (10, 1, 1);--EL ATRIBUTO DAMAGE HAY QUE QUITARLO

INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (1, 1);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (2, 1);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (3, 1);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (4, 1);

INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (15, 2);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (16, 2);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (17, 2);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (18, 2);

INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (31, 3);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (32, 3);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (33, 3);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (34, 3);

INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (49, 4);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (50, 4);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (51, 4);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (52, 4);


-- Cartas de Enemigos Normales
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (2, 1, 1, 'HONDERO');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (2, 1, 1, 'HONDERO');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (2, 1, 0, 'HONDERO');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (2, 1, 0, 'HONDERO');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (2, 1, 0, 'HONDERO');

INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 2, 1, 'PIQUERO', 'REGENERACION');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 2, 1, 'PIQUERO', 'REGENERACION');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 2, 0, 'PIQUERO', 'REGENERACION');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 2, 0, 'PIQUERO', 'REGENERACION');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 2, 0, 'PIQUERO', 'REGENERACION');

INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 1, 2, 'CHAMAN', 'MAGO_1');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (3, 2, 1, 'CHAMAN', 'MAGO_1');

INSERT INTO enemies(endurance, glory, gold, type ) VALUES (4, 2, 1, 'GUERRERO');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (4, 2, 0, 'GUERRERO');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (4, 2, 0, 'GUERRERO');

INSERT INTO enemies(endurance, glory, gold, type) VALUES (4, 3, 2, 'GUERRERO');
INSERT INTO enemies(endurance, glory, gold, type) VALUES (4, 3, 2, 'GUERRERO');
INSERT INTO enemies(endurance, glory, gold, type) VALUES (4, 2, 2, 'GUERRERO');
INSERT INTO enemies(endurance, glory, gold, type) VALUES (4, 2, 1, 'GUERRERO');
INSERT INTO enemies(endurance, glory, gold, type) VALUES (4, 2, 1, 'GUERRERO');

INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (5, 4, 2, 'NIGROMANTE', 'MAGO_2');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (5, 3, 2, 'NIGROMANTE', 'MAGO_2');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (5, 3, 2, 'NIGROMANTE', 'MAGO_2');
INSERT INTO enemies(endurance, glory, gold, type, condition_type) VALUES (5, 3, 0, 'NIGROMANTE', 'MAGO_2');

INSERT INTO enemies(endurance, glory, gold, type ) VALUES (6, 4, 1,'BERSERKER');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (6, 4, 0,'BERSERKER');
INSERT INTO enemies(endurance, glory, gold, type ) VALUES (6, 4, 0,'BERSERKER');

-- Cartas de Señores de la Guerra
INSERT INTO enemies(endurance, glory, gold, type, is_boss) VALUES (8, 0, 0, 'GURDRUG',true);
INSERT INTO enemies(endurance, glory, gold, type, is_boss) VALUES (9, 0, 0, 'ROGHKILLER',true);
INSERT INTO enemies(endurance, glory, gold, type, is_boss) VALUES (10, 0, 0, 'SHRIEKKNIFER',true);

INSERT INTO enemy_in_game(wounds, enemy_id, game_field_id) VALUES (0, 1, 1);
INSERT INTO enemy_in_game(wounds, enemy_id, game_field_id) VALUES (0, 3, 1);
INSERT INTO enemy_in_game(wounds, enemy_id, game_id) VALUES (0, 2, 1);
INSERT INTO enemy_in_game(wounds, enemy_id, game_id) VALUES (0, 4, 1);


--Cartas de Mercado
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(3, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'DAGA_ELFICA', 2);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(3, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'DAGA_ELFICA', 2);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(8, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'POCION_CURATIVA', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(8, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'POCION_CURATIVA', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(8, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'POCION_CURATIVA', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(4, 'MELEE', 'DISTANCIA', 'PERICIA', 'INCOMPATIBLE', 'PIEDRA_DE_AMOLAR', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage)
                    VALUES(5, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA' , 'VIAL_DE_CONJURACION', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage)
                    VALUES(3, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'ELIXIR_DE_CONCENTRACION', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage)
                    VALUES(3, 'MELEE', 'DISTANCIA', 'PERICIA', 'MAGIA', 'ELIXIR_DE_CONCENTRACION', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(3, 'INCOMPATIBLE', 'DISTANCIA', 'INCOMPATIBLE', 'MAGIA', 'CAPA_ELFICA', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(4, 'MELEE', 'INCOMPATIBLE', 'INCOMPATIBLE', 'INCOMPATIBLE', 'ARMADURA_DE_PLACAS', 0);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                    VALUES(5, 'MELEE', 'INCOMPATIBLE', 'INCOMPATIBLE', 'INCOMPATIBLE', 'ALABARDA_ORCA', 4);
INSERT INTO MARKET_CARD(price, profiency1, profiency2, profiency3, profiency4, type, damage) 
                     VALUES(5, 'INCOMPATIBLE', 'DISTANCIA', 'INCOMPATIBLE','INCOMPATIBLE', 'ARCO_COMPUESTO', 4);


INSERT INTO MARKET_CARD_IN_GAME (game_sale_id,market_card_id) VALUES (1,1);
INSERT INTO MARKET_CARD_IN_GAME (game_sale_id ,market_card_id) VALUES (1,2);
INSERT INTO MARKET_CARD_IN_GAME (game_sale_id,market_card_id) VALUES (1,3);
INSERT INTO MARKET_CARD_IN_GAME (game_sale_id,market_card_id) VALUES (1,4);
INSERT INTO MARKET_CARD_IN_GAME (game_sale_id,market_card_id) VALUES (1,5);
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (1,6);
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (1,7);
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (1,8);

--game para probar board

INSERT INTO games(id,start_time, has_scenes, max_players,min_players, state, username,user_id) VALUES --, winner_id 
(2,'2022-11-10', 0, 4, 2, 'EN_CURSO', 'admin',1); --,1

INSERT INTO users(username, password, email, birth_date, enabled) VALUES 
('user4', 'user', 'user4@user.com','1900-01-09', 1);--3 playerId
INSERT INTO authorities(id,user_id,username,authority) VALUES (5,5,'user4', 'user');
INSERT INTO users(username, password, email, birth_date, enabled) VALUES 
('user5', 'user', 'user5@user.com','1928-01-09', 1);--4 playerId
INSERT INTO authorities(id,user_id,username,authority) VALUES (6,6,'user5', 'user');
INSERT INTO users(username, password, email, birth_date, enabled) VALUES 
('user6', 'user', 'user6@user.com','1957-01-09', 1);--5 playerId
INSERT INTO authorities(id,user_id,username,authority) VALUES (7,7,'user6', 'user');
INSERT INTO users(username, password, email, birth_date, enabled) VALUES 
('samalbort', 'samuel', 'samalbort@user.com','2000-10-01', 1);--6 playerId
INSERT INTO authorities(id,user_id,username,authority) VALUES (8,8,'samalbort', 'user');
INSERT INTO users(username, password, email, birth_date, enabled) VALUES
('migybaman', 'miguel', 'migybaman@user.com','2000-10-01', 1);--7playerId
INSERT INTO authorities(id,user_id,username,authority) VALUES (9,9,'migybaman', 'user');

INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES 
(2, 7, 0, 0, TRUE, 'MAGIA','INCOMPATIBLE', 'MAGO_MASCULINO', 5, 2);
INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES 
(4, 9, 0, 0, TRUE, 'MELEE','INCOMPATIBLE', 'GUERRERO_FEMENINO',6, 2);
INSERT INTO players(glory, gold, wounds, enemy_kills, evasion, profiency, second_profiency, hero_type, user_id, game_id) VALUES 
(4, 9, 0, 0, TRUE, 'DISTANCIA', 'MELEE', 'EXPLORADOR_MASCULINO',7, 2);

--player user4 ->userId=5 playerId=3
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (31, 5);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (32, 5);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (33, 5);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_discard_id) VALUES (34, 5);
INSERT INTO MARKET_CARD_IN_GAME (game_id, player_id,market_card_id) VALUES (2,5,1);
--player user5 ->userID=6 playerId=4
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (15, 6);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (16, 6);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (17, 6);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (18, 6);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_discard_id) VALUES (19, 6);
--player user6 ->userID=7 playerId=5
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (1, 7);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (2, 7);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (3, 7);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_id) VALUES (4, 7);
INSERT INTO ABILITY_CARD_IN_GAME(ability_card_id, player_discard_id) VALUES (5, 7);
--ENEMIGOS DE GAME 2
INSERT INTO ENEMY_IN_GAME(wounds, enemy_id, game_field_id) VALUES (0, 1, 2);
INSERT INTO ENEMY_IN_GAME(wounds, enemy_id, game_field_id) VALUES (0, 2, 2);
INSERT INTO ENEMY_IN_GAME(wounds, enemy_id, game_field_id) VALUES (0 ,3, 2);
INSERT INTO ENEMY_IN_GAME(wounds, enemy_id, game_id) VALUES (0, 4, 2);
--INSERT INTO GAMES_MONSTER_FIELD(game_id, monster_field_id) VALUES(2,2);

--CARTAS DE MERCADO A LA VENTA
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (2,2);
--INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (2,3);
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (2,4);
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (2,5);
INSERT INTO MARKET_CARD_IN_GAME (game_id,market_card_id) VALUES (2,6);

INSERT INTO TURN(type, game_id, player_id) VALUES ('ATAQUE', 1, 1);
INSERT INTO TURN(type, game_id, player_id) VALUES ('ATAQUE', 2, 5);
-- INSERT INTO TURN(type, game_id, player_id) VALUES ('MERCADO', 1, 2);
-- INSERT INTO TURN(type, game_id, player_id) VALUES ('RESTABLECIMIENTO', 1, 3);
-- INSERT INTO TURN(type, game_id, player_id) VALUES ('ATAQUE', 1, 4);


 
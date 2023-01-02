package org.springframework.samples.notimeforheroes.card.ability;

public enum AbilityType{
    //Cartas del explorador
    COMPANERO_LOBO, // Daño 2, Previenes 2 de Daño
    DISPARO_CERTERO, // Daño 3, Pierdes 1 cartas, Finalizas tu ataque
    DISPARO_RAPIDO, // Daño 1, Roba 1 si es "Disparo rápido" úsala, sino ponla al fondo del mazo de Habilidad
    EN_LA_DIANA, // Daño 4, Gana 1 de Gloria, Pierdes 1 carta
    LLUVIA_DE_FLECHAS, // Daño 2, Esta carta daña a 2 enemigos y al héroe con menos heridas, en empate tú eliges
    RECOGER_FLECHAS, // Daño 0, Recupera un "Disparo Rápido", Baraja tu mazo de Habildades, Gana 1 moneda
    SUPERVIVENCIA, // Daño 0, Cambia 1 enemigo por el siguiente en el mazo de Horda
    //Cartas del guerrero
    ATAQUE_BRUTAL, // Daño 3, Pierdes 1 carta
    CARGA_CON_ESCUDO, // Daño 2, Previenes 2 de Daño
    DOBLE_ESPADAZO, // Daño 2, Pierdes 1 carta
    ESCUDO, // Previenes el daño de un enemigo, Finalizas tu ataque
    ESPADAZO, // Daño 1, si el pirmero "Espadazo" que jeugas Roba 1
    PASO_ATRAS, // Daño 0, Roba 2
    TODO_O_NADA, // Daño 1, Roba 1 carta y súmale su daño a esta carta, Recupera la carta que robaste
    VOZ_DE_ALIENTO, // Todos Recuperan 2 cartas, Roba 1 carta y gana 1 de Gloria
    //Cartas del mago
    AURA_PROTECTORA, //Daño 0, Cancela el daño del próximo ataque sufrido, Pierdes X cartas donde X es el número de enemigos en el campo
    BOLA_DE_FUEGO, //Daño 2, Daña a todos los enemigos, El resto de héroes sufren 1 de Daño
    DISPARO_GELIDO, // Daño 1, El enemigo afectado no causa daño este turno, Roba 1
    FLECHA_CORROSIVA, // Daño 1, Las siguientes cartas que dañen a este enemigo le hacen 1 más de daño, Pierdes 1 carta
    GOLPE_DE_BASTON, // Daño 1, Si no es el primer "Golpe de bastón" usado contra este enemigo en lugar de 1 esta carta hace 2 de daño
    ORBE_CURATIVO, // Daño 0, Todos Recuperan 2 cartas, Eliminas 1 herida de tu héroe, Elimina esta carta del juego
    PROYECTIL_IGNEO, // Daño 2, Gana 1 de Gloria
    RECONSTITUCION, // Daño 0, Roba 1 carta, Recupera 2 cartas
    TORRENTE_DE_LUZ, // Daño 2, Todos menos tú recuperan 2, Ganas 1 de Gloria
    //Cartas del pícaro
    AL_CORAZON, // Daño 4, Si derrotas un enemigo con esto gana 1 Moneda si el primer "Al Corazón" del turno, Pierdes 1 carta
    ATAQUE_FURTIVO, // Daño 2, Si derrotas un enemigo con esto gana 1 Moneda si el primer "Ataque Furtivo" del turno
    BALLESTA_PRECISA, // Daño 2, Si ya usaste "Ballesta precisa" contra ese enemigo hace 1 punto más de daño
    EN_LAS_SOMBRAS, // Daño 1, Previenes 2 de Daño
    ENGANAR, // Daño 0, Cuesta 2 monedas, El enemigo elegido no hace daño este turno
    ROBAR_BOLSILLOS, //Daño 0, Roba 1 moneda a cada héroe
    SAQUEO, //Daño 0, Gana 1 moneda por cada Enemigo en el campo, Ganas 1 de Gloria
    TRAMPA // Daño 0, Al resolver el ataque de la horda derrotas al enemigo de mayor Fortaleza pero su botín se anula
}
package org.springframework.samples.notimeforheroes.card.market;

public enum MarketCardType{
    DAGA_ELFICA, //Daño 2, Coste 3, Si el héroe tiene como Proficiency "Pericia" se recupera tras jugarla, PROFICIENCIAS:  Distancia, Pericia, Melee
    POCION_CURATIVA, //Daño 0, Coste 8, Retira una herida de tu héroe, Eliminala del juego
    PIEDRA_DE_AMOLAR, // Daño 0. Coste 4, Todas tus cartas hacen 1 más de daño este turno si hacían al menos 1 de Daño
    VIAL_DE_CONJURACION, // Daño 0, Coste 5, Busca una carta de tu pila de Desgaste y ponla en tu mano
    ELIXIR_DE_CONCENTRACION, // Daño 0, Coste 3, Roba 3 cartas
    CAPA_ELFICA, // Daño 0, Coste 3, El enemigo seleccionado no hace daño este turno, PROFICIENCIAS : Distancia, Magia
    ARMADURA_DE_PLACAS, //Daño 0, Coste 4, Recuperas 4 cartas, PROFICIENCIAS: Melee
    ALABARDA_ORCA, //Daño 4, Coste 5, PROFICIENCIAS: Melee
    ARCO_COMPUESTO //Daño 4, Coste 5, PROFICIENCIAS: Distancia
}
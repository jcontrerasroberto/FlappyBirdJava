/*
 * PROYECTO FINAL DE PROGRAMACIÓN ORIENTADA A OBJETOS
 * FLAPPY BIRD (MULTIPLAYER Y SINGLEPLAYER)
 * 
 * 2CM3
 * 
 * INTEGRANTES:
 * 
 * CONTRERAS BARRITA JOSÉ ROBERTO
 * CONTRERAS MENDEZ BRANDON
 * FONSECA RAMOS ANGEL GABRIEL
 * TOLEDO ESPINOSA CRISTINA ALINE
 * 
 * */

 /*
 * CLASE Flappy Bird
 * Esta clase contiene el método main para iniciar el programa
 * */
 
public class FlappyBird {
	//Constructor: Simplementa crea un objeto de la clase FlappyBirdFrame
	public FlappyBird(){
		FlappyBirdFrame fp = new FlappyBirdFrame();
	}

	//main: llama al constructor de la clase
	public static void main(String[] args) {
		new FlappyBird();
	}
}

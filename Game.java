/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room armadilha;
    private Room tesouro;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();


    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room tesouro, entrada, trono, exercito, armadilha, estátuas;

        // criar as salas
        tesouro = new Room("no local do tesouro");
        entrada = new Room("na entrada principal do castelo");
        trono = new Room("na sala do trono");
        exercito = new Room("no aposento do exército");
        armadilha = new Room("na sala da armadilha");
        estátuas = new Room("na sala das estátuas");

        // inicializar as saídas das salas
        tesouro.setExits(null, armadilha, null, trono);
        entrada.setExits(null, null, null, null);
        trono.setExits(null, tesouro, exercito,exercito );
        exercito.setExits(trono, armadilha, entrada, estátuas);
        armadilha.setExits(tesouro, estátuas, null, null);
        estátuas.setExits(armadilha, null,null, entrada);

        currentRoom = entrada;  // começar o jogo na entrada
    }
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocation(); // Utilize o método para imprimir informações de localização
    }






    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        String commandWord = command.getCommandWord();

        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("dance")) {
            System.out.println("vc estar dançando");
        }

        else {
            System.out.println("I don't know what you mean...");
        }

        return wantToQuit;
    }




    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */


    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocation() {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.getExit("north") != null) {
            System.out.print("north ");
        }
        if(currentRoom.getExit("east") != null) {
            System.out.print("east ");
        }
        if(currentRoom.getExit("south") != null) {
            System.out.print("south ");
        }
        if(currentRoom.getExit("west") != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom =currentRoom.getExit("west");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom == armadilha) {
                System.out.println("Vc caiu na armadilha , reiniciando o jogo...");
                createRooms(); // Reset the game by creating new rooms
            } else if (nextRoom == tesouro) {
                System.out.println("Parabéns! VOCE VENCEU!");
                System.exit(0); // Exit the game
            } else {
                currentRoom = nextRoom;
                printLocation(); // Utilize o método para imprimir informações de localização
            }
        }
    }
    private void look()
    {
        System.out.println(currentRoom.getDescription());
    }




}

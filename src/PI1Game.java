
// Importieren der VK_*-Tastenkonstanten
import static java.awt.event.KeyEvent.*;

/**
 * Dies ist die Hauptklasse eines Spiels. Sie enthält die Hauptmethode, die zum
 * Starten des Spiels aufgerufen werden muss.
 *
 * @author Thomas Röfer
 */
abstract class PI1Game extends Game
{
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    static void main()
    {
        // Das Spielfeld erzeugen
        final Field field = new Field(new String[] {
            "O-O-O-O",
            "    |",
            "O-O-O-O",
            "    |",
            "O-O-O-O-O",
            "    |",
            "O-O-O"
        });
        new GameObject(3, 0, 0, "goal");
        new GameObject(4, 0, 0, "bridge");
        new GameObject(4, 1, 3, "water-l");
        new GameObject(3, 3, 0, "water-l");
        new GameObject(4, 3, 0, "water-i");
        final Walker walker1 = new Walker(new GameObject(1, 0, 2, "claudius"), field);
        final Walker walker2 = new Walker(new GameObject(0, 1, 0, "laila"), field);
        final Walker walker3 = new Walker(new GameObject(3, 2, 2, "child"), field);
        final GameObject player = new GameObject(0, 3, 0, "woman");

        while (player.isVisible()) {
            final int key = getNextKey();
            if (key == VK_RIGHT && field.hasNeighbor(player.getX(), player.getY(), 0)) {
                player.setRotation(0);
                player.setLocation(player.getX() + 1, player.getY());
            }
            else if (key == VK_DOWN && field.hasNeighbor(player.getX(), player.getY(), 1)) {
                player.setRotation(1);
                player.setLocation(player.getX(), player.getY() + 1);
            }
            else if (key == VK_LEFT && field.hasNeighbor(player.getX(), player.getY(), 2)) {
                player.setRotation(2);
                player.setLocation(player.getX() - 1, player.getY());
            }
            else if (key == VK_UP && field.hasNeighbor(player.getX(), player.getY(), 3)) {
                player.setRotation(3);
                player.setLocation(player.getX(), player.getY() - 1);
            }
            else {
                playSound("error");
                continue;
            }

            playSound("step");
            sleep(200);
            walker1.act(player);
            walker2.act(player);
            walker3.act(player);
        }
    }
}

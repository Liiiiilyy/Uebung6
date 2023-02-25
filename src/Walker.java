/**
 * Diese Klasse definiert eine Spaziergänger:in, die dieselbe Strecke immer
 * auf und ab läuft. Dabei werden bei der Konstruktion die Startposition und
 * -richtung angegeben, sowie das Spielfeld, auf dem sie sich bewegt.
 *
 * @author Thomas Röfer
 */
class Walker
{
    private final GameObject avatar;
    private final Field field;

    Walker(final GameObject avatar, final Field field)
    {
        this.avatar = avatar;
        this.field = field;
    }

    void act(final GameObject player)
    {
        // Vorwärts bewegen
        if (avatar.getRotation() == 0) {
            avatar.setLocation(avatar.getX() + 1, avatar.getY());
        }
        else if (avatar.getRotation() == 1) {
            avatar.setLocation(avatar.getX(), avatar.getY() + 1);
        }
        else if (avatar.getRotation() == 2) {
            avatar.setLocation(avatar.getX() - 1, avatar.getY());
        }
        else {
            avatar.setLocation(avatar.getX(), avatar.getY() - 1);
        }

        // Sound dazu abspielen
        avatar.playSound("step");

        // Umdrehen, wenn nächster Schritt nicht mehr ausführbar
        if (!field.hasNeighbor(avatar.getX(), avatar.getY(), avatar.getRotation())) {
            avatar.setRotation(avatar.getRotation() + 2);
        }

        // Wenn gleiche Position wie Spielfigur, lasse diese verschwinden
        if (avatar.getX() == player.getX() && avatar.getY() == player.getY()) {
            player.setVisible(false);
            avatar.playSound("go-away");
        }
    }
}

package lab5.tools;


import lab5.types.Chapter;
import lab5.types.SpaceMarine;

/**
 * Beautifully output
 */
public class Shower {
    public void show(SpaceMarine marine) {
            ConsoleManager.println("Marine №" + marine.getId() +
                    ".\nName: '" + marine.getName() +
                    "'\nCoordinates: {\n\tx: " + marine.getCoordinates().getX() + "\n\ty: " + marine.getCoordinates().getY() + "\n}" +
                    "\nCreation Date: " + marine.getCreationDate().toLocalDate() + " " + marine.getCreationDate().toLocalTime() +
                    "\nHealth: " + marine.getHealth() +
                    "\nHeight: " + marine.getHeight() +
                    "\nCategory: " + marine.getCategory() +
                    "\nMelee Weapon: " + marine.getMeleeWeapon() +
                    "'\nChapter: {\n\tname: " + marine.getChapter().getName() + "\n\tParent Legion: " + marine.getChapter().getParentLegion() + "\n}\n");
    }
    public void show(Chapter chapter) {
        ConsoleManager.println("'\nChapter: {\n\tname: " + chapter.getName() +
                "\n\tParent Legion: " + chapter.getParentLegion() + "\n}\n");
    }
}

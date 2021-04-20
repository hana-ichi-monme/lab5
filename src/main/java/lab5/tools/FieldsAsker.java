package lab5.tools;

import lab5.exceptions.ExceedingTheLimitException;
import lab5.exceptions.NotNullException;
import lab5.types.AstartesCategory;
import lab5.types.Chapter;
import lab5.types.Coordinates;
import lab5.types.MeleeWeapon;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class FieldsAsker {
    private Scanner scanner;
    private String mode;
    public FieldsAsker(Scanner scanner) {
        this.scanner = scanner;
        mode = "interactive";
    }
    public String askName() {
        String name;
        while (true){
            try {
                ConsoleManager.println("Enter a name");

                name = scanner.nextLine().trim();
                if (name.equals("")) throw new NotNullException();
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError(" must be not null");
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return name;
    }
    public double askX() {
        String str;
        double x;
        while (true){
            try {
                ConsoleManager.println("Enter a X coordinate");

                str = scanner.nextLine().trim();
                x = Double.parseDouble(str);
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (NumberFormatException e) {
                ConsoleManager.printError(" must be a number");
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return x;
    }
    public long askY() {
        String str;
        long y;
        while (true){
            int MAX_Y = 441;
            try {
                ConsoleManager.println("Enter a Y coordinate");

                str = scanner.nextLine().trim();
                y = Long.parseLong(str);
                if (y > MAX_Y) {
                    throw new ExceedingTheLimitException();
                }
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (NumberFormatException e) {
                ConsoleManager.printError(" must be a number");
            } catch (ExceedingTheLimitException e) {
                ConsoleManager.printError(" must be less than " + MAX_Y);
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return y;
    }
    public Coordinates askCoordinates() {
        return new Coordinates(askX(), askY());
    }
    public Long askHealth() {
        String str;
        Long health;
        while (true){
            int MIN_HEALTH = 0;
            try {
                ConsoleManager.println("Enter a health");

                str = scanner.nextLine().trim();
                health = Long.parseLong(str);
                if (health < MIN_HEALTH) {
                    throw new ExceedingTheLimitException();
                }
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (NumberFormatException e) {
                ConsoleManager.printError(" must be a number");
            } catch (ExceedingTheLimitException e) {
                ConsoleManager.printError(" must be more than " + MIN_HEALTH);
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return health;
    }
    public float askHeight() {
        String str;
        float height;
        while (true) {
            int MIN_HEIGHT = 0;
            try {
                ConsoleManager.println("Enter a height");

                str = scanner.nextLine().trim();
                height = Float.parseFloat(str);
                if (height < MIN_HEIGHT) {
                    throw new ExceedingTheLimitException();
                }
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (NumberFormatException e) {
                ConsoleManager.printError(" must be a number");
            } catch (ExceedingTheLimitException e) {
                ConsoleManager.printError(" must be more than " + MIN_HEIGHT);
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return height;
    }
    public AstartesCategory askCategory() {
        String str;
        AstartesCategory category;
        while (true) {
            try {
                ConsoleManager.println("Enter a category");

                str = scanner.nextLine().trim();
                category = AstartesCategory.valueOf(str);
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError(" not in the list");
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return category;
    }
    public MeleeWeapon askMeleeWeapon() {
        String str;
        MeleeWeapon weapon;
        while (true) {
            try {
                ConsoleManager.println("Enter a melee weapon");

                str = scanner.nextLine().trim();
                if (str.isEmpty())
                    throw new NotNullException();
                weapon = MeleeWeapon.valueOf(str);
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("Melee weapon must be not null");
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError(" not in the list");
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return weapon;
    }
    public String askChapterName() {
        String str;
        while (true) {
            try {
                ConsoleManager.println("Enter a chapter's name");

                str = scanner.nextLine().trim();
                if (str.equals(""))
                    throw new NotNullException();
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (NotNullException e) {
                ConsoleManager.printError("Chapter's name must be not null");
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return str;
    }
    public String askChapterParentLegion() {
        String str;
        while (true) {
            try {
                ConsoleManager.println("Enter a chapter's parent legion");

                str = scanner.nextLine().trim();
                if (str.equals(""))
                    throw new NotNullException();
                break;
            } catch (NoSuchElementException e) {
                ConsoleManager.printError(" not recognized");
            } catch (Exception e) {
                ConsoleManager.printError("Unexpected exception");
            }
        }
        return str;
    }
    public Chapter askChapter() {
        return new Chapter(askChapterName(), askChapterParentLegion());
    }

    public Scanner getUserScanner() {
        return scanner;
    }

    public void setMode(String mode) {
        if (mode.equals("file") || mode.equals("interactive"))
            this.mode = mode;
    }

    public void setUserScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

package lab5.types;

public enum AstartesCategory {
    APOTHECARY,
    ASSAULT,
    TACTICAL,
    TERMINATOR;

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().toLowerCase().substring(1);
    }
}

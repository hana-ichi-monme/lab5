package lab5.types;

public enum MeleeWeapon {
    CHAIN_AXE,
    CHAIN_SWORD,
    MANREAPER;
    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().toLowerCase().substring(1);
    }
}

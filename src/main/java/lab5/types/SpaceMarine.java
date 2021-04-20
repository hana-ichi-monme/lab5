package lab5.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lab5.parsers.MarineDeserializer;
import lab5.parsers.MarineSerializer;

import java.time.LocalDateTime;

@JsonDeserialize(using = MarineDeserializer.class)
@JsonSerialize(using = MarineSerializer.class)
public class SpaceMarine implements Comparable<SpaceMarine>{
    final private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле может быть null, Значение поля должно быть больше 0
    private float height;
    private AstartesCategory category; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null

    private static int idCounter = 1;
    public SpaceMarine(){
        super();
        id = idCounter++;
    }
    public SpaceMarine(String name,
                       Coordinates coordinates,
                       LocalDateTime creationDate,
                       Long health,
                       float height,
                       AstartesCategory category,
                       MeleeWeapon meleeWeapon,
                       Chapter chapter){
        id = idCounter++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.height = height;
        this.category = category;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }
    /**
     * @return Identifier of Space Marine
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Name of Space Marine
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Coordinates of Space Marine
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return Creation date of Space Marine
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return Health of Space Marine
     */
    public Long getHealth() {
        return health;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return Height of Space Marine
     */
    public float getHeight() {
        return height;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    /**
     * @return Category of Space Marine
     */
    public AstartesCategory getCategory() {
        return category;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    /**
     * @return Weapon (melee) of Space Marine
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    /**
     * @return Chapter of Space Marine
     */
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    /**
     * Compares this Space Marine with another one
     * @param spaceMarine Another Space Marine to compare with
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(SpaceMarine spaceMarine){
        return health.compareTo(spaceMarine.getHealth());
    }

    @Override
    public String toString() {
        return "SpaceMarine@" + hashCode() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", " + coordinates +
                ", creationDate=" + creationDate.toLocalDate() + " " + creationDate.toLocalTime() +
                ", health=" + health +
                ", height=" + height +
                ", category=" + category +
                ", meleeWeapon=" + meleeWeapon +
                ", chapter=" + chapter +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode() +
                name.hashCode() +
                coordinates.hashCode() +
                creationDate.hashCode() +
                health.hashCode() +
                (int) height +
                category.hashCode() +
                meleeWeapon.hashCode() +
                chapter.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return name.equals(that.getName()) &&
                coordinates.equals(that.getCoordinates()) &&
                creationDate.equals(that.getCreationDate()) &&
                health.equals(that.getHealth()) &&
                height == that.getHeight() &&
                category.equals(that.getCategory()) &&
                meleeWeapon.equals(that.getMeleeWeapon()) &&
                chapter.equals(that.getChapter());

    }
}

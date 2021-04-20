package lab5.tools;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.MarineNotFoundException;
import lab5.types.*;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private FileManager fileManager;
    private Deque<SpaceMarine> marineCollection;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.lastInitTime = null;
        this.lastSaveTime = null;
        load();
    }

    public Deque<SpaceMarine> getMarineCollection() {
        return marineCollection;
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Load collection from file
     */
    public void load() {
        marineCollection = fileManager.read();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * Save collection to file
     */
    public void save() {
        fileManager.write(marineCollection);
        lastSaveTime = LocalDateTime.now();
    }

    public void addToCollection(SpaceMarine spaceMarine) {
        marineCollection.add(spaceMarine);
    }

    public int collectionSize() {
        return marineCollection.size();
    }

    public SpaceMarine getFirst() {
        if (collectionSize() == 0)
            return null;
        return marineCollection.getFirst();
    }

    public void clearCollection() {
        marineCollection.clear();
    }

    public int countByHealth(Long health) throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        int count = 0;
        Iterator<SpaceMarine> iterator = marineCollection.iterator();
        for (;;) {
            if (iterator.hasNext()) {
                SpaceMarine spaceMarine = iterator.next();
                if (spaceMarine.getHealth().equals(health))
                    count++;
            } else break;
        }
        return count;
    }

    public String collectionType() {
        return marineCollection.getClass().getName();
    }

    public SpaceMarine maxByHealth() throws CollectionIsEmptyException {
        if (marineCollection.isEmpty())
            throw new CollectionIsEmptyException();
        SpaceMarine maxMarine = getFirst();
        for (SpaceMarine marine: marineCollection)
            if (marine.getHealth().compareTo(maxMarine.getHealth()) > 0)
                maxMarine = marine;
        return maxMarine;
    }

    public Set<Chapter> uniqueChapters() throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        Set<Chapter> uniqueChapters = new HashSet<>();
        Set<Chapter> nonUniqueChapters = new HashSet<>();
        for (SpaceMarine marine : marineCollection) {
            Chapter chapter = marine.getChapter();
            if (uniqueChapters.contains(chapter)) {
                uniqueChapters.remove(chapter);
                nonUniqueChapters.add(chapter);
            } else if (!nonUniqueChapters.contains(chapter))
                uniqueChapters.add(chapter);
        }
        return uniqueChapters;
    }

    public SpaceMarine getById(Integer id) throws CollectionIsEmptyException, MarineNotFoundException {
        if (marineCollection.isEmpty())
            throw new CollectionIsEmptyException();
        SpaceMarine marineById = null;
        for (SpaceMarine marine: marineCollection) {
            if (marine.getId().equals(id)) {
                marineById = marine;
                break;
            }
        }
        if (marineById == null)
            throw new MarineNotFoundException();
        return marineById;
    }

    public void removeFromCollection(SpaceMarine marine) throws CollectionIsEmptyException, MarineNotFoundException {
        if (marineCollection.isEmpty())
            throw new CollectionIsEmptyException();
        if (!marineCollection.contains(marine))
            throw new MarineNotFoundException();
        marineCollection.remove(marine);
    }

    public void removeFirst() throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        marineCollection.removeFirst();
    }

    public void removeGreater(SpaceMarine spaceMarine) throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        marineCollection.removeIf(marine -> marine.compareTo(spaceMarine) > 0);
    }

    public void show() throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        for (SpaceMarine marine: marineCollection) {
            ConsoleManager.println(marine);
        }
    }

    public boolean collectionIsEmpty() {
        return marineCollection.isEmpty();
    }

    public void update(SpaceMarine marine,
                       String name,
                       Coordinates coordinates,
                       Long health,
                       float height,
                       AstartesCategory category,
                       MeleeWeapon meleeWeapon,
                       Chapter chapter) throws CollectionIsEmptyException, MarineNotFoundException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        if (!marineCollection.contains(marine))
            throw new MarineNotFoundException();
        removeFromCollection(marine);
        marine.setName(name);
        marine.setCoordinates(coordinates);
        marine.setHealth(health);
        marine.setHeight(height);
        marine.setCategory(category);
        marine.setMeleeWeapon(meleeWeapon);
        marine.setChapter(chapter);
        addToCollection(marine);
    }
}

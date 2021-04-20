package lab5.tools;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.MarineNotFoundException;
import lab5.types.*;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Manager working with the collection
 */
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

    /**
     * Collection getter
     * @return Space Marine collection
     */
    public Deque<SpaceMarine> getMarineCollection() {
        return marineCollection;
    }

    /**
     * Last initialization time getter
     * @return Last init time
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }
    /**
     * Last save time getter
     * @return Last save time
     */
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

    /**
     * Add Marine to collection
     * @param spaceMarine Marine
     */
    public void addToCollection(SpaceMarine spaceMarine) {
        marineCollection.add(spaceMarine);
    }

    /**
     * Collection size getter
     * @return collection.size()
     */
    public int collectionSize() {
        return marineCollection.size();
    }

    /**
     * First element of collection getter
     * @return 1st element
     */
    public SpaceMarine getFirst() {
        if (collectionSize() == 0)
            return null;
        return marineCollection.getFirst();
    }

    /**
     * Clear collection
     */
    public void clearCollection() {
        marineCollection.clear();
    }

    /**
     * @param health Marine's health
     * @return Count of Marines by health
     * @throws CollectionIsEmptyException Collection must be not empty
     */
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

    /**
     * @return Class name of collection
     */
    public String collectionType() {
        return marineCollection.getClass().getName();
    }

    /**
     * @return Marine with max health
     * @throws CollectionIsEmptyException Collection must be not empty
     */
    public SpaceMarine maxByHealth() throws CollectionIsEmptyException {
        if (marineCollection.isEmpty())
            throw new CollectionIsEmptyException();
        SpaceMarine maxMarine = getFirst();
        for (SpaceMarine marine: marineCollection)
            if (marine.getHealth().compareTo(maxMarine.getHealth()) > 0)
                maxMarine = marine;
        return maxMarine;
    }

    /**
     * @return All unique (non-recurring) Chapters
     * @throws CollectionIsEmptyException Collection must be not empty
     */
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

    /**
     * @param id Marine's id
     * @return Marine with some id
     * @throws CollectionIsEmptyException Collection must be not empty
     * @throws MarineNotFoundException There must be Marine with some id
     */
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

    /**
     * @param marine Marine, who is needed to remove
     * @throws CollectionIsEmptyException Collection must be not empty
     * @throws MarineNotFoundException There must be Marine
     */
    public void removeFromCollection(SpaceMarine marine) throws CollectionIsEmptyException, MarineNotFoundException {
        if (marineCollection.isEmpty())
            throw new CollectionIsEmptyException();
        if (!marineCollection.contains(marine))
            throw new MarineNotFoundException();
        marineCollection.remove(marine);
    }

    /**
     * Remove first element of collection
     * @throws CollectionIsEmptyException Collection must be not empty
     */
    public void removeFirst() throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        marineCollection.removeFirst();
    }

    /**
     * Remove all Marines, who is greater than this Marine
     * @param spaceMarine Marine
     * @throws CollectionIsEmptyException Collection must be not empty
     */
    public void removeGreater(SpaceMarine spaceMarine) throws CollectionIsEmptyException {
        if (collectionIsEmpty())
            throw new CollectionIsEmptyException();
        marineCollection.removeIf(marine -> marine.compareTo(spaceMarine) > 0);
    }

    /**
     * @return collections.isEmpty()
     */
    public boolean collectionIsEmpty() {
        return marineCollection.isEmpty();
    }

    /**
     * Update all Marine's fields
     * @throws CollectionIsEmptyException Collection must be not empty
     * @throws MarineNotFoundException There must be Marine, who is needed to update
     */
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

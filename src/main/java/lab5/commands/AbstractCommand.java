package lab5.commands;


public abstract class AbstractCommand implements Command {
    private final String name;
    private final String info;
    public AbstractCommand(String name, String info) {
        this.name = name;
        this.info = info;
    }
    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "(" + info + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCommand that = (AbstractCommand) o;
        return info.equals(that.getInfo());
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }
}

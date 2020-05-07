package note;

import io.ConsoleDisplay;
import io.Display;

public abstract class AbstractNote implements INote {

    private int id;
    private String title;

    public AbstractNote(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void displayTitle() {

        Display display = new ConsoleDisplay();
        display.displayTitle(getId(), getTitle());

    }
}

package note;

import io.ConsoleDisplay;
import io.Display;
import note.state.INoteState;
import note.state.IllegalStateChange;

import java.util.Date;

public class Note extends AbstractNote {

    INoteState state;
    String content;
    Date date;

    public Note(int id, String title, INoteState state, String content, Date date) {
        super(id, title);
        this.state = state;
        this.content = content;
        this.date = date;
    }

    public INoteState getState() {
        return state;
    }

    public void setState(INoteState state) {
        this.state = state;
    }

    public boolean cancel() throws IllegalStateChange {
        state.cancel(this);
        return false;
    }

    public boolean complete() throws IllegalStateChange {
        state.complete(this);
        return false;
    }

    public void displayContent(){
        Display display = new ConsoleDisplay();
        display.displayNote(getTitle(), getContent(), getDate().toString(), getState().toString());
    }

    private String getContent() {
        return content;
    }

    private Date getDate() {
        return date;
    }
}

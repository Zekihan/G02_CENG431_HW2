package note;

import io.ConsoleDisplay;
import io.Display;
import note.state.INoteState;

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

    public boolean cancel() {
        state.cancel(this);
        return false;
    }

    public boolean complete() {
        state.complete(this);
        return false;
    }

    public void displayContent(){
        Display display = new ConsoleDisplay();
        display.displayNote(getTitle(), getContent());
    }

    private String getContent() {
        return content;
    }

    private Date getDate() {
        return date;
    }
}

package note;

import io.ConsoleDisplay;
import io.Display;
import note.state.INoteState;
import note.state.IllegalStateChange;

import java.util.Date;

public class Note extends AbstractNote {

    private INoteState state;
    private String content;
    private Date date;

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

    public void cancel() throws IllegalStateChange {
        state.cancel(this);
    }

    public void complete() throws IllegalStateChange {
        state.complete(this);
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

package note.state;

import note.Note;

public interface INoteState {

    public boolean cancel(Note note);

    public boolean complete(Note note);

}

package note.state;

import note.Note;

public interface INoteState {
    boolean cancel(Note note);
    boolean complete(Note note);
    String getName();
}

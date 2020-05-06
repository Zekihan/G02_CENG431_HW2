package States;

import Notes.Note;

public interface INoteState {
    boolean cancel(Note note);
    boolean complete(Note note);
    String getName();
}

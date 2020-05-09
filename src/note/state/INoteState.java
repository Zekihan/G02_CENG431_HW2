package note.state;

import note.Note;

public interface INoteState {

    public boolean cancel(Note note) throws IllegalStateChange;

    public boolean complete(Note note) throws IllegalStateChange;

}

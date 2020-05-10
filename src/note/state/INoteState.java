package note.state;

import note.Note;

public interface INoteState {

    public void cancel(Note note) throws IllegalStateChange;

    public void complete(Note note) throws IllegalStateChange;

}

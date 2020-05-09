package note.state;

import note.Note;

public class CompletedState implements INoteState{
    @Override
    public boolean cancel(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("Completed notes cannot be canceled.");
    }

    @Override
    public boolean complete(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("This note is already completed.");
    }
}

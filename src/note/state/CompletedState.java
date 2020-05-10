package note.state;

import note.Note;

public class CompletedState implements INoteState{
    @Override
    public void cancel(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("Completed notes cannot be canceled.");
    }

    @Override
    public void complete(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("This note is already completed.");
    }

    @Override
    public String toString() {
        return "Completed State";
    }
}

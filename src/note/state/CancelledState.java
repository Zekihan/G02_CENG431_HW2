package note.state;

import note.Note;

public class CancelledState implements INoteState{
    @Override
    public boolean cancel(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("This note is already canceled.");
    }

    @Override
    public boolean complete(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("Completed notes cannot be canceled.");
    }

    @Override
    public String toString() {
        return "Cancelled State";
    }
}

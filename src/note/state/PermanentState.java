package note.state;

import note.Note;

public class PermanentState implements INoteState{
    @Override
    public boolean cancel(Note note) {
        note.setState(new CancelledState());
        return true;
    }

    @Override
    public boolean complete(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("Permanent notes cannot be completed.");
    }
}

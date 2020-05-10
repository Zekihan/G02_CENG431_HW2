package note.state;

import note.Note;

public class PermanentState implements INoteState{
    @Override
    public void cancel(Note note) {
        note.setState(new CancelledState());
    }

    @Override
    public void complete(Note note) throws IllegalStateChange{
        throw new IllegalStateChange("Permanent notes cannot be completed.");
    }

    @Override
    public String toString() {
        return "Permanent State";
    }
}

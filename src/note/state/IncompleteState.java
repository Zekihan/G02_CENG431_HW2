package note.state;

import note.Note;

public class IncompleteState implements INoteState{
    @Override
    public void cancel(Note note) {
        note.setState(new CancelledState());
    }

    @Override
    public void complete(Note note) {
        note.setState(new CompletedState());
    }

    @Override
    public String toString() {
        return "Incomplete State";
    }
}

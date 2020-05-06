package States;

import Notes.Note;

public class IncompleteState implements INoteState{
    @Override
    public boolean cancel(Note note) {
        note.setState(new CancelledState());
        return true;
    }

    @Override
    public boolean complete(Note note) {
        note.setState(new CompletedState());
        return true;
    }

    @Override
    public String getName() {
        return "IncompleteState";
    }
}

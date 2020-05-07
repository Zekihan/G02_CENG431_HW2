package note.state;

import note.Note;

public class CompletedState implements INoteState{
    @Override
    public boolean cancel(Note note) {
        System.out.println("This note is already canceled");
        return false;
    }

    @Override
    public boolean complete(Note note) {
        System.out.println("completed notes cannot canceled");
        return false;
    }

    @Override
    public String getName() {
        return "CompletedState";
    }
}

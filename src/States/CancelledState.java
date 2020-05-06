package States;

import Notes.Note;

public class CancelledState implements INoteState{
    @Override
    public boolean cancel(Note note) {
        System.out.println("This note is already canceled");
        return false;
    }

    @Override
    public boolean complete(Note note) {
        System.out.println("canceled notes cannot canceled");
        return false;
    }

    @Override
    public String getName() {
        return "CancelledState";
    }
}

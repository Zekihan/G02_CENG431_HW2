import Notes.Note;
import States.IncompleteState;
import States.PermanentState;

public class Main {

    public static void main(String[] args) {
        Note note = new Note();
        note.setState(new PermanentState());
        note.complete();
        System.out.println(note.getState().getName());
    }
}

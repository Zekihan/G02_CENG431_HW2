package note;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class NoteGroup extends AbstractNote{

    private List<INote> notes;

    public NoteGroup(int id, String title) {
        this(id, title, new LinkedList<>());
    }


    public NoteGroup(int id, String title, List<INote> notes) {
        super(id, title);
        this.notes = notes;
    }

    @Override
    public void displayContent() {
        for(INote note : notes){
            note.displayTitle();
        }
    }

    public boolean add(INote note){
        return notes.add(note);
    }

    public INote get(int id) throws NoSuchElementException{
        for(INote note : notes){
            if(note.getId() == id){
                return note;
            }
        }
        throw new NoSuchElementException("Note with given id not found");
    }

    public List<INote> getNotes() {
        return notes;
    }
}

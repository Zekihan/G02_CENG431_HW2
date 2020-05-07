package note;

import java.util.List;

public class NoteGroup extends AbstractNote{

    private List<INote> notes;

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

    public INote get(int id){
        for(INote note : notes){
            if(note.getId() == id){
                return note;
            }
        }
        return null;
    }

}

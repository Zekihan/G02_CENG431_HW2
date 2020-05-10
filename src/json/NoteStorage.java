package json;

import note.INote;

public interface NoteStorage {

    public INote loadNotes();

    public boolean saveNotes(INote note);

    public Boolean checkSave();

}

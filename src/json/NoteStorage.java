package json;

import note.INote;

import java.io.File;

public interface NoteStorage {

    public INote loadNotes();

    public boolean saveNotes(INote note);

    public Boolean checkSave();

}

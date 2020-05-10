package json;

import note.INote;

import java.io.File;

public class NoteSaver implements NoteStorage{

    private JsonConverter noteJson;
    String filePath = "notes.json";

    public NoteSaver() {
        this.noteJson = new NoteJson();
    }

    public NoteSaver(JsonConverter noteJson) {
        this.noteJson = noteJson;
    }

    @Override
    public INote loadNotes() {

        FileIO fio = new FileIO(filePath);
        String notes = fio.load();

        return noteJson.fromJson(notes);
    }

    @Override
    public boolean saveNotes(INote note) {

        String save = noteJson.toJson(note);

        FileIO fio = new FileIO(filePath);
        return fio.save(save);
    }

    @Override
    public Boolean checkSave(){

        File f = new File(filePath);

        return f.exists() && !f.isDirectory();
    }
}

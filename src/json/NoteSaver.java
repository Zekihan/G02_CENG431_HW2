package json;

import note.INote;

public class NoteSaver implements NoteStorage{

    private JsonConverter noteJson;
    private FileIO fio;
    private String filePath;

    public NoteSaver() {
        this.filePath = "notes.json";
        this.noteJson = new NoteJson();
        this.fio = new FileIO(filePath);
    }

    @Override
    public INote loadNotes() {
        String notes = fio.load();
        return noteJson.fromJson(notes);
    }

    @Override
    public boolean saveNotes(INote note) {
        String save = noteJson.toJson(note);
        return fio.save(save);
    }

    @Override
    public Boolean checkSave(){
        return fio.checkSave();
    }
}

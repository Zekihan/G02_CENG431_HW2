package json;

import note.INote;

public interface JsonSaver {

    public String toJson(INote note);

    public INote fromJson(String note);

}

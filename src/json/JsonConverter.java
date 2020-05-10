package json;

import note.INote;

public interface JsonConverter {

    public String toJson(INote note);

    public INote fromJson(String note);

}

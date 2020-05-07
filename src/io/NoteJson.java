package io;

import com.google.gson.*;
import note.INote;
import note.Note;
import note.state.INoteState;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class NoteJson {

    Gson gson;

    public NoteJson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(INoteState.class, (JsonSerializer<INoteState>) (src, typeOfSrc, context) -> {
            JsonObject jsonNoteState = new JsonObject();

            jsonNoteState.addProperty("class_name", src.getClass().getCanonicalName());

            return jsonNoteState;
        });
        builder.registerTypeAdapter(INoteState.class, new JsonDeserializer<INoteState>() {
            @Override
            public INoteState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                try {
                    Class<?> c = Class.forName(jsonObject.get("class_name").getAsString());
                    Constructor<?> cons = c.getConstructor();
                    INoteState object = (INoteState) cons.newInstance();
                    return object;
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    public String noteToJson(INote note){
        return gson.toJson(note);
    }

    public INote noteFromJson(String note){
        return gson.fromJson(note, Note.class);
    }

}

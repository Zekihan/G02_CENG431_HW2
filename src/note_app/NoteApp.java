package note_app;

import note.INote;
import note.Note;
import note.NoteGroup;
import note.state.IncompleteState;
import note.state.PermanentState;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NoteApp {


    public void init(){

        Date date = new Date();

        INote note1 = new Note(1,"title1", new PermanentState(), "content1", date);
        INote note2 = new Note(2,"title2", new IncompleteState(), "content2", date);
        INote note3 = new Note(3,"title3", new PermanentState(), "content3", date);
        INote note4 = new Note(4,"title4", new IncompleteState(), "content4", date);
        INote note5 = new Note(5,"title5", new IncompleteState(), "content5", date);

        List<INote> notelist = new LinkedList<>();

        NoteGroup group1 = new NoteGroup(6, "birthday notes", new LinkedList<>());

        group1.add(note3);
        group1.add(note4);


        notelist.add(note1);
        notelist.add(group1);
        notelist.add(note2);

        INote root = new NoteGroup(0, "Notes", notelist);

        ((NoteGroup) root).add(note5);
        ((NoteGroup) root).get(5).displayContent();

        root.displayContent();
        System.out.println("\n\n");



        group1.displayContent();

    }

}

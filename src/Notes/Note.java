package Notes;

import States.INoteState;

public class Note {
    INoteState state;

    public INoteState getState() {
        return state;
    }

    public void setState(INoteState state) {
        this.state = state;
    }

    public boolean cancel() {
        state.cancel(this);
        return false;
    }

    public boolean complete() {
        state.complete(this);
        return false;
    }
}

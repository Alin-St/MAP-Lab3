package model.statements;

import model.programState.ProgramState;

public class NullStatement implements IStatement {

    @Override
    public String toString() {
        return "nop";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public NullStatement deepCopy() {
        return new NullStatement();
    }
}

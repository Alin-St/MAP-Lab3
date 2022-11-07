package model.statements;

import model.ProgramState;

public class NullStatement implements IStatement {

    @Override
    public String toString() {
        return "nop";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }

    @Override
    public NullStatement deepCopy() {
        return new NullStatement();
    }
}

package model.statements;

import model.exceptions.InterpreterException;
import model.programState.ProgramState;
import model.types.IType;
import model.utility.MyIDictionary;

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

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        return typeEnv;
    }
}

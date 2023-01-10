package model.statements;

import model.exceptions.InterpreterException;
import model.programState.ProgramState;
import model.types.IType;
import model.utility.MyIDictionary;

public class CompoundStatement implements IStatement {

    private final IStatement _first;
    private final IStatement _second;

    public CompoundStatement(IStatement first, IStatement second) {
        _first = first;
        _second = second;
    }

    public String toString() {
        return _first.toString() + ";\n" + _second.toString();
    }

    public ProgramState execute(ProgramState state) {
        var stack = state.getExecutionStack();
        stack.push(_second);
        stack.push(_first);
        return null;
    }

    @Override
    public CompoundStatement deepCopy() {
        return new CompoundStatement(_first.deepCopy(), _second.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        return _second.typeCheck(_first.typeCheck(typeEnv));
    }
}

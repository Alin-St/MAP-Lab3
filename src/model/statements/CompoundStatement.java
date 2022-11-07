package model.statements;

import model.exceptions.InterpreterException;
import model.ProgramState;

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
        return state;
    }

    @Override
    public CompoundStatement deepCopy() {
        return new CompoundStatement(_first.deepCopy(), _second.deepCopy());
    }
}

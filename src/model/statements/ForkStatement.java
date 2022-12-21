package model.statements;

import model.exceptions.InterpreterException;
import model.programState.ExecutionStack;
import model.programState.ProgramState;

public class ForkStatement implements IStatement {

    private final IStatement _innerStatement;

    public ForkStatement(IStatement innerStatement) {
        _innerStatement = innerStatement;
    }

    @Override
    public String toString() {
        return "fork {\n" + _innerStatement.toString().indent(4)  + "}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        return new ProgramState(
                ProgramState.getNewId(),
                new ExecutionStack(_innerStatement),
                state.getSymbolTable().deepCopy(),
                state.getOutputStructure(),
                state.getFileTable(),
                state.getHeapTable()
        );
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(_innerStatement.deepCopy());
    }
}

package model.programState;

import model.exceptions.InterpreterException;
import model.statements.IStatement;
import model.utility.MyIStack;

public interface IExecutionStack extends MyIStack<IStatement> {
    @Override
    IExecutionStack deepCopy() throws InterpreterException;
}

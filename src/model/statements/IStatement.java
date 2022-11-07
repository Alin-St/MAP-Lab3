package model.statements;

import model.exceptions.InterpreterException;
import model.ProgramState;
import model.utility.IDeepCopyable;

public interface IStatement extends IDeepCopyable {
    ProgramState execute(ProgramState state) throws InterpreterException;
    String toString();
    IStatement deepCopy();
}

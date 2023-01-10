package model.statements;

import model.exceptions.InterpreterException;
import model.programState.ProgramState;
import model.types.IType;
import model.utility.IDeepCopyable;
import model.utility.MyIDictionary;

public interface IStatement extends IDeepCopyable {
    ProgramState execute(ProgramState state) throws InterpreterException;
    String toString();
    IStatement deepCopy();
    MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException;
}

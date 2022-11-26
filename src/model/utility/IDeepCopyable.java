package model.utility;

import model.exceptions.InterpreterException;

public interface IDeepCopyable {
    IDeepCopyable deepCopy() throws InterpreterException;
}

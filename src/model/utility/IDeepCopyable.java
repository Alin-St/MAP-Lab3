package model.utility;

import model.exceptions.InterpreterException;

public interface IDeepCopyable {
    Object deepCopy() throws InterpreterException;
}

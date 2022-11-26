package repository;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;

public interface IRepository {
    ProgramState getCurrentProgram();
    void setCurrentProgram(ProgramState value);
    void logProgramState(String prompt) throws InterpreterException;
}

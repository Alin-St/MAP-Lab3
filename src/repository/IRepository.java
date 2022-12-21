package repository;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;

import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStates);
    void logProgramState(ProgramState program, String prompt) throws InterpreterException;
}

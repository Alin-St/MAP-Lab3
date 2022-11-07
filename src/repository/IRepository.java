package repository;

import model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram();
    void setCurrentProgram(ProgramState value);
}

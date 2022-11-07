package repository;

import model.ProgramState;

public class Repository implements IRepository {

    private ProgramState _program;

    @Override
    public ProgramState getCurrentProgram() { return _program; }

    @Override
    public void setCurrentProgram(ProgramState value) { _program = value; }
}

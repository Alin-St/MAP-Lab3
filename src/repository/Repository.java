package repository;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    private ProgramState _program;
    private final String _logFilePath;

    public Repository(String logFilePath) {
        _logFilePath = logFilePath;
    }

    @Override
    public ProgramState getCurrentProgram() { return _program; }

    @Override
    public void setCurrentProgram(ProgramState value) { _program = value; }

    @Override
    public void logProgramState(String prompt) throws InterpreterException {
        try {
            var fw = new FileWriter(_logFilePath, true);
            var bw = new BufferedWriter(fw);
            var pw = new PrintWriter(bw);

            pw.println(prompt == null ? "Program state:" : prompt);
            pw.println(_program.toString().indent(4));

            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new InterpreterException("IOException: " + e.getMessage());
        }
    }
}

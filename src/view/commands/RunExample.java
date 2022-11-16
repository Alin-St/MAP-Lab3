package view.commands;

import controller.Controller;
import model.exceptions.InterpreterException;

public class RunExample extends Command {

    private final Controller _controller;

    public RunExample(String key, String desc, Controller controller) {
        super(key, desc);
        this._controller = controller;
    }

    @Override
    public void execute() {
        try {
            _controller.allSteps();
        } catch (InterpreterException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
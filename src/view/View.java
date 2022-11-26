package view;

import controller.Controller;
import model.programState.ProgramState;
import model.exceptions.InterpreterException;
import model.expressions.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;

import java.util.Scanner;

public class View {

    private final Controller _controller;

    public View(Controller controller) {
        _controller = controller;
    }

    public void run() {
        while (true)
        {
            String npl = _controller.getRepository().getCurrentProgram() == null ? " (no program loaded)" : "";

            System.out.println(
                    "1. Input a program.\n" +
                    "2. Run program" + npl + ".\n" +
                    "0. Exit.\n");

            int cmd = readInt("Enter a command: ");

            try {
                if (cmd == 1)
                    inputProgram();
                else if (cmd == 2)
                    runProgram();
                else if (cmd == 0)
                    break;
                else
                    System.out.println("Unknown command.");
            } catch (Exception e) {
                System.out.println("Error: " + e + "\n");
            }
        }

        System.out.println("Program closed.");
    }

    public void inputProgram() {
        System.out.println("1. Program 1:\n" + TIProgram1().toString().indent(4) + "\n" +
                "2. Program 2:\n" + TIProgram2().toString().indent(4) +  "\n" +
                "3. Program 3:\n" + TIProgram3().toString().indent(4) + "\n" +
                "4. Program 4:\n" + TIProgram4().toString().indent(4) + "\n" +
                "0. Exit");
        int cmd = readInt("Choose a program: ");

        if (cmd == 1)
            _controller.getRepository().setCurrentProgram(TIProgram1());
        else if (cmd == 2)
            _controller.getRepository().setCurrentProgram(TIProgram2());
        else if (cmd == 3)
            _controller.getRepository().setCurrentProgram(TIProgram3());
        else if (cmd == 4)
            _controller.getRepository().setCurrentProgram(TIProgram4());
        else {
            if (cmd != 0)
                System.out.println("Unknown command.\n");
            return;
        }

        System.out.println("Program loaded successfully.\n");
    }

    public void runProgram() throws InterpreterException {

        if (_controller.getRepository().getCurrentProgram() == null) {
            System.out.println("No program loaded.");
            return;
        }

        _controller.setDisplayFlag(true);
        _controller.allSteps();
    }

    int readInt(String prompt) {
        System.out.print(prompt);
        String s = new Scanner(System.in).nextLine();
        return Integer.parseInt(s);
    }

    ProgramState TIProgram1() {
        IStatement mainStatement = new CompoundStatement(
                new VariableDeclarationStatement("v", IntType.get()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );
        return new ProgramState(mainStatement);
    }

    ProgramState TIProgram2() {
        IStatement mainStatement = new CompoundStatement(
                new VariableDeclarationStatement("a", IntType.get()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", IntType.get()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(
                                        new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)),
                                                ArithmeticOperator.MULTIPLICATION
                                        ),
                                        ArithmeticOperator.ADDITION
                                )),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression(
                                                new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)),
                                                ArithmeticOperator.ADDITION
                                        )),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
        return new ProgramState(mainStatement);
    }

    ProgramState TIProgram3() {
        IStatement mainStatement = new CompoundStatement(
                new VariableDeclarationStatement("a", BoolType.get()),
                new CompoundStatement(
                        new VariableDeclarationStatement("v", IntType.get()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new ConditionalStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );
        return new ProgramState(mainStatement);
    }

    @SuppressWarnings("SpellCheckingInspection")
    ProgramState TIProgram4() {
        IStatement mainStatement = new CompoundStatement(
                new VariableDeclarationStatement("varf", StringType.get()),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenFileReaderStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("varc", IntType.get()),
                                        new CompoundStatement(
                                                new CompoundStatement(
                                                        new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                        new PrintStatement(new VariableExpression("varc"))
                                                ),
                                                new CompoundStatement(
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new PrintStatement(new VariableExpression("varc"))
                                                        ),
                                                        new CloseFileReaderStatement(new VariableExpression("varf"))
                                                )
                                        )
                                )
                        )
                )
        );
        return new ProgramState(mainStatement);
    }
}

package com.acme.edu.demotests;

import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class StringCommandUnitTest {
    StringCommand firstCommand;
    StringCommand secondCommand;

    @Before
    public void setUp() {
        firstCommand = new StringCommand("first message");
        secondCommand = new StringCommand("second message");
    }

    @Test
    public void shouldUnderstandWhenStringCommandIsSameType() {
        assertThat(firstCommand.isSameType(secondCommand));
    }

    @Test
    public void shouldAccumulateDifferentStringCommand() {
        Command tmp = firstCommand.reduce(secondCommand);

        assertThat(tmp.toString().contains(firstCommand.message + secondCommand.message));
    }

    @Test
    public void shouldAccumulateSameStringCommand() {
        StringCommand duplicate = new StringCommand("first message");
        /*
        LoggerController loggerController = new LoggerController(new Saver() {
            @Override
            public void save(Command cmd) {
                System.out.println(cmd);
            }
        });

        loggerController.log(firstCommand);
        loggerController.log(firstCommand);
        loggerController.flushBuffers();*/

        duplicate = (StringCommand) duplicate.reduce(firstCommand);
        duplicate = (StringCommand) duplicate.reduce(secondCommand);
        System.out.println(duplicate);

        assertThat((duplicate.toString()).contains("first message (x2") + System.lineSeparator() + secondCommand.toString());
    }
}

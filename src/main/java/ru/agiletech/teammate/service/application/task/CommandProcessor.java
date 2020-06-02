package ru.agiletech.teammate.service.application.task;

public interface CommandProcessor<T extends Command> {

    void process(T command);

}

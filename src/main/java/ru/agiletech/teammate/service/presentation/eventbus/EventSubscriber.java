package ru.agiletech.teammate.service.presentation.eventbus;

public interface EventSubscriber<T> {

    void onEvent(T serializedEvent);

}

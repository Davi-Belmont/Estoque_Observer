package event;

import java.util.ArrayList;
import java.util.List;

public class EventBus implements EventPublisher {

    private final List<Observer> observadores = new ArrayList<>();

    public void inscrever(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void publicar(Object evento) {
        for (Observer observador : observadores) {
            observador.atualizar(evento);
        }
    }
}
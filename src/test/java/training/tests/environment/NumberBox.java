package training.tests.environment;

public class NumberBox<T extends Number> {

    private T value;

    public NumberBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public double getDoubledValue () {
        return value.doubleValue() * 2;
    }
}
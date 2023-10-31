package v3.functionalinterface;

@FunctionalInterface
public interface Comparator <Key>{
    int compareKey(Key key1, Key key2);
}

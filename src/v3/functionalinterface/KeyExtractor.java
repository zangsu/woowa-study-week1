package v3.functionalinterface;

@FunctionalInterface
public interface KeyExtractor<Model, Key>{
    Key getKey(Model model);
}
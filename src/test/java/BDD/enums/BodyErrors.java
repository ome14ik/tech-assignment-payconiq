package BDD.enums;

public enum BodyErrors {

    NotFound("Not Found"),
    InternalServerError("Internal Server Error"),
    TeapotServerError("I'm a Teapot");

    private final String value;

    private BodyErrors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package api_engine.enums;

import java.util.Map;

public enum PathParameter {
    id ("id");

    private final String value;

    private PathParameter(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

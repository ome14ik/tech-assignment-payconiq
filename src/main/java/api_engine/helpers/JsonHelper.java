package api_engine.helpers;

import api_engine.enums.PathParameter;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonHelper {

    /**
     * parsing query parameters to path parameter
     */
      public static JSONObject parsParamToParamForPath(JSONObject queryParams){
        JSONObject pathParams = new JSONObject();

          for ( PathParameter listPath : PathParameter.values()) {
              String value = listPath.getValue();
               Map<String, String> map = getJsonObjectData(queryParams);
                  for (Map.Entry<String, String> mapSet : map.entrySet()) {
                      if ((mapSet.getKey().equals(value))) {
                          pathParams.put(value, mapSet.getValue());
                      }
                  }

          }
        return pathParams;
    }

    /**
     * parsing query parameters to path parameter
     */
    public static Map <String, Object> parsParamToParamForPath1(JSONObject queryParams){
        Map<String, Object> pathParams = new HashMap<>();

        for ( PathParameter listPath : PathParameter.values()) {
            String value = listPath.getValue();
            Map<String, Object> map = getJsonObjectData(queryParams);
            for (Map.Entry<String, Object> mapSet : map.entrySet()) {
                if ((mapSet.getKey().equals(value))) {
                    pathParams.put(value, mapSet.getValue());
                }
            }

        }
        return pathParams;
    }

    /**
     * Removing similar parameters from second parameter related to first parameter
     */
     public static JSONObject removeParameters (Map <String, Object> params1, JSONObject params2){
          Map <String, String> map= getJsonObjectData(params1);

          for (String key : map.keySet()) {
              params2.remove(key);
        }
          return params2;
    }

    /**
     Method for getting parameters from JsonObject to simple Map
     */
    private static Map<String, String> getJsonObjectData(Map <String, Object> params) {

        Map<String, String> map = new HashMap<>();

        for (Object key : params.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            String keyvalue = params.get(keyStr).toString();
            map.put(keyStr, keyvalue);
        }

        return map;
    }

}

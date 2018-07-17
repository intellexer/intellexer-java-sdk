package by.effectivesoft.intellexer.model.param;

import java.util.Map;

/**
 * Base class of request parameters
 */
public abstract class BaseParams {
    /**
     * Convert class fields to parameters to add them to the request
     *
     * @return Map of parameters
     */
    public abstract Map<String, String> toParamMap();
}

package by.effectivesoft.intellexer.model.param;


import java.util.HashMap;
import java.util.Map;

/**
 * Request parameters required to check spelling
 */
public class SpellCheckerParams extends BaseParams {
    private final Boolean separateLines;
    private final String language;
    private final Integer errorTune;
    private final Integer errorBound;
    private final Integer minProbabilityTune;
    private final Integer minProbabilityWeight;

    private SpellCheckerParams(Builder builder) {
        this.separateLines = builder.separateLines;
        this.language = builder.language;
        this.errorTune = builder.errorTune;
        this.errorBound = builder.errorBound;
        this.minProbabilityTune = builder.minProbabilityTune;
        this.minProbabilityWeight = builder.minProbabilityWeight;
    }

    /**
     * Get process each line independently flag
     *
     * @return Separate lines flag
     */
    public Boolean getSeparateLines() {
        return separateLines;
    }

    /**
     * Get input language
     *
     * @return Input language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Get error tune
     *
     * @return Error tune
     */
    public Integer getErrorTune() {
        return errorTune;
    }

    /**
     * Get maximum number of corrections for a single word regardless of its length
     *
     * @return Error bound
     */
    public Integer getErrorBound() {
        return errorBound;
    }

    /**
     * Get minimum probability tune
     *
     * @return Minimum probability tune
     */
    public Integer getMinProbabilityTune() {
        return minProbabilityTune;
    }

    /**
     * Get minimum probability for the words to be included to the list of candidates
     *
     * @return Minimum probability weight
     */
    public Integer getMinProbabilityWeight() {
        return minProbabilityWeight;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        if (separateLines != null) {
            params.put("separateLines", String.valueOf(separateLines));
        }
        if (language != null) {
            params.put("language", language);
        }
        if (errorTune != null) {
            params.put("errorTune", errorTune.toString());
        }
        if (errorBound != null) {
            params.put("errorBound", errorBound.toString());
        }
        if (minProbabilityTune != null) {
            params.put("minProbabilityTune", minProbabilityTune.toString());
        }
        if (minProbabilityWeight != null) {
            params.put("minProbabilityWeight", minProbabilityWeight.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link SpellCheckerParams} instance
     */
    public static class Builder {
        private Boolean separateLines;
        private String language;
        private Integer errorTune;
        private Integer errorBound;
        private Integer minProbabilityTune;
        private Integer minProbabilityWeight;

        /**
         * Set process each line independently flag
         *
         * @param separateLines if TRUE all lines will be processed independently
         * @return Builder
         */
        public Builder setSeparateLines(Boolean separateLines) {
            this.separateLines = separateLines;
            return this;
        }

        /**
         * Set input language
         *
         * @param language Input language
         * @return Builder
         */
        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        /**
         * Set adjust 'errorBound' to the length of words according to the expert bound values.
         * There are 3 possible modes:
         * <ul>
         * <li>Reduce(1) – choose the smaller value between the expert value and the bound set by the user; </li>
         * <li>Equal(2) – choose the bound set by the user;</li>
         * <li>Raise(3) – choose the bigger value between the expert value and the bound set by the user.</li>
         * </ul>
         *
         * @param errorTune Error tune
         * @return Builder
         */
        public Builder setErrorTune(Integer errorTune) {
            this.errorTune = errorTune;
            return this;
        }

        /**
         * Set maximum number of corrections for a single word regardless of its length
         *
         * @param errorBound Error bound
         * @return Builder
         */
        public Builder setErrorBound(Integer errorBound) {
            this.errorBound = errorBound;
            return this;
        }

        /**
         * Set adjust 'minProbabilityWeight' to the length of words according to the expert probability values.
         * There are 3 possible modes:
         * <ul>
         * <li>Reduce(1) – choose the smaller value between the expert value and the bound set by the user; </li>
         * <li>Equal(2) – choose the bound set by the user;</li>
         * <li>Raise(3) – choose the bigger value between the expert value and the bound set by the user.</li>
         * </ul>
         *
         * @param minProbabilityTune Minimum probability tune
         * @return Builder
         */
        public Builder setMinProbabilityTune(Integer minProbabilityTune) {
            this.minProbabilityTune = minProbabilityTune;
            return this;
        }

        /**
         * Set minimum probability for the words to be included to the list of candidates
         *
         * @param minProbabilityWeight Minimum probability weight
         * @return Builder
         */
        public Builder setMinProbabilityWeight(Integer minProbabilityWeight) {
            this.minProbabilityWeight = minProbabilityWeight;
            return this;
        }

        public SpellCheckerParams build() {
            return new SpellCheckerParams(this);
        }
    }

}

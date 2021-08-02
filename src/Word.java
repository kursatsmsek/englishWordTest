public class Word {
    private String turkishMean;
    private String englishMeanFirst;
    private String englishMeanSecond;
    private String englishMeanThird;

    public Word(String turkishMean, String englishMeanFirst) {
        this.turkishMean = turkishMean;
        this.englishMeanFirst = englishMeanFirst;
    }

    public Word(String turkishMean, String englishMeanFirst, String englishMeanSecond) {
        this.turkishMean = turkishMean;
        this.englishMeanFirst = englishMeanFirst;
        this.englishMeanSecond = englishMeanSecond;
    }

    public Word(String turkishMean, String englishMeanFirst, String englishMeanSecond, String englishMeanThird) {
        this.turkishMean = turkishMean;
        this.englishMeanFirst = englishMeanFirst;
        this.englishMeanSecond = englishMeanSecond;
        this.englishMeanThird = englishMeanThird;
    }

    public String getTurkishMean() {
        return turkishMean;
    }

    public void setTurkishMean(String turkishMean) {
        this.turkishMean = turkishMean;
    }

    public String getEnglishMeanFirst() {
        return englishMeanFirst;
    }

    public void setEnglishMeanFirst(String englishMeanFirst) {
        this.englishMeanFirst = englishMeanFirst;
    }

    public String getEnglishMeanSecond() {
        return englishMeanSecond;
    }

    public void setEnglishMeanSecond(String englishMeanSecond) {
        this.englishMeanSecond = englishMeanSecond;
    }

    public String getEnglishMeanThird() {
        return englishMeanThird;
    }

    public void setEnglishMeanThird(String englishMeanThird) {
        this.englishMeanThird = englishMeanThird;
    }
}
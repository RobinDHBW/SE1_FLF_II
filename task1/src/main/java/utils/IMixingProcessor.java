package utils;

import java.util.List;

public interface IMixingProcessor {
    void setMixingRate(Integer mixingRate);
    Integer getMixingRate();
    List<Object> mixTwoInputs(List<Object> input1, List<Object> input2);
    List<Integer> calcRatio(Integer quantity);
}

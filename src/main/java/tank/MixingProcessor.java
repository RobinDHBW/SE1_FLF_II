package tank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MixingProcessor implements IMixingProcessor{
    private Integer mixingRate = 0;

    public MixingProcessor(){

    }

    private Boolean checkInputAmount(Integer amount1, Integer amount2){
        if(mixingRate>0) {
            Integer check = (amount1 + amount2) / 100 * mixingRate;
            if (check.equals(amount2)) return true;
        }else {
            if(amount2.equals(0)) return true;
        }
        return false;
    }

    @Override
    public List<Object> mixTwoInputs(List<Object> primary, List<Object> secondary) {
        try {
            if(!checkInputAmount(primary.size(), secondary.size())) throw new Exception("Wrong input amount!");

            return Stream.concat(primary.stream(), secondary.stream()).toList();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void setMixingRate(Integer mixingRate) {
        this.mixingRate = mixingRate;
    }

    @Override
    public Integer getMixingRate() {
        return mixingRate;
    }

    @Override
    public List<Integer> calcRatio(Integer quantity) {
        List<Integer> ratios = new ArrayList<>();
        if(this.mixingRate>0) {
            Integer ratio = quantity / 100 * mixingRate;
            ratios.add(quantity - ratio);
            ratios.add(ratio);
        }else{
            ratios.add(quantity);
            ratios.add(0);
        }
        return ratios;
    }
}

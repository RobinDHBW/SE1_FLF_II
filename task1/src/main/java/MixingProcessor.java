

import utils.IMixingProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MixingProcessor {
    private Integer mixingRate = 0;
    private static final MixingProcessor instance = new MixingProcessor();
    public Port port;

    private MixingProcessor(){
        this.port = new Port();
    }

    public static MixingProcessor getInstance() {
        return instance;
    }

    private Boolean checkInputAmount(Integer amount1, Integer amount2){
        if(mixingRate>0) {
            Integer check = (amount1 + amount2) / 100 * mixingRate;
            return check.equals(amount2);
        }else {
            return amount2.equals(0);
        }
    }

    public List<Object> innerMixTwoInputs(List<Object> primary, List<Object> secondary) {
        try {
            if(!checkInputAmount(primary.size(), secondary.size())) throw new Exception("Wrong input amount!");

            return Stream.concat(primary.stream(), secondary.stream()).toList();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void innerSetMixingRate(Integer mixingRate) {
        this.mixingRate = mixingRate;
    }

    public Integer innerGetMixingRate() {
        return mixingRate;
    }

    public List<Integer> innerCalcRatio(Integer quantity) {
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

    public class Port  implements IMixingProcessor{
        @Override
        public List<Integer> calcRatio(Integer quantity) {
            return innerCalcRatio(quantity);
        }

        @Override
        public Integer getMixingRate() {
            return innerGetMixingRate();
        }

        @Override
        public void setMixingRate(Integer mixingRate) {
            innerSetMixingRate(mixingRate);
        }

        @Override
        public List<Object> mixTwoInputs(List<Object> input1, List<Object> input2) {
            return innerMixTwoInputs(input1, input2);
        }
    }

}

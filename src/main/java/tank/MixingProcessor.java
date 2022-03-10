package tank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MixingProcessor implements IMixingProcessor{
    Integer mixingRate = 0;

    public MixingProcessor(){

    }

    private Boolean checkInputAmount(Integer amount1, Integer amount2){
        if(mixingRate>0) {
            Integer check = (amount1 + amount1) / mixingRate;
            if (check == amount2) return true;
        }else {
            if(amount2 == 0) return true;
        }
        return false;
    }

    @Override
    public List<Object> mixTwoInputs(List<Object> input1, List<Object> input2) {
        try {
            if(!checkInputAmount(input1.size(), input2.size())) throw new Exception("Wrong input amount!");
            //TODO check (input1+input2)/mixingRate=input2.size --> Anteil stimmt

            return Stream.concat(input1.stream(), input2.stream()).toList();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean setMixingRate(Integer mixingRate) {
        return null;
    }

    @Override
    public List<Integer> calcRatio(Integer quantity) {
        List<Integer> ratios = new ArrayList<>();
        if(this.mixingRate>0) {
            Integer ratio = quantity / mixingRate;
            ratios.add(quantity - ratio);
            ratios.add(ratio);
        }else{
            ratios.add(quantity);
            ratios.add(0);
        }
        return ratios;
    }
}

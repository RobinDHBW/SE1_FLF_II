package task1;

import java.lang.reflect.Method;
import java.util.List;

public class MixingProcessorReflector {
    private Object port;

    public MixingProcessorReflector(){
        this.port = CompFactory.build();
    }

    public void setMixingRate(Integer mixingRate){
        try {
            Method setPathMethod = port.getClass().getDeclaredMethod("setMixingRate", Integer.class);
            setPathMethod.invoke(port, mixingRate);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    };
    public Integer getMixingRate(){
        try {
            Method setPathMethod = port.getClass().getDeclaredMethod("getMixingRate");
            return (Integer) setPathMethod.invoke(port);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    };

    @SuppressWarnings("unchecked")
    public List<Object> mixTwoInputs(List<Object> input1, List<Object> input2){
        try {
            Method setPathMethod = port.getClass().getDeclaredMethod("mixTwoInputs", List.class, List.class);
            return (List<Object>) setPathMethod.invoke(port, input1, input2);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    };

    @SuppressWarnings("unchecked")
    public List<Integer> calcRatio(Integer quantity){
        try {
            Method setPathMethod = port.getClass().getDeclaredMethod("calcRatio", Integer.class);
            return (List<Integer>) setPathMethod.invoke(port, quantity);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    };


}


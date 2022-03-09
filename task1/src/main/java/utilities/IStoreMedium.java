package utilities;

import java.util.List;

public interface IStoreMedium {
    void fill(Object input, Integer quantity);

    List<Object> remove(Integer quantity);

    Double getRelativeFillState();

    Integer getAbsoluteFillState();

}

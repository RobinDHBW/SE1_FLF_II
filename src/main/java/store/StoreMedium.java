package store;

import java.util.*;

public abstract class StoreMedium implements IStoreMedium {
    protected Object[][][] store;
    protected HashMap<Character, Integer> fillState = new HashMap<>();
    protected Boolean isFull = false;
    protected Boolean isEmpty = true;
    protected Object subject;
    protected Integer capacity;

    public StoreMedium(Integer length, Integer height, Integer width, Object subject) {

        this.store = new Object[length][height][width];
        this.subject = subject;
        this.capacity = length * height * width;

        fillState.put('x', length - 1);
        fillState.put('y', height - 1);
        fillState.put('z', width - 1);
    }

    protected Integer countSlots() {
        return (Arrays.stream(this.store)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream).toList()
                .stream()
                .filter(Objects::nonNull).toList()).size();
    }

    protected void fillLoop(Object input, Integer quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        for (int i = x; i >= 0; i--) {
            for (int j = y; j >= 0; j--) {
                for (int k = z; k >= 0; k--) {
                    if (j == 0 && i == 0 && k == 0) isFull = true;
                    if (quantity-- == 0) return;
                    this.store[i][j][k] = input;
                    isEmpty = false;
                    fillState.put('x', i);
                    fillState.put('y', j);
                    fillState.put('z', k);
                }
            }
        }
    }

    protected List<Object> removeLoop(Integer quantity) {

        int x = this.fillState.get('x');
        int y = this.fillState.get('y');
        int z = this.fillState.get('z');

        List<Object> output = new ArrayList<>();

        for (int i = x; i < this.store.length; i++) {
            for (int j = y; j < this.store[0].length; j++) {
                y = 0;
                for (int k = z; k < this.store[0][0].length; k++) {
                    z = 0;
                    this.fillState.put('x', i);
                    this.fillState.put('y', j);
                    this.fillState.put('z', k);
                    if (quantity == output.size()) {
                        return output;
                    }

                    output.add(this.store[i][j][k]);
                    this.store[i][j][k] = null;
                    isFull = false;
                    if (i == this.store.length - 1 && j == this.store[0].length - 1 && k == this.store[0][0].length - 1) {
                        isEmpty = true;
                        if (quantity > 1) throw new RuntimeException("Medium already empty");
                    }
                }
            }
        }
        return output;
    }

    /**
     * @param input    - input of the tank
     * @param quantity - amount to fill
     */
    public void fill(Object input, Integer quantity) {
        if (!isFull) {
            fillLoop(input, quantity);
        }
    }

    /**
     * @param quantity - amount
     * @return - list
     */
    public List<Object> remove(Integer quantity) {
        if (isEmpty || quantity > getAbsoluteFillState())
            throw new RuntimeException("Not enough stored in medium - Needed: " + quantity + " stored: " + getAbsoluteFillState());
        return removeLoop(quantity);
    }

    public Double getRelativeFillState() {
        Integer xLength = store.length;
        Integer yLength = store[0].length;
        Integer zLength = store[0][0].length;
        Integer count = this.countSlots();

        if (count == 0) return 0.0;
        return 1.0 / ((xLength * yLength * zLength) / count);
    }

    public Integer getAbsoluteFillState() {
        return countSlots();
    }

    public Integer getCapacity() {
        return capacity;
    }
}

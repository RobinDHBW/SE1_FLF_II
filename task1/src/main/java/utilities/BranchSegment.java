package utilities;

import java.util.ArrayList;
import java.util.List;

public class BranchSegment {

    public BranchSegment(Integer count, Integer[] length) {
        for (int i = 0; i < count; i++) {
            List<SegmentPart> branchSegments = new ArrayList<>();
            branchSegments.add(new SegmentPart(length[i]));
        }
    }

}

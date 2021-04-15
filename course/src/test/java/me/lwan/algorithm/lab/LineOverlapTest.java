package me.lwan.algorithm.lab;

public class LineOverlapTest extends BaseTest {

    private final LineOverlap lineOverlap = new LineOverlap();

    @Override
    public void runTest() {
        LineOverlap.Line[] lines = generateLines();

        int overlay1 = getMaxOverlapCount(lines);
        int overlay2 = lineOverlap.getMaxOverlapCount(lines);

        if (overlay1 != overlay2) {
            throw new RuntimeException("Error... Right Answer=" + overlay1 + ", Wrong Answer=" + overlay2);
        }

    }

    private LineOverlap.Line[] generateLines() {
        LineOverlap.Line[] arr = new LineOverlap.Line[nextInt(100, 100000)];
        for (int i = 0; i < arr.length; i++) {
            int start = nextInt(0, 1000);
            int end = start + nextInt(1, 1000);
            arr[i] = new LineOverlap.Line(start, end);
        }
        return arr;
    }

    private int getMaxOverlapCount(LineOverlap.Line[] lines) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (LineOverlap.Line line : lines) {
            start = Math.min(line.start, start);
            end = Math.max(line.end, end);
        }

        int count = 0;
        for (float point = start + 0.5f; point < end; point++) {
            int overlay = 0;
            for (LineOverlap.Line line : lines) {
                if (line.start < point && line.end > point) {
                    overlay++;
                }
            }

            count = Math.max(count, overlay);
        }

        return count;
    }

}

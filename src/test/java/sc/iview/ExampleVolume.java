package sc.iview;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.integer.UnsignedByteType;

/**
 * This method creates a RandomAccessibleInterval with random contents and shows it as a volume in SciView.
 */
public class ExampleVolume extends VistoolsExample {

    public static void main(String... args) {
        RandomAccessibleInterval<UnsignedByteType> demoImg = makeDemoImg(50, 50, 50);

        SvFunctions.show(demoImg, "demoVolume");
    }

}

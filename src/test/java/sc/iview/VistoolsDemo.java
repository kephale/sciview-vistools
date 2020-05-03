package sc.iview;

import graphics.scenery.Node;
import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.UnsignedByteType;

import java.util.Random;

public class VistoolsDemo {

    public static void main(String... args) {

        makeAndShowVolume();

    }

    /**
     * This method creates a RandomAccessibleInterval with random contents and shows it as a volume in SciView.
     */
    private static void makeAndShowVolume() {
        RandomAccessibleInterval<UnsignedByteType> demoImg = makeDemoImg(50, 50, 50);

        // add Options to sciview, have activate/center on new nodes as an option

        SciView sv = SvFunctions.show(demoImg, "demoVolume");
    }

    private static RandomAccessibleInterval<UnsignedByteType> makeDemoImg(int w, int h, int d) {
        Img<UnsignedByteType> out = ArrayImgs.unsignedBytes(w, h, d);

        int seed = 17;
        Random rng = new Random(seed);

        Cursor<UnsignedByteType> oCur = out.cursor();
        int maxVal = -1;
        while( oCur.hasNext() ) {
            oCur.fwd();
            if( maxVal < 0 )
                maxVal = (int) oCur.get().getMaxValue();
            oCur.get().set( rng.nextInt( maxVal ) );
        }

        return out;
    }

}

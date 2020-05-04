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

        //makeAndShowVolume();

    }

    /**
     * This method creates a RandomAccessibleInterval with random contents and shows it as a volume in SciView.
     */
    private static void makeAndShowVolume() {
        RandomAccessibleInterval<UnsignedByteType> demoImg = makeDemoImg(50, 50, 50);

        // add Options to sciview, have activate/center on new nodes as an option

        SciView sv = SvFunctions.show(demoImg, "demoVolume");
    }

    /**
     * Make an image of random noise with a gradient along Z (dimension==2)
     * @param w
     * @param h
     * @param d
     * @return
     */
    private static RandomAccessibleInterval<UnsignedByteType> makeDemoImg(int w, int h, int d) {
        Img<UnsignedByteType> out = ArrayImgs.unsignedBytes(w, h, d);

        int seed = 17;
        Random rng = new Random(seed);

        Cursor<UnsignedByteType> oCur = out.cursor();
        long[] pos = new long[3];
        int maxVal = -1;
        while( oCur.hasNext() ) {
            oCur.fwd();
            oCur.localize(pos);
            if( maxVal < 0 )
                maxVal = (int) oCur.get().getMaxValue() / d;
            int val = rng.nextInt((int) (maxVal * ( pos[2] + 1 )));
            oCur.get().set( val );
        }

        return out;
    }

}

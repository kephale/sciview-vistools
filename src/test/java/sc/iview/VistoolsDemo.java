package sc.iview;

import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.UnsignedByteType;

import java.util.Random;

public class VistoolsDemo {

    public static void main(String... args) {

        RandomAccessibleInterval<UnsignedByteType> demoImg = makeDemoImg(50, 50, 50);

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

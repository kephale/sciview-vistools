package sc.iview;

import com.google.common.io.ByteStreams;
import net.imagej.mesh.Mesh;
import net.imagej.mesh.io.stl.STLMeshIO;
import net.imagej.mesh.naive.NaiveFloatMesh;
import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.UnsignedByteType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class VistoolsExample {
    /**
     * Make an image of random noise with a gradient along Z (dimension==2)
     * @param w
     * @param h
     * @param d
     * @return
     */
    protected static RandomAccessibleInterval<UnsignedByteType> makeDemoImg(int w, int h, int d) {
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

    public static Mesh open(InputStream meshStream) {
        byte[] bytes;
        try {
            bytes = ByteStreams.toByteArray(meshStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        final Mesh mesh = new NaiveFloatMesh();
        new STLMeshIO().read(mesh, bytes);

        return mesh;
    }
}

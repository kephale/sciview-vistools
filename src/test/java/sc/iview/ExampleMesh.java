package sc.iview;

import com.google.common.io.ByteStreams;
import net.imagej.mesh.Mesh;
import net.imagej.mesh.io.stl.STLMeshIO;
import net.imagej.mesh.naive.NaiveFloatMesh;

import java.io.IOException;
import java.io.InputStream;

/**
 * This example creates a RandomAccessibleInterval with random contents and shows it as a volume in SciView.
 */
public class ExampleMesh {

    public static void main(String... args) {
        InputStream meshStream = SciView.class.getClassLoader().getResourceAsStream("WieseRobert_simplified_Cip1.stl");

        Mesh mesh = open(meshStream);

        SvFunctions.show(mesh, "demoMesh");
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

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
public class ExampleMesh extends VistoolsExample {

    public static void main(String... args) {
        InputStream meshStream = SciView.class.getClassLoader().getResourceAsStream("WieseRobert_simplified_Cip1.stl");

        Mesh mesh = open(meshStream);

        SvFunctions.show(mesh, "demoMesh");
    }


}

package sc.iview;

import net.imagej.mesh.Mesh;

import java.io.InputStream;

/**
 * This example creates a RandomAccessibleInterval with random contents and shows it as a volume in SciView.
 */
public class ExampleVolumeAndMesh extends VistoolsExample {

    public static void main(String... args) {
        InputStream meshStream = SciView.class.getClassLoader().getResourceAsStream("WieseRobert_simplified_Cip1.stl");

        Mesh mesh = open(meshStream);

        SciView sv = SvFunctions.show(mesh, "demoMesh");

        SvFunctions.show( makeDemoImg(50, 50, 50), "exampleVolume", new SvOptions().addTo(sv) );
    }


}

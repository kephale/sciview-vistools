package sc.iview;

import graphics.scenery.Node;
import net.imagej.mesh.Mesh;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.RealType;

/**
 * All show methods return a SciView which can be used to add more stuff to the same instance
 * Modeled after BigDataViewer's BdvFunctions
 *
 * @author Kyle Harrington
 */
public class SvFunctions {

    // Show methods for volumes
    public static < T extends RealType > SciView show( final RandomAccessibleInterval< T > img, final String name ) {
        return show( img, name, new SvOptions() );
    }

    public static < T extends RealType > SciView show(RandomAccessibleInterval<T> img, String name, SvOptions options) {
        SciView sciView = options.getAddTo();
        sciView.addVolume(img, name);
        return sciView;
    }

    // Show methods for meshes
    public static SciView show(Mesh mesh, String name) {
        return show(mesh, name, new SvOptions());
    }

    public static SciView show(Mesh mesh, String name, SvOptions options) {
        SciView sciView = options.getAddTo();
        Node n = sciView.addMesh(mesh);
        n.setName(name);
        return sciView;
    }
}

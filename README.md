# sciview-vistools

Helpers to use SciView in other projects without needing to directly call ImageJ or Scijava code.

# Usage

From `sc.iview.ExampleVolume`

```
RandomAccessibleInterval<UnsignedByteType> demoImg = makeDemoImg(50, 50, 50);
SvFunctions.show(demoImg, "demoVolume");
```

From `sc.iview.ExampleVolumeAndMesh`

```
InputStream meshStream = SciView.class.getClassLoader().getResourceAsStream("WieseRobert_simplified_Cip1.stl");
Mesh mesh = open(meshStream);
SciView sv = SvFunctions.show(mesh, "demoMesh");
SvFunctions.show( makeDemoImg(50, 50, 50), "exampleVolume", new SvOptions().addTo(sv) );
```
